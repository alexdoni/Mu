package com.luck.picture.lib.basic;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.luck.picture.lib.C2140R;
import com.luck.picture.lib.PictureOnlyCameraFragment;
import com.luck.picture.lib.PictureSelectorPreviewFragment;
import com.luck.picture.lib.PictureSelectorSystemFragment;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.immersive.ImmersiveManager;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.utils.StyleUtils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PictureSelectorTransparentActivity extends AppCompatActivity {
    private SelectorConfig selectorConfig;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initSelectorConfig();
        immersive();
        setContentView(C2140R.layout.ps_empty);
        if (!isExternalPreview()) {
            setActivitySize();
        }
        setupFragment();
    }

    private void initSelectorConfig() {
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
    }

    private boolean isExternalPreview() {
        return getIntent().getIntExtra(PictureConfig.EXTRA_MODE_TYPE_SOURCE, 0) == 2;
    }

    private void immersive() {
        if (this.selectorConfig.selectorStyle == null) {
            SelectorProviders.getInstance().getSelectorConfig();
        }
        SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        int statusBarColor = selectMainStyle.getStatusBarColor();
        int navigationBarColor = selectMainStyle.getNavigationBarColor();
        boolean isDarkStatusBarBlack = selectMainStyle.isDarkStatusBarBlack();
        if (!StyleUtils.checkStyleValidity(statusBarColor)) {
            statusBarColor = ContextCompat.getColor(this, C2140R.color.ps_color_grey);
        }
        if (!StyleUtils.checkStyleValidity(navigationBarColor)) {
            navigationBarColor = ContextCompat.getColor(this, C2140R.color.ps_color_grey);
        }
        ImmersiveManager.immersiveAboveAPI23(this, statusBarColor, navigationBarColor, isDarkStatusBarBlack);
    }

    private void setupFragment() {
        String str;
        Fragment newInstance;
        int intExtra = getIntent().getIntExtra(PictureConfig.EXTRA_MODE_TYPE_SOURCE, 0);
        if (intExtra == 1) {
            str = PictureSelectorSystemFragment.TAG;
            newInstance = PictureSelectorSystemFragment.newInstance();
        } else if (intExtra == 2) {
            Fragment onInjectPreviewFragment = this.selectorConfig.onInjectActivityPreviewListener != null ? this.selectorConfig.onInjectActivityPreviewListener.onInjectPreviewFragment() : null;
            if (onInjectPreviewFragment != null) {
                newInstance = onInjectPreviewFragment;
                str = ((PictureSelectorPreviewFragment) onInjectPreviewFragment).getFragmentTag();
            } else {
                str = PictureSelectorPreviewFragment.TAG;
                newInstance = PictureSelectorPreviewFragment.newInstance();
            }
            int intExtra2 = getIntent().getIntExtra(PictureConfig.EXTRA_PREVIEW_CURRENT_POSITION, 0);
            ArrayList<LocalMedia> arrayList = new ArrayList<>(this.selectorConfig.selectedPreviewResult);
            ((PictureSelectorPreviewFragment) newInstance).setExternalPreviewData(intExtra2, arrayList.size(), arrayList, getIntent().getBooleanExtra(PictureConfig.EXTRA_EXTERNAL_PREVIEW_DISPLAY_DELETE, false));
        } else {
            str = PictureOnlyCameraFragment.TAG;
            newInstance = PictureOnlyCameraFragment.newInstance();
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(str);
        if (findFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
        }
        FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, str, newInstance);
    }

    private void setActivitySize() {
        Window window = getWindow();
        window.setGravity(51);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = 1;
        attributes.width = 1;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (getIntent().getIntExtra(PictureConfig.EXTRA_MODE_TYPE_SOURCE, 0) == 2 && !this.selectorConfig.isPreviewZoomEffect) {
            overridePendingTransition(0, this.selectorConfig.selectorStyle.getWindowAnimationStyle().activityExitAnimation);
        } else {
            overridePendingTransition(0, C2140R.anim.ps_anim_fade_out);
        }
    }
}
