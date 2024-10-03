package com.appsflyer.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFh1gSDK {
    public final AFh1fSDK AFInAppEventType;
    public final AFh1nSDK AFKeystoreWrapper;
    public AFh1iSDK valueOf;

    public AFh1gSDK(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "");
        this.valueOf = values(jSONObject);
        this.AFKeystoreWrapper = AFInAppEventParameterName(jSONObject);
        this.AFInAppEventType = AFKeystoreWrapper(jSONObject);
    }

    private static AFh1fSDK AFKeystoreWrapper(JSONObject jSONObject) {
        Object m1882constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            JSONObject AFKeystoreWrapper = AFKeystoreWrapper(jSONObject, "meta_data");
            m1882constructorimpl = Result.m1882constructorimpl(AFKeystoreWrapper != null ? new AFh1fSDK(AFKeystoreWrapper.optDouble("send_rate", 1.0d)) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        return (AFh1fSDK) (Result.m1888isFailureimpl(m1882constructorimpl) ? null : m1882constructorimpl);
    }

    private static AFh1nSDK AFInAppEventParameterName(JSONObject jSONObject) {
        Object m1882constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            JSONObject AFKeystoreWrapper = AFKeystoreWrapper(jSONObject, "exc_mngr");
            m1882constructorimpl = Result.m1882constructorimpl(AFKeystoreWrapper != null ? new AFh1nSDK(AFKeystoreWrapper.getString("sdk_ver"), AFKeystoreWrapper.optInt("min", -1), AFKeystoreWrapper.optInt("expire", -1), AFKeystoreWrapper.optLong("ttl", -1L)) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        return (AFh1nSDK) (Result.m1888isFailureimpl(m1882constructorimpl) ? null : m1882constructorimpl);
    }

    private static AFh1iSDK values(JSONObject jSONObject) {
        Object m1882constructorimpl;
        AFh1iSDK aFh1iSDK;
        List emptyList;
        try {
            Result.Companion companion = Result.INSTANCE;
            JSONObject AFKeystoreWrapper = AFKeystoreWrapper(jSONObject, "r_debugger");
            if (AFKeystoreWrapper != null) {
                long j = AFKeystoreWrapper.getLong("ttl");
                int i = AFKeystoreWrapper.getInt("counter");
                String optString = AFKeystoreWrapper.optString("app_ver", "");
                String optString2 = AFKeystoreWrapper.optString("sdk_ver", "");
                float optDouble = (float) AFKeystoreWrapper.optDouble("ratio", 1.0d);
                JSONArray optJSONArray = AFKeystoreWrapper.optJSONArray("tags");
                if (optJSONArray == null) {
                    emptyList = CollectionsKt.emptyList();
                } else {
                    Intrinsics.checkNotNullExpressionValue(optJSONArray, "");
                    ArrayList arrayList = new ArrayList();
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        String string = optJSONArray.getString(i2);
                        Intrinsics.checkNotNullExpressionValue(string, "");
                        arrayList.add(string);
                    }
                    emptyList = arrayList;
                }
                Intrinsics.checkNotNullExpressionValue(optString, "");
                Intrinsics.checkNotNullExpressionValue(optString2, "");
                aFh1iSDK = new AFh1iSDK(j, optDouble, emptyList, i, optString, optString2);
            } else {
                aFh1iSDK = null;
            }
            m1882constructorimpl = Result.m1882constructorimpl(aFh1iSDK);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1882constructorimpl = Result.m1882constructorimpl(ResultKt.createFailure(th));
        }
        return (AFh1iSDK) (Result.m1888isFailureimpl(m1882constructorimpl) ? null : m1882constructorimpl);
    }

    private static JSONObject AFKeystoreWrapper(JSONObject jSONObject, String str) throws JSONException, NullPointerException {
        JSONObject optJSONObject;
        if (!jSONObject.has(str) || (optJSONObject = jSONObject.getJSONArray(str).optJSONObject(0).optJSONObject("data")) == null) {
            return null;
        }
        return optJSONObject.optJSONObject("v1");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            AFh1gSDK aFh1gSDK = (AFh1gSDK) obj;
            return Intrinsics.areEqual(this.AFKeystoreWrapper, aFh1gSDK.AFKeystoreWrapper) && Intrinsics.areEqual(this.AFInAppEventType, aFh1gSDK.AFInAppEventType) && Intrinsics.areEqual(this.valueOf, aFh1gSDK.valueOf);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.appsflyer.internal.model.rc.Features");
    }

    public final int hashCode() {
        AFh1nSDK aFh1nSDK = this.AFKeystoreWrapper;
        int hashCode = (aFh1nSDK != null ? aFh1nSDK.hashCode() : 0) * 31;
        AFh1fSDK aFh1fSDK = this.AFInAppEventType;
        int hashCode2 = (hashCode + (aFh1fSDK != null ? aFh1fSDK.hashCode() : 0)) * 31;
        AFh1iSDK aFh1iSDK = this.valueOf;
        return hashCode2 + (aFh1iSDK != null ? aFh1iSDK.hashCode() : 0);
    }
}
