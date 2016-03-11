package com.vitor.homework.cashregister.barcode;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

/**
 * The result of barcode data parsing
 */
public class BarcodeInfo {

    private String mProductID;
    private int mNum;

    public BarcodeInfo(String productID) {
        this(productID, 0);
    }

    public BarcodeInfo(String productID, int num) {
        this.mProductID = productID;
        this.mNum = num;
    }

    public String getProductID() {
        return mProductID;
    }

    public int getNum() {
        return mNum;
    }

    public void increaseNum(int num) {
        if (num > 0)
            mNum += num;
    }

    @Override
    public String toString() {
        return "BarcodeInfo{" +
                "mProductID='" + mProductID + '\'' +
                ", mNum=" + mNum +
                '}';
    }
}
