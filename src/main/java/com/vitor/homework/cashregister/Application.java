package com.vitor.homework.cashregister;

import com.vitor.homework.cashregister.promotions.DiscountStrategy;
import com.vitor.homework.cashregister.promotions.OneFreeStrategy;

/**
 * Created by Vitor Chen on 16-3-11.
 * mail: exen3995@gmail.com
 */

public class Application {

    public static void main(String []args){

        //initial cash register
        CashRegister cashRegister = new CashRegister();
        cashRegister.putProductInfo(Constants.APPLE);
        cashRegister.putProductInfo(Constants.BADMINTON);
        cashRegister.putProductInfo(Constants.COKE);

        DiscountStrategy discountStrategy =
                new DiscountStrategy(1, "95折商品", 0.05f);
        OneFreeStrategy oneFreeStrategy =
                new OneFreeStrategy(2, "买二赠一商品", 2);

        //Scene 1: buy two, get one free
        System.out.println("有符合“买二赠一”优惠条件的商品：");

        cashRegister.clearPromStrategy();
        oneFreeStrategy.clearPromProduct();

        oneFreeStrategy.putPromProduct(Constants.BADMINTON.getProductID());
        oneFreeStrategy.putPromProduct(Constants.COKE.getProductID());

        cashRegister.addPromStrategy(oneFreeStrategy);
        cashRegister.print(Constants.BARCODE_JSON);

        System.out.println("\n");

        //Scene 2: no promotions
        System.out.println("没有任何优惠：");

        cashRegister.clearPromStrategy();
        cashRegister.print(Constants.BARCODE_JSON);

        System.out.println("\n");

        //Scene 3: 5% off promotion
        System.out.println("有符合“95折”优惠条件的商品：");
        discountStrategy.clearPromProduct();

        discountStrategy.putPromProduct(Constants.APPLE.getProductID());

        cashRegister.clearPromStrategy();
        cashRegister.addPromStrategy(discountStrategy);
        cashRegister.print(Constants.BARCODE_JSON);

        System.out.println("\n");

        //Scene 4: buy two, get one free & 5% off promotion at the same time
        System.out.println("有符合“95折”优惠条件的商品，又有符合“买二赠一”优惠条件的商品：");

        oneFreeStrategy.clearPromProduct();
        oneFreeStrategy.putPromProduct(Constants.BADMINTON.getProductID());
        oneFreeStrategy.putPromProduct(Constants.COKE.getProductID());

        discountStrategy.clearPromProduct();
        discountStrategy.putPromProduct(Constants.COKE.getProductID());
        discountStrategy.putPromProduct(Constants.APPLE.getProductID());

        cashRegister.clearPromStrategy();
        cashRegister.addPromStrategy(oneFreeStrategy);
        cashRegister.addPromStrategy(discountStrategy);

        cashRegister.print(Constants.BARCODE_JSON);

    }
}
