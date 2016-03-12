package com.vitor.homework.cashregister.promotions.entities;

import com.vitor.homework.cashregister.product.ProductInfo;

import java.util.ArrayList;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class ProductSummary {
    private final ProductInfo mProductInfo;
    private final int mOriNum;

    private ArrayList<SaleResult> mSaleResults;

    public ProductSummary(ProductInfo productInfo, int oriNum) {
        this.mProductInfo = productInfo;
        this.mOriNum = oriNum;

        this.mSaleResults = new ArrayList<>();
    }

    public int getOriNum() {
        return mOriNum;
    }

    public ProductInfo getProductInfo() {
        return mProductInfo;
    }

    public ArrayList<SaleResult> getSaleResults(){
        return mSaleResults;
    }

    public void appendSaleResult(SaleResult result){
        mSaleResults.add(result);
    }

    public float calcOriTotal() {
        float price = mProductInfo.getPrice();
        return mOriNum * price;
    }

    public float calcDiscountAmount(){
        float discountAmount = 0;
        for (SaleResult saleResult : mSaleResults){
            discountAmount += saleResult.getDiscountAmount();
        }
        return discountAmount;
    }

    public int calcFreeNum(){
        int freeNum = 0;
        for (SaleResult saleResult : mSaleResults){
            freeNum += saleResult.getFreeNum();
        }
        return freeNum;
    }

    public int calcTotalNum(){
        return calcFreeNum() + mOriNum;
    }

    public float calcSubTotal() {
        return calcOriTotal() - calcDiscountAmount();
    }

    public float calcTotalSaved() {
        return calcDiscountAmount() + calcFreeSaved();
    }

    public float calcFreeSaved(){
        return calcFreeNum() * mProductInfo.getPrice();
    }
}
