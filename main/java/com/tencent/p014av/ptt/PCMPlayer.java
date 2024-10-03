package com.tencent.p014av.ptt;

import android.media.AudioTrack;
import com.tencent.p014av.ptt.PttListener;
import com.tencent.p014av.utils.QLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PCMPlayer {
    public String TAG;
    private AudioTrack audioTrack;
    private int bufferSize;
    private int channel;
    private int format;
    PlayThread pcmPlayer;
    public int playGain;
    public int playLevel;
    private int sampleRate;

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeProcess(byte[] bArr, int i, int i2, int i3, int i4);

    public int getBufferSize() {
        return this.bufferSize;
    }

    public PCMPlayer() {
        this.TAG = "PCMPlayer";
        this.sampleRate = 16000;
        this.channel = 4;
        this.format = 2;
        this.playLevel = 0;
        this.playGain = 100;
        this.pcmPlayer = null;
    }

    public PCMPlayer(int i, int i2, int i3) {
        this.TAG = "PCMPlayer";
        this.playLevel = 0;
        this.playGain = 100;
        this.pcmPlayer = null;
        this.sampleRate = i;
        this.channel = i2;
        this.format = i3;
    }

    public boolean initPCMPlayer() {
        if (this.audioTrack != null) {
            QLog.m602i(this.TAG, "init pcm player, audio track not null, release first!");
            this.audioTrack.release();
            this.audioTrack = null;
        }
        int i = this.sampleRate;
        int i2 = this.channel;
        int i3 = this.format;
        this.audioTrack = new AudioTrack(3, i, i2, i3, i3, 1);
        return true;
    }

    public int getSilkFilePlayTime(String str) {
        int i;
        FileInputStream fileInputStream;
        String str2;
        StringBuilder sb;
        int i2 = 0;
        int i3 = 0;
        try {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    QLog.m602i(this.TAG, "playLength = 0 time = 0 , silk count = 0");
                    return 0;
                }
                fileInputStream = new FileInputStream(file);
                try {
                    int available = fileInputStream.available();
                    i = 9;
                    try {
                        byte[] bArr = new byte[available];
                        int read = fileInputStream.read(bArr);
                        fileInputStream.close();
                        int i4 = 0;
                        int i5 = 9;
                        int i6 = 0;
                        while (i5 < read - 1 && i5 < available - 1) {
                            i4++;
                            try {
                                i6 += 20;
                                i5 += (bArr[i5 + 1] << 8) + bArr[i5] + 2;
                            } catch (FileNotFoundException e) {
                                e = e;
                                int i7 = i6;
                                i = i5;
                                i2 = i4;
                                fileInputStream = null;
                                i3 = i7;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                str2 = this.TAG;
                                sb = new StringBuilder("playLength = ");
                                sb.append(i);
                                sb.append(" time = ");
                                sb.append(i3);
                                sb.append(" , silk count = ");
                                sb.append(i2);
                                QLog.m602i(str2, sb.toString());
                                return i3;
                            } catch (IOException e3) {
                                e = e3;
                                int i8 = i6;
                                i = i5;
                                i2 = i4;
                                fileInputStream = null;
                                i3 = i8;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                str2 = this.TAG;
                                sb = new StringBuilder("playLength = ");
                                sb.append(i);
                                sb.append(" time = ");
                                sb.append(i3);
                                sb.append(" , silk count = ");
                                sb.append(i2);
                                QLog.m602i(str2, sb.toString());
                                return i3;
                            } catch (Exception e5) {
                                e = e5;
                                int i9 = i6;
                                i = i5;
                                i2 = i4;
                                fileInputStream = null;
                                i3 = i9;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                str2 = this.TAG;
                                sb = new StringBuilder("playLength = ");
                                sb.append(i);
                                sb.append(" time = ");
                                sb.append(i3);
                                sb.append(" , silk count = ");
                                sb.append(i2);
                                QLog.m602i(str2, sb.toString());
                                return i3;
                            } catch (Throwable unused) {
                                int i10 = i6;
                                i = i5;
                                i2 = i4;
                                fileInputStream = null;
                                i3 = i10;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e7) {
                                        e7.printStackTrace();
                                    }
                                }
                                str2 = this.TAG;
                                sb = new StringBuilder("playLength = ");
                                sb.append(i);
                                sb.append(" time = ");
                                sb.append(i3);
                                sb.append(" , silk count = ");
                                sb.append(i2);
                                QLog.m602i(str2, sb.toString());
                                return i3;
                            }
                        }
                        QLog.m602i(this.TAG, " getSilkFilePlayTime fileName:" + str + "length :" + read);
                        QLog.m602i(this.TAG, "playLength = " + i5 + " time = " + i6 + " , silk count = " + i4);
                        return i6;
                    } catch (FileNotFoundException e8) {
                        e = e8;
                        i3 = 0;
                    } catch (IOException e9) {
                        e = e9;
                        i3 = 0;
                    } catch (Exception e10) {
                        e = e10;
                        i3 = 0;
                    } catch (Throwable unused2) {
                        i3 = 0;
                    }
                } catch (FileNotFoundException e11) {
                    e = e11;
                    i3 = 0;
                    i = 0;
                } catch (IOException e12) {
                    e = e12;
                    i3 = 0;
                    i = 0;
                } catch (Exception e13) {
                    e = e13;
                    i3 = 0;
                    i = 0;
                } catch (Throwable unused3) {
                    i3 = 0;
                    i = 0;
                }
            } catch (FileNotFoundException e14) {
                e = e14;
                i = 0;
                fileInputStream = null;
                i3 = 0;
            } catch (IOException e15) {
                e = e15;
                i = 0;
                fileInputStream = null;
                i3 = 0;
            } catch (Exception e16) {
                e = e16;
                i = 0;
                fileInputStream = null;
                i3 = 0;
            } catch (Throwable unused4) {
                i = 0;
                fileInputStream = null;
                i3 = 0;
            }
        } catch (Throwable unused5) {
        }
    }

    public boolean isPlaying() {
        PlayThread playThread = this.pcmPlayer;
        return playThread != null && playThread.isRunning;
    }

    public void play(String str, PttListener.PlayFileListener playFileListener) {
        if (str == null || str.length() == 0) {
            playFileListener.onCompleted(PttError.PLAYER_PARAM_NULL, null);
            return;
        }
        PlayThread playThread = this.pcmPlayer;
        if (playThread == null || !playThread.isRunning) {
            this.pcmPlayer = new PlayThread(str, playFileListener);
            this.audioTrack.play();
            this.pcmPlayer.start();
        } else {
            QLog.m602i(this.TAG, "file is playing , not play again!");
            playFileListener.onCompleted(PttError.PLAYER_PLAYING_ERROR, null);
        }
    }

    public void stop() {
        PlayThread playThread = this.pcmPlayer;
        if (playThread != null && playThread.isRunning) {
            QLog.m602i(this.TAG, "stop silk player ");
            this.pcmPlayer.isRunning = false;
            try {
                this.pcmPlayer.join();
            } catch (Exception e) {
                QLog.m602i(this.TAG, "join  pcmPlayer thread error" + e.getMessage());
            }
        }
        QLog.m602i(this.TAG, "stop silk player end!");
    }

    /* loaded from: classes3.dex */
    class PlayThread extends Thread {
        PttListener.PlayFileListener callBack;
        public volatile boolean isRunning = true;
        public String playPath;

        public PlayThread(String str, PttListener.PlayFileListener playFileListener) {
            this.playPath = str;
            this.callBack = playFileListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:67:0x01f6  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0215 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r9v12, types: [int] */
        /* JADX WARN: Type inference failed for: r9v13 */
        /* JADX WARN: Type inference failed for: r9v14, types: [boolean] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 550
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.ptt.PCMPlayer.PlayThread.run():void");
        }
    }
}
