package com.google.ads.conversiontracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.google.ads.conversiontracking.C0885i;
import com.google.firebase.messaging.Constants;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlinx.coroutines.DebugKt;

/* renamed from: com.google.ads.conversiontracking.g */
/* loaded from: classes.dex */
public class C0883g {

    /* renamed from: a */
    private static final Map<String, String> f444a = new HashMap();

    /* renamed from: b */
    private static boolean f445b = false;

    /* renamed from: c */
    private static long f446c = -1;

    /* renamed from: d */
    private static boolean f447d = true;

    /* renamed from: e */
    private static boolean f448e = false;

    /* renamed from: f */
    private static final Object f449f = new Object();

    /* renamed from: g */
    private static C0881e f450g = null;

    /* renamed from: h */
    private static boolean f451h = false;

    /* renamed from: com.google.ads.conversiontracking.g$d */
    /* loaded from: classes.dex */
    public enum d {
        DOUBLECLICK_AUDIENCE,
        DOUBLECLICK_CONVERSION,
        GOOGLE_CONVERSION,
        IAP_CONVERSION
    }

    /* renamed from: a */
    public static C0881e m162a(Context context) {
        C0881e c0881e;
        synchronized (f449f) {
            if (f450g == null) {
                f450g = new C0881e(context);
            }
            c0881e = f450g;
        }
        return c0881e;
    }

    /* renamed from: a */
    public static boolean m181a(Context context, c cVar, boolean z) {
        return m182a(context, m170a(cVar), m184b(cVar), z);
    }

    /* renamed from: a */
    public static boolean m182a(Context context, String str, String str2, boolean z) {
        if (f445b && f448e) {
            return f447d;
        }
        if (z) {
            return true;
        }
        boolean z2 = context.getSharedPreferences(str, 0).getBoolean(str2, false);
        if (z2) {
            String valueOf = String.valueOf(str2);
            Log.i("GoogleConversionReporter", valueOf.length() != 0 ? "Already sent ping for conversion ".concat(valueOf) : new String("Already sent ping for conversion "));
        }
        return !z2;
    }

    /* renamed from: b */
    public static long m183b(Context context) {
        return context.getSharedPreferences("google_conversion", 0).getLong("last_retry_time", 0L);
    }

    /* renamed from: a */
    public static void m176a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, true);
        edit.commit();
    }

    /* renamed from: c */
    public static void m187c(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("google_conversion", 0).edit();
        edit.putLong("last_retry_time", m161a());
        edit.commit();
    }

    /* renamed from: a */
    public static String m167a(Context context, c cVar) throws NoSuchAlgorithmException {
        return m168a(context, cVar, new C0877a(context).m125a());
    }

    /* renamed from: a */
    public static String m168a(Context context, c cVar, C0885i.a aVar) throws NoSuchAlgorithmException {
        String str;
        String packageName = context.getPackageName();
        try {
            str = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GoogleConversionReporter", "Error to retrieve app version", e);
            str = "";
        }
        String m189e = aVar == null ? m189e(context) : null;
        if (!cVar.f464c && cVar.f465d == d.DOUBLECLICK_CONVERSION) {
            return m172a(cVar, packageName, str, aVar, m189e);
        }
        if (cVar.f465d == d.DOUBLECLICK_AUDIENCE) {
            return m171a(cVar, aVar);
        }
        if (cVar.f465d == d.IAP_CONVERSION) {
            return m186c(cVar, packageName, str, aVar, m189e);
        }
        return m185b(cVar, packageName, str, aVar, m189e);
    }

    /* renamed from: a */
    private static void m178a(Uri.Builder builder, boolean z, Map<String, ?> map) {
        if (!z || map == null) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                String valueOf = String.valueOf(entry.getKey());
                builder.appendQueryParameter(valueOf.length() != 0 ? "data.".concat(valueOf) : new String("data."), (String) entry.getValue());
            } else if (entry.getValue() instanceof String[]) {
                for (String str : (String[]) entry.getValue()) {
                    String valueOf2 = String.valueOf(entry.getKey());
                    builder.appendQueryParameter(valueOf2.length() != 0 ? "data.".concat(valueOf2) : new String("data."), str);
                }
            }
        }
    }

    /* renamed from: a */
    public static b m164a(Uri uri) {
        if (uri == null) {
            return null;
        }
        String queryParameter = uri.getQueryParameter("referrer");
        if (TextUtils.isEmpty(queryParameter)) {
            return null;
        }
        String valueOf = String.valueOf(queryParameter);
        Uri parse = Uri.parse(valueOf.length() != 0 ? "http://hostname/?".concat(valueOf) : new String("http://hostname/?"));
        String queryParameter2 = parse.getQueryParameter("conv");
        String queryParameter3 = parse.getQueryParameter("gclid");
        if (TextUtils.isEmpty(queryParameter2) || TextUtils.isEmpty(queryParameter3)) {
            return null;
        }
        String queryParameter4 = parse.getQueryParameter("ai");
        if (queryParameter4 == null) {
            queryParameter4 = "";
        }
        return new b(queryParameter2, new a(queryParameter3, queryParameter4));
    }

    /* renamed from: a */
    public static String m169a(a aVar) {
        if (aVar == null) {
            return "";
        }
        if (TextUtils.isEmpty(aVar.f458b)) {
            String valueOf = String.valueOf(aVar.f457a);
            return valueOf.length() != 0 ? "&gclid=".concat(valueOf) : new String("&gclid=");
        }
        String str = aVar.f457a;
        String str2 = aVar.f458b;
        StringBuilder sb = new StringBuilder("&gclid=".length() + 2 + String.valueOf(str).length() + "ai".length() + String.valueOf(str2).length());
        sb.append("&gclid=");
        sb.append(str);
        sb.append("&ai=");
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: a */
    private static List<String> m175a(SharedPreferences sharedPreferences) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            if (a.m190a((String) entry.getValue()) == null) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static boolean m180a(Context context, final b bVar) {
        if (bVar == null) {
            return false;
        }
        final SharedPreferences sharedPreferences = context.getSharedPreferences("google_conversion_click_referrer", 0);
        final List<String> m175a = m175a(sharedPreferences);
        if (sharedPreferences.getString(bVar.f460a, null) == null && sharedPreferences.getAll().size() == 100 && m175a.isEmpty()) {
            return false;
        }
        String str = bVar.f461b.f457a;
        String str2 = bVar.f461b.f458b;
        long j = bVar.f461b.f459c;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20 + " ".length() + String.valueOf(str2).length() + " ".length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(" ");
        sb.append(j);
        final String sb2 = sb.toString();
        synchronized (f444a) {
            Iterator<String> it = m175a.iterator();
            while (it.hasNext()) {
                f444a.remove(it.next());
            }
            f444a.put(bVar.f460a, sb2);
        }
        new Thread(new Runnable() { // from class: com.google.ads.conversiontracking.g.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                Iterator it2 = m175a.iterator();
                while (it2.hasNext()) {
                    edit.remove((String) it2.next());
                }
                edit.putString(bVar.f460a, sb2);
                edit.commit();
            }
        }).start();
        return true;
    }

    /* renamed from: a */
    public static a m163a(Context context, String str) {
        String str2;
        Map<String, String> map = f444a;
        synchronized (map) {
            str2 = map.get(str);
        }
        if (str2 == null) {
            str2 = context.getSharedPreferences("google_conversion_click_referrer", 0).getString(str, "");
        }
        return a.m190a(str2);
    }

    /* renamed from: a */
    static String m166a(long j) {
        return String.format(Locale.US, "%d.%03d", Long.valueOf(j / 1000), Long.valueOf(j % 1000));
    }

    /* renamed from: a */
    private static String m173a(C0885i.a aVar) {
        if (aVar == null) {
            return null;
        }
        return aVar.m226b() ? "1" : "0";
    }

    /* renamed from: a */
    private static void m179a(StringBuilder sb, C0885i.a aVar, String str) {
        String m173a = m173a(aVar);
        if (m173a != null) {
            String valueOf = String.valueOf(m173a);
            sb.append(valueOf.length() != 0 ? ";dc_lat=".concat(valueOf) : new String(";dc_lat="));
        }
        if (aVar == null) {
            String valueOf2 = String.valueOf(str);
            sb.append(valueOf2.length() != 0 ? ";isu=".concat(valueOf2) : new String(";isu="));
        } else {
            String valueOf3 = String.valueOf(aVar.m225a());
            sb.append(valueOf3.length() != 0 ? ";dc_rdid=".concat(valueOf3) : new String(";dc_rdid="));
        }
    }

    /* renamed from: a */
    private static void m177a(Uri.Builder builder, C0885i.a aVar, String str) {
        if (m173a(aVar) != null) {
            builder.appendQueryParameter("lat", m173a(aVar));
        }
        if (aVar != null) {
            builder.appendQueryParameter("rdid", aVar.m225a());
        } else {
            builder.appendQueryParameter("muid", str);
        }
    }

    /* renamed from: a */
    public static String m172a(c cVar, String str, String str2, C0885i.a aVar, String str3) {
        String str4 = cVar.f462a;
        String valueOf = String.valueOf(Build.VERSION.RELEASE);
        String m166a = m166a(m161a());
        StringBuilder sb = new StringBuilder("https://pubads.g.doubleclick.net/activity;xsp=".length() + 13 + String.valueOf(str4).length() + "ait".length() + "bundleid".length() + String.valueOf(str).length() + "appversion".length() + String.valueOf(str2).length() + "osversion".length() + String.valueOf(valueOf).length() + "sdkversion".length() + "ct-sdk-a-v2.2.4".length() + "timestamp".length() + String.valueOf(m166a).length());
        sb.append("https://pubads.g.doubleclick.net/activity;xsp=");
        sb.append(str4);
        sb.append(";ait=1;bundleid=");
        sb.append(str);
        sb.append(";appversion=");
        sb.append(str2);
        sb.append(";osversion=");
        sb.append(valueOf);
        sb.append(";sdkversion=ct-sdk-a-v2.2.4;timestamp=");
        sb.append(m166a);
        StringBuilder sb2 = new StringBuilder(sb.toString());
        m179a(sb2, aVar, str3);
        return sb2.toString();
    }

    /* renamed from: a */
    public static String m171a(c cVar, C0885i.a aVar) {
        if (aVar == null) {
            return null;
        }
        String valueOf = String.valueOf(cVar.f467f);
        StringBuilder sb = new StringBuilder(valueOf.length() != 0 ? "https://pubads.g.doubleclick.net/activity;dc_iu=".concat(valueOf) : new String("https://pubads.g.doubleclick.net/activity;dc_iu="));
        m179a(sb, aVar, (String) null);
        if (cVar.f470i != null) {
            for (Map.Entry entry : cVar.f470i.entrySet()) {
                String encode = Uri.encode((String) entry.getKey());
                String encode2 = Uri.encode(entry.getValue().toString());
                StringBuilder sb2 = new StringBuilder(String.valueOf(encode).length() + 2 + String.valueOf(encode2).length());
                sb2.append(";");
                sb2.append(encode);
                sb2.append("=");
                sb2.append(encode2);
                sb.append(sb2.toString());
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m185b(c cVar, String str, String str2, C0885i.a aVar, String str3) {
        String m169a = m169a(cVar.f469h);
        Uri.Builder appendQueryParameter = Uri.parse("https://www.googleadservices.com/pagead/conversion/").buildUpon().appendEncodedPath(String.valueOf(cVar.f462a).concat(RemoteSettings.FORWARD_SLASH_STRING)).appendQueryParameter("bundleid", str).appendQueryParameter("appversion", str2).appendQueryParameter("osversion", Build.VERSION.RELEASE).appendQueryParameter("sdkversion", "ct-sdk-a-v2.2.4").appendQueryParameter("gms", aVar != null ? "1" : "0");
        m177a(appendQueryParameter, aVar, str3);
        if (cVar.f466e != null && cVar.f467f != null) {
            appendQueryParameter.appendQueryParameter(Constants.ScionAnalytics.PARAM_LABEL, cVar.f466e).appendQueryParameter("value", cVar.f467f);
        }
        if (cVar.f472k != 0) {
            appendQueryParameter.appendQueryParameter("timestamp", m166a(cVar.f472k));
        } else {
            appendQueryParameter.appendQueryParameter("timestamp", m166a(m161a()));
        }
        if (cVar.f464c) {
            appendQueryParameter.appendQueryParameter("remarketing_only", "1");
        }
        if (cVar.f473l) {
            appendQueryParameter.appendQueryParameter(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "1");
        }
        if (cVar.f463b) {
            appendQueryParameter.appendQueryParameter("usage_tracking_enabled", "1");
        } else {
            appendQueryParameter.appendQueryParameter("usage_tracking_enabled", "0");
        }
        if (cVar.f468g != null) {
            appendQueryParameter.appendQueryParameter("currency_code", cVar.f468g);
        }
        m178a(appendQueryParameter, cVar.f464c, (Map<String, ?>) cVar.f470i);
        String valueOf = String.valueOf(appendQueryParameter.build());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 0 + String.valueOf(m169a).length());
        sb.append(valueOf);
        sb.append(m169a);
        return sb.toString();
    }

    /* renamed from: c */
    public static String m186c(c cVar, String str, String str2, C0885i.a aVar, String str3) {
        Uri.Builder appendQueryParameter = Uri.parse("https://www.googleadservices.com/pagead/conversion/").buildUpon().appendQueryParameter("sku", cVar.f471j).appendQueryParameter("value", cVar.f467f).appendQueryParameter("bundleid", str).appendQueryParameter("appversion", str2).appendQueryParameter("osversion", Build.VERSION.RELEASE).appendQueryParameter("sdkversion", "ct-sdk-a-v2.2.4").appendQueryParameter("timestamp", m166a(m161a()));
        m177a(appendQueryParameter, aVar, str3);
        return appendQueryParameter.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.ads.conversiontracking.g$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a */
        static final /* synthetic */ int[] f456a;

        static {
            int[] iArr = new int[d.values().length];
            f456a = iArr;
            try {
                iArr[d.DOUBLECLICK_CONVERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f456a[d.IAP_CONVERSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f456a[d.GOOGLE_CONVERSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: a */
    public static String m170a(c cVar) {
        int i = AnonymousClass2.f456a[cVar.f465d.ordinal()];
        return i != 1 ? i != 2 ? "google_nonrepeatable_conversion" : "iap_nonrepeatable_conversion" : "doubleclick_nonrepeatable_conversion";
    }

    /* renamed from: b */
    public static String m184b(c cVar) {
        int i = AnonymousClass2.f456a[cVar.f465d.ordinal()];
        if (i != 1) {
            return i != 2 ? cVar.f466e : String.format("google_iap_ping:%s", cVar.f471j);
        }
        return cVar.f462a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static long m161a() {
        if (f445b) {
            long j = f446c;
            if (j >= 0) {
                return j;
            }
        }
        return System.currentTimeMillis();
    }

    /* renamed from: e */
    private static String m189e(Context context) throws NoSuchAlgorithmException {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = JsonSerializer.Null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(string.getBytes());
        return C0894s.m251a(messageDigest.digest(), false);
    }

    /* renamed from: d */
    public static boolean m188d(Context context) {
        if (f445b) {
            return f451h;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* renamed from: a */
    public static String m174a(String str) {
        if (((String) m165a(str)).length() != 0) {
            return str;
        }
        throw new IllegalStateException("Parameter cannot be empty string");
    }

    /* renamed from: a */
    public static <T> T m165a(T t) {
        t.getClass();
        return t;
    }

    /* renamed from: com.google.ads.conversiontracking.g$b */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a */
        private final String f460a;

        /* renamed from: b */
        private final a f461b;

        public b(String str, a aVar) {
            this.f460a = str;
            this.f461b = aVar;
        }
    }

    /* renamed from: com.google.ads.conversiontracking.g$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        private final String f457a;

        /* renamed from: b */
        private final String f458b;

        /* renamed from: c */
        private final long f459c;

        private a(String str, String str2, long j) {
            this.f457a = str;
            this.f458b = str2;
            this.f459c = j;
        }

        public a(String str, String str2) {
            this(str, str2, C0883g.m161a());
        }

        /* renamed from: a */
        public boolean m194a() {
            return this.f459c + 7776000000L < C0883g.m161a();
        }

        /* renamed from: a */
        public static a m190a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String[] split = str.split(" ");
            if (split.length != 3) {
                return null;
            }
            try {
                a aVar = new a(split[0], split[1], Long.parseLong(split[2]));
                if (aVar.m194a()) {
                    return null;
                }
                return aVar;
            } catch (NumberFormatException unused) {
                return null;
            }
        }
    }

    /* renamed from: com.google.ads.conversiontracking.g$c */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a */
        private String f462a;

        /* renamed from: b */
        private boolean f463b;

        /* renamed from: c */
        private boolean f464c;

        /* renamed from: d */
        private d f465d;

        /* renamed from: e */
        private String f466e;

        /* renamed from: f */
        private String f467f;

        /* renamed from: g */
        private String f468g;

        /* renamed from: h */
        private a f469h;

        /* renamed from: i */
        private Map<String, ?> f470i;

        /* renamed from: j */
        private String f471j;

        /* renamed from: k */
        private long f472k;

        /* renamed from: l */
        private boolean f473l;

        /* renamed from: a */
        public c m213a(String str) {
            this.f462a = str;
            return this;
        }

        /* renamed from: a */
        public c m209a() {
            this.f464c = true;
            return this;
        }

        /* renamed from: a */
        public c m212a(d dVar) {
            this.f465d = dVar;
            return this;
        }

        /* renamed from: b */
        public c m217b(String str) {
            this.f466e = str;
            return this;
        }

        /* renamed from: c */
        public c m218c(String str) {
            this.f467f = str;
            return this;
        }

        /* renamed from: d */
        public c m219d(String str) {
            this.f468g = str;
            return this;
        }

        /* renamed from: a */
        public c m211a(a aVar) {
            this.f469h = aVar;
            return this;
        }

        /* renamed from: a */
        public c m214a(Map<String, ?> map) {
            this.f470i = map;
            return this;
        }

        /* renamed from: e */
        public c m220e(String str) {
            this.f471j = str;
            return this;
        }

        /* renamed from: a */
        public c m215a(boolean z) {
            this.f463b = z;
            return this;
        }

        /* renamed from: a */
        public c m210a(long j) {
            this.f472k = TimeUnit.MILLISECONDS.toSeconds(j);
            return this;
        }

        /* renamed from: b */
        public c m216b() {
            this.f473l = true;
            return this;
        }
    }
}
