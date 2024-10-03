package com.facebook.bolts;

import android.net.Uri;
import kotlin.Metadata;

/* compiled from: AppLinkResolver.kt */
@Metadata(m1394d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, m1395d2 = {"Lcom/facebook/bolts/AppLinkResolver;", "", "getAppLinkFromUrlInBackground", "Lcom/facebook/bolts/Task;", "Lcom/facebook/bolts/AppLink;", "url", "Landroid/net/Uri;", "facebook-bolts_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public interface AppLinkResolver {
    Task<AppLink> getAppLinkFromUrlInBackground(Uri url);
}
