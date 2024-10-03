package com.p008ld.sdk.model;

import com.p008ld.sdk.bean.LDResult;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.ClaimGiftBagInfo;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.bean.CouponBean;
import com.p008ld.sdk.core.bean.CreateOrderBean;
import com.p008ld.sdk.core.bean.GiftBagInfo;
import com.p008ld.sdk.core.bean.LDGradeQueryInfo;
import com.p008ld.sdk.core.bean.LDProductInfo;
import com.p008ld.sdk.core.bean.LDUploadAuth;
import com.p008ld.sdk.core.bean.LDUserInfo;
import com.p008ld.sdk.core.bean.LoginResultInfo;
import com.p008ld.sdk.core.bean.OrderRecordBean;
import com.p008ld.sdk.core.bean.OrderStatusBean;
import com.p008ld.sdk.core.bean.PayOrderBean;
import com.p008ld.sdk.core.bean.PayUrlBean;
import com.p008ld.sdk.core.bean.UserWalletResponseBean;
import com.p008ld.sdk.track.LDTrackRequest;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* compiled from: LDLoginService.kt */
/* loaded from: classes2.dex */
public interface LDLoginService {
    @POST("user/bindUser")
    Call<LDResult<Object>> bindUser(@Body RequestBody requestBody);

    @POST("user/cancelOut")
    Call<LDResult<Object>> cancelOut(@Body RequestBody requestBody);

    @POST("store/naru/package/claim")
    Call<LDResult<ClaimGiftBagInfo>> claimGiftPackage(@Body RequestBody requestBody);

    @POST("member/pay/order/direct-purchase/init_create")
    Call<LDResult<CreateOrderBean>> createDirectOrder(@HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST("user/genLogUploadAuth")
    Call<LDResult<LDUploadAuth>> genLogUploadAuth(@Body RequestBody requestBody);

    @POST("user/genProfileImgUploadAuth")
    Call<LDResult<LDUploadAuth>> genProfileImgUploadAuth(@Body RequestBody requestBody);

    @GET("member/pay/config/resource")
    Call<LDResult<ConfigBean>> getConfig(@HeaderMap Map<String, String> map);

    @GET("member/pay/coupon/user/coupon/list")
    Call<LDResult<List<CouponBean>>> getUserCouponList(@HeaderMap Map<String, String> map, @Query("userId") String str, @Query("type") int i);

    @POST("user/getUserInfo")
    Call<LDResult<LDUserInfo>> getUserInfo(@Body RequestBody requestBody);

    @GET("member/user/wallet")
    Call<LDResult<UserWalletResponseBean>> getUserWallet(@HeaderMap Map<String, String> map, @Query("userId") String str);

    @POST("member/user/login")
    Call<LDResult<LoginResultInfo>> loginSpace(@HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST("user/logout")
    Call<LDResult<Object>> logout(@Body RequestBody requestBody);

    @POST("member/pay/order/direct-purchase/other_notify")
    Call<LDResult<PayOrderBean>> payDirectOrder(@HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST("user/pcLogin")
    Call<LDResult<LDUser>> pcLogin(@Body RequestBody requestBody);

    @POST("member/pay/order/direct-purchase/list")
    Call<LDResult<List<OrderRecordBean>>> queryDirectPurchaseList(@Body RequestBody requestBody);

    @POST("store/naru/app/package")
    Call<LDResult<GiftBagInfo>> queryGiftPackages(@Body RequestBody requestBody);

    @POST("client/pay/grades/query")
    Call<LDResult<List<LDGradeQueryInfo>>> queryGrades(@Body RequestBody requestBody);

    @POST("member/pay/order/lb/list")
    Call<LDResult<List<OrderRecordBean>>> queryLBList(@Body RequestBody requestBody);

    @GET("member/pay/order/query")
    Call<LDResult<OrderStatusBean>> queryOrderStatus(@HeaderMap Map<String, String> map, @Query("orderId") String str);

    @POST("/member/pay/order/cp/product/list")
    Call<LDResult<List<LDProductInfo>>> queryProducts(@Body RequestBody requestBody);

    @POST("member/pay/order/recharge-lb/create")
    Call<LDResult<PayUrlBean>> rechargeLB(@HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST("member/pay/cp/app/save/role/info")
    Call<LDResult<Object>> reportRoleInfo(@HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST("user/sendEmail")
    Call<LDResult<Object>> sendEmail(@Body RequestBody requestBody);

    @POST("user/updateUser")
    Call<LDResult<Object>> updateUser(@Body RequestBody requestBody);

    @POST("user/updateUserBasicInfo")
    Call<LDResult<Object>> updateUserBasicInfo(@Body RequestBody requestBody);

    @POST("collection/upload")
    Call<LDResult<Object>> uploadCollectionData(@HeaderMap Map<String, String> map, @Body List<LDTrackRequest> list);

    @POST("user/login")
    Call<LDResult<LDUser>> userLogin(@Body RequestBody requestBody);
}
