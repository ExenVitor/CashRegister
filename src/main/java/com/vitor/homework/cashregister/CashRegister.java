package com.vitor.homework.cashregister;

import com.vitor.homework.cashregister.barcode.BarcodeInfo;
import com.vitor.homework.cashregister.barcode.BarcodeInfoParser;
import com.vitor.homework.cashregister.product.ProductInfo;
import com.vitor.homework.cashregister.product.ProductManager;
import com.vitor.homework.cashregister.promotions.BaseStrategy;
import com.vitor.homework.cashregister.promotions.IStrategyComparator;
import com.vitor.homework.cashregister.promotions.entities.ProductSummary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class CashRegister {

    private ProductManager mProductManager;
    private ArrayList<BaseStrategy> mPromStrategies;
    private HashMap<String, BaseStrategy> mPromStrategyMap;
    private IStrategyComparator mStrategyComparator;
    private BarcodeInfoParser mBarcodeInfoParser;
    private ReceiptBuilder mReceiptBuilder;

    public CashRegister() {
        init();
    }

    private void init() {
        mProductManager = new ProductManager();
        mPromStrategies = new ArrayList<>();
        mPromStrategyMap = new HashMap<>();
        mStrategyComparator = new IStrategyComparator();
        mBarcodeInfoParser = new BarcodeInfoParser();
        mReceiptBuilder = new ReceiptBuilder();
    }

    public void putProductInfo(ProductInfo productInfo) {
        mProductManager.putProductInfo(productInfo);
    }

    public void addPromStrategy(BaseStrategy strategy) {
        if (strategy != null) {
            mPromStrategies.add(strategy);
            Collections.sort(mPromStrategies, mStrategyComparator);
            mPromStrategyMap.put(strategy.getID(), strategy);
        }
    }

    public void removePromStrategy(BaseStrategy strategy) {
        mPromStrategies.remove(strategy);
        mPromStrategyMap.remove(strategy.getID());
    }

    public void clearPromStrategy() {
        mPromStrategies.clear();
        mPromStrategyMap.clear();
    }

    public void print(String barcodeJson) {

        ArrayList<BarcodeInfo> barcodeList = parseBarcode(barcodeJson);

        ArrayList<ProductSummary> summaryList = genSummaryList(barcodeList);

        System.out.println(mReceiptBuilder.build(summaryList, mPromStrategyMap));

    }

    private ArrayList<BarcodeInfo> parseBarcode(String barcodeJson) {
        ArrayList<BarcodeInfo> barcodeList = null;
        try {
            barcodeList = mBarcodeInfoParser.parse(barcodeJson);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return barcodeList;
    }

    private ArrayList<ProductSummary> genSummaryList(ArrayList<BarcodeInfo> barcodeList) {
        ArrayList<ProductSummary> summaryList = new ArrayList<>();
        for (BarcodeInfo barcodeInfo : barcodeList) {
            String productID = barcodeInfo.getProductID();
            int num = barcodeInfo.getNum();
            ProductInfo productInfo =
                    mProductManager.getProductInfo(productID);
            if (productInfo == null) {
                throw new IllegalArgumentException("Can not find the product," +
                        " ID: " + productID);
            }

            ProductSummary summary = new ProductSummary(productInfo, num);

            //apply promotion
            applyPromotion(summary);

            summaryList.add(summary);

        }
        return summaryList;
    }

    /**
     * Apply promotions for product summary if any promotion strategy contains it
     *
     * @param summary
     */
    private void applyPromotion(ProductSummary summary) {
        int currentPriority = Integer.MAX_VALUE;
        String productID = summary.getProductInfo().getProductID();
        for (BaseStrategy strategy : mPromStrategies) {

            if (currentPriority != strategy.getPriority()
                    && currentPriority != Integer.MAX_VALUE) {
                break;
            }

            if (strategy.containsProduct(productID)) {
                strategy.calcSaleResult(summary);
                currentPriority = strategy.getPriority();
            }

        }
    }
}
