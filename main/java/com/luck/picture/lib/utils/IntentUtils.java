package com.luck.picture.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.luck.picture.lib.basic.PictureFileProvider;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import java.io.File;

/* loaded from: classes2.dex */
public class IntentUtils {
    public static void startSystemPlayerVideo(Context context, String str) {
        Uri parse;
        Intent intent = new Intent("android.intent.action.VIEW");
        boolean z = PictureMimeType.isContent(str) || PictureMimeType.isHasHttp(str);
        if (SdkVersionUtils.isQ()) {
            parse = z ? Uri.parse(str) : Uri.fromFile(new File(str));
        } else if (!SdkVersionUtils.isMaxN()) {
            parse = z ? Uri.parse(str) : Uri.fromFile(new File(str));
        } else if (z) {
            parse = Uri.parse(str);
        } else {
            parse = PictureFileProvider.getUriForFile(context, context.getPackageName() + ".luckProvider", new File(str));
        }
        intent.addFlags(268468224);
        intent.addFlags(1);
        intent.setDataAndType(parse, SelectMimeType.SYSTEM_VIDEO);
        context.startActivity(intent);
    }
}
