package com.p008ld.sdk.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: PictureEngine.kt */
/* loaded from: classes2.dex */
public final class zzb implements ImageEngine {
    public static final zza zza = new zza(null);
    private static zzb zzb;

    public /* synthetic */ zzb(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private zzb() {
    }

    @Override // com.luck.picture.lib.engine.ImageEngine
    public void loadImage(Context context, String url, ImageView imageView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).load(url).into(imageView);
        }
    }

    @Override // com.luck.picture.lib.engine.ImageEngine
    public void loadImage(Context context, ImageView imageView, String url, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(url, "url");
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).load(url).override(i, i2).into(imageView);
        }
    }

    @Override // com.luck.picture.lib.engine.ImageEngine
    public void loadAlbumCover(Context context, String url, ImageView imageView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).asBitmap().load(url).override(CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256).sizeMultiplier(0.5f).transform(new CenterCrop(), new RoundedCorners(8)).placeholder(zzt.zzd(context, "ps_image_placeholder")).into(imageView);
        }
    }

    @Override // com.luck.picture.lib.engine.ImageEngine
    public void loadGridImage(Context context, String url, ImageView imageView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).load(url).override(200, 200).centerCrop().placeholder(zzt.zzd(context, "ps_image_placeholder")).into(imageView);
        }
    }

    @Override // com.luck.picture.lib.engine.ImageEngine
    public void pauseRequests(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Glide.with(context).pauseRequests();
    }

    @Override // com.luck.picture.lib.engine.ImageEngine
    public void resumeRequests(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Glide.with(context).resumeRequests();
    }

    /* compiled from: PictureEngine.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        public final zzb zza() {
            zzb zzbVar = zzb.zzb;
            if (zzbVar == null) {
                synchronized (this) {
                    zzbVar = zzb.zzb;
                    if (zzbVar == null) {
                        zzbVar = new zzb(null);
                        zza zzaVar = zzb.zza;
                        zzb.zzb = zzbVar;
                    }
                }
            }
            return zzbVar;
        }
    }
}
