package com.facebook.share.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import com.facebook.common.C0830R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.share.C0849R;

/* loaded from: classes.dex */
public final class ShareButton extends ShareButtonBase {
    public ShareButton(final Context context) {
        super(context, null, 0, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, AnalyticsEvents.EVENT_SHARE_BUTTON_DID_TAP);
    }

    public ShareButton(final Context context, final AttributeSet attrs) {
        super(context, attrs, 0, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, AnalyticsEvents.EVENT_SHARE_BUTTON_DID_TAP);
    }

    public ShareButton(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, AnalyticsEvents.EVENT_SHARE_BUTTON_DID_TAP);
    }

    @Override // com.facebook.FacebookButtonBase
    protected int getDefaultStyleResource() {
        return C0849R.style.com_facebook_button_share;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.FacebookButtonBase
    public int getDefaultRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode();
    }

    @Override // com.facebook.share.widget.ShareButtonBase
    protected ShareDialog getDialog() {
        ShareDialog shareDialog;
        if (getFragment() != null) {
            shareDialog = new ShareDialog(getFragment(), getRequestCode());
        } else if (getNativeFragment() != null) {
            shareDialog = new ShareDialog(getNativeFragment(), getRequestCode());
        } else {
            shareDialog = new ShareDialog(getActivity(), getRequestCode());
        }
        shareDialog.setCallbackManager(getCallbackManager());
        return shareDialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.share.widget.ShareButtonBase, com.facebook.FacebookButtonBase
    public void configureButton(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super.configureButton(context, attrs, defStyleAttr, defStyleRes);
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(getContext(), C0830R.drawable.com_facebook_button_icon), (Drawable) null, (Drawable) null, (Drawable) null);
    }
}
