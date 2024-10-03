package com.linecorp.linesdk.dialog.internal;

import com.linecorp.linesdk.dialog.internal.GetTargetUserTask;
import com.linecorp.linesdk.message.MessageData;

/* loaded from: classes2.dex */
public interface SendMessageContract {

    /* loaded from: classes2.dex */
    public interface Presenter {
        void addTargetUser(TargetUser targetUser);

        void getFriends(GetTargetUserTask.NextAction nextAction);

        void getGroups(GetTargetUserTask.NextAction nextAction);

        int getTargetUserListSize();

        void release();

        void removeTargetUser(TargetUser targetUser);

        void sendMessage(MessageData messageData);
    }

    /* loaded from: classes2.dex */
    public interface View {
        void onExceedMaxTargetUserCount(int i);

        void onSendMessageFailure();

        void onSendMessageSuccess();

        void onTargetUserAdded(TargetUser targetUser);

        void onTargetUserRemoved(TargetUser targetUser);
    }
}
