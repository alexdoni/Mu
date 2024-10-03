package com.p017xx.commom.glide.request.transition;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import com.p017xx.commom.glide.request.transition.Transition;

/* loaded from: classes3.dex */
public class ViewTransition<R> implements Transition<R> {
    private final ViewTransitionAnimationFactory viewTransitionAnimationFactory;

    /* loaded from: classes3.dex */
    interface ViewTransitionAnimationFactory {
        Animation build(Context context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewTransition(ViewTransitionAnimationFactory viewTransitionAnimationFactory) {
        this.viewTransitionAnimationFactory = viewTransitionAnimationFactory;
    }

    @Override // com.p017xx.commom.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        View view = viewAdapter.getView();
        if (view == null) {
            return false;
        }
        view.clearAnimation();
        view.startAnimation(this.viewTransitionAnimationFactory.build(view.getContext()));
        return false;
    }
}
