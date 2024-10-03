package com.p008ld.sdk.util;

import android.content.Context;
import android.net.Uri;
import com.luck.picture.lib.engine.CompressFileEngine;
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import java.io.File;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import top.zibin.luban.Luban;
import top.zibin.luban.OnNewCompressListener;

/* compiled from: PictureEngine.kt */
/* loaded from: classes2.dex */
public final class zzc implements CompressFileEngine {
    @Override // com.luck.picture.lib.engine.CompressFileEngine
    public void onStartCompress(Context context, ArrayList<Uri> source, OnKeyValueResultCallbackListener onKeyValueResultCallbackListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(source, "source");
        Luban.with(context).load(source).ignoreBy(500).setCompressListener(new zza(onKeyValueResultCallbackListener)).launch();
    }

    /* compiled from: PictureEngine.kt */
    /* loaded from: classes2.dex */
    public static final class zza implements OnNewCompressListener {
        final /* synthetic */ OnKeyValueResultCallbackListener zza;

        @Override // top.zibin.luban.OnNewCompressListener
        public void onStart() {
        }

        zza(OnKeyValueResultCallbackListener onKeyValueResultCallbackListener) {
            this.zza = onKeyValueResultCallbackListener;
        }

        @Override // top.zibin.luban.OnNewCompressListener
        public void onSuccess(String source, File compressFile) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(compressFile, "compressFile");
            OnKeyValueResultCallbackListener onKeyValueResultCallbackListener = this.zza;
            if (onKeyValueResultCallbackListener != null) {
                onKeyValueResultCallbackListener.onCallback(source, compressFile.getAbsolutePath());
            }
        }

        @Override // top.zibin.luban.OnNewCompressListener
        public void onError(String source, Throwable th) {
            Intrinsics.checkNotNullParameter(source, "source");
            OnKeyValueResultCallbackListener onKeyValueResultCallbackListener = this.zza;
            if (onKeyValueResultCallbackListener != null) {
                onKeyValueResultCallbackListener.onCallback(source, null);
            }
        }
    }
}
