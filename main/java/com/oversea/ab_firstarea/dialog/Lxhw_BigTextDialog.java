package com.oversea.ab_firstarea.dialog;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.share.internal.ShareConstants;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes.dex */
public class Lxhw_BigTextDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    public static Lxhw_BigTextDialog defaultInstance;
    private TextView content_text_view;
    private ImageView mBackBtn;
    private TextView tv_tile;
    private String TAG = getClass().toString();
    private String type = "";

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xbigtext_layout";
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getDialog().getWindow().setLayout(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        String str;
        String str2;
        defaultInstance = this;
        this.type = getArguments().getString(ShareConstants.MEDIA_TYPE);
        ImageView imageView = (ImageView) view.findViewById(Util.getIdByName(getActivity(), "id", "tw_sdk_back_iv"));
        this.mBackBtn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_BigTextDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Log.i(Lxhw_BigTextDialog.this.TAG, "******onClick");
                Lxhw_BigTextDialog.this.dismiss();
            }
        });
        this.mBackBtn.setVisibility(0);
        this.content_text_view = (TextView) view.findViewById(Util.getIdByName(getActivity(), "id", "content_text_view"));
        this.tv_tile = (TextView) view.findViewById(Util.getIdByName(getActivity(), "id", "tv_tile"));
        if (this.type.equals("1")) {
            str = getString(Util.getIdByName(getActivity(), TypedValues.Custom.S_STRING, "service_policy_text"));
            str2 = getString(Util.getIdByName(getActivity(), TypedValues.Custom.S_STRING, "tw_agreement_2"));
        } else if (this.type.equals("2")) {
            str = getString(Util.getIdByName(getActivity(), TypedValues.Custom.S_STRING, "privacy_policy_text"));
            str2 = getString(Util.getIdByName(getActivity(), TypedValues.Custom.S_STRING, "tw_agreement_4"));
        } else {
            str = "";
            str2 = "";
        }
        this.content_text_view.setText(str);
        this.tv_tile.setText(str2);
    }
}
