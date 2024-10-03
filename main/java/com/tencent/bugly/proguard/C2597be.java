package com.tencent.bugly.proguard;

import android.content.Context;
import com.facebook.internal.security.CertificateUtil;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.be */
/* loaded from: classes3.dex */
public final class C2597be {

    /* renamed from: a */
    private static List<File> f1300a = new ArrayList();

    /* renamed from: d */
    private static Map<String, Integer> m979d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split = str2.split(CertificateUtil.DELIMITER);
                if (split.length != 2) {
                    C2577al.m787e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split[0], Integer.valueOf(Integer.parseInt(split[1])));
            }
            return hashMap;
        } catch (Exception e) {
            C2577al.m787e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m969a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static <KeyT, ValueT> ValueT m967a(Map<KeyT, ValueT> map, KeyT keyt, ValueT valuet) {
        ValueT valuet2;
        try {
            valuet2 = map.get(keyt);
        } catch (Exception e) {
            C2577al.m782a(e);
        }
        return valuet2 != null ? valuet2 : valuet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        return null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m968a(java.io.BufferedInputStream r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L26
            r2 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L26
        L8:
            int r2 = r4.read()     // Catch: java.lang.Throwable -> L24
            r3 = -1
            if (r2 == r3) goto L2d
            if (r2 != 0) goto L20
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L24
            byte[] r2 = r1.toByteArray()     // Catch: java.lang.Throwable -> L24
            java.lang.String r3 = "UTf-8"
            r4.<init>(r2, r3)     // Catch: java.lang.Throwable -> L24
            r1.close()
            return r4
        L20:
            r1.write(r2)     // Catch: java.lang.Throwable -> L24
            goto L8
        L24:
            r4 = move-exception
            goto L28
        L26:
            r4 = move-exception
            r1 = r0
        L28:
            com.tencent.bugly.proguard.C2577al.m782a(r4)     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L30
        L2d:
            r1.close()
        L30:
            return r0
        L31:
            r4 = move-exception
            if (r1 == 0) goto L37
            r1.close()
        L37:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2597be.m968a(java.io.BufferedInputStream):java.lang.String");
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* renamed from: a */
    public static CrashDetailBean m965a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        BufferedInputStream bufferedInputStream;
        String str2;
        String m968a;
        BufferedInputStream bufferedInputStream2 = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            C2577al.m787e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            ?? canRead = file.canRead();
            try {
                if (canRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    } catch (IOException e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        String m968a2 = m968a(bufferedInputStream);
                        if (m968a2 != null && m968a2.equals("NATIVE_RQD_REPORT")) {
                            HashMap hashMap = new HashMap();
                            loop0: while (true) {
                                str2 = null;
                                while (true) {
                                    m968a = m968a(bufferedInputStream);
                                    if (m968a == null) {
                                        break loop0;
                                    }
                                    if (str2 == null) {
                                        str2 = m968a;
                                    }
                                }
                                hashMap.put(str2, m968a);
                            }
                            if (str2 != null) {
                                C2577al.m787e("record not pair! drop! %s", str2);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                return null;
                            }
                            CrashDetailBean m966a = m966a(context, hashMap, nativeExceptionHandler);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            return m966a;
                        }
                        C2577al.m787e("record read fail! %s", m968a2);
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        return null;
                    } catch (IOException e6) {
                        e = e6;
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = canRead;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x006b, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006f, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0070, code lost:
    
        com.tencent.bugly.proguard.C2577al.m782a(r9);
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m976b(java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "reg_record.txt"
            java.io.BufferedReader r9 = com.tencent.bugly.proguard.C2581ap.m837b(r9, r0)
            r0 = 0
            if (r9 != 0) goto La
            return r0
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74
            r1.<init>()     // Catch: java.lang.Throwable -> L74
            java.lang.String r2 = r9.readLine()     // Catch: java.lang.Throwable -> L74
            if (r2 == 0) goto L69
            boolean r10 = r2.startsWith(r10)     // Catch: java.lang.Throwable -> L74
            if (r10 != 0) goto L1c
            goto L69
        L1c:
            java.lang.String r10 = "                "
            r2 = 0
            r3 = 18
            r4 = r2
            r5 = r4
        L23:
            java.lang.String r6 = r9.readLine()     // Catch: java.lang.Throwable -> L74
            java.lang.String r7 = "\n"
            if (r6 == 0) goto L57
            int r8 = r4 % 4
            if (r8 != 0) goto L3a
            if (r4 <= 0) goto L34
            r1.append(r7)     // Catch: java.lang.Throwable -> L74
        L34:
            java.lang.String r5 = "  "
            r1.append(r5)     // Catch: java.lang.Throwable -> L74
            goto L4d
        L3a:
            int r7 = r6.length()     // Catch: java.lang.Throwable -> L74
            r8 = 16
            if (r7 <= r8) goto L44
            r3 = 28
        L44:
            int r5 = r3 - r5
            java.lang.String r5 = r10.substring(r2, r5)     // Catch: java.lang.Throwable -> L74
            r1.append(r5)     // Catch: java.lang.Throwable -> L74
        L4d:
            int r5 = r6.length()     // Catch: java.lang.Throwable -> L74
            r1.append(r6)     // Catch: java.lang.Throwable -> L74
            int r4 = r4 + 1
            goto L23
        L57:
            r1.append(r7)     // Catch: java.lang.Throwable -> L74
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Throwable -> L74
            if (r9 == 0) goto L68
            r9.close()     // Catch: java.lang.Exception -> L64
            goto L68
        L64:
            r9 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r9)
        L68:
            return r10
        L69:
            if (r9 == 0) goto L73
            r9.close()     // Catch: java.lang.Exception -> L6f
            goto L73
        L6f:
            r9 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r9)
        L73:
            return r0
        L74:
            r10 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r10)     // Catch: java.lang.Throwable -> L83
            if (r9 == 0) goto L82
            r9.close()     // Catch: java.lang.Exception -> L7e
            goto L82
        L7e:
            r9 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r9)
        L82:
            return r0
        L83:
            r10 = move-exception
            if (r9 == 0) goto L8e
            r9.close()     // Catch: java.lang.Exception -> L8a
            goto L8e
        L8a:
            r9 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r9)
        L8e:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2597be.m976b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0045, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
    
        com.tencent.bugly.proguard.C2577al.m782a(r3);
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m977c(java.lang.String r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "map_record.txt"
            java.io.BufferedReader r3 = com.tencent.bugly.proguard.C2581ap.m837b(r3, r0)
            r0 = 0
            if (r3 != 0) goto La
            return r0
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a
            r1.<init>()     // Catch: java.lang.Throwable -> L4a
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L4a
            if (r2 == 0) goto L3f
            boolean r4 = r2.startsWith(r4)     // Catch: java.lang.Throwable -> L4a
            if (r4 != 0) goto L1c
            goto L3f
        L1c:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L4a
            if (r4 == 0) goto L30
            java.lang.String r2 = "  "
            r1.append(r2)     // Catch: java.lang.Throwable -> L4a
            r1.append(r4)     // Catch: java.lang.Throwable -> L4a
            java.lang.String r4 = "\n"
            r1.append(r4)     // Catch: java.lang.Throwable -> L4a
            goto L1c
        L30:
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L4a
            if (r3 == 0) goto L3e
            r3.close()     // Catch: java.lang.Exception -> L3a
            goto L3e
        L3a:
            r3 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r3)
        L3e:
            return r4
        L3f:
            if (r3 == 0) goto L49
            r3.close()     // Catch: java.lang.Exception -> L45
            goto L49
        L45:
            r3 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r3)
        L49:
            return r0
        L4a:
            r4 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r4)     // Catch: java.lang.Throwable -> L59
            if (r3 == 0) goto L58
            r3.close()     // Catch: java.lang.Exception -> L54
            goto L58
        L54:
            r3 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r3)
        L58:
            return r0
        L59:
            r4 = move-exception
            if (r3 == 0) goto L64
            r3.close()     // Catch: java.lang.Exception -> L60
            goto L64
        L60:
            r3 = move-exception
            com.tencent.bugly.proguard.C2577al.m782a(r3)
        L64:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2597be.m977c(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static String m971a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String m976b = m976b(str, str2);
        if (m976b != null && !m976b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(m976b);
        }
        String m977c = m977c(str, str2);
        if (m977c != null && !m977c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(m977c);
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m975b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /* renamed from: c */
    public static void m978c(String str) {
        File[] listFiles;
        if (str == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.canRead() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                        file2.delete();
                        C2577al.m785c("Delete empty record file %s", file2.getAbsoluteFile());
                    }
                }
            }
        } catch (Throwable th) {
            C2577al.m782a(th);
        }
    }

    /* renamed from: a */
    public static void m973a(boolean z, String str) {
        if (str != null) {
            f1300a.add(new File(str, "rqd_record.eup"));
            f1300a.add(new File(str, "reg_record.txt"));
            f1300a.add(new File(str, "map_record.txt"));
            f1300a.add(new File(str, "backup_record.txt"));
            if (z) {
                m978c(str);
            }
        }
        List<File> list = f1300a;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (File file : f1300a) {
            if (file.exists() && file.canWrite()) {
                file.delete();
                C2577al.m785c("Delete record file %s", file.getAbsoluteFile());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    /* renamed from: a */
    public static String m970a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader = null;
        if (str != null && i > 0) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                C2577al.m781a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
                f1300a.add(file);
                C2577al.m785c("Add this record file to list for cleaning lastly.", new Object[0]);
                if (str2 == null) {
                    return C2581ap.m819a(new File(str), i, z);
                }
                String sb = new StringBuilder();
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                        while (true) {
                            try {
                                String readLine = bufferedReader2.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                                    sb.append(readLine);
                                    sb.append("\n");
                                }
                                if (i > 0 && sb.length() > i) {
                                    if (z) {
                                        sb.delete(i, sb.length());
                                        break;
                                    }
                                    sb.delete(0, sb.length() - i);
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                try {
                                    C2577al.m782a(th);
                                    sb.append("\n[error:" + th.toString() + "]");
                                    String sb2 = sb.toString();
                                    if (bufferedReader == null) {
                                        return sb2;
                                    }
                                    bufferedReader.close();
                                    sb = sb2;
                                    return sb;
                                } catch (Throwable th2) {
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e) {
                                            C2577al.m782a(e);
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        }
                        String sb3 = sb.toString();
                        bufferedReader2.close();
                        sb = sb3;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                    return sb;
                } catch (Exception e2) {
                    C2577al.m782a(e2);
                    return sb;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private static Map<String, String> m972a(Map<String, String> map) {
        String str = map.get("key-value");
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("\n")) {
            String[] split = str2.split("=");
            if (split.length == 2) {
                hashMap.put(split[0], split[1]);
            }
        }
        return hashMap;
    }

    /* renamed from: b */
    private static long m974b(Map<String, String> map) {
        String str = map.get("launchTime");
        if (str == null) {
            return -1L;
        }
        C2577al.m785c("[Native record info] launchTime: %s", str);
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            if (C2577al.m782a(e)) {
                return -1L;
            }
            e.printStackTrace();
            return -1L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0037 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.tencent.bugly.crashreport.crash.CrashDetailBean m966a(android.content.Context r25, java.util.Map<java.lang.String, java.lang.String> r26, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler r27) {
        /*
            Method dump skipped, instructions count: 589
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2597be.m966a(android.content.Context, java.util.Map, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }
}
