package com.photon.mytool.factory.datafactory.strategy.strategy;

import com.github.javafaker.Faker;
import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategy;

import java.util.UUID;

public class StringRandomValueStrategy implements RandomValueStrategy<String> {

    //使用faker来生成随机string
    private static final Faker FAKER = new Faker();

    @Override
    public String generate() {

        String sentence = FAKER.name().name();
        return sentence;
    }
}