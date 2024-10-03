package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzfm extends com.google.android.gms.internal.measurement.zzbu implements zzfk {
    @Override // com.google.android.gms.measurement.internal.zzfk
    public final zzam zza(zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        Parcel zza = zza(21, m318a_);
        zzam zzamVar = (zzam) com.google.android.gms.internal.measurement.zzbw.zza(zza, zzam.CREATOR);
        zza.recycle();
        return zzamVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final String zzb(zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        Parcel zza = zza(11, m318a_);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final List<zzmh> zza(zzo zzoVar, Bundle bundle) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, bundle);
        Parcel zza = zza(24, m318a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzmh.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final List<zznc> zza(zzo zzoVar, boolean z) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, z);
        Parcel zza = zza(7, m318a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zznc.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final List<zzad> zza(String str, String str2, zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        Parcel zza = zza(16, m318a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzad.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final List<zzad> zza(String str, String str2, String str3) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        m318a_.writeString(str3);
        Parcel zza = zza(17, m318a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzad.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final List<zznc> zza(String str, String str2, boolean z, zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, z);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        Parcel zza = zza(14, m318a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zznc.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final List<zznc> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        m318a_.writeString(str3);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, z);
        Parcel zza = zza(15, m318a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zznc.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zzc(zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(4, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(zzbg zzbgVar, zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzbgVar);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(1, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(zzbg zzbgVar, String str, String str2) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzbgVar);
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzb(5, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zzd(zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(18, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(zzad zzadVar, zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzadVar);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(12, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(zzad zzadVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzadVar);
        zzb(13, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zze(zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(20, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeLong(j);
        m318a_.writeString(str);
        m318a_.writeString(str2);
        m318a_.writeString(str3);
        zzb(10, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(Bundle bundle, zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, bundle);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(19, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zzf(zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(6, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final void zza(zznc zzncVar, zzo zzoVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzncVar);
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzoVar);
        zzb(2, m318a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzfk
    public final byte[] zza(zzbg zzbgVar, String str) throws RemoteException {
        Parcel m318a_ = m318a_();
        com.google.android.gms.internal.measurement.zzbw.zza(m318a_, zzbgVar);
        m318a_.writeString(str);
        Parcel zza = zza(9, m318a_);
        byte[] createByteArray = zza.createByteArray();
        zza.recycle();
        return createByteArray;
    }
}
