package com.vitor.homework.cashregister.product;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class ProductManagerTest {
    @Test
    public void testManager() throws Exception {
        ProductManager manager = new ProductManager();

        ProductInfo invalidProduct = new ProductInfo("", "", null, -1);
        manager.putProductInfo(invalidProduct);
        assertNull(manager.getProductInfo(invalidProduct.getProductID()));

        ProductInfo validProduct = new ProductInfo("000001", "bottles", "coke", 3.f);
        manager.putProductInfo(validProduct);
        assertEquals(validProduct,
                manager.getProductInfo(validProduct.getProductID()));
    }
}