package com.xsdk.ab_firstbase.statisics.util;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class AESUtil {
    public static final String E_K_3 = "eGMtJQ==";
    private static final String TAG = "ED";

    public static String aesGcmEncrypt(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, new SecretKeySpec(str2.getBytes(), "AES"));
            byte[] iv = cipher.getIV();
            byte[] doFinal = cipher.doFinal(str.getBytes("utf-8"));
            byte[] bArr = new byte[iv.length + doFinal.length];
            System.arraycopy(iv, 0, bArr, 0, iv.length);
            System.arraycopy(doFinal, 0, bArr, iv.length, doFinal.length);
            return Base64.encode(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String aesGcmDecrypt(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            byte[] decode = Base64.decode(str);
            cipher.init(2, secretKeySpec, new GCMParameterSpec(128, decode, 0, 12));
            return new String(cipher.doFinal(decode, 12, decode.length - 12), "utf-8");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void test(String str) throws Exception {
        String aesGcmDecrypt = aesGcmDecrypt("1JDOIkp63eznXiN6Wg4Tohd2J3UyWgEUXydxejAaDEDkBokEgURCUDRKLqLTy6q8nm+oC8Tx45s8UM7tgnqynG0SX0jQeZKmTw/dUuyDl8j2mOEDsOBbG8EHI13TWQ+5d/wwRSMNZU3d2USdmVgJSOwYL+FUQYDSQRDjoAzOo0zcUTWVvyKFP2wp45AslkagpqVFKtCQlNWq+P5/lCsWtfo5lBRPHfWNYWdsDcFqPh4+ZkUHYVDbwiaHaPwpGhDDKEYlHGtenoBXy4sgwc7IzSZeGBdJNXmpV/EonbLuhtdwFl4s48Ip+pvZr31P78ekcimK01H+d3se5DsqToKepCC/UkYRNXIU6NXCT1vlNz34rJXAzwcug6z34zeewDv6yqMfSuZeLoGXV484Gr4oDiMcE8+IsG9iXNtBreh9pyYvxv0xVkyVhhtftzdeDdZ5PRTdP9QG91tK8FnCMQcIFcok6CG6KLJIUTq/qAxDNAJV74H9zedqHsowwq/VgjcNf8bNeiI2VPDM1q800zLpbUNb8kZIPne2Q8554avK6/5HbifjxZTxfO1J1BDRxv72PS1zxuJqp2m5vuNK9WWSsEHhCLuMY72gelvHaOf5aTOvP6IzAvIKKO8Y8Pvi1S8cG+9fvZdYMxD0Kw3HggXJ+53/BqF31dDn5u/Zpr9F23mq07nA3eOVwRvLnUPm5BQcjgG3MZeMK4PYrrYTS7iaIpViV9FzFHZNHsssonFOnEC/cHqEkAkzISVYaBDv3jv3u0kAt0R32kydn7P0EuC/vOI3ltICdRzH/I+zkjafnMsqaYObtgSBHld3C1IkkeAJt5NlydLU65BaEQsknlXCWbzefA8hWiN4jZcX87iRcJ3OfCufrQdtVBP0tujOGHXjWFoHdA1KCVGU762yICl3KGULThqwghWice+9yxQW2Vp5Rd7EBR1uSnbARxKPW4n2YEbB6KQkLWGzS9p6xA==", str);
        System.out.println("aes-jie mi hou：" + aesGcmDecrypt);
    }
}
