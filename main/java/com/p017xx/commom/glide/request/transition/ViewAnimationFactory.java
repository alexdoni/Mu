package com.p017xx.commom.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.request.transition.ViewTransition;

/* loaded from: classes3.dex */
public class ViewAnimationFactory<R> implements TransitionFactory<R> {
    private Transition<R> transition;
    private final ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory;

    public ViewAnimationFactory(Animation animation) {
        this(new ConcreteViewTransitionAnimationFactory(animation));
    }

    public ViewAnimationFactory(int i) {
        this(new ResourceViewTransitionAnimationFactory(i));
    }

    ViewAnimationFactory(ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory) {
        this.viewTransitionAnimationFactory = viewTransitionAnimationFactory;
    }

    @Override // com.p017xx.commom.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.get();
        }
        if (this.transition == null) {
            this.transition = new ViewTransition(this.viewTransitionAnimationFactory);
        }
        return this.transition;
    }

    /* loaded from: classes3.dex */
    private static class ConcreteViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        private final Animation animation;

        ConcreteViewTransitionAnimationFactory(Animation animation) {
            this.animation = animation;
        }

        @Override // com.xx.commom.glide.request.transition.ViewTransition.ViewTransitionAnimationFactory
        public Animation build(Context context) {
            return this.animation;
        }
    }

    /* loaded from: classes3.dex */
    private static class ResourceViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        private final int animationId;

        ResourceViewTransitionAnimationFactory(int i) {
            this.animationId = i;
        }

        @Override // com.xx.commom.glide.request.transition.ViewTransition.ViewTransitionAnimationFactory
        public Animation build(Context context) {
            return AnimationUtils.loadAnimation(context, this.animationId);
        }
    }
}
