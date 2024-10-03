package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashlyticsRemoteConfigListener.kt */
@Metadata(m1394d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m1395d2 = {"Lcom/google/firebase/crashlytics/internal/CrashlyticsRemoteConfigListener;", "Lcom/google/firebase/remoteconfig/interop/rollouts/RolloutsStateSubscriber;", "userMetadata", "Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;", "(Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;)V", "onRolloutsStateChanged", "", "rolloutsState", "Lcom/google/firebase/remoteconfig/interop/rollouts/RolloutsState;", "com.google.firebase-firebase-crashlytics"}, m1396k = 1, m1397mv = {1, 7, 1}, m1399xi = 48)
/* loaded from: classes2.dex */
public final class CrashlyticsRemoteConfigListener implements RolloutsStateSubscriber {
    private final UserMetadata userMetadata;

    public CrashlyticsRemoteConfigListener(UserMetadata userMetadata) {
        Intrinsics.checkNotNullParameter(userMetadata, "userMetadata");
        this.userMetadata = userMetadata;
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber
    public void onRolloutsStateChanged(RolloutsState rolloutsState) {
        Intrinsics.checkNotNullParameter(rolloutsState, "rolloutsState");
        UserMetadata userMetadata = this.userMetadata;
        Set<RolloutAssignment> rolloutAssignments = rolloutsState.getRolloutAssignments();
        Intrinsics.checkNotNullExpressionValue(rolloutAssignments, "rolloutsState.rolloutAssignments");
        Set<RolloutAssignment> set = rolloutAssignments;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        for (RolloutAssignment rolloutAssignment : set) {
            arrayList.add(com.google.firebase.crashlytics.internal.metadata.RolloutAssignment.create(rolloutAssignment.getRolloutId(), rolloutAssignment.getParameterKey(), rolloutAssignment.getParameterValue(), rolloutAssignment.getVariantId(), rolloutAssignment.getTemplateVersion()));
        }
        userMetadata.updateRolloutsState(arrayList);
        Logger.getLogger().m538d("Updated Crashlytics Rollout State");
    }
}
