package com.linecorp.linesdk.dialog.internal;

import android.os.AsyncTask;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.SendMessageResponse;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.message.MessageData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SendMessageTask extends AsyncTask<List<TargetUser>, Void, LineApiResponse<List<SendMessageResponse>>> {
    private ApiStatusListener apiStatusListener;
    private LineApiClient lineApiClient;
    private List<MessageData> messages;

    SendMessageTask(LineApiClient lineApiClient, List<MessageData> list) {
        this(lineApiClient, list, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SendMessageTask(LineApiClient lineApiClient, List<MessageData> list, ApiStatusListener apiStatusListener) {
        this.lineApiClient = lineApiClient;
        this.messages = list;
        this.apiStatusListener = apiStatusListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public LineApiResponse<List<SendMessageResponse>> doInBackground(List<TargetUser>... listArr) {
        ArrayList arrayList = new ArrayList();
        Iterator<TargetUser> it = listArr[0].iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getId());
        }
        return this.lineApiClient.sendMessageToMultipleUsers(arrayList, this.messages, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(LineApiResponse<List<SendMessageResponse>> lineApiResponse) {
        if (this.apiStatusListener != null) {
            if (lineApiResponse.isSuccess()) {
                this.apiStatusListener.onSuccess();
            } else {
                this.apiStatusListener.onFailure();
            }
        }
    }
}
