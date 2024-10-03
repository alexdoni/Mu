package com.bumptech.glide.gifdecoder;

/* loaded from: classes.dex */
class GifFrame {
    static final int DISPOSAL_BACKGROUND = 2;
    static final int DISPOSAL_NONE = 1;
    static final int DISPOSAL_PREVIOUS = 3;
    static final int DISPOSAL_UNSPECIFIED = 0;
    int bufferFrameStart;
    int delay;
    int dispose;

    /* renamed from: ih */
    int f304ih;
    boolean interlace;

    /* renamed from: iw */
    int f305iw;

    /* renamed from: ix */
    int f306ix;

    /* renamed from: iy */
    int f307iy;
    int[] lct;
    int transIndex;
    boolean transparency;
}
