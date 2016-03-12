package com.vitor.homework.cashregister.promotions;

import com.vitor.homework.cashregister.promotions.entities.ProductSummary;
import com.vitor.homework.cashregister.promotions.entities.SaleResult;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class DiscountStrategy extends BaseStrategy {

    private float mPercentOff;

    /**
     * The percent off discount strategy
     *
     * @param percentOff  the value range is (0.0, 1.0)
     * @throws IllegalArgumentException
     */
    public DiscountStrategy(int priority,
                            String description,
                            float percentOff) throws IllegalArgumentException {
        super(priority, description);
        if (percentOff <= 0 || percentOff >= 1) {
            throw new IllegalArgumentException("Invalid percent off!" +
                    " It must between 0.0 and 1.0");
        }
        this.mPercentOff = percentOff;
    }

    public float getPercentOff() {
        return mPercentOff;
    }

    public void setPercentOff(float percentOff) {
        this.mPercentOff = percentOff;
    }

    @Override
    public void calcSaleResult(ProductSummary summary) {
        if (summary != null) {
            float oriTotal = summary.calcOriTotal();
            float discountAmount = oriTotal * mPercentOff;
            summary.appendSaleResult(new SaleResult(getID(), 0, discountAmount));
        }
    }
}
