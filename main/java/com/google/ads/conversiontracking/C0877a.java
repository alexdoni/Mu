package com.google.ads.conversiontracking;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import com.google.ads.conversiontracking.C0885i;
import java.io.IOException;
import java.util.List;

/* renamed from: com.google.ads.conversiontracking.a */
/* loaded from: classes.dex */
public class C0877a {

    /* renamed from: a */
    private Context f400a;

    public C0877a(Context context) {
        this.f400a = new a(context);
    }

    /* renamed from: a */
    public C0885i.a m125a() {
        try {
            return C0885i.m222a(this.f400a);
        } catch (C0886j | C0887k | IOException | IllegalStateException unused) {
            return null;
        }
    }

    /* renamed from: com.google.ads.conversiontracking.a$a */
    /* loaded from: classes.dex */
    static class a extends ContextWrapper {

        /* renamed from: a */
        private final b f401a;

        /* renamed from: b */
        private final c f402b;

        public a(Context context) {
            super(context);
            this.f401a = new b(context);
            this.f402b = new c(context.getResources());
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public PackageManager getPackageManager() {
            return this.f401a;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Resources getResources() {
            return this.f402b;
        }
    }

    /* renamed from: com.google.ads.conversiontracking.a$c */
    /* loaded from: classes.dex */
    static class c extends Resources {
        @Override // android.content.res.Resources
        public String getString(int i) {
            return "";
        }

        public c(Resources resources) {
            super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        }
    }

    /* renamed from: com.google.ads.conversiontracking.a$b */
    /* loaded from: classes.dex */
    static class b extends PackageManager {

        /* renamed from: a */
        private final Context f403a;

        /* renamed from: b */
        private final PackageManager f404b;

        public b(Context context) {
            this.f403a = context;
            this.f404b = context.getPackageManager();
        }

        @Override // android.content.pm.PackageManager
        public ApplicationInfo getApplicationInfo(String str, int i) throws PackageManager.NameNotFoundException {
            ApplicationInfo applicationInfo = this.f404b.getApplicationInfo(str, i);
            if (str.equals(this.f403a.getPackageName()) && (i & 128) == 128) {
                if (applicationInfo.metaData == null) {
                    applicationInfo.metaData = new Bundle();
                }
                applicationInfo.metaData.putInt("com.google.android.gms.version", 4323000);
            }
            return applicationInfo;
        }

        @Override // android.content.pm.PackageManager
        public PackageInfo getPackageInfo(String str, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getPackageInfo(str, i);
        }

        @Override // android.content.pm.PackageManager
        public void addPackageToPreferred(String str) {
            this.f404b.addPackageToPreferred(str);
        }

        @Override // android.content.pm.PackageManager
        public boolean addPermission(PermissionInfo permissionInfo) {
            return this.f404b.addPermission(permissionInfo);
        }

        @Override // android.content.pm.PackageManager
        public boolean addPermissionAsync(PermissionInfo permissionInfo) {
            return this.f404b.addPermissionAsync(permissionInfo);
        }

        @Override // android.content.pm.PackageManager
        public void addPreferredActivity(IntentFilter intentFilter, int i, ComponentName[] componentNameArr, ComponentName componentName) {
            this.f404b.addPreferredActivity(intentFilter, i, componentNameArr, componentName);
        }

        @Override // android.content.pm.PackageManager
        public String[] canonicalToCurrentPackageNames(String[] strArr) {
            return this.f404b.canonicalToCurrentPackageNames(strArr);
        }

        @Override // android.content.pm.PackageManager
        public int checkPermission(String str, String str2) {
            return this.f404b.checkPermission(str, str2);
        }

        @Override // android.content.pm.PackageManager
        public int checkSignatures(int i, int i2) {
            return this.f404b.checkSignatures(i, i2);
        }

        @Override // android.content.pm.PackageManager
        public int checkSignatures(String str, String str2) {
            return this.f404b.checkSignatures(str, str2);
        }

        @Override // android.content.pm.PackageManager
        public void clearPackagePreferredActivities(String str) {
            this.f404b.clearPackagePreferredActivities(str);
        }

        @Override // android.content.pm.PackageManager
        public String[] currentToCanonicalPackageNames(String[] strArr) {
            return this.f404b.currentToCanonicalPackageNames(strArr);
        }

        @Override // android.content.pm.PackageManager
        public void extendVerificationTimeout(int i, int i2, long j) {
            this.f404b.extendVerificationTimeout(i, i2, j);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getActivityIcon(Intent intent) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityIcon(intent);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getActivityIcon(ComponentName componentName) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityIcon(componentName);
        }

        @Override // android.content.pm.PackageManager
        public ActivityInfo getActivityInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityInfo(componentName, i);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getActivityLogo(Intent intent) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityLogo(intent);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getActivityLogo(ComponentName componentName) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityLogo(componentName);
        }

        @Override // android.content.pm.PackageManager
        public List<PermissionGroupInfo> getAllPermissionGroups(int i) {
            return this.f404b.getAllPermissionGroups(i);
        }

        @Override // android.content.pm.PackageManager
        public int getApplicationEnabledSetting(String str) {
            return this.f404b.getApplicationEnabledSetting(str);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getApplicationIcon(String str) throws PackageManager.NameNotFoundException {
            return this.f404b.getApplicationIcon(str);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getApplicationIcon(ApplicationInfo applicationInfo) {
            return this.f404b.getApplicationIcon(applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public CharSequence getApplicationLabel(ApplicationInfo applicationInfo) {
            return this.f404b.getApplicationLabel(applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getApplicationLogo(String str) throws PackageManager.NameNotFoundException {
            return this.f404b.getApplicationLogo(str);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getApplicationLogo(ApplicationInfo applicationInfo) {
            return this.f404b.getApplicationLogo(applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public int getComponentEnabledSetting(ComponentName componentName) {
            return this.f404b.getComponentEnabledSetting(componentName);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getDefaultActivityIcon() {
            return this.f404b.getDefaultActivityIcon();
        }

        @Override // android.content.pm.PackageManager
        public Drawable getDrawable(String str, int i, ApplicationInfo applicationInfo) {
            return this.f404b.getDrawable(str, i, applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public List<ApplicationInfo> getInstalledApplications(int i) {
            return this.f404b.getInstalledApplications(i);
        }

        @Override // android.content.pm.PackageManager
        public List<PackageInfo> getInstalledPackages(int i) {
            return this.f404b.getInstalledPackages(i);
        }

        @Override // android.content.pm.PackageManager
        public String getInstallerPackageName(String str) {
            return this.f404b.getInstallerPackageName(str);
        }

        @Override // android.content.pm.PackageManager
        public InstrumentationInfo getInstrumentationInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getInstrumentationInfo(componentName, i);
        }

        @Override // android.content.pm.PackageManager
        public Intent getLaunchIntentForPackage(String str) {
            return this.f404b.getLaunchIntentForPackage(str);
        }

        @Override // android.content.pm.PackageManager
        public String getNameForUid(int i) {
            return this.f404b.getNameForUid(i);
        }

        @Override // android.content.pm.PackageManager
        public int[] getPackageGids(String str) throws PackageManager.NameNotFoundException {
            return this.f404b.getPackageGids(str);
        }

        @Override // android.content.pm.PackageManager
        public String[] getPackagesForUid(int i) {
            return this.f404b.getPackagesForUid(i);
        }

        @Override // android.content.pm.PackageManager
        public List<PackageInfo> getPackagesHoldingPermissions(String[] strArr, int i) {
            return this.f404b.getPackagesHoldingPermissions(strArr, i);
        }

        @Override // android.content.pm.PackageManager
        public PermissionGroupInfo getPermissionGroupInfo(String str, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getPermissionGroupInfo(str, i);
        }

        @Override // android.content.pm.PackageManager
        public PermissionInfo getPermissionInfo(String str, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getPermissionInfo(str, i);
        }

        @Override // android.content.pm.PackageManager
        public int getPreferredActivities(List<IntentFilter> list, List<ComponentName> list2, String str) {
            return this.f404b.getPreferredActivities(list, list2, str);
        }

        @Override // android.content.pm.PackageManager
        public List<PackageInfo> getPreferredPackages(int i) {
            return this.f404b.getPreferredPackages(i);
        }

        @Override // android.content.pm.PackageManager
        public ProviderInfo getProviderInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getProviderInfo(componentName, i);
        }

        @Override // android.content.pm.PackageManager
        public ActivityInfo getReceiverInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getReceiverInfo(componentName, i);
        }

        @Override // android.content.pm.PackageManager
        public Resources getResourcesForActivity(ComponentName componentName) throws PackageManager.NameNotFoundException {
            return this.f404b.getResourcesForActivity(componentName);
        }

        @Override // android.content.pm.PackageManager
        public Resources getResourcesForApplication(String str) throws PackageManager.NameNotFoundException {
            return this.f404b.getResourcesForApplication(str);
        }

        @Override // android.content.pm.PackageManager
        public Resources getResourcesForApplication(ApplicationInfo applicationInfo) throws PackageManager.NameNotFoundException {
            return this.f404b.getResourcesForApplication(applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public ServiceInfo getServiceInfo(ComponentName componentName, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.getServiceInfo(componentName, i);
        }

        @Override // android.content.pm.PackageManager
        public FeatureInfo[] getSystemAvailableFeatures() {
            return this.f404b.getSystemAvailableFeatures();
        }

        @Override // android.content.pm.PackageManager
        public String[] getSystemSharedLibraryNames() {
            return this.f404b.getSystemSharedLibraryNames();
        }

        @Override // android.content.pm.PackageManager
        public CharSequence getText(String str, int i, ApplicationInfo applicationInfo) {
            return this.f404b.getText(str, i, applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public XmlResourceParser getXml(String str, int i, ApplicationInfo applicationInfo) {
            return this.f404b.getXml(str, i, applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public boolean hasSystemFeature(String str) {
            return this.f404b.hasSystemFeature(str);
        }

        @Override // android.content.pm.PackageManager
        public boolean isSafeMode() {
            return this.f404b.isSafeMode();
        }

        @Override // android.content.pm.PackageManager
        public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int i) {
            return this.f404b.queryBroadcastReceivers(intent, i);
        }

        @Override // android.content.pm.PackageManager
        public List<ProviderInfo> queryContentProviders(String str, int i, int i2) {
            return this.f404b.queryContentProviders(str, i, i2);
        }

        @Override // android.content.pm.PackageManager
        public List<InstrumentationInfo> queryInstrumentation(String str, int i) {
            return this.f404b.queryInstrumentation(str, i);
        }

        @Override // android.content.pm.PackageManager
        public List<ResolveInfo> queryIntentActivities(Intent intent, int i) {
            return this.f404b.queryIntentActivities(intent, i);
        }

        @Override // android.content.pm.PackageManager
        public List<ResolveInfo> queryIntentActivityOptions(ComponentName componentName, Intent[] intentArr, Intent intent, int i) {
            return this.f404b.queryIntentActivityOptions(componentName, intentArr, intent, i);
        }

        @Override // android.content.pm.PackageManager
        public List<ResolveInfo> queryIntentContentProviders(Intent intent, int i) {
            return this.f404b.queryIntentContentProviders(intent, i);
        }

        @Override // android.content.pm.PackageManager
        public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
            return this.f404b.queryIntentServices(intent, i);
        }

        @Override // android.content.pm.PackageManager
        public List<PermissionInfo> queryPermissionsByGroup(String str, int i) throws PackageManager.NameNotFoundException {
            return this.f404b.queryPermissionsByGroup(str, i);
        }

        @Override // android.content.pm.PackageManager
        public void removePackageFromPreferred(String str) {
            this.f404b.removePackageFromPreferred(str);
        }

        @Override // android.content.pm.PackageManager
        public void removePermission(String str) {
            this.f404b.removePermission(str);
        }

        @Override // android.content.pm.PackageManager
        public ResolveInfo resolveActivity(Intent intent, int i) {
            return this.f404b.resolveActivity(intent, i);
        }

        @Override // android.content.pm.PackageManager
        public ProviderInfo resolveContentProvider(String str, int i) {
            return this.f404b.resolveContentProvider(str, i);
        }

        @Override // android.content.pm.PackageManager
        public ResolveInfo resolveService(Intent intent, int i) {
            return this.f404b.resolveService(intent, i);
        }

        @Override // android.content.pm.PackageManager
        public void setApplicationEnabledSetting(String str, int i, int i2) {
            this.f404b.setApplicationEnabledSetting(str, i, i2);
        }

        @Override // android.content.pm.PackageManager
        public void setComponentEnabledSetting(ComponentName componentName, int i, int i2) {
            this.f404b.setComponentEnabledSetting(componentName, i, i2);
        }

        @Override // android.content.pm.PackageManager
        public void setInstallerPackageName(String str, String str2) {
            this.f404b.setInstallerPackageName(str, str2);
        }

        @Override // android.content.pm.PackageManager
        public void verifyPendingInstall(int i, int i2) {
            this.f404b.verifyPendingInstall(i, i2);
        }

        @Override // android.content.pm.PackageManager
        public PackageInstaller getPackageInstaller() {
            return this.f404b.getPackageInstaller();
        }

        @Override // android.content.pm.PackageManager
        public CharSequence getUserBadgedLabel(CharSequence charSequence, UserHandle userHandle) {
            return this.f404b.getUserBadgedLabel(charSequence, userHandle);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle userHandle, Rect rect, int i) {
            return this.f404b.getUserBadgedDrawableForDensity(drawable, userHandle, rect, i);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getUserBadgedIcon(Drawable drawable, UserHandle userHandle) {
            return this.f404b.getUserBadgedIcon(drawable, userHandle);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getActivityBanner(Intent intent) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityBanner(intent);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getActivityBanner(ComponentName componentName) throws PackageManager.NameNotFoundException {
            return this.f404b.getActivityBanner(componentName);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getApplicationBanner(ApplicationInfo applicationInfo) {
            return this.f404b.getApplicationBanner(applicationInfo);
        }

        @Override // android.content.pm.PackageManager
        public Drawable getApplicationBanner(String str) throws PackageManager.NameNotFoundException {
            return this.f404b.getApplicationBanner(str);
        }

        @Override // android.content.pm.PackageManager
        public Intent getLeanbackLaunchIntentForPackage(String str) {
            return this.f404b.getLeanbackLaunchIntentForPackage(str);
        }
    }
}
