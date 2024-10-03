package com.yalantis.ucrop;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropFragment;
import com.yalantis.ucrop.UCropGalleryAdapter;
import com.yalantis.ucrop.decoration.GridSpacingItemDecoration;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.statusbar.ImmersiveManager;
import com.yalantis.ucrop.util.DensityUtil;
import com.yalantis.ucrop.util.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class UCropMultipleActivity extends AppCompatActivity implements UCropFragmentCallback {
    private ArrayList<AspectRatio> aspectRatioList;
    private int currentFragmentPosition;
    private UCropGalleryAdapter galleryAdapter;
    private boolean isForbidCropGifWebp;
    private boolean isSkipCropForbid;
    private boolean mShowLoader;
    private int mStatusBarColor;
    private int mToolbarCancelDrawable;
    private int mToolbarColor;
    private int mToolbarCropDrawable;
    private String mToolbarTitle;
    private int mToolbarTitleSize;
    private int mToolbarWidgetColor;
    private String outputCropFileName;
    private UCropFragment uCropCurrentFragment;
    private ArrayList<String> uCropNotSupportList;
    private ArrayList<String> uCropSupportList;
    private final List<UCropFragment> fragments = new ArrayList();
    private final LinkedHashMap<String, JSONObject> uCropTotalQueue = new LinkedHashMap<>();
    private final HashSet<String> filterSet = new HashSet<>();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        immersive();
        setContentView(C2880R.layout.ucrop_activity_multiple);
        setupViews(getIntent());
        initCropFragments(getIntent());
    }

    private void immersive() {
        Intent intent = getIntent();
        boolean booleanExtra = intent.getBooleanExtra(UCrop.Options.EXTRA_DARK_STATUS_BAR_BLACK, false);
        int intExtra = intent.getIntExtra(UCrop.Options.EXTRA_STATUS_BAR_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_statusbar));
        this.mStatusBarColor = intExtra;
        ImmersiveManager.immersiveAboveAPI23(this, intExtra, intExtra, booleanExtra);
    }

    private void initCropFragments(Intent intent) {
        String str;
        int i = 0;
        this.isSkipCropForbid = intent.getBooleanExtra(UCrop.Options.EXTRA_CROP_FORBID_SKIP, false);
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(UCrop.EXTRA_CROP_TOTAL_DATA_SOURCE);
        if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
            throw new IllegalArgumentException("Missing required parameters, count cannot be less than 1");
        }
        this.uCropSupportList = new ArrayList<>();
        this.uCropNotSupportList = new ArrayList<>();
        while (i < stringArrayListExtra.size()) {
            String str2 = stringArrayListExtra.get(i);
            this.uCropTotalQueue.put(str2, new JSONObject());
            String path = FileUtils.isContent(str2) ? FileUtils.getPath(this, Uri.parse(str2)) : str2;
            String pathToMimeType = getPathToMimeType(str2);
            if (FileUtils.isUrlHasVideo(path) || FileUtils.isHasVideo(pathToMimeType) || FileUtils.isHasAudio(pathToMimeType)) {
                this.uCropNotSupportList.add(str2);
            } else {
                this.uCropSupportList.add(str2);
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Uri parse = (FileUtils.isContent(str2) || FileUtils.isHasHttp(str2)) ? Uri.parse(str2) : Uri.fromFile(new File(str2));
                    String postfixDefaultJPEG = FileUtils.getPostfixDefaultJPEG(this, this.isForbidCropGifWebp, parse);
                    if (TextUtils.isEmpty(this.outputCropFileName)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(FileUtils.getCreateFileName("CROP_" + (i + 1)));
                        sb.append(postfixDefaultJPEG);
                        str = sb.toString();
                    } else {
                        str = (i + 1) + FileUtils.getCreateFileName() + "_" + this.outputCropFileName;
                    }
                    Uri fromFile = Uri.fromFile(new File(getSandboxPathDir(), str));
                    extras.putParcelable(UCrop.EXTRA_INPUT_URI, parse);
                    extras.putParcelable("com.yalantis.ucrop.OutputUri", fromFile);
                    ArrayList<AspectRatio> arrayList = this.aspectRatioList;
                    AspectRatio aspectRatio = (arrayList == null || arrayList.size() <= i) ? null : this.aspectRatioList.get(i);
                    extras.putFloat(UCrop.EXTRA_ASPECT_RATIO_X, aspectRatio != null ? aspectRatio.getAspectRatioX() : -1.0f);
                    extras.putFloat(UCrop.EXTRA_ASPECT_RATIO_Y, aspectRatio != null ? aspectRatio.getAspectRatioY() : -1.0f);
                    this.fragments.add(UCropFragment.newInstance(extras));
                }
            }
            i++;
        }
        if (this.uCropSupportList.size() == 0) {
            throw new IllegalArgumentException("No clipping data sources are available");
        }
        setGalleryAdapter();
        switchCropFragment(this.fragments.get(getCropSupportPosition()), getCropSupportPosition());
        this.galleryAdapter.setCurrentSelectPosition(getCropSupportPosition());
    }

    private int getCropSupportPosition() {
        ArrayList<String> stringArrayList;
        Bundle extras = getIntent().getExtras();
        if (extras == null || (stringArrayList = extras.getStringArrayList(UCrop.Options.EXTRA_SKIP_CROP_MIME_TYPE)) == null || stringArrayList.size() <= 0) {
            return 0;
        }
        this.filterSet.addAll(stringArrayList);
        int i = -1;
        for (int i2 = 0; i2 < this.uCropSupportList.size(); i2++) {
            i++;
            if (!this.filterSet.contains(getPathToMimeType(this.uCropSupportList.get(i2)))) {
                break;
            }
        }
        if (i == -1 || i > this.fragments.size()) {
            return 0;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPathToMimeType(String str) {
        if (FileUtils.isContent(str)) {
            return FileUtils.getMimeTypeFromMediaContentUri(this, Uri.parse(str));
        }
        return FileUtils.getMimeTypeFromMediaContentUri(this, Uri.fromFile(new File(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCropFragment(UCropFragment uCropFragment, int i) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!uCropFragment.isAdded()) {
            UCropFragment uCropFragment2 = this.uCropCurrentFragment;
            if (uCropFragment2 != null) {
                beginTransaction.hide(uCropFragment2);
            }
            beginTransaction.add(C2880R.id.fragment_container, uCropFragment, UCropFragment.TAG + "-" + i);
        } else {
            beginTransaction.hide(this.uCropCurrentFragment).show(uCropFragment);
            uCropFragment.fragmentReVisible();
        }
        this.currentFragmentPosition = i;
        this.uCropCurrentFragment = uCropFragment;
        beginTransaction.commitAllowingStateLoss();
    }

    private void setGalleryAdapter() {
        RecyclerView recyclerView = (RecyclerView) findViewById(C2880R.id.recycler_gallery);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(Integer.MAX_VALUE, DensityUtil.dip2px(this, 6.0f), true));
        }
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, C2880R.anim.ucrop_layout_animation_fall_down));
        recyclerView.setBackgroundResource(getIntent().getIntExtra(UCrop.Options.EXTRA_GALLERY_BAR_BACKGROUND, C2880R.drawable.ucrop_gallery_bg));
        UCropGalleryAdapter uCropGalleryAdapter = new UCropGalleryAdapter(this.uCropSupportList);
        this.galleryAdapter = uCropGalleryAdapter;
        uCropGalleryAdapter.setOnItemClickListener(new UCropGalleryAdapter.OnItemClickListener() { // from class: com.yalantis.ucrop.UCropMultipleActivity.1
            @Override // com.yalantis.ucrop.UCropGalleryAdapter.OnItemClickListener
            public void onItemClick(int i, View view) {
                if (UCropMultipleActivity.this.isSkipCropForbid) {
                    return;
                }
                if (!UCropMultipleActivity.this.filterSet.contains(UCropMultipleActivity.this.getPathToMimeType((String) UCropMultipleActivity.this.uCropSupportList.get(i)))) {
                    if (UCropMultipleActivity.this.galleryAdapter.getCurrentSelectPosition() == i) {
                        return;
                    }
                    UCropMultipleActivity.this.galleryAdapter.notifyItemChanged(UCropMultipleActivity.this.galleryAdapter.getCurrentSelectPosition());
                    UCropMultipleActivity.this.galleryAdapter.setCurrentSelectPosition(i);
                    UCropMultipleActivity.this.galleryAdapter.notifyItemChanged(i);
                    UCropMultipleActivity.this.switchCropFragment((UCropFragment) UCropMultipleActivity.this.fragments.get(i), i);
                    return;
                }
                Toast.makeText(UCropMultipleActivity.this.getApplicationContext(), UCropMultipleActivity.this.getString(C2880R.string.ucrop_not_crop), 0).show();
            }
        });
        recyclerView.setAdapter(this.galleryAdapter);
    }

    private String getSandboxPathDir() {
        File file;
        String stringExtra = getIntent().getStringExtra(UCrop.Options.EXTRA_CROP_OUTPUT_DIR);
        if (stringExtra == null || "".equals(stringExtra)) {
            file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "Sandbox");
        } else {
            file = new File(stringExtra);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator;
    }

    private void setupViews(Intent intent) {
        this.aspectRatioList = getIntent().getParcelableArrayListExtra(UCrop.Options.EXTRA_MULTIPLE_ASPECT_RATIO);
        this.isForbidCropGifWebp = intent.getBooleanExtra(UCrop.Options.EXTRA_CROP_FORBID_GIF_WEBP, false);
        this.outputCropFileName = intent.getStringExtra(UCrop.Options.EXTRA_CROP_OUTPUT_FILE_NAME);
        this.mStatusBarColor = intent.getIntExtra(UCrop.Options.EXTRA_STATUS_BAR_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_statusbar));
        this.mToolbarColor = intent.getIntExtra(UCrop.Options.EXTRA_TOOL_BAR_COLOR, ContextCompat.getColor(this, C2880R.color.ucrop_color_toolbar));
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
        setupAppBar();
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

    private void setStatusBarColor(int i) {
        Window window = getWindow();
        if (window != null) {
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
        }
    }

    @Override // com.yalantis.ucrop.UCropFragmentCallback
    public void loadingProgress(boolean z) {
        this.mShowLoader = z;
        supportInvalidateOptionsMenu();
    }

    @Override // com.yalantis.ucrop.UCropFragmentCallback
    public void onCropFinish(UCropFragment.UCropResult uCropResult) {
        int i = uCropResult.mResultCode;
        if (i != -1) {
            if (i != 96) {
                return;
            }
            handleCropError(uCropResult.mResultData);
            return;
        }
        int size = this.currentFragmentPosition + this.uCropNotSupportList.size();
        boolean z = true;
        int size2 = (this.uCropNotSupportList.size() + this.uCropSupportList.size()) - 1;
        mergeCropResult(uCropResult.mResultData);
        if (size == size2) {
            onCropCompleteFinish();
            return;
        }
        int i2 = this.currentFragmentPosition + 1;
        String pathToMimeType = getPathToMimeType(this.uCropSupportList.get(i2));
        while (true) {
            if (!this.filterSet.contains(pathToMimeType)) {
                z = false;
                break;
            } else {
                if (i2 == size2) {
                    break;
                }
                i2++;
                pathToMimeType = getPathToMimeType(this.uCropSupportList.get(i2));
            }
        }
        if (z) {
            onCropCompleteFinish();
            return;
        }
        switchCropFragment(this.fragments.get(i2), i2);
        UCropGalleryAdapter uCropGalleryAdapter = this.galleryAdapter;
        uCropGalleryAdapter.notifyItemChanged(uCropGalleryAdapter.getCurrentSelectPosition());
        this.galleryAdapter.setCurrentSelectPosition(i2);
        UCropGalleryAdapter uCropGalleryAdapter2 = this.galleryAdapter;
        uCropGalleryAdapter2.notifyItemChanged(uCropGalleryAdapter2.getCurrentSelectPosition());
    }

    private void onCropCompleteFinish() {
        JSONArray jSONArray = new JSONArray();
        Iterator<Map.Entry<String, JSONObject>> it = this.uCropTotalQueue.entrySet().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().getValue());
        }
        Intent intent = new Intent();
        intent.putExtra("output", jSONArray.toString());
        setResult(-1, intent);
        finish();
    }

    private void mergeCropResult(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(UCrop.EXTRA_CROP_INPUT_ORIGINAL);
            JSONObject jSONObject = this.uCropTotalQueue.get(stringExtra);
            Uri output = UCrop.getOutput(intent);
            jSONObject.put("outPutPath", output != null ? output.getPath() : "");
            jSONObject.put("imageWidth", UCrop.getOutputImageWidth(intent));
            jSONObject.put("imageHeight", UCrop.getOutputImageHeight(intent));
            jSONObject.put("offsetX", UCrop.getOutputImageOffsetX(intent));
            jSONObject.put("offsetY", UCrop.getOutputImageOffsetY(intent));
            jSONObject.put("aspectRatio", UCrop.getOutputCropAspectRatio(intent));
            this.uCropTotalQueue.put(stringExtra, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCropError(Intent intent) {
        Throwable error = UCrop.getError(intent);
        if (error != null) {
            Toast.makeText(this, error.getMessage(), 1).show();
        } else {
            Toast.makeText(this, "Unexpected error", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        UCropDevelopConfig.destroy();
        super.onDestroy();
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
                e.printStackTrace();
            }
            ((Animatable) findItem.getIcon()).start();
        }
        MenuItem findItem2 = menu.findItem(C2880R.id.menu_crop);
        Drawable drawable = ContextCompat.getDrawable(this, this.mToolbarCropDrawable);
        if (drawable == null) {
            return true;
        }
        drawable.mutate();
        drawable.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(this.mToolbarWidgetColor, BlendModeCompat.SRC_ATOP));
        findItem2.setIcon(drawable);
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
            UCropFragment uCropFragment = this.uCropCurrentFragment;
            if (uCropFragment != null && uCropFragment.isAdded()) {
                this.uCropCurrentFragment.cropAndSaveImage();
            }
        } else if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
