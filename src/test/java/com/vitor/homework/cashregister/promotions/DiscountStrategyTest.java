package com.vitor.homework.cashregister.promotions;

import com.vitor.homework.cashregister.product.ProductInfo;
import com.vitor.homework.cashregister.promotions.entities.ProductSummary;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class DiscountStrategyTest {

    @Test
    public void testCalcSaleResult() throws Exception {
        float percentOff = 0.05f;
        BaseStrategy strategy = new DiscountStrategy(1, "5% off", percentOff);

        float price = 10.f;
        ProductInfo productInfo =
                new ProductInfo("ITEM000004", "code", "bottles", price);

        int oriNum = 10;
        ProductSummary summary = new ProductSummary(productInfo, oriNum);

        strategy.calcSaleResult(summary);

        float oriTotal = price * oriNum;
        float discountAmount = oriTotal * percentOff;
        float subTotal = oriTotal - discountAmount;
        float savedTotal = discountAmount;

        assertEquals(oriTotal, summary.calcOriTotal(), 0.001f);
        assertEquals(discountAmount, summary.calcDiscountAmount(), 0.001f);
        assertEquals(subTotal, summary.calcSubTotal(), 0.001f);
        assertEquals(savedTotal, summary.calcTotalSaved(), 0.001f);
    }
}