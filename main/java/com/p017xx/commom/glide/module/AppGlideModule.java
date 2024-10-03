package com.p017xx.commom.glide.module;

import android.content.Context;
import com.p017xx.commom.glide.GlideBuilder;

/* loaded from: classes3.dex */
public abstract class AppGlideModule extends LibraryGlideModule implements AppliesOptions {
    @Override // com.p017xx.commom.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
    }

    public boolean isManifestParsingEnabled() {
        return true;
    }
}
