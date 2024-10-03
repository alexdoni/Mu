package com.linecorp.android.security;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public class TLSSocketFactory extends SSLSocketFactory {
    private static final String TAG = "TLSSocketFactory";
    private static final int TLS12_ENABLED_API_LEVEL = 16;
    private static final String[] UNSAFE_CIPHERS = {"RC4", "DES", "PSK", "_DHE_"};
    private Class<?> openSslSocketClass;
    private boolean removeUnsafeCiphers;
    private Method setHostnameMethod;
    private final SSLSocketFactory sslSocketFactory;

    public TLSSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this(sSLSocketFactory, true);
    }

    public TLSSocketFactory(SSLSocketFactory sSLSocketFactory, boolean z) {
        this.sslSocketFactory = sSLSocketFactory;
        this.removeUnsafeCiphers = z;
        initSNI();
    }

    private void initSNI() {
        try {
            try {
                this.openSslSocketClass = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
            } catch (ClassNotFoundException unused) {
                this.openSslSocketClass = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            }
            this.setHostnameMethod = this.openSslSocketClass.getMethod("setHostname", String.class);
        } catch (ClassNotFoundException | NoSuchMethodException unused2) {
        }
    }

    private static String[] getProtocols() {
        return new String[]{"TLSv1.2"};
    }

    /* loaded from: classes2.dex */
    private static class LoggingHandshakeCompletedListener implements HandshakeCompletedListener {
        private LoggingHandshakeCompletedListener() {
        }

        @Override // javax.net.ssl.HandshakeCompletedListener
        public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            String str;
            SSLSession session = handshakeCompletedEvent.getSession();
            String protocol = session.getProtocol();
            String cipherSuite = session.getCipherSuite();
            Log.d(TLSSocketFactory.TAG, "Handshake completed", new Throwable("This is not Error."));
            Log.d(TLSSocketFactory.TAG, String.format("Connected with: %s/%s", protocol, cipherSuite));
            try {
                str = session.getPeerPrincipal().getName();
            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
                str = null;
            }
            Log.d(TLSSocketFactory.TAG, String.format("Peer name: %s\n", str));
        }
    }

    private void setHostname(Socket socket, String str) {
        Method method;
        if (!this.openSslSocketClass.isInstance(socket) || (method = this.setHostnameMethod) == null) {
            return;
        }
        try {
            method.invoke(socket, str);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket createSocket = this.sslSocketFactory.createSocket(socket, str, i, z);
        setHostname(createSocket, str);
        return wrapSocket(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        Socket createSocket = this.sslSocketFactory.createSocket(str, i);
        setHostname(createSocket, str);
        return wrapSocket(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return wrapSocket(this.sslSocketFactory.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return wrapSocket(this.sslSocketFactory.createSocket(inetAddress, i, inetAddress2, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        Socket createSocket = this.sslSocketFactory.createSocket(str, i, inetAddress, i2);
        setHostname(createSocket, str);
        return wrapSocket(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return wrapSocket(this.sslSocketFactory.createSocket());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        if (this.removeUnsafeCiphers) {
            return removeUnsafeCiphers(this.sslSocketFactory.getDefaultCipherSuites());
        }
        return this.sslSocketFactory.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        if (this.removeUnsafeCiphers) {
            return removeUnsafeCiphers(this.sslSocketFactory.getSupportedCipherSuites());
        }
        return this.sslSocketFactory.getSupportedCipherSuites();
    }

    public Socket wrapSocket(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            return socket;
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        sSLSocket.setEnabledProtocols(getProtocols());
        if (this.removeUnsafeCiphers) {
            sSLSocket.setEnabledCipherSuites(removeUnsafeCiphers(sSLSocket.getEnabledCipherSuites()));
        }
        return new NoSSLv3SSLSocket(sSLSocket);
    }

    private static String[] removeUnsafeCiphers(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        for (String str : strArr) {
            for (String str2 : UNSAFE_CIPHERS) {
                if (str.contains(str2)) {
                    arrayList.remove(str);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NoSSLv3SSLSocket extends DelegateSSLSocket {
        private NoSSLv3SSLSocket(SSLSocket sSLSocket) {
            super(sSLSocket);
        }

        @Override // com.linecorp.android.security.TLSSocketFactory.DelegateSSLSocket, javax.net.ssl.SSLSocket
        public void setEnabledProtocols(String[] strArr) {
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                ArrayList arrayList = new ArrayList(Arrays.asList(this.delegate.getEnabledProtocols()));
                if (arrayList.size() > 1) {
                    arrayList.remove("SSLv3");
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    /* loaded from: classes2.dex */
    private static class DelegateSSLSocket extends SSLSocket {
        protected final SSLSocket delegate;

        DelegateSSLSocket(SSLSocket sSLSocket) {
            this.delegate = sSLSocket;
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getSupportedCipherSuites() {
            return this.delegate.getSupportedCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getEnabledCipherSuites() {
            return this.delegate.getEnabledCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setEnabledCipherSuites(String[] strArr) {
            this.delegate.setEnabledCipherSuites(strArr);
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getSupportedProtocols() {
            return this.delegate.getSupportedProtocols();
        }

        @Override // javax.net.ssl.SSLSocket
        public String[] getEnabledProtocols() {
            return this.delegate.getEnabledProtocols();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setEnabledProtocols(String[] strArr) {
            this.delegate.setEnabledProtocols(strArr);
        }

        @Override // javax.net.ssl.SSLSocket
        public SSLSession getSession() {
            return this.delegate.getSession();
        }

        @Override // javax.net.ssl.SSLSocket
        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.delegate.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        @Override // javax.net.ssl.SSLSocket
        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.delegate.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        @Override // javax.net.ssl.SSLSocket
        public void startHandshake() throws IOException {
            this.delegate.startHandshake();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setUseClientMode(boolean z) {
            this.delegate.setUseClientMode(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getUseClientMode() {
            return this.delegate.getUseClientMode();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setNeedClientAuth(boolean z) {
            this.delegate.setNeedClientAuth(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public void setWantClientAuth(boolean z) {
            this.delegate.setWantClientAuth(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getNeedClientAuth() {
            return this.delegate.getNeedClientAuth();
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getWantClientAuth() {
            return this.delegate.getWantClientAuth();
        }

        @Override // javax.net.ssl.SSLSocket
        public void setEnableSessionCreation(boolean z) {
            this.delegate.setEnableSessionCreation(z);
        }

        @Override // javax.net.ssl.SSLSocket
        public boolean getEnableSessionCreation() {
            return this.delegate.getEnableSessionCreation();
        }

        @Override // java.net.Socket
        public void bind(SocketAddress socketAddress) throws IOException {
            this.delegate.bind(socketAddress);
        }

        @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            this.delegate.close();
        }

        @Override // java.net.Socket
        public void connect(SocketAddress socketAddress) throws IOException {
            this.delegate.connect(socketAddress);
        }

        @Override // java.net.Socket
        public void connect(SocketAddress socketAddress, int i) throws IOException {
            this.delegate.connect(socketAddress, i);
        }

        @Override // java.net.Socket
        public SocketChannel getChannel() {
            return this.delegate.getChannel();
        }

        @Override // java.net.Socket
        public InetAddress getInetAddress() {
            return this.delegate.getInetAddress();
        }

        @Override // java.net.Socket
        public InputStream getInputStream() throws IOException {
            return this.delegate.getInputStream();
        }

        @Override // java.net.Socket
        public boolean getKeepAlive() throws SocketException {
            return this.delegate.getKeepAlive();
        }

        @Override // java.net.Socket
        public InetAddress getLocalAddress() {
            return this.delegate.getLocalAddress();
        }

        @Override // java.net.Socket
        public int getLocalPort() {
            return this.delegate.getLocalPort();
        }

        @Override // java.net.Socket
        public SocketAddress getLocalSocketAddress() {
            return this.delegate.getLocalSocketAddress();
        }

        @Override // java.net.Socket
        public boolean getOOBInline() throws SocketException {
            return this.delegate.getOOBInline();
        }

        @Override // java.net.Socket
        public OutputStream getOutputStream() throws IOException {
            return this.delegate.getOutputStream();
        }

        @Override // java.net.Socket
        public int getPort() {
            return this.delegate.getPort();
        }

        @Override // java.net.Socket
        public synchronized int getReceiveBufferSize() throws SocketException {
            return this.delegate.getReceiveBufferSize();
        }

        @Override // java.net.Socket
        public SocketAddress getRemoteSocketAddress() {
            return this.delegate.getRemoteSocketAddress();
        }

        @Override // java.net.Socket
        public boolean getReuseAddress() throws SocketException {
            return this.delegate.getReuseAddress();
        }

        @Override // java.net.Socket
        public synchronized int getSendBufferSize() throws SocketException {
            return this.delegate.getSendBufferSize();
        }

        @Override // java.net.Socket
        public int getSoLinger() throws SocketException {
            return this.delegate.getSoLinger();
        }

        @Override // java.net.Socket
        public synchronized int getSoTimeout() throws SocketException {
            return this.delegate.getSoTimeout();
        }

        @Override // java.net.Socket
        public boolean getTcpNoDelay() throws SocketException {
            return this.delegate.getTcpNoDelay();
        }

        @Override // java.net.Socket
        public int getTrafficClass() throws SocketException {
            return this.delegate.getTrafficClass();
        }

        @Override // java.net.Socket
        public boolean isBound() {
            return this.delegate.isBound();
        }

        @Override // java.net.Socket
        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        @Override // java.net.Socket
        public boolean isConnected() {
            return this.delegate.isConnected();
        }

        @Override // java.net.Socket
        public boolean isInputShutdown() {
            return this.delegate.isInputShutdown();
        }

        @Override // java.net.Socket
        public boolean isOutputShutdown() {
            return this.delegate.isOutputShutdown();
        }

        @Override // java.net.Socket
        public void sendUrgentData(int i) throws IOException {
            this.delegate.sendUrgentData(i);
        }

        @Override // java.net.Socket
        public void setKeepAlive(boolean z) throws SocketException {
            this.delegate.setKeepAlive(z);
        }

        @Override // java.net.Socket
        public void setOOBInline(boolean z) throws SocketException {
            this.delegate.setOOBInline(z);
        }

        @Override // java.net.Socket
        public void setPerformancePreferences(int i, int i2, int i3) {
            this.delegate.setPerformancePreferences(i, i2, i3);
        }

        @Override // java.net.Socket
        public synchronized void setReceiveBufferSize(int i) throws SocketException {
            this.delegate.setReceiveBufferSize(i);
        }

        @Override // java.net.Socket
        public void setReuseAddress(boolean z) throws SocketException {
            this.delegate.setReuseAddress(z);
        }

        @Override // java.net.Socket
        public synchronized void setSendBufferSize(int i) throws SocketException {
            this.delegate.setSendBufferSize(i);
        }

        @Override // java.net.Socket
        public void setSoLinger(boolean z, int i) throws SocketException {
            this.delegate.setSoLinger(z, i);
        }

        @Override // java.net.Socket
        public synchronized void setSoTimeout(int i) throws SocketException {
            this.delegate.setSoTimeout(i);
        }

        @Override // java.net.Socket
        public void setTcpNoDelay(boolean z) throws SocketException {
            this.delegate.setTcpNoDelay(z);
        }

        @Override // java.net.Socket
        public void setTrafficClass(int i) throws SocketException {
            this.delegate.setTrafficClass(i);
        }

        @Override // java.net.Socket
        public void shutdownInput() throws IOException {
            this.delegate.shutdownInput();
        }

        @Override // java.net.Socket
        public void shutdownOutput() throws IOException {
            this.delegate.shutdownOutput();
        }

        @Override // javax.net.ssl.SSLSocket, java.net.Socket
        public String toString() {
            return this.delegate.toString();
        }

        public boolean equals(Object obj) {
            return this.delegate.equals(obj);
        }
    }
}
