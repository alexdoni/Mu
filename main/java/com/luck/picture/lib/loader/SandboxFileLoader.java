package com.luck.picture.lib.loader;

import android.content.Context;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.utils.SortUtils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class SandboxFileLoader {
    public static LocalMediaFolder loadInAppSandboxFolderFile(Context context, String str) {
        ArrayList<LocalMedia> loadInAppSandboxFile = loadInAppSandboxFile(context, str);
        if (loadInAppSandboxFile == null || loadInAppSandboxFile.size() <= 0) {
            return null;
        }
        SortUtils.sortLocalMediaAddedTime(loadInAppSandboxFile);
        LocalMedia localMedia = loadInAppSandboxFile.get(0);
        LocalMediaFolder localMediaFolder = new LocalMediaFolder();
        localMediaFolder.setFolderName(localMedia.getParentFolderName());
        localMediaFolder.setFirstImagePath(localMedia.getPath());
        localMediaFolder.setFirstMimeType(localMedia.getMimeType());
        localMediaFolder.setBucketId(localMedia.getBucketId());
        localMediaFolder.setFolderTotalNum(loadInAppSandboxFile.size());
        localMediaFolder.setData(loadInAppSandboxFile);
        return localMediaFolder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0159, code lost:
    
        if (r2 < r6.filterVideoMinSecond) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x016a, code lost:
    
        if (r2 > r6.filterVideoMaxSecond) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0171, code lost:
    
        if (r2 != 0) goto L79;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.luck.picture.lib.entity.LocalMedia> loadInAppSandboxFile(android.content.Context r24, java.lang.String r25) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.loader.SandboxFileLoader.loadInAppSandboxFile(android.content.Context, java.lang.String):java.util.ArrayList");
    }
}
