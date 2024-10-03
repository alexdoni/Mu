package com.linecorp.linesdk.internal.nwclient;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.linecorp.linesdk.internal.JWKSet;
import io.jsonwebtoken.JwsHeader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JWKSetParser extends JsonToObjectBaseResponseParser<JWKSet> {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
    public JWKSet parseJsonToObject(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray(UserMetadata.KEYDATA_FILENAME);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            arrayList.add(new JWKSet.JWK.Builder().keyType(jSONObject2.getString("kty")).algorithm(jSONObject2.getString(JwsHeader.ALGORITHM)).use(jSONObject2.getString("use")).keyId(jSONObject2.getString(JwsHeader.KEY_ID)).curve(jSONObject2.getString("crv")).m586x(jSONObject2.getString("x")).m587y(jSONObject2.getString("y")).build());
        }
        return new JWKSet.Builder().keys(arrayList).build();
    }
}
