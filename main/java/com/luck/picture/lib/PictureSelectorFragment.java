package com.luck.picture.lib;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.luck.picture.lib.adapter.PictureImageGridAdapter;
import com.luck.picture.lib.animators.AlphaInAnimationAdapter;
import com.luck.picture.lib.animators.SlideInBottomAnimationAdapter;
import com.luck.picture.lib.basic.IPictureSelectorEvent;
import com.luck.picture.lib.basic.PictureCommonFragment;
import com.luck.picture.lib.config.InjectResourceSource;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.dialog.AlbumListPopWindow;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.interfaces.OnAlbumItemClickListener;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.interfaces.OnRecyclerViewPreloadMoreListener;
import com.luck.picture.lib.interfaces.OnRecyclerViewScrollListener;
import com.luck.picture.lib.interfaces.OnRecyclerViewScrollStateListener;
import com.luck.picture.lib.interfaces.OnRequestPermissionListener;
import com.luck.picture.lib.loader.IBridgeMediaLoader;
import com.luck.picture.lib.loader.LocalMediaLoader;
import com.luck.picture.lib.loader.LocalMediaPageLoader;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.luck.picture.lib.permissions.PermissionResultCallback;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import com.luck.picture.lib.utils.AnimUtils;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.utils.DoubleUtils;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.utils.ToastUtils;
import com.luck.picture.lib.utils.ValueOf;
import com.luck.picture.lib.widget.BottomNavBar;
import com.luck.picture.lib.widget.CompleteSelectView;
import com.luck.picture.lib.widget.RecyclerPreloadView;
import com.luck.picture.lib.widget.SlideSelectTouchListener;
import com.luck.picture.lib.widget.SlideSelectionHandler;
import com.luck.picture.lib.widget.TitleBar;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class PictureSelectorFragment extends PictureCommonFragment implements OnRecyclerViewPreloadMoreListener, IPictureSelectorEvent {
    private static final Object LOCK = new Object();
    private static int SELECT_ANIM_DURATION = 135;
    public static final String TAG = "PictureSelectorFragment";
    private AlbumListPopWindow albumListPopWindow;
    private int allFolderSize;
    private BottomNavBar bottomNarBar;
    private CompleteSelectView completeSelectView;
    private boolean isCameraCallback;
    private boolean isDisplayCamera;
    private boolean isMemoryRecycling;
    private PictureImageGridAdapter mAdapter;
    private SlideSelectTouchListener mDragSelectTouchListener;
    private RecyclerPreloadView mRecycler;
    private TitleBar titleBar;
    private TextView tvCurrentDataTime;
    private TextView tvDataEmpty;
    private long intervalClickTime = 0;
    private int currentPosition = -1;

    public static PictureSelectorFragment newInstance() {
        PictureSelectorFragment pictureSelectorFragment = new PictureSelectorFragment();
        pictureSelectorFragment.setArguments(new Bundle());
        return pictureSelectorFragment;
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment
    public String getFragmentTag() {
        return TAG;
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public int getResourceId() {
        int layoutResource = InjectResourceSource.getLayoutResource(getContext(), 1, this.selectorConfig);
        return layoutResource != 0 ? layoutResource : C2140R.layout.ps_fragment_selector;
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void onSelectedChange(boolean z, LocalMedia localMedia) {
        this.bottomNarBar.setSelectedChange();
        this.completeSelectView.setSelectedChange(false);
        if (checkNotifyStrategy(z)) {
            this.mAdapter.notifyItemPositionChanged(localMedia.position);
            this.mRecycler.postDelayed(new Runnable() { // from class: com.luck.picture.lib.PictureSelectorFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    PictureSelectorFragment.this.mAdapter.notifyDataSetChanged();
                }
            }, SELECT_ANIM_DURATION);
        } else {
            this.mAdapter.notifyItemPositionChanged(localMedia.position);
        }
        if (z) {
            return;
        }
        sendChangeSubSelectPositionEvent(true);
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void onFixedSelectedChange(LocalMedia localMedia) {
        this.mAdapter.notifyItemPositionChanged(localMedia.position);
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void sendChangeSubSelectPositionEvent(boolean z) {
        if (this.selectorConfig.selectorStyle.getSelectMainStyle().isSelectNumberStyle()) {
            int i = 0;
            while (i < this.selectorConfig.getSelectCount()) {
                LocalMedia localMedia = this.selectorConfig.getSelectedResult().get(i);
                i++;
                localMedia.setNum(i);
                if (z) {
                    this.mAdapter.notifyItemPositionChanged(localMedia.position);
                }
            }
        }
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void onCheckOriginalChange() {
        this.bottomNarBar.setOriginalCheck();
    }

    private boolean checkNotifyStrategy(boolean z) {
        if (!this.selectorConfig.isMaxSelectEnabledMask) {
            return false;
        }
        if (this.selectorConfig.isWithVideoImage) {
            if (this.selectorConfig.selectionMode == 1) {
                return false;
            }
            if (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum && (z || this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum - 1)) {
                return false;
            }
        } else if (this.selectorConfig.getSelectCount() != 0 && (!z || this.selectorConfig.getSelectCount() != 1)) {
            if (PictureMimeType.isHasVideo(this.selectorConfig.getResultFirstMimeType())) {
                int i = this.selectorConfig.maxVideoSelectNum > 0 ? this.selectorConfig.maxVideoSelectNum : this.selectorConfig.maxSelectNum;
                if (this.selectorConfig.getSelectCount() != i && (z || this.selectorConfig.getSelectCount() != i - 1)) {
                    return false;
                }
            } else if (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum && (z || this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum - 1)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(PictureConfig.EXTRA_ALL_FOLDER_SIZE, this.allFolderSize);
        bundle.putInt(PictureConfig.EXTRA_CURRENT_PAGE, this.mPage);
        bundle.putInt(PictureConfig.EXTRA_PREVIEW_CURRENT_POSITION, this.mRecycler.getLastVisiblePosition());
        bundle.putBoolean(PictureConfig.EXTRA_DISPLAY_CAMERA, this.mAdapter.isDisplayCamera());
        this.selectorConfig.addAlbumDataSource(this.albumListPopWindow.getAlbumList());
        this.selectorConfig.addDataSource(this.mAdapter.getData());
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        reStartSavedInstance(bundle);
        this.isMemoryRecycling = bundle != null;
        this.tvDataEmpty = (TextView) view.findViewById(C2140R.id.tv_data_empty);
        this.completeSelectView = (CompleteSelectView) view.findViewById(C2140R.id.ps_complete_select);
        this.titleBar = (TitleBar) view.findViewById(C2140R.id.title_bar);
        this.bottomNarBar = (BottomNavBar) view.findViewById(C2140R.id.bottom_nar_bar);
        this.tvCurrentDataTime = (TextView) view.findViewById(C2140R.id.tv_current_data_time);
        onCreateLoader();
        initAlbumListPopWindow();
        initTitleBar();
        initComplete();
        initRecycler(view);
        initBottomNavBar();
        if (this.isMemoryRecycling) {
            recoverSaveInstanceData();
        } else {
            requestLoadData();
        }
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void onFragmentResume() {
        setRootViewKeyListener(requireView());
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void reStartSavedInstance(Bundle bundle) {
        if (bundle != null) {
            this.allFolderSize = bundle.getInt(PictureConfig.EXTRA_ALL_FOLDER_SIZE);
            this.mPage = bundle.getInt(PictureConfig.EXTRA_CURRENT_PAGE, this.mPage);
            this.currentPosition = bundle.getInt(PictureConfig.EXTRA_PREVIEW_CURRENT_POSITION, this.currentPosition);
            this.isDisplayCamera = bundle.getBoolean(PictureConfig.EXTRA_DISPLAY_CAMERA, this.selectorConfig.isDisplayCamera);
            return;
        }
        this.isDisplayCamera = this.selectorConfig.isDisplayCamera;
    }

    private void initComplete() {
        if (this.selectorConfig.selectionMode == 1 && this.selectorConfig.isDirectReturnSingle) {
            this.selectorConfig.selectorStyle.getTitleBarStyle().setHideCancelButton(false);
            this.titleBar.getTitleCancelView().setVisibility(0);
            this.completeSelectView.setVisibility(8);
            return;
        }
        this.completeSelectView.setCompleteSelectViewStyle();
        this.completeSelectView.setSelectedChange(false);
        if (this.selectorConfig.selectorStyle.getSelectMainStyle().isCompleteSelectRelativeTop()) {
            if (this.completeSelectView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) this.completeSelectView.getLayoutParams()).topToTop = C2140R.id.title_bar;
                ((ConstraintLayout.LayoutParams) this.completeSelectView.getLayoutParams()).bottomToBottom = C2140R.id.title_bar;
                if (this.selectorConfig.isPreviewFullScreenMode) {
                    ((ConstraintLayout.LayoutParams) this.completeSelectView.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(getContext());
                }
            } else if ((this.completeSelectView.getLayoutParams() instanceof RelativeLayout.LayoutParams) && this.selectorConfig.isPreviewFullScreenMode) {
                ((RelativeLayout.LayoutParams) this.completeSelectView.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(getContext());
            }
        }
        this.completeSelectView.setOnClickListener(new View.OnClickListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PictureSelectorFragment.this.selectorConfig.isEmptyResultReturn || PictureSelectorFragment.this.selectorConfig.getSelectCount() != 0) {
                    PictureSelectorFragment.this.dispatchTransformResult();
                } else {
                    PictureSelectorFragment.this.onExitPictureSelector();
                }
            }
        });
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void onCreateLoader() {
        IBridgeMediaLoader localMediaLoader;
        if (this.selectorConfig.loaderFactory != null) {
            this.mLoader = this.selectorConfig.loaderFactory.onCreateLoader();
            if (this.mLoader != null) {
                return;
            }
            throw new NullPointerException("No available " + IBridgeMediaLoader.class + " loader found");
        }
        if (this.selectorConfig.isPageStrategy) {
            localMediaLoader = new LocalMediaPageLoader(getAppContext(), this.selectorConfig);
        } else {
            localMediaLoader = new LocalMediaLoader(getAppContext(), this.selectorConfig);
        }
        this.mLoader = localMediaLoader;
    }

    private void initTitleBar() {
        if (this.selectorConfig.selectorStyle.getTitleBarStyle().isHideTitleBar()) {
            this.titleBar.setVisibility(8);
        }
        this.titleBar.setTitleBarStyle();
        this.titleBar.setOnTitleBarListener(new TitleBar.OnTitleBarListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.3
            @Override // com.luck.picture.lib.widget.TitleBar.OnTitleBarListener
            public void onTitleDoubleClick() {
                if (PictureSelectorFragment.this.selectorConfig.isAutomaticTitleRecyclerTop) {
                    if (SystemClock.uptimeMillis() - PictureSelectorFragment.this.intervalClickTime < 500 && PictureSelectorFragment.this.mAdapter.getItemCount() > 0) {
                        PictureSelectorFragment.this.mRecycler.scrollToPosition(0);
                    } else {
                        PictureSelectorFragment.this.intervalClickTime = SystemClock.uptimeMillis();
                    }
                }
            }

            @Override // com.luck.picture.lib.widget.TitleBar.OnTitleBarListener
            public void onBackPressed() {
                if (PictureSelectorFragment.this.albumListPopWindow.isShowing()) {
                    PictureSelectorFragment.this.albumListPopWindow.dismiss();
                } else {
                    PictureSelectorFragment.this.onKeyBackFragmentFinish();
                }
            }

            @Override // com.luck.picture.lib.widget.TitleBar.OnTitleBarListener
            public void onShowAlbumPopWindow(View view) {
                PictureSelectorFragment.this.albumListPopWindow.showAsDropDown(view);
            }
        });
    }

    private void initAlbumListPopWindow() {
        AlbumListPopWindow buildPopWindow = AlbumListPopWindow.buildPopWindow(getContext(), this.selectorConfig);
        this.albumListPopWindow = buildPopWindow;
        buildPopWindow.setOnPopupWindowStatusListener(new AlbumListPopWindow.OnPopupWindowStatusListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.4
            @Override // com.luck.picture.lib.dialog.AlbumListPopWindow.OnPopupWindowStatusListener
            public void onShowPopupWindow() {
                if (PictureSelectorFragment.this.selectorConfig.isOnlySandboxDir) {
                    return;
                }
                AnimUtils.rotateArrow(PictureSelectorFragment.this.titleBar.getImageArrow(), true);
            }

            @Override // com.luck.picture.lib.dialog.AlbumListPopWindow.OnPopupWindowStatusListener
            public void onDismissPopupWindow() {
                if (PictureSelectorFragment.this.selectorConfig.isOnlySandboxDir) {
                    return;
                }
                AnimUtils.rotateArrow(PictureSelectorFragment.this.titleBar.getImageArrow(), false);
            }
        });
        addAlbumPopWindowAction();
    }

    private void recoverSaveInstanceData() {
        this.mAdapter.setDisplayCamera(this.isDisplayCamera);
        setEnterAnimationDuration(0L);
        if (this.selectorConfig.isOnlySandboxDir) {
            handleInAppDirAllMedia(this.selectorConfig.currentLocalMediaFolder);
        } else {
            handleRecoverAlbumData(new ArrayList(this.selectorConfig.albumDataSource));
        }
    }

    private void handleRecoverAlbumData(List<LocalMediaFolder> list) {
        LocalMediaFolder localMediaFolder;
        if (ActivityCompatHelper.isDestroy(getActivity())) {
            return;
        }
        if (list.size() > 0) {
            if (this.selectorConfig.currentLocalMediaFolder != null) {
                localMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            } else {
                localMediaFolder = list.get(0);
                this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
            }
            this.titleBar.setTitle(localMediaFolder.getFolderName());
            this.albumListPopWindow.bindAlbumData(list);
            if (this.selectorConfig.isPageStrategy) {
                handleFirstPageMedia(new ArrayList<>(this.selectorConfig.dataSource), true);
                return;
            } else {
                setAdapterData(localMediaFolder.getData());
                return;
            }
        }
        showDataNull();
    }

    private void requestLoadData() {
        this.mAdapter.setDisplayCamera(this.isDisplayCamera);
        if (PermissionChecker.isCheckReadStorage(this.selectorConfig.chooseMode, getContext())) {
            beginLoadData();
            return;
        }
        final String[] readPermissionArray = PermissionConfig.getReadPermissionArray(getAppContext(), this.selectorConfig.chooseMode);
        onPermissionExplainEvent(true, readPermissionArray);
        if (this.selectorConfig.onPermissionsEventListener != null) {
            onApplyPermissionsEvent(-1, readPermissionArray);
        } else {
            PermissionChecker.getInstance().requestPermissions(this, readPermissionArray, new PermissionResultCallback() { // from class: com.luck.picture.lib.PictureSelectorFragment.5
                @Override // com.luck.picture.lib.permissions.PermissionResultCallback
                public void onGranted() {
                    PictureSelectorFragment.this.beginLoadData();
                }

                @Override // com.luck.picture.lib.permissions.PermissionResultCallback
                public void onDenied() {
                    PictureSelectorFragment.this.handlePermissionDenied(readPermissionArray);
                }
            });
        }
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void onApplyPermissionsEvent(int i, String[] strArr) {
        if (i != -1) {
            super.onApplyPermissionsEvent(i, strArr);
        } else {
            this.selectorConfig.onPermissionsEventListener.requestPermission(this, strArr, new OnRequestPermissionListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.6
                @Override // com.luck.picture.lib.interfaces.OnRequestPermissionListener
                public void onCall(String[] strArr2, boolean z) {
                    if (z) {
                        PictureSelectorFragment.this.beginLoadData();
                    } else {
                        PictureSelectorFragment.this.handlePermissionDenied(strArr2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beginLoadData() {
        onPermissionExplainEvent(false, null);
        if (this.selectorConfig.isOnlySandboxDir) {
            loadOnlyInAppDirectoryAllMediaData();
        } else {
            loadAllAlbumData();
        }
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void handlePermissionSettingResult(String[] strArr) {
        boolean isCheckSelfPermission;
        if (strArr == null) {
            return;
        }
        onPermissionExplainEvent(false, null);
        boolean z = strArr.length > 0 && TextUtils.equals(strArr[0], PermissionConfig.CAMERA[0]);
        if (this.selectorConfig.onPermissionsEventListener != null) {
            isCheckSelfPermission = this.selectorConfig.onPermissionsEventListener.hasPermissions(this, strArr);
        } else {
            isCheckSelfPermission = PermissionChecker.isCheckSelfPermission(getContext(), strArr);
        }
        if (isCheckSelfPermission) {
            if (z) {
                openSelectedCamera();
            } else {
                beginLoadData();
            }
        } else if (z) {
            ToastUtils.showToast(getContext(), getString(C2140R.string.ps_camera));
        } else {
            ToastUtils.showToast(getContext(), getString(C2140R.string.ps_jurisdiction));
            onKeyBackFragmentFinish();
        }
        PermissionConfig.CURRENT_REQUEST_PERMISSION = new String[0];
    }

    private void addAlbumPopWindowAction() {
        this.albumListPopWindow.setOnIBridgeAlbumWidget(new OnAlbumItemClickListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.7
            @Override // com.luck.picture.lib.interfaces.OnAlbumItemClickListener
            public void onItemClick(int i, LocalMediaFolder localMediaFolder) {
                PictureSelectorFragment pictureSelectorFragment = PictureSelectorFragment.this;
                pictureSelectorFragment.isDisplayCamera = pictureSelectorFragment.selectorConfig.isDisplayCamera && localMediaFolder.getBucketId() == -1;
                PictureSelectorFragment.this.mAdapter.setDisplayCamera(PictureSelectorFragment.this.isDisplayCamera);
                PictureSelectorFragment.this.titleBar.setTitle(localMediaFolder.getFolderName());
                LocalMediaFolder localMediaFolder2 = PictureSelectorFragment.this.selectorConfig.currentLocalMediaFolder;
                long bucketId = localMediaFolder2.getBucketId();
                if (PictureSelectorFragment.this.selectorConfig.isPageStrategy) {
                    if (localMediaFolder.getBucketId() != bucketId) {
                        localMediaFolder2.setData(PictureSelectorFragment.this.mAdapter.getData());
                        localMediaFolder2.setCurrentDataPage(PictureSelectorFragment.this.mPage);
                        localMediaFolder2.setHasMore(PictureSelectorFragment.this.mRecycler.isEnabledLoadMore());
                        if (localMediaFolder.getData().size() <= 0 || localMediaFolder.isHasMore()) {
                            PictureSelectorFragment.this.mPage = 1;
                            if (PictureSelectorFragment.this.selectorConfig.loaderDataEngine == null) {
                                PictureSelectorFragment.this.mLoader.loadPageMediaData(localMediaFolder.getBucketId(), PictureSelectorFragment.this.mPage, PictureSelectorFragment.this.selectorConfig.pageSize, new OnQueryDataResultListener<LocalMedia>() { // from class: com.luck.picture.lib.PictureSelectorFragment.7.2
                                    @Override // com.luck.picture.lib.interfaces.OnQueryDataResultListener
                                    public void onComplete(ArrayList<LocalMedia> arrayList, boolean z) {
                                        PictureSelectorFragment.this.handleSwitchAlbum(arrayList, z);
                                    }
                                });
                            } else {
                                PictureSelectorFragment.this.selectorConfig.loaderDataEngine.loadFirstPageMediaData(PictureSelectorFragment.this.getContext(), localMediaFolder.getBucketId(), PictureSelectorFragment.this.mPage, PictureSelectorFragment.this.selectorConfig.pageSize, new OnQueryDataResultListener<LocalMedia>() { // from class: com.luck.picture.lib.PictureSelectorFragment.7.1
                                    @Override // com.luck.picture.lib.interfaces.OnQueryDataResultListener
                                    public void onComplete(ArrayList<LocalMedia> arrayList, boolean z) {
                                        PictureSelectorFragment.this.handleSwitchAlbum(arrayList, z);
                                    }
                                });
                            }
                        } else {
                            PictureSelectorFragment.this.setAdapterData(localMediaFolder.getData());
                            PictureSelectorFragment.this.mPage = localMediaFolder.getCurrentDataPage();
                            PictureSelectorFragment.this.mRecycler.setEnabledLoadMore(localMediaFolder.isHasMore());
                            PictureSelectorFragment.this.mRecycler.smoothScrollToPosition(0);
                        }
                    }
                } else if (localMediaFolder.getBucketId() != bucketId) {
                    PictureSelectorFragment.this.setAdapterData(localMediaFolder.getData());
                    PictureSelectorFragment.this.mRecycler.smoothScrollToPosition(0);
                }
                PictureSelectorFragment.this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
                PictureSelectorFragment.this.albumListPopWindow.dismiss();
                if (PictureSelectorFragment.this.mDragSelectTouchListener == null || !PictureSelectorFragment.this.selectorConfig.isFastSlidingSelect) {
                    return;
                }
                PictureSelectorFragment.this.mDragSelectTouchListener.setRecyclerViewHeaderCount(PictureSelectorFragment.this.mAdapter.isDisplayCamera() ? 1 : 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSwitchAlbum(ArrayList<LocalMedia> arrayList, boolean z) {
        if (ActivityCompatHelper.isDestroy(getActivity())) {
            return;
        }
        this.mRecycler.setEnabledLoadMore(z);
        if (arrayList.size() == 0) {
            this.mAdapter.getData().clear();
        }
        setAdapterData(arrayList);
        this.mRecycler.onScrolled(0, 0);
        this.mRecycler.smoothScrollToPosition(0);
    }

    private void initBottomNavBar() {
        this.bottomNarBar.setBottomNavBarStyle();
        this.bottomNarBar.setOnBottomNavBarListener(new BottomNavBar.OnBottomNavBarListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.8
            @Override // com.luck.picture.lib.widget.BottomNavBar.OnBottomNavBarListener
            public void onPreview() {
                PictureSelectorFragment.this.onStartPreview(0, true);
            }

            @Override // com.luck.picture.lib.widget.BottomNavBar.OnBottomNavBarListener
            public void onCheckOriginalChange() {
                PictureSelectorFragment.this.sendSelectedOriginalChangeEvent();
            }
        });
        this.bottomNarBar.setSelectedChange();
    }

    @Override // com.luck.picture.lib.basic.IPictureSelectorEvent
    public void loadAllAlbumData() {
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadAllAlbumData(getContext(), new OnQueryAllAlbumListener<LocalMediaFolder>() { // from class: com.luck.picture.lib.PictureSelectorFragment.9
                @Override // com.luck.picture.lib.interfaces.OnQueryAllAlbumListener
                public void onComplete(List<LocalMediaFolder> list) {
                    PictureSelectorFragment.this.handleAllAlbumData(false, list);
                }
            });
        } else {
            final boolean preloadPageFirstData = preloadPageFirstData();
            this.mLoader.loadAllAlbum(new OnQueryAllAlbumListener<LocalMediaFolder>() { // from class: com.luck.picture.lib.PictureSelectorFragment.10
                @Override // com.luck.picture.lib.interfaces.OnQueryAllAlbumListener
                public void onComplete(List<LocalMediaFolder> list) {
                    PictureSelectorFragment.this.handleAllAlbumData(preloadPageFirstData, list);
                }
            });
        }
    }

    private boolean preloadPageFirstData() {
        Context requireContext;
        int i;
        if (!this.selectorConfig.isPageStrategy || !this.selectorConfig.isPreloadFirst) {
            return false;
        }
        LocalMediaFolder localMediaFolder = new LocalMediaFolder();
        localMediaFolder.setBucketId(-1L);
        if (TextUtils.isEmpty(this.selectorConfig.defaultAlbumName)) {
            TitleBar titleBar = this.titleBar;
            if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
                requireContext = requireContext();
                i = C2140R.string.ps_all_audio;
            } else {
                requireContext = requireContext();
                i = C2140R.string.ps_camera_roll;
            }
            titleBar.setTitle(requireContext.getString(i));
        } else {
            this.titleBar.setTitle(this.selectorConfig.defaultAlbumName);
        }
        localMediaFolder.setFolderName(this.titleBar.getTitleText());
        this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
        loadFirstPageMediaData(localMediaFolder.getBucketId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAllAlbumData(boolean z, List<LocalMediaFolder> list) {
        LocalMediaFolder localMediaFolder;
        if (ActivityCompatHelper.isDestroy(getActivity())) {
            return;
        }
        if (list.size() > 0) {
            if (z) {
                localMediaFolder = list.get(0);
                this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
            } else if (this.selectorConfig.currentLocalMediaFolder != null) {
                localMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            } else {
                localMediaFolder = list.get(0);
                this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
            }
            this.titleBar.setTitle(localMediaFolder.getFolderName());
            this.albumListPopWindow.bindAlbumData(list);
            if (this.selectorConfig.isPageStrategy) {
                if (this.selectorConfig.isPreloadFirst) {
                    this.mRecycler.setEnabledLoadMore(true);
                    return;
                } else {
                    loadFirstPageMediaData(localMediaFolder.getBucketId());
                    return;
                }
            }
            setAdapterData(localMediaFolder.getData());
            return;
        }
        showDataNull();
    }

    @Override // com.luck.picture.lib.basic.IPictureSelectorEvent
    public void loadFirstPageMediaData(long j) {
        this.mPage = 1;
        this.mRecycler.setEnabledLoadMore(true);
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadFirstPageMediaData(getContext(), j, this.mPage, this.mPage * this.selectorConfig.pageSize, new OnQueryDataResultListener<LocalMedia>() { // from class: com.luck.picture.lib.PictureSelectorFragment.11
                @Override // com.luck.picture.lib.interfaces.OnQueryDataResultListener
                public void onComplete(ArrayList<LocalMedia> arrayList, boolean z) {
                    PictureSelectorFragment.this.handleFirstPageMedia(arrayList, z);
                }
            });
        } else {
            this.mLoader.loadPageMediaData(j, this.mPage, this.mPage * this.selectorConfig.pageSize, new OnQueryDataResultListener<LocalMedia>() { // from class: com.luck.picture.lib.PictureSelectorFragment.12
                @Override // com.luck.picture.lib.interfaces.OnQueryDataResultListener
                public void onComplete(ArrayList<LocalMedia> arrayList, boolean z) {
                    PictureSelectorFragment.this.handleFirstPageMedia(arrayList, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFirstPageMedia(ArrayList<LocalMedia> arrayList, boolean z) {
        if (ActivityCompatHelper.isDestroy(getActivity())) {
            return;
        }
        this.mRecycler.setEnabledLoadMore(z);
        if (this.mRecycler.isEnabledLoadMore() && arrayList.size() == 0) {
            onRecyclerViewPreloadMore();
        } else {
            setAdapterData(arrayList);
        }
    }

    @Override // com.luck.picture.lib.basic.IPictureSelectorEvent
    public void loadOnlyInAppDirectoryAllMediaData() {
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadOnlyInAppDirAllMediaData(getContext(), new OnQueryAlbumListener<LocalMediaFolder>() { // from class: com.luck.picture.lib.PictureSelectorFragment.13
                @Override // com.luck.picture.lib.interfaces.OnQueryAlbumListener
                public void onComplete(LocalMediaFolder localMediaFolder) {
                    PictureSelectorFragment.this.handleInAppDirAllMedia(localMediaFolder);
                }
            });
        } else {
            this.mLoader.loadOnlyInAppDirAllMedia(new OnQueryAlbumListener<LocalMediaFolder>() { // from class: com.luck.picture.lib.PictureSelectorFragment.14
                @Override // com.luck.picture.lib.interfaces.OnQueryAlbumListener
                public void onComplete(LocalMediaFolder localMediaFolder) {
                    PictureSelectorFragment.this.handleInAppDirAllMedia(localMediaFolder);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleInAppDirAllMedia(LocalMediaFolder localMediaFolder) {
        if (ActivityCompatHelper.isDestroy(getActivity())) {
            return;
        }
        String str = this.selectorConfig.sandboxDir;
        boolean z = localMediaFolder != null;
        this.titleBar.setTitle(z ? localMediaFolder.getFolderName() : new File(str).getName());
        if (z) {
            this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
            setAdapterData(localMediaFolder.getData());
        } else {
            showDataNull();
        }
    }

    private void recoveryRecyclerPosition() {
        if (this.currentPosition > 0) {
            this.mRecycler.post(new Runnable() { // from class: com.luck.picture.lib.PictureSelectorFragment.15
                @Override // java.lang.Runnable
                public void run() {
                    PictureSelectorFragment.this.mRecycler.scrollToPosition(PictureSelectorFragment.this.currentPosition);
                    PictureSelectorFragment.this.mRecycler.setLastVisiblePosition(PictureSelectorFragment.this.currentPosition);
                }
            });
        }
    }

    private void initRecycler(View view) {
        this.mRecycler = (RecyclerPreloadView) view.findViewById(C2140R.id.recycler);
        SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        int mainListBackgroundColor = selectMainStyle.getMainListBackgroundColor();
        if (StyleUtils.checkStyleValidity(mainListBackgroundColor)) {
            this.mRecycler.setBackgroundColor(mainListBackgroundColor);
        } else {
            this.mRecycler.setBackgroundColor(ContextCompat.getColor(getAppContext(), C2140R.color.ps_color_black));
        }
        int i = this.selectorConfig.imageSpanCount <= 0 ? 4 : this.selectorConfig.imageSpanCount;
        if (this.mRecycler.getItemDecorationCount() == 0) {
            if (StyleUtils.checkSizeValidity(selectMainStyle.getAdapterItemSpacingSize())) {
                this.mRecycler.addItemDecoration(new GridSpacingItemDecoration(i, selectMainStyle.getAdapterItemSpacingSize(), selectMainStyle.isAdapterItemIncludeEdge()));
            } else {
                this.mRecycler.addItemDecoration(new GridSpacingItemDecoration(i, DensityUtil.dip2px(view.getContext(), 1.0f), selectMainStyle.isAdapterItemIncludeEdge()));
            }
        }
        this.mRecycler.setLayoutManager(new GridLayoutManager(getContext(), i));
        RecyclerView.ItemAnimator itemAnimator = this.mRecycler.getItemAnimator();
        if (itemAnimator != null) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            this.mRecycler.setItemAnimator(null);
        }
        if (this.selectorConfig.isPageStrategy) {
            this.mRecycler.setReachBottomRow(2);
            this.mRecycler.setOnRecyclerViewPreloadListener(this);
        } else {
            this.mRecycler.setHasFixedSize(true);
        }
        PictureImageGridAdapter pictureImageGridAdapter = new PictureImageGridAdapter(getContext(), this.selectorConfig);
        this.mAdapter = pictureImageGridAdapter;
        pictureImageGridAdapter.setDisplayCamera(this.isDisplayCamera);
        int i2 = this.selectorConfig.animationMode;
        if (i2 == 1) {
            this.mRecycler.setAdapter(new AlphaInAnimationAdapter(this.mAdapter));
        } else if (i2 == 2) {
            this.mRecycler.setAdapter(new SlideInBottomAnimationAdapter(this.mAdapter));
        } else {
            this.mRecycler.setAdapter(this.mAdapter);
        }
        addRecyclerAction();
    }

    private void addRecyclerAction() {
        this.mAdapter.setOnItemClickListener(new PictureImageGridAdapter.OnItemClickListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.16
            @Override // com.luck.picture.lib.adapter.PictureImageGridAdapter.OnItemClickListener
            public void openCameraClick() {
                if (DoubleUtils.isFastDoubleClick()) {
                    return;
                }
                PictureSelectorFragment.this.openSelectedCamera();
            }

            @Override // com.luck.picture.lib.adapter.PictureImageGridAdapter.OnItemClickListener
            public int onSelected(View view, int i, LocalMedia localMedia) {
                int confirmSelect = PictureSelectorFragment.this.confirmSelect(localMedia, view.isSelected());
                if (confirmSelect == 0) {
                    if (PictureSelectorFragment.this.selectorConfig.onSelectAnimListener != null) {
                        long onSelectAnim = PictureSelectorFragment.this.selectorConfig.onSelectAnimListener.onSelectAnim(view);
                        if (onSelectAnim > 0) {
                            int unused = PictureSelectorFragment.SELECT_ANIM_DURATION = (int) onSelectAnim;
                        }
                    } else {
                        Animation loadAnimation = AnimationUtils.loadAnimation(PictureSelectorFragment.this.getContext(), C2140R.anim.ps_anim_modal_in);
                        int unused2 = PictureSelectorFragment.SELECT_ANIM_DURATION = (int) loadAnimation.getDuration();
                        view.startAnimation(loadAnimation);
                    }
                }
                return confirmSelect;
            }

            @Override // com.luck.picture.lib.adapter.PictureImageGridAdapter.OnItemClickListener
            public void onItemClick(View view, int i, LocalMedia localMedia) {
                if (PictureSelectorFragment.this.selectorConfig.selectionMode == 1 && PictureSelectorFragment.this.selectorConfig.isDirectReturnSingle) {
                    PictureSelectorFragment.this.selectorConfig.selectedResult.clear();
                    if (PictureSelectorFragment.this.confirmSelect(localMedia, false) == 0) {
                        PictureSelectorFragment.this.dispatchTransformResult();
                        return;
                    }
                    return;
                }
                if (DoubleUtils.isFastDoubleClick()) {
                    return;
                }
                PictureSelectorFragment.this.onStartPreview(i, false);
            }

            @Override // com.luck.picture.lib.adapter.PictureImageGridAdapter.OnItemClickListener
            public void onItemLongClick(View view, int i) {
                if (PictureSelectorFragment.this.mDragSelectTouchListener == null || !PictureSelectorFragment.this.selectorConfig.isFastSlidingSelect) {
                    return;
                }
                ((Vibrator) PictureSelectorFragment.this.getActivity().getSystemService("vibrator")).vibrate(50L);
                PictureSelectorFragment.this.mDragSelectTouchListener.startSlideSelection(i);
            }
        });
        this.mRecycler.setOnRecyclerViewScrollStateListener(new OnRecyclerViewScrollStateListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.17
            @Override // com.luck.picture.lib.interfaces.OnRecyclerViewScrollStateListener
            public void onScrollFast() {
                if (PictureSelectorFragment.this.selectorConfig.imageEngine != null) {
                    PictureSelectorFragment.this.selectorConfig.imageEngine.pauseRequests(PictureSelectorFragment.this.getContext());
                }
            }

            @Override // com.luck.picture.lib.interfaces.OnRecyclerViewScrollStateListener
            public void onScrollSlow() {
                if (PictureSelectorFragment.this.selectorConfig.imageEngine != null) {
                    PictureSelectorFragment.this.selectorConfig.imageEngine.resumeRequests(PictureSelectorFragment.this.getContext());
                }
            }
        });
        this.mRecycler.setOnRecyclerViewScrollListener(new OnRecyclerViewScrollListener() { // from class: com.luck.picture.lib.PictureSelectorFragment.18
            @Override // com.luck.picture.lib.interfaces.OnRecyclerViewScrollListener
            public void onScrolled(int i, int i2) {
                PictureSelectorFragment.this.setCurrentMediaCreateTimeText();
            }

            @Override // com.luck.picture.lib.interfaces.OnRecyclerViewScrollListener
            public void onScrollStateChanged(int i) {
                if (i == 1) {
                    PictureSelectorFragment.this.showCurrentMediaCreateTimeUI();
                } else if (i == 0) {
                    PictureSelectorFragment.this.hideCurrentMediaCreateTimeUI();
                }
            }
        });
        if (this.selectorConfig.isFastSlidingSelect) {
            final HashSet hashSet = new HashSet();
            SlideSelectTouchListener withSelectListener = new SlideSelectTouchListener().setRecyclerViewHeaderCount(this.mAdapter.isDisplayCamera() ? 1 : 0).withSelectListener(new SlideSelectionHandler(new SlideSelectionHandler.ISelectionHandler() { // from class: com.luck.picture.lib.PictureSelectorFragment.19
                @Override // com.luck.picture.lib.widget.SlideSelectionHandler.ISelectionHandler
                public HashSet<Integer> getSelection() {
                    for (int i = 0; i < PictureSelectorFragment.this.selectorConfig.getSelectCount(); i++) {
                        hashSet.add(Integer.valueOf(PictureSelectorFragment.this.selectorConfig.getSelectedResult().get(i).position));
                    }
                    return hashSet;
                }

                @Override // com.luck.picture.lib.widget.SlideSelectionHandler.ISelectionHandler
                public void changeSelection(int i, int i2, boolean z, boolean z2) {
                    ArrayList<LocalMedia> data = PictureSelectorFragment.this.mAdapter.getData();
                    if (data.size() == 0 || i > data.size()) {
                        return;
                    }
                    LocalMedia localMedia = data.get(i);
                    PictureSelectorFragment pictureSelectorFragment = PictureSelectorFragment.this;
                    PictureSelectorFragment.this.mDragSelectTouchListener.setActive(pictureSelectorFragment.confirmSelect(localMedia, pictureSelectorFragment.selectorConfig.getSelectedResult().contains(localMedia)) != -1);
                }
            }));
            this.mDragSelectTouchListener = withSelectListener;
            this.mRecycler.addOnItemTouchListener(withSelectListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentMediaCreateTimeText() {
        int firstVisiblePosition;
        if (!this.selectorConfig.isDisplayTimeAxis || (firstVisiblePosition = this.mRecycler.getFirstVisiblePosition()) == -1) {
            return;
        }
        ArrayList<LocalMedia> data = this.mAdapter.getData();
        if (data.size() <= firstVisiblePosition || data.get(firstVisiblePosition).getDateAddedTime() <= 0) {
            return;
        }
        this.tvCurrentDataTime.setText(DateUtils.getDataFormat(getContext(), data.get(firstVisiblePosition).getDateAddedTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCurrentMediaCreateTimeUI() {
        if (this.selectorConfig.isDisplayTimeAxis && this.mAdapter.getData().size() > 0 && this.tvCurrentDataTime.getAlpha() == 0.0f) {
            this.tvCurrentDataTime.animate().setDuration(150L).alphaBy(1.0f).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideCurrentMediaCreateTimeUI() {
        if (!this.selectorConfig.isDisplayTimeAxis || this.mAdapter.getData().size() <= 0) {
            return;
        }
        this.tvCurrentDataTime.animate().setDuration(250L).alpha(0.0f).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onStartPreview(int r13, boolean r14) {
        /*
            r12 = this;
            androidx.fragment.app.FragmentActivity r0 = r12.getActivity()
            java.lang.String r1 = com.luck.picture.lib.PictureSelectorPreviewFragment.TAG
            boolean r0 = com.luck.picture.lib.utils.ActivityCompatHelper.checkFragmentNonExits(r0, r1)
            if (r0 == 0) goto Lc8
            r0 = 0
            if (r14 == 0) goto L24
            java.util.ArrayList r1 = new java.util.ArrayList
            com.luck.picture.lib.config.SelectorConfig r2 = r12.selectorConfig
            java.util.ArrayList r2 = r2.getSelectedResult()
            r1.<init>(r2)
            int r2 = r1.size()
            r3 = 0
        L20:
            r9 = r1
            r5 = r2
            r7 = r3
            goto L59
        L24:
            java.util.ArrayList r1 = new java.util.ArrayList
            com.luck.picture.lib.adapter.PictureImageGridAdapter r2 = r12.mAdapter
            java.util.ArrayList r2 = r2.getData()
            r1.<init>(r2)
            com.luck.picture.lib.config.SelectorConfig r2 = r12.selectorConfig
            com.luck.picture.lib.entity.LocalMediaFolder r2 = r2.currentLocalMediaFolder
            if (r2 == 0) goto L41
            int r3 = r2.getFolderTotalNum()
            long r4 = r2.getBucketId()
            r9 = r1
            r7 = r4
            r5 = r3
            goto L59
        L41:
            int r2 = r1.size()
            int r3 = r1.size()
            if (r3 <= 0) goto L56
            java.lang.Object r3 = r1.get(r0)
            com.luck.picture.lib.entity.LocalMedia r3 = (com.luck.picture.lib.entity.LocalMedia) r3
            long r3 = r3.getBucketId()
            goto L20
        L56:
            r3 = -1
            goto L20
        L59:
            if (r14 != 0) goto L75
            com.luck.picture.lib.config.SelectorConfig r1 = r12.selectorConfig
            boolean r1 = r1.isPreviewZoomEffect
            if (r1 == 0) goto L75
            com.luck.picture.lib.widget.RecyclerPreloadView r1 = r12.mRecycler
            com.luck.picture.lib.config.SelectorConfig r2 = r12.selectorConfig
            boolean r2 = r2.isPreviewFullScreenMode
            if (r2 == 0) goto L6a
            goto L72
        L6a:
            android.content.Context r0 = r12.getContext()
            int r0 = com.luck.picture.lib.utils.DensityUtil.getStatusBarHeight(r0)
        L72:
            com.luck.picture.lib.magical.BuildRecycleItemViewParams.generateViewParams(r1, r0)
        L75:
            com.luck.picture.lib.config.SelectorConfig r0 = r12.selectorConfig
            com.luck.picture.lib.interfaces.OnPreviewInterceptListener r0 = r0.onPreviewInterceptListener
            if (r0 == 0) goto L9b
            com.luck.picture.lib.config.SelectorConfig r0 = r12.selectorConfig
            com.luck.picture.lib.interfaces.OnPreviewInterceptListener r0 = r0.onPreviewInterceptListener
            android.content.Context r1 = r12.getContext()
            int r4 = r12.mPage
            com.luck.picture.lib.widget.TitleBar r2 = r12.titleBar
            java.lang.String r10 = r2.getTitleText()
            com.luck.picture.lib.adapter.PictureImageGridAdapter r2 = r12.mAdapter
            boolean r11 = r2.isDisplayCamera()
            r2 = r13
            r3 = r5
            r5 = r7
            r7 = r10
            r8 = r11
            r10 = r14
            r0.onPreview(r1, r2, r3, r4, r5, r7, r8, r9, r10)
            goto Lc8
        L9b:
            androidx.fragment.app.FragmentActivity r0 = r12.getActivity()
            java.lang.String r1 = com.luck.picture.lib.PictureSelectorPreviewFragment.TAG
            boolean r0 = com.luck.picture.lib.utils.ActivityCompatHelper.checkFragmentNonExits(r0, r1)
            if (r0 == 0) goto Lc8
            com.luck.picture.lib.PictureSelectorPreviewFragment r10 = com.luck.picture.lib.PictureSelectorPreviewFragment.newInstance()
            com.luck.picture.lib.widget.TitleBar r0 = r12.titleBar
            java.lang.String r2 = r0.getTitleText()
            com.luck.picture.lib.adapter.PictureImageGridAdapter r0 = r12.mAdapter
            boolean r3 = r0.isDisplayCamera()
            int r6 = r12.mPage
            r0 = r10
            r1 = r14
            r4 = r13
            r0.setInternalPreviewData(r1, r2, r3, r4, r5, r6, r7, r9)
            androidx.fragment.app.FragmentActivity r0 = r12.getActivity()
            java.lang.String r1 = com.luck.picture.lib.PictureSelectorPreviewFragment.TAG
            com.luck.picture.lib.basic.FragmentInjectManager.injectFragment(r0, r1, r10)
        Lc8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.PictureSelectorFragment.onStartPreview(int, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdapterData(final ArrayList<LocalMedia> arrayList) {
        long enterAnimationDuration = getEnterAnimationDuration();
        if (enterAnimationDuration > 0) {
            requireView().postDelayed(new Runnable() { // from class: com.luck.picture.lib.PictureSelectorFragment.20
                @Override // java.lang.Runnable
                public void run() {
                    PictureSelectorFragment.this.setAdapterDataComplete(arrayList);
                }
            }, enterAnimationDuration);
        } else {
            setAdapterDataComplete(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdapterDataComplete(ArrayList<LocalMedia> arrayList) {
        setEnterAnimationDuration(0L);
        sendChangeSubSelectPositionEvent(false);
        this.mAdapter.setDataAndDataSetChanged(arrayList);
        this.selectorConfig.dataSource.clear();
        this.selectorConfig.albumDataSource.clear();
        recoveryRecyclerPosition();
        if (this.mAdapter.isDataEmpty()) {
            showDataNull();
        } else {
            hideDataNull();
        }
    }

    @Override // com.luck.picture.lib.interfaces.OnRecyclerViewPreloadMoreListener
    public void onRecyclerViewPreloadMore() {
        if (this.isMemoryRecycling) {
            requireView().postDelayed(new Runnable() { // from class: com.luck.picture.lib.PictureSelectorFragment.21
                @Override // java.lang.Runnable
                public void run() {
                    PictureSelectorFragment.this.loadMoreMediaData();
                }
            }, 350L);
        } else {
            loadMoreMediaData();
        }
    }

    @Override // com.luck.picture.lib.basic.IPictureSelectorEvent
    public void loadMoreMediaData() {
        if (this.mRecycler.isEnabledLoadMore()) {
            this.mPage++;
            LocalMediaFolder localMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            long bucketId = localMediaFolder != null ? localMediaFolder.getBucketId() : 0L;
            if (this.selectorConfig.loaderDataEngine != null) {
                this.selectorConfig.loaderDataEngine.loadMoreMediaData(getContext(), bucketId, this.mPage, this.selectorConfig.pageSize, this.selectorConfig.pageSize, new OnQueryDataResultListener<LocalMedia>() { // from class: com.luck.picture.lib.PictureSelectorFragment.22
                    @Override // com.luck.picture.lib.interfaces.OnQueryDataResultListener
                    public void onComplete(ArrayList<LocalMedia> arrayList, boolean z) {
                        PictureSelectorFragment.this.handleMoreMediaData(arrayList, z);
                    }
                });
            } else {
                this.mLoader.loadPageMediaData(bucketId, this.mPage, this.selectorConfig.pageSize, new OnQueryDataResultListener<LocalMedia>() { // from class: com.luck.picture.lib.PictureSelectorFragment.23
                    @Override // com.luck.picture.lib.interfaces.OnQueryDataResultListener
                    public void onComplete(ArrayList<LocalMedia> arrayList, boolean z) {
                        PictureSelectorFragment.this.handleMoreMediaData(arrayList, z);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMoreMediaData(List<LocalMedia> list, boolean z) {
        if (ActivityCompatHelper.isDestroy(getActivity())) {
            return;
        }
        this.mRecycler.setEnabledLoadMore(z);
        if (this.mRecycler.isEnabledLoadMore()) {
            removePageCameraRepeatData(list);
            if (list.size() > 0) {
                int size = this.mAdapter.getData().size();
                this.mAdapter.getData().addAll(list);
                PictureImageGridAdapter pictureImageGridAdapter = this.mAdapter;
                pictureImageGridAdapter.notifyItemRangeChanged(size, pictureImageGridAdapter.getItemCount());
                hideDataNull();
            } else {
                onRecyclerViewPreloadMore();
            }
            if (list.size() < 10) {
                RecyclerPreloadView recyclerPreloadView = this.mRecycler;
                recyclerPreloadView.onScrolled(recyclerPreloadView.getScrollX(), this.mRecycler.getScrollY());
            }
        }
    }

    private void removePageCameraRepeatData(List<LocalMedia> list) {
        try {
            try {
                if (this.selectorConfig.isPageStrategy && this.isCameraCallback) {
                    synchronized (LOCK) {
                        Iterator<LocalMedia> it = list.iterator();
                        while (it.hasNext()) {
                            if (this.mAdapter.getData().contains(it.next())) {
                                it.remove();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.isCameraCallback = false;
        }
    }

    @Override // com.luck.picture.lib.basic.PictureCommonFragment, com.luck.picture.lib.basic.IPictureSelectorCommonEvent
    public void dispatchCameraMediaResult(LocalMedia localMedia) {
        if (!isAddSameImp(this.albumListPopWindow.getFirstAlbumImageCount())) {
            this.mAdapter.getData().add(0, localMedia);
            this.isCameraCallback = true;
        }
        if (this.selectorConfig.selectionMode == 1 && this.selectorConfig.isDirectReturnSingle) {
            this.selectorConfig.selectedResult.clear();
            if (confirmSelect(localMedia, false) == 0) {
                dispatchTransformResult();
            }
        } else {
            confirmSelect(localMedia, false);
        }
        this.mAdapter.notifyItemInserted(this.selectorConfig.isDisplayCamera ? 1 : 0);
        PictureImageGridAdapter pictureImageGridAdapter = this.mAdapter;
        boolean z = this.selectorConfig.isDisplayCamera;
        pictureImageGridAdapter.notifyItemRangeChanged(z ? 1 : 0, this.mAdapter.getData().size());
        if (this.selectorConfig.isOnlySandboxDir) {
            LocalMediaFolder localMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            if (localMediaFolder == null) {
                localMediaFolder = new LocalMediaFolder();
            }
            localMediaFolder.setBucketId(ValueOf.toLong(Integer.valueOf(localMedia.getParentFolderName().hashCode())));
            localMediaFolder.setFolderName(localMedia.getParentFolderName());
            localMediaFolder.setFirstMimeType(localMedia.getMimeType());
            localMediaFolder.setFirstImagePath(localMedia.getPath());
            localMediaFolder.setFolderTotalNum(this.mAdapter.getData().size());
            localMediaFolder.setCurrentDataPage(this.mPage);
            localMediaFolder.setHasMore(false);
            localMediaFolder.setData(this.mAdapter.getData());
            this.mRecycler.setEnabledLoadMore(false);
            this.selectorConfig.currentLocalMediaFolder = localMediaFolder;
        } else {
            mergeFolder(localMedia);
        }
        this.allFolderSize = 0;
        if (this.mAdapter.getData().size() > 0 || this.selectorConfig.isDirectReturnSingle) {
            hideDataNull();
        } else {
            showDataNull();
        }
    }

    private void mergeFolder(LocalMedia localMedia) {
        LocalMediaFolder folder;
        LocalMediaFolder localMediaFolder;
        String str;
        List<LocalMediaFolder> albumList = this.albumListPopWindow.getAlbumList();
        if (this.albumListPopWindow.getFolderCount() == 0) {
            folder = new LocalMediaFolder();
            if (TextUtils.isEmpty(this.selectorConfig.defaultAlbumName)) {
                str = getString(this.selectorConfig.chooseMode == SelectMimeType.ofAudio() ? C2140R.string.ps_all_audio : C2140R.string.ps_camera_roll);
            } else {
                str = this.selectorConfig.defaultAlbumName;
            }
            folder.setFolderName(str);
            folder.setFirstImagePath("");
            folder.setBucketId(-1L);
            albumList.add(0, folder);
        } else {
            folder = this.albumListPopWindow.getFolder(0);
        }
        folder.setFirstImagePath(localMedia.getPath());
        folder.setFirstMimeType(localMedia.getMimeType());
        folder.setData(this.mAdapter.getData());
        folder.setBucketId(-1L);
        folder.setFolderTotalNum(isAddSameImp(folder.getFolderTotalNum()) ? folder.getFolderTotalNum() : folder.getFolderTotalNum() + 1);
        LocalMediaFolder localMediaFolder2 = this.selectorConfig.currentLocalMediaFolder;
        if (localMediaFolder2 == null || localMediaFolder2.getFolderTotalNum() == 0) {
            this.selectorConfig.currentLocalMediaFolder = folder;
        }
        int i = 0;
        while (true) {
            if (i >= albumList.size()) {
                localMediaFolder = null;
                break;
            }
            localMediaFolder = albumList.get(i);
            if (TextUtils.equals(localMediaFolder.getFolderName(), localMedia.getParentFolderName())) {
                break;
            } else {
                i++;
            }
        }
        if (localMediaFolder == null) {
            localMediaFolder = new LocalMediaFolder();
            albumList.add(localMediaFolder);
        }
        localMediaFolder.setFolderName(localMedia.getParentFolderName());
        if (localMediaFolder.getBucketId() == -1 || localMediaFolder.getBucketId() == 0) {
            localMediaFolder.setBucketId(localMedia.getBucketId());
        }
        if (this.selectorConfig.isPageStrategy) {
            localMediaFolder.setHasMore(true);
        } else if (!isAddSameImp(folder.getFolderTotalNum()) || !TextUtils.isEmpty(this.selectorConfig.outPutCameraDir) || !TextUtils.isEmpty(this.selectorConfig.outPutAudioDir)) {
            localMediaFolder.getData().add(0, localMedia);
        }
        localMediaFolder.setFolderTotalNum(isAddSameImp(folder.getFolderTotalNum()) ? localMediaFolder.getFolderTotalNum() : localMediaFolder.getFolderTotalNum() + 1);
        localMediaFolder.setFirstImagePath(this.selectorConfig.cameraPath);
        localMediaFolder.setFirstMimeType(localMedia.getMimeType());
        this.albumListPopWindow.bindAlbumData(albumList);
    }

    private boolean isAddSameImp(int i) {
        int i2;
        return i != 0 && (i2 = this.allFolderSize) > 0 && i2 < i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        SlideSelectTouchListener slideSelectTouchListener = this.mDragSelectTouchListener;
        if (slideSelectTouchListener != null) {
            slideSelectTouchListener.stopAutoScroll();
        }
    }

    private void showDataNull() {
        if (this.selectorConfig.currentLocalMediaFolder == null || this.selectorConfig.currentLocalMediaFolder.getBucketId() == -1) {
            if (this.tvDataEmpty.getVisibility() == 8) {
                this.tvDataEmpty.setVisibility(0);
            }
            this.tvDataEmpty.setCompoundDrawablesRelativeWithIntrinsicBounds(0, C2140R.drawable.ps_ic_no_data, 0, 0);
            this.tvDataEmpty.setText(getString(this.selectorConfig.chooseMode == SelectMimeType.ofAudio() ? C2140R.string.ps_audio_empty : C2140R.string.ps_empty));
        }
    }

    private void hideDataNull() {
        if (this.tvDataEmpty.getVisibility() == 0) {
            this.tvDataEmpty.setVisibility(8);
        }
    }
}
