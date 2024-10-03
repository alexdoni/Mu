package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzcw extends zzbu implements zzcu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void beginAdUnitExposure(String str, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeLong(j);
        zzb(23, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, bundle);
        zzb(9, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void clearMeasurementEnabled(long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeLong(j);
        zzb(43, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void endAdUnitExposure(String str, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeLong(j);
        zzb(24, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void generateEventId(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(22, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getAppInstanceId(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(20, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getCachedAppInstanceId(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(19, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getConditionalUserProperties(String str, String str2, zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, zzcvVar);
        zzb(10, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getCurrentScreenClass(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(17, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getCurrentScreenName(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(16, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getGmpAppId(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(21, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getMaxUserProperties(String str, zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        zzbw.zza(m318a_, zzcvVar);
        zzb(6, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getSessionId(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(46, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getTestFlag(zzcv zzcvVar, int i) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        m318a_.writeInt(i);
        zzb(38, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void getUserProperties(String str, String str2, boolean z, zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, z);
        zzbw.zza(m318a_, zzcvVar);
        zzb(5, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void initForTests(Map map) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeMap(map);
        zzb(37, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void initialize(IObjectWrapper iObjectWrapper, zzdd zzddVar, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        zzbw.zza(m318a_, zzddVar);
        m318a_.writeLong(j);
        zzb(1, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void isDataCollectionEnabled(zzcv zzcvVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzcvVar);
        zzb(40, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, bundle);
        zzbw.zza(m318a_, z);
        zzbw.zza(m318a_, z2);
        m318a_.writeLong(j);
        zzb(2, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzcv zzcvVar, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, bundle);
        zzbw.zza(m318a_, zzcvVar);
        m318a_.writeLong(j);
        zzb(3, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeInt(i);
        m318a_.writeString(str);
        zzbw.zza(m318a_, iObjectWrapper);
        zzbw.zza(m318a_, iObjectWrapper2);
        zzbw.zza(m318a_, iObjectWrapper3);
        zzb(33, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        zzbw.zza(m318a_, bundle);
        m318a_.writeLong(j);
        zzb(27, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        m318a_.writeLong(j);
        zzb(28, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        m318a_.writeLong(j);
        zzb(29, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        m318a_.writeLong(j);
        zzb(30, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzcv zzcvVar, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        zzbw.zza(m318a_, zzcvVar);
        m318a_.writeLong(j);
        zzb(31, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        m318a_.writeLong(j);
        zzb(25, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        m318a_.writeLong(j);
        zzb(26, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void performAction(Bundle bundle, zzcv zzcvVar, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        zzbw.zza(m318a_, zzcvVar);
        m318a_.writeLong(j);
        zzb(32, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void registerOnMeasurementEventListener(zzda zzdaVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzdaVar);
        zzb(35, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void resetAnalyticsData(long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeLong(j);
        zzb(12, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        m318a_.writeLong(j);
        zzb(8, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setConsent(Bundle bundle, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        m318a_.writeLong(j);
        zzb(44, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        m318a_.writeLong(j);
        zzb(45, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, iObjectWrapper);
        m318a_.writeString(str);
        m318a_.writeString(str2);
        m318a_.writeLong(j);
        zzb(15, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, z);
        zzb(39, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setDefaultEventParameters(Bundle bundle) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        zzb(42, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setEventInterceptor(zzda zzdaVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzdaVar);
        zzb(34, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setInstanceIdProvider(zzdb zzdbVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzdbVar);
        zzb(18, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, z);
        m318a_.writeLong(j);
        zzb(11, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setMinimumSessionDuration(long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeLong(j);
        zzb(13, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setSessionTimeoutDuration(long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeLong(j);
        zzb(14, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setUserId(String str, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeLong(j);
        zzb(7, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, iObjectWrapper);
        zzbw.zza(m318a_, z);
        m318a_.writeLong(j);
        zzb(4, m318a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzcu
    public final void unregisterOnMeasurementEventListener(zzda zzdaVar) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, zzdaVar);
        zzb(36, m318a_);
    }
}
