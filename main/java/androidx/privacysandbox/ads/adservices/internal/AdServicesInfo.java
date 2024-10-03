package androidx.privacysandbox.ads.adservices.internal;

import android.os.Build;
import android.os.ext.SdkExtensions;
import com.facebook.internal.ServerProtocol;
import kotlin.Metadata;

/* compiled from: AdServicesInfo.kt */
@Metadata(m1394d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, m1395d2 = {"Landroidx/privacysandbox/ads/adservices/internal/AdServicesInfo;", "", "()V", ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "", "Extensions30Impl", "ads-adservices_release"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AdServicesInfo {
    public static final AdServicesInfo INSTANCE = new AdServicesInfo();

    private AdServicesInfo() {
    }

    public final int version() {
        if (Build.VERSION.SDK_INT >= 30) {
            return Extensions30Impl.INSTANCE.getAdServicesVersion();
        }
        return 0;
    }

    /* compiled from: AdServicesInfo.kt */
    @Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, m1395d2 = {"Landroidx/privacysandbox/ads/adservices/internal/AdServicesInfo$Extensions30Impl;", "", "()V", "getAdServicesVersion", "", "ads-adservices_release"}, m1396k = 1, m1397mv = {1, 8, 0}, m1399xi = 48)
    /* loaded from: classes.dex */
    private static final class Extensions30Impl {
        public static final Extensions30Impl INSTANCE = new Extensions30Impl();

        private Extensions30Impl() {
        }

        public final int getAdServicesVersion() {
            return SdkExtensions.getExtensionVersion(1000000);
        }
    }
}
