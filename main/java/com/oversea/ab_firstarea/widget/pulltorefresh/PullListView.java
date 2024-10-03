package com.oversea.ab_firstarea.widget.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Scroller;

/* loaded from: classes.dex */
public class PullListView extends ListView implements AbsListView.OnScrollListener {
    private static final float OFFSET_RADIO = 1.8f;
    private static final int SCROLLBACK_FOOTER = 1;
    private static final int SCROLLBACK_HEADER = 0;
    private static final int SCROLL_DURATION = 200;
    private int count;
    private ListAdapter mAdapter;
    private boolean mEnablePullLoad;
    private boolean mEnablePullRefresh;
    private PullListViewFooter mFooterView;
    private int mFooterViewHeight;
    private PullListViewHeader mHeaderView;
    private int mHeaderViewHeight;
    private boolean mIsFooterReady;
    private float mLastY;
    private PullOnListViewListener mListViewListener;
    private boolean mPullLoading;
    private boolean mPullRefreshing;
    private int mScrollBack;
    private Scroller mScroller;
    private int mTotalItemCount;

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public PullListView(Context context) {
        super(context);
        this.mLastY = -1.0f;
        this.mEnablePullRefresh = true;
        this.mEnablePullLoad = true;
        this.mPullRefreshing = false;
        this.mIsFooterReady = false;
        this.mAdapter = null;
        this.count = 0;
        initView(context);
    }

    public PullListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastY = -1.0f;
        this.mEnablePullRefresh = true;
        this.mEnablePullLoad = true;
        this.mPullRefreshing = false;
        this.mIsFooterReady = false;
        this.mAdapter = null;
        this.count = 0;
        initView(context);
    }

    private void initView(Context context) {
        this.mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        PullListViewHeader pullListViewHeader = new PullListViewHeader(context);
        this.mHeaderView = pullListViewHeader;
        this.mHeaderViewHeight = pullListViewHeader.getHeaderHeight();
        this.mHeaderView.setGravity(80);
        addHeaderView(this.mHeaderView);
        PullListViewFooter pullListViewFooter = new PullListViewFooter(context);
        this.mFooterView = pullListViewFooter;
        this.mFooterViewHeight = pullListViewFooter.getFooterHeight();
        setPullRefreshEnable(true);
        setPullLoadEnable(true);
        this.mFooterView.hide();
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        if (!this.mIsFooterReady) {
            this.mIsFooterReady = true;
            this.mFooterView.setGravity(48);
            addFooterView(this.mFooterView);
        }
        super.setAdapter(listAdapter);
    }

    public void setPullRefreshEnable(boolean z) {
        this.mEnablePullRefresh = z;
        if (!z) {
            this.mHeaderView.setVisibility(4);
        } else {
            this.mHeaderView.setVisibility(0);
        }
    }

    public void setPullLoadEnable(boolean z) {
        this.mEnablePullLoad = z;
        if (!z) {
            this.mFooterView.hide();
            this.mFooterView.setOnClickListener(null);
        } else {
            this.mPullLoading = false;
            this.mFooterView.setState(1);
            this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.widget.pulltorefresh.PullListView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PullListView.this.startLoadMore();
                }
            });
        }
    }

    public void stopRefresh() {
        if (this.mPullRefreshing) {
            this.mPullRefreshing = false;
            resetHeaderHeight();
        }
        int count = this.mAdapter.getCount();
        this.count = count;
        if (count > 0) {
            this.mFooterView.setState(1);
        } else {
            this.mFooterView.setState(4);
        }
    }

    private void updateHeaderHeight(float f) {
        this.mHeaderView.setVisiableHeight(((int) f) + this.mHeaderView.getVisiableHeight());
        if (this.mEnablePullRefresh && !this.mPullRefreshing) {
            if (this.mHeaderView.getVisiableHeight() >= this.mHeaderViewHeight) {
                this.mHeaderView.setState(1);
            } else {
                this.mHeaderView.setState(0);
            }
        }
        setSelection(0);
    }

    private void resetHeaderHeight() {
        boolean z;
        int visiableHeight = this.mHeaderView.getVisiableHeight();
        int i = this.mHeaderViewHeight;
        if (visiableHeight < i || !(z = this.mPullRefreshing)) {
            this.mScrollBack = 0;
            this.mScroller.startScroll(0, visiableHeight, 0, visiableHeight * (-1), 200);
        } else if (visiableHeight > i || !z) {
            this.mScrollBack = 0;
            this.mScroller.startScroll(0, visiableHeight, 0, -(visiableHeight - i), 200);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoadMore() {
        this.mFooterView.show();
        this.mPullLoading = true;
        this.mFooterView.setState(2);
        PullOnListViewListener pullOnListViewListener = this.mListViewListener;
        if (pullOnListViewListener != null) {
            pullOnListViewListener.onLoadMore();
        }
    }

    public void stopLoadMore() {
        this.mFooterView.hide();
        this.mPullLoading = false;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null) {
            return;
        }
        if (listAdapter.getCount() > this.count) {
            this.mFooterView.setState(1);
        } else {
            this.mFooterView.setState(3);
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mLastY == -1.0f) {
            this.mLastY = motionEvent.getRawY();
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastY = motionEvent.getRawY();
        } else if (action == 1) {
            this.mLastY = -1.0f;
            if (getFirstVisiblePosition() == 0) {
                if (this.mEnablePullRefresh && this.mHeaderView.getVisiableHeight() >= this.mHeaderViewHeight) {
                    this.mPullRefreshing = true;
                    this.mHeaderView.setState(2);
                    PullOnListViewListener pullOnListViewListener = this.mListViewListener;
                    if (pullOnListViewListener != null) {
                        pullOnListViewListener.onRefresh();
                    }
                }
                if (this.mEnablePullRefresh) {
                    resetHeaderHeight();
                }
            }
        } else if (action == 2) {
            float rawY = motionEvent.getRawY() - this.mLastY;
            this.mLastY = motionEvent.getRawY();
            if (this.mEnablePullRefresh && getFirstVisiblePosition() == 0 && (this.mHeaderView.getVisiableHeight() > 0 || rawY > 0.0f)) {
                updateHeaderHeight(rawY / OFFSET_RADIO);
            } else if (this.mEnablePullLoad && !this.mPullLoading && getLastVisiblePosition() == this.mTotalItemCount - 1 && rawY < 0.0f) {
                startLoadMore();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScrollBack == 0) {
                this.mHeaderView.setVisiableHeight(this.mScroller.getCurrY());
            }
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setOnListViewListener(PullOnListViewListener pullOnListViewListener) {
        this.mListViewListener = pullOnListViewListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.mTotalItemCount = i3;
    }

    public PullListViewHeader getHeaderView() {
        return this.mHeaderView;
    }

    public PullListViewFooter getFooterView() {
        return this.mFooterView;
    }

    public ProgressBar getHeaderProgressBar() {
        return this.mHeaderView.getHeaderProgressBar();
    }

    public ProgressBar getFooterProgressBar() {
        return this.mFooterView.getFooterProgressBar();
    }
}
