package com.linecorp.linesdk.dialog.internal;

import android.os.AsyncTask;
import com.linecorp.linesdk.FriendSortField;
import com.linecorp.linesdk.GetFriendsResponse;
import com.linecorp.linesdk.GetGroupsResponse;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineGroup;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.dialog.internal.TargetUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class GetTargetUserTask extends AsyncTask<Void, List<TargetUser>, Void> {
    private LineApiClient lineApiClient;
    private NextAction nextAction;
    private TargetUser.Type type;

    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface NextAction {
        void run(List<TargetUser> list);
    }

    public GetTargetUserTask(TargetUser.Type type, LineApiClient lineApiClient, NextAction nextAction) {
        this.type = type;
        this.lineApiClient = lineApiClient;
        this.nextAction = nextAction;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        if (this.type == TargetUser.Type.FRIEND) {
            getAllFriends();
            return null;
        }
        if (this.type != TargetUser.Type.GROUP) {
            return null;
        }
        getAllGroups();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(List<TargetUser>... listArr) {
        this.nextAction.run(listArr[0]);
    }

    private List<TargetUser> convertFriendsToTargetUsers(List<LineProfile> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<LineProfile> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(TargetUser.createInstance(it.next()));
        }
        return arrayList;
    }

    private List<TargetUser> convertGroupsToTargetUsers(List<LineGroup> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<LineGroup> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(TargetUser.createInstance(it.next()));
        }
        return arrayList;
    }

    private void getAllFriends() {
        String str = "";
        while (str != null) {
            LineApiResponse<GetFriendsResponse> friends = this.lineApiClient.getFriends(FriendSortField.RELATION, str, true);
            if (!friends.isSuccess()) {
                publishProgress(Collections.emptyList());
                return;
            } else {
                GetFriendsResponse responseData = friends.getResponseData();
                publishProgress(convertFriendsToTargetUsers(responseData.getFriends()));
                str = responseData.getNextPageRequestToken();
            }
        }
    }

    private void getAllGroups() {
        String str = "";
        while (str != null) {
            LineApiResponse<GetGroupsResponse> groups = this.lineApiClient.getGroups(str, true);
            if (!groups.isSuccess()) {
                publishProgress(Collections.emptyList());
                return;
            } else {
                GetGroupsResponse responseData = groups.getResponseData();
                publishProgress(convertGroupsToTargetUsers(responseData.getGroups()));
                str = responseData.getNextPageRequestToken();
            }
        }
    }
}
