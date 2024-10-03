package com.luck.picture.lib.basic;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.C2140R;
import com.luck.picture.lib.PictureSelectorFragment;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.immersive.ImmersiveManager;
import com.luck.picture.lib.language.PictureLanguageUtils;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.utils.StyleUtils;

/* loaded from: classes2.dex */
public class PictureSelectorSupporterActivity extends AppCompatActivity {
    private SelectorConfig selectorConfig;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initSelectorConfig();
        immersive();
        setContentView(C2140R.layout.ps_activity_container);
        setupFragment();
    }

    private void initSelectorConfig() {
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
    }

    private void immersive() {
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
        FragmentInjectManager.injectFragment(this, PictureSelectorFragment.TAG, PictureSelectorFragment.newInstance());
    }

    public void initAppLanguage() {
        SelectorConfig selectorConfig = this.selectorConfig;
        if (selectorConfig == null || selectorConfig.language == -2 || this.selectorConfig.isOnlyCamera) {
            return;
        }
        PictureLanguageUtils.setAppLanguage(this, this.selectorConfig.language, this.selectorConfig.defaultLanguage);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initAppLanguage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        SelectorConfig selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        if (selectorConfig != null) {
            super.attachBaseContext(PictureContextWrapper.wrap(context, selectorConfig.language, selectorConfig.defaultLanguage));
        } else {
            super.attachBaseContext(context);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        SelectorConfig selectorConfig = this.selectorConfig;
        if (selectorConfig != null) {
            overridePendingTransition(0, selectorConfig.selectorStyle.getWindowAnimationStyle().activityExitAnimation);
        }
    }
}
