package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public abstract class zze extends zzb implements zzf {
    public static zzf zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.integrity.protocol.IIntegrityService");
        return queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zzd(iBinder);
    }
}
