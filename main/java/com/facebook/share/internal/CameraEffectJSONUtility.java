package com.facebook.share.internal;

import com.facebook.share.model.CameraEffectArguments;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CameraEffectJSONUtility.kt */
@Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0007R2\u0010\u0003\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m1395d2 = {"Lcom/facebook/share/internal/CameraEffectJSONUtility;", "", "()V", "SETTERS", "Ljava/util/HashMap;", "Ljava/lang/Class;", "Lcom/facebook/share/internal/CameraEffectJSONUtility$Setter;", "Lkotlin/collections/HashMap;", "convertToCameraEffectArguments", "Lcom/facebook/share/model/CameraEffectArguments;", "jsonObject", "Lorg/json/JSONObject;", "convertToJSON", "arguments", "Setter", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class CameraEffectJSONUtility {
    public static final CameraEffectJSONUtility INSTANCE = new CameraEffectJSONUtility();
    private static final HashMap<Class<?>, Setter> SETTERS = MapsKt.hashMapOf(TuplesKt.m1402to(String.class, new Setter() { // from class: com.facebook.share.internal.CameraEffectJSONUtility$SETTERS$1
        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(builder, "builder");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            builder.putArgument(key, (String) value);
        }

        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(key, "key");
            json.put(key, value);
        }
    }), TuplesKt.m1402to(String[].class, new Setter() { // from class: com.facebook.share.internal.CameraEffectJSONUtility$SETTERS$2
        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(builder, "builder");
            Intrinsics.checkNotNullParameter(key, "key");
            throw new IllegalArgumentException("Unexpected type from JSON");
        }

        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(key, "key");
            JSONArray jSONArray = new JSONArray();
            if (value != null) {
                String[] strArr = (String[]) value;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    String str = strArr[i];
                    i++;
                    jSONArray.put(str);
                }
                json.put(key, jSONArray);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String?>");
        }
    }), TuplesKt.m1402to(JSONArray.class, new Setter() { // from class: com.facebook.share.internal.CameraEffectJSONUtility$SETTERS$3
        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(builder, "builder");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value != null) {
                JSONArray jSONArray = (JSONArray) value;
                ArrayList arrayList = new ArrayList();
                int length = jSONArray.length();
                if (length > 0) {
                    int i = 0;
                    while (true) {
                        int i2 = i + 1;
                        Object obj = jSONArray.get(i);
                        if (obj instanceof String) {
                            arrayList.add(obj);
                            if (i2 >= length) {
                                break;
                            } else {
                                i = i2;
                            }
                        } else {
                            throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected type in an array: ", obj.getClass()));
                        }
                    }
                }
                Object[] array = arrayList.toArray(new String[0]);
                if (array != null) {
                    builder.putArgument(key, (String[]) array);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        }

        @Override // com.facebook.share.internal.CameraEffectJSONUtility.Setter
        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(key, "key");
            throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
        }
    }));

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CameraEffectJSONUtility.kt */
    @Metadata(m1394d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&J\"\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&¨\u0006\f"}, m1395d2 = {"Lcom/facebook/share/internal/CameraEffectJSONUtility$Setter;", "", "setOnArgumentsBuilder", "", "builder", "Lcom/facebook/share/model/CameraEffectArguments$Builder;", "key", "", "value", "setOnJSON", "json", "Lorg/json/JSONObject;", "facebook-common_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public interface Setter {
        void setOnArgumentsBuilder(CameraEffectArguments.Builder builder, String key, Object value) throws JSONException;

        void setOnJSON(JSONObject json, String key, Object value) throws JSONException;
    }

    private CameraEffectJSONUtility() {
    }

    @JvmStatic
    public static final JSONObject convertToJSON(CameraEffectArguments arguments) throws JSONException {
        if (arguments == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : arguments.keySet()) {
            Object obj = arguments.get(str);
            if (obj != null) {
                Setter setter = SETTERS.get(obj.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unsupported type: ", obj.getClass()));
                }
                setter.setOnJSON(jSONObject, str, obj);
            }
        }
        return jSONObject;
    }

    @JvmStatic
    public static final CameraEffectArguments convertToCameraEffectArguments(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        CameraEffectArguments.Builder builder = new CameraEffectArguments.Builder();
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object obj = jsonObject.get(key);
            if (obj != JSONObject.NULL) {
                Setter setter = SETTERS.get(obj.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unsupported type: ", obj.getClass()));
                }
                Intrinsics.checkNotNullExpressionValue(key, "key");
                setter.setOnArgumentsBuilder(builder, key, obj);
            }
        }
        return builder.build();
    }
}
