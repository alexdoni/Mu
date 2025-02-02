package com.luck.picture.lib.engine;

import android.content.Context;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;

@Deprecated
/* loaded from: classes2.dex */
public interface ExtendLoaderEngine {
    void loadAllAlbumData(Context context, OnQueryAllAlbumListener<LocalMediaFolder> onQueryAllAlbumListener);

    void loadFirstPageMediaData(Context context, long j, int i, int i2, OnQueryDataResultListener<LocalMedia> onQueryDataResultListener);

    void loadMoreMediaData(Context context, long j, int i, int i2, int i3, OnQueryDataResultListener<LocalMedia> onQueryDataResultListener);

    void loadOnlyInAppDirAllMediaData(Context context, OnQueryAlbumListener<LocalMediaFolder> onQueryAlbumListener);
}
