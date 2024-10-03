package com.linecorp.linesdk.dialog.internal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.dialog.internal.GetTargetUserTask;
import com.linecorp.linesdk.dialog.internal.TargetListAdapter;
import com.linecorp.linesdk.dialog.internal.TargetUser;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class SendMessageTargetPagerAdapter extends PagerAdapter {
    private Context context;
    private TargetListAdapter.OnSelectedChangeListener listener;
    private SendMessagePresenter presenter;
    private HashMap<TargetUser.Type, TargetListWithSearchView> viewHashMap = new HashMap<>();

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public SendMessageTargetPagerAdapter(Context context, SendMessagePresenter sendMessagePresenter, TargetListAdapter.OnSelectedChangeListener onSelectedChangeListener) {
        this.context = context;
        this.presenter = sendMessagePresenter;
        this.listener = onSelectedChangeListener;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return TargetUser.getTargetTypeCount();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public View instantiateItem(ViewGroup viewGroup, int i) {
        final TargetListWithSearchView targetListWithSearchView;
        TargetUser.Type type = TargetUser.Type.values()[i];
        int i2 = C20371.$SwitchMap$com$linecorp$linesdk$dialog$internal$TargetUser$Type[type.ordinal()];
        if (i2 == 1) {
            targetListWithSearchView = new TargetListWithSearchView(this.context, C2028R.string.search_no_fiend, this.listener);
            this.presenter.getFriends(new GetTargetUserTask.NextAction() { // from class: com.linecorp.linesdk.dialog.internal.SendMessageTargetPagerAdapter$$ExternalSyntheticLambda0
                @Override // com.linecorp.linesdk.dialog.internal.GetTargetUserTask.NextAction
                public final void run(List list) {
                    TargetListWithSearchView.this.addTargetUsers(list);
                }
            });
        } else {
            if (i2 != 2) {
                return null;
            }
            targetListWithSearchView = new TargetListWithSearchView(this.context, C2028R.string.search_no_group, this.listener);
            this.presenter.getGroups(new GetTargetUserTask.NextAction() { // from class: com.linecorp.linesdk.dialog.internal.SendMessageTargetPagerAdapter$$ExternalSyntheticLambda0
                @Override // com.linecorp.linesdk.dialog.internal.GetTargetUserTask.NextAction
                public final void run(List list) {
                    TargetListWithSearchView.this.addTargetUsers(list);
                }
            });
        }
        this.viewHashMap.put(type, targetListWithSearchView);
        viewGroup.addView(targetListWithSearchView);
        return targetListWithSearchView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.linecorp.linesdk.dialog.internal.SendMessageTargetPagerAdapter$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C20371 {
        static final /* synthetic */ int[] $SwitchMap$com$linecorp$linesdk$dialog$internal$TargetUser$Type;

        static {
            int[] iArr = new int[TargetUser.Type.values().length];
            $SwitchMap$com$linecorp$linesdk$dialog$internal$TargetUser$Type = iArr;
            try {
                iArr[TargetUser.Type.FRIEND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$linecorp$linesdk$dialog$internal$TargetUser$Type[TargetUser.Type.GROUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        int i2 = C20371.$SwitchMap$com$linecorp$linesdk$dialog$internal$TargetUser$Type[TargetUser.Type.values()[i].ordinal()];
        if (i2 != 1) {
            return i2 != 2 ? "" : this.context.getString(C2028R.string.select_tab_groups);
        }
        return this.context.getString(C2028R.string.select_tab_friends);
    }

    public void unSelect(TargetUser targetUser) {
        this.viewHashMap.get(targetUser.getType()).unSelect(targetUser);
    }
}
