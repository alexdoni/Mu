package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Pair;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.aj */
/* loaded from: classes3.dex */
public final class RunnableC2575aj implements Runnable {

    /* renamed from: a */
    protected int f1099a;

    /* renamed from: b */
    protected long f1100b;

    /* renamed from: c */
    protected long f1101c;

    /* renamed from: d */
    private int f1102d;

    /* renamed from: e */
    private int f1103e;

    /* renamed from: f */
    private final Context f1104f;

    /* renamed from: g */
    private final int f1105g;

    /* renamed from: h */
    private final byte[] f1106h;

    /* renamed from: i */
    private final C2566aa f1107i;

    /* renamed from: j */
    private final C2568ac f1108j;

    /* renamed from: k */
    private final C2571af f1109k;

    /* renamed from: l */
    private final C2574ai f1110l;

    /* renamed from: m */
    private final int f1111m;

    /* renamed from: n */
    private final InterfaceC2573ah f1112n;

    /* renamed from: o */
    private final InterfaceC2573ah f1113o;

    /* renamed from: p */
    private String f1114p;

    /* renamed from: q */
    private final String f1115q;

    /* renamed from: r */
    private final Map<String, String> f1116r;

    /* renamed from: s */
    private boolean f1117s;

    public RunnableC2575aj(Context context, int i, int i2, byte[] bArr, String str, String str2, InterfaceC2573ah interfaceC2573ah, boolean z) {
        this(context, i, i2, bArr, str, str2, interfaceC2573ah, 2, 30000, z);
    }

    public RunnableC2575aj(Context context, int i, int i2, byte[] bArr, String str, String str2, InterfaceC2573ah interfaceC2573ah, int i3, int i4, boolean z) {
        this.f1102d = 2;
        this.f1103e = 30000;
        this.f1114p = null;
        this.f1099a = 0;
        this.f1100b = 0L;
        this.f1101c = 0L;
        this.f1117s = false;
        this.f1104f = context;
        this.f1107i = C2566aa.m646a(context);
        this.f1106h = bArr;
        this.f1108j = C2568ac.m710a();
        if (C2571af.f1064a == null) {
            C2571af.f1064a = new C2571af(context);
        }
        this.f1109k = C2571af.f1064a;
        C2574ai m746a = C2574ai.m746a();
        this.f1110l = m746a;
        this.f1111m = i;
        this.f1114p = str;
        this.f1115q = str2;
        this.f1112n = interfaceC2573ah;
        this.f1113o = m746a.f1083a;
        this.f1105g = i2;
        if (i3 > 0) {
            this.f1102d = i3;
        }
        if (i4 > 0) {
            this.f1103e = i4;
        }
        this.f1117s = z;
        this.f1116r = null;
    }

    /* renamed from: a */
    private static void m766a(String str) {
        C2577al.m787e("[Upload] Failed to upload(%d): %s", 1, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x002a  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m767a(boolean r5, int r6, java.lang.String r7) {
        /*
            r4 = this;
            int r0 = r4.f1105g
            r1 = 630(0x276, float:8.83E-43)
            if (r0 == r1) goto L1a
            r1 = 640(0x280, float:8.97E-43)
            if (r0 == r1) goto L17
            r1 = 830(0x33e, float:1.163E-42)
            if (r0 == r1) goto L1a
            r1 = 840(0x348, float:1.177E-42)
            if (r0 == r1) goto L17
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L1c
        L17:
            java.lang.String r0 = "userinfo"
            goto L1c
        L1a:
            java.lang.String r0 = "crash"
        L1c:
            r1 = 1
            r2 = 0
            if (r5 == 0) goto L2a
            java.lang.Object[] r6 = new java.lang.Object[r1]
            r6[r2] = r0
            java.lang.String r0 = "[Upload] Success: %s"
            com.tencent.bugly.proguard.C2577al.m781a(r0, r6)
            goto L3d
        L2a:
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3[r2] = r6
            r3[r1] = r0
            r6 = 2
            r3[r6] = r7
            java.lang.String r6 = "[Upload] Failed to upload(%d) %s: %s"
            com.tencent.bugly.proguard.C2577al.m787e(r6, r3)
        L3d:
            long r0 = r4.f1100b
            long r2 = r4.f1101c
            long r0 = r0 + r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 <= 0) goto L5d
            com.tencent.bugly.proguard.ai r6 = r4.f1110l
            boolean r0 = r4.f1117s
            long r0 = r6.m758a(r0)
            long r2 = r4.f1100b
            long r0 = r0 + r2
            long r2 = r4.f1101c
            long r0 = r0 + r2
            com.tencent.bugly.proguard.ai r6 = r4.f1110l
            boolean r2 = r4.f1117s
            r6.m762a(r0, r2)
        L5d:
            com.tencent.bugly.proguard.ah r6 = r4.f1112n
            if (r6 == 0) goto L64
            r6.mo745a(r5, r7)
        L64:
            com.tencent.bugly.proguard.ah r6 = r4.f1113o
            if (r6 == 0) goto L6b
            r6.mo745a(r5, r7)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.RunnableC2575aj.m767a(boolean, int, java.lang.String):void");
    }

    /* renamed from: a */
    private static boolean m768a(C2610br c2610br, C2566aa c2566aa, C2568ac c2568ac) {
        if (c2610br == null) {
            C2577al.m786d("resp == null!", new Object[0]);
            return false;
        }
        if (c2610br.f1388a != 0) {
            C2577al.m787e("resp result error %d", Byte.valueOf(c2610br.f1388a));
            return false;
        }
        try {
            if (!C2581ap.m844b(c2610br.f1394g) && !C2566aa.m648b().m668i().equals(c2610br.f1394g)) {
                C2635w.m1154a().m1174a(C2568ac.f1054a, DeviceRequestsHelper.DEVICE_INFO_DEVICE, c2610br.f1394g.getBytes("UTF-8"), true);
                c2566aa.m660d(c2610br.f1394g);
            }
        } catch (Throwable th) {
            C2577al.m782a(th);
        }
        c2566aa.f1037m = c2610br.f1392e;
        if (c2610br.f1389b == 510) {
            if (c2610br.f1390c == null) {
                C2577al.m787e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(c2610br.f1389b));
                return false;
            }
            C2612bt c2612bt = (C2612bt) C2570ae.m727a(c2610br.f1390c, C2612bt.class);
            if (c2612bt == null) {
                C2577al.m787e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(c2610br.f1389b));
                return false;
            }
            c2568ac.m717a(c2612bt);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        C2568ac c2568ac;
        Pair pair;
        boolean booleanValue;
        try {
            this.f1099a = 0;
            this.f1100b = 0L;
            this.f1101c = 0L;
            if (C2567ab.m690c(this.f1104f) == null) {
                str = "network is not available";
            } else {
                byte[] bArr = this.f1106h;
                if (bArr != null && bArr.length != 0) {
                    if (this.f1104f != null && this.f1107i != null && (c2568ac = this.f1108j) != null && this.f1109k != null) {
                        str = c2568ac.m719c() == null ? "illegal local strategy" : null;
                    }
                    str = "illegal access error";
                }
                str = "request package is empty!";
            }
            if (str != null) {
                m767a(false, 0, str);
                return;
            }
            byte[] m835a = C2581ap.m835a(this.f1106h);
            if (m835a == null) {
                m767a(false, 0, "failed to zip request body");
                return;
            }
            HashMap hashMap = new HashMap(10);
            hashMap.put("tls", "1");
            hashMap.put("prodId", this.f1107i.m661e());
            hashMap.put("bundleId", this.f1107i.f1027c);
            hashMap.put("appVer", this.f1107i.f1039o);
            Map<String, String> map = this.f1116r;
            if (map != null) {
                hashMap.putAll(map);
            }
            hashMap.put("cmd", Integer.toString(this.f1105g));
            hashMap.put("platformId", Byte.toString((byte) 1));
            hashMap.put("sdkVer", this.f1107i.f1032h);
            hashMap.put("strategylastUpdateTime", Long.toString(this.f1108j.m719c().f882o));
            this.f1110l.m759a(this.f1111m, System.currentTimeMillis());
            String str2 = this.f1114p;
            this.f1108j.m719c();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = i + 1;
                if (i < this.f1102d) {
                    if (i3 > 1) {
                        C2577al.m786d("[Upload] Failed to upload last time, wait and try(%d) again.", Integer.valueOf(i3));
                        C2581ap.m840b(this.f1103e);
                        if (i3 == this.f1102d) {
                            C2577al.m786d("[Upload] Use the back-up url at the last time: %s", this.f1115q);
                            str2 = this.f1115q;
                        }
                    }
                    C2577al.m785c("[Upload] Send %d bytes", Integer.valueOf(m835a.length));
                    str2 = m769b(str2);
                    C2577al.m785c("[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).", str2, Integer.valueOf(this.f1105g), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    byte[] m734a = this.f1109k.m734a(str2, m835a, this, hashMap);
                    Map<String, String> map2 = this.f1109k.f1066c;
                    Pair<Boolean, Boolean> m765a = m765a(m734a, map2);
                    if (!((Boolean) m765a.first).booleanValue()) {
                        booleanValue = ((Boolean) m765a.second).booleanValue();
                    } else {
                        Pair<Boolean, Boolean> m764a = m764a(map2);
                        if (!((Boolean) m764a.first).booleanValue()) {
                            booleanValue = ((Boolean) m764a.second).booleanValue();
                        } else {
                            byte[] m845b = C2581ap.m845b(m734a);
                            if (m845b != null) {
                                m734a = m845b;
                            }
                            C2610br m725a = C2570ae.m725a(m734a);
                            if (m725a == null) {
                                m767a(false, 1, "failed to decode response package");
                                Boolean bool = Boolean.FALSE;
                                pair = new Pair(bool, bool);
                            } else {
                                Object[] objArr = new Object[2];
                                objArr[0] = Integer.valueOf(m725a.f1389b);
                                objArr[1] = Integer.valueOf(m725a.f1390c == null ? 0 : m725a.f1390c.length);
                                C2577al.m785c("[Upload] Response cmd is: %d, length of sBuffer is: %d", objArr);
                                if (!m768a(m725a, this.f1107i, this.f1108j)) {
                                    m767a(false, 2, "failed to process response package");
                                    Boolean bool2 = Boolean.FALSE;
                                    pair = new Pair(bool2, bool2);
                                } else {
                                    m767a(true, 2, "successfully uploaded");
                                    Boolean bool3 = Boolean.TRUE;
                                    pair = new Pair(bool3, bool3);
                                }
                            }
                            booleanValue = !((Boolean) pair.first).booleanValue() ? ((Boolean) pair.second).booleanValue() : false;
                        }
                    }
                    if (!booleanValue) {
                        return;
                    }
                    i2 = 1;
                    i = i3;
                } else {
                    m767a(false, i2, "failed after many attempts");
                    return;
                }
            }
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private Pair<Boolean, Boolean> m765a(byte[] bArr, Map<String, String> map) {
        if (bArr == null) {
            m766a("Failed to upload for no response!");
            return new Pair<>(Boolean.FALSE, Boolean.TRUE);
        }
        C2577al.m785c("[Upload] Received %d bytes", Integer.valueOf(bArr.length));
        if (bArr.length == 0) {
            m767a(false, 1, "response data from server is empty");
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    C2577al.m785c("[Upload] HTTP headers from server: key = %s, value = %s", entry.getKey(), entry.getValue());
                }
            }
            Boolean bool = Boolean.FALSE;
            return new Pair<>(bool, bool);
        }
        Boolean bool2 = Boolean.TRUE;
        return new Pair<>(bool2, bool2);
    }

    /* renamed from: a */
    public final void m770a(long j) {
        this.f1099a++;
        this.f1100b += j;
    }

    /* renamed from: b */
    public final void m771b(long j) {
        this.f1101c += j;
    }

    /* renamed from: b */
    private static String m769b(String str) {
        if (C2581ap.m844b(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", str, UUID.randomUUID().toString());
        } catch (Throwable th) {
            C2577al.m782a(th);
            return str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.util.Pair<java.lang.Boolean, java.lang.Boolean> m764a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.RunnableC2575aj.m764a(java.util.Map):android.util.Pair");
    }
}
