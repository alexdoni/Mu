package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.C2577al;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class TraceFileHelper {

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a */
    /* loaded from: classes3.dex */
    public static class C2561a {

        /* renamed from: a */
        public long f949a;

        /* renamed from: b */
        public String f950b;

        /* renamed from: c */
        public long f951c;

        /* renamed from: d */
        public Map<String, String[]> f952d;
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b */
    /* loaded from: classes3.dex */
    public interface InterfaceC2562b {
        /* renamed from: a */
        boolean mo623a(long j);

        /* renamed from: a */
        boolean mo624a(long j, long j2, String str);

        /* renamed from: a */
        boolean mo625a(String str, int i, String str2, String str3);
    }

    public static C2561a readTargetDumpInfo(final String str, String str2, final boolean z) {
        if (str != null && str2 != null) {
            final C2561a c2561a = new C2561a();
            readTraceFile(str2, new InterfaceC2562b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.1
                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC2562b
                /* renamed from: a */
                public final boolean mo625a(String str3, int i, String str4, String str5) {
                    C2577al.m785c("new thread %s", str3);
                    if (C2561a.this.f949a > 0 && C2561a.this.f951c > 0 && C2561a.this.f950b != null) {
                        if (C2561a.this.f952d == null) {
                            C2561a.this.f952d = new HashMap();
                        }
                        C2561a.this.f952d.put(str3, new String[]{str4, str5, String.valueOf(i)});
                    }
                    return true;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC2562b
                /* renamed from: a */
                public final boolean mo624a(long j, long j2, String str3) {
                    C2577al.m785c("new process %s", str3);
                    if (!str3.equals(str)) {
                        return true;
                    }
                    C2561a.this.f949a = j;
                    C2561a.this.f950b = str3;
                    C2561a.this.f951c = j2;
                    return z;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC2562b
                /* renamed from: a */
                public final boolean mo623a(long j) {
                    C2577al.m785c("process end %d", Long.valueOf(j));
                    return C2561a.this.f949a <= 0 || C2561a.this.f951c <= 0 || C2561a.this.f950b == null;
                }
            });
            if (c2561a.f949a > 0 && c2561a.f951c > 0 && c2561a.f950b != null) {
                return c2561a;
            }
        }
        return null;
    }

    public static C2561a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            C2577al.m787e("path:%s", str);
            return null;
        }
        final C2561a c2561a = new C2561a();
        readTraceFile(str, new InterfaceC2562b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.2
            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC2562b
            /* renamed from: a */
            public final boolean mo625a(String str2, int i, String str3, String str4) {
                C2577al.m785c("new thread %s", str2);
                if (C2561a.this.f952d == null) {
                    C2561a.this.f952d = new HashMap();
                }
                C2561a.this.f952d.put(str2, new String[]{str3, str4, String.valueOf(i)});
                return true;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC2562b
            /* renamed from: a */
            public final boolean mo624a(long j, long j2, String str2) {
                C2577al.m785c("new process %s", str2);
                C2561a.this.f949a = j;
                C2561a.this.f950b = str2;
                C2561a.this.f951c = j2;
                return z;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC2562b
            /* renamed from: a */
            public final boolean mo623a(long j) {
                C2577al.m785c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (c2561a.f949a > 0 && c2561a.f951c > 0 && c2561a.f950b != null) {
            return c2561a;
        }
        C2577al.m787e("first dump error %s", c2561a.f949a + " " + c2561a.f951c + " " + c2561a.f950b);
        return null;
    }

    public static void readTraceFile(String str, InterfaceC2562b interfaceC2562b) {
        Throwable th;
        if (str == null || interfaceC2562b == null) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        file.lastModified();
        file.length();
        int i = 0;
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                try {
                    Pattern compile = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                    Pattern compile2 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                    Pattern compile3 = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                    Pattern compile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    while (true) {
                        Pattern[] patternArr = new Pattern[1];
                        patternArr[i] = compile;
                        Object[] m621a = m621a(bufferedReader2, patternArr);
                        if (m621a == null) {
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException e) {
                                if (C2577al.m782a(e)) {
                                    return;
                                }
                                e.printStackTrace();
                                return;
                            }
                        }
                        Pattern[] patternArr2 = new Pattern[1];
                        patternArr2[i] = compile3;
                        Object[] m621a2 = m621a(bufferedReader2, patternArr2);
                        if (m621a2 == null) {
                            C2577al.m786d("Failed to find process name.", new Object[i]);
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException e2) {
                                if (C2577al.m782a(e2)) {
                                    return;
                                }
                                e2.printStackTrace();
                                return;
                            }
                        }
                        String[] split = m621a[1].toString().split("\\s");
                        long parseLong = Long.parseLong(split[2]);
                        long time = simpleDateFormat.parse(split[4] + " " + split[5]).getTime();
                        Matcher matcher = compile3.matcher(m621a2[1].toString());
                        matcher.find();
                        matcher.group(1);
                        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                        if (!interfaceC2562b.mo624a(parseLong, time, matcher.group(1))) {
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException e3) {
                                if (C2577al.m782a(e3)) {
                                    return;
                                }
                                e3.printStackTrace();
                                return;
                            }
                        }
                        while (true) {
                            Object[] m621a3 = m621a(bufferedReader2, compile4, compile2);
                            if (m621a3 == null) {
                                break;
                            }
                            if (m621a3[0] == compile4) {
                                String obj = m621a3[1].toString();
                                Matcher matcher2 = Pattern.compile("\".+\"").matcher(obj);
                                matcher2.find();
                                String group = matcher2.group();
                                String substring = group.substring(1, group.length() - 1);
                                obj.contains("NATIVE");
                                Matcher matcher3 = Pattern.compile("tid=\\d+").matcher(obj);
                                matcher3.find();
                                String group2 = matcher3.group();
                                interfaceC2562b.mo625a(substring, Integer.parseInt(group2.substring(group2.indexOf("=") + 1)), m620a(bufferedReader2), m622b(bufferedReader2));
                            } else if (!interfaceC2562b.mo623a(Long.parseLong(m621a3[1].toString().split("\\s")[2]))) {
                                try {
                                    bufferedReader2.close();
                                    return;
                                } catch (IOException e4) {
                                    if (C2577al.m782a(e4)) {
                                        return;
                                    }
                                    e4.printStackTrace();
                                    return;
                                }
                            }
                        }
                        simpleDateFormat = simpleDateFormat2;
                        i = 0;
                    }
                } catch (Exception e5) {
                    e = e5;
                    bufferedReader = bufferedReader2;
                    if (!C2577al.m782a(e)) {
                        e.printStackTrace();
                    }
                    C2577al.m786d("trace open fail:%s : %s", e.getClass().getName(), e.getMessage());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                            if (C2577al.m782a(e6)) {
                                return;
                            }
                            e6.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader == null) {
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                        throw th;
                    } catch (IOException e7) {
                        if (C2577al.m782a(e7)) {
                            throw th;
                        }
                        e7.printStackTrace();
                        throw th;
                    }
                }
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    private static Object[] m621a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            for (Pattern pattern : patternArr) {
                if (pattern.matcher(readLine).matches()) {
                    return new Object[]{pattern, readLine};
                }
            }
        }
    }

    /* renamed from: a */
    private static String m620a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static String m622b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.trim().length() <= 0) {
                break;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }
}
