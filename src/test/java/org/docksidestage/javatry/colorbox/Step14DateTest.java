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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Date with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step14DateTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is date in color-boxes formatted as slash-separated (e.g. 2019/04/24)? <br>
     * (カラーボックスに入っている日付をスラッシュ区切り (e.g. 2019/04/24) のフォーマットしたら？)
     */
    public void test_formatDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> dates = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(c -> c instanceof LocalDate)
                .map(c -> (LocalDate)c)
                .map(x -> x.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .collect(Collectors.toList());

        dates.forEach(x->log(x));
    }
    /**
     * What string of toString() is converted to LocalDate from slash-separated date string (e.g. 2019/04/24) in Set in yellow color-box? <br>
     * (yellowのカラーボックスに入っているSetの中のスラッシュ区切り (e.g. 2019/04/24) の日付文字列をLocalDateに変換してtoString()したら？)
     */
    public void test_parseDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(c -> c instanceof Set<?>)
                .map(c -> (Set<?>) c)
                .flatMap(c -> c.stream())
                .filter(a -> a instanceof String)
                .map(a -> ((String) a).toString())
                .forEach(dates -> {
                    try {
                        log("Dates: " + LocalDate.parse(dates,DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                    }
                    catch(DateTimeParseException ignored){
                    }
                });
    }

    /**
     * What is total of month numbers of date in color-boxes? <br>
     * (カラーボックスに入っている日付の月を全て足したら？)
     */
    public void test_sumMonth() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> contents = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .collect(Collectors.toList());
        int monthsum = 0;

        monthsum += contents.stream().filter(c -> c instanceof LocalDate)
                .map(c -> (LocalDate)c)
                .mapToInt(c -> (int)c.getMonthValue())
                .sum();
        monthsum += contents.stream().filter(c -> c instanceof LocalDateTime)
                .map(c -> (LocalDateTime)c)
                .mapToInt(c -> (int)c.getMonthValue())
                .sum();
        log(monthsum);
    }

    /**
     * What day of week is second-found date in color-boxes added to three days? <br>
     * (カラーボックスに入っている二番目に見つかる日付に3日進めると何曜日？)
     */
    public void test_plusDays_weekOfDay() {
        {
            List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
            List<Object> contents = colorBoxList.stream()
                    .flatMap(colorBox -> colorBox.getSpaceList().stream())
                    .map(boxSpace -> boxSpace.getContent())
                    .collect(Collectors.toList());

            contents.stream().filter(c -> c instanceof LocalDate || c instanceof LocalDateTime)
                    .map(d -> (d instanceof LocalDate)?((LocalDate)d):((LocalDate)((LocalDateTime) d).toLocalDate()))
                    .forEach(d -> log(d.plusDays(3).getDayOfWeek()));
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * How many days (number of day) are between two dates in yellow color-boxes?   <br>
     * (yellowのカラーボックスに入っている二つの日付は何日離れている？)
     */
    public void test_diffDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> yellowContent = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .collect(Collectors.toList());
//
        List<Long> epochs = yellowContent.stream().filter(c -> c instanceof LocalDate || c instanceof LocalDateTime)
                .map(d -> (d instanceof LocalDate)?((LocalDate)d):((LocalDate)((LocalDateTime) d).toLocalDate()))
                .map(d -> d.toEpochDay()).sorted()
                .collect(Collectors.toList());

        for (int i=0; i<epochs.size()-1;i++) {
            log(epochs.get(i+1)-epochs.get(i));
        }

    }

    /**
     * Find LocalDate in yellow color-box,
     * and add same color-box's LocalDateTime's seconds as number of months to it,
     * and add red color-box's Long number as days to it(previous result),
     * and subtract the first decimal place of BigDecimal that has three(3) as integer in list in color-boxes from it(previous result),
     * What date is the result?<br>
     * (yellowのカラーボックスに入っているLocalDateに、同じカラーボックスに入っているLocalDateTimeの秒数を月数として足して、
     * redのカラーボックスに入っているLong型を日数として足して、カラーボックスに入っているリストの中のBigDecimalの整数値が3の小数点第一位の数を日数として引いた日付は？)
     */
    public void test_birthdate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> contents = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .collect(Collectors.toList());

        LocalDate old = (LocalDate) contents.stream().filter(c -> c instanceof LocalDate).collect(Collectors.toList()).get(0);
        log(old);

        int addedmonth = (int) contents.stream().filter(c -> c instanceof LocalDateTime).map(d -> (LocalDateTime)d).mapToInt(d -> (int)d.getSecond()).sum();
        old = old.plusMonths(addedmonth);
        log(addedmonth,old);

        int value = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("red"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(c -> c instanceof Long)
                .mapToInt(c -> (int) ((Long) c).intValue())
                .sum();
        old = old.plusDays(value);
        log(value,old);

        int bdValue = contents.stream().filter(c -> c instanceof List)
                .map(c -> (List<?>)c).flatMap(l -> l.stream())
                .filter(l -> l instanceof BigDecimal)
                .map(l-> (BigDecimal)l)
                .filter(c -> c.intValue() == 3)
                .mapToInt(c -> ((c.subtract(new BigDecimal(3))).multiply(new BigDecimal(10))).intValue())
                .sum();

        old = old.plusDays(-bdValue);
        log(bdValue,old);


    }

    /**
     * What second is LocalTime in color-boxes? <br>
     * (カラーボックスに入っているLocalTimeの秒は？)
     */
    public void test_beReader() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> {
                    if(boxSpace instanceof DoorBoxSpace){
                    ((DoorBoxSpace)boxSpace).openTheDoor();
                    }
                    return boxSpace.getContent();
                })
                .filter(c -> c instanceof LocalTime)
                .map(c -> (LocalTime)c)
                .forEach(c -> log(c.getSecond()));
    }
}
