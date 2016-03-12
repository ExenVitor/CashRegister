package com.vitor.homework.cashregister.promotions;

import com.vitor.homework.cashregister.promotions.entities.ProductSummary;
import com.vitor.homework.cashregister.promotions.entities.SaleResult;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class OneFreeStrategy extends BaseStrategy {

    private int mPurchasedNum;

    /**
     * The buy some get one free strategy
     *
     * @param purchasedNum get one free product per this number
     * @throws IllegalArgumentException
     */
    public OneFreeStrategy(int priority, String description, int purchasedNum)
            throws IllegalArgumentException {
        super(priority, description);
        if (purchasedNum <= 0) {
            throw new IllegalArgumentException("The purchased number" +
                    " must greater than zero");
        }
        this.mPurchasedNum = purchasedNum;
    }

    public int getPurchasedNum() {
        return mPurchasedNum;
    }

    public void setPurchasedNum(int purchasedNum) {
        this.mPurchasedNum = purchasedNum;
    }

    @Override
    public void calcSaleResult(ProductSummary summary) {
        if (summary != null) {
            int oriNum = summary.getOriNum();
            int freeNum = oriNum / this.mPurchasedNum;
            if (freeNum > 0) {
                summary.appendSaleResult(new SaleResult(getID(), freeNum, 0));
            }
        }
    }
}
