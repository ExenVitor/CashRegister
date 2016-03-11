package com.vitor.homework.cashregister.barcode;

import com.google.gson.Gson;
import com.vitor.homework.cashregister.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class BarcodeInfoParser {
    private static final String BARCODE_REGEX = "(^ITEM\\d+)(-(\\d+))?";
    private static final String INVALID_INPUT_MSG = "Barcode data invalid!!" +
            " Please check your input and try again.";

    private Pattern mPattern;

    public BarcodeInfoParser() {
        mPattern = Pattern.compile(BARCODE_REGEX);
    }

    public ArrayList<BarcodeInfo> parse(String barcodeJson)
            throws IllegalArgumentException {
        String[] itemArray = parseFromJson(barcodeJson);
        if (itemArray == null || itemArray.length == 0) {
            throw new IllegalArgumentException(INVALID_INPUT_MSG);
        }

        LinkedHashMap<String, BarcodeInfo> barcodeMap = genBarcodeMap(itemArray);

        return new ArrayList<>(barcodeMap.values());
    }

    private String[] parseFromJson(String barcodeJson) {
        Gson gson = new Gson();
        String[] itemArray;
        try {
            itemArray = gson.fromJson(barcodeJson,
                    String[].class);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_INPUT_MSG);
        }
        return itemArray;
    }

    private LinkedHashMap<String, BarcodeInfo> genBarcodeMap(String[] barcodeData) {
        LinkedHashMap<String, BarcodeInfo> barcodeMap =
                new LinkedHashMap<>();

        for (String data : barcodeData) {
            Matcher matcher = mPattern.matcher(data);
            if (matcher.matches()) {
                String productID = matcher.group(1);
                String num = matcher.group(3);
                if (Utils.isStringEmpty(num)) {
                    num = "1";
                }

                BarcodeInfo info = barcodeMap.get(productID);
                if (info == null) {
                    info = new BarcodeInfo(productID);
                }
                info.increaseNum(Integer.parseInt(num));
                barcodeMap.put(productID, info);

            } else {
                throw new IllegalArgumentException(INVALID_INPUT_MSG);
            }
        }
        return barcodeMap;
    }

}
