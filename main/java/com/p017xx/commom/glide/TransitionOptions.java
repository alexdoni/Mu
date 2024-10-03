package com.p017xx.commom.glide;

import com.p017xx.commom.glide.TransitionOptions;
import com.p017xx.commom.glide.request.transition.NoTransition;
import com.p017xx.commom.glide.request.transition.TransitionFactory;
import com.p017xx.commom.glide.request.transition.ViewAnimationFactory;
import com.p017xx.commom.glide.request.transition.ViewPropertyAnimationFactory;
import com.p017xx.commom.glide.request.transition.ViewPropertyTransition;
import com.p017xx.commom.glide.util.Preconditions;

/* loaded from: classes3.dex */
public abstract class TransitionOptions<CHILD extends TransitionOptions<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    private TransitionFactory<? super TranscodeType> transitionFactory = NoTransition.getFactory();

    private CHILD self() {
        return this;
    }

    public final CHILD dontTransition() {
        return transition(NoTransition.getFactory());
    }

    public final CHILD transition(int i) {
        return transition(new ViewAnimationFactory(i));
    }

    public final CHILD transition(ViewPropertyTransition.Animator animator) {
        return transition(new ViewPropertyAnimationFactory(animator));
    }

    public final CHILD transition(TransitionFactory<? super TranscodeType> transitionFactory) {
        this.transitionFactory = (TransitionFactory) Preconditions.checkNotNull(transitionFactory);
        return self();
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final CHILD m1877clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TransitionFactory<? super TranscodeType> getTransitionFactory() {
        return this.transitionFactory;
    }
}
