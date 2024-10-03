package com.tencent.p014av.utils;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class NioUtils {
    public static final String TAG = "NioUtils";

    public static boolean destroyDirectByteBuffer(ByteBuffer byteBuffer) {
        QLog.m598d(TAG, "destroyDirectByteBuffer start");
        if (!byteBuffer.isDirect()) {
            return false;
        }
        try {
            byteBuffer.getClass().getMethod("free", new Class[0]).invoke(byteBuffer, new Object[0]);
            QLog.m598d(TAG, "destroyDirectByteBuffer end");
            return true;
        } catch (Exception e) {
            QLog.m601e(TAG, "destroyDirectByteBuffer", e);
            return false;
        }
    }

    public static ByteBuffer createDirectByteBuffer(int i) {
        QLog.m598d(TAG, "createDirectByteBuffer len = " + i);
        return ByteBuffer.allocateDirect(i);
    }
}
