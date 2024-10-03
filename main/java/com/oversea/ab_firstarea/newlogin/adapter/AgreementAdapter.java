package com.oversea.ab_firstarea.newlogin.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.newlogin.bean.AgreementBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.List;

/* loaded from: classes.dex */
public class AgreementAdapter extends BaseExpandableListAdapter {
    private CheckBoxListener checkBoxListener;
    private Context context;
    private List<AgreementBean> dataEntity;

    /* loaded from: classes.dex */
    public interface CheckBoxListener {
        void checkStateListener(int i, int i2, boolean z);

        void checkStateListener(int i, boolean z);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public AgreementAdapter(Context context, List<AgreementBean> list) {
        this.context = context;
        this.dataEntity = list;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<AgreementBean> list = this.dataEntity;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (this.dataEntity.get(i).getChildren() == null) {
            return 0;
        }
        return this.dataEntity.get(i).getChildren().size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.dataEntity.get(i) == null ? "" : this.dataEntity.get(i).getAgreement();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        if (this.dataEntity.get(i).getChildren() == null) {
            return null;
        }
        return this.dataEntity.get(i).getChildren().get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(final int i, boolean z, View view, ViewGroup viewGroup) {
        final ParentHolder parentHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(Util.getIdByName(this.context, "layout", "drhw_xagreement_item_parent"), (ViewGroup) null);
            parentHolder = new ParentHolder();
            parentHolder.tvParent = (TextView) view.findViewById(Util.getIdByName(this.context, "id", "tv_item_agreement_parent"));
            parentHolder.checkBox = (CheckBox) view.findViewById(Util.getIdByName(this.context, "id", "cb_item_agreement_parent"));
            view.setTag(parentHolder);
        } else {
            parentHolder = (ParentHolder) view.getTag();
        }
        final AgreementBean agreementBean = this.dataEntity.get(i);
        parentHolder.tvParent.setText(agreementBean.getAgreement());
        try {
            parentHolder.tvParent.getPaint().setFlags(8);
            parentHolder.tvParent.getPaint().setAntiAlias(true);
        } catch (Exception unused) {
        }
        parentHolder.tvParent.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if ("Personalized Advertising".equals(parentHolder.tvParent.getText())) {
                    Lxhw_DialogManage.getInstance().showGdprDialog(AgreementAdapter.this.context, null);
                } else {
                    if (TextUtils.isEmpty(agreementBean.getUrl())) {
                        return;
                    }
                    Lxhw_DialogManage.getInstance().showWebCommon(AgreementAdapter.this.context, agreementBean.getUrl(), "");
                }
            }
        });
        parentHolder.checkBox.setChecked(this.dataEntity.get(i).isChecked());
        parentHolder.checkBox.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                boolean isChecked = ((CheckBox) view2).isChecked();
                ((AgreementBean) AgreementAdapter.this.dataEntity.get(i)).setChecked(isChecked);
                AgreementAdapter.this.checkBoxListener.checkStateListener(i, isChecked);
            }
        });
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(final int i, final int i2, boolean z, View view, ViewGroup viewGroup) {
        final ChildrenHolder childrenHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(Util.getIdByName(this.context, "layout", "drhw_xagreement_item_child"), (ViewGroup) null);
            childrenHolder = new ChildrenHolder();
            childrenHolder.tvChild = (TextView) view.findViewById(Util.getIdByName(this.context, "id", "tv_item_agreement_child"));
            childrenHolder.checkBox = (CheckBox) view.findViewById(Util.getIdByName(this.context, "id", "cb_item_agreement_child"));
            view.setTag(childrenHolder);
        } else {
            childrenHolder = (ChildrenHolder) view.getTag();
        }
        final AgreementBean.Children children = this.dataEntity.get(i).getChildren().get(i2);
        childrenHolder.tvChild.setText(children.getAgreement());
        try {
            childrenHolder.tvChild.getPaint().setFlags(8);
            childrenHolder.tvChild.getPaint().setAntiAlias(true);
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(children.getUrl())) {
            childrenHolder.tvChild.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Lxhw_DialogManage.getInstance().showWebCommon(AgreementAdapter.this.context, children.getUrl(), "");
                }
            });
        }
        childrenHolder.checkBox.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.newlogin.adapter.AgreementAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                boolean isChecked = ((CheckBox) view2).isChecked();
                ((AgreementBean) AgreementAdapter.this.dataEntity.get(i)).getChildren().get(i2).setChecked(isChecked);
                childrenHolder.checkBox.setChecked(isChecked);
                LLog.i_noControl("groupPosition:" + i, "childPosition:" + i2 + " isChecked:" + isChecked);
                AgreementAdapter.this.checkBoxListener.checkStateListener(i, i2, isChecked);
            }
        });
        childrenHolder.checkBox.setChecked(this.dataEntity.get(i).getChildren().get(i2).isChecked());
        return view;
    }

    /* loaded from: classes.dex */
    class ParentHolder {
        CheckBox checkBox;
        TextView tvParent;

        ParentHolder() {
        }
    }

    /* loaded from: classes.dex */
    class ChildrenHolder {
        CheckBox checkBox;
        TextView tvChild;

        ChildrenHolder() {
        }
    }

    public void setCheckBoxListener(CheckBoxListener checkBoxListener) {
        this.checkBoxListener = checkBoxListener;
    }

    public CheckBoxListener getCheckBoxListener() {
        return this.checkBoxListener;
    }

    public void reFreshData(List<AgreementBean> list) {
        this.dataEntity = list;
        notifyDataSetChanged();
    }
}
