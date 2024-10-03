package com.oversea.ab_firstplatform.plugin.pay;

import android.text.TextUtils;
import com.ffandroid.sdk.FF_ProductType;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;

/* loaded from: classes2.dex */
public class Lxhw_PayParams {
    private float amount;
    private int balance;
    private String commodityPrice;
    private String currencyType;
    private String orderId;
    private int roleLevel;
    private String productId = "";
    private String storeProductId = "";
    private String productName = "";
    private String productDesc = "";
    private String serverId = "";
    private String serverName = "";
    private String roleId = "";
    private String roleName = "";
    private String vip = "";
    private String cpOrderID = "";
    private String extension = "";
    private String product_type = "coin";

    /* loaded from: classes2.dex */
    public enum ProductType {
        COIN,
        PACKAGE
    }

    public String getCommodityPrice() {
        return this.commodityPrice;
    }

    public void setCommodityPrice(String str) {
        this.commodityPrice = str;
    }

    public String getCurrencyType() {
        return this.currencyType;
    }

    public void setCurrencyType(String str) {
        this.currencyType = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getProductId() {
        if (TextUtils.isEmpty(this.productId)) {
            this.productId = "";
        }
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getStoreProductId() {
        return this.storeProductId;
    }

    public void setStoreProductId(String str) {
        this.storeProductId = str;
    }

    public String getProductName() {
        if (TextUtils.isEmpty(this.productName)) {
            this.productName = "";
        }
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductDesc() {
        if (TextUtils.isEmpty(this.productDesc)) {
            this.productDesc = "";
        }
        return this.productDesc;
    }

    public void setProductDesc(String str) {
        this.productDesc = str;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int i) {
        this.balance = i;
    }

    public String getServerId() {
        if (TextUtils.isEmpty(this.serverId)) {
            this.serverId = "";
        }
        return this.serverId;
    }

    public void setServerId(String str) {
        this.serverId = str;
    }

    public String getServerName() {
        if (TextUtils.isEmpty(this.serverName)) {
            this.serverName = "";
        }
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public String getRoleId() {
        if (TextUtils.isEmpty(this.roleId)) {
            this.roleId = "";
        }
        return this.roleId;
    }

    public void setRoleId(String str) {
        this.roleId = str;
    }

    public String getRoleName() {
        if (TextUtils.isEmpty(this.roleName)) {
            this.roleName = "";
        }
        return this.roleName;
    }

    public void setRoleName(String str) {
        this.roleName = str;
    }

    public int getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(int i) {
        this.roleLevel = i;
    }

    public String getVip() {
        if (TextUtils.isEmpty(this.vip)) {
            this.vip = "";
        }
        return this.vip;
    }

    public void setVip(String str) {
        this.vip = str;
    }

    public String getCpOrderID() {
        if (TextUtils.isEmpty(this.cpOrderID)) {
            this.cpOrderID = Lxhw_XUserInfo.getInstance().getSdkId() + "_" + System.currentTimeMillis();
        }
        return this.cpOrderID;
    }

    public void setCpOrderID(String str) {
        this.cpOrderID = str;
    }

    public String getProduct_type() {
        if (TextUtils.isEmpty(this.product_type)) {
            this.product_type = "";
        }
        return this.product_type;
    }

    public void setProduct_type(ProductType productType) {
        if (productType == ProductType.COIN) {
            this.product_type = "coin";
        } else if (productType == ProductType.PACKAGE) {
            this.product_type = "package";
        }
    }

    public void setProduct_type(FF_ProductType fF_ProductType) {
        if (fF_ProductType == FF_ProductType.COIN) {
            this.product_type = "coin";
        } else if (fF_ProductType == FF_ProductType.PACKAGE) {
            this.product_type = "package";
        }
    }

    public String getExtension() {
        if (TextUtils.isEmpty(this.extension)) {
            this.extension = "";
        }
        return this.extension;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public String toString() {
        return "Lxhw_PayParams{productId='" + this.productId + "', storeProductId='" + this.storeProductId + "', productName='" + this.productName + "', amount=" + this.amount + ", serverId='" + this.serverId + "', serverName='" + this.serverName + "', roleId='" + this.roleId + "', roleName='" + this.roleName + "', roleLevel=" + this.roleLevel + ", vip='" + this.vip + "', orderId='" + this.orderId + "', currencyType='" + this.currencyType + "', commodityPrice='" + this.commodityPrice + "'}";
    }
}
