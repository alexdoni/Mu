package com.luck.picture.lib.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import com.luck.picture.lib.config.PictureMimeType;
import java.io.IOException;

/* loaded from: classes2.dex */
public class MediaPlayerView extends FrameLayout implements SurfaceHolder.Callback {
    private MediaPlayer mediaPlayer;
    private VideoSurfaceView surfaceView;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public MediaPlayerView(Context context) {
        super(context);
        init();
    }

    public MediaPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MediaPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.surfaceView = new VideoSurfaceView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        this.surfaceView.setLayoutParams(layoutParams);
        addView(this.surfaceView);
        SurfaceHolder holder = this.surfaceView.getHolder();
        holder.setFormat(-2);
        holder.addCallback(this);
    }

    public MediaPlayer initMediaPlayer() {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        this.mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.luck.picture.lib.widget.MediaPlayerView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                MediaPlayerView.this.surfaceView.adjustVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
            }
        });
        return this.mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public VideoSurfaceView getSurfaceView() {
        return this.surfaceView;
    }

    public void start(String str) {
        try {
            if (PictureMimeType.isContent(str)) {
                this.mediaPlayer.setDataSource(getContext(), Uri.parse(str));
            } else {
                this.mediaPlayer.setDataSource(str);
            }
            this.mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mediaPlayer.setAudioStreamType(3);
        this.mediaPlayer.setDisplay(surfaceHolder);
    }

    public void clearCanvas() {
        this.surfaceView.getHolder().setFormat(-1);
        this.surfaceView.getHolder().setFormat(-2);
    }

    /* loaded from: classes2.dex */
    public static class VideoSurfaceView extends SurfaceView {
        private int videoHeight;
        private int videoWidth;

        public VideoSurfaceView(Context context) {
            this(context, null);
        }

        public VideoSurfaceView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public VideoSurfaceView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public void adjustVideoSize(int i, int i2) {
            if (i == 0 || i2 == 0) {
                return;
            }
            this.videoWidth = i;
            this.videoHeight = i2;
            getHolder().setFixedSize(i, i2);
            requestLayout();
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
        
            if (r1 > r6) goto L27;
         */
        @Override // android.view.SurfaceView, android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected void onMeasure(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.videoWidth
                int r0 = getDefaultSize(r0, r6)
                int r1 = r5.videoHeight
                int r1 = getDefaultSize(r1, r7)
                int r2 = r5.videoWidth
                if (r2 <= 0) goto L7a
                int r2 = r5.videoHeight
                if (r2 <= 0) goto L7a
                int r0 = android.view.View.MeasureSpec.getMode(r6)
                int r6 = android.view.View.MeasureSpec.getSize(r6)
                int r1 = android.view.View.MeasureSpec.getMode(r7)
                int r7 = android.view.View.MeasureSpec.getSize(r7)
                r2 = 1073741824(0x40000000, float:2.0)
                if (r0 != r2) goto L41
                if (r1 != r2) goto L41
                int r0 = r5.videoWidth
                int r1 = r0 * r7
                int r2 = r5.videoHeight
                int r3 = r6 * r2
                if (r1 >= r3) goto L37
                int r0 = r0 * r7
                int r0 = r0 / r2
                goto L62
            L37:
                int r1 = r0 * r7
                int r3 = r6 * r2
                if (r1 <= r3) goto L5f
                int r2 = r2 * r6
                int r1 = r2 / r0
                goto L51
            L41:
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r0 != r2) goto L53
                int r0 = r5.videoHeight
                int r0 = r0 * r6
                int r2 = r5.videoWidth
                int r0 = r0 / r2
                if (r1 != r3) goto L50
                if (r0 <= r7) goto L50
                goto L5f
            L50:
                r1 = r0
            L51:
                r0 = r6
                goto L7a
            L53:
                if (r1 != r2) goto L64
                int r1 = r5.videoWidth
                int r1 = r1 * r7
                int r2 = r5.videoHeight
                int r1 = r1 / r2
                if (r0 != r3) goto L61
                if (r1 <= r6) goto L61
            L5f:
                r0 = r6
                goto L62
            L61:
                r0 = r1
            L62:
                r1 = r7
                goto L7a
            L64:
                int r2 = r5.videoWidth
                int r4 = r5.videoHeight
                if (r1 != r3) goto L70
                if (r4 <= r7) goto L70
                int r1 = r7 * r2
                int r1 = r1 / r4
                goto L72
            L70:
                r1 = r2
                r7 = r4
            L72:
                if (r0 != r3) goto L61
                if (r1 <= r6) goto L61
                int r4 = r4 * r6
                int r1 = r4 / r2
                goto L51
            L7a:
                r5.setMeasuredDimension(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.widget.MediaPlayerView.VideoSurfaceView.onMeasure(int, int):void");
        }
    }

    public void release() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer.setOnPreparedListener(null);
            this.mediaPlayer.setOnCompletionListener(null);
            this.mediaPlayer.setOnErrorListener(null);
            this.mediaPlayer = null;
        }
    }
}
