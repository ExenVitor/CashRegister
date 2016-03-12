package com.vitor.homework.cashregister.product;

import com.vitor.homework.cashregister.utils.Utils;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class ProductInfo {
    private String mProductID;
    private String mName;
    private String mUnit;
    private float mPrice;

    public ProductInfo(String productID, String name, String unit, float price) {
        this.mProductID = productID;
        this.mName = name;
        this.mUnit = unit;
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

    public String getUnit(){
        return mUnit;
    }

    void setUnit(String unit){
        this.mUnit = unit;
    }

    public float getPrice() {
        return mPrice;
    }

    void setPrice(float price) {
        this.mPrice = price;
    }

    public boolean isValidProduct() {
        return !Utils.isStringEmpty(mProductID)
                && !Utils.isStringEmpty(mUnit)
                && !Utils.isStringEmpty(mName)
                && mPrice >= 0;
    }
}
