package com.luck.picture.lib.utils;

import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class SortUtils {
    public static void sortFolder(List<LocalMediaFolder> list) {
        Collections.sort(list, new Comparator() { // from class: com.luck.picture.lib.utils.SortUtils$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return SortUtils.lambda$sortFolder$0((LocalMediaFolder) obj, (LocalMediaFolder) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$sortFolder$0(LocalMediaFolder localMediaFolder, LocalMediaFolder localMediaFolder2) {
        if (localMediaFolder.getData() == null || localMediaFolder2.getData() == null) {
            return 0;
        }
        return Integer.compare(localMediaFolder2.getFolderTotalNum(), localMediaFolder.getFolderTotalNum());
    }

    public static void sortLocalMediaAddedTime(List<LocalMedia> list) {
        Collections.sort(list, new Comparator() { // from class: com.luck.picture.lib.utils.SortUtils$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((LocalMedia) obj2).getDateAddedTime(), ((LocalMedia) obj).getDateAddedTime());
                return compare;
            }
        });
    }
}
