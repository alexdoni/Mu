package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class GooglePlayInAppReview {
    private static GooglePlayInAppReview instance;
    private final String TAG = "GooglePlayInAppReview";
    ReviewManager manager = null;
    ReviewInfo reviewInfo = null;

    public static GooglePlayInAppReview getInstance() {
        if (instance == null) {
            instance = new GooglePlayInAppReview();
        }
        return instance;
    }

    public void init(Activity activity) {
        this.manager = ReviewManagerFactory.create(activity);
        getReviewInfo(activity);
    }

    private void getReviewInfo(Activity activity) {
        ReviewManager reviewManager = this.manager;
        if (reviewManager == null) {
            init(activity);
        } else {
            reviewManager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() { // from class: com.oversea.ab_firstarea.haiwai.GooglePlayInAppReview.1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<ReviewInfo> task) {
                    try {
                        if (task == null) {
                            LLog.e_noControl("GooglePlayInAppReview getReviewInfo onComplete task null");
                            return;
                        }
                        if (task.isSuccessful()) {
                            LLog.i_noControl("GooglePlayInAppReview getReviewInfo reviewInfo success");
                            GooglePlayInAppReview.this.reviewInfo = task.getResult();
                        } else {
                            LLog.e_noControl("GooglePlayInAppReview getReviewInfo reviewInfo faile:" + task.getException().getMessage());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    public void launchReviewFlow(Activity activity) {
        LLog.i_noControl("GooglePlayInAppReview launchReviewFlow");
        ReviewInfo reviewInfo = this.reviewInfo;
        if (reviewInfo == null) {
            LLog.e_noControl("GooglePlayInAppReview launchReviewFlow reviewInfo null");
            getReviewInfo(activity);
            return;
        }
        ReviewManager reviewManager = this.manager;
        if (reviewManager == null) {
            LLog.e_noControl("GooglePlayInAppReview launchReviewFlow manager null");
        } else {
            reviewManager.launchReviewFlow(activity, reviewInfo).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.oversea.ab_firstarea.haiwai.GooglePlayInAppReview.2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    if (task == null) {
                        LLog.e_noControl("GooglePlayInAppReview launchReviewFlow onComplete task null");
                    } else if (task.isSuccessful()) {
                        LLog.i_noControl("GooglePlayInAppReview success");
                    }
                }
            });
        }
    }
}
