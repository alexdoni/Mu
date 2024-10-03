package com.linecorp.linesdk.internal.nwclient;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.internal.ServerProtocol;
import com.linecorp.linesdk.BuildConfig;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineIdToken;
import com.linecorp.linesdk.Scope;
import com.linecorp.linesdk.internal.AccessTokenVerificationResult;
import com.linecorp.linesdk.internal.IdTokenKeyType;
import com.linecorp.linesdk.internal.InternalAccessToken;
import com.linecorp.linesdk.internal.IssueAccessTokenResult;
import com.linecorp.linesdk.internal.JWKSet;
import com.linecorp.linesdk.internal.OneTimePassword;
import com.linecorp.linesdk.internal.OpenIdDiscoveryDocument;
import com.linecorp.linesdk.internal.RefreshTokenResult;
import com.linecorp.linesdk.internal.nwclient.core.ChannelServiceHttpClient;
import com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser;
import com.linecorp.linesdk.utils.UriUtils;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LineAuthenticationApiClient {
    private static final String AVAILABLE_TOKEN_TYPE = "Bearer";
    private static final String BASE_PATH_OAUTH_V21_API = "oauth2/v2.1";
    private static final ResponseDataParser<OneTimePassword> ONE_TIME_PASSWORD_PARSER;
    private static final ResponseDataParser<RefreshTokenResult> REFRESH_TOKEN_RESULT_PARSER;
    private static final String TAG = "LineAuthApiClient";
    private static final ResponseDataParser<AccessTokenVerificationResult> VERIFICATION_RESULT_PARSER;
    private final ResponseDataParser<IssueAccessTokenResult> ISSUE_ACCESS_TOKEN_RESULT_PARSER;
    private final Uri apiBaseUrl;
    private final ChannelServiceHttpClient httpClient;
    private final Uri openidDiscoveryDocumentUrl;
    private final OpenIdSigningKeyResolver signingKeyResolver;
    private static final ResponseDataParser<?> NO_RESULT_RESPONSE_PARSER = new NoResultResponseParser();
    private static final ResponseDataParser<OpenIdDiscoveryDocument> OPEN_ID_DISCOVERY_DOCUMENT_PARSER = new OpenIdDiscoveryDocumentParser();
    private static final ResponseDataParser<JWKSet> JWK_SET_PARSER = new JWKSetParser();

    static {
        ONE_TIME_PASSWORD_PARSER = new OneTimePasswordParser();
        VERIFICATION_RESULT_PARSER = new VerificationResultParser();
        REFRESH_TOKEN_RESULT_PARSER = new RefreshTokenResultParser();
    }

    public LineAuthenticationApiClient(Context context, Uri uri, Uri uri2) {
        this(uri, uri2, new ChannelServiceHttpClient(context, BuildConfig.VERSION_NAME));
    }

    LineAuthenticationApiClient(Uri uri, Uri uri2, ChannelServiceHttpClient channelServiceHttpClient) {
        this.ISSUE_ACCESS_TOKEN_RESULT_PARSER = new IssueAccessTokenResultParser();
        this.signingKeyResolver = new OpenIdSigningKeyResolver(this);
        this.openidDiscoveryDocumentUrl = uri;
        this.apiBaseUrl = uri2;
        this.httpClient = channelServiceHttpClient;
    }

    public LineApiResponse<OneTimePassword> getOneTimeIdAndPassword(String str) {
        return this.httpClient.post(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_OAUTH_V21_API, "otp"), Collections.emptyMap(), UriUtils.buildParams("client_id", str), ONE_TIME_PASSWORD_PARSER);
    }

    /* loaded from: classes2.dex */
    private static class OneTimePasswordParser extends JsonToObjectBaseResponseParser<OneTimePassword> {
        private OneTimePasswordParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public OneTimePassword parseJsonToObject(JSONObject jSONObject) throws JSONException {
            return new OneTimePassword(jSONObject.getString("otpId"), jSONObject.getString("otp"));
        }
    }

    public LineApiResponse<IssueAccessTokenResult> issueAccessToken(String str, String str2, OneTimePassword oneTimePassword, String str3) {
        return this.httpClient.post(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_OAUTH_V21_API, "token"), Collections.emptyMap(), UriUtils.buildParams("grant_type", "authorization_code", "code", str2, ServerProtocol.DIALOG_PARAM_REDIRECT_URI, str3, "client_id", str, "otp", oneTimePassword.getPassword(), "id_token_key_type", IdTokenKeyType.JWK.name(), "client_version", "LINE SDK Android v5.3.1"), this.ISSUE_ACCESS_TOKEN_RESULT_PARSER);
    }

    /* loaded from: classes2.dex */
    private class IssueAccessTokenResultParser extends JsonToObjectBaseResponseParser<IssueAccessTokenResult> {
        private IssueAccessTokenResultParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public IssueAccessTokenResult parseJsonToObject(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString("token_type");
            if (!LineAuthenticationApiClient.AVAILABLE_TOKEN_TYPE.equals(string)) {
                throw new JSONException("Illegal token type. token_type=" + string);
            }
            try {
                return new IssueAccessTokenResult(new InternalAccessToken(jSONObject.getString("access_token"), 1000 * jSONObject.getLong(AccessToken.EXPIRES_IN_KEY), System.currentTimeMillis(), jSONObject.getString("refresh_token")), Scope.parseToList(jSONObject.getString("scope")), parseIdToken(jSONObject.optString("id_token")));
            } catch (Exception e) {
                throw new JSONException(e.getMessage());
            }
        }

        private LineIdToken parseIdToken(String str) throws Exception {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return IdTokenParser.parse(str, LineAuthenticationApiClient.this.signingKeyResolver);
        }
    }

    public LineApiResponse<AccessTokenVerificationResult> verifyAccessToken(InternalAccessToken internalAccessToken) {
        return this.httpClient.get(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_OAUTH_V21_API, "verify"), Collections.emptyMap(), UriUtils.buildParams("access_token", internalAccessToken.getAccessToken()), VERIFICATION_RESULT_PARSER);
    }

    /* loaded from: classes2.dex */
    private static class VerificationResultParser extends JsonToObjectBaseResponseParser<AccessTokenVerificationResult> {
        private VerificationResultParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public AccessTokenVerificationResult parseJsonToObject(JSONObject jSONObject) throws JSONException {
            return new AccessTokenVerificationResult(jSONObject.getString("client_id"), jSONObject.getLong(AccessToken.EXPIRES_IN_KEY) * 1000, Scope.parseToList(jSONObject.getString("scope")));
        }
    }

    public LineApiResponse<RefreshTokenResult> refreshToken(String str, InternalAccessToken internalAccessToken) {
        return this.httpClient.post(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_OAUTH_V21_API, "token"), Collections.emptyMap(), UriUtils.buildParams("grant_type", "refresh_token", "refresh_token", internalAccessToken.getRefreshToken(), "client_id", str), REFRESH_TOKEN_RESULT_PARSER);
    }

    /* loaded from: classes2.dex */
    private static class RefreshTokenResultParser extends JsonToObjectBaseResponseParser<RefreshTokenResult> {
        private RefreshTokenResultParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public RefreshTokenResult parseJsonToObject(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString("token_type");
            if (!LineAuthenticationApiClient.AVAILABLE_TOKEN_TYPE.equals(string)) {
                throw new JSONException("Illegal token type. token_type=" + string);
            }
            return new RefreshTokenResult(jSONObject.getString("access_token"), 1000 * jSONObject.getLong(AccessToken.EXPIRES_IN_KEY), jSONObject.getString("refresh_token"), Scope.parseToList(jSONObject.getString("scope")));
        }
    }

    public LineApiResponse<?> revokeAccessToken(String str, InternalAccessToken internalAccessToken) {
        return this.httpClient.post(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_OAUTH_V21_API, "revoke"), Collections.emptyMap(), UriUtils.buildParams("access_token", internalAccessToken.getAccessToken(), "client_id", str), NO_RESULT_RESPONSE_PARSER);
    }

    public LineApiResponse<?> revokeRefreshToken(String str, InternalAccessToken internalAccessToken) {
        return this.httpClient.post(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_OAUTH_V21_API, "revoke"), Collections.emptyMap(), UriUtils.buildParams("refresh_token", internalAccessToken.getRefreshToken(), "client_id", str), NO_RESULT_RESPONSE_PARSER);
    }

    public LineApiResponse<OpenIdDiscoveryDocument> getOpenIdDiscoveryDocument() {
        LineApiResponse<OpenIdDiscoveryDocument> lineApiResponse = this.httpClient.get(UriUtils.buildUri(this.openidDiscoveryDocumentUrl, new String[0]), Collections.emptyMap(), Collections.emptyMap(), OPEN_ID_DISCOVERY_DOCUMENT_PARSER);
        if (!lineApiResponse.isSuccess()) {
            Log.e(TAG, "getOpenIdDiscoveryDocument failed: " + lineApiResponse);
        }
        return lineApiResponse;
    }

    public LineApiResponse<JWKSet> getJWKSet() {
        LineApiResponse<OpenIdDiscoveryDocument> openIdDiscoveryDocument = getOpenIdDiscoveryDocument();
        if (!openIdDiscoveryDocument.isSuccess()) {
            return LineApiResponse.createAsError(openIdDiscoveryDocument.getResponseCode(), openIdDiscoveryDocument.getErrorData());
        }
        LineApiResponse<JWKSet> lineApiResponse = this.httpClient.get(Uri.parse(openIdDiscoveryDocument.getResponseData().getJwksUri()), Collections.emptyMap(), Collections.emptyMap(), JWK_SET_PARSER);
        if (!lineApiResponse.isSuccess()) {
            Log.e(TAG, "getJWKSet failed: " + lineApiResponse);
        }
        return lineApiResponse;
    }
}
