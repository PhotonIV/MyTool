package com.photon.mytool.factory.datafactory.normal;

import com.github.javafaker.Faker;

import java.util.Random;

/**
 * @author lzx
 * @description 生成随机数工厂
 * @date 2023/03/19/ 22:07
 */
public class RandomValueFactory {

    private final Faker faker = new Faker();
    private final Random random = new Random();

    public Object createRandomValue(Class<?> type) {
        if (type == String.class) {
            return faker.lorem().sentence();
        }


        return null;
    }


}
