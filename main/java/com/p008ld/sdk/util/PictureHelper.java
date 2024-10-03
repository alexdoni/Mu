package com.p008ld.sdk.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PictureHelper.kt */
/* loaded from: classes2.dex */
public final class PictureHelper {
    public static final PictureHelper INSTANCE = new PictureHelper();

    private PictureHelper() {
    }

    @JvmStatic
    public static final void loadImage(String str, ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (ActivityCompatHelper.assertValidRequest(imageView.getContext())) {
            Glide.with(imageView).load(str).into(imageView);
        }
    }

    @JvmStatic
    public static final void loadAvatar(String str, ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (ActivityCompatHelper.assertValidRequest(imageView.getContext())) {
            Glide.with(imageView).load(str).centerCrop().placeholder(zzt.zzd(imageView.getContext(), "ld_default_avatar")).into(imageView);
        }
    }

    @JvmStatic
    public static final void openPictureSelector(Context context, OnResultCallbackListener<LocalMedia> listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        PictureSelector.create(context).openGallery(SelectMimeType.ofImage()).setMaxSelectNum(1).setSelectionMode(1).isGif(false).isDisplayCamera(true).isPreviewImage(true).setCropEngine(new zzd()).setImageEngine(zzb.zza.zza()).setCompressEngine(new zzc()).setSandboxFileEngine(new zzu()).forResult(listener);
    }
}
