package com.tencent.p014av.utils;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
public class AVDeviceHelper {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getOrigMacAddr(Context context) {
        return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
    }

    private static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = DIGITS_LOWER;
            cArr[i] = cArr2[(b & 240) >>> 4];
            i = i2 + 1;
            cArr[i2] = cArr2[b & Ascii.f555SI];
        }
        return new String(cArr);
    }

    public static String string2Md5(String str) {
        if (str == null) {
            return "";
        }
        try {
            return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getDeviceIdentifier(Context context) {
        return doRead(context) + ";" + getOrigMacAddr(context) + ";" + getOrigAndroidID(context);
    }

    public static String doRead(Context context) {
        return string2Md5("");
    }

    public static String getOrigAndroidID(Context context) {
        return string2Md5("");
    }

    public static String getSimulateIDFA(Context context) {
        return doRead(context) + ";" + getOrigMacAddr(context) + ";" + getOrigAndroidID(context);
    }
}
