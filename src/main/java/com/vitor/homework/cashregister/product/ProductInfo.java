package com.vitor.homework.cashregister.product;

import com.vitor.homework.cashregister.utils.Utils;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class ProductInfo {
    private String mProductID;
    private String mName;
    private float mPrice;

    public ProductInfo(String productID, String name, float price) {
        this.mProductID = productID;
        this.mName = name;
        this.mPrice = price;
    }

    public String getProductID() {
        return mProductID;
    }

    void setProductID(String productID) {
        this.mProductID = productID;
    }

    public String getName() {
        return mName;
    }

    void setName(String name) {
        this.mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    void setPrice(float price) {
        this.mPrice = price;
    }

    public boolean isValidProduct() {
        return !Utils.isStringEmpty(mProductID)
                && !Utils.isStringEmpty(mName)
                && mPrice >= 0;
    }
}
