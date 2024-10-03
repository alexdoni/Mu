package com.oversea.ab_firstplatform.model;

import android.text.TextUtils;
import com.oversea.ab_firstarea.net.model.ProductDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SkuProductListData {
    private static SkuProductListData instance;
    private Map<String, Product_info> product_infoMap = new HashMap();
    private Map<String, String> productIdToMoneyMap = new HashMap();
    private Map<String, ProductDetail> productIdToDetail = new HashMap();
    private List<ProductDetail> productDetailList = new ArrayList();

    public static SkuProductListData getInstance() {
        if (instance == null) {
            instance = new SkuProductListData();
        }
        return instance;
    }

    public Map<String, Product_info> getProduct_infoMap() {
        return this.product_infoMap;
    }

    public void setProduct_infoMap(Map<String, Product_info> map) {
        this.product_infoMap = map;
    }

    public List<ProductDetail> getProductDetailList() {
        List<ProductDetail> list = this.productDetailList;
        if (list != null && list.size() == 0) {
            Iterator<ProductDetail> it = getProductDetailMap().values().iterator();
            while (it.hasNext()) {
                this.productDetailList.add(it.next());
            }
        }
        return this.productDetailList;
    }

    public Map<String, ProductDetail> getProductDetailMap() {
        return this.productIdToDetail;
    }

    public Product_info queryProduct_info(String str) {
        Map<String, Product_info> map = this.product_infoMap;
        if (map == null || map.get(str) == null) {
            return null;
        }
        return this.product_infoMap.get(str);
    }

    public String getProductIdShowMoney(String str) {
        Product_info queryProduct_info = queryProduct_info(str);
        if (queryProduct_info == null) {
            return "";
        }
        if (TextUtils.isEmpty(queryProduct_info.getRealMoney())) {
            return TextUtils.isEmpty(queryProduct_info.getDefaultMoney()) ? "" : queryProduct_info.getDefaultMoney();
        }
        return queryProduct_info.getRealMoney();
    }

    /* loaded from: classes2.dex */
    public static class Product_info {
        private long price_amount_micros;
        private String product_id = "";
        private String defaultMoney = "";
        private String defaultCurrency = "";
        private String realMoney = "";
        private String realCurrency = "";
        private String title = "";
        private String description = "";

        public void setProduct_id(String str) {
            this.product_id = str;
        }

        public String getProduct_id() {
            return this.product_id;
        }

        public String getRealMoney() {
            return this.realMoney;
        }

        public void setRealMoney(String str) {
            this.realMoney = str;
        }

        public String getRealCurrency() {
            return this.realCurrency;
        }

        public void setRealCurrency(String str) {
            this.realCurrency = str;
        }

        public String getDefaultMoney() {
            return this.defaultMoney;
        }

        public void setDefaultMoney(String str) {
            this.defaultMoney = str;
        }

        public String getDefaultCurrency() {
            return this.defaultCurrency;
        }

        public void setDefaultCurrency(String str) {
            this.defaultCurrency = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public long getPrice_amount_micros() {
            return this.price_amount_micros;
        }

        public void setPrice_amount_micros(long j) {
            this.price_amount_micros = j;
        }
    }
}
