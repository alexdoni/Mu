package com.linecorp.linesdk.auth.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.linecorp.linesdk.BuildConfig;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
class LineAppVersion {
    private final int major;
    private final int minor;
    private final int revision;

    public static LineAppVersion getLineAppVersion(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(BuildConfig.LINE_APP_PACKAGE_NAME, 128).versionName;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            return new LineAppVersion(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        } catch (PackageManager.NameNotFoundException | NullPointerException | NumberFormatException unused) {
            return null;
        }
    }

    public LineAppVersion(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.revision = i3;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getRevision() {
        return this.revision;
    }

    public boolean isEqualOrGreaterThan(LineAppVersion lineAppVersion) {
        if (lineAppVersion == null) {
            return false;
        }
        int i = this.major;
        int i2 = lineAppVersion.major;
        if (i != i2) {
            return i >= i2;
        }
        int i3 = this.minor;
        int i4 = lineAppVersion.minor;
        return i3 != i4 ? i3 >= i4 : this.revision >= lineAppVersion.revision;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineAppVersion lineAppVersion = (LineAppVersion) obj;
        return this.major == lineAppVersion.major && this.minor == lineAppVersion.minor && this.revision == lineAppVersion.revision;
    }

    public int hashCode() {
        return (((this.major * 31) + this.minor) * 31) + this.revision;
    }
}
