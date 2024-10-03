package com.u2020.sdk.mc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ChannelReader.java */
/* renamed from: com.u2020.sdk.mc.d */
/* loaded from: classes3.dex */
public final class C2654d {

    /* renamed from: a */
    public static final String f1577a = "agentID";

    /* renamed from: b */
    public static final String f1578b = "siteID";

    private C2654d() {
    }

    /* renamed from: a */
    public static Channel m1201a(final File apkFile) {
        Map<String, Object> m1202b = m1202b(apkFile);
        if (m1202b == null) {
            return null;
        }
        long parseLong = Long.parseLong(m1202b.get(f1577a).toString());
        String obj = m1202b.get(f1578b).toString();
        m1202b.remove(f1577a);
        m1202b.remove(f1578b);
        return new Channel(parseLong, obj, m1202b);
    }

    /* renamed from: b */
    public static Map<String, Object> m1202b(final File apkFile) {
        try {
            String m1204d = m1204d(apkFile);
            if (m1204d == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(m1204d);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                hashMap.put(obj, jSONObject.get(obj));
            }
            return hashMap;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static Map<String, Object> m1203c(final File apkFile) throws Exception {
        try {
            String m1205e = m1205e(apkFile);
            if (m1205e != null && m1205e.length() != 0) {
                JSONObject jSONObject = new JSONObject(m1205e);
                Iterator<String> keys = jSONObject.keys();
                HashMap hashMap = new HashMap();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    hashMap.put(obj, jSONObject.get(obj));
                }
                return hashMap;
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    /* renamed from: d */
    public static String m1204d(final File apkFile) {
        return C2656f.m1213b(apkFile, C2652b.f1560a);
    }

    /* renamed from: e */
    private static String m1205e(File file) throws Exception {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            long length = randomAccessFile.length();
            byte[] bArr = new byte[C2652b.f1561b.length];
            long length2 = length - r6.length;
            randomAccessFile.seek(length2);
            randomAccessFile.readFully(bArr);
            if (C2652b.m1196a(bArr)) {
                long j = length2 - 2;
                randomAccessFile.seek(j);
                int m1192a = C2652b.m1192a(randomAccessFile);
                if (m1192a > 0) {
                    randomAccessFile.seek(j - m1192a);
                    byte[] bArr2 = new byte[m1192a];
                    randomAccessFile.readFully(bArr2);
                    String str = new String(bArr2, "UTF-8");
                    randomAccessFile.close();
                    return str;
                }
                throw new Exception("zip channel info not found");
            }
            throw new Exception("zip v1 magic not found");
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    /* renamed from: f */
    public static Channel m1206f(File file) throws Exception {
        Map<String, Object> m1203c = m1203c(file);
        if (m1203c == null) {
            return null;
        }
        long parseLong = Long.parseLong(m1203c.get(f1577a).toString());
        String obj = m1203c.get(f1578b).toString();
        m1203c.remove(f1577a);
        m1203c.remove(f1578b);
        return new Channel(parseLong, obj, m1203c);
    }
}
