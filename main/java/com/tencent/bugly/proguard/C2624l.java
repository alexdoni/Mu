package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.l */
/* loaded from: classes3.dex */
public final class C2624l {

    /* renamed from: a */
    public ByteBuffer f1462a;

    /* renamed from: b */
    protected String f1463b;

    public C2624l(int i) {
        this.f1463b = "GBK";
        this.f1462a = ByteBuffer.allocate(i);
    }

    public C2624l() {
        this(128);
    }

    /* renamed from: a */
    private void m1062a(int i) {
        if (this.f1462a.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.f1462a.capacity() + i) * 2);
            allocate.put(this.f1462a.array(), 0, this.f1462a.position());
            this.f1462a = allocate;
        }
    }

    /* renamed from: b */
    private void m1070b(byte b, int i) {
        if (i < 15) {
            this.f1462a.put((byte) (b | (i << 4)));
        } else {
            if (i < 256) {
                this.f1462a.put((byte) (b | 240));
                this.f1462a.put((byte) i);
                return;
            }
            throw new C2622j("tag is too large: ".concat(String.valueOf(i)));
        }
    }

    /* renamed from: a */
    public final void m1081a(boolean z, int i) {
        m1072a(z ? (byte) 1 : (byte) 0, i);
    }

    /* renamed from: a */
    public final void m1072a(byte b, int i) {
        m1062a(3);
        if (b == 0) {
            m1070b(Ascii.f548FF, i);
        } else {
            m1070b((byte) 0, i);
            this.f1462a.put(b);
        }
    }

    /* renamed from: a */
    public final void m1080a(short s, int i) {
        m1062a(4);
        if (s >= -128 && s <= 127) {
            m1072a((byte) s, i);
        } else {
            m1070b((byte) 1, i);
            this.f1462a.putShort(s);
        }
    }

    /* renamed from: a */
    public final void m1073a(int i, int i2) {
        m1062a(6);
        if (i >= -32768 && i <= 32767) {
            m1080a((short) i, i2);
        } else {
            m1070b((byte) 2, i2);
            this.f1462a.putInt(i);
        }
    }

    /* renamed from: a */
    public final void m1074a(long j, int i) {
        m1062a(10);
        if (j >= -2147483648L && j <= 2147483647L) {
            m1073a((int) j, i);
        } else {
            m1070b((byte) 3, i);
            this.f1462a.putLong(j);
        }
    }

    /* renamed from: a */
    private void m1061a(float f, int i) {
        m1062a(6);
        m1070b((byte) 4, i);
        this.f1462a.putFloat(f);
    }

    /* renamed from: a */
    private void m1060a(double d, int i) {
        m1062a(10);
        m1070b((byte) 5, i);
        this.f1462a.putDouble(d);
    }

    /* renamed from: a */
    public final void m1077a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f1463b);
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        m1062a(bytes.length + 10);
        if (bytes.length > 255) {
            m1070b((byte) 7, i);
            this.f1462a.putInt(bytes.length);
            this.f1462a.put(bytes);
        } else {
            m1070b((byte) 6, i);
            this.f1462a.put((byte) bytes.length);
            this.f1462a.put(bytes);
        }
    }

    /* renamed from: a */
    public final <K, V> void m1079a(Map<K, V> map, int i) {
        m1062a(8);
        m1070b((byte) 8, i);
        m1073a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                m1076a(entry.getKey(), 0);
                m1076a(entry.getValue(), 1);
            }
        }
    }

    /* renamed from: a */
    private void m1069a(boolean[] zArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(zArr.length, 0);
        for (boolean z : zArr) {
            m1081a(z, 0);
        }
    }

    /* renamed from: a */
    public final void m1082a(byte[] bArr, int i) {
        m1062a(bArr.length + 8);
        m1070b(Ascii.f546CR, i);
        m1070b((byte) 0, 0);
        m1073a(bArr.length, 0);
        this.f1462a.put(bArr);
    }

    /* renamed from: a */
    private void m1068a(short[] sArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(sArr.length, 0);
        for (short s : sArr) {
            m1080a(s, 0);
        }
    }

    /* renamed from: a */
    private void m1065a(int[] iArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(iArr.length, 0);
        for (int i2 : iArr) {
            m1073a(i2, 0);
        }
    }

    /* renamed from: a */
    private void m1066a(long[] jArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(jArr.length, 0);
        for (long j : jArr) {
            m1074a(j, 0);
        }
    }

    /* renamed from: a */
    private void m1064a(float[] fArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(fArr.length, 0);
        for (float f : fArr) {
            m1061a(f, 0);
        }
    }

    /* renamed from: a */
    private void m1063a(double[] dArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(dArr.length, 0);
        for (double d : dArr) {
            m1060a(d, 0);
        }
    }

    /* renamed from: a */
    private void m1067a(Object[] objArr, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(objArr.length, 0);
        for (Object obj : objArr) {
            m1076a(obj, 0);
        }
    }

    /* renamed from: a */
    public final <T> void m1078a(Collection<T> collection, int i) {
        m1062a(8);
        m1070b((byte) 9, i);
        m1073a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                m1076a(it.next(), 0);
            }
        }
    }

    /* renamed from: a */
    public final void m1075a(AbstractC2625m abstractC2625m, int i) {
        m1062a(2);
        m1070b((byte) 10, i);
        abstractC2625m.mo994a(this);
        m1062a(2);
        m1070b((byte) 11, 0);
    }

    /* renamed from: a */
    public final void m1076a(Object obj, int i) {
        if (obj instanceof Byte) {
            m1072a(((Byte) obj).byteValue(), i);
            return;
        }
        if (obj instanceof Boolean) {
            m1081a(((Boolean) obj).booleanValue(), i);
            return;
        }
        if (obj instanceof Short) {
            m1080a(((Short) obj).shortValue(), i);
            return;
        }
        if (obj instanceof Integer) {
            m1073a(((Integer) obj).intValue(), i);
            return;
        }
        if (obj instanceof Long) {
            m1074a(((Long) obj).longValue(), i);
            return;
        }
        if (obj instanceof Float) {
            m1061a(((Float) obj).floatValue(), i);
            return;
        }
        if (obj instanceof Double) {
            m1060a(((Double) obj).doubleValue(), i);
            return;
        }
        if (obj instanceof String) {
            m1077a((String) obj, i);
            return;
        }
        if (obj instanceof Map) {
            m1079a((Map) obj, i);
            return;
        }
        if (obj instanceof List) {
            m1078a((Collection) obj, i);
            return;
        }
        if (obj instanceof AbstractC2625m) {
            m1075a((AbstractC2625m) obj, i);
            return;
        }
        if (obj instanceof byte[]) {
            m1082a((byte[]) obj, i);
            return;
        }
        if (obj instanceof boolean[]) {
            m1069a((boolean[]) obj, i);
            return;
        }
        if (obj instanceof short[]) {
            m1068a((short[]) obj, i);
            return;
        }
        if (obj instanceof int[]) {
            m1065a((int[]) obj, i);
            return;
        }
        if (obj instanceof long[]) {
            m1066a((long[]) obj, i);
            return;
        }
        if (obj instanceof float[]) {
            m1064a((float[]) obj, i);
            return;
        }
        if (obj instanceof double[]) {
            m1063a((double[]) obj, i);
            return;
        }
        if (obj.getClass().isArray()) {
            m1067a((Object[]) obj, i);
        } else if (obj instanceof Collection) {
            m1078a((Collection) obj, i);
        } else {
            throw new C2622j("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* renamed from: a */
    public final int m1071a(String str) {
        this.f1463b = str;
        return 0;
    }
}
