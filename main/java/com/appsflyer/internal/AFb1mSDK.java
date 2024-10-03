package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes.dex */
public final class AFb1mSDK {
    public static String valueOf(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return AFInAppEventType(messageDigest.digest());
        } catch (Exception e) {
            AFLogger.afErrorLog("Error turning data to SHA-256 string", e);
            return null;
        }
    }

    private static String AFInAppEventType(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte[] AFKeystoreWrapper(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return messageDigest.digest();
        } catch (Exception e) {
            AFLogger.afErrorLog("Error turning string to SHA-256 byte array", e);
            return null;
        }
    }

    public static String AFInAppEventType(String str, String str2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(str2.getBytes(Charset.defaultCharset()), "HmacSHA256"));
            return AFInAppEventType(mac.doFinal(str.getBytes(Charset.defaultCharset()))).toLowerCase(Locale.getDefault());
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            AFLogger.afErrorLog(e.getMessage(), e, true);
            return e.getMessage();
        }
    }

    public static boolean AFInAppEventType(Map<String, Object> map, String[] strArr, AFd1sSDK aFd1sSDK) throws IllegalStateException {
        if (map == null || map.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (!map.containsKey(str)) {
                return false;
            }
        }
        String str2 = (String) map.remove("sig");
        if (str2 == null) {
            return false;
        }
        String m83e = AFd1sSDK.m83e();
        StringBuilder sb = new StringBuilder();
        sb.append(new JSONObject(map));
        sb.append(m83e);
        return AFInAppEventType(sb.toString(), AFb1kSDK.AFInAppEventType(aFd1sSDK.AFInAppEventParameterName, aFd1sSDK.AFKeystoreWrapper)).equals(str2);
    }
}
