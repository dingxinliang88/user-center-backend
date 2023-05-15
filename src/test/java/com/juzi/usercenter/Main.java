package com.juzi.usercenter;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author codejuzi
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            System.out.println(RandomStringUtils.randomAlphabetic(10));
        }
    }
}
