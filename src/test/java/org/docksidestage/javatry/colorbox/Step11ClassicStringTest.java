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

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author kunal.jain
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    } //==> 5 (green)

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxlen = 0;
        String maxstring = null;
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String){
                    String str = (String) content;
                    if (maxlen < str.length()){
                        maxstring = str;
                        maxlen = str.length();
                    }
                }
            }
        }
        log(maxstring + " (" + maxlen +")");
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    // Did it only for color name.
    //    public void test_length_findMaxMinDiff() {
    //        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
    //
    //        int maxdiff=0, minlen;
    //        int colorLength  = colorBoxList.get(0).getColor().getColorName().length();
    //        minlen = colorLength;
    //
    //        for(int i=1;i<colorBoxList.size();i++) {
    //            colorLength  = colorBoxList.get(i).getColor().getColorName().length();
    //            if (colorLength < minlen) {
    //                minlen = colorLength;
    //            }
    //            if (maxdiff < colorLength-minlen) {
    //                maxdiff = colorLength-minlen;
    //            }
    //        }
    //        log(maxdiff);
    //    }
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer maxlen = null;
        Integer minlen = null;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String str = (String) content;
                    if (maxlen == null || maxlen < str.length()) {
                        maxlen = str.length();
                    }
                    if (minlen == null || minlen > str.length()) {
                        minlen = str.length();
                    }
                }
            }
        }
        log(maxlen-minlen);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // TODO kunal Java variable name should be camel case e.g.: maxlen -> maxLen by ちーかま
        // Please fix other parts
        int maxlen = 0, max2len = 0;
        String max2value = null, maxvalue = null;
        String str = null;
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content==null){
                    continue;
                }

                str = content.toString();

                // TODO kunal please reformat by ちーかま
                // NG:
                // {
                // else if
                // OK:
                // } else if
                if (maxlen < str.length()){
                    max2value = maxvalue;
                    max2len = maxlen;
                    maxvalue = str;
                    maxlen = str.length();
                }
                // TODO kunal add space e.g.: max2len<str.length() -> max2len < str.length() by ちーかま
                // Please fix other parts
                else if (max2len<str.length() && str.length()!=maxlen){
                    max2value = str;
                    max2len = str.length();
                }
            }
        }
        if (max2len==0){
            log("NO SECOND MAX");
        }
        else{
            log("Max ("+maxlen+"): "+maxvalue);
            log("Second Max ("+max2len+"): "+max2value);
        }
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sumlen = 0;
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String){
                    String str = (String) content;
                    sumlen+=str.length();
                }
            }
        }
        log("Total sum of lengths: " + sumlen);

    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxlen = 0;
        String maxcolor = null;
        for(ColorBox colorBox: colorBoxList) {
            BoxColor boxColor = colorBox.getColor();
            String color = boxColor.getColorName();
            if (maxlen < color.length()){
                maxcolor = color;
                maxlen = color.length();
            }
        }
        log(maxcolor + " (" + maxlen +")");
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // Good kunal　It works even if there are multiple boxes with strings containing Water by ちーかま
        boolean found = false;
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().startsWith("Water")){
                    found = true;
                    break;
                }
            }
            if(found) {
                BoxColor boxColor = colorBox.getColor();
                log(boxColor.getColorName() + ": starts with Water...");
                found = false;
            }
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        boolean found = false;
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().endsWith("front")){
                    found = true;
                    break;
                }
            }
            if(found) {
                BoxColor boxColor = colorBox.getColor();
                log(boxColor.getColorName() + ": ends with front...");
                found = false;
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().endsWith("front")){
                    log("front starts at :" + (1+content.toString().indexOf("front")));
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().contains("ど")){
                    log("Last ど at : " + (1+content.toString().lastIndexOf("ど")));
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().endsWith("front")){
                    log(content + " ends with front and starts with :" + content.toString().substring(0, 1));
                }
            }
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().startsWith("Water")){
                    log(content + " starts with Water and ends with :" + content.toString().substring(content.toString().length() - 1));
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String && content.toString().contains("o")){
                    String replaced = content.toString().replace("o","");
                    log(content + " => " + replaced + " ==> characters =  " + replaced.length());
                }
            }
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof java.io.File){
                    String pathstring = ((File) content).getPath().replace("/","\\");
                    log(content + " => (Windows) => " + pathstring);
                }
            }
        }
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        int result = 0;
        String str = null;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof YourPrivateRoom.DevilBox){
                    YourPrivateRoom.DevilBox devilBox = (YourPrivateRoom.DevilBox) content;
                    devilBox.wakeUp();
                    devilBox.allowMe();
                    devilBox.open();
                    try {
                        str = devilBox.getText();
                    }
                    catch (YourPrivateRoom.DevilBoxTextNotFoundException e){
                        continue;
                    }
                    result+= str.length();
                }
            }
        }
        log(result);
    }


    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof Map){
                    String result = "map:{ ";
                    Map<Object, Object> map = (Map)content;
                    for (Object key : map.keySet()){
                        result+= key.toString()+" = "+(map.get(key)).toString()+" ; ";
                    }
                    log(result+"}");
                }
            }
        }
    }


    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for(ColorBox colorBox: colorBoxList) {
            for(BoxSpace boxSpace: colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof Map){
                    String result = "";
                    log(mapString((Map)content,result));
                }
            }
        }
    }

    // Well done! kunal
    public String mapString(Map map, String str){
        str+="map:{ ";
        for (Object key : map.keySet()){
            Object value = map.get(key);
            if (value instanceof Map){
                str+= key.toString()+" = "+mapString((Map)value,str);

            }
            else{
                str+= key.toString()+" = "+value.toString()+" ; ";
            }
        }
        str+="} ; ";
        return str;
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        BoxSpace upperWhiteSpace = new YourPrivateRoom().getColorBoxList().get(4).getSpaceList().get(0);
//        log(upperWhiteSpace);

        YourPrivateRoom.SecretBox secretBox = (YourPrivateRoom.SecretBox) upperWhiteSpace.getContent();
        String mapping = secretBox.getText();
        log(mapping);    //Got string from upperbox

        Map<String, String> secretMap = new LinkedHashMap<>();
        for(String map : mapping.split("[:|{|}|;]",-1)){
            if(map.contains("=")) {
                String[] mapkv = map.split("[\\ |=]+", -1);
                secretMap.put(mapkv[1], mapkv[2]);
            }
        }
        log(secretMap);
    }

    // TODO: FINISH THIS
    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
//    public void test_parseMap_nested() {
//        BoxSpace middleWhiteSpace = new YourPrivateRoom().getColorBoxList().get(4).getSpaceList().get(1);
//        BoxSpace lowerWhiteSpace = new YourPrivateRoom().getColorBoxList().get(4).getSpaceList().get(2);
//
//        YourPrivateRoom.SecretBox msb = (YourPrivateRoom.SecretBox) middleWhiteSpace.getContent();
//        String msbmapping = msb.getText();
//        log(msbmapping);
//        log(parseStringMap(msbmapping));
//        YourPrivateRoom.SecretBox lsb = (YourPrivateRoom.SecretBox) lowerWhiteSpace.getContent();
//        String lsbmapping = lsb.getText();
//        log(lsbmapping);
//        log(parseStringMap(lsbmapping));
//
//
//    }
//
//    public String[] parseStringMap(String mapping){
//        String[] parts = mapping.split("map:\\{ ",2);
//        return(parts);
//    }



}

