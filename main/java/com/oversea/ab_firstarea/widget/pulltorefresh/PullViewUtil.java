package com.oversea.ab_firstarea.widget.pulltorefresh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/* loaded from: classes.dex */
public class PullViewUtil {
    public static void setAbsListViewHeight(AbsListView absListView, int i, int i2) {
        int absListViewHeight = getAbsListViewHeight(absListView, i, i2);
        ViewGroup.LayoutParams layoutParams = absListView.getLayoutParams();
        layoutParams.height = absListViewHeight;
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(0, 0, 0, 0);
        absListView.setLayoutParams(layoutParams);
    }

    public static int getAbsListViewHeight(AbsListView absListView, int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        absListView.measure(makeMeasureSpec, makeMeasureSpec2);
        ListAdapter listAdapter = (ListAdapter) absListView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int count = listAdapter.getCount();
        if (absListView instanceof ListView) {
            int i3 = 0;
            for (int i4 = 0; i4 < count; i4++) {
                View view = listAdapter.getView(i4, null, absListView);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i3 += view.getMeasuredHeight();
            }
            return count == 0 ? i2 : i3 + (((ListView) absListView).getDividerHeight() * (count - 1));
        }
        if (!(absListView instanceof GridView)) {
            return 0;
        }
        int i5 = count % i;
        if (i5 > 0) {
            i5 = 1;
        }
        if (listAdapter.getCount() == 0) {
            return i2;
        }
        View view2 = listAdapter.getView(0, null, absListView);
        view2.measure(makeMeasureSpec, makeMeasureSpec2);
        int i6 = (count / i) + i5;
        return (view2.getMeasuredHeight() * i6) + ((i6 - 1) * i2);
    }

    public static void measureView(View view) {
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public static int resizeTextSize(int i, int i2, int i3) {
        float f;
        try {
            f = Math.min(i / 480.0f, i2 / 800.0f);
        } catch (Exception unused) {
            f = 1.0f;
        }
        return Math.round(i3 * f);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
