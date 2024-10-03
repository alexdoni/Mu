package com.appsflyer;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public class AFKeystoreWrapper {
    public KeyStore AFInAppEventParameterName;
    private Context AFInAppEventType;
    public final Object AFKeystoreWrapper = new Object();
    public String values = "";
    public int valueOf = 0;

    public AFKeystoreWrapper(Context context) {
        this.AFInAppEventType = context;
        AFLogger.afInfoLog("Initialising KeyStore..");
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            this.AFInAppEventParameterName = keyStore;
            keyStore.load(null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            AFLogger.afErrorLog("Couldn't load keystore instance of type: AndroidKeyStore", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0020, code lost:
    
        r1 = r3.split(",");
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0028, code lost:
    
        if (r1.length != 3) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002a, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("Found a matching AF key with alias:\n".concat(java.lang.String.valueOf(r3)));
        r2 = true;
        r3 = r1[1].trim().split("=");
        r1 = r1[2].trim().split("=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        if (r3.length != 2) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
    
        if (r1.length != 2) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0057, code lost:
    
        r6.values = r3[1].trim();
        r6.valueOf = java.lang.Integer.parseInt(r1[1].trim());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean valueOf() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.AFKeystoreWrapper
            monitor-enter(r0)
            java.security.KeyStore r1 = r6.AFInAppEventParameterName     // Catch: java.lang.Throwable -> L88
            r2 = 0
            if (r1 == 0) goto L86
            java.util.Enumeration r1 = r1.aliases()     // Catch: java.lang.Throwable -> L6c
        Lc:
            boolean r3 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L6c
            if (r3 == 0) goto L86
            java.lang.Object r3 = r1.nextElement()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L6c
            if (r3 == 0) goto Lc
            boolean r4 = AFInAppEventParameterName(r3)     // Catch: java.lang.Throwable -> L6c
            if (r4 == 0) goto Lc
            java.lang.String r1 = ","
            java.lang.String[] r1 = r3.split(r1)     // Catch: java.lang.Throwable -> L6c
            int r4 = r1.length     // Catch: java.lang.Throwable -> L6c
            r5 = 3
            if (r4 != r5) goto L86
            java.lang.String r4 = "Found a matching AF key with alias:\n"
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = r4.concat(r3)     // Catch: java.lang.Throwable -> L6c
            com.appsflyer.AFLogger.afInfoLog(r3)     // Catch: java.lang.Throwable -> L6c
            r2 = 1
            r3 = r1[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r4 = "="
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L6c
            r4 = 2
            r1 = r1[r4]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r5 = "="
            java.lang.String[] r1 = r1.split(r5)     // Catch: java.lang.Throwable -> L6c
            int r5 = r3.length     // Catch: java.lang.Throwable -> L6c
            if (r5 != r4) goto L86
            int r5 = r1.length     // Catch: java.lang.Throwable -> L6c
            if (r5 != r4) goto L86
            r3 = r3[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L6c
            r6.values = r3     // Catch: java.lang.Throwable -> L6c
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L6c
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L6c
            r6.valueOf = r1     // Catch: java.lang.Throwable -> L6c
            goto L86
        L6c:
            r1 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            java.lang.String r4 = "Couldn't list KeyStore Aliases: "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L88
            java.lang.Class r4 = r1.getClass()     // Catch: java.lang.Throwable -> L88
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L88
            r3.append(r4)     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L88
            com.appsflyer.AFLogger.afErrorLog(r3, r1)     // Catch: java.lang.Throwable -> L88
        L86:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            return r2
        L88:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AFKeystoreWrapper.valueOf():boolean");
    }

    public final void AFKeystoreWrapper(String str) {
        AFLogger.afInfoLog("Creating a new key with alias: ".concat(String.valueOf(str)));
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 5);
            synchronized (this.AFKeystoreWrapper) {
                if (!this.AFInAppEventParameterName.containsAlias(str)) {
                    KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(str, 3).setCertificateSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    keyPairGenerator.initialize(build);
                    keyPairGenerator.generateKeyPair();
                } else {
                    AFLogger.afInfoLog("Alias already exists: ".concat(String.valueOf(str)));
                }
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Exception ");
            sb.append(th.getMessage());
            sb.append(" occurred");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    private static boolean AFInAppEventParameterName(String str) {
        return str.startsWith("com.appsflyer");
    }

    public final String AFKeystoreWrapper() {
        StringBuilder sb = new StringBuilder("com.appsflyer,KSAppsFlyerId=");
        synchronized (this.AFKeystoreWrapper) {
            sb.append(this.values);
            sb.append(",KSAppsFlyerRICounter=");
            sb.append(this.valueOf);
        }
        return sb.toString();
    }

    public final String AFInAppEventType() {
        String str;
        synchronized (this.AFKeystoreWrapper) {
            str = this.values;
        }
        return str;
    }

    public final int values() {
        int i;
        synchronized (this.AFKeystoreWrapper) {
            i = this.valueOf;
        }
        return i;
    }
}
