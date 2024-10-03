package com.linecorp.android.security.encryption;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class StringCipher {
    private static final int AES_KEY_SIZE_IN_BIT = 256;
    private static final int DEFAULT_ITERATIONS = 10000;
    private static final int HMAC_KEY_SIZE_IN_BIT = 256;
    private static final int HMAC_SIZE_IN_BYTE = 32;
    private static final int IV_SIZE_IN_BYTE = 16;
    private static final String SALT_SHARED_PREFERENCE_KEY = "salt";
    private static final int SALT_SIZE_IN_BYTE = 16;
    private final Cipher cipher;
    private final Mac hmac;
    private boolean isSerialIncludedInDevicePackageSpecificId;
    private final SecretKeyFactory keyFactory;
    private final int pbkdf2IterationCount;
    private SecretKeys secretKeys;
    private final SecureRandom secureRandom;
    private final String sharedPreferenceName;
    private final Object syncObject;

    public StringCipher(String str) {
        this(str, DEFAULT_ITERATIONS, false);
    }

    public StringCipher(String str, int i, boolean z) {
        this.syncObject = new Object();
        this.sharedPreferenceName = str;
        this.pbkdf2IterationCount = i;
        this.isSerialIncludedInDevicePackageSpecificId = z;
        try {
            this.secureRandom = new SecureRandom();
            this.keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.hmac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(Context context) {
        synchronized (this.syncObject) {
            if (this.secretKeys == null) {
                this.secretKeys = getSecretKeys(context);
            }
        }
    }

    public String encrypt(Context context, String str) {
        String encodeToString;
        synchronized (this.syncObject) {
            initialize(context);
            try {
                int blockSize = this.cipher.getBlockSize();
                byte[] bArr = new byte[blockSize];
                this.secureRandom.nextBytes(bArr);
                this.cipher.init(1, this.secretKeys.encryptionKey, new IvParameterSpec(bArr));
                byte[] doFinal = this.cipher.doFinal(str.getBytes("UTF-8"));
                byte[] bArr2 = new byte[doFinal.length + blockSize + 32];
                System.arraycopy(bArr, 0, bArr2, 0, blockSize);
                int i = blockSize + 0;
                System.arraycopy(doFinal, 0, bArr2, i, doFinal.length);
                this.hmac.init(this.secretKeys.integrityKey);
                this.hmac.update(bArr2, 0, blockSize + doFinal.length);
                byte[] doFinal2 = this.hmac.doFinal();
                System.arraycopy(doFinal2, 0, bArr2, i + doFinal.length, doFinal2.length);
                encodeToString = Base64.encodeToString(bArr2, 0);
            } catch (UnsupportedEncodingException e) {
                e = e;
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e2) {
                e = e2;
                throw new RuntimeException(e);
            } catch (InvalidKeyException e3) {
                e = e3;
                throw new RuntimeException(e);
            } catch (BadPaddingException e4) {
                throw new EncryptionException(e4);
            } catch (IllegalBlockSizeException e5) {
                e = e5;
                throw new RuntimeException(e);
            }
        }
        return encodeToString;
    }

    public String decrypt(Context context, String str) {
        String str2;
        synchronized (this.syncObject) {
            initialize(context);
            try {
                byte[] decode = Base64.decode(str, 0);
                byte[] copyOfRange = Arrays.copyOfRange(decode, decode.length - 32, decode.length);
                this.hmac.init(this.secretKeys.integrityKey);
                this.hmac.update(decode, 0, decode.length - 32);
                if (!MessageDigest.isEqual(this.hmac.doFinal(), copyOfRange)) {
                    throw new EncryptionException("Cipher text has been tampered with.");
                }
                this.cipher.init(2, this.secretKeys.encryptionKey, new IvParameterSpec(decode, 0, 16));
                str2 = new String(this.cipher.doFinal(decode, 16, (decode.length - 16) - 32), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e = e;
                throw new RuntimeException(e);
            } catch (InvalidAlgorithmParameterException e2) {
                e = e2;
                throw new RuntimeException(e);
            } catch (InvalidKeyException e3) {
                e = e3;
                throw new RuntimeException(e);
            } catch (BadPaddingException e4) {
                throw new EncryptionException(e4);
            } catch (IllegalBlockSizeException e5) {
                e = e5;
                throw new RuntimeException(e);
            }
        }
        return str2;
    }

    private SecretKeys getSecretKeys(Context context) {
        String generateDevicePackageSpecificId = generateDevicePackageSpecificId(context);
        try {
            byte[] encoded = this.keyFactory.generateSecret(new PBEKeySpec(generateDevicePackageSpecificId.toCharArray(), getSalt(context), this.pbkdf2IterationCount, 512)).getEncoded();
            return new SecretKeys(new SecretKeySpec(Arrays.copyOfRange(encoded, 0, 32), "AES"), new SecretKeySpec(Arrays.copyOfRange(encoded, 32, encoded.length), "HmacSHA256"));
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateDevicePackageSpecificId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return Build.MODEL + Build.MANUFACTURER + (this.isSerialIncludedInDevicePackageSpecificId ? Build.SERIAL : "") + string + context.getPackageName();
    }

    private byte[] getSalt(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.sharedPreferenceName, 0);
        String string = sharedPreferences.getString(SALT_SHARED_PREFERENCE_KEY, null);
        if (!TextUtils.isEmpty(string)) {
            return Base64.decode(string, 0);
        }
        byte[] bArr = new byte[16];
        this.secureRandom.nextBytes(bArr);
        sharedPreferences.edit().putString(SALT_SHARED_PREFERENCE_KEY, Base64.encodeToString(bArr, 0)).apply();
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SecretKeys {
        private final SecretKey encryptionKey;
        private final SecretKey integrityKey;

        SecretKeys(SecretKey secretKey, SecretKey secretKey2) {
            this.encryptionKey = secretKey;
            this.integrityKey = secretKey2;
        }
    }
}
