package com.mu.logtool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.mu.utility.Encrypt.Encrypt;
import com.mu.utility.FileUtils;
import com.mu.utility.MemoryUtility;
import com.mu.utility.SystemUtil;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";
    private static final String Upload_URL = "ヰな﹃ふテててЦせΙСΨηけㄌム∏ㄎチ︽ㄩけㄉㄢちモけΗォュЁチνテせΙΨはて";
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Android/data/";
    private static CrashHandler mInstance = null;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
    }

    public static CrashHandler getInstance() {
        if (mInstance == null) {
            synchronized (CrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new CrashHandler();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        Encrypt.Init();
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = context.getApplicationContext();
    }

    private void dumpExceptionToSDCard(Throwable th) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            String str = PATH + this.mContext.getPackageName() + "/crash/";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
            File file2 = new File(str + "crash" + format + FILE_NAME_SUFFIX);
            try {
                MemoryUtility memoryUtility = new MemoryUtility(this.mContext);
                Log.e("Android", "1");
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
                Log.e("Android", "2");
                PackageManager packageManager = this.mContext.getPackageManager();
                Log.e("Android", "3");
                PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 1);
                Log.e("Android", "4");
                printWriter.println(getText("crach_time") + format);
                Log.e("Android", "5");
                printWriter.println("IMEI: " + SystemUtil.getIMEI(this.mContext));
                Log.e("Android", "6");
                printWriter.println("packageName: " + this.mContext.getPackageName());
                Log.e("Android", "7");
                printWriter.println(getText("app_version") + packageInfo.versionName);
                Log.e("Android", Constants.SDK_LOGIN_TYPE_HUAWEI);
                printWriter.println(getText("app_code") + packageInfo.versionCode);
                Log.e("Android", Constants.SDK_LOGIN_TYPE_HUAWEI);
                printWriter.println(getText("android_version") + Build.VERSION.RELEASE);
                Log.e("Android", "9");
                printWriter.println(getText("android_apiVersion") + Build.VERSION.SDK_INT);
                Log.e("Android", "10");
                printWriter.println(getText("android_MANUFACTURER") + Build.MANUFACTURER);
                Log.e("Android", "11");
                printWriter.println(getText("android_MODEL") + Build.MODEL);
                Log.e("Android", "12");
                printWriter.println(getText("systemAvaialbeMemorySize") + memoryUtility.getSystemAvaialbeMemorySize());
                Log.e("Android", "13");
                printWriter.println(getText("IsLowMemoryState") + memoryUtility.isLowMemory());
                Log.e("Android", "14");
                th.printStackTrace(printWriter);
                Log.e("Android", "15");
                printWriter.close();
                Log.e("Android", "16");
                uploadExceptionToServer(th, file2);
            } catch (Exception unused) {
            }
        }
    }

    public String getText(String str) {
        Context context = this.mContext;
        return context.getString(context.getResources().getIdentifier(str, TypedValues.Custom.S_STRING, this.mContext.getPackageName()));
    }

    private void uploadExceptionToServer(Throwable th, File file) {
        if (file != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String LoadJson = FileUtils.LoadJson(bufferedReader);
                bufferedReader.close();
                doUploadPost(Encrypt.DeCode(Upload_URL), LoadJson.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String doUploadPost(final String str, final byte[] bArr) {
        new Thread(new Runnable() { // from class: com.mu.logtool.CrashHandler.1
            /* JADX WARN: Code restructure failed: missing block: B:10:0x0043, code lost:
            
                if (r2 != null) goto L34;
             */
            /* JADX WARN: Code restructure failed: missing block: B:19:0x006f, code lost:
            
                if (r2 == null) goto L42;
             */
            /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r5 = this;
                    java.lang.String r0 = "HttpUtility"
                    byte[] r1 = r2
                    if (r1 == 0) goto L7d
                    int r1 = r1.length
                    if (r1 >= 0) goto Lb
                    goto L7d
                Lb:
                    r1 = 0
                    java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.io.IOException -> L5a java.net.MalformedURLException -> L66
                    java.lang.String r3 = r3     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.io.IOException -> L5a java.net.MalformedURLException -> L66
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.io.IOException -> L5a java.net.MalformedURLException -> L66
                    java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.io.IOException -> L5a java.net.MalformedURLException -> L66
                    java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.io.IOException -> L5a java.net.MalformedURLException -> L66
                    java.lang.String r1 = "POST"
                    r2.setRequestMethod(r1)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r1 = 1
                    r2.setDoInput(r1)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r2.setDoOutput(r1)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r1 = 30000(0x7530, float:4.2039E-41)
                    r2.setConnectTimeout(r1)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r2.setReadTimeout(r1)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r1 = 0
                    r2.setUseCaches(r1)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    java.io.OutputStream r1 = r2.getOutputStream()     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    byte[] r3 = r2     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r1.write(r3)     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r1.flush()     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r1.close()     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    r2.getResponseCode()     // Catch: java.lang.Exception -> L46 java.io.IOException -> L48 java.net.MalformedURLException -> L4a java.lang.Throwable -> L75
                    if (r2 == 0) goto L74
                    goto L71
                L46:
                    r1 = move-exception
                    goto L52
                L48:
                    r1 = move-exception
                    goto L5e
                L4a:
                    r1 = move-exception
                    goto L6a
                L4c:
                    r0 = move-exception
                    goto L77
                L4e:
                    r2 = move-exception
                    r4 = r2
                    r2 = r1
                    r1 = r4
                L52:
                    java.lang.String r3 = "Exception doUploadPost"
                    android.util.Log.e(r0, r3, r1)     // Catch: java.lang.Throwable -> L75
                    if (r2 == 0) goto L74
                    goto L71
                L5a:
                    r2 = move-exception
                    r4 = r2
                    r2 = r1
                    r1 = r4
                L5e:
                    java.lang.String r3 = "IOException doUploadPost"
                    android.util.Log.e(r0, r3, r1)     // Catch: java.lang.Throwable -> L75
                    if (r2 == 0) goto L74
                    goto L71
                L66:
                    r2 = move-exception
                    r4 = r2
                    r2 = r1
                    r1 = r4
                L6a:
                    java.lang.String r3 = "MalformedURLException doUploadPost"
                    android.util.Log.e(r0, r3, r1)     // Catch: java.lang.Throwable -> L75
                    if (r2 == 0) goto L74
                L71:
                    r2.disconnect()
                L74:
                    return
                L75:
                    r0 = move-exception
                    r1 = r2
                L77:
                    if (r1 == 0) goto L7c
                    r1.disconnect()
                L7c:
                    throw r0
                L7d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mu.logtool.CrashHandler.RunnableC22391.run():void");
            }
        }).start();
        return null;
    }
}
