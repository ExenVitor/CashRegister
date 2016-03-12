package com.vitor.homework.cashregister.promotions.entities;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class SaleResult {
    private final String mStrategyID;
    private final int mFreeNum;
    private final float mDiscountAmount;

    public SaleResult(String strategyID, int freeNum, float discountAmount){
        this.mStrategyID = strategyID;
        this.mFreeNum = freeNum;
        this.mDiscountAmount = discountAmount;
    }

    public String getStrategyID() {
        return mStrategyID;
    }

    public int getFreeNum() {
        return mFreeNum;
    }

    public float getDiscountAmount() {
        return mDiscountAmount;
    }
}
