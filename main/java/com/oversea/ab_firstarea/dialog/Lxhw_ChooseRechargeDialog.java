package com.oversea.ab_firstarea.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.PayInitBean;
import com.oversea.ab_firstarea.net.model.PayOrderCreateBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.DealPay;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.model.SkuProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class Lxhw_ChooseRechargeDialog extends Lxhw_BaseDialogFragment implements View.OnClickListener {
    private String TAG = getClass().toString();
    private TextView back_tv;
    private LinearLayout google_recharge_ll;
    private LinearLayout gw_recharge_ll;
    private ImageView ivPay;
    private LinearLayout llParentVer;
    private Activity mContext;
    private TextView tvPay;
    private TextView tw_choose_recharge_gw;

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public String getLayoutId() {
        return "drhw_xchoose_recharge";
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            getDialog().getWindow().setLayout((int) (displayMetrics.heightPixels * 0.95d), -2);
        } else {
            getDialog().getWindow().setLayout((int) (displayMetrics.widthPixels * 0.9d), -2);
        }
    }

    @Override // com.oversea.ab_firstarea.dialog.Lxhw_BaseDialogFragment
    public void initView(View view) {
        this.tw_choose_recharge_gw = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tw_choose_recharge_gw"));
        if (DealPay.getInstance().getPayInitBean() != null && DealPay.getInstance().getPayInitBean().getData() != null && DealPay.getInstance().getPayInitBean().getData().getAdditional_rate() > 0) {
            this.tw_choose_recharge_gw.setText(Util.getStringInt(this.mContext, "tw_choose_recharge_gw", DealPay.getInstance().getPayInitBean().getData().getAdditional_rate()));
        } else {
            String string = Util.getString(this.mContext, "tw_choose_recharge_gw");
            this.tw_choose_recharge_gw.setText(string.substring(0, string.indexOf("(")));
        }
        TextView textView = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "back_tv"));
        this.back_tv = textView;
        textView.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "google_recharge_ll"));
        this.google_recharge_ll = linearLayout;
        linearLayout.setOnClickListener(this);
        this.ivPay = (ImageView) view.findViewById(Util.getIdByName(this.mContext, "id", "iv_pay_way"));
        this.tvPay = (TextView) view.findViewById(Util.getIdByName(this.mContext, "id", "tv_pay_way"));
        if (PayLibHelper.getInstance().isHuawei()) {
            this.ivPay.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_huaweiicon"));
            this.tvPay.setText("Huawei Pay");
        } else if (PayLibHelper.getInstance().isOneStore()) {
            this.ivPay.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_onestore_icon"));
            this.tvPay.setText("OneStore");
        } else if (PayLibHelper.getInstance().isSamsung()) {
            this.ivPay.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_samsung_icon"));
            this.tvPay.setText("Samsung");
        } else if (PayLibHelper.getInstance().isAptoide()) {
            this.ivPay.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_aptoide_icon"));
            this.tvPay.setText("AppCoins Wallet");
        } else {
            this.ivPay.setImageResource(Util.getIdByName(this.mContext, "drawable", "drhw_googleplayicon"));
            this.tvPay.setText("Google Play");
        }
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(Util.getIdByName(this.mContext, "id", "gw_recharge_ll"));
        this.gw_recharge_ll = linearLayout2;
        linearLayout2.setOnClickListener(this);
        initPayList(view);
        setCancelable(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.back_tv) {
            ComConstants.GETORDER_STATU = 4;
            Lxhw_XSDK.getInstance().onResult(18, "pay cancel");
        } else if (view == this.google_recharge_ll) {
            DealPay dealPay = DealPay.getInstance();
            Objects.requireNonNull(DealPay.getInstance());
            dealPay.payOrderCreate("createOrder_common", null);
        } else if (view == this.gw_recharge_ll) {
            DealPay.getInstance().jumpPayActivity(this.mContext, "Official website");
        }
        dismissAllowingStateLoss();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x02a3 A[Catch: Exception -> 0x051e, TryCatch #0 {Exception -> 0x051e, blocks: (B:3:0x0006, B:5:0x0010, B:7:0x001e, B:9:0x0030, B:11:0x006e, B:13:0x0081, B:15:0x0094, B:17:0x00a6, B:18:0x00b1, B:21:0x0101, B:25:0x011a, B:27:0x0130, B:29:0x0188, B:31:0x0192, B:33:0x01d0, B:35:0x01da, B:37:0x01de, B:39:0x01e8, B:41:0x01fa, B:42:0x0262, B:43:0x0273, B:45:0x0287, B:47:0x0291, B:48:0x029d, B:50:0x02a3, B:52:0x02e6, B:53:0x0316, B:55:0x0320, B:57:0x0324, B:59:0x032e, B:61:0x0340, B:64:0x0378, B:63:0x0389, B:68:0x02f2, B:70:0x039e, B:73:0x019a, B:75:0x01a4, B:76:0x01ae, B:81:0x03cb, B:83:0x03e1, B:85:0x0434, B:86:0x0460, B:88:0x046a, B:90:0x046e, B:92:0x0478, B:94:0x048a, B:95:0x04f6, B:96:0x0507, B:98:0x043e, B:101:0x008d, B:102:0x0046, B:104:0x0058), top: B:2:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initPayList(android.view.View r24) {
        /*
            Method dump skipped, instructions count: 1315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.dialog.Lxhw_ChooseRechargeDialog.initPayList(android.view.View):void");
    }

    public void payOrderCreate(final Lxhw_PayParams lxhw_PayParams, final PayInitBean.Data.WebPayListBean webPayListBean, final PayInitBean.Data.WebPayListBean.childChannelBean childchannelbean) {
        HashMap hashMap = new HashMap();
        if (childchannelbean == null) {
            hashMap.put("pay_channel_parent_id", Integer.valueOf(webPayListBean.getPay_channel_parent_id()));
            hashMap.put("pay_channel_group_id", Integer.valueOf(webPayListBean.getPay_channel_group_id()));
            hashMap.put("pay_channel_id", Integer.valueOf(webPayListBean.getPay_channel_id()));
        } else {
            hashMap.put("pay_channel_parent_id", Integer.valueOf(childchannelbean.getPay_channel_parent_id()));
            hashMap.put("pay_channel_group_id", Integer.valueOf(childchannelbean.getPay_channel_group_id()));
            hashMap.put("pay_channel_id", Integer.valueOf(childchannelbean.getPay_channel_id()));
        }
        hashMap.put("pay_channel_code", "");
        hashMap.put("with_options", 1);
        String productId = lxhw_PayParams.getProductId();
        hashMap.put("product_id", productId + "");
        SkuProductListData.Product_info product_info = SkuProductListData.getInstance().getProduct_infoMap().get(productId);
        if (product_info == null || product_info.getPrice_amount_micros() < 0 || TextUtils.isEmpty(product_info.getRealCurrency())) {
            GoogleProductListData.Product_list productInfo = PayLibHelper.getInstance().getProductInfo(productId);
            if (productInfo != null) {
                hashMap.put("currency_code", productInfo.getCurrency_code());
                try {
                    if (TextUtils.isEmpty(productInfo.getAmount())) {
                        hashMap.put("amount", productInfo.getAmount() + "");
                    } else {
                        hashMap.put("amount", new BigDecimal(productInfo.getAmount()).multiply(new BigDecimal(String.valueOf(1000000))).toString());
                    }
                } catch (Throwable th) {
                    LLog.i_Control(productId + "micro =" + th.toString());
                    StringBuilder sb = new StringBuilder();
                    sb.append(productInfo.getAmount());
                    sb.append("");
                    hashMap.put("amount", sb.toString());
                }
            } else {
                hashMap.put("currency_code", "USD");
                hashMap.put("amount", "0");
            }
        } else {
            hashMap.put("currency_code", product_info.getRealCurrency() + "");
            hashMap.put("amount", product_info.getPrice_amount_micros() + "");
        }
        hashMap.put("cp_order_number", lxhw_PayParams.getCpOrderID() + "");
        hashMap.put("cp_ext_params", lxhw_PayParams.getExtension());
        hashMap.put("server_id", lxhw_PayParams.getServerId());
        hashMap.put("server_name", lxhw_PayParams.getServerName());
        hashMap.put("role_id", lxhw_PayParams.getRoleId());
        hashMap.put("role_name", lxhw_PayParams.getRoleName());
        hashMap.put("role_level", lxhw_PayParams.getRoleLevel() + "");
        hashMap.put("remain_coin", lxhw_PayParams.getBalance() + "");
        hashMap.put("product_type", lxhw_PayParams.getProduct_type());
        hashMap.put("product_name", lxhw_PayParams.getProductName());
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams(AreaBaseService.PAYORDERCREATE_ROUTE);
        if (hashMap.size() > 0) {
            for (Map.Entry entry : hashMap.entrySet()) {
                createCommonParams.put((String) entry.getKey(), entry.getValue());
            }
        }
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.PAYORDERCREATE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("createOrder_common", createCommonParams, AreaBaseService.PAYORDERCREATE, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dialog.Lxhw_ChooseRechargeDialog.4
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                PayInitBean.Data.WebPayListBean.childChannelBean childchannelbean2;
                LoadingDialog.cancelDialogForLoading();
                try {
                    if (!TextUtils.isEmpty(str)) {
                        PayOrderCreateBean payOrderCreateBean = (PayOrderCreateBean) JsonUtility.jsonToObject(PayOrderCreateBean.class, str);
                        if (payOrderCreateBean.getCode() == 0) {
                            if (payOrderCreateBean != null && payOrderCreateBean.getData() != null) {
                                String trade_pay_url = payOrderCreateBean.getData().getTrade_pay_url();
                                if (TextUtils.isEmpty(trade_pay_url)) {
                                    ToastUtils.toastShow(Lxhw_ChooseRechargeDialog.this.mContext, "url is null!");
                                    return;
                                }
                                if (trade_pay_url.startsWith("https://apichain.catappult.io/transaction/inapp")) {
                                    PayLibHelper.getInstance().pay(Lxhw_ChooseRechargeDialog.this.mContext, trade_pay_url, lxhw_PayParams);
                                } else {
                                    PayInitBean.Data.WebPayListBean webPayListBean2 = webPayListBean;
                                    if ((webPayListBean2 != null && webPayListBean2.getIs_wv() == 0) || ((childchannelbean2 = childchannelbean) != null && childchannelbean2.getIs_wv() == 0)) {
                                        Lxhw_ChooseRechargeDialog.this.openBrow(trade_pay_url);
                                    } else if (payOrderCreateBean.getData().getJump_to_mycard_point_view() != 1) {
                                        DealPay.getInstance().jumpPayActivity(Lxhw_ChooseRechargeDialog.this.mContext, trade_pay_url);
                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("order_number", payOrderCreateBean.getData().getOrder_number());
                                        bundle.putString("product_id", lxhw_PayParams.getProductId());
                                        Lxhw_DialogManage.getInstance().showMyCardDialog(Lxhw_ChooseRechargeDialog.this.mContext, bundle);
                                        ComConstants.GETORDER_STATU = 4;
                                    }
                                }
                                Lxhw_ChooseRechargeDialog.this.dismissAllowingStateLoss();
                                return;
                            }
                            Lxhw_DialogManage.getInstance().cancelDialog();
                            ComConstants.GETORDER_STATU = 4;
                            LLog.e_noControl("payOrderCreate bean or data null ");
                            return;
                        }
                        Lxhw_AreaPlatform.getInstance().onTrackEventJsonObject(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_PAY_ERROR, ComConfig.CUSTOM_SDK_ORDER_CREATE_ERROR, payOrderCreateBean.getCode(), payOrderCreateBean.getMessage());
                        Lxhw_DialogManage.getInstance().cancelDialog();
                        ComConstants.GETORDER_STATU = 4;
                        ToastUtils.toastShow(Lxhw_ChooseRechargeDialog.this.mContext, ComUtil.getBaseBeanTip(payOrderCreateBean));
                        Lxhw_XSDK.getInstance().onResult(17, "getCode != 0");
                        return;
                    }
                    Lxhw_DialogManage.getInstance().cancelDialog();
                    ComConstants.GETORDER_STATU = 4;
                } catch (Throwable th2) {
                    ComConstants.GETORDER_STATU = 4;
                    Lxhw_DialogManage.getInstance().cancelDialog();
                    th2.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LoadingDialog.cancelDialogForLoading();
                ComConstants.GETORDER_STATU = 4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openBrow(String str) {
        try {
            ComConstants.GETORDER_STATU = 4;
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception unused) {
        }
    }
}
