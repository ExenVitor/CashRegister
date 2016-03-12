package com.vitor.homework.cashregister.product;

import com.vitor.homework.cashregister.utils.Utils;

import java.util.HashMap;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class ProductManager {

    private HashMap<String, ProductInfo> mProductMap;

    public ProductManager(){
        mProductMap = new HashMap<>();
    }

    public void putProductInfo(ProductInfo productInfo){
        if(productInfo != null && productInfo.isValidProduct()){
            mProductMap.put(productInfo.getProductID(), productInfo);
        }
    }

    public ProductInfo getProductInfo(String productID){
        ProductInfo productInfo = null;
        if(!Utils.isStringEmpty(productID)){
            productInfo = mProductMap.get(productID);
        }
        return productInfo;
    }
}
