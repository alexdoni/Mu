package com.linecorp.linesdk.internal.nwclient;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.Scopes;
import com.linecorp.linesdk.BuildConfig;
import com.linecorp.linesdk.FriendSortField;
import com.linecorp.linesdk.GetFriendsResponse;
import com.linecorp.linesdk.GetGroupsResponse;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.LineFriendshipStatus;
import com.linecorp.linesdk.LineGroup;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.SendMessageResponse;
import com.linecorp.linesdk.internal.InternalAccessToken;
import com.linecorp.linesdk.internal.nwclient.core.ChannelServiceHttpClient;
import com.linecorp.linesdk.internal.nwclient.core.ResponseDataParser;
import com.linecorp.linesdk.message.MessageData;
import com.linecorp.linesdk.message.MessageSendRequest;
import com.linecorp.linesdk.message.OttRequest;
import com.linecorp.linesdk.utils.UriUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TalkApiClient {
    private static final String BASE_PATH_COMMON_API = "v2";
    private static final String BASE_PATH_FRIENDSHIP_API = "friendship/v1";
    static final String BASE_PATH_GRAPH_API = "graph/v2";
    static final String BASE_PATH_MESSAGE_API = "message/v3";
    static final String PATH_FRIENDS = "friends";
    static final String PATH_GROUPS = "groups";
    static final String PATH_OTS_FRIENDS = "ots/friends";
    static final String PATH_OTS_GROUPS = "ots/groups";
    static final String PATH_OTT_ISSUE = "ott/issue";
    static final String PATH_OTT_SHARE = "ott/share";
    private static final String REQUEST_HEADER_ACCESS_TOKEN = "Authorization";
    private static final String TOKEN_TYPE_BEARER = "Bearer";
    private final Uri apiBaseUrl;
    private final ChannelServiceHttpClient httpClient;
    private static final ResponseDataParser<LineProfile> PROFILE_PARSER = new ProfileParser();
    private static final ResponseDataParser<LineFriendshipStatus> FRIENDSHIP_STATUS_PARSER = new FriendshipStatusParser();
    private static final ResponseDataParser<GetFriendsResponse> FRIENDS_PARSER = new FriendsParser();
    private static final ResponseDataParser<GetGroupsResponse> GROUP_PARSER = new GroupParser();

    public TalkApiClient(Context context, Uri uri) {
        this(uri, new ChannelServiceHttpClient(context, BuildConfig.VERSION_NAME));
    }

    TalkApiClient(Uri uri, ChannelServiceHttpClient channelServiceHttpClient) {
        this.apiBaseUrl = uri;
        this.httpClient = channelServiceHttpClient;
    }

    private static Map<String, String> buildRequestHeaders(InternalAccessToken internalAccessToken) {
        return UriUtils.buildParams("Authorization", "Bearer " + internalAccessToken.getAccessToken());
    }

    public LineApiResponse<LineProfile> getProfile(InternalAccessToken internalAccessToken) {
        return this.httpClient.get(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_COMMON_API, Scopes.PROFILE), buildRequestHeaders(internalAccessToken), Collections.emptyMap(), PROFILE_PARSER);
    }

    public LineApiResponse<LineFriendshipStatus> getFriendshipStatus(InternalAccessToken internalAccessToken) {
        return this.httpClient.get(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_FRIENDSHIP_API, "status"), buildRequestHeaders(internalAccessToken), Collections.emptyMap(), FRIENDSHIP_STATUS_PARSER);
    }

    public LineApiResponse<GetFriendsResponse> getFriends(InternalAccessToken internalAccessToken, FriendSortField friendSortField, String str, boolean z) {
        Uri buildUri = UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_GRAPH_API, z ? PATH_OTS_FRIENDS : "friends");
        Map<String, String> buildParams = UriUtils.buildParams("sort", friendSortField.getServerKey());
        if (!TextUtils.isEmpty(str)) {
            buildParams.put("pageToken", str);
        }
        return this.httpClient.get(buildUri, buildRequestHeaders(internalAccessToken), buildParams, FRIENDS_PARSER);
    }

    public LineApiResponse<GetGroupsResponse> getGroups(InternalAccessToken internalAccessToken, String str, boolean z) {
        Map<String, String> emptyMap;
        Uri buildUri = UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_GRAPH_API, z ? PATH_OTS_GROUPS : PATH_GROUPS);
        if (!TextUtils.isEmpty(str)) {
            emptyMap = UriUtils.buildParams("pageToken", str);
        } else {
            emptyMap = Collections.emptyMap();
        }
        return this.httpClient.get(buildUri, buildRequestHeaders(internalAccessToken), emptyMap, GROUP_PARSER);
    }

    public LineApiResponse<GetFriendsResponse> getFriendsApprovers(InternalAccessToken internalAccessToken, FriendSortField friendSortField, String str) {
        Uri buildUri = UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_GRAPH_API, "friends", "approvers");
        Map<String, String> buildParams = UriUtils.buildParams("sort", friendSortField.getServerKey());
        if (!TextUtils.isEmpty(str)) {
            buildParams.put("pageToken", str);
        }
        return this.httpClient.get(buildUri, buildRequestHeaders(internalAccessToken), buildParams, FRIENDS_PARSER);
    }

    public LineApiResponse<GetFriendsResponse> getGroupApprovers(InternalAccessToken internalAccessToken, String str, String str2) {
        Map<String, String> emptyMap;
        Uri buildUri = UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_GRAPH_API, PATH_GROUPS, str, "approvers");
        if (!TextUtils.isEmpty(str2)) {
            emptyMap = UriUtils.buildParams("pageToken", str2);
        } else {
            emptyMap = Collections.emptyMap();
        }
        return this.httpClient.get(buildUri, buildRequestHeaders(internalAccessToken), emptyMap, FRIENDS_PARSER);
    }

    public LineApiResponse<String> sendMessage(InternalAccessToken internalAccessToken, String str, List<MessageData> list) {
        try {
            return this.httpClient.postWithJson(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_MESSAGE_API, "send"), buildRequestHeaders(internalAccessToken), MessageSendRequest.createSingleUserType(str, list).toJsonString(), new StringParser("status"));
        } catch (JSONException e) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError(e));
        }
    }

    public LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsers(InternalAccessToken internalAccessToken, List<String> list, List<MessageData> list2) {
        return sendMessageToMultipleUsers(internalAccessToken, list, list2, false);
    }

    public LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsers(InternalAccessToken internalAccessToken, List<String> list, List<MessageData> list2, boolean z) {
        if (z) {
            LineApiResponse<String> ott = getOtt(internalAccessToken, list);
            if (ott.isSuccess()) {
                return sendMessageToMultipleUsersUsingOtt(internalAccessToken, ott.getResponseData(), list2);
            }
            return LineApiResponse.createAsError(ott.getResponseCode(), ott.getErrorData());
        }
        return sendMessageToMultipleUsersUsingUserIds(internalAccessToken, list, list2);
    }

    private LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsersUsingUserIds(InternalAccessToken internalAccessToken, List<String> list, List<MessageData> list2) {
        try {
            return this.httpClient.postWithJson(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_MESSAGE_API, "multisend"), buildRequestHeaders(internalAccessToken), MessageSendRequest.createMultiUsersType(list, list2).toJsonString(), new MultiSendResponseParser());
        } catch (JSONException e) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError(e));
        }
    }

    protected LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsersUsingOtt(InternalAccessToken internalAccessToken, String str, List<MessageData> list) {
        try {
            return this.httpClient.postWithJson(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_MESSAGE_API, PATH_OTT_SHARE), buildRequestHeaders(internalAccessToken), MessageSendRequest.createOttType(str, list).toJsonString(), new MultiSendResponseParser());
        } catch (JSONException e) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError(e));
        }
    }

    private LineApiResponse<String> getOtt(InternalAccessToken internalAccessToken, List<String> list) {
        try {
            return this.httpClient.postWithJson(UriUtils.buildUri(this.apiBaseUrl, BASE_PATH_MESSAGE_API, PATH_OTT_ISSUE), buildRequestHeaders(internalAccessToken), new OttRequest(list).toJsonString(), new StringParser("token"));
        } catch (JSONException e) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError(e));
        }
    }

    /* loaded from: classes2.dex */
    static class FriendsParser extends JsonToObjectBaseResponseParser<GetFriendsResponse> {
        FriendsParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public GetFriendsResponse parseJsonToObject(JSONObject jSONObject) throws JSONException {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("friends");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(ProfileParser.parseLineProfile(jSONArray.getJSONObject(i)));
            }
            return new GetFriendsResponse(arrayList, jSONObject.optString("pageToken", null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ProfileParser extends JsonToObjectBaseResponseParser<LineProfile> {
        ProfileParser() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LineProfile parseLineProfile(JSONObject jSONObject) throws JSONException {
            String optString = jSONObject.optString("pictureUrl", null);
            return new LineProfile(jSONObject.getString("userId"), jSONObject.getString("displayName"), optString == null ? null : Uri.parse(optString), jSONObject.optString("statusMessage", null));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public LineProfile parseJsonToObject(JSONObject jSONObject) throws JSONException {
            return parseLineProfile(jSONObject);
        }
    }

    /* loaded from: classes2.dex */
    static class FriendshipStatusParser extends JsonToObjectBaseResponseParser<LineFriendshipStatus> {
        FriendshipStatusParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public LineFriendshipStatus parseJsonToObject(JSONObject jSONObject) throws JSONException {
            return new LineFriendshipStatus(jSONObject.getBoolean("friendFlag"));
        }
    }

    /* loaded from: classes2.dex */
    static class GroupParser extends JsonToObjectBaseResponseParser<GetGroupsResponse> {
        GroupParser() {
        }

        private static LineGroup parseLineGroup(JSONObject jSONObject) throws JSONException {
            String optString = jSONObject.optString("pictureUrl", null);
            return new LineGroup(jSONObject.getString("groupId"), jSONObject.getString("groupName"), optString != null ? Uri.parse(optString) : null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public GetGroupsResponse parseJsonToObject(JSONObject jSONObject) throws JSONException {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray(TalkApiClient.PATH_GROUPS);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(parseLineGroup(jSONArray.getJSONObject(i)));
            }
            return new GetGroupsResponse(arrayList, jSONObject.optString("pageToken", null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class StringParser extends JsonToObjectBaseResponseParser<String> {
        private String jsonKey;

        StringParser(String str) {
            this.jsonKey = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public String parseJsonToObject(JSONObject jSONObject) throws JSONException {
            return jSONObject.getString(this.jsonKey);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class MultiSendResponseParser extends JsonToObjectBaseResponseParser<List<SendMessageResponse>> {
        MultiSendResponseParser() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.linecorp.linesdk.internal.nwclient.JsonToObjectBaseResponseParser
        public List<SendMessageResponse> parseJsonToObject(JSONObject jSONObject) throws JSONException {
            ArrayList arrayList = new ArrayList();
            if (jSONObject.has("results")) {
                JSONArray jSONArray = jSONObject.getJSONArray("results");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(SendMessageResponse.fromJsonObject(jSONArray.getJSONObject(i)));
                }
            }
            return arrayList;
        }
    }
}
