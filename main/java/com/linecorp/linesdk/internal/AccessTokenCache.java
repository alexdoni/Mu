package com.linecorp.linesdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.linecorp.android.security.encryption.EncryptionException;
import com.linecorp.android.security.encryption.StringCipher;
import com.linecorp.linesdk.utils.ObjectUtils;

/* loaded from: classes2.dex */
public class AccessTokenCache {
    private static final String DATA_KEY_ACCESS_TOKEN = "accessToken";
    private static final String DATA_KEY_EXPIRES_IN_MILLIS = "expiresIn";
    private static final String DATA_KEY_ISSUED_CLIENT_TIME_MILLIS = "issuedClientTime";
    private static final String DATA_KEY_REFRESH_TOKEN = "refreshToken";
    private static final long NO_DATA = -1;
    private static final String SHARED_PREFERENCE_KEY_PREFIX = "com.linecorp.linesdk.accesstoken.";
    private final Context context;
    private final StringCipher encryptor;
    private final String sharedPreferenceKey;

    public AccessTokenCache(Context context, String str) {
        this(context.getApplicationContext(), str, EncryptorHolder.getEncryptor());
    }

    public AccessTokenCache(Context context, String str, StringCipher stringCipher) {
        this.context = context;
        this.sharedPreferenceKey = SHARED_PREFERENCE_KEY_PREFIX + str;
        this.encryptor = stringCipher;
    }

    public void clear() {
        this.context.getSharedPreferences(this.sharedPreferenceKey, 0).edit().clear().apply();
    }

    public void saveAccessToken(InternalAccessToken internalAccessToken) {
        this.context.getSharedPreferences(this.sharedPreferenceKey, 0).edit().putString(DATA_KEY_ACCESS_TOKEN, encryptString(internalAccessToken.getAccessToken())).putString(DATA_KEY_EXPIRES_IN_MILLIS, encryptLong(internalAccessToken.getExpiresInMillis())).putString(DATA_KEY_ISSUED_CLIENT_TIME_MILLIS, encryptLong(internalAccessToken.getIssuedClientTimeMillis())).putString(DATA_KEY_REFRESH_TOKEN, encryptString(internalAccessToken.getRefreshToken())).apply();
    }

    public InternalAccessToken getAccessToken() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(this.sharedPreferenceKey, 0);
        try {
            String decryptToString = decryptToString(sharedPreferences.getString(DATA_KEY_ACCESS_TOKEN, null));
            long decryptToLong = decryptToLong(sharedPreferences.getString(DATA_KEY_EXPIRES_IN_MILLIS, null));
            long decryptToLong2 = decryptToLong(sharedPreferences.getString(DATA_KEY_ISSUED_CLIENT_TIME_MILLIS, null));
            if (TextUtils.isEmpty(decryptToString) || decryptToLong == -1 || decryptToLong2 == -1) {
                return null;
            }
            return new InternalAccessToken(decryptToString, decryptToLong, decryptToLong2, (String) ObjectUtils.defaultIfNull(decryptToString(sharedPreferences.getString(DATA_KEY_REFRESH_TOKEN, null)), ""));
        } catch (EncryptionException unused) {
            clear();
            return null;
        }
    }

    private String encryptString(String str) {
        return this.encryptor.encrypt(this.context, str);
    }

    private String encryptLong(long j) {
        return this.encryptor.encrypt(this.context, String.valueOf(j));
    }

    private String decryptToString(String str) {
        if (str == null) {
            return null;
        }
        return this.encryptor.decrypt(this.context, str);
    }

    private long decryptToLong(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.valueOf(this.encryptor.decrypt(this.context, str)).longValue();
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }
}
