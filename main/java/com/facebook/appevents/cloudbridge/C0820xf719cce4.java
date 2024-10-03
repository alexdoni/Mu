package com.facebook.appevents.cloudbridge;

import com.facebook.internal.Utility;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppEventsConversionsAPITransformerWebRequests.kt */
@Metadata(m1394d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n"}, m1395d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "responseCode", ""}, m1396k = 3, m1397mv = {1, 5, 1}, m1399xi = 48)
/* renamed from: com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformerWebRequests$transformGraphRequestAndSendToCAPIGEndPoint$1$1 */
/* loaded from: classes.dex */
public final class C0820xf719cce4 extends Lambda implements Function2<String, Integer, Unit> {
    final /* synthetic */ List<Map<String, Object>> $processedEvents;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C0820xf719cce4(List<? extends Map<String, ? extends Object>> list) {
        super(2);
        this.$processedEvents = list;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, Integer num) {
        invoke2(str, num);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String str, final Integer num) {
        Utility utility = Utility.INSTANCE;
        final List<Map<String, Object>> list = this.$processedEvents;
        Utility.runOnNonUiThread(new Runnable() { // from class: com.facebook.appevents.cloudbridge.AppEventsConversionsAPITransformerWebRequests$transformGraphRequestAndSendToCAPIGEndPoint$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                C0820xf719cce4.m1673invoke$lambda0(num, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0, reason: not valid java name */
    public static final void m1673invoke$lambda0(Integer num, List processedEvents) {
        HashSet hashSet;
        Intrinsics.checkNotNullParameter(processedEvents, "$processedEvents");
        hashSet = AppEventsConversionsAPITransformerWebRequests.ACCEPTABLE_HTTP_RESPONSE;
        if (CollectionsKt.contains(hashSet, num)) {
            return;
        }
        AppEventsConversionsAPITransformerWebRequests.INSTANCE.handleError$facebook_core_release(num, processedEvents, 5);
    }
}
