package com.tencent.bugly.proguard;

import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.i */
/* loaded from: classes3.dex */
public final class C2621i {

    /* renamed from: a */
    private StringBuilder f1456a;

    /* renamed from: b */
    private int f1457b;

    /* renamed from: a */
    private void m1019a(String str) {
        for (int i = 0; i < this.f1457b; i++) {
            this.f1456a.append('\t');
        }
        if (str != null) {
            StringBuilder sb = this.f1456a;
            sb.append(str);
            sb.append(": ");
        }
    }

    public C2621i(StringBuilder sb, int i) {
        this.f1456a = sb;
        this.f1457b = i;
    }

    /* renamed from: a */
    public final C2621i m1027a(boolean z, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append(z ? 'T' : 'F');
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C2621i m1020a(byte b, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append((int) b);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    private C2621i m1008a(char c, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append(c);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C2621i m1026a(short s, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append((int) s);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C2621i m1021a(int i, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append(i);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C2621i m1022a(long j, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append(j);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    private C2621i m1010a(float f, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append(f);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    private C2621i m1009a(double d, String str) {
        m1019a(str);
        StringBuilder sb = this.f1456a;
        sb.append(d);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C2621i m1024a(String str, String str2) {
        m1019a(str2);
        if (str == null) {
            this.f1456a.append("null\n");
        } else {
            StringBuilder sb = this.f1456a;
            sb.append(str);
            sb.append('\n');
        }
        return this;
    }

    /* renamed from: a */
    public final C2621i m1028a(byte[] bArr, String str) {
        m1019a(str);
        if (bArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (bArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(bArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(bArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (byte b : bArr) {
            c2621i.m1020a(b, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* renamed from: a */
    private C2621i m1018a(short[] sArr, String str) {
        m1019a(str);
        if (sArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (sArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(sArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(sArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (short s : sArr) {
            c2621i.m1026a(s, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* renamed from: a */
    private C2621i m1015a(int[] iArr, String str) {
        m1019a(str);
        if (iArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (iArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(iArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(iArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (int i : iArr) {
            c2621i.m1021a(i, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* renamed from: a */
    private C2621i m1016a(long[] jArr, String str) {
        m1019a(str);
        if (jArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (jArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(jArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(jArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (long j : jArr) {
            c2621i.m1022a(j, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* renamed from: a */
    private C2621i m1014a(float[] fArr, String str) {
        m1019a(str);
        if (fArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (fArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(fArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(fArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (float f : fArr) {
            c2621i.m1010a(f, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* renamed from: a */
    private C2621i m1013a(double[] dArr, String str) {
        m1019a(str);
        if (dArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (dArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(dArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(dArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (double d : dArr) {
            c2621i.m1009a(d, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* renamed from: a */
    public final <K, V> C2621i m1025a(Map<K, V> map, String str) {
        m1019a(str);
        if (map == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (map.isEmpty()) {
            StringBuilder sb = this.f1456a;
            sb.append(map.size());
            sb.append(", {}\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(map.size());
        sb2.append(", {\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        C2621i c2621i2 = new C2621i(this.f1456a, this.f1457b + 2);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            c2621i.m1008a('(', (String) null);
            c2621i2.m1011a((C2621i) entry.getKey(), (String) null);
            c2621i2.m1011a((C2621i) entry.getValue(), (String) null);
            c2621i.m1008a(')', (String) null);
        }
        m1008a('}', (String) null);
        return this;
    }

    /* renamed from: a */
    private <T> C2621i m1017a(T[] tArr, String str) {
        m1019a(str);
        if (tArr == null) {
            this.f1456a.append("null\n");
            return this;
        }
        if (tArr.length == 0) {
            StringBuilder sb = this.f1456a;
            sb.append(tArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f1456a;
        sb2.append(tArr.length);
        sb2.append(", [\n");
        C2621i c2621i = new C2621i(this.f1456a, this.f1457b + 1);
        for (T t : tArr) {
            c2621i.m1011a((C2621i) t, (String) null);
        }
        m1008a(']', (String) null);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private <T> C2621i m1012a(Collection<T> collection, String str) {
        if (collection == null) {
            m1019a(str);
            this.f1456a.append("null\t");
            return this;
        }
        return m1017a(collection.toArray(), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private <T> C2621i m1011a(T t, String str) {
        if (t == 0) {
            this.f1456a.append("null\n");
        } else if (t instanceof Byte) {
            m1020a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            m1027a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            m1026a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            m1021a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            m1022a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            m1010a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            m1009a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            m1024a((String) t, str);
        } else if (t instanceof Map) {
            m1025a((Map) t, str);
        } else if (t instanceof List) {
            m1012a((Collection) t, str);
        } else if (t instanceof AbstractC2625m) {
            m1023a((AbstractC2625m) t, str);
        } else if (t instanceof byte[]) {
            m1028a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            m1011a((C2621i) t, str);
        } else if (t instanceof short[]) {
            m1018a((short[]) t, str);
        } else if (t instanceof int[]) {
            m1015a((int[]) t, str);
        } else if (t instanceof long[]) {
            m1016a((long[]) t, str);
        } else if (t instanceof float[]) {
            m1014a((float[]) t, str);
        } else if (t instanceof double[]) {
            m1013a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            m1017a((Object[]) t, str);
        } else {
            throw new C2622j("write object error: unsupport type.");
        }
        return this;
    }

    /* renamed from: a */
    public final C2621i m1023a(AbstractC2625m abstractC2625m, String str) {
        m1008a('{', str);
        if (abstractC2625m == null) {
            StringBuilder sb = this.f1456a;
            sb.append('\t');
            sb.append(JsonSerializer.Null);
        } else {
            abstractC2625m.mo995a(this.f1456a, this.f1457b + 1);
        }
        m1008a('}', (String) null);
        return this;
    }
}
