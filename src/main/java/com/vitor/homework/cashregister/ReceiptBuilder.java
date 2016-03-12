package com.vitor.homework.cashregister;

import com.vitor.homework.cashregister.product.ProductInfo;
import com.vitor.homework.cashregister.promotions.BaseStrategy;
import com.vitor.homework.cashregister.promotions.entities.ProductSummary;
import com.vitor.homework.cashregister.promotions.entities.SaleResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class ReceiptBuilder {

    private static final String TITLE = "***<没钱赚商店>购物清单***\n";
    private static final String DIVIDER = "----------------------\n";
    private static final String END = "**********************";

    private static final String PRODUCT_BASIC_INFO =
            "名称：%s，数量：%d%s，单价：%.2f(元)，小计：%.2f(元)";

    private static final String DISCOUNT_SAVED_AMOUNT = "，节省%.2f(元)";

    private static final String GROUP_INFO = "名称：%s，数量：%d%s";

    private static final String TOTAL = "总计：%0.2f(元)";

    private static final String TOTAL_SAVED_AMOUNT = "节省：%0.2f(元)";

    /**
     * key: Promotion ID
     */
    private LinkedHashMap<String, ArrayList<String>> mGroupInfoMap =
            new LinkedHashMap<>();

    private float mTotal = 0;

    private float mTotalSaved = 0;

    public ReceiptBuilder() {
        init();
    }

    private void init(){
        mGroupInfoMap.clear();
        mTotal = 0;
        mTotalSaved = 0;
    }

    public String build(ArrayList<ProductSummary> summaryList,
                        HashMap<String, BaseStrategy> strategyMap) {
        init();
        String receipt = null;
        if (summaryList != null && summaryList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder(TITLE);
            traverseSummaries(summaryList, stringBuilder);

            stringBuilder.append(DIVIDER);

            traverseGroupInfoMap(strategyMap, stringBuilder);

            stringBuilder.append(String.format(TOTAL, mTotal));

            if(mTotalSaved > 0){
                stringBuilder.append(String.format(TOTAL_SAVED_AMOUNT, mTotalSaved));
            }

            stringBuilder.append(END);

            receipt = stringBuilder.toString();
        }
        return receipt;
    }

    private void traverseSummaries(ArrayList<ProductSummary> summaryList,
                                   StringBuilder sb) {
        for (ProductSummary summary : summaryList) {
            ProductInfo info = summary.getProductInfo();
            String productName = info.getName();
            int totalNum = summary.calcTotalNum();
            String unit = info.getUnit();
            float price = info.getPrice();
            float subtotal = summary.calcSubTotal();
            float totalSavedAmount = summary.calcTotalSaved();

            mTotal += subtotal;
            mTotalSaved += totalSavedAmount;

            sb.append(String.format(PRODUCT_BASIC_INFO,
                    productName, totalNum, unit, price, subtotal));

            float discountAmount = summary.calcDiscountAmount();
            if (discountAmount > 0) {
                sb.append(String.format(DISCOUNT_SAVED_AMOUNT, discountAmount));
            }

            sb.append("\n");

            traverseSaleResults(info, summary.getSaleResults());
        }
    }

    private void traverseSaleResults(ProductInfo info, ArrayList<SaleResult> resultList) {
        if (resultList != null) {
            for (SaleResult saleResult : resultList) {
                //Only group buy some get one free promotions
                int freeNum = saleResult.getFreeNum();
                String strategyID = saleResult.getStrategyID();
                if (saleResult.getFreeNum() > 0) {
                    ArrayList<String> infoList =
                            mGroupInfoMap.get(strategyID);
                    if (infoList == null) {
                        infoList = new ArrayList<>();
                    }
                    infoList.add(String.format(GROUP_INFO,
                            info.getName(),
                            freeNum,
                            info.getUnit()));
                    mGroupInfoMap.put(strategyID, infoList);
                }
            }
        }
    }

    private void traverseGroupInfoMap(HashMap<String, BaseStrategy> strategyMap,
                                      StringBuilder sb) {
        if (mGroupInfoMap.size() > 0) {
            Set<String> strategyIDs = mGroupInfoMap.keySet();
            for (String id : strategyIDs) {
                BaseStrategy strategy = strategyMap.get(id);
                sb.append(strategy.getPromDescription()).append("：");
                ArrayList<String> infoGroup = mGroupInfoMap.get(id);
                for (String info : infoGroup) {
                    sb.append(info).append("\n");
                }
                sb.append(DIVIDER);
            }
        }
    }
}
