package com.luck.picture.lib.loader;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.utils.MediaUtils;
import com.luck.picture.lib.utils.SdkVersionUtils;
import java.util.List;

/* loaded from: classes2.dex */
public final class LocalMediaLoader extends IBridgeMediaLoader {
    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    public String getAlbumFirstCover(long j) {
        return null;
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    public void loadPageMediaData(long j, int i, int i2, OnQueryDataResultListener<LocalMedia> onQueryDataResultListener) {
    }

    public LocalMediaLoader(Context context, SelectorConfig selectorConfig) {
        super(context, selectorConfig);
    }

    private static String getSelectionArgsForVideoMediaCondition(String str, String str2) {
        return "media_type=?" + str2 + " AND " + str;
    }

    private static String getSelectionArgsForAudioMediaCondition(String str, String str2) {
        return "media_type=?" + str2 + " AND " + str;
    }

    private static String getSelectionArgsForAllMediaCondition(String str, String str2, String str3) {
        return "(media_type=?" + str3 + " OR media_type=? AND " + str + ") AND " + str2;
    }

    private static String getSelectionArgsForImageMediaCondition(String str, String str2) {
        return "media_type=?" + str2 + " AND " + str;
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    public void loadAllAlbum(final OnQueryAllAlbumListener<LocalMediaFolder> onQueryAllAlbumListener) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<List<LocalMediaFolder>>() { // from class: com.luck.picture.lib.loader.LocalMediaLoader.1
            /* JADX WARN: Code restructure failed: missing block: B:46:0x014c, code lost:
            
                if (r1.isClosed() == false) goto L49;
             */
            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<com.luck.picture.lib.entity.LocalMediaFolder> doInBackground() {
                /*
                    Method dump skipped, instructions count: 359
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.loader.LocalMediaLoader.C21921.doInBackground():java.util.List");
            }

            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public void onSuccess(List<LocalMediaFolder> list) {
                PictureThreadUtils.cancel(this);
                OnQueryAllAlbumListener onQueryAllAlbumListener2 = onQueryAllAlbumListener;
                if (onQueryAllAlbumListener2 != null) {
                    onQueryAllAlbumListener2.onComplete(list);
                }
            }
        });
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    public void loadOnlyInAppDirAllMedia(final OnQueryAlbumListener<LocalMediaFolder> onQueryAlbumListener) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<LocalMediaFolder>() { // from class: com.luck.picture.lib.loader.LocalMediaLoader.2
            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public LocalMediaFolder doInBackground() {
                return SandboxFileLoader.loadInAppSandboxFolderFile(LocalMediaLoader.this.getContext(), LocalMediaLoader.this.getConfig().sandboxDir);
            }

            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public void onSuccess(LocalMediaFolder localMediaFolder) {
                PictureThreadUtils.cancel(this);
                OnQueryAlbumListener onQueryAlbumListener2 = onQueryAlbumListener;
                if (onQueryAlbumListener2 != null) {
                    onQueryAlbumListener2.onComplete(localMediaFolder);
                }
            }
        });
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    protected String getSelection() {
        String durationCondition = getDurationCondition();
        String fileSizeCondition = getFileSizeCondition();
        String queryMimeCondition = getQueryMimeCondition();
        int i = getConfig().chooseMode;
        if (i == 0) {
            return getSelectionArgsForAllMediaCondition(durationCondition, fileSizeCondition, queryMimeCondition);
        }
        if (i == 1) {
            return getSelectionArgsForImageMediaCondition(fileSizeCondition, queryMimeCondition);
        }
        if (i == 2) {
            return getSelectionArgsForVideoMediaCondition(durationCondition, queryMimeCondition);
        }
        if (i != 3) {
            return null;
        }
        return getSelectionArgsForAudioMediaCondition(durationCondition, queryMimeCondition);
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    protected String[] getSelectionArgs() {
        int i = getConfig().chooseMode;
        if (i == 0) {
            return new String[]{String.valueOf(1), String.valueOf(3)};
        }
        if (i == 1) {
            return new String[]{String.valueOf(1)};
        }
        if (i == 2) {
            return new String[]{String.valueOf(3)};
        }
        if (i != 3) {
            return null;
        }
        return new String[]{String.valueOf(2)};
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    protected String getSortOrder() {
        return TextUtils.isEmpty(getConfig().sortOrder) ? "date_modified DESC" : getConfig().sortOrder;
    }

    @Override // com.luck.picture.lib.loader.IBridgeMediaLoader
    protected LocalMedia parseLocalMedia(Cursor cursor, boolean z) {
        long j;
        long j2;
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow(PROJECTION[0]);
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow(PROJECTION[1]);
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow(PROJECTION[2]);
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow(PROJECTION[3]);
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow(PROJECTION[4]);
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow(PROJECTION[5]);
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow(PROJECTION[6]);
        int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow(PROJECTION[7]);
        int columnIndexOrThrow9 = cursor.getColumnIndexOrThrow(PROJECTION[8]);
        int columnIndexOrThrow10 = cursor.getColumnIndexOrThrow(PROJECTION[9]);
        int columnIndexOrThrow11 = cursor.getColumnIndexOrThrow(PROJECTION[10]);
        int columnIndexOrThrow12 = cursor.getColumnIndexOrThrow(PROJECTION[11]);
        long j3 = cursor.getLong(columnIndexOrThrow);
        long j4 = cursor.getLong(columnIndexOrThrow11);
        String string = cursor.getString(columnIndexOrThrow3);
        String string2 = cursor.getString(columnIndexOrThrow2);
        String realPathUri = SdkVersionUtils.isQ() ? MediaUtils.getRealPathUri(j3, string) : string2;
        if (TextUtils.isEmpty(string)) {
            string = PictureMimeType.ofJPEG();
        }
        if (string.endsWith(SelectMimeType.SYSTEM_IMAGE)) {
            string = MediaUtils.getMimeTypeFromMediaUrl(string2);
            j = j4;
            if (!getConfig().isGif && PictureMimeType.isHasGif(string)) {
                return null;
            }
        } else {
            j = j4;
        }
        if (string.endsWith(SelectMimeType.SYSTEM_IMAGE)) {
            return null;
        }
        if (!getConfig().isWebp && string.startsWith(PictureMimeType.ofWEBP())) {
            return null;
        }
        if (!getConfig().isBmp && PictureMimeType.isHasBmp(string)) {
            return null;
        }
        int i = cursor.getInt(columnIndexOrThrow4);
        int i2 = cursor.getInt(columnIndexOrThrow5);
        int i3 = cursor.getInt(columnIndexOrThrow12);
        if (i3 == 90 || i3 == 270) {
            i = cursor.getInt(columnIndexOrThrow5);
            i2 = cursor.getInt(columnIndexOrThrow4);
        }
        long j5 = cursor.getLong(columnIndexOrThrow6);
        long j6 = cursor.getLong(columnIndexOrThrow7);
        String string3 = cursor.getString(columnIndexOrThrow8);
        String string4 = cursor.getString(columnIndexOrThrow9);
        long j7 = cursor.getLong(columnIndexOrThrow10);
        if (TextUtils.isEmpty(string4)) {
            string4 = PictureMimeType.getUrlToFileName(string2);
        }
        if (getConfig().isFilterSizeDuration && j6 > 0 && j6 < 1024) {
            return null;
        }
        if (PictureMimeType.isHasVideo(string) || PictureMimeType.isHasAudio(string)) {
            if (getConfig().filterVideoMinSecond > 0) {
                j2 = j6;
                if (j5 < getConfig().filterVideoMinSecond) {
                    return null;
                }
            } else {
                j2 = j6;
            }
            if (getConfig().filterVideoMaxSecond > 0 && j5 > getConfig().filterVideoMaxSecond) {
                return null;
            }
            if (getConfig().isFilterSizeDuration && j5 <= 0) {
                return null;
            }
        } else {
            j2 = j6;
        }
        LocalMedia create = LocalMedia.create();
        create.setId(j3);
        create.setBucketId(j7);
        create.setPath(realPathUri);
        create.setRealPath(string2);
        create.setFileName(string4);
        create.setParentFolderName(string3);
        create.setDuration(j5);
        create.setChooseModel(getConfig().chooseMode);
        create.setMimeType(string);
        create.setWidth(i);
        create.setHeight(i2);
        create.setSize(j2);
        create.setDateAddedTime(j);
        if (this.mConfig.onQueryFilterListener == null || !this.mConfig.onQueryFilterListener.onFilter(create)) {
            return create;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocalMediaFolder getImageFolder(String str, String str2, String str3, List<LocalMediaFolder> list) {
        for (LocalMediaFolder localMediaFolder : list) {
            String folderName = localMediaFolder.getFolderName();
            if (!TextUtils.isEmpty(folderName) && TextUtils.equals(folderName, str3)) {
                return localMediaFolder;
            }
        }
        LocalMediaFolder localMediaFolder2 = new LocalMediaFolder();
        localMediaFolder2.setFolderName(str3);
        localMediaFolder2.setFirstImagePath(str);
        localMediaFolder2.setFirstMimeType(str2);
        list.add(localMediaFolder2);
        return localMediaFolder2;
    }
}
