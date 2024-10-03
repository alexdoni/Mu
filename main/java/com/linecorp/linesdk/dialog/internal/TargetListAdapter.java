package com.linecorp.linesdk.dialog.internal;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.dialog.internal.TargetListAdapter;
import com.linecorp.linesdk.dialog.internal.TargetUser;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class TargetListAdapter extends RecyclerView.Adapter<TargetViewHolder> {
    private OnSelectedChangeListener externalListener;
    private List<TargetUser> originalTargetList;
    private String queryString = "";
    private OnSelectedChangeListener listener = new OnSelectedChangeListener() { // from class: com.linecorp.linesdk.dialog.internal.TargetListAdapter.2
        @Override // com.linecorp.linesdk.dialog.internal.TargetListAdapter.OnSelectedChangeListener
        public void onSelected(TargetUser targetUser, boolean z) {
            TargetListAdapter.this.externalListener.onSelected(targetUser, z);
        }
    };
    private List<TargetUser> targetList = new ArrayList<TargetUser>() { // from class: com.linecorp.linesdk.dialog.internal.TargetListAdapter.1
        {
            addAll(TargetListAdapter.this.originalTargetList);
        }
    };

    /* loaded from: classes2.dex */
    public interface OnSelectedChangeListener {
        void onSelected(TargetUser targetUser, boolean z);
    }

    /* loaded from: classes2.dex */
    public class TargetViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private int highlightTextColor;
        private ImageView imageView;
        private TextView textView;
        private ViewGroup viewContainer;

        public TargetViewHolder(ViewGroup viewGroup) {
            super(viewGroup);
            this.highlightTextColor = 0;
            this.viewContainer = viewGroup;
            this.textView = (TextView) viewGroup.findViewById(C2028R.id.textView);
            this.imageView = (ImageView) viewGroup.findViewById(C2028R.id.imageView);
            this.checkBox = (CheckBox) viewGroup.findViewById(C2028R.id.checkBox);
            this.highlightTextColor = viewGroup.getResources().getColor(C2028R.color.text_highlight);
        }

        public void bind(final TargetUser targetUser, final OnSelectedChangeListener onSelectedChangeListener) {
            this.viewContainer.setSelected(targetUser.getSelected().booleanValue());
            this.checkBox.setChecked(targetUser.getSelected().booleanValue());
            this.textView.setText(createHighlightTextSpan(targetUser.getDisplayName(), TargetListAdapter.this.queryString));
            this.viewContainer.setOnClickListener(new View.OnClickListener() { // from class: com.linecorp.linesdk.dialog.internal.TargetListAdapter$TargetViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TargetListAdapter.TargetViewHolder.this.m585x72bb9e1d(targetUser, onSelectedChangeListener, view);
                }
            });
            Picasso.get().load(targetUser.getPictureUri()).placeholder(targetUser.getType() == TargetUser.Type.FRIEND ? C2028R.drawable.friend_thumbnail : C2028R.drawable.group_thumbnail).into(this.imageView);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$bind$0$com-linecorp-linesdk-dialog-internal-TargetListAdapter$TargetViewHolder */
        public /* synthetic */ void m585x72bb9e1d(TargetUser targetUser, OnSelectedChangeListener onSelectedChangeListener, View view) {
            boolean z = !targetUser.getSelected().booleanValue();
            this.viewContainer.setSelected(z);
            targetUser.setSelected(Boolean.valueOf(z));
            this.checkBox.setChecked(z);
            onSelectedChangeListener.onSelected(targetUser, z);
        }

        private SpannableString createHighlightTextSpan(String str, String str2) {
            int indexOf;
            SpannableString spannableString = new SpannableString(str);
            if (!str2.isEmpty() && (indexOf = str.toLowerCase().indexOf(str2.toLowerCase())) != -1) {
                spannableString.setSpan(new ForegroundColorSpan(this.highlightTextColor), indexOf, str2.length() + indexOf, 0);
            }
            return spannableString;
        }
    }

    public TargetListAdapter(List<TargetUser> list, OnSelectedChangeListener onSelectedChangeListener) {
        this.originalTargetList = list;
        this.externalListener = onSelectedChangeListener;
    }

    public int filter(String str) {
        this.queryString = str;
        this.targetList.clear();
        if (str.isEmpty()) {
            this.targetList.addAll(this.originalTargetList);
        } else {
            String lowerCase = str.toLowerCase();
            for (TargetUser targetUser : this.originalTargetList) {
                if (targetUser.getDisplayName().toLowerCase().contains(lowerCase)) {
                    this.targetList.add(targetUser);
                }
            }
        }
        notifyDataSetChanged();
        return this.targetList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TargetViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TargetViewHolder((ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(C2028R.layout.layout_target_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(TargetViewHolder targetViewHolder, int i) {
        targetViewHolder.bind(this.targetList.get(i), this.listener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.targetList.size();
    }

    public void unSelect(TargetUser targetUser) {
        for (int i = 0; i < this.targetList.size(); i++) {
            TargetUser targetUser2 = this.targetList.get(i);
            if (targetUser2.getId().equals(targetUser.getId())) {
                targetUser2.setSelected(false);
                notifyItemChanged(i);
                return;
            }
        }
    }

    public void addAll(List<TargetUser> list) {
        int size = this.targetList.size() - 1;
        this.originalTargetList.addAll(list);
        this.targetList.addAll(list);
        notifyItemRangeInserted(size, list.size());
    }
}
