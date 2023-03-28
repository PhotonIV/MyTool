package com.photon.mytool.factory.datafactory.normal.impl;

import com.photon.mytool.factory.datafactory.normal.RandomValueGenerator;

import java.util.Random;

/**
 * @author lzx
 * @description
 * @date 2023/03/23/ 9:19
 */
public class IntegerRandomValueGenerator implements RandomValueGenerator {
    @Override
    public Object generate() {
        Random random = new Random();
        int i = random.nextInt();
        return i;
    }
}
