package androidx.core.graphics;

import android.graphics.Rect;

/* loaded from: classes.dex */
public final class Insets {
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;

    /* renamed from: top, reason: collision with root package name */
    public final int f2909top;

    private Insets(int i, int i2, int i3, int i4) {
        this.left = i;
        this.f2909top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    /* renamed from: of */
    public static Insets m10of(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return NONE;
        }
        return new Insets(i, i2, i3, i4);
    }

    /* renamed from: of */
    public static Insets m11of(Rect rect) {
        return m10of(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static Insets add(Insets insets, Insets insets2) {
        return m10of(insets.left + insets2.left, insets.f2909top + insets2.f2909top, insets.right + insets2.right, insets.bottom + insets2.bottom);
    }

    public static Insets subtract(Insets insets, Insets insets2) {
        return m10of(insets.left - insets2.left, insets.f2909top - insets2.f2909top, insets.right - insets2.right, insets.bottom - insets2.bottom);
    }

    public static Insets max(Insets insets, Insets insets2) {
        return m10of(Math.max(insets.left, insets2.left), Math.max(insets.f2909top, insets2.f2909top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom));
    }

    public static Insets min(Insets insets, Insets insets2) {
        return m10of(Math.min(insets.left, insets2.left), Math.min(insets.f2909top, insets2.f2909top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.bottom == insets.bottom && this.left == insets.left && this.right == insets.right && this.f2909top == insets.f2909top;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.f2909top) * 31) + this.right) * 31) + this.bottom;
    }

    public String toString() {
        return "Insets{left=" + this.left + ", top=" + this.f2909top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
    }

    @Deprecated
    public static Insets wrap(android.graphics.Insets insets) {
        return toCompatInsets(insets);
    }

    public static Insets toCompatInsets(android.graphics.Insets insets) {
        return m10of(insets.left, insets.top, insets.right, insets.bottom);
    }

    public android.graphics.Insets toPlatformInsets() {
        return Api29Impl.m12of(this.left, this.f2909top, this.right, this.bottom);
    }

    /* loaded from: classes.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        /* renamed from: of */
        static android.graphics.Insets m12of(int i, int i2, int i3, int i4) {
            return android.graphics.Insets.of(i, i2, i3, i4);
        }
    }
}
