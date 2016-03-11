package com.vitor.homework.cashregister.barcode;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Vitor Chen on 16-3-12.
 * mail: exen3995@gmail.com
 */

public class BarcodeInfoParserTest {

    @Test
    public void testParse() throws Exception {
        BarcodeInfoParser parser = new BarcodeInfoParser();

        ArrayList<BarcodeInfo> infoArray;

        //invalid input
        String invalidStr = "'ITEM0000001'";
        infoArray = parseInput(parser, invalidStr);
        Assert.assertNull(infoArray);

        invalidStr = "[ '1ITEM3' ]";
        infoArray = parseInput(parser, invalidStr);
        Assert.assertNull(infoArray);

        invalidStr = "[ 'ITEM33sdf2' ]";
        infoArray = parseInput(parser, invalidStr);
        Assert.assertNull(infoArray);

        invalidStr = "[ 'ITEM000002-1sdf' ]";
        infoArray = parseInput(parser, invalidStr);
        Assert.assertNull(infoArray);

        invalidStr = "[ 'ITEM000002-' ]";
        infoArray = parseInput(parser, invalidStr);
        Assert.assertNull(infoArray);

        invalidStr = "[ 'ITEM000001'," +
                    " 'ITEM24df4'," +
                    " '1ITEM3435'," +
                    " 'ITEM24324-4' ]";
        infoArray = parseInput(parser, invalidStr);
        Assert.assertNull(infoArray);


        //valid input
        String validStr = "[" +
                "    'ITEM000001'," +
                "    'ITEM000001'," +
                "    'ITEM000001'," +
                "    'ITEM000001'," +
                "    'ITEM000001'," +
                "    'ITEM000003-2'," +
                "    'ITEM000005'," +
                "    'ITEM000005'," +
                "    'ITEM000005'" +
                "]";

        infoArray = parseInput(parser, validStr);

        assertNotNull(infoArray);
        assertEquals(infoArray.size(), 3);

        for (BarcodeInfo info : infoArray) {
            String productID = info.getProductID();
            int num = info.getNum();
            if ("ITEM000001".equals(productID)) {
                assertEquals(info.getNum(), 5);
            } else if ("ITEM000003".equals(productID)) {
                assertEquals(num, 2);
            } else if ("ITEM000005".equals(productID)) {
                assertEquals(num, 3);
            }
        }
    }

    private static ArrayList<BarcodeInfo> parseInput(BarcodeInfoParser parser, String input) {
        ArrayList<BarcodeInfo> infoArray = null;
        try {
            infoArray = parser.parse(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return infoArray;
    }
}