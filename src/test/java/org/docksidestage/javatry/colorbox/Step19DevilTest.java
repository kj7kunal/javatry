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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.size.BoxSize;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Devil with color-box, (try if you woke up Devil in StringTest) <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step19DevilTest extends PlainTestCase {

    List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

    public List<Object> extractContent(){
        return colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> {
                    if(boxSpace instanceof DoorBoxSpace){
                        ((DoorBoxSpace)boxSpace).openTheDoor();
                    }
                    return boxSpace.getContent();
                })
                .collect(Collectors.toList());

    }

    // ===================================================================================
    //                                                                        Devil Parade
    //                                                                        ============
    /**
     * Find a colorbox(lets call it A) that contains null,
     * Next find a colorbox(B) that name ends with third character of colorbox A,
     * Next find a colorbox(C) that the list in the space contains a number that 2nd decimal place is same as tens place of depth of color-box B,
     * Next find a colorbox(D) that length of name is same as 1st place number of BigDecimal value found in the colorbox C,
     * At last, the question is what is in the lowest space of colorbox D? <br>
     * (nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっている
     * スペースの中のリストの中で最初に見つかるBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？)
     */
    public void test_too_long() {
        char charThird = colorBoxList.stream()
                .filter(cB -> cB.getSpaceList().stream().anyMatch(bS->bS.getContent() == null))
                .map(c -> c.getColor().getColorName())
                .collect(Collectors.toList()).get(0).charAt(2);
        log(charThird);

        int depth = colorBoxList.stream()
                .filter(cB -> cB.getColor().getColorName().endsWith(String.valueOf(charThird)))
                .map(cB -> cB.getSize().getDepth()).collect(Collectors.toList()).get(0)/10;
        log(depth);

        int len = colorBoxList.stream()
                .flatMap(cB -> cB.getSpaceList().stream())
                .map(bS->bS.getContent())
                .filter(c -> c instanceof List)
                .map(c -> (List<?>)c).flatMap(l -> l.stream())
                .filter(l -> l instanceof BigDecimal)
                .map(l-> (BigDecimal)l)
                .filter(c -> {
                    int i = String.valueOf(c.doubleValue()).indexOf('.');
                    if (String.valueOf(c.doubleValue()).length()<=i+2){
                        return false;
                    }
                    return Character.getNumericValue(String.valueOf(c.doubleValue()).charAt(i+2)) == depth;
                })
                .mapToInt(c -> Character.getNumericValue(String.valueOf(c.doubleValue()).charAt(0)))
                .sum();
        log(len);

         log(colorBoxList.stream()
                .filter(cB -> cB.getColor().getColorName().length() == len)
                .map(cB -> cB.getSpaceList().get(2).getContent()).collect(Collectors.toList()));

    }




    // ===================================================================================
    //                                                                      Java Destroyer
    //                                                                      ==============
    /**
     * What string of toString() is BoxSize of red color-box after changing height to 160 (forcedly in this method)? <br>
     * ((このテストメソッドの中だけで無理やり)赤いカラーボックスの高さを160に変更して、BoxSizeをtoString()すると？)
     */
    public void test_looks_like_easy() {
        ColorBox redcolorbox = new YourPrivateRoom().getColorBoxList().stream()
                .filter(box -> box.getColor().getColorName().equals("red"))
                .collect(Collectors.toList()).get(0);
        log(redcolorbox);

        try {
            Field height = redcolorbox.getSize().getClass().getDeclaredField("height");
            height.setAccessible(true);
            height.setInt(redcolorbox.getSize(), 160);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {       //forcing
            throw new IllegalStateException("Failed to handle field object of height: ", e);
        }

        log(redcolorbox);
    }

    // ===================================================================================
    //                                                                        Meta Journey
    //                                                                        ============
    /**
     * What value is returned from functional method of interface that has FunctionalInterface annotation in color-boxes? <br> 
     * (カラーボックスに入っているFunctionalInterfaceアノテーションが付与されているインターフェースのFunctionalメソッドの戻り値は？)
     */
    public void test_be_frameworker() {
        extractContent().stream()
                .filter(c -> c instanceof YourPrivateRoom.FavoriteProvider)
                .forEach(c -> log(((YourPrivateRoom.FavoriteProvider) c).justHere()));
    }
}
