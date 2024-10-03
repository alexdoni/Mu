package com.facebook.internal.qualityvalidation;

import com.facebook.share.internal.ShareConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;

/* compiled from: Excuse.kt */
@Target({})
@Metadata(m1394d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003R\u000f\u0010\u0004\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, m1395d2 = {"Lcom/facebook/internal/qualityvalidation/Excuse;", "", ShareConstants.MEDIA_TYPE, "", "reason", "()Ljava/lang/String;", "facebook-core_release"}, m1396k = 1, m1397mv = {1, 5, 1}, m1399xi = 48)
@kotlin.annotation.Target(allowedTargets = {})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface Excuse {
    String reason();

    String type();
}
