package com.p008ld.sdk.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.AnalyticsEvents;

/* compiled from: ResIdManger.java */
/* loaded from: classes2.dex */
public class zzt {
    public static View zza(Context context, String str, View view) {
        return view.findViewById(zze(context, str));
    }

    public static View zza(Context context, String str, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(zzf(context, str), viewGroup);
    }

    public static String zza(Context context, String str) {
        return context.getString(zzg(context, str));
    }

    public static int zzb(Context context, String str) {
        return context.getResources().getDimensionPixelSize(zzc(context, str));
    }

    public static int zzc(Context context, String str) {
        return context.getResources().getIdentifier(str, "dimen", context.getPackageName());
    }

    public static int zzd(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    public static int zze(Context context, String str) {
        return context.getResources().getIdentifier(str, "id", context.getPackageName());
    }

    public static int zzf(Context context, String str) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    public static int zzg(Context context, String str) {
        return context.getResources().getIdentifier(str, TypedValues.Custom.S_STRING, context.getPackageName());
    }

    public static int zzh(Context context, String str) {
        return context.getResources().getIdentifier(str, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, context.getPackageName());
    }
}
