package com.facebook.appevents.cloudbridge;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.cloudbridge.AppEventType;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AppEventsConversionsAPITransformer.kt */
@Metadata(m1394d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001:\u00042345B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0089\u0001\u0010\u000e\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0018\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00060\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\u0018JJ\u0010\u0019\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006\u0018\u00010\u000f2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0018\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00060\u000fH\u0002J:\u0010\u001b\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006\u0018\u00010\u000f2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0002JU\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\u0000¢\u0006\u0002\b\u001dJ5\u0010\u001e\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006\u0018\u00010\u000f2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\u0000¢\u0006\u0002\b J\u0088\u0001\u0010!\u001a\u00020\u00112\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132.\u0010\u0016\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00060\"j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006`#2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0013H\u0002JE\u0010$\u001a\u00020%2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u0001H\u0000¢\u0006\u0002\b(J,\u0010)\u001a\u00020%2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u0001H\u0002J,\u0010*\u001a\u00020%2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u0001H\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0002JA\u0010-\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006\u0018\u00010\"j\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006\u0018\u0001`#2\u0006\u0010.\u001a\u00020\u0004H\u0001¢\u0006\u0002\b/J\u001f\u00100\u001a\u0004\u0018\u00010\u00012\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0001H\u0001¢\u0006\u0002\b1R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer;", "", "()V", "TAG", "", "customEventTransformations", "", "Lcom/facebook/appevents/cloudbridge/CustomEventField;", "Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$SectionCustomEventFieldMapping;", "standardEventTransformations", "Lcom/facebook/appevents/cloudbridge/ConversionsAPIEventName;", "topLevelTransformations", "Lcom/facebook/appevents/cloudbridge/AppEventUserAndAppDataField;", "Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$SectionFieldMapping;", "combineAllTransformedData", "", "eventType", "Lcom/facebook/appevents/cloudbridge/AppEventType;", "userData", "", "appData", "restOfData", "customEvents", "eventTime", "combineAllTransformedData$facebook_core_release", "combineAllTransformedDataForCustom", "commonFields", "combineAllTransformedDataForMobileAppInstall", "combineCommonFields", "combineCommonFields$facebook_core_release", "conversionsAPICompatibleEvent", "parameters", "conversionsAPICompatibleEvent$facebook_core_release", "splitAppEventParameters", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "transformAndUpdateAppAndUserData", "", "field", "value", "transformAndUpdateAppAndUserData$facebook_core_release", "transformAndUpdateAppData", "transformAndUpdateUserData", "transformEventName", "input", "transformEvents", "appEvents", "transformEvents$facebook_core_release", "transformValue", "transformValue$facebook_core_release", "DataProcessingParameterName", "SectionCustomEventFieldMapping", "SectionFieldMapping", "ValueTransformationType", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AppEventsConversionsAPITransformer {
    public static final String TAG = "AppEventsConversionsAPITransformer";
    public static final AppEventsConversionsAPITransformer INSTANCE = new AppEventsConversionsAPITransformer();
    private static final Map<AppEventUserAndAppDataField, SectionFieldMapping> topLevelTransformations = MapsKt.mapOf(TuplesKt.m1402to(AppEventUserAndAppDataField.ANON_ID, new SectionFieldMapping(ConversionsAPISection.USER_DATA, ConversionsAPIUserAndAppDataField.ANON_ID)), TuplesKt.m1402to(AppEventUserAndAppDataField.APP_USER_ID, new SectionFieldMapping(ConversionsAPISection.USER_DATA, ConversionsAPIUserAndAppDataField.FB_LOGIN_ID)), TuplesKt.m1402to(AppEventUserAndAppDataField.ADVERTISER_ID, new SectionFieldMapping(ConversionsAPISection.USER_DATA, ConversionsAPIUserAndAppDataField.MAD_ID)), TuplesKt.m1402to(AppEventUserAndAppDataField.PAGE_ID, new SectionFieldMapping(ConversionsAPISection.USER_DATA, ConversionsAPIUserAndAppDataField.PAGE_ID)), TuplesKt.m1402to(AppEventUserAndAppDataField.PAGE_SCOPED_USER_ID, new SectionFieldMapping(ConversionsAPISection.USER_DATA, ConversionsAPIUserAndAppDataField.PAGE_SCOPED_USER_ID)), TuplesKt.m1402to(AppEventUserAndAppDataField.ADV_TE, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.ADV_TE)), TuplesKt.m1402to(AppEventUserAndAppDataField.APP_TE, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.APP_TE)), TuplesKt.m1402to(AppEventUserAndAppDataField.CONSIDER_VIEWS, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.CONSIDER_VIEWS)), TuplesKt.m1402to(AppEventUserAndAppDataField.DEVICE_TOKEN, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.DEVICE_TOKEN)), TuplesKt.m1402to(AppEventUserAndAppDataField.EXT_INFO, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.EXT_INFO)), TuplesKt.m1402to(AppEventUserAndAppDataField.INCLUDE_DWELL_DATA, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.INCLUDE_DWELL_DATA)), TuplesKt.m1402to(AppEventUserAndAppDataField.INCLUDE_VIDEO_DATA, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.INCLUDE_VIDEO_DATA)), TuplesKt.m1402to(AppEventUserAndAppDataField.INSTALL_REFERRER, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.INSTALL_REFERRER)), TuplesKt.m1402to(AppEventUserAndAppDataField.INSTALLER_PACKAGE, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.INSTALLER_PACKAGE)), TuplesKt.m1402to(AppEventUserAndAppDataField.RECEIPT_DATA, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.RECEIPT_DATA)), TuplesKt.m1402to(AppEventUserAndAppDataField.URL_SCHEMES, new SectionFieldMapping(ConversionsAPISection.APP_DATA, ConversionsAPIUserAndAppDataField.URL_SCHEMES)), TuplesKt.m1402to(AppEventUserAndAppDataField.USER_DATA, new SectionFieldMapping(ConversionsAPISection.USER_DATA, null)));
    public static final Map<CustomEventField, SectionCustomEventFieldMapping> customEventTransformations = MapsKt.mapOf(TuplesKt.m1402to(CustomEventField.EVENT_TIME, new SectionCustomEventFieldMapping(null, ConversionsAPICustomEventField.EVENT_TIME)), TuplesKt.m1402to(CustomEventField.EVENT_NAME, new SectionCustomEventFieldMapping(null, ConversionsAPICustomEventField.EVENT_NAME)), TuplesKt.m1402to(CustomEventField.VALUE_TO_SUM, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.VALUE_TO_SUM)), TuplesKt.m1402to(CustomEventField.CONTENT_IDS, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.CONTENT_IDS)), TuplesKt.m1402to(CustomEventField.CONTENTS, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.CONTENTS)), TuplesKt.m1402to(CustomEventField.CONTENT_TYPE, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.CONTENT_TYPE)), TuplesKt.m1402to(CustomEventField.CURRENCY, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.CURRENCY)), TuplesKt.m1402to(CustomEventField.DESCRIPTION, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.DESCRIPTION)), TuplesKt.m1402to(CustomEventField.LEVEL, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.LEVEL)), TuplesKt.m1402to(CustomEventField.MAX_RATING_VALUE, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.MAX_RATING_VALUE)), TuplesKt.m1402to(CustomEventField.NUM_ITEMS, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.NUM_ITEMS)), TuplesKt.m1402to(CustomEventField.PAYMENT_INFO_AVAILABLE, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.PAYMENT_INFO_AVAILABLE)), TuplesKt.m1402to(CustomEventField.REGISTRATION_METHOD, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.REGISTRATION_METHOD)), TuplesKt.m1402to(CustomEventField.SEARCH_STRING, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.SEARCH_STRING)), TuplesKt.m1402to(CustomEventField.SUCCESS, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.SUCCESS)), TuplesKt.m1402to(CustomEventField.ORDER_ID, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.ORDER_ID)), TuplesKt.m1402to(CustomEventField.AD_TYPE, new SectionCustomEventFieldMapping(ConversionsAPISection.CUSTOM_DATA, ConversionsAPICustomEventField.AD_TYPE)));
    public static final Map<String, ConversionsAPIEventName> standardEventTransformations = MapsKt.mapOf(TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_UNLOCKED_ACHIEVEMENT, ConversionsAPIEventName.UNLOCKED_ACHIEVEMENT), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_ACTIVATED_APP, ConversionsAPIEventName.ACTIVATED_APP), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_ADDED_PAYMENT_INFO, ConversionsAPIEventName.ADDED_PAYMENT_INFO), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_ADDED_TO_CART, ConversionsAPIEventName.ADDED_TO_CART), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_ADDED_TO_WISHLIST, ConversionsAPIEventName.ADDED_TO_WISHLIST), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION, ConversionsAPIEventName.COMPLETED_REGISTRATION), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT, ConversionsAPIEventName.VIEWED_CONTENT), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT, ConversionsAPIEventName.INITIATED_CHECKOUT), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_ACHIEVED_LEVEL, ConversionsAPIEventName.ACHIEVED_LEVEL), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_PURCHASED, ConversionsAPIEventName.PURCHASED), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_RATED, ConversionsAPIEventName.RATED), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_SEARCHED, ConversionsAPIEventName.SEARCHED), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_SPENT_CREDITS, ConversionsAPIEventName.SPENT_CREDITS), TuplesKt.m1402to(AppEventsConstants.EVENT_NAME_COMPLETED_TUTORIAL, ConversionsAPIEventName.COMPLETED_TUTORIAL));

    /* compiled from: AppEventsConversionsAPITransformer.kt */
    @Metadata(m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[ValueTransformationType.valuesCustom().length];
            iArr[ValueTransformationType.ARRAY.ordinal()] = 1;
            iArr[ValueTransformationType.BOOL.ordinal()] = 2;
            iArr[ValueTransformationType.INT.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ConversionsAPISection.valuesCustom().length];
            iArr2[ConversionsAPISection.APP_DATA.ordinal()] = 1;
            iArr2[ConversionsAPISection.USER_DATA.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[AppEventType.valuesCustom().length];
            iArr3[AppEventType.MOBILE_APP_INSTALL.ordinal()] = 1;
            iArr3[AppEventType.CUSTOM.ordinal()] = 2;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private AppEventsConversionsAPITransformer() {
    }

    /* compiled from: AppEventsConversionsAPITransformer.kt */
    @Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$SectionFieldMapping;", "", "section", "Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;", "field", "Lcom/facebook/appevents/cloudbridge/ConversionsAPIUserAndAppDataField;", "(Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;Lcom/facebook/appevents/cloudbridge/ConversionsAPIUserAndAppDataField;)V", "getField", "()Lcom/facebook/appevents/cloudbridge/ConversionsAPIUserAndAppDataField;", "setField", "(Lcom/facebook/appevents/cloudbridge/ConversionsAPIUserAndAppDataField;)V", "getSection", "()Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;", "setSection", "(Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final /* data */ class SectionFieldMapping {
        private ConversionsAPIUserAndAppDataField field;
        private ConversionsAPISection section;

        public static /* synthetic */ SectionFieldMapping copy$default(SectionFieldMapping sectionFieldMapping, ConversionsAPISection conversionsAPISection, ConversionsAPIUserAndAppDataField conversionsAPIUserAndAppDataField, int i, Object obj) {
            if ((i & 1) != 0) {
                conversionsAPISection = sectionFieldMapping.section;
            }
            if ((i & 2) != 0) {
                conversionsAPIUserAndAppDataField = sectionFieldMapping.field;
            }
            return sectionFieldMapping.copy(conversionsAPISection, conversionsAPIUserAndAppDataField);
        }

        /* renamed from: component1, reason: from getter */
        public final ConversionsAPISection getSection() {
            return this.section;
        }

        /* renamed from: component2, reason: from getter */
        public final ConversionsAPIUserAndAppDataField getField() {
            return this.field;
        }

        public final SectionFieldMapping copy(ConversionsAPISection section, ConversionsAPIUserAndAppDataField field) {
            Intrinsics.checkNotNullParameter(section, "section");
            return new SectionFieldMapping(section, field);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SectionFieldMapping)) {
                return false;
            }
            SectionFieldMapping sectionFieldMapping = (SectionFieldMapping) other;
            return this.section == sectionFieldMapping.section && this.field == sectionFieldMapping.field;
        }

        public int hashCode() {
            int hashCode = this.section.hashCode() * 31;
            ConversionsAPIUserAndAppDataField conversionsAPIUserAndAppDataField = this.field;
            return hashCode + (conversionsAPIUserAndAppDataField == null ? 0 : conversionsAPIUserAndAppDataField.hashCode());
        }

        public String toString() {
            return "SectionFieldMapping(section=" + this.section + ", field=" + this.field + ')';
        }

        public SectionFieldMapping(ConversionsAPISection section, ConversionsAPIUserAndAppDataField conversionsAPIUserAndAppDataField) {
            Intrinsics.checkNotNullParameter(section, "section");
            this.section = section;
            this.field = conversionsAPIUserAndAppDataField;
        }

        public final ConversionsAPISection getSection() {
            return this.section;
        }

        public final void setSection(ConversionsAPISection conversionsAPISection) {
            Intrinsics.checkNotNullParameter(conversionsAPISection, "<set-?>");
            this.section = conversionsAPISection;
        }

        public final ConversionsAPIUserAndAppDataField getField() {
            return this.field;
        }

        public final void setField(ConversionsAPIUserAndAppDataField conversionsAPIUserAndAppDataField) {
            this.field = conversionsAPIUserAndAppDataField;
        }
    }

    /* compiled from: AppEventsConversionsAPITransformer.kt */
    @Metadata(m1394d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$SectionCustomEventFieldMapping;", "", "section", "Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;", "field", "Lcom/facebook/appevents/cloudbridge/ConversionsAPICustomEventField;", "(Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;Lcom/facebook/appevents/cloudbridge/ConversionsAPICustomEventField;)V", "getField", "()Lcom/facebook/appevents/cloudbridge/ConversionsAPICustomEventField;", "setField", "(Lcom/facebook/appevents/cloudbridge/ConversionsAPICustomEventField;)V", "getSection", "()Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;", "setSection", "(Lcom/facebook/appevents/cloudbridge/ConversionsAPISection;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public static final /* data */ class SectionCustomEventFieldMapping {
        private ConversionsAPICustomEventField field;
        private ConversionsAPISection section;

        public static /* synthetic */ SectionCustomEventFieldMapping copy$default(SectionCustomEventFieldMapping sectionCustomEventFieldMapping, ConversionsAPISection conversionsAPISection, ConversionsAPICustomEventField conversionsAPICustomEventField, int i, Object obj) {
            if ((i & 1) != 0) {
                conversionsAPISection = sectionCustomEventFieldMapping.section;
            }
            if ((i & 2) != 0) {
                conversionsAPICustomEventField = sectionCustomEventFieldMapping.field;
            }
            return sectionCustomEventFieldMapping.copy(conversionsAPISection, conversionsAPICustomEventField);
        }

        /* renamed from: component1, reason: from getter */
        public final ConversionsAPISection getSection() {
            return this.section;
        }

        /* renamed from: component2, reason: from getter */
        public final ConversionsAPICustomEventField getField() {
            return this.field;
        }

        public final SectionCustomEventFieldMapping copy(ConversionsAPISection section, ConversionsAPICustomEventField field) {
            Intrinsics.checkNotNullParameter(field, "field");
            return new SectionCustomEventFieldMapping(section, field);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SectionCustomEventFieldMapping)) {
                return false;
            }
            SectionCustomEventFieldMapping sectionCustomEventFieldMapping = (SectionCustomEventFieldMapping) other;
            return this.section == sectionCustomEventFieldMapping.section && this.field == sectionCustomEventFieldMapping.field;
        }

        public int hashCode() {
            ConversionsAPISection conversionsAPISection = this.section;
            return ((conversionsAPISection == null ? 0 : conversionsAPISection.hashCode()) * 31) + this.field.hashCode();
        }

        public String toString() {
            return "SectionCustomEventFieldMapping(section=" + this.section + ", field=" + this.field + ')';
        }

        public SectionCustomEventFieldMapping(ConversionsAPISection conversionsAPISection, ConversionsAPICustomEventField field) {
            Intrinsics.checkNotNullParameter(field, "field");
            this.section = conversionsAPISection;
            this.field = field;
        }

        public final ConversionsAPISection getSection() {
            return this.section;
        }

        public final void setSection(ConversionsAPISection conversionsAPISection) {
            this.section = conversionsAPISection;
        }

        public final ConversionsAPICustomEventField getField() {
            return this.field;
        }

        public final void setField(ConversionsAPICustomEventField conversionsAPICustomEventField) {
            Intrinsics.checkNotNullParameter(conversionsAPICustomEventField, "<set-?>");
            this.field = conversionsAPICustomEventField;
        }
    }

    /* compiled from: AppEventsConversionsAPITransformer.kt */
    @Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$DataProcessingParameterName;", "", "rawValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getRawValue", "()Ljava/lang/String;", "OPTIONS", "COUNTRY", "STATE", "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public enum DataProcessingParameterName {
        OPTIONS(FacebookSdk.DATA_PROCESSION_OPTIONS),
        COUNTRY(FacebookSdk.DATA_PROCESSION_OPTIONS_COUNTRY),
        STATE(FacebookSdk.DATA_PROCESSION_OPTIONS_STATE);


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String rawValue;

        DataProcessingParameterName(String str) {
            this.rawValue = str;
        }

        public final String getRawValue() {
            return this.rawValue;
        }

        /* compiled from: AppEventsConversionsAPITransformer.kt */
        @Metadata(m1394d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\u0002¨\u0006\u0007"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$DataProcessingParameterName$Companion;", "", "()V", "invoke", "Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$DataProcessingParameterName;", "rawValue", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DataProcessingParameterName invoke(String rawValue) {
                Intrinsics.checkNotNullParameter(rawValue, "rawValue");
                for (DataProcessingParameterName dataProcessingParameterName : DataProcessingParameterName.valuesCustom()) {
                    if (Intrinsics.areEqual(dataProcessingParameterName.getRawValue(), rawValue)) {
                        return dataProcessingParameterName;
                    }
                }
                return null;
            }
        }

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static DataProcessingParameterName[] valuesCustom() {
            DataProcessingParameterName[] valuesCustom = values();
            return (DataProcessingParameterName[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
        }
    }

    /* compiled from: AppEventsConversionsAPITransformer.kt */
    @Metadata(m1394d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$ValueTransformationType;", "", "(Ljava/lang/String;I)V", "ARRAY", "BOOL", "INT", "Companion", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
    /* loaded from: classes.dex */
    public enum ValueTransformationType {
        ARRAY,
        BOOL,
        INT;


        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* compiled from: AppEventsConversionsAPITransformer.kt */
        @Metadata(m1394d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\u0002¨\u0006\u0007"}, m1395d2 = {"Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$ValueTransformationType$Companion;", "", "()V", "invoke", "Lcom/facebook/appevents/cloudbridge/AppEventsConversionsAPITransformer$ValueTransformationType;", "rawValue", "", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ValueTransformationType invoke(String rawValue) {
                Intrinsics.checkNotNullParameter(rawValue, "rawValue");
                if (!Intrinsics.areEqual(rawValue, AppEventUserAndAppDataField.EXT_INFO.getRawValue()) && !Intrinsics.areEqual(rawValue, AppEventUserAndAppDataField.URL_SCHEMES.getRawValue()) && !Intrinsics.areEqual(rawValue, CustomEventField.CONTENT_IDS.getRawValue()) && !Intrinsics.areEqual(rawValue, CustomEventField.CONTENTS.getRawValue()) && !Intrinsics.areEqual(rawValue, DataProcessingParameterName.OPTIONS.getRawValue())) {
                    if (!Intrinsics.areEqual(rawValue, AppEventUserAndAppDataField.ADV_TE.getRawValue()) && !Intrinsics.areEqual(rawValue, AppEventUserAndAppDataField.APP_TE.getRawValue())) {
                        if (Intrinsics.areEqual(rawValue, CustomEventField.EVENT_TIME.getRawValue())) {
                            return ValueTransformationType.INT;
                        }
                        return null;
                    }
                    return ValueTransformationType.BOOL;
                }
                return ValueTransformationType.ARRAY;
            }
        }

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static ValueTransformationType[] valuesCustom() {
            ValueTransformationType[] valuesCustom = values();
            return (ValueTransformationType[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.Map] */
    @JvmStatic
    public static final Object transformValue$facebook_core_release(String field, Object value) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        ValueTransformationType invoke = ValueTransformationType.INSTANCE.invoke(field);
        String str = value instanceof String ? (String) value : null;
        if (invoke == null || str == null) {
            return value;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[invoke.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return StringsKt.toIntOrNull(value.toString());
                }
                throw new NoWhenBranchMatchedException();
            }
            Integer intOrNull = StringsKt.toIntOrNull(str.toString());
            if (intOrNull != null) {
                return Boolean.valueOf(intOrNull.intValue() != 0);
            }
            return null;
        }
        try {
            Utility utility = Utility.INSTANCE;
            List<String> convertJSONArrayToList = Utility.convertJSONArrayToList(new JSONArray(str));
            ArrayList arrayList = new ArrayList();
            Iterator it = convertJSONArrayToList.iterator();
            while (it.hasNext()) {
                ?? r1 = (String) it.next();
                try {
                    try {
                        Utility utility2 = Utility.INSTANCE;
                        r1 = Utility.convertJSONObjectToHashMap(new JSONObject((String) r1));
                    } catch (JSONException unused) {
                        Utility utility3 = Utility.INSTANCE;
                        r1 = Utility.convertJSONArrayToList(new JSONArray((String) r1));
                    }
                } catch (JSONException unused2) {
                }
                arrayList.add(r1);
            }
            return arrayList;
        } catch (JSONException e) {
            Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG, "\n transformEvents JSONException: \n%s\n%s", value, e);
            return Unit.INSTANCE;
        }
    }

    @JvmStatic
    public static final ArrayList<Map<String, Object>> transformEvents$facebook_core_release(String appEvents) {
        Intrinsics.checkNotNullParameter(appEvents, "appEvents");
        ArrayList<Map> arrayList = new ArrayList();
        try {
            Utility utility = Utility.INSTANCE;
            for (String str : Utility.convertJSONArrayToList(new JSONArray(appEvents))) {
                Utility utility2 = Utility.INSTANCE;
                arrayList.add(Utility.convertJSONObjectToHashMap(new JSONObject(str)));
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            ArrayList<Map<String, Object>> arrayList2 = new ArrayList<>();
            for (Map map : arrayList) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (String str2 : map.keySet()) {
                    CustomEventField invoke = CustomEventField.INSTANCE.invoke(str2);
                    SectionCustomEventFieldMapping sectionCustomEventFieldMapping = customEventTransformations.get(invoke);
                    if (invoke != null && sectionCustomEventFieldMapping != null) {
                        ConversionsAPISection section = sectionCustomEventFieldMapping.getSection();
                        if (section != null) {
                            if (section == ConversionsAPISection.CUSTOM_DATA) {
                                String rawValue = sectionCustomEventFieldMapping.getField().getRawValue();
                                Object obj = map.get(str2);
                                if (obj == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                                }
                                Object transformValue$facebook_core_release = transformValue$facebook_core_release(str2, obj);
                                if (transformValue$facebook_core_release != null) {
                                    linkedHashMap.put(rawValue, transformValue$facebook_core_release);
                                } else {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                                }
                            } else {
                                continue;
                            }
                        } else {
                            try {
                                String rawValue2 = sectionCustomEventFieldMapping.getField().getRawValue();
                                if (invoke == CustomEventField.EVENT_NAME && ((String) map.get(str2)) != null) {
                                    AppEventsConversionsAPITransformer appEventsConversionsAPITransformer = INSTANCE;
                                    Object obj2 = map.get(str2);
                                    if (obj2 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                    }
                                    linkedHashMap2.put(rawValue2, appEventsConversionsAPITransformer.transformEventName((String) obj2));
                                } else if (invoke == CustomEventField.EVENT_TIME && ((Integer) map.get(str2)) != null) {
                                    Object obj3 = map.get(str2);
                                    if (obj3 == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                                    }
                                    Object transformValue$facebook_core_release2 = transformValue$facebook_core_release(str2, obj3);
                                    if (transformValue$facebook_core_release2 != null) {
                                        linkedHashMap2.put(rawValue2, transformValue$facebook_core_release2);
                                    } else {
                                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                                    }
                                }
                            } catch (ClassCastException e) {
                                Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG, "\n transformEvents ClassCastException: \n %s ", ExceptionsKt.stackTraceToString(e));
                            }
                        }
                    }
                }
                if (!linkedHashMap.isEmpty()) {
                    linkedHashMap2.put(ConversionsAPISection.CUSTOM_DATA.getRawValue(), linkedHashMap);
                }
                arrayList2.add(linkedHashMap2);
            }
            return arrayList2;
        } catch (JSONException e2) {
            Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG, "\n transformEvents JSONException: \n%s\n%s", appEvents, e2);
            return null;
        }
    }

    private final void transformAndUpdateAppData(Map<String, Object> appData, AppEventUserAndAppDataField field, Object value) {
        SectionFieldMapping sectionFieldMapping = topLevelTransformations.get(field);
        ConversionsAPIUserAndAppDataField field2 = sectionFieldMapping == null ? null : sectionFieldMapping.getField();
        if (field2 == null) {
            return;
        }
        appData.put(field2.getRawValue(), value);
    }

    private final void transformAndUpdateUserData(Map<String, Object> userData, AppEventUserAndAppDataField field, Object value) {
        if (field == AppEventUserAndAppDataField.USER_DATA) {
            try {
                Utility utility = Utility.INSTANCE;
                userData.putAll(Utility.convertJSONObjectToHashMap(new JSONObject((String) value)));
                return;
            } catch (JSONException e) {
                Logger.INSTANCE.log(LoggingBehavior.APP_EVENTS, TAG, "\n transformEvents JSONException: \n%s\n%s", value, e);
                return;
            }
        }
        SectionFieldMapping sectionFieldMapping = topLevelTransformations.get(field);
        ConversionsAPIUserAndAppDataField field2 = sectionFieldMapping == null ? null : sectionFieldMapping.getField();
        if (field2 == null) {
            return;
        }
        userData.put(field2.getRawValue(), value);
    }

    public final void transformAndUpdateAppAndUserData$facebook_core_release(Map<String, Object> userData, Map<String, Object> appData, AppEventUserAndAppDataField field, Object value) {
        Intrinsics.checkNotNullParameter(userData, "userData");
        Intrinsics.checkNotNullParameter(appData, "appData");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        SectionFieldMapping sectionFieldMapping = topLevelTransformations.get(field);
        if (sectionFieldMapping == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[sectionFieldMapping.getSection().ordinal()];
        if (i == 1) {
            transformAndUpdateAppData(appData, field, value);
        } else {
            if (i != 2) {
                return;
            }
            transformAndUpdateUserData(userData, field, value);
        }
    }

    private final String transformEventName(String input) {
        Map<String, ConversionsAPIEventName> map = standardEventTransformations;
        if (!map.containsKey(input)) {
            return input;
        }
        ConversionsAPIEventName conversionsAPIEventName = map.get(input);
        return conversionsAPIEventName == null ? "" : conversionsAPIEventName.getRawValue();
    }

    public final Map<String, Object> combineCommonFields$facebook_core_release(Map<String, ? extends Object> userData, Map<String, ? extends Object> appData, Map<String, ? extends Object> restOfData) {
        Intrinsics.checkNotNullParameter(userData, "userData");
        Intrinsics.checkNotNullParameter(appData, "appData");
        Intrinsics.checkNotNullParameter(restOfData, "restOfData");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(OtherEventConstants.ACTION_SOURCE.getRawValue(), OtherEventConstants.APP.getRawValue());
        linkedHashMap.put(ConversionsAPISection.USER_DATA.getRawValue(), userData);
        linkedHashMap.put(ConversionsAPISection.APP_DATA.getRawValue(), appData);
        linkedHashMap.putAll(restOfData);
        return linkedHashMap;
    }

    private final List<Map<String, Object>> combineAllTransformedDataForMobileAppInstall(Map<String, ? extends Object> commonFields, Object eventTime) {
        if (eventTime == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(commonFields);
        linkedHashMap.put(ConversionsAPICustomEventField.EVENT_NAME.getRawValue(), OtherEventConstants.MOBILE_APP_INSTALL.getRawValue());
        linkedHashMap.put(ConversionsAPICustomEventField.EVENT_TIME.getRawValue(), eventTime);
        return CollectionsKt.listOf(linkedHashMap);
    }

    private final List<Map<String, Object>> combineAllTransformedDataForCustom(Map<String, ? extends Object> commonFields, List<? extends Map<String, ? extends Object>> customEvents) {
        if (customEvents.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = customEvents.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(commonFields);
            linkedHashMap.putAll(map);
            arrayList.add(linkedHashMap);
        }
        return arrayList;
    }

    public final List<Map<String, Object>> combineAllTransformedData$facebook_core_release(AppEventType eventType, Map<String, Object> userData, Map<String, Object> appData, Map<String, Object> restOfData, List<? extends Map<String, ? extends Object>> customEvents, Object eventTime) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        Intrinsics.checkNotNullParameter(userData, "userData");
        Intrinsics.checkNotNullParameter(appData, "appData");
        Intrinsics.checkNotNullParameter(restOfData, "restOfData");
        Intrinsics.checkNotNullParameter(customEvents, "customEvents");
        Map<String, Object> combineCommonFields$facebook_core_release = combineCommonFields$facebook_core_release(userData, appData, restOfData);
        int i = WhenMappings.$EnumSwitchMapping$2[eventType.ordinal()];
        if (i == 1) {
            return combineAllTransformedDataForMobileAppInstall(combineCommonFields$facebook_core_release, eventTime);
        }
        if (i != 2) {
            return null;
        }
        return combineAllTransformedDataForCustom(combineCommonFields$facebook_core_release, customEvents);
    }

    private final AppEventType splitAppEventParameters(Map<String, ? extends Object> parameters, Map<String, Object> userData, Map<String, Object> appData, ArrayList<Map<String, Object>> customEvents, Map<String, Object> restOfData) {
        Object obj = parameters.get(OtherEventConstants.EVENT.getRawValue());
        AppEventType.Companion companion = AppEventType.INSTANCE;
        if (obj != null) {
            AppEventType invoke = companion.invoke((String) obj);
            if (invoke == AppEventType.OTHER) {
                return invoke;
            }
            for (Map.Entry<String, ? extends Object> entry : parameters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                AppEventUserAndAppDataField invoke2 = AppEventUserAndAppDataField.INSTANCE.invoke(key);
                if (invoke2 != null) {
                    INSTANCE.transformAndUpdateAppAndUserData$facebook_core_release(userData, appData, invoke2, value);
                } else {
                    boolean areEqual = Intrinsics.areEqual(key, ConversionsAPISection.CUSTOM_EVENTS.getRawValue());
                    boolean z = value instanceof String;
                    if (invoke == AppEventType.CUSTOM && areEqual && z) {
                        ArrayList<Map<String, Object>> transformEvents$facebook_core_release = transformEvents$facebook_core_release((String) value);
                        if (transformEvents$facebook_core_release != null) {
                            customEvents.addAll(transformEvents$facebook_core_release);
                        }
                    } else if (DataProcessingParameterName.INSTANCE.invoke(key) != null) {
                        restOfData.put(key, value);
                    }
                }
            }
            return invoke;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public final List<Map<String, Object>> conversionsAPICompatibleEvent$facebook_core_release(Map<String, ? extends Object> parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        AppEventType splitAppEventParameters = splitAppEventParameters(parameters, linkedHashMap, linkedHashMap2, arrayList, linkedHashMap3);
        if (splitAppEventParameters == AppEventType.OTHER) {
            return null;
        }
        return combineAllTransformedData$facebook_core_release(splitAppEventParameters, linkedHashMap, linkedHashMap2, linkedHashMap3, arrayList, parameters.get(OtherEventConstants.INSTALL_EVENT_TIME.getRawValue()));
    }
}
