package com.luck.picture.lib.entity;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MediaData {
    public ArrayList<LocalMedia> data;
    public boolean isHasNextMore;

    public MediaData() {
    }

    public MediaData(boolean z, ArrayList<LocalMedia> arrayList) {
        this.isHasNextMore = z;
        this.data = arrayList;
    }
}
