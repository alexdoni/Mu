package androidx.core.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.List;

/* loaded from: classes.dex */
public interface LocationListenerCompat extends LocationListener {
    @Override // android.location.LocationListener
    void onFlushComplete(int i);

    @Override // android.location.LocationListener
    void onLocationChanged(List<Location> list);

    @Override // android.location.LocationListener
    void onProviderDisabled(String str);

    @Override // android.location.LocationListener
    void onProviderEnabled(String str);

    @Override // android.location.LocationListener
    void onStatusChanged(String str, int i, Bundle bundle);

    @SynthesizedClassV2(kind = 8, versionHash = "7a5b85d3ee2e0991ca3502602e9389a98f55c0576b887125894a7ec03823f8d3")
    /* renamed from: androidx.core.location.LocationListenerCompat$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static void $default$onFlushComplete(LocationListenerCompat _this, int i) {
        }

        public static void $default$onProviderDisabled(LocationListenerCompat _this, String str) {
        }

        public static void $default$onProviderEnabled(LocationListenerCompat _this, String str) {
        }

        public static void $default$onStatusChanged(LocationListenerCompat _this, String str, int i, Bundle bundle) {
        }

        public static void $default$onLocationChanged(LocationListenerCompat _this, List list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                _this.onLocationChanged((Location) list.get(i));
            }
        }
    }
}
