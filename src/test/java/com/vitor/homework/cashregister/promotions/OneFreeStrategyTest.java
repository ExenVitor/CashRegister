package com.vitor.homework.cashregister.promotions;

import com.vitor.homework.cashregister.product.ProductInfo;
import com.vitor.homework.cashregister.promotions.entities.ProductSummary;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class OneFreeStrategyTest {

    @Test
    public void testCalcSaleResult() throws Exception {
        int purchasedNum = 2;
        BaseStrategy strategy = new OneFreeStrategy(1, "buy 2 get one free", purchasedNum);

        float price = 10.f;
        ProductInfo productInfo =
                new ProductInfo("ITEM000004", "code", "bottles", price);

        int oriNum = 11;
        ProductSummary summary = new ProductSummary(productInfo, oriNum);

        strategy.calcSaleResult(summary);

        int getFreeNum = oriNum / purchasedNum;
        int totalNum = getFreeNum + oriNum;
        float oriTotal = price * oriNum;
        float freeSaved = getFreeNum * price;
        float subTotal = oriTotal;
        float totalSaved = freeSaved;

        assertEquals(getFreeNum, summary.calcFreeNum());
        assertEquals(totalNum, summary.calcTotalNum());
        assertEquals(oriTotal, summary.calcOriTotal(), 0.001f);
        assertEquals(freeSaved, summary.calcFreeSaved(), 0.001f);
        assertEquals(subTotal, summary.calcSubTotal(), 0.001f);
        assertEquals(totalSaved, summary.calcTotalSaved(), 0.001f);

    }
}