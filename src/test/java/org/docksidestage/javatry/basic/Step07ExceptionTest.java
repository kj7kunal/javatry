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
package org.docksidestage.javatry.basic;

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                             Message
    //                                                                             =======
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_message_basic() {
        IllegalStateException exp = new IllegalStateException("mystic");
        String sea = exp.getMessage();
        log(sea); // your answer? => mystic
    }

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
    }

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーはメッセージとスタックトレースを代わりに)
     */
    public void test_exception_checkedException_basic() {
        try {
            log(new File(".").getCanonicalPath());
        }
        catch (IOException e) {
            // TODO [comment] also you can just use log(e), it shows message and stack-trace
            log(e.getMessage());
            e.printStackTrace();
        }

    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? => Failed to call the third help method: -1
            log(land); // your answer? => IllegalArgumentException
            log(e); // your answer? => java.lang.IllegalStateException: Failed to call the second help method: -1
        }
    }

    private void throwCauseFirstLevel() {
        int count = -1;
        try {
            throwCauseSecondLevel(count);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: " + count, e);
        }
    }

    private void throwCauseSecondLevel(int count) {
        try {
            if (count < 0) {
                throwCauseThirdLevel(count);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: " + count, e);
        }
    }

    private void throwCauseThirdLevel(int count) {
        if (count < 0) {
            Integer.valueOf("piari");
        }
    }

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment. <br>
     * テストを実行して、例外メッセージを読んで、状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            //  SupercarClient invokes orderSupercar with requirement "steering wheel is like sea"
            //  SupercarDealer invokes makeSupercar with catalog key "piari"
            //  SupercarManufacturer invokes findSteeringWheelSpecId which returns '3' as specId and invokes makeSteeringWheel with it
            //  SupercarSteeringWheelManufacturer invokes createSupercarSteeringWheelComponentDB() which returns "\\(^_^)/" as specText
            //     and also invokes makeSpecialScrew with it which throws a runtime exception
            //  SpecialScrewCannotMakeBySpecException: The kawaii face is not useful to make screw: ScrewSpec:{\(^_^)/}
            // _/_/_/_/_/_/_/_/_/_/
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            // TODO Unavailable ScrewSpec:{\(^_^)/} is little hard to understand,
            //  for example, let's say I'm a car shop,
            //      Me: I want a nice car :D
            //      Car salesman: Unavailable ScrewSpec:{\(^_^)/}
            //      Me: ????
            //  I want to be told like "we don't have a right car for you, because your requirement is "steering wheel is like sea"..." by zaya 2019/10/16
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() { // simple implementation here
        throw new IllegalStateException("something illegal");
    }
}
