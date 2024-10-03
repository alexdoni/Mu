package com.oversea.ab_firstarea.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearPhotoControl;
import com.oversea.ab_firstplatform.Lxhw_Platform;

/* loaded from: classes.dex */
public class Head_Image_deal {
    private static Head_Image_deal instance;
    Bitmap bitmapP;
    HeadCallBackListener callBackListener;
    String TAG = "Head_Image_deal";
    boolean iFinish = true;
    final int SUCCESS = 1;
    final int FAIL = 2;
    Handler handler = new Handler() { // from class: com.oversea.ab_firstarea.utils.Head_Image_deal.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            Head_Image_deal.this.doUploadHead();
        }
    };
    Lxhw_CamearCallBack.CamearCallBackListener camearCallBackListener = new Lxhw_CamearCallBack.CamearCallBackListener() { // from class: com.oversea.ab_firstarea.utils.Head_Image_deal.2
        @Override // com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearCallBack.CamearCallBackListener
        public void onCCBResult(Uri uri, Bitmap bitmap) {
            if (bitmap != null) {
                Head_Image_deal.this.bitmapP = bitmap;
                Head_Image_deal.this.sendMessage(1);
            } else {
                Log.v(Head_Image_deal.this.TAG, "**onCCBResult no have");
                Head_Image_deal.this.result(false, "fail");
            }
        }
    };

    /* loaded from: classes.dex */
    public interface HeadCallBackListener {
        void headResult(boolean z, String str);
    }

    public void doDownHead(Activity activity, HeadCallBackListener headCallBackListener) {
    }

    public void doUploadHead() {
    }

    private Head_Image_deal() {
    }

    public static Head_Image_deal getInstance() {
        if (instance == null) {
            instance = new Head_Image_deal();
        }
        return instance;
    }

    public void sendMessage(int i) {
        if (this.handler == null) {
            return;
        }
        Message message = new Message();
        message.what = i;
        this.handler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void result(boolean z, String str) {
        this.iFinish = true;
        this.callBackListener.headResult(z, str);
    }

    public void uploadHead(Activity activity, HeadCallBackListener headCallBackListener) {
        if (TextUtils.isEmpty(Lxhw_Platform.getInstance().userExtraData.getRoleName()) || TextUtils.isEmpty(Lxhw_Platform.getInstance().userExtraData.getRoleID())) {
            Log.v(this.TAG, "doDownHead**role info null");
            return;
        }
        this.iFinish = false;
        this.callBackListener = headCallBackListener;
        Lxhw_CamearPhotoControl.getInstance().autoObtainStoragePermission(activity, this.camearCallBackListener);
    }
}
