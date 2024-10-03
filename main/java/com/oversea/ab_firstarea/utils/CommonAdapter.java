package com.oversea.ab_firstarea.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private static final String TAG = "zxt";
    private int layoutId;
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public abstract void convert(ViewHolder viewHolder, T t, int i, View view);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public CommonAdapter(Context context, List<T> list, int i) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = list;
        this.layoutId = i;
    }

    public CommonAdapter(Context context, int i) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutId = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.mDatas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        return this.mDatas.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = ViewHolder.get(this.mContext, view, viewGroup, this.layoutId, i);
        Log.d(TAG, "getView() called with: position = [" + i + "], convertView = [" + view + "], parent = [" + viewGroup + "]");
        convert(viewHolder, getItem(i), i, view);
        return viewHolder.getConvertView();
    }

    public void setDatas(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void remove(int i) {
        List<T> list = this.mDatas;
        if (list == null || list.size() <= i || i <= -1) {
            return;
        }
        this.mDatas.remove(i);
        notifyDataSetChanged();
    }

    public void removeAll() {
        List<T> list = this.mDatas;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mDatas.clear();
        notifyDataSetChanged();
    }

    public void addDatas(List<T> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list);
            List<T> list2 = this.mDatas;
            if (list2 != null) {
                list2.addAll(arrayList);
            } else {
                this.mDatas = arrayList;
            }
            notifyDataSetChanged();
        }
    }

    public List<T> getDatas() {
        return this.mDatas;
    }
}
