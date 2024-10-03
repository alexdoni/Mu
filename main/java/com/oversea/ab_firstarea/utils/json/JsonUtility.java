package com.oversea.ab_firstarea.utils.json;

import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.gson.Gson;
import com.xsdk.ab_firstbase.gson.GsonBuilder;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import com.xsdk.ab_firstbase.statisics.util.reflection.ReflectionUtils;
import com.xsdk.ab_firstbase.statisics.util.reflection.TypeDiscoverInfo;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsonUtility {
    private static Gson gson = new GsonBuilder().create();

    public static Object createStrongObjectFromJSON(TypeDiscoverInfo typeDiscoverInfo, String str) throws Exception {
        return ReflectionUtils.buildStrongTypeObject(typeDiscoverInfo, new JsonSerializer().deSerialize(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> T jsonToObject(java.lang.Class<T> r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "message"
            java.lang.String r1 = "code"
            java.lang.String r2 = "data"
            r3 = 0
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L19
            r4.<init>(r8)     // Catch: org.json.JSONException -> L19
            boolean r5 = r4.isNull(r2)     // Catch: org.json.JSONException -> L17
            if (r5 != 0) goto L1e
            java.lang.Object r3 = r4.get(r2)     // Catch: org.json.JSONException -> L17
            goto L1e
        L17:
            r5 = move-exception
            goto L1b
        L19:
            r5 = move-exception
            r4 = r3
        L1b:
            r5.printStackTrace()
        L1e:
            java.lang.Class<com.oversea.ab_firstplatform.model.BaseBean> r5 = com.oversea.ab_firstplatform.model.BaseBean.class
            java.lang.String r5 = r5.getName()
            java.lang.String r6 = r7.getName()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L4d
            if (r3 == 0) goto L48
            boolean r7 = com.xsdk.ab_firstbase.statisics.util.JudgeJson.isJsonArray(r3)
            if (r7 == 0) goto L3f
            java.lang.Class<com.oversea.ab_firstplatform.model.BaseBeanArray> r7 = com.oversea.ab_firstplatform.model.BaseBeanArray.class
            java.lang.Object r7 = jsonParseToObject(r7, r8)
            com.oversea.ab_firstplatform.model.BaseBean r7 = (com.oversea.ab_firstplatform.model.BaseBean) r7
            return r7
        L3f:
            java.lang.Class<com.oversea.ab_firstplatform.model.BaseBeanObject> r7 = com.oversea.ab_firstplatform.model.BaseBeanObject.class
            java.lang.Object r7 = jsonParseToObject(r7, r8)
            com.oversea.ab_firstplatform.model.BaseBean r7 = (com.oversea.ab_firstplatform.model.BaseBean) r7
            return r7
        L48:
            com.oversea.ab_firstplatform.model.BaseBean r7 = createBaseBean(r4)
            return r7
        L4d:
            if (r3 == 0) goto L98
            boolean r5 = com.xsdk.ab_firstbase.statisics.util.JudgeJson.isJsonArray(r3)
            if (r5 == 0) goto L88
            boolean r3 = com.xsdk.ab_firstbase.statisics.util.JudgeJson.isJsonArrayIsNull(r3)
            if (r3 == 0) goto L83
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r5 = r4.getString(r1)     // Catch: org.json.JSONException -> L7f
            r3.put(r1, r5)     // Catch: org.json.JSONException -> L7f
            java.lang.String r1 = r4.getString(r0)     // Catch: org.json.JSONException -> L7f
            r3.put(r0, r1)     // Catch: org.json.JSONException -> L7f
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L7f
            r0.<init>()     // Catch: org.json.JSONException -> L7f
            r3.put(r2, r0)     // Catch: org.json.JSONException -> L7f
            java.lang.String r0 = r3.toString()     // Catch: org.json.JSONException -> L7f
            java.lang.Object r7 = jsonParseToObject(r7, r0)     // Catch: org.json.JSONException -> L7f
            return r7
        L7f:
            r0 = move-exception
            r0.printStackTrace()
        L83:
            java.lang.Object r7 = jsonParseToObject(r7, r8)
            return r7
        L88:
            boolean r0 = com.xsdk.ab_firstbase.statisics.util.JudgeJson.isJsonObject(r3)
            if (r0 == 0) goto L93
            java.lang.Object r7 = jsonParseToObject(r7, r8)
            return r7
        L93:
            com.oversea.ab_firstplatform.model.BaseBean r7 = createBaseBean(r4)
            return r7
        L98:
            java.lang.Object r7 = jsonParseToObject(r7, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.utils.json.JsonUtility.jsonToObject(java.lang.Class, java.lang.String):java.lang.Object");
    }

    public static <T> T jsonParseToObject(Class<T> cls, String str) {
        return (T) gson.fromJson(str, new ParameterizedTypeImpl(cls, new Class[]{cls}));
    }

    private static BaseBean createBaseBean(JSONObject jSONObject) {
        BaseBean baseBean = new BaseBean();
        baseBean.setCode(jSONObject.optInt("code"));
        baseBean.setMessage(jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE));
        return baseBean;
    }
}
