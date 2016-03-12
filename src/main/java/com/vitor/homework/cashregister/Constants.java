package com.vitor.homework.cashregister;

import com.vitor.homework.cashregister.product.ProductInfo;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class Constants {
    public static final ProductInfo BADMINTON =
            new ProductInfo("ITEM000001", "羽毛球", "个", 1.f);

    public static final ProductInfo COKE =
            new ProductInfo("ITEM000005", "可口可乐", "瓶", 3.f);

    public static final ProductInfo APPLE =
            new ProductInfo("ITEM000005", "苹果", "斤", 5.5f);

    public static final String BARCODE_JSON =
                    "[\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000003-2',\n" +
                    "    'ITEM000005',\n" +
                    "    'ITEM000005',\n" +
                    "    'ITEM000005'\n" +
                    "]";
}
