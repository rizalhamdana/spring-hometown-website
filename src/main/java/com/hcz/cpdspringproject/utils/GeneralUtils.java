package com.hcz.cpdspringproject.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * GeneralUtils
 */
public class GeneralUtils {

    private static ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext("beans.xml");

    public static ApplicationContext getContext() {
        if (APP_CONTEXT == null) {
            APP_CONTEXT = new ClassPathXmlApplicationContext("beans.xml");

        }
        return APP_CONTEXT;
    }
}