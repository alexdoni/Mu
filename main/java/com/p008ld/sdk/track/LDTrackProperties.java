package com.p008ld.sdk.track;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDTrackRequest.kt */
/* loaded from: classes2.dex */
public final class LDTrackProperties {
    private final String android_id;
    private final String app_package_name;
    private final String app_version;
    private final String channel_id;
    private final String device_id;
    private final int event_result;
    private final String failure_reason;
    private final String login_type;
    private final String mnq_mac;
    private final String mnq_version;
    private final String order_id;
    private final String sdk_version;
    private final String sub_app_id;
    private final String sub_channel_id;
    private final String user_id;

    public final String component1() {
        return this.sub_app_id;
    }

    public final String component10() {
        return this.device_id;
    }

    public final String component11() {
        return this.mnq_mac;
    }

    public final String component12() {
        return this.user_id;
    }

    public final String component13() {
        return this.login_type;
    }

    public final String component14() {
        return this.order_id;
    }

    public final String component15() {
        return this.failure_reason;
    }

    public final String component2() {
        return this.app_package_name;
    }

    public final String component3() {
        return this.channel_id;
    }

    public final String component4() {
        return this.sub_channel_id;
    }

    public final String component5() {
        return this.sdk_version;
    }

    public final String component6() {
        return this.app_version;
    }

    public final String component7() {
        return this.mnq_version;
    }

    public final String component8() {
        return this.android_id;
    }

    public final int component9() {
        return this.event_result;
    }

    public final LDTrackProperties copy(String sub_app_id, String app_package_name, String channel_id, String sub_channel_id, String sdk_version, String app_version, String mnq_version, String android_id, int i, String device_id, String mnq_mac, String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(sub_app_id, "sub_app_id");
        Intrinsics.checkNotNullParameter(app_package_name, "app_package_name");
        Intrinsics.checkNotNullParameter(channel_id, "channel_id");
        Intrinsics.checkNotNullParameter(sub_channel_id, "sub_channel_id");
        Intrinsics.checkNotNullParameter(sdk_version, "sdk_version");
        Intrinsics.checkNotNullParameter(app_version, "app_version");
        Intrinsics.checkNotNullParameter(mnq_version, "mnq_version");
        Intrinsics.checkNotNullParameter(android_id, "android_id");
        Intrinsics.checkNotNullParameter(device_id, "device_id");
        Intrinsics.checkNotNullParameter(mnq_mac, "mnq_mac");
        return new LDTrackProperties(sub_app_id, app_package_name, channel_id, sub_channel_id, sdk_version, app_version, mnq_version, android_id, i, device_id, mnq_mac, str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDTrackProperties)) {
            return false;
        }
        LDTrackProperties lDTrackProperties = (LDTrackProperties) obj;
        return Intrinsics.areEqual(this.sub_app_id, lDTrackProperties.sub_app_id) && Intrinsics.areEqual(this.app_package_name, lDTrackProperties.app_package_name) && Intrinsics.areEqual(this.channel_id, lDTrackProperties.channel_id) && Intrinsics.areEqual(this.sub_channel_id, lDTrackProperties.sub_channel_id) && Intrinsics.areEqual(this.sdk_version, lDTrackProperties.sdk_version) && Intrinsics.areEqual(this.app_version, lDTrackProperties.app_version) && Intrinsics.areEqual(this.mnq_version, lDTrackProperties.mnq_version) && Intrinsics.areEqual(this.android_id, lDTrackProperties.android_id) && this.event_result == lDTrackProperties.event_result && Intrinsics.areEqual(this.device_id, lDTrackProperties.device_id) && Intrinsics.areEqual(this.mnq_mac, lDTrackProperties.mnq_mac) && Intrinsics.areEqual(this.user_id, lDTrackProperties.user_id) && Intrinsics.areEqual(this.login_type, lDTrackProperties.login_type) && Intrinsics.areEqual(this.order_id, lDTrackProperties.order_id) && Intrinsics.areEqual(this.failure_reason, lDTrackProperties.failure_reason);
    }

    public int hashCode() {
        int hashCode = ((((((((((((((((((((this.sub_app_id.hashCode() * 31) + this.app_package_name.hashCode()) * 31) + this.channel_id.hashCode()) * 31) + this.sub_channel_id.hashCode()) * 31) + this.sdk_version.hashCode()) * 31) + this.app_version.hashCode()) * 31) + this.mnq_version.hashCode()) * 31) + this.android_id.hashCode()) * 31) + this.event_result) * 31) + this.device_id.hashCode()) * 31) + this.mnq_mac.hashCode()) * 31;
        String str = this.user_id;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.login_type;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.order_id;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.failure_reason;
        return hashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "LDTrackProperties(sub_app_id=" + this.sub_app_id + ", app_package_name=" + this.app_package_name + ", channel_id=" + this.channel_id + ", sub_channel_id=" + this.sub_channel_id + ", sdk_version=" + this.sdk_version + ", app_version=" + this.app_version + ", mnq_version=" + this.mnq_version + ", android_id=" + this.android_id + ", event_result=" + this.event_result + ", device_id=" + this.device_id + ", mnq_mac=" + this.mnq_mac + ", user_id=" + this.user_id + ", login_type=" + this.login_type + ", order_id=" + this.order_id + ", failure_reason=" + this.failure_reason + ')';
    }

    public LDTrackProperties(String sub_app_id, String app_package_name, String channel_id, String sub_channel_id, String sdk_version, String app_version, String mnq_version, String android_id, int i, String device_id, String mnq_mac, String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(sub_app_id, "sub_app_id");
        Intrinsics.checkNotNullParameter(app_package_name, "app_package_name");
        Intrinsics.checkNotNullParameter(channel_id, "channel_id");
        Intrinsics.checkNotNullParameter(sub_channel_id, "sub_channel_id");
        Intrinsics.checkNotNullParameter(sdk_version, "sdk_version");
        Intrinsics.checkNotNullParameter(app_version, "app_version");
        Intrinsics.checkNotNullParameter(mnq_version, "mnq_version");
        Intrinsics.checkNotNullParameter(android_id, "android_id");
        Intrinsics.checkNotNullParameter(device_id, "device_id");
        Intrinsics.checkNotNullParameter(mnq_mac, "mnq_mac");
        this.sub_app_id = sub_app_id;
        this.app_package_name = app_package_name;
        this.channel_id = channel_id;
        this.sub_channel_id = sub_channel_id;
        this.sdk_version = sdk_version;
        this.app_version = app_version;
        this.mnq_version = mnq_version;
        this.android_id = android_id;
        this.event_result = i;
        this.device_id = device_id;
        this.mnq_mac = mnq_mac;
        this.user_id = str;
        this.login_type = str2;
        this.order_id = str3;
        this.failure_reason = str4;
    }

    public final String getSub_app_id() {
        return this.sub_app_id;
    }

    public final String getApp_package_name() {
        return this.app_package_name;
    }

    public final String getChannel_id() {
        return this.channel_id;
    }

    public final String getSub_channel_id() {
        return this.sub_channel_id;
    }

    public final String getSdk_version() {
        return this.sdk_version;
    }

    public final String getApp_version() {
        return this.app_version;
    }

    public final String getMnq_version() {
        return this.mnq_version;
    }

    public final String getAndroid_id() {
        return this.android_id;
    }

    public final int getEvent_result() {
        return this.event_result;
    }

    public final String getDevice_id() {
        return this.device_id;
    }

    public final String getMnq_mac() {
        return this.mnq_mac;
    }

    public final String getUser_id() {
        return this.user_id;
    }

    public final String getLogin_type() {
        return this.login_type;
    }

    public final String getOrder_id() {
        return this.order_id;
    }

    public final String getFailure_reason() {
        return this.failure_reason;
    }
}
