package com.linecorp.linesdk.dialog;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDialog;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.dialog.internal.SendMessageContract;
import com.linecorp.linesdk.dialog.internal.SendMessagePresenter;
import com.linecorp.linesdk.dialog.internal.SendMessageTargetPagerAdapter;
import com.linecorp.linesdk.dialog.internal.TargetUser;
import com.linecorp.linesdk.dialog.internal.UserThumbnailView;
import com.linecorp.linesdk.message.MessageData;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class SendMessageDialog extends AppCompatDialog implements SendMessageContract.View {
    private Button buttonConfirm;
    private View.OnClickListener confirmClickListener;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout linearLayoutTargetUser;
    private MessageData messageData;
    private OnSendListener onSendListener;
    private SendMessagePresenter presenter;
    private SendMessageTargetPagerAdapter sendMessageTargetAdapter;
    private TabLayout tabLayout;
    private Map<String, View> targetUserViewCacheMap;
    private ViewPager viewPager;

    /* loaded from: classes2.dex */
    public interface OnSendListener {
        void onSendFailure(DialogInterface dialogInterface);

        void onSendSuccess(DialogInterface dialogInterface);
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }

    public SendMessageDialog(Context context, LineApiClient lineApiClient) {
        super(context, C2028R.style.DialogTheme);
        this.targetUserViewCacheMap = new HashMap();
        this.layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.confirmClickListener = new View.OnClickListener() { // from class: com.linecorp.linesdk.dialog.SendMessageDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendMessageDialog.this.m1872lambda$new$1$comlinecorplinesdkdialogSendMessageDialog(view);
            }
        };
        this.presenter = new SendMessagePresenter(lineApiClient, this);
        SendMessagePresenter sendMessagePresenter = this.presenter;
        this.sendMessageTargetAdapter = new SendMessageTargetPagerAdapter(context, sendMessagePresenter, sendMessagePresenter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(getContext()).inflate(C2028R.layout.dialog_send_message, (ViewGroup) null);
        setContentView(inflate);
        this.viewPager = (ViewPager) inflate.findViewById(C2028R.id.viewPager);
        this.tabLayout = (TabLayout) inflate.findViewById(C2028R.id.tabLayout);
        this.buttonConfirm = (Button) inflate.findViewById(C2028R.id.buttonConfirm);
        this.linearLayoutTargetUser = (LinearLayout) inflate.findViewById(C2028R.id.linearLayoutTargetUserList);
        this.horizontalScrollView = (HorizontalScrollView) inflate.findViewById(C2028R.id.horizontalScrollView);
        setupUi();
    }

    private void setupUi() {
        this.viewPager.setAdapter(this.sendMessageTargetAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.buttonConfirm.setOnClickListener(this.confirmClickListener);
        this.viewPager.post(new Runnable() { // from class: com.linecorp.linesdk.dialog.SendMessageDialog$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                SendMessageDialog.this.m1873lambda$setupUi$0$comlinecorplinesdkdialogSendMessageDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupUi$0$com-linecorp-linesdk-dialog-SendMessageDialog, reason: not valid java name */
    public /* synthetic */ void m1873lambda$setupUi$0$comlinecorplinesdkdialogSendMessageDialog() {
        getWindow().clearFlags(131080);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-linecorp-linesdk-dialog-SendMessageDialog, reason: not valid java name */
    public /* synthetic */ void m1872lambda$new$1$comlinecorplinesdkdialogSendMessageDialog(View view) {
        this.presenter.sendMessage(this.messageData);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.presenter.release();
        super.dismiss();
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.View
    public void onTargetUserRemoved(TargetUser targetUser) {
        this.linearLayoutTargetUser.removeView(this.targetUserViewCacheMap.get(targetUser.getId()));
        this.sendMessageTargetAdapter.unSelect(targetUser);
        updateConfirmButtonLabel();
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.View
    public void onTargetUserAdded(TargetUser targetUser) {
        if (this.targetUserViewCacheMap.get(targetUser.getId()) == null) {
            this.targetUserViewCacheMap.put(targetUser.getId(), createUserThumbnailView(targetUser));
        }
        this.linearLayoutTargetUser.addView(this.targetUserViewCacheMap.get(targetUser.getId()), this.layoutParams);
        this.horizontalScrollView.post(new Runnable() { // from class: com.linecorp.linesdk.dialog.SendMessageDialog$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SendMessageDialog.this.m584xe300859e();
            }
        });
        updateConfirmButtonLabel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onTargetUserAdded$2$com-linecorp-linesdk-dialog-SendMessageDialog */
    public /* synthetic */ void m584xe300859e() {
        this.horizontalScrollView.fullScroll(66);
    }

    private UserThumbnailView createUserThumbnailView(final TargetUser targetUser) {
        UserThumbnailView userThumbnailView = new UserThumbnailView(getContext());
        userThumbnailView.setOnClickListener(new View.OnClickListener() { // from class: com.linecorp.linesdk.dialog.SendMessageDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SendMessageDialog.this.m583x279e2024(targetUser, view);
            }
        });
        userThumbnailView.setTargetUser(targetUser);
        return userThumbnailView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createUserThumbnailView$3$com-linecorp-linesdk-dialog-SendMessageDialog */
    public /* synthetic */ void m583x279e2024(TargetUser targetUser, View view) {
        this.presenter.removeTargetUser(targetUser);
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.View
    public void onExceedMaxTargetUserCount(int i) {
        Toast.makeText(getContext(), String.format(Locale.getDefault(), "You can only select up to %1$d.", Integer.valueOf(i)), 1).show();
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.View
    public void onSendMessageSuccess() {
        OnSendListener onSendListener = this.onSendListener;
        if (onSendListener != null) {
            onSendListener.onSendSuccess(this);
        }
        dismiss();
    }

    @Override // com.linecorp.linesdk.dialog.internal.SendMessageContract.View
    public void onSendMessageFailure() {
        OnSendListener onSendListener = this.onSendListener;
        if (onSendListener != null) {
            onSendListener.onSendFailure(this);
        }
        dismiss();
    }

    public void setOnSendListener(OnSendListener onSendListener) {
        if (this.onSendListener != null) {
            throw new IllegalStateException("OnSendListener is already taken and can not be replaced.");
        }
        this.onSendListener = onSendListener;
    }

    private void updateConfirmButtonLabel() {
        int targetUserListSize = this.presenter.getTargetUserListSize();
        if (targetUserListSize == 0) {
            this.buttonConfirm.setText(R.string.ok);
            this.buttonConfirm.setVisibility(8);
            return;
        }
        this.buttonConfirm.setText(getContext().getString(R.string.ok) + " (" + targetUserListSize + ")");
        this.buttonConfirm.setVisibility(0);
    }
}
