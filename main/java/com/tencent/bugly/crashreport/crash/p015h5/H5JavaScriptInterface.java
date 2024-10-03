package com.tencent.bugly.crashreport.crash.p015h5;

import android.webkit.JavascriptInterface;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareInternalUtility;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.C2577al;
import com.tencent.bugly.proguard.C2581ap;
import com.tencent.bugly.proguard.C2594bb;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class H5JavaScriptInterface {

    /* renamed from: a */
    private static HashSet<Integer> f953a = new HashSet<>();

    /* renamed from: b */
    private String f954b = null;

    /* renamed from: c */
    private Thread f955c = null;

    /* renamed from: d */
    private String f956d = null;

    /* renamed from: e */
    private Map<String, String> f957e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.InterfaceC2554a interfaceC2554a) {
        String str = null;
        if (interfaceC2554a == null || f953a.contains(Integer.valueOf(interfaceC2554a.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f953a.add(Integer.valueOf(interfaceC2554a.hashCode()));
        Thread currentThread = Thread.currentThread();
        h5JavaScriptInterface.f955c = currentThread;
        if (currentThread != null) {
            StringBuilder sb = new StringBuilder("\n");
            for (int i = 2; i < currentThread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = currentThread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
            str = sb.toString();
        }
        h5JavaScriptInterface.f956d = str;
        HashMap hashMap = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) interfaceC2554a.mo619c());
        hashMap.put("[WebView] ContentDescription", sb2.toString());
        h5JavaScriptInterface.f957e = hashMap;
        return h5JavaScriptInterface;
    }

    /* renamed from: a */
    private static C2594bb m626a(String str) {
        String string;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                C2594bb c2594bb = new C2594bb();
                c2594bb.f1283a = jSONObject.getString("projectRoot");
                if (c2594bb.f1283a == null) {
                    return null;
                }
                c2594bb.f1284b = jSONObject.getString("context");
                if (c2594bb.f1284b == null) {
                    return null;
                }
                c2594bb.f1285c = jSONObject.getString("url");
                if (c2594bb.f1285c == null) {
                    return null;
                }
                c2594bb.f1286d = jSONObject.getString("userAgent");
                if (c2594bb.f1286d == null) {
                    return null;
                }
                c2594bb.f1287e = jSONObject.getString("language");
                if (c2594bb.f1287e == null) {
                    return null;
                }
                c2594bb.f1288f = jSONObject.getString("name");
                if (c2594bb.f1288f == null || c2594bb.f1288f.equals(JsonSerializer.Null) || (string = jSONObject.getString("stacktrace")) == null) {
                    return null;
                }
                int indexOf = string.indexOf("\n");
                if (indexOf < 0) {
                    C2577al.m786d("H5 crash stack's format is wrong!", new Object[0]);
                    return null;
                }
                c2594bb.f1290h = string.substring(indexOf + 1);
                c2594bb.f1289g = string.substring(0, indexOf);
                int indexOf2 = c2594bb.f1289g.indexOf(CertificateUtil.DELIMITER);
                if (indexOf2 > 0) {
                    c2594bb.f1289g = c2594bb.f1289g.substring(indexOf2 + 1);
                }
                c2594bb.f1291i = jSONObject.getString(ShareInternalUtility.STAGING_PARAM);
                if (c2594bb.f1288f == null) {
                    return null;
                }
                c2594bb.f1292j = jSONObject.getLong("lineNumber");
                if (c2594bb.f1292j < 0) {
                    return null;
                }
                c2594bb.f1293k = jSONObject.getLong("columnNumber");
                if (c2594bb.f1293k < 0) {
                    return null;
                }
                C2577al.m781a("H5 crash information is following: ", new Object[0]);
                C2577al.m781a("[projectRoot]: " + c2594bb.f1283a, new Object[0]);
                C2577al.m781a("[context]: " + c2594bb.f1284b, new Object[0]);
                C2577al.m781a("[url]: " + c2594bb.f1285c, new Object[0]);
                C2577al.m781a("[userAgent]: " + c2594bb.f1286d, new Object[0]);
                C2577al.m781a("[language]: " + c2594bb.f1287e, new Object[0]);
                C2577al.m781a("[name]: " + c2594bb.f1288f, new Object[0]);
                C2577al.m781a("[message]: " + c2594bb.f1289g, new Object[0]);
                C2577al.m781a("[stacktrace]: \n" + c2594bb.f1290h, new Object[0]);
                C2577al.m781a("[file]: " + c2594bb.f1291i, new Object[0]);
                C2577al.m781a("[lineNumber]: " + c2594bb.f1292j, new Object[0]);
                C2577al.m781a("[columnNumber]: " + c2594bb.f1293k, new Object[0]);
                return c2594bb;
            } catch (Throwable th) {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        C2577al.m786d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            C2577al.m786d("Payload from JS is null.", new Object[0]);
            return;
        }
        String m846c = C2581ap.m846c(str.getBytes());
        String str2 = this.f954b;
        if (str2 != null && str2.equals(m846c)) {
            C2577al.m786d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.f954b = m846c;
        C2577al.m786d("Handling JS exception ...", new Object[0]);
        C2594bb m626a = m626a(str);
        if (m626a == null) {
            C2577al.m786d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (m626a.f1283a != null) {
            linkedHashMap2.put("[JS] projectRoot", m626a.f1283a);
        }
        if (m626a.f1284b != null) {
            linkedHashMap2.put("[JS] context", m626a.f1284b);
        }
        if (m626a.f1285c != null) {
            linkedHashMap2.put("[JS] url", m626a.f1285c);
        }
        if (m626a.f1286d != null) {
            linkedHashMap2.put("[JS] userAgent", m626a.f1286d);
        }
        if (m626a.f1291i != null) {
            linkedHashMap2.put("[JS] file", m626a.f1291i);
        }
        if (m626a.f1292j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(m626a.f1292j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.f957e);
        linkedHashMap.put("Java Stack", this.f956d);
        Thread thread = this.f955c;
        if (m626a != null) {
            InnerApi.postH5CrashAsync(thread, m626a.f1288f, m626a.f1289g, m626a.f1290h, linkedHashMap);
        }
    }
}
