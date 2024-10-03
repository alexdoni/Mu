package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ap */
/* loaded from: classes3.dex */
public final class C2581ap {

    /* renamed from: a */
    private static Map<String, String> f1156a;

    /* renamed from: a */
    public static String m822a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (C2577al.m782a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: a */
    public static String m816a() {
        return m818a(System.currentTimeMillis());
    }

    /* renamed from: a */
    public static String m818a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    public static String m823a(Date date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    public static byte[] m834a(String str, String str2) {
        ZipOutputStream zipOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        if (str == null || str.length() == 0) {
            return null;
        }
        C2577al.m785c("rqdp{  ZF start}", new Object[0]);
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        } catch (Throwable th) {
            th = th;
            zipOutputStream = null;
        }
        try {
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.finish();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            C2577al.m785c("rqdp{  ZF end}", new Object[0]);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                C2577al.m785c("rqdp{  ZF end}", new Object[0]);
                return null;
            } catch (Throwable th3) {
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                C2577al.m785c("rqdp{  ZF end}", new Object[0]);
                throw th3;
            }
        }
    }

    /* renamed from: a */
    public static byte[] m835a(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        C2577al.m785c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            return C2600bh.m990a().mo991a(bArr);
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m845b(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        C2577al.m785c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            return C2600bh.m990a().mo992b(bArr);
        } catch (Throwable th) {
            if (th.getMessage() != null && th.getMessage().contains("Not in GZIP format")) {
                C2577al.m786d(th.getMessage(), new Object[0]);
                return null;
            }
            if (C2577al.m782a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static long m836b() {
        try {
            return (((System.currentTimeMillis() + TimeZone.getDefault().getRawOffset()) / 86400000) * 86400000) - TimeZone.getDefault().getRawOffset();
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return -1L;
            }
            th.printStackTrace();
            return -1L;
        }
    }

    /* renamed from: c */
    public static String m846c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m831a(File file, File file2) {
        ZipOutputStream zipOutputStream;
        FileInputStream fileInputStream;
        C2577al.m785c("rqdp{  ZF start}", new Object[0]);
        if (!m843b(file, file2)) {
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
            } catch (Throwable th) {
                th = th;
                zipOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream = null;
        }
        try {
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            byte[] bArr = new byte[Math.max(5000, 1000)];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                zipOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            C2577al.m785c("rqdp{  ZF end}", new Object[0]);
            return true;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                C2577al.m785c("rqdp{  ZF end}", new Object[0]);
                return false;
            } catch (Throwable th4) {
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                C2577al.m785c("rqdp{  ZF end}", new Object[0]);
                throw th4;
            }
        }
    }

    /* renamed from: b */
    private static boolean m843b(File file, File file2) {
        if (file == null || file2 == null || file.equals(file2)) {
            C2577al.m786d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        }
        if (!file.exists() || !file.canRead()) {
            C2577al.m786d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
        try {
            if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Throwable th) {
            if (!C2577al.m782a(th)) {
                th.printStackTrace();
            }
        }
        return file2.exists() && file2.canWrite();
    }

    /* renamed from: a */
    public static String m820a(String str) {
        if (str.trim().equals("")) {
            return "";
        }
        try {
            if (f1156a == null) {
                f1156a = new HashMap();
            }
            if (f1156a.containsKey(str)) {
                return f1156a.get(str);
            }
            String systemProperty = NativeCrashHandler.getInstance().getSystemProperty(str);
            if (!TextUtils.isEmpty(systemProperty) && !systemProperty.equals("fail")) {
                f1156a.put(str, systemProperty);
            }
            return systemProperty;
        } catch (Throwable th) {
            C2577al.m784b(th);
            return "fail";
        }
    }

    /* renamed from: b */
    public static void m840b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static boolean m844b(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /* renamed from: c */
    public static void m847c(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.isFile() && file.exists() && file.canWrite()) {
            file.delete();
        }
    }

    /* renamed from: c */
    public static byte[] m849c(long j) {
        try {
            return String.valueOf(j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static long m850d(byte[] bArr) {
        if (bArr == null) {
            return -1L;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    /* renamed from: a */
    public static Context m811a(Context context) {
        Context applicationContext;
        return (context == null || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    /* renamed from: b */
    public static String m838b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static void m829a(Class<?> cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static Object m814a(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m828a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry<String, PlugInBean> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey".concat(String.valueOf(i)), (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).f865a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).f867c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).f866b);
        }
        parcel.writeBundle(bundle);
    }

    /* renamed from: a */
    public static Map<String, PlugInBean> m826a(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey".concat(String.valueOf(i))));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i2 + "plugInId"), readBundle.getString("pluginVal" + i2 + "plugInVersion"), readBundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            hashMap = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            C2577al.m787e("map plugin parcel error!", new Object[0]);
        }
        return hashMap;
    }

    /* renamed from: b */
    public static void m841b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        ArrayList<String> arrayList2 = new ArrayList<>(size);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(UserMetadata.KEYDATA_FILENAME, arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    /* renamed from: b */
    public static Map<String, String> m839b(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList(UserMetadata.KEYDATA_FILENAME);
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList != null && stringArrayList2 != null && stringArrayList.size() == stringArrayList2.size()) {
            hashMap = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        } else {
            C2577al.m787e("map parcel error!", new Object[0]);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static byte[] m833a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* renamed from: a */
    public static String m817a(int i, String str) {
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        StringBuilder sb = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(strArr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
                if (i > 0 && sb.length() > i) {
                    sb.delete(0, sb.length() - i);
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                sb.append("\n[error:");
                sb.append(th.toString());
                sb.append("]");
                String sb2 = sb.toString();
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return sb2;
            } finally {
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static Map<String, String> m827a(boolean z, int i) {
        if (!z) {
            C2577al.m785c("get all thread stack not enable", new Object[0]);
            return new HashMap();
        }
        Map<String, String> m825a = m825a(i);
        return m825a == null ? new HashMap() : m825a;
    }

    /* renamed from: a */
    private static Map<String, String> m825a(int i) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            int i2 = 0;
            sb.setLength(0);
            if (entry.getValue() != null && entry.getValue().length != 0) {
                StackTraceElement[] value = entry.getValue();
                int length = value.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = value[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                    i2++;
                }
                hashMap.put(entry.getKey().getName() + "(" + entry.getKey().getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static String m821a(Thread thread) {
        if (thread == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static boolean m830a(Context context, String str) {
        C2577al.m785c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < 10000) {
                    return false;
                }
                C2577al.m785c("[Util] Lock file (%s) is expired, unlock it.", str);
                m842b(context, str);
            }
            if (file.createNewFile()) {
                C2577al.m785c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            C2577al.m785c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            C2577al.m782a(th);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m842b(Context context, String str) {
        C2577al.m785c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            C2577al.m785c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C2577al.m782a(th);
            return false;
        }
    }

    /* renamed from: a */
    public static String m819a(File file, int i, boolean z) {
        BufferedReader bufferedReader;
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                    if (i > 0 && sb.length() > i) {
                        if (z) {
                            sb.delete(i, sb.length());
                            break;
                        }
                        sb.delete(0, sb.length() - i);
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        C2577al.m782a(th);
                        return null;
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e) {
                                C2577al.m782a(e);
                            }
                        }
                    }
                }
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception e2) {
                C2577al.m782a(e2);
            }
            return sb2;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    /* renamed from: a */
    public static BufferedReader m813a(File file) {
        if (file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (Throwable th) {
                C2577al.m782a(th);
            }
        }
        return null;
    }

    /* renamed from: b */
    public static BufferedReader m837b(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists() && file.canRead()) {
                return m813a(file);
            }
            return null;
        } catch (NullPointerException e) {
            C2577al.m782a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static Thread m824a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            C2577al.m787e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m832a(Runnable runnable) {
        C2576ak m772a = C2576ak.m772a();
        if (m772a != null) {
            return m772a.m774a(runnable);
        }
        String[] split = runnable.getClass().getName().split("\\.");
        return m824a(runnable, split[split.length - 1]) != null;
    }

    /* renamed from: d */
    public static boolean m852d(String str) {
        if (m844b(str)) {
            return false;
        }
        if (str.length() > 255) {
            C2577al.m781a("URL(%s)'s length is larger than 255.", str);
            return false;
        }
        if (str.toLowerCase().startsWith("http")) {
            return true;
        }
        C2577al.m781a("URL(%s) is not start with \"http\".", str);
        return false;
    }

    /* renamed from: a */
    public static SharedPreferences m812a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    /* renamed from: c */
    public static void m848c(String str, String str2) {
        if (C2566aa.m648b() == null || C2566aa.m648b().f990O == null) {
            return;
        }
        C2566aa.m648b().f990O.edit().putString(str, str2).apply();
    }

    /* renamed from: d */
    public static String m851d(String str, String str2) {
        return (C2566aa.m648b() == null || C2566aa.m648b().f990O == null) ? "" : C2566aa.m648b().f990O.getString(str, str2);
    }

    /* renamed from: a */
    public static <T> T m815a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            return creator.createFromParcel(obtain);
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                if (obtain == null) {
                    return null;
                }
                obtain.recycle();
                return null;
            } finally {
                if (obtain != null) {
                    obtain.recycle();
                }
            }
        }
    }
}
