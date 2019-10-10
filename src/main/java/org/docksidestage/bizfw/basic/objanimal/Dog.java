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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.loud.Barker;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for dog(犬).
 * @author jflute
 */
public class Dog extends Animal implements FastRunner,Barker {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Cat.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Dog() {
    }

    // ===================================================================================
    //                                                                              Runner
    //                                                                              ======
    @Override
    public void run() {
        // dummy implementation
        logger.debug("...Running now");
    }
    // ===================================================================================
    //                                                                              Barker
    //                                                                              ======
    @Override
    public void barkNtimes(int n) {
        for (int i = 0; i < n; i++) {
            logger.debug(getBarkWord());
        }
    }
    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    protected String getBarkWord() {
        return "wan"; // bow? in English
    }
}
