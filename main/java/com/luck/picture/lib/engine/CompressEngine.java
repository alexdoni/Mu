package com.luck.picture.lib.engine;

import android.content.Context;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes2.dex */
public interface CompressEngine {
    void onStartCompress(Context context, ArrayList<LocalMedia> arrayList, OnCallbackListener<ArrayList<LocalMedia>> onCallbackListener);
}
