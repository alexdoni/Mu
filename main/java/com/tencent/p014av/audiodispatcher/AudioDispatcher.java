package com.tencent.p014av.audiodispatcher;

import com.tencent.p014av.utils.QLog;
import com.tencent.p014av.wrapper.GMEJavaInstance;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class AudioDispatcher {
    private static final String TAG = "AudioDispatcher";
    private static final int TRY_BIND_SOCKET_TIMES = 20;
    private static AudioDispatcher mAudioDispatcher;
    private static ArrayBlockingQueue<byte[]> mQueue = new ArrayBlockingQueue<>(10);
    private ServerSocket mServerSocket;
    private SocketClient mSocketClient;
    private int mPort = 7878;
    private AtomicBoolean mIsSendingAudioData = new AtomicBoolean(false);
    private AtomicBoolean mIsWaitingClient = new AtomicBoolean(false);

    public static AudioDispatcher getInstance() {
        if (mAudioDispatcher == null) {
            mAudioDispatcher = new AudioDispatcher();
        }
        return mAudioDispatcher;
    }

    public void offerAudioData(byte[] bArr) {
        if (bArr != null) {
            if (mQueue.remainingCapacity() <= 1) {
                mQueue.poll();
            }
            mQueue.add(bArr);
        }
    }

    public boolean isNeedOfferAudioData() {
        return this.mIsSendingAudioData.get();
    }

    public void onAudioCaptureChange(boolean z) {
        GMEAudioBroadcast.getInstance().onAudioCaptureChange(z);
    }

    public void onAudioEnableBroadcast(boolean z) {
        if (z) {
            GMEAudioBroadcast.getInstance().registerBroadcast(GMEJavaInstance.getmActivity());
        } else {
            GMEAudioBroadcast.getInstance().unRegisterBroadcast(GMEJavaInstance.getmActivity());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] pollAudioData() {
        return mQueue.poll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAudioQueue() {
        mQueue.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean startServer() {
        QLog.m600e(TAG, "startServer");
        if (this.mIsSendingAudioData.get()) {
            QLog.m602i(TAG, "AudioDispatcher is sending audio data. request refuse");
            return false;
        }
        if (this.mIsWaitingClient.get()) {
            QLog.m602i(TAG, "AudioDispatcher Server is ready and waiting accept.");
            return true;
        }
        ServerSocket TryBindSocket = TryBindSocket();
        this.mServerSocket = TryBindSocket;
        if (TryBindSocket == null) {
            QLog.m602i(TAG, "AudioDispatcher Server TryBindSocket failed");
            return false;
        }
        QLog.m602i(TAG, "AudioDispatcher Server start");
        new AudioServer().start();
        return true;
    }

    public int getPort() {
        return this.mPort;
    }

    public void onRecordStateChange(boolean z) {
        if (z) {
            return;
        }
        QLog.m600e(TAG, "onRecordStateChange:" + z + " try closeSocketClient");
        closeSocketClient();
    }

    public void closeSocketClient() {
        SocketClient socketClient = this.mSocketClient;
        if (socketClient != null) {
            socketClient.stopClient();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class AudioServer extends Thread {
        private AudioServer() {
            QLog.m600e(AudioDispatcher.TAG, "AudioServer start");
            AudioDispatcher.this.mIsWaitingClient.set(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Socket accept = AudioDispatcher.this.mServerSocket.accept();
                AudioDispatcher.this.mSocketClient = new SocketClient(accept);
                AudioDispatcher.this.mSocketClient.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AudioDispatcher.this.mIsWaitingClient.set(false);
            QLog.m600e(AudioDispatcher.TAG, "AudioServer end");
        }
    }

    private ServerSocket TryBindSocket() {
        ServerSocket serverSocket;
        IOException e;
        BindException e2;
        ServerSocket serverSocket2 = null;
        int i = 0;
        boolean z = true;
        do {
            try {
                int i2 = this.mPort + 1;
                this.mPort = i2;
                serverSocket = new ServerSocket(i2);
            } catch (BindException e3) {
                serverSocket = serverSocket2;
                e2 = e3;
            } catch (IOException e4) {
                serverSocket = serverSocket2;
                e = e4;
            }
            try {
                QLog.m600e(TAG, "try to bind Socket" + this.mPort);
                z = false;
            } catch (BindException e5) {
                e2 = e5;
                e2.printStackTrace();
                i++;
                if (i >= 20) {
                    return serverSocket;
                }
                QLog.m600e(TAG, "BindException" + e2.getMessage());
                serverSocket2 = serverSocket;
            } catch (IOException e6) {
                e = e6;
                QLog.m600e(TAG, "IOException" + e.getMessage());
                return serverSocket;
            }
            serverSocket2 = serverSocket;
        } while (z);
        return serverSocket2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class SocketClient extends Thread {
        private boolean mFlag = true;
        private Socket mSocket;

        public SocketClient(Socket socket) {
            this.mSocket = socket;
            AudioDispatcher.this.clearAudioQueue();
            AudioDispatcher.this.mIsSendingAudioData.set(true);
        }

        public void stopClient() {
            this.mFlag = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            QLog.m600e(AudioDispatcher.TAG, "start Send audio data");
            try {
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(this.mSocket.getOutputStream());
                    do {
                        byte[] pollAudioData = AudioDispatcher.this.pollAudioData();
                        if (pollAudioData == null) {
                            Thread.sleep(10L);
                        } else {
                            dataOutputStream.write(pollAudioData);
                        }
                    } while (this.mFlag);
                    dataOutputStream.close();
                    this.mSocket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    QLog.m600e(AudioDispatcher.TAG, e2.getMessage());
                    this.mSocket.close();
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    this.mSocket.close();
                }
                AudioDispatcher.this.mIsSendingAudioData.set(false);
                QLog.m600e(AudioDispatcher.TAG, "stop Send audio data");
            } catch (Throwable th) {
                try {
                    this.mSocket.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
    }
}
