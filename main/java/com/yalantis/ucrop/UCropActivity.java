package com.yalantis.ucrop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.statusbar.ImmersiveManager;
import com.yalantis.ucrop.util.FileUtils;
import com.yalantis.ucrop.util.SelectedStateListDrawable;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.TransformImageView;
import com.yalantis.ucrop.view.UCropView;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class UCropActivity extends AppCompatActivity {
    public static final int ALL = 3;
    private static final long CONTROLS_ANIMATION_DURATION = 50;
    public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final int NONE = 0;
    public static final int ROTATE = 2;
    private static final int ROTATE_WIDGET_SENSITIVITY_COEFFICIENT = 42;
    public static final int SCALE = 1;
    private static final int SCALE_WIDGET_SENSITIVITY_COEFFICIENT = 15000;
    private static final int TABS_COUNT = 3;
    private static final String TAG = "UCropActivity";
    private boolean isForbidCropGifWebp;
    private boolean isUseCustomBitmap;
    private int mActiveControlsWidgetColor;
    private View mBlockingView;
    private Transition mControlsTransition;
    private GestureCropImageView mGestureCropImageView;
    private ViewGroup mLayoutAspectRatio;
    private ViewGroup mLayoutRotate;
    private ViewGroup mLayoutScale;
    private int mLogoColor;
    private OverlayView mOverlayView;
    private int mRootViewBackgroundColor;
    private boolean mShowBottomControls;
    private int mStatusBarColor;
    private TextView mTextViewRotateAngle;
    private TextView mTextViewScalePercent;
    private int mToolbarCancelDrawable;
    private int mToolbarColor;
    private int mToolbarCropDrawable;
    private String mToolbarTitle;
    private int mToolbarTitleSize;
    private int mToolbarWidgetColor;
    private UCropView mUCropView;
    private ViewGroup mWrapperStateAspectRatio;
    private ViewGroup mWrapperStateRotate;
    private ViewGroup mWrapperStateScale;
    private boolean mShowLoader = true;
    private List<ViewGroup> mCropAspectRatioViews = new ArrayList();
    private Bitmap.CompressFormat mCompressFormat = DEFAULT_COMPRESS_FORMAT;
    private int mCompressQuality = 90;
    private int[] mAllowedGestures = {1, 2, 3};
    private TransformImageView.TransformImageListener mImageListener = new TransformImageView.TransformImageListener() { // from class: com.yalantis.ucrop.UCropActivity.1
        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onRotate(float f) {
            UCropActivity.this.setAngleText(f);
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onScale(float f) {
            UCropActivity.this.setScaleText(f);
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onLoadComplete() {
            UCropActivity.this.mUCropView.animate().alpha(1.0f).setDuration(300L).setInterpolator(new AccelerateInterpolator());
            UCropActivity.this.mBlockingView.setClickable(false);
            if (UCropActivity.this.getIntent().getBooleanExtra(UCrop.Options.EXTRA_CROP_FORBID_GIF_WEBP, false)) {
                String mimeTypeFromMediaContentUri = FileUtils.getMimeTypeFromMediaContentUri(UCropActivity.this, (Uri) UCropActivity.this.getIntent().getParcelableExtra(UCrop.EXTRA_INPUT_URI));
                if (FileUtils.isGif(mimeTypeFromMediaContentUri) || FileUtils.isWebp(mimeTypeFromMediaContentUri)) {
                    UCropActivity.this.mBlockingView.setClickable(true);
                }
            }
            UCropActivity.this.mShowLoader = false;
            UCropActivity.this.supportInvalidateOptionsMenu();
        }

        @Override // com.yalantis.ucrop.view.TransformImageView.TransformImageListener
        public void onLoadFailure(Exception exc) {
            UCropActivity.this.setResultError(exc);
            UCropActivity.this.finish();
        }
    };
    private final View.OnClickListener mStateClickListener = new View.OnClickListener() { // from class: com.yalantis.ucrop.UCropActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.isSelected()) {
                return;
            }
            UCropActivity.this.setWidgetState(view.getId());
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface GestureTypes {
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        immersive();
        setContentView(C2880R.layout.ucrop_activity_photobox);
        Intent intent = getIntent();
        setupViews(intent);
        setImageData(intent);
        setInitialState();
        addBlockingView();
    }

    private void immersive() {
        Intent intent = getIntent();
        boolean booleanExtra = intent.getBooleanExtra(UCrop.Options.EXTRA_DARK_STATUS_BAR_BLACK, false);
        int intExtra = intent.getIntExtra(UCrop.Options.EXTRA_STATUS_BAR_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_statusbar));
        this.mStatusBarColor = intExtra;
        ImmersiveManager.immersiveAboveAPI23(this, intExtra, intExtra, booleanExtra);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C2880R.menu.ucrop_menu_activity, menu);
        MenuItem findItem = menu.findItem(C2880R.id.menu_loader);
        Drawable icon = findItem.getIcon();
        if (icon != null) {
            try {
                icon.mutate();
                icon.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
                findItem.setIcon(icon);
            } catch (IllegalStateException e) {
                Log.i(TAG, String.format("%s - %s", e.getMessage(), getString(C2880R.string.ucrop_mutate_exception_hint)));
            }
            ((Animatable) findItem.getIcon()).start();
        }
        MenuItem findItem2 = menu.findItem(C2880R.id.menu_crop);
        Drawable drawable = ContextCompat.getDrawable(this, this.mToolbarCropDrawable);
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
            findItem2.setIcon(drawable);
        }
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C2880R.id.menu_crop).setVisible(!this.mShowLoader);
        menu.findItem(C2880R.id.menu_loader).setVisible(this.mShowLoader);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C2880R.id.menu_crop) {
            cropAndSaveImage();
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        GestureCropImageView gestureCropImageView = this.mGestureCropImageView;
        if (gestureCropImageView != null) {
            gestureCropImageView.cancelAllAnimations();
        }
    }

    private void setImageData(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra(UCrop.EXTRA_INPUT_URI);
        Uri uri2 = (Uri) intent.getParcelableExtra("com.yalantis.ucrop.OutputUri");
        processOptions(intent);
        if (uri != null && uri2 != null) {
            try {
                this.mGestureCropImageView.setImageUri(uri, FileUtils.replaceOutputUri(this, this.isForbidCropGifWebp, uri, uri2), this.isUseCustomBitmap);
                return;
            } catch (Exception e) {
                setResultError(e);
                finish();
                return;
            }
        }
        setResultError(new NullPointerException(getString(C2880R.string.ucrop_error_input_data_is_absent)));
        finish();
    }

    private void processOptions(Intent intent) {
        String stringExtra = intent.getStringExtra(UCrop.Options.EXTRA_COMPRESSION_FORMAT_NAME);
        Bitmap.CompressFormat valueOf = !TextUtils.isEmpty(stringExtra) ? Bitmap.CompressFormat.valueOf(stringExtra) : null;
        if (valueOf == null) {
            valueOf = DEFAULT_COMPRESS_FORMAT;
        }
        this.mCompressFormat = valueOf;
        this.mCompressQuality = intent.getIntExtra(UCrop.Options.EXTRA_COMPRESSION_QUALITY, 90);
        int[] intArrayExtra = intent.getIntArrayExtra(UCrop.Options.EXTRA_ALLOWED_GESTURES);
        if (intArrayExtra != null && intArrayExtra.length == 3) {
            this.mAllowedGestures = intArrayExtra;
        }
        this.isUseCustomBitmap = intent.getBooleanExtra(UCrop.Options.EXTRA_CROP_CUSTOM_LOADER_BITMAP, false);
        this.mGestureCropImageView.setMaxBitmapSize(intent.getIntExtra(UCrop.Options.EXTRA_MAX_BITMAP_SIZE, 0));
        this.mGestureCropImageView.setMaxScaleMultiplier(intent.getFloatExtra(UCrop.Options.EXTRA_MAX_SCALE_MULTIPLIER, 10.0f));
        this.mGestureCropImageView.setImageToWrapCropBoundsAnimDuration(intent.getIntExtra(UCrop.Options.EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 500));
        this.mOverlayView.setFreestyleCropEnabled(intent.getBooleanExtra(UCrop.Options.EXTRA_FREE_STYLE_CROP, false));
        this.mOverlayView.setDragSmoothToCenter(intent.getBooleanExtra(UCrop.Options.EXTRA_CROP_DRAG_CENTER, false));
        this.mOverlayView.setDimmedColor(intent.getIntExtra(UCrop.Options.EXTRA_DIMMED_LAYER_COLOR, getResources().getColor(C2880R.color.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleStrokeColor(intent.getIntExtra(UCrop.Options.EXTRA_CIRCLE_STROKE_COLOR, getResources().getColor(C2880R.color.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleDimmedLayer(intent.getBooleanExtra(UCrop.Options.EXTRA_CIRCLE_DIMMED_LAYER, false));
        this.mOverlayView.setShowCropFrame(intent.getBooleanExtra(UCrop.Options.EXTRA_SHOW_CROP_FRAME, true));
        this.mOverlayView.setCropFrameColor(intent.getIntExtra(UCrop.Options.EXTRA_CROP_FRAME_COLOR, getResources().getColor(C2880R.color.ucrop_color_default_crop_frame)));
        this.mOverlayView.setCropFrameStrokeWidth(intent.getIntExtra(UCrop.Options.EXTRA_CROP_FRAME_STROKE_WIDTH, getResources().getDimensionPixelSize(C2880R.dimen.ucrop_default_crop_frame_stoke_width)));
        this.mOverlayView.setShowCropGrid(intent.getBooleanExtra(UCrop.Options.EXTRA_SHOW_CROP_GRID, true));
        this.mOverlayView.setCropGridRowCount(intent.getIntExtra(UCrop.Options.EXTRA_CROP_GRID_ROW_COUNT, 2));
        this.mOverlayView.setCropGridColumnCount(intent.getIntExtra(UCrop.Options.EXTRA_CROP_GRID_COLUMN_COUNT, 2));
        this.mOverlayView.setCropGridColor(intent.getIntExtra(UCrop.Options.EXTRA_CROP_GRID_COLOR, getResources().getColor(C2880R.color.ucrop_color_default_crop_grid)));
        this.mOverlayView.setCropGridStrokeWidth(intent.getIntExtra(UCrop.Options.EXTRA_CROP_GRID_STROKE_WIDTH, getResources().getDimensionPixelSize(C2880R.dimen.ucrop_default_crop_grid_stoke_width)));
        this.mOverlayView.setDimmedStrokeWidth(intent.getIntExtra(UCrop.Options.EXTRA_CIRCLE_STROKE_WIDTH_LAYER, getResources().getDimensionPixelSize(C2880R.dimen.ucrop_default_crop_grid_stoke_width)));
        float floatExtra = intent.getFloatExtra(UCrop.EXTRA_ASPECT_RATIO_X, -1.0f);
        float floatExtra2 = intent.getFloatExtra(UCrop.EXTRA_ASPECT_RATIO_Y, -1.0f);
        int intExtra = intent.getIntExtra(UCrop.Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(UCrop.Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (floatExtra >= 0.0f && floatExtra2 >= 0.0f) {
            ViewGroup viewGroup = this.mWrapperStateAspectRatio;
            if (viewGroup != null) {
                viewGroup.setVisibility(8);
            }
            float f = floatExtra / floatExtra2;
            this.mGestureCropImageView.setTargetAspectRatio(Float.isNaN(f) ? 0.0f : f);
        } else if (parcelableArrayListExtra != null && intExtra < parcelableArrayListExtra.size()) {
            float aspectRatioX = ((AspectRatio) parcelableArrayListExtra.get(intExtra)).getAspectRatioX() / ((AspectRatio) parcelableArrayListExtra.get(intExtra)).getAspectRatioY();
            this.mGestureCropImageView.setTargetAspectRatio(Float.isNaN(aspectRatioX) ? 0.0f : aspectRatioX);
        } else {
            this.mGestureCropImageView.setTargetAspectRatio(0.0f);
        }
        int intExtra2 = intent.getIntExtra(UCrop.EXTRA_MAX_SIZE_X, 0);
        int intExtra3 = intent.getIntExtra(UCrop.EXTRA_MAX_SIZE_Y, 0);
        if (intExtra2 <= 0 || intExtra3 <= 0) {
            return;
        }
        this.mGestureCropImageView.setMaxResultImageSizeX(intExtra2);
        this.mGestureCropImageView.setMaxResultImageSizeY(intExtra3);
    }

    private void setupViews(Intent intent) {
        this.isForbidCropGifWebp = intent.getBooleanExtra(UCrop.Options.EXTRA_CROP_FORBID_GIF_WEBP, false);
        this.mStatusBarColor = intent.getIntExtra(UCrop.Options.EXTRA_STATUS_BAR_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_statusbar));
        this.mToolbarColor = intent.getIntExtra(UCrop.Options.EXTRA_TOOL_BAR_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_toolbar));
        this.mActiveControlsWidgetColor = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_COLOR_CONTROLS_WIDGET_ACTIVE, ContextCompat.getColor(this, C2880R.color.ucrop_color_active_controls_color));
        this.mToolbarWidgetColor = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_WIDGET_COLOR_TOOLBAR, ContextCompat.getColor(this, C2880R.color.ucrop_color_toolbar_widget));
        this.mToolbarCancelDrawable = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_WIDGET_CANCEL_DRAWABLE, C2880R.drawable.ucrop_ic_cross);
        this.mToolbarCropDrawable = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_WIDGET_CROP_DRAWABLE, C2880R.drawable.ucrop_ic_done);
        this.mToolbarTitle = intent.getStringExtra(UCrop.Options.EXTRA_UCROP_TITLE_TEXT_TOOLBAR);
        this.mToolbarTitleSize = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_TITLE_TEXT_SIZE_TOOLBAR, 18);
        String str = this.mToolbarTitle;
        if (str == null) {
            str = getResources().getString(C2880R.string.ucrop_label_edit_photo);
        }
        this.mToolbarTitle = str;
        this.mLogoColor = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_LOGO_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_default_logo));
        this.mShowBottomControls = !intent.getBooleanExtra(UCrop.Options.EXTRA_HIDE_BOTTOM_CONTROLS, false);
        this.mRootViewBackgroundColor = intent.getIntExtra(UCrop.Options.EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_crop_background));
        setupAppBar();
        initiateRootViews();
        if (this.mShowBottomControls) {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(C2880R.id.ucrop_photobox)).findViewById(C2880R.id.controls_wrapper);
            viewGroup.setVisibility(0);
            LayoutInflater.from(this).inflate(C2880R.layout.ucrop_controls, viewGroup, true);
            AutoTransition autoTransition = new AutoTransition();
            this.mControlsTransition = autoTransition;
            autoTransition.setDuration(CONTROLS_ANIMATION_DURATION);
            ViewGroup viewGroup2 = (ViewGroup) findViewById(C2880R.id.state_aspect_ratio);
            this.mWrapperStateAspectRatio = viewGroup2;
            viewGroup2.setOnClickListener(this.mStateClickListener);
            ViewGroup viewGroup3 = (ViewGroup) findViewById(C2880R.id.state_rotate);
            this.mWrapperStateRotate = viewGroup3;
            viewGroup3.setOnClickListener(this.mStateClickListener);
            ViewGroup viewGroup4 = (ViewGroup) findViewById(C2880R.id.state_scale);
            this.mWrapperStateScale = viewGroup4;
            viewGroup4.setOnClickListener(this.mStateClickListener);
            this.mLayoutAspectRatio = (ViewGroup) findViewById(C2880R.id.layout_aspect_ratio);
            this.mLayoutRotate = (ViewGroup) findViewById(C2880R.id.layout_rotate_wheel);
            this.mLayoutScale = (ViewGroup) findViewById(C2880R.id.layout_scale_wheel);
            setupAspectRatioWidget(intent);
            setupRotateWidget();
            setupScaleWidget();
            setupStatesWrapper();
        }
    }

    private void setupAppBar() {
        setStatusBarColor(this.mStatusBarColor);
        Toolbar toolbar = (Toolbar) findViewById(C2880R.id.toolbar);
        toolbar.setBackgroundColor(this.mToolbarColor);
        toolbar.setTitleTextColor(this.mToolbarWidgetColor);
        TextView textView = (TextView) toolbar.findViewById(C2880R.id.toolbar_title);
        textView.setTextColor(this.mToolbarWidgetColor);
        textView.setText(this.mToolbarTitle);
        textView.setTextSize(this.mToolbarTitleSize);
        Drawable mutate = AppCompatResources.getDrawable(this, this.mToolbarCancelDrawable).mutate();
        mutate.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
        toolbar.setNavigationIcon(mutate);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initiateRootViews() {
        UCropView uCropView = (UCropView) findViewById(C2880R.id.ucrop);
        this.mUCropView = uCropView;
        this.mGestureCropImageView = uCropView.getCropImageView();
        this.mOverlayView = this.mUCropView.getOverlayView();
        this.mGestureCropImageView.setTransformImageListener(this.mImageListener);
        ((ImageView) findViewById(C2880R.id.image_view_logo)).setColorFilter(this.mLogoColor, PorterDuff.Mode.SRC_ATOP);
        findViewById(C2880R.id.ucrop_frame).setBackgroundColor(this.mRootViewBackgroundColor);
        if (this.mShowBottomControls) {
            return;
        }
        ((RelativeLayout.LayoutParams) findViewById(C2880R.id.ucrop_frame).getLayoutParams()).bottomMargin = 0;
        findViewById(C2880R.id.ucrop_frame).requestLayout();
    }

    private void setupStatesWrapper() {
        ImageView imageView = (ImageView) findViewById(C2880R.id.image_view_state_scale);
        ImageView imageView2 = (ImageView) findViewById(C2880R.id.image_view_state_rotate);
        ImageView imageView3 = (ImageView) findViewById(C2880R.id.image_view_state_aspect_ratio);
        imageView.setImageDrawable(new SelectedStateListDrawable(imageView.getDrawable(), this.mActiveControlsWidgetColor));
        imageView2.setImageDrawable(new SelectedStateListDrawable(imageView2.getDrawable(), this.mActiveControlsWidgetColor));
        imageView3.setImageDrawable(new SelectedStateListDrawable(imageView3.getDrawable(), this.mActiveControlsWidgetColor));
    }

    private void setStatusBarColor(int i) {
        Window window = getWindow();
        if (window != null) {
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
        }
    }

    private void setupAspectRatioWidget(Intent intent) {
        int intExtra = intent.getIntExtra(UCrop.Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(UCrop.Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
            parcelableArrayListExtra = new ArrayList();
            parcelableArrayListExtra.add(new AspectRatio(null, 1.0f, 1.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 4.0f));
            parcelableArrayListExtra.add(new AspectRatio(getString(C2880R.string.ucrop_label_original).toUpperCase(), 0.0f, 0.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 2.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 16.0f, 9.0f));
            intExtra = 2;
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(C2880R.id.layout_aspect_ratio);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        Iterator it = parcelableArrayListExtra.iterator();
        while (it.hasNext()) {
            AspectRatio aspectRatio = (AspectRatio) it.next();
            FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(C2880R.layout.ucrop_aspect_ratio, (ViewGroup) null);
            frameLayout.setLayoutParams(layoutParams);
            AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) frameLayout.getChildAt(0);
            aspectRatioTextView.setActiveColor(this.mActiveControlsWidgetColor);
            aspectRatioTextView.setAspectRatio(aspectRatio);
            linearLayout.addView(frameLayout);
            this.mCropAspectRatioViews.add(frameLayout);
        }
        this.mCropAspectRatioViews.get(intExtra).setSelected(true);
        Iterator<ViewGroup> it2 = this.mCropAspectRatioViews.iterator();
        while (it2.hasNext()) {
            it2.next().setOnClickListener(new View.OnClickListener() { // from class: com.yalantis.ucrop.UCropActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    UCropActivity.this.mGestureCropImageView.setTargetAspectRatio(((AspectRatioTextView) ((ViewGroup) view).getChildAt(0)).getAspectRatio(view.isSelected()));
                    UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
                    if (view.isSelected()) {
                        return;
                    }
                    for (ViewGroup viewGroup : UCropActivity.this.mCropAspectRatioViews) {
                        viewGroup.setSelected(viewGroup == view);
                    }
                }
            });
        }
    }

    private void setupRotateWidget() {
        this.mTextViewRotateAngle = (TextView) findViewById(C2880R.id.text_view_rotate);
        ((HorizontalProgressWheelView) findViewById(C2880R.id.rotate_scroll_wheel)).setScrollingListener(new HorizontalProgressWheelView.ScrollingListener() { // from class: com.yalantis.ucrop.UCropActivity.3
            @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
            public void onScroll(float f, float f2) {
                UCropActivity.this.mGestureCropImageView.postRotate(f / 42.0f);
            }

            @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
            public void onScrollEnd() {
                UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
            }

            @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
            public void onScrollStart() {
                UCropActivity.this.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView) findViewById(C2880R.id.rotate_scroll_wheel)).setMiddleLineColor(this.mActiveControlsWidgetColor);
        findViewById(C2880R.id.wrapper_reset_rotate).setOnClickListener(new View.OnClickListener() { // from class: com.yalantis.ucrop.UCropActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UCropActivity.this.resetRotation();
            }
        });
        findViewById(C2880R.id.wrapper_rotate_by_angle).setOnClickListener(new View.OnClickListener() { // from class: com.yalantis.ucrop.UCropActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UCropActivity.this.rotateByAngle(90);
            }
        });
        setAngleTextColor(this.mActiveControlsWidgetColor);
    }

    private void setupScaleWidget() {
        this.mTextViewScalePercent = (TextView) findViewById(C2880R.id.text_view_scale);
        ((HorizontalProgressWheelView) findViewById(C2880R.id.scale_scroll_wheel)).setScrollingListener(new HorizontalProgressWheelView.ScrollingListener() { // from class: com.yalantis.ucrop.UCropActivity.6
            @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
            public void onScroll(float f, float f2) {
                if (f > 0.0f) {
                    UCropActivity.this.mGestureCropImageView.zoomInImage(UCropActivity.this.mGestureCropImageView.getCurrentScale() + (f * ((UCropActivity.this.mGestureCropImageView.getMaxScale() - UCropActivity.this.mGestureCropImageView.getMinScale()) / 15000.0f)));
                } else {
                    UCropActivity.this.mGestureCropImageView.zoomOutImage(UCropActivity.this.mGestureCropImageView.getCurrentScale() + (f * ((UCropActivity.this.mGestureCropImageView.getMaxScale() - UCropActivity.this.mGestureCropImageView.getMinScale()) / 15000.0f)));
                }
            }

            @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
            public void onScrollEnd() {
                UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
            }

            @Override // com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener
            public void onScrollStart() {
                UCropActivity.this.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView) findViewById(C2880R.id.scale_scroll_wheel)).setMiddleLineColor(this.mActiveControlsWidgetColor);
        setScaleTextColor(this.mActiveControlsWidgetColor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAngleText(float f) {
        TextView textView = this.mTextViewRotateAngle;
        if (textView != null) {
            textView.setText(String.format(Locale.getDefault(), "%.1f°", Float.valueOf(f)));
        }
    }

    private void setAngleTextColor(int i) {
        TextView textView = this.mTextViewRotateAngle;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScaleText(float f) {
        TextView textView = this.mTextViewScalePercent;
        if (textView != null) {
            textView.setText(String.format(Locale.getDefault(), "%d%%", Integer.valueOf((int) (f * 100.0f))));
        }
    }

    private void setScaleTextColor(int i) {
        TextView textView = this.mTextViewScalePercent;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRotation() {
        GestureCropImageView gestureCropImageView = this.mGestureCropImageView;
        gestureCropImageView.postRotate(-gestureCropImageView.getCurrentAngle());
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rotateByAngle(int i) {
        this.mGestureCropImageView.postRotate(i);
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }

    private void setInitialState() {
        if (this.mShowBottomControls) {
            if (this.mWrapperStateAspectRatio.getVisibility() == 0) {
                setWidgetState(C2880R.id.state_aspect_ratio);
                return;
            } else {
                setWidgetState(C2880R.id.state_scale);
                return;
            }
        }
        setAllowedGestures(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWidgetState(int i) {
        if (this.mShowBottomControls) {
            this.mWrapperStateAspectRatio.setSelected(i == C2880R.id.state_aspect_ratio);
            this.mWrapperStateRotate.setSelected(i == C2880R.id.state_rotate);
            this.mWrapperStateScale.setSelected(i == C2880R.id.state_scale);
            this.mLayoutAspectRatio.setVisibility(i == C2880R.id.state_aspect_ratio ? 0 : 8);
            this.mLayoutRotate.setVisibility(i == C2880R.id.state_rotate ? 0 : 8);
            this.mLayoutScale.setVisibility(i == C2880R.id.state_scale ? 0 : 8);
            changeSelectedTab(i);
            if (i == C2880R.id.state_scale) {
                setAllowedGestures(0);
            } else if (i == C2880R.id.state_rotate) {
                setAllowedGestures(1);
            } else {
                setAllowedGestures(2);
            }
        }
    }

    private void changeSelectedTab(int i) {
        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(C2880R.id.ucrop_photobox), this.mControlsTransition);
        this.mWrapperStateScale.findViewById(C2880R.id.text_view_scale).setVisibility(i == C2880R.id.state_scale ? 0 : 8);
        this.mWrapperStateAspectRatio.findViewById(C2880R.id.text_view_crop).setVisibility(i == C2880R.id.state_aspect_ratio ? 0 : 8);
        this.mWrapperStateRotate.findViewById(C2880R.id.text_view_rotate).setVisibility(i != C2880R.id.state_rotate ? 8 : 0);
    }

    private void setAllowedGestures(int i) {
        GestureCropImageView gestureCropImageView = this.mGestureCropImageView;
        int i2 = this.mAllowedGestures[i];
        gestureCropImageView.setScaleEnabled(i2 == 3 || i2 == 1);
        GestureCropImageView gestureCropImageView2 = this.mGestureCropImageView;
        int i3 = this.mAllowedGestures[i];
        gestureCropImageView2.setRotateEnabled(i3 == 3 || i3 == 2);
        this.mGestureCropImageView.setGestureEnabled(getIntent().getBooleanExtra(UCrop.Options.EXTRA_DRAG_IMAGES, true));
    }

    private void addBlockingView() {
        if (this.mBlockingView == null) {
            this.mBlockingView = new View(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, C2880R.id.toolbar);
            this.mBlockingView.setLayoutParams(layoutParams);
            this.mBlockingView.setClickable(true);
        }
        ((RelativeLayout) findViewById(C2880R.id.ucrop_photobox)).addView(this.mBlockingView);
    }

    protected void cropAndSaveImage() {
        this.mBlockingView.setClickable(true);
        this.mShowLoader = true;
        supportInvalidateOptionsMenu();
        this.mGestureCropImageView.cropAndSaveImage(this.mCompressFormat, this.mCompressQuality, new BitmapCropCallback() { // from class: com.yalantis.ucrop.UCropActivity.8
            @Override // com.yalantis.ucrop.callback.BitmapCropCallback
            public void onBitmapCropped(Uri uri, int i, int i2, int i3, int i4) {
                UCropActivity uCropActivity = UCropActivity.this;
                uCropActivity.setResultUri(uri, uCropActivity.mGestureCropImageView.getTargetAspectRatio(), i, i2, i3, i4);
                UCropActivity.this.finish();
            }

            @Override // com.yalantis.ucrop.callback.BitmapCropCallback
            public void onCropFailure(Throwable th) {
                UCropActivity.this.setResultError(th);
                UCropActivity.this.finish();
            }
        });
    }

    protected void setResultUri(Uri uri, float f, int i, int i2, int i3, int i4) {
        setResult(-1, new Intent().putExtra("com.yalantis.ucrop.OutputUri", uri).putExtra("com.yalantis.ucrop.CropAspectRatio", f).putExtra("com.yalantis.ucrop.ImageWidth", i3).putExtra("com.yalantis.ucrop.ImageHeight", i4).putExtra("com.yalantis.ucrop.OffsetX", i).putExtra("com.yalantis.ucrop.OffsetY", i2).putExtra(UCrop.EXTRA_CROP_INPUT_ORIGINAL, FileUtils.getInputPath((Uri) getIntent().getParcelableExtra(UCrop.EXTRA_INPUT_URI))));
    }

    protected void setResultError(Throwable th) {
        setResult(96, new Intent().putExtra("com.yalantis.ucrop.Error", th));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        UCropDevelopConfig.destroy();
        super.onDestroy();
    }
}
