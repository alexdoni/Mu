package com.linecorp.linesdk.api;

import com.linecorp.linesdk.FriendSortField;
import com.linecorp.linesdk.GetFriendsResponse;
import com.linecorp.linesdk.GetGroupsResponse;
import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineFriendshipStatus;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.SendMessageResponse;
import com.linecorp.linesdk.message.MessageData;
import java.util.List;

/* loaded from: classes2.dex */
public interface LineApiClient {
    LineApiResponse<LineAccessToken> getCurrentAccessToken();

    LineApiResponse<GetFriendsResponse> getFriends(FriendSortField friendSortField, String str);

    LineApiResponse<GetFriendsResponse> getFriends(FriendSortField friendSortField, String str, boolean z);

    LineApiResponse<GetFriendsResponse> getFriendsApprovers(FriendSortField friendSortField, String str);

    LineApiResponse<LineFriendshipStatus> getFriendshipStatus();

    LineApiResponse<GetFriendsResponse> getGroupApprovers(String str, String str2);

    LineApiResponse<GetGroupsResponse> getGroups(String str);

    LineApiResponse<GetGroupsResponse> getGroups(String str, boolean z);

    LineApiResponse<LineProfile> getProfile();

    LineApiResponse<?> logout();

    LineApiResponse<LineAccessToken> refreshAccessToken();

    LineApiResponse<String> sendMessage(String str, List<MessageData> list);

    LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsers(List<String> list, List<MessageData> list2);

    LineApiResponse<List<SendMessageResponse>> sendMessageToMultipleUsers(List<String> list, List<MessageData> list2, boolean z);

    LineApiResponse<LineCredential> verifyToken();
}
