package com.mu;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUriExposedException;
import androidx.core.content.FileProvider;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.mu.utility.FileUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class DownApkBroadcast extends BroadcastReceiver {
    private Context context;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        this.context = context;
        action.hashCode();
        if (action.equals("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED")) {
            Intent intent2 = new Intent("android.intent.action.VIEW_DOWNLOADS");
            intent2.setFlags(268435456);
            context.startActivity(intent2);
        } else if (action.equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            installApk(intent.getLongExtra("extra_download_id", -1L));
        }
    }

    private void installApk(long j) {
        try {
            DownloadManager downloadManager = (DownloadManager) this.context.getSystemService("download");
            if (downloadManager == null) {
                return;
            }
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(j);
            Cursor query2 = downloadManager.query(query);
            if (query2 != null && query2.moveToFirst()) {
                String string = query2.getString(query2.getColumnIndex("local_uri"));
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        startIntent(string);
                    } catch (FileUriExposedException e) {
                        e.printStackTrace();
                    }
                } else {
                    startIntent(string);
                }
            }
            if (query2 != null) {
                query2.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void startIntent(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (this.context.getPackageManager().canRequestPackageInstalls()) {
                installApkHasPermission(str);
                return;
            }
            ((Activity) this.context).startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + this.context.getPackageName())), 100);
            return;
        }
        installApkHasPermission(str);
    }

    private void installApkHasPermission(String str) {
        File file;
        Intent intent;
        try {
            String substring = str.substring(str.lastIndexOf(47) + 1);
            String substring2 = str.substring(0, str.lastIndexOf(47));
            String substring3 = substring2.substring(substring2.lastIndexOf(47) + 1);
            String str2 = Environment.getExternalStorageDirectory().toString() + RemoteSettings.FORWARD_SLASH_STRING + this.context.getPackageName() + RemoteSettings.FORWARD_SLASH_STRING + substring3;
            if (FileUtils.checkApkValid(new File(str2, substring))) {
                if (str2.equals("")) {
                    file = new File(str.replace("file:/", ""));
                } else {
                    file = new File(str2, substring);
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    intent = new Intent("android.intent.action.INSTALL_PACKAGE");
                    Uri uriForFile = FileProvider.getUriForFile(this.context, this.context.getPackageName() + ".fileProvider", file);
                    intent.setFlags(1);
                    intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                    intent.addFlags(268435456);
                    intent.addFlags(2);
                } else {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(268435456);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                }
                ((Activity) this.context).startActivityForResult(intent, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
