package com.linecorp.linesdk.dialog.internal;

import android.os.AsyncTask;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.dialog.internal.GetTargetUserTask;
import com.linecorp.linesdk.dialog.internal.SendMessageContract;
import com.linecorp.linesdk.dialog.internal.TargetListAdapter;
import com.linecorp.linesdk.dialog.internal.TargetUser;
import com.linecorp.linesdk.message.MessageData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SendMessagePresenter implements SendMessageContract.Presenter, TargetListAdapter.OnSelectedChangeListener {
    private static final int MAX_TARGET_SIZE = 10;
    private LineApiClient lineApiClient;
    private SendMessageContract.View view;
    private List<TargetUser> targetUserList = new ArrayList();
    private List<AsyncTask> asyncTaskList = new ArrayList();
    private ApiStatusListener apiStatusListener = new ApiStatusListener() { // from class: com.linecorp.linesdk.dialog.internal.SendMessagePresenter.1
        @Override // com.linecorp.linesdk.dialog.internal.ApiStatusListener
        public void onSuccess() {
            SendMessagePresenter.this.view.onSendMessageSuccess();
        }

        @Override // com.linecorp.linesdk.dialog.internal.ApiStatusListener
        public void onFailure() {
            SendMessagePresenter.this.view.onSendMessageFailure();
        }
    };

    public SendMessagePresenter(LineApiClient lineApiClient, SendMessageContract.View view) {
        this.lineApiClient = lineApiClient;
        this.view = view;
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public void removeTargetUser(TargetUser targetUser) {
        this.targetUserList.remove(targetUser);
        this.view.onTargetUserRemoved(targetUser);
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public void addTargetUser(TargetUser targetUser) {
        this.targetUserList.add(targetUser);
        this.view.onTargetUserAdded(targetUser);
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public void sendMessage(MessageData messageData) {
        SendMessageTask sendMessageTask = new SendMessageTask(this.lineApiClient, new ArrayList<MessageData>(messageData) { // from class: com.linecorp.linesdk.dialog.internal.SendMessagePresenter.2
            final /* synthetic */ MessageData val$messageData;

            {
                this.val$messageData = messageData;
                add(messageData);
            }
        }, this.apiStatusListener);
        this.asyncTaskList.add(sendMessageTask);
        sendMessageTask.execute(this.targetUserList);
    }

    @Override // com.linecorp.linesdk.dialog.internal.TargetListAdapter.OnSelectedChangeListener
    public void onSelected(TargetUser targetUser, boolean z) {
        if (z) {
            if (this.targetUserList.size() < 10) {
                addTargetUser(targetUser);
                return;
            } else {
                this.view.onTargetUserRemoved(targetUser);
                this.view.onExceedMaxTargetUserCount(10);
                return;
            }
        }
        removeTargetUser(targetUser);
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public int getTargetUserListSize() {
        return this.targetUserList.size();
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public void release() {
        Iterator<AsyncTask> it = this.asyncTaskList.iterator();
        while (it.hasNext()) {
            it.next().cancel(true);
        }
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public void getFriends(GetTargetUserTask.NextAction nextAction) {
        getTargets(TargetUser.Type.FRIEND, nextAction);
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.Presenter
    public void getGroups(GetTargetUserTask.NextAction nextAction) {
        getTargets(TargetUser.Type.GROUP, nextAction);
    }

    private void getTargets(TargetUser.Type type, GetTargetUserTask.NextAction nextAction) {
        GetTargetUserTask getTargetUserTask = new GetTargetUserTask(type, this.lineApiClient, nextAction);
        getTargetUserTask.execute(new Void[0]);
        this.asyncTaskList.add(getTargetUserTask);
    }
}
