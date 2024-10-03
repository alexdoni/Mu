package com.linecorp.linesdk.dialog.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.linecorp.linesdk.C2028R;
import com.linecorp.linesdk.dialog.internal.TargetListAdapter;
import java.util.List;

/* loaded from: classes2.dex */
public class TargetListWithSearchView extends ConstraintLayout {
    private AppCompatTextView emptyView;
    private TargetListAdapter.OnSelectedChangeListener listener;
    private int noMembersResId;
    private RecyclerView recyclerView;
    private SearchView searchView;

    public TargetListWithSearchView(Context context, int i, TargetListAdapter.OnSelectedChangeListener onSelectedChangeListener) {
        super(context);
        this.noMembersResId = i;
        this.listener = onSelectedChangeListener;
        init();
    }

    public TargetListWithSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TargetListWithSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void addTargetUsers(List<TargetUser> list) {
        TargetListAdapter targetListAdapter = (TargetListAdapter) this.recyclerView.getAdapter();
        if (targetListAdapter == null) {
            this.recyclerView.setAdapter(new TargetListAdapter(list, this.listener));
        } else {
            targetListAdapter.addAll(list);
        }
        if (this.recyclerView.getAdapter().getItemCount() == 0) {
            this.emptyView.setText(this.noMembersResId);
            this.emptyView.setVisibility(0);
        }
    }

    public void unSelect(TargetUser targetUser) {
        TargetListAdapter targetListAdapter = (TargetListAdapter) this.recyclerView.getAdapter();
        if (targetListAdapter == null) {
            return;
        }
        targetListAdapter.unSelect(targetUser);
    }

    private void init() {
        View inflate = inflate(getContext(), C2028R.layout.layout_select_target, this);
        this.recyclerView = (RecyclerView) inflate.findViewById(C2028R.id.recyclerView);
        this.searchView = (SearchView) inflate.findViewById(C2028R.id.searchView);
        this.emptyView = (AppCompatTextView) inflate.findViewById(C2028R.id.emptyView);
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.linecorp.linesdk.dialog.internal.TargetListWithSearchView.1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                searchText(str);
                TargetListWithSearchView.this.searchView.clearFocus();
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                searchText(str);
                return true;
            }

            private void searchText(String str) {
                TargetListAdapter targetListAdapter = (TargetListAdapter) TargetListWithSearchView.this.recyclerView.getAdapter();
                if (targetListAdapter != null) {
                    if (targetListAdapter.filter(str) == 0) {
                        TargetListWithSearchView.this.emptyView.setVisibility(0);
                        if (!str.isEmpty()) {
                            TargetListWithSearchView.this.emptyView.setText(C2028R.string.search_no_results);
                            return;
                        } else {
                            TargetListWithSearchView.this.emptyView.setText(TargetListWithSearchView.this.noMembersResId);
                            return;
                        }
                    }
                    TargetListWithSearchView.this.emptyView.setVisibility(4);
                }
            }
        });
    }
}
