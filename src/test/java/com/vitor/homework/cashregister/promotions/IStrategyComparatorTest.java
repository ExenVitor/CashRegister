package com.vitor.homework.cashregister.promotions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class IStrategyComparatorTest {

    @Test
    public void testCompare() throws Exception {

        ArrayList<BaseStrategy> strategies = new ArrayList<>();
        BaseStrategy lowPriority = new DiscountStrategy(1, "%5 off", 0.05f);
        BaseStrategy highPriority = new OneFreeStrategy(2, "Buy two get one for free", 2);

        strategies.add(lowPriority);
        strategies.add(highPriority);

        Collections.sort(strategies, new IStrategyComparator());

        assertTrue(strategies.indexOf(lowPriority) > strategies.indexOf(highPriority));
    }
}