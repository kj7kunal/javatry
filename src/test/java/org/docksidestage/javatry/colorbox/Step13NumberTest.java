/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.cert.CollectionCertStoreParameters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(c -> c instanceof Integer || c instanceof Long || c instanceof BigInteger)
                .map(c -> (Number)c)
                .filter(x -> x.intValue()>=0 && x.intValue()<=54)
                .collect(Collectors.toList());//count();

        log("Total count of integers: "+answer.size()+" => "+answer);
    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_Number() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(c -> c instanceof Number)
                .map(c -> (Number)c)
                .filter(x -> x.doubleValue()>=0 && x.doubleValue()<=54)
                .collect(Collectors.toList());//count();

        log("Total count of numbers: "+answer.size()+" => "+answer);
    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String color = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream().map(bS -> bS.getContent())
                        .anyMatch(c -> c instanceof Integer || c instanceof Long || c instanceof BigInteger))
                .reduce(colorBoxList.get(0), (s1, s2) -> s1.getSize().getWidth() > s2.getSize().getWidth() ? s1 : s2)
                .getColor().getColorName();
        log(color + ": colorbox has the biggest width and has integer content");
    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        BigDecimal answer = colorBoxList.stream()                           //open colorbox stream
                .flatMap(colorBox -> colorBox.getSpaceList().stream())      //flatmap colorbox and get spaces for each colorbox
                .map(boxSpace -> boxSpace.getContent())                     //get content for each space
                .filter(c -> c instanceof List)                             //filter content that have lists
                .map(c -> (List<Object>)c)                                  //typecast into List<Object>
                .flatMap(l -> l.stream())                                   //stream list
                .filter(l -> l instanceof BigDecimal)                       //take only bigdecimals in each list
                .map(l-> (BigDecimal)l)                                     //cast to BigDecimal.class
                .reduce(BigDecimal.ZERO, (x,y)->(BigDecimal)x.add((BigDecimal)y));  //add all BigDecimals

        log("Total sum of bigdecimal: "+answer);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {
    }
}
