package kotlin;

import com.oversea.ab_firstarea.utils.UpdateHelper;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.spongycastle.asn1.cmc.BodyPartID;
import sun.misc.Unsafe;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes3.dex */
public final /* synthetic */ class UByte$$ExternalSyntheticBackport0 {
    /* renamed from: m */
    public static /* synthetic */ int m1403m(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    /* renamed from: m */
    public static /* synthetic */ int m1404m(int i, int i2) {
        return (int) ((i & BodyPartID.bodyIdMax) % (i2 & BodyPartID.bodyIdMax));
    }

    /* renamed from: m */
    public static /* synthetic */ int m1405m(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: m */
    public static /* synthetic */ int m1407m(boolean z) {
        if (z) {
            return UpdateHelper.UPDATE_REQUEST_CODE;
        }
        return 1237;
    }

    /* renamed from: m */
    public static /* synthetic */ long m1408m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if ((j3 ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE)) {
            j2 = 0;
        }
        return j3 - j2;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m1409m(Object obj) {
        return obj == null;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m1410m(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!atomicReference.compareAndSet(obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m1411m(AtomicReferenceArray atomicReferenceArray, int i, Object obj, Object obj2) {
        while (!atomicReferenceArray.compareAndSet(i, obj, obj2)) {
            if (atomicReferenceArray.get(i) != obj) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m1412m(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Object obj, Object obj2, Object obj3) {
        while (!atomicReferenceFieldUpdater.compareAndSet(obj, obj2, obj3)) {
            if (atomicReferenceFieldUpdater.get(obj) != obj2) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m1413m(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ int m$1(int i, int i2) {
        return (int) ((i & BodyPartID.bodyIdMax) / (i2 & BodyPartID.bodyIdMax));
    }

    public static /* synthetic */ long m$1(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1);
    }
}
