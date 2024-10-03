package com.linecorp.linesdk.api.internal;

import android.text.TextUtils;
import com.linecorp.linesdk.FriendSortField;
import com.linecorp.linesdk.GetFriendsResponse;
import com.linecorp.linesdk.GetGroupsResponse;
import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineFriendshipStatus;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.SendMessageResponse;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.internal.AccessTokenCache;
import com.linecorp.linesdk.internal.AccessTokenVerificationResult;
import com.linecorp.linesdk.internal.InternalAccessToken;
import com.linecorp.linesdk.internal.RefreshTokenResult;
import com.linecorp.linesdk.internal.nwclient.LineAuthenticationApiClient;
import com.linecorp.linesdk.internal.nwclient.TalkApiClient;
import com.linecorp.linesdk.message.MessageData;
import java.util.List;

/* loaded from: classes2.dex */
public class LineApiClientImpl implements LineApiClient {
    private static final LineApiResponse ERROR_RESPONSE_NO_TOKEN = LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError("access token is null"));
    private final AccessTokenCache accessTokenCache;
    private final String channelId;
    private final LineAuthenticationApiClient oauthApiClient;
    private final TalkApiClient talkApiClient;

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface APIWithAccessToken<T> {
        LineApiResponse<T> call(InternalAccessToken internalAccessToken);
    }

    public LineApiClientImpl(String str, LineAuthenticationApiClient lineAuthenticationApiClient, TalkApiClient talkApiClient, AccessTokenCache accessTokenCache) {
        this.channelId = str;
        this.oauthApiClient = lineAuthenticationApiClient;
        this.talkApiClient = talkApiClient;
        this.accessTokenCache = accessTokenCache;
    }

    private <T> LineApiResponse<T> callWithAccessToken(APIWithAccessToken<T> aPIWithAccessToken) {
        InternalAccessToken accessToken = this.accessTokenCache.getAccessToken();
        if (accessToken == null) {
            return ERROR_RESPONSE_NO_TOKEN;
        }
        return aPIWithAccessToken.call(accessToken);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    public LineApiResponse<?> logout() {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda8
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                LineApiResponse logout;
                logout = LineApiClientImpl.this.logout(internalAccessToken);
                return logout;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LineApiResponse<?> logout(InternalAccessToken internalAccessToken) {
        LineApiResponse<?> revokeRefreshToken = this.oauthApiClient.revokeRefreshToken(this.channelId, internalAccessToken);
        if (revokeRefreshToken.isSuccess()) {
            this.accessTokenCache.clear();
        }
        return revokeRefreshToken;
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    public LineApiResponse<LineAccessToken> refreshAccessToken() {
        InternalAccessToken accessToken = this.accessTokenCache.getAccessToken();
        if (accessToken == null || TextUtils.isEmpty(accessToken.getRefreshToken())) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError("access token or refresh token is not found."));
        }
        LineApiResponse<RefreshTokenResult> refreshToken = this.oauthApiClient.refreshToken(this.channelId, accessToken);
        if (!refreshToken.isSuccess()) {
            return LineApiResponse.createAsError(refreshToken.getResponseCode(), refreshToken.getErrorData());
        }
        RefreshTokenResult responseData = refreshToken.getResponseData();
        InternalAccessToken internalAccessToken = new InternalAccessToken(responseData.getAccessToken(), responseData.getExpiresInMillis(), System.currentTimeMillis(), TextUtils.isEmpty(responseData.getRefreshToken()) ? accessToken.getRefreshToken() : responseData.getRefreshToken());
        this.accessTokenCache.saveAccessToken(internalAccessToken);
        return LineApiResponse.createAsSuccess(new LineAccessToken(internalAccessToken.getAccessToken(), internalAccessToken.getExpiresInMillis(), internalAccessToken.getIssuedClientTimeMillis()));
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    public LineApiResponse<LineCredential> verifyToken() {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda7
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                LineApiResponse verifyToken;
                verifyToken = LineApiClientImpl.this.verifyToken(internalAccessToken);
                return verifyToken;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LineApiResponse<LineCredential> verifyToken(InternalAccessToken internalAccessToken) {
        LineApiResponse<AccessTokenVerificationResult> verifyAccessToken = this.oauthApiClient.verifyAccessToken(internalAccessToken);
        if (!verifyAccessToken.isSuccess()) {
            return LineApiResponse.createAsError(verifyAccessToken.getResponseCode(), verifyAccessToken.getErrorData());
        }
        AccessTokenVerificationResult responseData = verifyAccessToken.getResponseData();
        long currentTimeMillis = System.currentTimeMillis();
        this.accessTokenCache.saveAccessToken(new InternalAccessToken(internalAccessToken.getAccessToken(), responseData.getExpiresInMillis(), currentTimeMillis, internalAccessToken.getRefreshToken()));
        return LineApiResponse.createAsSuccess(new LineCredential(new LineAccessToken(internalAccessToken.getAccessToken(), responseData.getExpiresInMillis(), currentTimeMillis), responseData.getScopes()));
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    public LineApiResponse<LineAccessToken> getCurrentAccessToken() {
        InternalAccessToken accessToken = this.accessTokenCache.getAccessToken();
        if (accessToken == null) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError("The cached access token does not exist."));
        }
        return LineApiResponse.createAsSuccess(new LineAccessToken(accessToken.getAccessToken(), accessToken.getExpiresInMillis(), accessToken.getIssuedClientTimeMillis()));
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<LineProfile> getProfile() {
        final TalkApiClient talkApiClient = this.talkApiClient;
        talkApiClient.getClass();
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda2
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return TalkApiClient.this.getProfile(internalAccessToken);
            }
        });
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<LineFriendshipStatus> getFriendshipStatus() {
        final TalkApiClient talkApiClient = this.talkApiClient;
        talkApiClient.getClass();
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda4
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return TalkApiClient.this.getFriendshipStatus(internalAccessToken);
            }
        });
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<GetFriendsResponse> getFriends(FriendSortField friendSortField, String str) {
        return getFriends(friendSortField, str, false);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<GetFriendsResponse> getFriends(final FriendSortField friendSortField, final String str, final boolean z) {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda3
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return LineApiClientImpl.this.m577x2efe3e02(friendSortField, str, z, internalAccessToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getFriends$0$com-linecorp-linesdk-api-internal-LineApiClientImpl */
    public /* synthetic */ LineApiResponse m577x2efe3e02(FriendSortField friendSortField, String str, boolean z, InternalAccessToken internalAccessToken) {
        return this.talkApiClient.getFriends(internalAccessToken, friendSortField, str, z);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<GetFriendsResponse> getFriendsApprovers(final FriendSortField friendSortField, final String str) {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda5
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return LineApiClientImpl.this.m578xde004ff9(friendSortField, str, internalAccessToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getFriendsApprovers$1$com-linecorp-linesdk-api-internal-LineApiClientImpl */
    public /* synthetic */ LineApiResponse m578xde004ff9(FriendSortField friendSortField, String str, InternalAccessToken internalAccessToken) {
        return this.talkApiClient.getFriendsApprovers(internalAccessToken, friendSortField, str);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<GetGroupsResponse> getGroups(String str) {
        return getGroups(str, false);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<GetGroupsResponse> getGroups(final String str, final boolean z) {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda0
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return LineApiClientImpl.this.m580x2b0be415(str, z, internalAccessToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGroups$2$com-linecorp-linesdk-api-internal-LineApiClientImpl */
    public /* synthetic */ LineApiResponse m580x2b0be415(String str, boolean z, InternalAccessToken internalAccessToken) {
        return this.talkApiClient.getGroups(internalAccessToken, str, z);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<GetFriendsResponse> getGroupApprovers(final String str, final String str2) {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda9
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return LineApiClientImpl.this.m579xa088d311(str, str2, internalAccessToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGroupApprovers$3$com-linecorp-linesdk-api-internal-LineApiClientImpl */
    public /* synthetic */ LineApiResponse m579xa088d311(String str, String str2, InternalAccessToken internalAccessToken) {
        return this.talkApiClient.getGroupApprovers(internalAccessToken, str, str2);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<String> sendMessage(final String str, final List<MessageData> list) {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda1
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return LineApiClientImpl.this.m581x89e5eb2c(str, list, internalAccessToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendMessage$4$com-linecorp-linesdk-api-internal-LineApiClientImpl */
    public /* synthetic */ LineApiResponse m581x89e5eb2c(String str, List list, InternalAccessToken internalAccessToken) {
        return this.talkApiClient.sendMessage(internalAccessToken, str, list);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsers(List<String> list, List<MessageData> list2) {
        return sendMessageToMultipleUsers(list, list2, false);
    }

    @Override // com.linecorp.linesdk.api.LineApiClient
    @TokenAutoRefresh
    public LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsers(final List<String> list, final List<MessageData> list2, final boolean z) {
        return callWithAccessToken(new APIWithAccessToken() { // from class: com.linecorp.linesdk.api.internal.LineApiClientImpl$$ExternalSyntheticLambda6
            @Override // com.linecorp.linesdk.api.internal.LineApiClientImpl.APIWithAccessToken
            public final LineApiResponse call(InternalAccessToken internalAccessToken) {
                return LineApiClientImpl.this.m582xb38b2aa6(list, list2, z, internalAccessToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendMessageToMultipleUsers$5$com-linecorp-linesdk-api-internal-LineApiClientImpl */
    public /* synthetic */ LineApiResponse m582xb38b2aa6(List list, List list2, boolean z, InternalAccessToken internalAccessToken) {
        return this.talkApiClient.sendMessageToMultipleUsers(internalAccessToken, list, list2, z);
    }
}
