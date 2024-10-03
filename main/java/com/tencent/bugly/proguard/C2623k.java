package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.k */
/* loaded from: classes3.dex */
public final class C2623k {

    /* renamed from: a */
    protected String f1458a = "GBK";

    /* renamed from: b */
    private ByteBuffer f1459b;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.k$a */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a */
        public byte f1460a;

        /* renamed from: b */
        public int f1461b;
    }

    public C2623k() {
    }

    public C2623k(byte[] bArr) {
        this.f1459b = ByteBuffer.wrap(bArr);
    }

    public C2623k(byte[] bArr, byte b) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.f1459b = wrap;
        wrap.position(4);
    }

    /* renamed from: a */
    public final void m1056a(byte[] bArr) {
        ByteBuffer byteBuffer = this.f1459b;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        this.f1459b = ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private static int m1031a(a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.f1460a = (byte) (b & Ascii.f555SI);
        aVar.f1461b = (b & 240) >> 4;
        if (aVar.f1461b != 15) {
            return 1;
        }
        aVar.f1461b = byteBuffer.get();
        return 2;
    }

    /* renamed from: a */
    private void m1037a(a aVar) {
        m1031a(aVar, this.f1459b);
    }

    /* renamed from: a */
    private void m1036a(int i) {
        ByteBuffer byteBuffer = this.f1459b;
        byteBuffer.position(byteBuffer.position() + i);
    }

    /* renamed from: b */
    private boolean m1040b(int i) {
        a aVar;
        try {
            aVar = new a();
            while (true) {
                int m1031a = m1031a(aVar, this.f1459b.duplicate());
                if (i <= aVar.f1461b || aVar.f1460a == 11) {
                    break;
                }
                m1036a(m1031a);
                m1035a(aVar.f1460a);
            }
        } catch (C2620h | BufferUnderflowException unused) {
        }
        return i == aVar.f1461b;
    }

    /* renamed from: a */
    private void m1034a() {
        a aVar = new a();
        do {
            m1037a(aVar);
            m1035a(aVar.f1460a);
        } while (aVar.f1460a != 11);
    }

    /* renamed from: b */
    private void m1039b() {
        a aVar = new a();
        m1037a(aVar);
        m1035a(aVar.f1460a);
    }

    /* renamed from: a */
    private void m1035a(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                m1036a(1);
                return;
            case 1:
                m1036a(2);
                return;
            case 2:
                m1036a(4);
                return;
            case 3:
                m1036a(8);
                return;
            case 4:
                m1036a(4);
                return;
            case 5:
                m1036a(8);
                return;
            case 6:
                int i2 = this.f1459b.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                m1036a(i2);
                return;
            case 7:
                m1036a(this.f1459b.getInt());
                return;
            case 8:
                int m1049a = m1049a(0, 0, true);
                while (i < m1049a * 2) {
                    m1039b();
                    i++;
                }
                return;
            case 9:
                int m1049a2 = m1049a(0, 0, true);
                while (i < m1049a2) {
                    m1039b();
                    i++;
                }
                return;
            case 10:
                m1034a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                a aVar = new a();
                m1037a(aVar);
                if (aVar.f1460a != 0) {
                    throw new C2620h("skipField with invalid type, type value: " + ((int) b) + ", " + ((int) aVar.f1460a));
                }
                m1036a(m1049a(0, 0, true));
                return;
            default:
                throw new C2620h("invalid type.");
        }
    }

    /* renamed from: a */
    public final boolean m1057a(int i, boolean z) {
        return m1048a((byte) 0, i, z) != 0;
    }

    /* renamed from: a */
    public final byte m1048a(byte b, int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return b;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b2 = aVar.f1460a;
        if (b2 == 0) {
            return this.f1459b.get();
        }
        if (b2 == 12) {
            return (byte) 0;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    public final short m1055a(short s, int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return s;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 0) {
            return this.f1459b.get();
        }
        if (b == 1) {
            return this.f1459b.getShort();
        }
        if (b == 12) {
            return (short) 0;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    public final int m1049a(int i, int i2, boolean z) {
        if (!m1040b(i2)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return i;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 0) {
            return this.f1459b.get();
        }
        if (b == 1) {
            return this.f1459b.getShort();
        }
        if (b == 2) {
            return this.f1459b.getInt();
        }
        if (b == 12) {
            return 0;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    public final long m1051a(long j, int i, boolean z) {
        int i2;
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return j;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 0) {
            i2 = this.f1459b.get();
        } else if (b == 1) {
            i2 = this.f1459b.getShort();
        } else {
            if (b != 2) {
                if (b == 3) {
                    return this.f1459b.getLong();
                }
                if (b == 12) {
                    return 0L;
                }
                throw new C2620h("type mismatch.");
            }
            i2 = this.f1459b.getInt();
        }
        return i2;
    }

    /* renamed from: a */
    private float m1030a(float f, int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return f;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 4) {
            return this.f1459b.getFloat();
        }
        if (b == 12) {
            return 0.0f;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    private double m1029a(double d, int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return d;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 4) {
            return this.f1459b.getFloat();
        }
        if (b == 5) {
            return this.f1459b.getDouble();
        }
        if (b == 12) {
            return 0.0d;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: b */
    public final String m1058b(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 6) {
            int i2 = this.f1459b.get();
            if (i2 < 0) {
                i2 += 256;
            }
            byte[] bArr = new byte[i2];
            this.f1459b.get(bArr);
            try {
                return new String(bArr, this.f1458a);
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        }
        if (b == 7) {
            int i3 = this.f1459b.getInt();
            if (i3 > 104857600 || i3 < 0) {
                throw new C2620h("String too long: ".concat(String.valueOf(i3)));
            }
            byte[] bArr2 = new byte[i3];
            this.f1459b.get(bArr2);
            try {
                return new String(bArr2, this.f1458a);
            } catch (UnsupportedEncodingException unused2) {
                return new String(bArr2);
            }
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    public final <K, V> HashMap<K, V> m1054a(Map<K, V> map, int i, boolean z) {
        return (HashMap) m1033a(new HashMap(), map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private <K, V> Map<K, V> m1033a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry<K, V> next = map2.entrySet().iterator().next();
        K key = next.getKey();
        V value = next.getValue();
        if (m1040b(i)) {
            a aVar = new a();
            m1037a(aVar);
            if (aVar.f1460a == 8) {
                int m1049a = m1049a(0, 0, true);
                if (m1049a < 0) {
                    throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
                }
                for (int i2 = 0; i2 < m1049a; i2++) {
                    map.put(m1053a((C2623k) key, 0, true), m1053a((C2623k) value, 1, true));
                }
            } else {
                throw new C2620h("type mismatch.");
            }
        } else if (z) {
            throw new C2620h("require field not exist.");
        }
        return map;
    }

    /* renamed from: d */
    private boolean[] m1042d(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            boolean[] zArr = new boolean[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                zArr[i2] = m1057a(0, true);
            }
            return zArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: c */
    public final byte[] m1059c(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        byte b = aVar.f1460a;
        if (b == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            byte[] bArr = new byte[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                bArr[i2] = m1048a(bArr[0], 0, true);
            }
            return bArr;
        }
        if (b == 13) {
            a aVar2 = new a();
            m1037a(aVar2);
            if (aVar2.f1460a != 0) {
                throw new C2620h("type mismatch, tag: " + i + ", type: " + ((int) aVar.f1460a) + ", " + ((int) aVar2.f1460a));
            }
            int m1049a2 = m1049a(0, 0, true);
            if (m1049a2 < 0) {
                throw new C2620h("invalid size, tag: " + i + ", type: " + ((int) aVar.f1460a) + ", " + ((int) aVar2.f1460a) + ", size: " + m1049a2);
            }
            byte[] bArr2 = new byte[m1049a2];
            this.f1459b.get(bArr2);
            return bArr2;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: e */
    private short[] m1043e(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            short[] sArr = new short[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                sArr[i2] = m1055a(sArr[0], 0, true);
            }
            return sArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: f */
    private int[] m1044f(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            int[] iArr = new int[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                iArr[i2] = m1049a(iArr[0], 0, true);
            }
            return iArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: g */
    private long[] m1045g(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            long[] jArr = new long[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                jArr[i2] = m1051a(jArr[0], 0, true);
            }
            return jArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: h */
    private float[] m1046h(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            float[] fArr = new float[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                fArr[i2] = m1030a(fArr[0], 0, true);
            }
            return fArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: i */
    private double[] m1047i(int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            double[] dArr = new double[m1049a];
            for (int i2 = 0; i2 < m1049a; i2++) {
                dArr[i2] = m1029a(dArr[0], 0, true);
            }
            return dArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    private <T> T[] m1038a(T[] tArr, int i, boolean z) {
        if (tArr == null || tArr.length == 0) {
            throw new C2620h("unable to get type of key and value.");
        }
        return (T[]) m1041b(tArr[0], i, z);
    }

    /* renamed from: a */
    private <T> List<T> m1032a(List<T> list, int i, boolean z) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        Object[] m1041b = m1041b(list.get(0), i, z);
        if (m1041b == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : m1041b) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    private <T> T[] m1041b(T t, int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m1037a(aVar);
        if (aVar.f1460a == 9) {
            int m1049a = m1049a(0, 0, true);
            if (m1049a < 0) {
                throw new C2620h("size invalid: ".concat(String.valueOf(m1049a)));
            }
            T[] tArr = (T[]) ((Object[]) Array.newInstance(t.getClass(), m1049a));
            for (int i2 = 0; i2 < m1049a; i2++) {
                tArr[i2] = m1053a((C2623k) t, 0, true);
            }
            return tArr;
        }
        throw new C2620h("type mismatch.");
    }

    /* renamed from: a */
    public final AbstractC2625m m1052a(AbstractC2625m abstractC2625m, int i, boolean z) {
        if (!m1040b(i)) {
            if (z) {
                throw new C2620h("require field not exist.");
            }
            return null;
        }
        try {
            AbstractC2625m abstractC2625m2 = (AbstractC2625m) abstractC2625m.getClass().newInstance();
            a aVar = new a();
            m1037a(aVar);
            if (aVar.f1460a != 10) {
                throw new C2620h("type mismatch.");
            }
            abstractC2625m2.mo993a(this);
            m1034a();
            return abstractC2625m2;
        } catch (Exception e) {
            throw new C2620h(e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public final <T> Object m1053a(T t, int i, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf(m1048a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(m1057a(i, z));
        }
        if (t instanceof Short) {
            return Short.valueOf(m1055a((short) 0, i, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(m1049a(0, i, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(m1051a(0L, i, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(m1030a(0.0f, i, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(m1029a(0.0d, i, z));
        }
        if (t instanceof String) {
            return String.valueOf(m1058b(i, z));
        }
        if (t instanceof Map) {
            return m1054a((Map) t, i, z);
        }
        if (t instanceof List) {
            return m1032a((List) t, i, z);
        }
        if (t instanceof AbstractC2625m) {
            return m1052a((AbstractC2625m) t, i, z);
        }
        if (t.getClass().isArray()) {
            if ((t instanceof byte[]) || (t instanceof Byte[])) {
                return m1059c(i, z);
            }
            if (t instanceof boolean[]) {
                return m1042d(i, z);
            }
            if (t instanceof short[]) {
                return m1043e(i, z);
            }
            if (t instanceof int[]) {
                return m1044f(i, z);
            }
            if (t instanceof long[]) {
                return m1045g(i, z);
            }
            if (t instanceof float[]) {
                return m1046h(i, z);
            }
            if (t instanceof double[]) {
                return m1047i(i, z);
            }
            return m1038a((Object[]) t, i, z);
        }
        throw new C2620h("read object error: unsupport type.");
    }

    /* renamed from: a */
    public final int m1050a(String str) {
        this.f1458a = str;
        return 0;
    }
}
