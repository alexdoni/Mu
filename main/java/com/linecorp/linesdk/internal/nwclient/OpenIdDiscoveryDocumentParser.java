package com.linecorp.linesdk.internal.nwclient;

import com.linecorp.linesdk.internal.OpenIdDiscoveryDocument;
import com.linecorp.linesdk.utils.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class OpenIdDiscoveryDocumentParser extends JsonToObjectBaseResponseParser<OpenIdDiscoveryDocument> {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
    public OpenIdDiscoveryDocument parseJsonToObject(JSONObject jSONObject) throws JSONException {
        return new OpenIdDiscoveryDocument.Builder().issuer(jSONObject.getString("issuer")).authorizationEndpoint(jSONObject.getString("authorization_endpoint")).tokenEndpoint(jSONObject.getString("token_endpoint")).jwksUri(jSONObject.getString("jwks_uri")).responseTypesSupported(JSONUtils.toStringList(jSONObject.getJSONArray("response_types_supported"))).subjectTypesSupported(JSONUtils.toStringList(jSONObject.getJSONArray("subject_types_supported"))).idTokenSigningAlgValuesSupported(JSONUtils.toStringList(jSONObject.getJSONArray("id_token_signing_alg_values_supported"))).build();
    }
}
