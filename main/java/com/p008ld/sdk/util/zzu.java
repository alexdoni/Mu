package com.p008ld.sdk.util;

import android.content.Context;
import com.luck.picture.lib.engine.UriToFileTransformEngine;
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import com.luck.picture.lib.utils.SandboxTransformUtils;

/* compiled from: PictureEngine.kt */
/* loaded from: classes2.dex */
public final class zzu implements UriToFileTransformEngine {
    @Override // com.luck.picture.lib.engine.UriToFileTransformEngine
    public void onUriToFileAsyncTransform(Context context, String str, String str2, OnKeyValueResultCallbackListener onKeyValueResultCallbackListener) {
        if (onKeyValueResultCallbackListener != null) {
            onKeyValueResultCallbackListener.onCallback(str, SandboxTransformUtils.copyPathToSandbox(context, str, str2));
        }
    }
}
