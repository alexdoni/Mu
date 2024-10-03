package com.oversea.ab_firstarea.p012f.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.oversea.ab_firstarea.activity.Lxhw_PayActivity;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dpresenter.PresenterSimple;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_SimplePresenterImpl;
import com.oversea.ab_firstarea.dview.Lxhw_BaseView;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.PayInitBean;
import com.oversea.ab_firstarea.net.model.PayOrderCreateBean;
import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.orderSyn.OrderBaseInfo;
import com.oversea.ab_firstarea.plugin.channel.Tsdk;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.model.SkuProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Lxhw_RechargeType;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class DealPay implements Lxhw_BaseView<String> {
    private static DealPay instance;
    private Lxhw_BaseView<String> baseView;
    private Activity mContext;
    private PayInitBean payInitBean;
    private Lxhw_PayParams payParams;
    private PresenterSimple presenter;
    private final String TAG = "DealPay";
    public final String CREATEORDER_COMMON = "createOrder_common";

    public static DealPay getInstance() {
        if (instance == null) {
            instance = new DealPay();
        }
        return instance;
    }

    public void init(Activity activity) {
        if (this.presenter != null) {
            return;
        }
        this.mContext = activity;
        this.presenter = new Lxhw_SimplePresenterImpl(activity, this);
    }

    public PayInitBean getPayInitBean() {
        return this.payInitBean;
    }

    public void jumpPayActivity(final Activity activity, final String str) {
        if (TextUtils.isEmpty(str) && getPayInitBean() != null && getPayInitBean().getData() != null && getPayInitBean().getData().getWeb_pay_list() != null && getPayInitBean().getData().getWeb_pay_list().size() > 0) {
            Lxhw_DialogManage.getInstance().enterChooseRechargeCenter(this.mContext);
            return;
        }
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doGetUserInfo");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.USERINFO_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doGetUserInfo", createCommonParams, AreaBaseService.USERINFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.f.sdk.DealPay.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    UserInfoBean userInfoBean = (UserInfoBean) JsonUtility.jsonToObject(UserInfoBean.class, str2);
                    if (userInfoBean.getCode() == 0) {
                        ComConstants.GETORDER_STATU = 4;
                        Intent intent = new Intent(activity, (Class<?>) Lxhw_PayActivity.class);
                        if (!TextUtils.isEmpty(str)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("payMethod", str);
                            intent.putExtra("bundle", bundle);
                        }
                        activity.startActivity(intent);
                        return;
                    }
                    LLog.i_Control("jumpPayActivity code=" + userInfoBean.getCode() + " msg=" + userInfoBean.getMessage());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.i_Control("jumpPayActivity httpRequestFail code=" + baseBean.getCode() + " msg=" + baseBean.getMessage());
                Lxhw_XSDK lxhw_XSDK = Lxhw_XSDK.getInstance();
                StringBuilder sb = new StringBuilder("pay fail:");
                sb.append(baseBean.getMessage());
                lxhw_XSDK.onResult(17, sb.toString());
            }
        });
    }

    public Lxhw_PayParams getPayParams() {
        if (this.payParams == null) {
            this.payParams = new Lxhw_PayParams();
        }
        return this.payParams;
    }

    public void payInit(Lxhw_PayParams lxhw_PayParams) {
        if (lxhw_PayParams == null) {
            LLog.e_Control("payInit payParams null");
            return;
        }
        if (!TextUtils.isEmpty(lxhw_PayParams.getStoreProductId())) {
            lxhw_PayParams.setStoreProductId("");
        }
        this.payParams = lxhw_PayParams;
        LLog.i_Control("payInit");
        HashMap hashMap = new HashMap();
        hashMap.put("pay_channel_parent_id", Integer.valueOf(Lxhw_AppInfoDecorator.getChannelPay_id() != 0 ? Lxhw_AppInfoDecorator.getChannelPay_id() : 2));
        hashMap.put("cp_product_id", lxhw_PayParams.getProductId());
        Lxhw_DialogManage.getInstance().showDialog();
        this.presenter.doRequestComAddHead(AreaBaseService.PAYINIT_ROUTE, AreaBaseService.PAYINIT, hashMap);
    }

    public void payOrderCreate(String str, OrderBaseInfo orderBaseInfo) {
        LLog.i_Control("payOrderCreate orderType=" + str);
        PayInitBean payInitBean = this.payInitBean;
        if (payInitBean == null) {
            LLog.i_Control("payOrderCreate payInitBean = null");
            return;
        }
        if (payInitBean.getData().getPopup_switch() == 1 && !TextUtils.isEmpty(this.payInitBean.getData().getPopup_tips()) && !TextUtils.isEmpty(this.payInitBean.getData().getPopup_url()) && this.payInitBean.getData().getPopup_url().startsWith("http")) {
            LLog.i_Control("payOrderCreate Popup_switch = 1 ");
            ComConstants.GETORDER_STATU = 4;
            jumpPopup(this.payInitBean.getData().getPopup_tips(), this.payInitBean.getData().getPopup_url());
            Lxhw_XSDK.getInstance().onResult(17, this.payInitBean.getData().getPopup_tips());
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("pay_channel_parent_id", Integer.valueOf(this.payInitBean.getData().getPay_channel_parent_id()));
        hashMap.put("pay_channel_group_id", Integer.valueOf(this.payInitBean.getData().getPay_channel_group_id()));
        hashMap.put("pay_channel_id", Integer.valueOf(this.payInitBean.getData().getPay_channel_id()));
        hashMap.put("pay_channel_code", this.payInitBean.getData().getPay_channel_code());
        hashMap.put("product_id", this.payParams.getProductId() + "");
        String productId = this.payParams.getProductId();
        if (!TextUtils.isEmpty(this.payParams.getStoreProductId())) {
            productId = this.payParams.getStoreProductId();
        }
        LLog.i_Control("product_id " + this.payParams.getProductId() + " finalProductID=" + productId);
        SkuProductListData.Product_info product_info = SkuProductListData.getInstance().getProduct_infoMap().get(productId);
        if (product_info == null || product_info.getPrice_amount_micros() < 0 || TextUtils.isEmpty(product_info.getRealCurrency())) {
            GoogleProductListData.Product_list productInfo = PayLibHelper.getInstance().getProductInfo(productId);
            if (productInfo != null) {
                if (!Lxhw_AreaPlatform.getInstance().isFF()) {
                    this.payParams.setCurrencyType(productInfo.getCurrency_code());
                    this.payParams.setCommodityPrice(productInfo.getAmount());
                    this.payParams.setProductName(productInfo.getProduct_name());
                }
                hashMap.put("currency_code", productInfo.getCurrency_code());
                try {
                    if (TextUtils.isEmpty(productInfo.getAmount())) {
                        hashMap.put("amount", productInfo.getAmount() + "");
                    } else {
                        hashMap.put("amount", new BigDecimal(productInfo.getAmount()).multiply(new BigDecimal(String.valueOf(1000000))).toString());
                    }
                } catch (Exception e) {
                    LLog.i_Control(productId + "micro =" + e.toString());
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
        hashMap.put("cp_order_number", this.payParams.getCpOrderID() + "");
        hashMap.put("cp_ext_params", this.payParams.getExtension());
        hashMap.put("server_id", this.payParams.getServerId());
        hashMap.put("server_name", this.payParams.getServerName());
        hashMap.put("role_id", this.payParams.getRoleId());
        hashMap.put("role_name", this.payParams.getRoleName());
        hashMap.put("role_level", this.payParams.getRoleLevel() + "");
        hashMap.put("remain_coin", this.payParams.getBalance() + "");
        hashMap.put("product_type", this.payParams.getProduct_type());
        if (!TextUtils.isEmpty(this.payParams.getProductName())) {
            hashMap.put("product_name", this.payParams.getProductName());
        }
        if (PayLibHelper.getInstance().isAptoide()) {
            hashMap.put("with_options", 1);
        }
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams(AreaBaseService.PAYORDERCREATE_ROUTE);
        if (hashMap.size() > 0) {
            for (Map.Entry entry : hashMap.entrySet()) {
                createCommonParams.put((String) entry.getKey(), entry.getValue());
            }
        }
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.PAYORDERCREATE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead(str, createCommonParams, AreaBaseService.PAYORDERCREATE, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.f.sdk.DealPay.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        PayOrderCreateBean payOrderCreateBean = (PayOrderCreateBean) JsonUtility.jsonToObject(PayOrderCreateBean.class, str2);
                        if (payOrderCreateBean.getCode() == 0) {
                            if (payOrderCreateBean != null && payOrderCreateBean.getData() != null) {
                                LLog.v_Control("payOrderCreate bean " + payOrderCreateBean.getData().getOrder_number());
                                if (!TextUtils.isEmpty(payOrderCreateBean.getData().getOrder_number())) {
                                    DealPay.this.payParams.setOrderId(payOrderCreateBean.getData().getOrder_number());
                                    if (!Lxhw_AreaPlatform.getInstance().isFF()) {
                                        Tsdk.getInstance().pay(DealPay.this.payParams);
                                    } else if (PayLibHelper.getInstance().isAptoide()) {
                                        PayLibHelper.getInstance().pay(DealPay.this.mContext, payOrderCreateBean.getData().getTrade_pay_url(), DealPay.this.payParams);
                                    } else {
                                        PayLibHelper.getInstance().pay(DealPay.this.mContext, payOrderCreateBean.getData().getOrder_number(), DealPay.this.payParams);
                                    }
                                } else {
                                    Lxhw_DialogManage.getInstance().cancelDialog();
                                    ComConstants.GETORDER_STATU = 4;
                                }
                            } else {
                                Lxhw_DialogManage.getInstance().cancelDialog();
                                ComConstants.GETORDER_STATU = 4;
                                LLog.e_noControl("payOrderCreate bean or data null ");
                            }
                        } else {
                            Lxhw_AreaPlatform.getInstance().onTrackEventJsonObject(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_PAY_ERROR, ComConfig.CUSTOM_SDK_ORDER_CREATE_ERROR, payOrderCreateBean.getCode(), payOrderCreateBean.getMessage());
                            Lxhw_DialogManage.getInstance().cancelDialog();
                            ComConstants.GETORDER_STATU = 4;
                            ToastUtils.toastShow(DealPay.this.mContext, ComUtil.getBaseBeanTip(payOrderCreateBean));
                            Lxhw_XSDK.getInstance().onResult(17, "getCode != 0");
                        }
                    } else {
                        Lxhw_DialogManage.getInstance().cancelDialog();
                        ComConstants.GETORDER_STATU = 4;
                    }
                } catch (Throwable th) {
                    ComConstants.GETORDER_STATU = 4;
                    Lxhw_DialogManage.getInstance().cancelDialog();
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                ComConstants.GETORDER_STATU = 4;
            }
        });
    }

    public void jumpPopup(String str, String str2) {
        Lxhw_DialogManage.getInstance().enterPErrorTip(Lxhw_XSDK.getInstance().getContext(), str, str2);
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqSuccess(String str, String str2) {
        try {
            if (AreaBaseService.PAYINIT_ROUTE.equals(str)) {
                PayInitBean payInitBean = (PayInitBean) JsonUtility.jsonToObject(PayInitBean.class, str2);
                this.payInitBean = payInitBean;
                if (payInitBean != null) {
                    if (payInitBean.getCode() != 0) {
                        Lxhw_AreaPlatform.getInstance().onTrackEventJsonObject(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_PAY_ERROR, ComConfig.CUSTOM_SDK_ORDER_INIT_ERROR, this.payInitBean.getCode(), this.payInitBean.getMessage());
                        Lxhw_XSDK.getInstance().onResult(17, "payInit fail");
                    }
                    if (this.payInitBean.getData() != null && !TextUtils.isEmpty(this.payInitBean.getData().getStore_product_id())) {
                        this.payParams.setStoreProductId(this.payInitBean.getData().getStore_product_id());
                    }
                    LLog.v_noControl("onReqSuccess android_pay_type=" + this.payInitBean.getData().getAndroid_pay_type() + " rechargetype=" + Lxhw_RechargeType.getType() + "rolel=" + this.payParams.getRoleLevel() + "thirdl=" + this.payInitBean.getData().getInapp_enable_third_level());
                    if (!Lxhw_RechargeType.getType().equals(Lxhw_RechargeType.GOOGLE_TYPE) && (!Lxhw_RechargeType.getType().equals(Lxhw_RechargeType.ALL_TYPE) || this.payInitBean.getData().getAndroid_pay_type() != 1)) {
                        Lxhw_DialogManage.getInstance().cancelDialog();
                    }
                    if (Lxhw_RechargeType.getType().equals(Lxhw_RechargeType.GOOGLE_TYPE)) {
                        Objects.requireNonNull(getInstance());
                        payOrderCreate("createOrder_common", null);
                        return;
                    }
                    if (Lxhw_RechargeType.getType().equals(Lxhw_RechargeType.THIRD_TYPE)) {
                        if (this.payInitBean.getData().getAndroid_pay_type() == 3) {
                            jumpPayActivity(this.mContext, "");
                            return;
                        } else {
                            Lxhw_DialogManage.getInstance().enterChooseRechargeCenter(this.mContext);
                            return;
                        }
                    }
                    if (Lxhw_RechargeType.getType().equals(Lxhw_RechargeType.ALL_TYPE)) {
                        if (this.payInitBean.getData().getAndroid_pay_type() == 1) {
                            Objects.requireNonNull(getInstance());
                            payOrderCreate("createOrder_common", null);
                            return;
                        }
                        if (this.payInitBean.getData().getAndroid_pay_type() == 2) {
                            if (this.payParams.getRoleLevel() >= this.payInitBean.getData().getInapp_enable_third_level()) {
                                Lxhw_DialogManage.getInstance().enterChooseRechargeCenter(this.mContext);
                                return;
                            } else {
                                Objects.requireNonNull(getInstance());
                                payOrderCreate("createOrder_common", null);
                                return;
                            }
                        }
                        if (this.payInitBean.getData().getAndroid_pay_type() == 3) {
                            jumpPayActivity(this.mContext, "");
                            return;
                        } else {
                            Lxhw_DialogManage.getInstance().enterChooseRechargeCenter(this.mContext);
                            return;
                        }
                    }
                    if (Lxhw_RechargeType.getType().equals(Lxhw_RechargeType.ONLYTHIRD_TYPE)) {
                        jumpPayActivity(this.mContext, "");
                        return;
                    } else {
                        Lxhw_DialogManage.getInstance().enterChooseRechargeCenter(this.mContext);
                        return;
                    }
                }
                Lxhw_DialogManage.getInstance().cancelDialog();
                ComConstants.GETORDER_STATU = 4;
                LLog.e_noControl("payInit initbean null");
                Lxhw_AreaPlatform.getInstance().onTrackEventJsonObject(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_PAY_ERROR, ComConfig.CUSTOM_SDK_ORDER_INIT_ERROR, -1, "payInit initbean null");
                Lxhw_XSDK.getInstance().onResult(17, "payInit initbean null");
                return;
            }
            if (AreaBaseService.PAYORDERCREATE_ROUTE.equals(str)) {
                PayOrderCreateBean payOrderCreateBean = (PayOrderCreateBean) JsonUtility.jsonToObject(PayOrderCreateBean.class, str2);
                if (payOrderCreateBean != null && payOrderCreateBean.getData() != null) {
                    LLog.v_Control("payOrderCreate bean " + payOrderCreateBean.getData().getOrder_number());
                    if (!TextUtils.isEmpty(payOrderCreateBean.getData().getOrder_number())) {
                        PayLibHelper.getInstance().pay(this.mContext, payOrderCreateBean.getData().getOrder_number(), this.payParams);
                        return;
                    } else {
                        Lxhw_DialogManage.getInstance().cancelDialog();
                        ComConstants.GETORDER_STATU = 4;
                        return;
                    }
                }
                Lxhw_DialogManage.getInstance().cancelDialog();
                ComConstants.GETORDER_STATU = 4;
                LLog.e_noControl("payOrderCreate bean or data null ");
            }
        } catch (Exception unused) {
            Lxhw_XSDK.getInstance().onResult(17, "payInit fail");
            Lxhw_DialogManage.getInstance().cancelDialog();
            ComConstants.GETORDER_STATU = 4;
        }
    }

    @Override // com.oversea.ab_firstarea.dview.Lxhw_BaseView
    public void onReqFail(String str, BaseBean baseBean) {
        LLog.e_noControl("onReqFail type=" + str + "code=" + baseBean.getCode() + " " + baseBean.getMessage());
        ComConstants.GETORDER_STATU = 4;
        Lxhw_XSDK.getInstance().onResult(17, "payInit fail");
        ToastUtils.toastLongShow(this.mContext, ComUtil.getBaseBeanTip(baseBean));
        Lxhw_DialogManage.getInstance().cancelDialog();
    }
}
