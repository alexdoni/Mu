package com.xsdk.ab_firstbase.statisics.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes3.dex */
public class MyImageView extends ImageView {
    public static final int GET_DATA_SUCCESS = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int SERVER_ERROR = 3;
    private Handler handler;

    public MyImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler() { // from class: com.xsdk.ab_firstbase.statisics.util.image.MyImageView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                MyImageView.this.setImageBitmap((Bitmap) message.obj);
            }
        };
    }

    public MyImageView(Context context) {
        super(context);
        this.handler = new Handler() { // from class: com.xsdk.ab_firstbase.statisics.util.image.MyImageView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                MyImageView.this.setImageBitmap((Bitmap) message.obj);
            }
        };
    }

    public MyImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handler = new Handler() { // from class: com.xsdk.ab_firstbase.statisics.util.image.MyImageView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                MyImageView.this.setImageBitmap((Bitmap) message.obj);
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.xsdk.ab_firstbase.statisics.util.image.MyImageView$2] */
    public void setImageURL(final String str) {
        new Thread() { // from class: com.xsdk.ab_firstbase.statisics.util.image.MyImageView.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                InputStream inputStream = null;
                try {
                    try {
                        try {
                            try {
                                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setConnectTimeout(10000);
                                if (httpURLConnection.getResponseCode() == 200) {
                                    inputStream = httpURLConnection.getInputStream();
                                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                                    Message obtain = Message.obtain();
                                    obtain.obj = decodeStream;
                                    obtain.what = 1;
                                    MyImageView.this.handler.sendMessage(obtain);
                                    inputStream.close();
                                } else {
                                    MyImageView.this.handler.sendEmptyMessage(3);
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                MyImageView.this.handler.sendEmptyMessage(2);
                                if (0 != 0) {
                                    inputStream.close();
                                }
                            }
                        } catch (FileNotFoundException e2) {
                            e2.printStackTrace();
                            MyImageView.this.handler.sendEmptyMessage(2);
                            if (0 != 0) {
                                inputStream.close();
                            }
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        }.start();
    }
}
