package com.oversea.ab_firstarea.net.model;

/* loaded from: classes.dex */
public class ProductDetail {
    private String description;
    private String formattedPrice;
    private String name;
    private long priceAmountMicros;
    private String priceCurrencyCode;
    private String productId;
    private String title;
    private String type;

    public long getPriceAmountMicros() {
        return this.priceAmountMicros;
    }

    public void setPriceAmountMicros(long j) {
        this.priceAmountMicros = j;
    }

    public String getPriceCurrencyCode() {
        return this.priceCurrencyCode;
    }

    public void setPriceCurrencyCode(String str) {
        this.priceCurrencyCode = str;
    }

    public String getFormattedPrice() {
        return this.formattedPrice;
    }

    public void setFormattedPrice(String str) {
        this.formattedPrice = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
