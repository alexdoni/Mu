package com.p017xx.commom.glide.request.transition;

import android.view.View;
import com.p017xx.commom.glide.request.transition.Transition;

/* loaded from: classes3.dex */
public class ViewPropertyTransition<R> implements Transition<R> {
    private final Animator animator;

    /* loaded from: classes3.dex */
    public interface Animator {
        void animate(View view);
    }

    public ViewPropertyTransition(Animator animator) {
        this.animator = animator;
    }

    @Override // com.p017xx.commom.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        if (viewAdapter.getView() == null) {
            return false;
        }
        this.animator.animate(viewAdapter.getView());
        return false;
    }
}
