package com.appsflyer.internal;

import com.appsflyer.internal.platform_extension.Plugin;
import com.appsflyer.internal.platform_extension.PluginInfo;
import com.facebook.internal.ServerProtocol;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFi1pSDK implements AFi1qSDK {
    private PluginInfo AFInAppEventParameterName = new PluginInfo(Plugin.NATIVE, "6.13.1", null, 4, null);

    @Override // com.appsflyer.internal.AFi1qSDK
    public final void AFInAppEventType(PluginInfo pluginInfo) {
        Intrinsics.checkNotNullParameter(pluginInfo, "");
        this.AFInAppEventParameterName = pluginInfo;
    }

    @Override // com.appsflyer.internal.AFi1qSDK
    public final Map<String, Object> valueOf() {
        Map<String, Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m1402to("platform", this.AFInAppEventParameterName.getPlugin().getPluginName()), TuplesKt.m1402to(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, this.AFInAppEventParameterName.getVersion()));
        if (!this.AFInAppEventParameterName.getAdditionalParams().isEmpty()) {
            mutableMapOf.put("extras", this.AFInAppEventParameterName.getAdditionalParams());
        }
        return mutableMapOf;
    }
}
