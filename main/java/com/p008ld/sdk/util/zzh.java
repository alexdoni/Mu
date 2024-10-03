package com.p008ld.sdk.util;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: LDIOUtils.java */
/* loaded from: classes2.dex */
class zzh {
    public static void zza(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
