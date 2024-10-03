package com.luck.picture.lib.utils;

import android.content.Context;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import com.luck.picture.lib.thread.PictureThreadUtils;

/* loaded from: classes2.dex */
public class DownloadFileUtils {
    public static void saveLocalFile(final Context context, final String str, final String str2, final OnCallbackListener<String> onCallbackListener) {
        PictureThreadUtils.executeByIo(new PictureThreadUtils.SimpleTask<String>() { // from class: com.luck.picture.lib.utils.DownloadFileUtils.1
            /* JADX WARN: Removed duplicated region for block: B:16:0x005d A[Catch: Exception -> 0x021e, TryCatch #0 {Exception -> 0x021e, blocks: (B:3:0x0006, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x004f, B:14:0x0054, B:16:0x005d, B:17:0x00a7, B:19:0x01db, B:21:0x01e3, B:22:0x020b, B:24:0x0217, B:29:0x01ef, B:31:0x01f7, B:32:0x0204, B:34:0x0066, B:36:0x0070, B:37:0x0083, B:38:0x0077, B:40:0x00b5, B:43:0x00bf, B:45:0x00ce, B:47:0x00d6, B:50:0x00df, B:51:0x00e4, B:53:0x00ed, B:54:0x0137, B:55:0x00f6, B:57:0x0100, B:58:0x0113, B:59:0x0107, B:61:0x0145, B:63:0x0154, B:65:0x015c, B:68:0x0165, B:69:0x016a, B:71:0x0173, B:72:0x01cd, B:73:0x017c, B:75:0x0184, B:77:0x018c, B:79:0x0196, B:80:0x01a9, B:81:0x019d), top: B:2:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:34:0x0066 A[Catch: Exception -> 0x021e, TryCatch #0 {Exception -> 0x021e, blocks: (B:3:0x0006, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x004f, B:14:0x0054, B:16:0x005d, B:17:0x00a7, B:19:0x01db, B:21:0x01e3, B:22:0x020b, B:24:0x0217, B:29:0x01ef, B:31:0x01f7, B:32:0x0204, B:34:0x0066, B:36:0x0070, B:37:0x0083, B:38:0x0077, B:40:0x00b5, B:43:0x00bf, B:45:0x00ce, B:47:0x00d6, B:50:0x00df, B:51:0x00e4, B:53:0x00ed, B:54:0x0137, B:55:0x00f6, B:57:0x0100, B:58:0x0113, B:59:0x0107, B:61:0x0145, B:63:0x0154, B:65:0x015c, B:68:0x0165, B:69:0x016a, B:71:0x0173, B:72:0x01cd, B:73:0x017c, B:75:0x0184, B:77:0x018c, B:79:0x0196, B:80:0x01a9, B:81:0x019d), top: B:2:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:53:0x00ed A[Catch: Exception -> 0x021e, TryCatch #0 {Exception -> 0x021e, blocks: (B:3:0x0006, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x004f, B:14:0x0054, B:16:0x005d, B:17:0x00a7, B:19:0x01db, B:21:0x01e3, B:22:0x020b, B:24:0x0217, B:29:0x01ef, B:31:0x01f7, B:32:0x0204, B:34:0x0066, B:36:0x0070, B:37:0x0083, B:38:0x0077, B:40:0x00b5, B:43:0x00bf, B:45:0x00ce, B:47:0x00d6, B:50:0x00df, B:51:0x00e4, B:53:0x00ed, B:54:0x0137, B:55:0x00f6, B:57:0x0100, B:58:0x0113, B:59:0x0107, B:61:0x0145, B:63:0x0154, B:65:0x015c, B:68:0x0165, B:69:0x016a, B:71:0x0173, B:72:0x01cd, B:73:0x017c, B:75:0x0184, B:77:0x018c, B:79:0x0196, B:80:0x01a9, B:81:0x019d), top: B:2:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:55:0x00f6 A[Catch: Exception -> 0x021e, TryCatch #0 {Exception -> 0x021e, blocks: (B:3:0x0006, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x004f, B:14:0x0054, B:16:0x005d, B:17:0x00a7, B:19:0x01db, B:21:0x01e3, B:22:0x020b, B:24:0x0217, B:29:0x01ef, B:31:0x01f7, B:32:0x0204, B:34:0x0066, B:36:0x0070, B:37:0x0083, B:38:0x0077, B:40:0x00b5, B:43:0x00bf, B:45:0x00ce, B:47:0x00d6, B:50:0x00df, B:51:0x00e4, B:53:0x00ed, B:54:0x0137, B:55:0x00f6, B:57:0x0100, B:58:0x0113, B:59:0x0107, B:61:0x0145, B:63:0x0154, B:65:0x015c, B:68:0x0165, B:69:0x016a, B:71:0x0173, B:72:0x01cd, B:73:0x017c, B:75:0x0184, B:77:0x018c, B:79:0x0196, B:80:0x01a9, B:81:0x019d), top: B:2:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:71:0x0173 A[Catch: Exception -> 0x021e, TryCatch #0 {Exception -> 0x021e, blocks: (B:3:0x0006, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x004f, B:14:0x0054, B:16:0x005d, B:17:0x00a7, B:19:0x01db, B:21:0x01e3, B:22:0x020b, B:24:0x0217, B:29:0x01ef, B:31:0x01f7, B:32:0x0204, B:34:0x0066, B:36:0x0070, B:37:0x0083, B:38:0x0077, B:40:0x00b5, B:43:0x00bf, B:45:0x00ce, B:47:0x00d6, B:50:0x00df, B:51:0x00e4, B:53:0x00ed, B:54:0x0137, B:55:0x00f6, B:57:0x0100, B:58:0x0113, B:59:0x0107, B:61:0x0145, B:63:0x0154, B:65:0x015c, B:68:0x0165, B:69:0x016a, B:71:0x0173, B:72:0x01cd, B:73:0x017c, B:75:0x0184, B:77:0x018c, B:79:0x0196, B:80:0x01a9, B:81:0x019d), top: B:2:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:73:0x017c A[Catch: Exception -> 0x021e, TryCatch #0 {Exception -> 0x021e, blocks: (B:3:0x0006, B:6:0x002f, B:8:0x003e, B:10:0x0046, B:13:0x004f, B:14:0x0054, B:16:0x005d, B:17:0x00a7, B:19:0x01db, B:21:0x01e3, B:22:0x020b, B:24:0x0217, B:29:0x01ef, B:31:0x01f7, B:32:0x0204, B:34:0x0066, B:36:0x0070, B:37:0x0083, B:38:0x0077, B:40:0x00b5, B:43:0x00bf, B:45:0x00ce, B:47:0x00d6, B:50:0x00df, B:51:0x00e4, B:53:0x00ed, B:54:0x0137, B:55:0x00f6, B:57:0x0100, B:58:0x0113, B:59:0x0107, B:61:0x0145, B:63:0x0154, B:65:0x015c, B:68:0x0165, B:69:0x016a, B:71:0x0173, B:72:0x01cd, B:73:0x017c, B:75:0x0184, B:77:0x018c, B:79:0x0196, B:80:0x01a9, B:81:0x019d), top: B:2:0x0006 }] */
            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.lang.String doInBackground() {
                /*
                    Method dump skipped, instructions count: 548
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.utils.DownloadFileUtils.C22251.doInBackground():java.lang.String");
            }

            @Override // com.luck.picture.lib.thread.PictureThreadUtils.Task
            public void onSuccess(String str3) {
                PictureThreadUtils.cancel(this);
                OnCallbackListener onCallbackListener2 = onCallbackListener;
                if (onCallbackListener2 != null) {
                    onCallbackListener2.onCall(str3);
                }
            }
        });
    }
}
