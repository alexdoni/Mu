package com.mu.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.FileProvider;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mu.DownApkBroadcast;
import com.mu.utility.FileUtils;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class UpdateManager {
    private static final int DOWNLOAD = 1;
    private static final int DOWNLOAD_FINISH = 2;
    DownApkBroadcast downApkBroadcast;
    private long downApkId;
    DownApkProgressRunnable downApkProgressRunnable;
    private Context mContext;
    private Dialog mDownloadDialog;
    HashMap<String, String> mHashMap;
    private ProgressBar mProgress;
    private String mSavePath;
    private TextView mTextView;
    downLoadApkUrl mdownloadApkUrl;
    private String packageNaString;
    private float progress;
    private boolean cancelUpdate = false;
    private String mUrlString = "";
    private String mApkString = "";
    private String mVersionString = "";
    private String localAPkUri = "";
    private Handler mHandler = new Handler() { // from class: com.mu.update.UpdateManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                UpdateManager.this.mProgress.setProgress((int) UpdateManager.this.progress);
                TextView textView = UpdateManager.this.mTextView;
                textView.setText((Math.round(UpdateManager.this.progress * 100.0f) / 100.0f) + "%");
                if (UpdateManager.this.mDownloadDialog.isShowing()) {
                    return;
                }
                UpdateManager.this.mDownloadDialog.show();
                return;
            }
            if (i == 8) {
                if (UpdateManager.this.mDownloadDialog.isShowing()) {
                    UpdateManager.this.mDownloadDialog.dismiss();
                }
            } else {
                if (i != 16) {
                    return;
                }
                if (UpdateManager.this.mdownloadApkUrl != null) {
                    UpdateManager.this.mdownloadApkUrl.interrupt();
                }
                UpdateManager.this.mdownloadApkUrl = new downLoadApkUrl();
                UpdateManager.this.mdownloadApkUrl.start();
            }
        }
    };
    private Handler mdownHandle = new Handler(new Handler.Callback() { // from class: com.mu.update.UpdateManager.2
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                Toast.makeText(UpdateManager.this.mContext, "创建下载链接成功", 1).show();
                return false;
            }
            if (i != 2) {
                return false;
            }
            Toast.makeText(UpdateManager.this.mContext, "该应用正在下载中,请稍后", 1).show();
            return false;
        }
    });

    public UpdateManager(Context context) {
        this.packageNaString = "";
        this.mContext = context;
        this.packageNaString = context.getPackageName();
    }

    public void checkUpdate(String str) {
        String[] split = str.split("#");
        if (split.length > 1) {
            String str2 = split[0];
            this.mUrlString = str2;
            showNoticeDialog(str2);
            this.mVersionString = split[1];
        }
    }

    private boolean isUpdate(String str) {
        return Integer.valueOf(str).intValue() > getVersionCode(this.mContext);
    }

    private int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(ConfigInfo.ApkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void showNoticeDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(this.mContext.getResources().getIdentifier("soft_update_title", TypedValues.Custom.S_STRING, this.mContext.getPackageName()));
        builder.setMessage(this.mContext.getResources().getIdentifier("soft_update_info", TypedValues.Custom.S_STRING, this.mContext.getPackageName()));
        builder.setPositiveButton(this.mContext.getResources().getIdentifier("soft_update_updatebtn", TypedValues.Custom.S_STRING, this.mContext.getPackageName()), new DialogInterface.OnClickListener() { // from class: com.mu.update.UpdateManager.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                UpdateManager.this.showDownloadDialog();
            }
        });
        builder.setNegativeButton(this.mContext.getResources().getIdentifier("soft_update_later", TypedValues.Custom.S_STRING, this.mContext.getPackageName()), new DialogInterface.OnClickListener() { // from class: com.mu.update.UpdateManager.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                UpdateManager.this.quitApplication();
            }
        });
        AlertDialog create = builder.create();
        create.setCancelable(false);
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(this.mContext.getResources().getIdentifier("soft_updating", TypedValues.Custom.S_STRING, this.mContext.getPackageName()));
        View inflate = LayoutInflater.from(this.mContext).inflate(this.mContext.getResources().getIdentifier("softupdate_progress", "layout", this.mContext.getPackageName()), (ViewGroup) null);
        this.mProgress = (ProgressBar) inflate.findViewById(this.mContext.getResources().getIdentifier("update_progress", "id", this.mContext.getPackageName()));
        this.mTextView = (TextView) inflate.findViewById(this.mContext.getResources().getIdentifier("textView", "id", this.mContext.getPackageName()));
        builder.setView(inflate);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        this.mDownloadDialog = create;
        create.setCancelable(false);
        this.mDownloadDialog.show();
        if (Environment.getExternalStorageState().equals("mounted")) {
            downLoadApkUrl downloadapkurl = new downLoadApkUrl();
            this.mdownloadApkUrl = downloadapkurl;
            downloadapkurl.start();
        }
    }

    public void RecursionDeleteFile(File file, Boolean bool) {
        if (file.isFile()) {
            File file2 = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(file2);
            file2.delete();
            return;
        }
        if (file.isDirectory()) {
            if (!file.getName().equals(this.mVersionString) || bool.booleanValue()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    File file3 = new File(file.getAbsolutePath() + System.currentTimeMillis());
                    file.renameTo(file3);
                    file3.delete();
                    return;
                }
                for (File file4 : listFiles) {
                    RecursionDeleteFile(file4, bool);
                }
                File file5 = new File(file.getAbsolutePath() + System.currentTimeMillis());
                file.renameTo(file5);
                file5.delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class downLoadApkUrl extends Thread {
        private downLoadApkUrl() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UpdateManager updateManager = UpdateManager.this;
            updateManager.creationApkLoadUrl(updateManager.mUrlString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void creationApkLoadUrl(String str) {
        String file = Environment.getExternalStorageDirectory().toString();
        File file2 = new File(file, this.packageNaString);
        if (!file2.exists()) {
            file2.mkdir();
        }
        this.mSavePath = file + RemoteSettings.FORWARD_SLASH_STRING + this.packageNaString + RemoteSettings.FORWARD_SLASH_STRING + this.mVersionString;
        this.mApkString = str.substring(str.lastIndexOf(47) + 1);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(this.mSavePath.replace(Environment.getExternalStorageDirectory().toString(), ""), this.mApkString);
        request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str)));
        request.setNotificationVisibility(1);
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        DownloadManager downloadManager = (DownloadManager) this.mContext.getSystemService("download");
        if (downloadManager == null) {
            return;
        }
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterByStatus(2);
        Cursor query2 = downloadManager.query(query);
        while (query2.moveToNext()) {
            String string = query2.getString(query2.getColumnIndex(ShareConstants.MEDIA_URI));
            query2.getString(query2.getColumnIndex("local_uri"));
            if (string.equals(str)) {
                this.downApkId = Integer.parseInt(query2.getString(query2.getColumnIndex(FileDownloadModel.f718ID)));
                startDownloadRegister();
                this.localAPkUri = query2.getString(query2.getColumnIndex("local_uri"));
                return;
            }
        }
        query.setFilterByStatus(8);
        Cursor query3 = downloadManager.query(query);
        while (query3.moveToNext()) {
            String string2 = query3.getString(query3.getColumnIndex(ShareConstants.MEDIA_URI));
            String string3 = query3.getString(query3.getColumnIndex("title"));
            if (string2.equals(str) && new File(this.mSavePath, string3).exists()) {
                this.mApkString = string3;
                this.localAPkUri = query3.getString(query3.getColumnIndex("local_uri"));
                installApk();
                if (this.mDownloadDialog.isShowing()) {
                    this.mDownloadDialog.dismiss();
                    return;
                }
                return;
            }
        }
        this.downApkId = downloadManager.enqueue(request);
        this.mdownHandle.sendEmptyMessage(1);
        startDownloadRegister();
    }

    private void startDownloadRegister() {
        if (this.downApkBroadcast == null) {
            this.downApkBroadcast = new DownApkBroadcast();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
            intentFilter.addAction("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
            this.mContext.registerReceiver(this.downApkBroadcast, intentFilter);
        }
        if (this.downApkProgressRunnable == null) {
            DownApkProgressRunnable downApkProgressRunnable = new DownApkProgressRunnable(this.mContext, this.mHandler);
            this.downApkProgressRunnable = downApkProgressRunnable;
            downApkProgressRunnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DownApkProgressRunnable implements Runnable {
        private Context context;
        private DownloadManager downloadManager;
        private Handler handler;

        public DownApkProgressRunnable(Context context, Handler handler) {
            this.context = context;
            this.handler = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.downloadManager = (DownloadManager) this.context.getSystemService("download");
            DownloadManager.Query query = new DownloadManager.Query();
            boolean z = true;
            query.setFilterById(UpdateManager.this.downApkId);
            while (z) {
                try {
                    Cursor query2 = this.downloadManager.query(query);
                    if (query2 != null && query2.moveToFirst()) {
                        int i = query2.getInt(query2.getColumnIndex("status"));
                        if (i == 2) {
                            UpdateManager.this.progress = ((query2.getInt(query2.getColumnIndex("bytes_so_far")) * 1.0f) / query2.getInt(query2.getColumnIndex("total_size"))) * 100.0f;
                            this.handler.sendEmptyMessage(2);
                        } else if (i != 4) {
                            if (i == 8) {
                                UpdateManager.this.localAPkUri = query2.getString(query2.getColumnIndex("local_uri"));
                                this.handler.sendEmptyMessage(8);
                            } else if (i == 16) {
                                this.handler.sendEmptyMessage(16);
                            }
                            z = false;
                        } else {
                            this.handler.sendEmptyMessage(4);
                        }
                    }
                    if (query2 != null) {
                        query2.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public void installApk() {
        if (Build.VERSION.SDK_INT >= 26) {
            if (this.mContext.getPackageManager().canRequestPackageInstalls()) {
                installApkHasPermission();
                return;
            }
            ((Activity) this.mContext).startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + this.mContext.getPackageName())), 100);
            return;
        }
        installApkHasPermission();
    }

    private void installApkHasPermission() {
        Intent intent;
        try {
            File file = new File(this.mSavePath, this.mApkString);
            if (FileUtils.checkApkValid(file)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    intent = new Intent("android.intent.action.INSTALL_PACKAGE");
                    Uri uriForFile = FileProvider.getUriForFile(this.mContext, this.mContext.getPackageName() + ".fileProvider", file);
                    intent.setFlags(1);
                    intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                    intent.addFlags(268435456);
                    intent.addFlags(2);
                } else {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(268435456);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                }
                ((Activity) this.mContext).startActivityForResult(intent, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void quitApplication() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(67108864);
        this.mContext.startActivity(intent);
        Process.killProcess(Process.myPid());
    }
}
