package com.p008ld.sdk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropImageEngine;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: PictureEngine.kt */
/* loaded from: classes2.dex */
public class zzd implements CropFileEngine {
    @Override // com.luck.picture.lib.engine.CropFileEngine
    public void onStartCrop(Fragment fragment, Uri srcUri, Uri destinationUri, ArrayList<String> arrayList, int i) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(srcUri, "srcUri");
        Intrinsics.checkNotNullParameter(destinationUri, "destinationUri");
        UCrop.Options zza2 = zza();
        UCrop m1392of = UCrop.m1392of(srcUri, destinationUri, arrayList);
        m1392of.withOptions(zza2);
        m1392of.setImageEngine(new zza());
        try {
            m1392of.start(fragment.requireContext(), fragment, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: PictureEngine.kt */
    /* loaded from: classes2.dex */
    public static final class zza implements UCropImageEngine {
        zza() {
        }

        @Override // com.yalantis.ucrop.UCropImageEngine
        public void loadImage(Context context, String url, ImageView imageView) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(imageView, "imageView");
            if (ActivityCompatHelper.assertValidRequest(context)) {
                Glide.with(context).load(url).override(CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256).into(imageView);
            }
        }

        @Override // com.yalantis.ucrop.UCropImageEngine
        public void loadImage(Context context, Uri url, int i, int i2, UCropImageEngine.OnCallbackListener<Bitmap> call) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(call, "call");
            if (ActivityCompatHelper.assertValidRequest(context)) {
                Glide.with(context).asBitmap().override(i, i2).load(url).into((RequestBuilder) new C3261zza(call));
            }
        }

        /* compiled from: PictureEngine.kt */
        /* renamed from: com.ld.sdk.util.zzd$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C3261zza extends CustomTarget<Bitmap> {
            final /* synthetic */ UCropImageEngine.OnCallbackListener<Bitmap> zza;

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            C3261zza(UCropImageEngine.OnCallbackListener<Bitmap> onCallbackListener) {
                this.zza = onCallbackListener;
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                this.zza.onCall(resource);
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                this.zza.onCall(null);
            }
        }
    }

    private final UCrop.Options zza() {
        UCrop.Options options = new UCrop.Options();
        options.withAspectRatio(1.0f, 1.0f);
        options.setCircleDimmedLayer(true);
        return options;
    }
}
