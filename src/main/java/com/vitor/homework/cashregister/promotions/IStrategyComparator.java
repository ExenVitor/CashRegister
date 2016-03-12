package com.vitor.homework.cashregister.promotions;

import java.util.Comparator;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class IStrategyComparator implements Comparator<BaseStrategy> {
    @Override
    public int compare(BaseStrategy o1, BaseStrategy o2) {
        return o2.getPriority() - o1.getPriority();
    }
}
