package com.linecorp.linesdk.internal;

import android.content.Context;
import com.linecorp.android.security.encryption.StringCipher;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class EncryptorHolder {
    private static final String ENCRYPTION_SALT_SHARED_PREFERENCE_NAME = "com.linecorp.linesdk.sharedpreference.encryptionsalt";
    private static final int DEFAULT_ITERATION_COUNT = 5000;
    private static final StringCipher ENCRYPTOR = new StringCipher(ENCRYPTION_SALT_SHARED_PREFERENCE_NAME, DEFAULT_ITERATION_COUNT, true);
    private static volatile boolean s_isInitializationStarted = false;

    private EncryptorHolder() {
    }

    public static void initializeOnWorkerThread(Context context) {
        if (s_isInitializationStarted) {
            return;
        }
        s_isInitializationStarted = true;
        Executors.newSingleThreadExecutor().execute(new EncryptorInitializationTask(context.getApplicationContext()));
    }

    public static StringCipher getEncryptor() {
        return ENCRYPTOR;
    }

    /* loaded from: classes2.dex */
    private static class EncryptorInitializationTask implements Runnable {
        private final Context context;

        EncryptorInitializationTask(Context context) {
            this.context = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            EncryptorHolder.ENCRYPTOR.initialize(this.context);
        }
    }
}
