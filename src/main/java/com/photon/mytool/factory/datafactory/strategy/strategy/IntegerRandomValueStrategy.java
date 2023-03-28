package com.photon.mytool.factory.datafactory.strategy.strategy;

import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategy;

import java.util.Random;

public class IntegerRandomValueStrategy implements RandomValueStrategy<Integer> {
    @Override
    public Integer generate() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }
}