package com.xsdk.ab_firstbase.statisics.util;

import android.text.TextUtils;
import com.google.common.base.Ascii;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
public class MD5 {
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest;

    static {
        messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(MD5.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            e.printStackTrace();
        }
    }

    public static synchronized String getMD5String(String str) {
        synchronized (MD5.class) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            return getMD5String(str.getBytes());
        }
    }

    public static synchronized String getMD5String(byte[] bArr) {
        String bufferToHex;
        synchronized (MD5.class) {
            messagedigest.update(bArr);
            bufferToHex = bufferToHex(messagedigest.digest());
        }
        return bufferToHex;
    }

    private static synchronized String bufferToHex(byte[] bArr) {
        String bufferToHex;
        synchronized (MD5.class) {
            bufferToHex = bufferToHex(bArr, 0, bArr.length);
        }
        return bufferToHex;
    }

    private static synchronized String bufferToHex(byte[] bArr, int i, int i2) {
        String stringBuffer;
        synchronized (MD5.class) {
            StringBuffer stringBuffer2 = new StringBuffer(i2 * 2);
            int i3 = i2 + i;
            while (i < i3) {
                appendHexPair(bArr[i], stringBuffer2);
                i++;
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    private static synchronized void appendHexPair(byte b, StringBuffer stringBuffer) {
        synchronized (MD5.class) {
            char[] cArr = hexDigits;
            char c = cArr[(b & 240) >> 4];
            char c2 = cArr[b & Ascii.f555SI];
            stringBuffer.append(c);
            stringBuffer.append(c2);
        }
    }
}
