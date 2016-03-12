package com.vitor.homework.cashregister.promotions;

import com.vitor.homework.cashregister.promotions.entities.ProductSummary;
import com.vitor.homework.cashregister.utils.Utils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public abstract class BaseStrategy {

    private final String mID;
    protected int mPriority;
    protected String mDescription;
    protected Set<String> mPromProducts;

    /**
     * The abstract class of promotion strategy
     *
     * @param priority
     * @param description Promotion description, can be used for group print
     */
    public BaseStrategy(int priority, String description) {
        this.mPriority = priority;
        this.mDescription = description;
        mPromProducts = new HashSet<>();

        mID = UUID.randomUUID().toString();
    }

    public void putPromProduct(String productID) {
        if (!Utils.isStringEmpty(productID)) {
            mPromProducts.add(productID);
        }
    }

    public boolean containsProduct(String productID) {
        return !Utils.isStringEmpty(productID)
                && mPromProducts.contains(productID);
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPromDescription(String description) {
        this.mDescription = description;
    }

    public String getPromDescription() {
        return mDescription;
    }

    public String getID() {
        return mID;
    }

    public abstract void calcSaleResult(ProductSummary summary);

}
