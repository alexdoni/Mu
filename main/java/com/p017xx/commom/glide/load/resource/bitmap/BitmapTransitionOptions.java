package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.TransitionOptions;
import com.p017xx.commom.glide.request.transition.BitmapTransitionFactory;
import com.p017xx.commom.glide.request.transition.DrawableCrossFadeFactory;
import com.p017xx.commom.glide.request.transition.TransitionFactory;

/* loaded from: classes3.dex */
public final class BitmapTransitionOptions extends TransitionOptions<BitmapTransitionOptions, Bitmap> {
    public static BitmapTransitionOptions withCrossFade() {
        return new BitmapTransitionOptions().crossFade();
    }

    public static BitmapTransitionOptions withCrossFade(int i) {
        return new BitmapTransitionOptions().crossFade(i);
    }

    public static BitmapTransitionOptions withCrossFade(DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new BitmapTransitionOptions().crossFade(drawableCrossFadeFactory);
    }

    public static BitmapTransitionOptions withCrossFade(DrawableCrossFadeFactory.Builder builder) {
        return new BitmapTransitionOptions().crossFade(builder);
    }

    public static BitmapTransitionOptions withWrapped(TransitionFactory<Drawable> transitionFactory) {
        return new BitmapTransitionOptions().transitionUsing(transitionFactory);
    }

    public static BitmapTransitionOptions with(TransitionFactory<Bitmap> transitionFactory) {
        return new BitmapTransitionOptions().transition(transitionFactory);
    }

    public BitmapTransitionOptions crossFade() {
        return crossFade(new DrawableCrossFadeFactory.Builder());
    }

    public BitmapTransitionOptions crossFade(int i) {
        return crossFade(new DrawableCrossFadeFactory.Builder(i));
    }

    public BitmapTransitionOptions crossFade(DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return transitionUsing(drawableCrossFadeFactory);
    }

    public BitmapTransitionOptions transitionUsing(TransitionFactory<Drawable> transitionFactory) {
        return transition(new BitmapTransitionFactory(transitionFactory));
    }

    public BitmapTransitionOptions crossFade(DrawableCrossFadeFactory.Builder builder) {
        return transitionUsing(builder.build());
    }
}
