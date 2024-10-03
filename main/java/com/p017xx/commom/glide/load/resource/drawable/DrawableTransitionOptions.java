package com.p017xx.commom.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.TransitionOptions;
import com.p017xx.commom.glide.request.transition.DrawableCrossFadeFactory;
import com.p017xx.commom.glide.request.transition.TransitionFactory;

/* loaded from: classes3.dex */
public final class DrawableTransitionOptions extends TransitionOptions<DrawableTransitionOptions, Drawable> {
    public static DrawableTransitionOptions withCrossFade() {
        return new DrawableTransitionOptions().crossFade();
    }

    public static DrawableTransitionOptions withCrossFade(int i) {
        return new DrawableTransitionOptions().crossFade(i);
    }

    public static DrawableTransitionOptions withCrossFade(DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new DrawableTransitionOptions().crossFade(drawableCrossFadeFactory);
    }

    public static DrawableTransitionOptions withCrossFade(DrawableCrossFadeFactory.Builder builder) {
        return new DrawableTransitionOptions().crossFade(builder);
    }

    public static DrawableTransitionOptions with(TransitionFactory<Drawable> transitionFactory) {
        return new DrawableTransitionOptions().transition(transitionFactory);
    }

    public DrawableTransitionOptions crossFade() {
        return crossFade(new DrawableCrossFadeFactory.Builder());
    }

    public DrawableTransitionOptions crossFade(int i) {
        return crossFade(new DrawableCrossFadeFactory.Builder(i));
    }

    public DrawableTransitionOptions crossFade(DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return transition(drawableCrossFadeFactory);
    }

    public DrawableTransitionOptions crossFade(DrawableCrossFadeFactory.Builder builder) {
        return crossFade(builder.build());
    }
}
