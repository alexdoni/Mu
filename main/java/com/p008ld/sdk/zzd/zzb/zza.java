package com.p008ld.sdk.zzd.zzb;

import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: I.java */
/* loaded from: classes2.dex */
class zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(int i, String str, String str2) {
        Logger logger = Logger.getLogger(str);
        if (i == 4) {
            logger.log(Level.INFO, str2);
        } else {
            logger.log(Level.WARNING, str2);
        }
    }
}
