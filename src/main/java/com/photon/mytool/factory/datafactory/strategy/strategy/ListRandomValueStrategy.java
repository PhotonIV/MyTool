package com.photon.mytool.factory.datafactory.strategy.strategy;

import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategy;
import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListRandomValueStrategy implements RandomValueStrategy<List<?>> {
    @Override
    public List<?> generate() {
        // 生成一个随机长度的 List，并填充随机数据
        int size = new Random().nextInt(10);
        List<Object> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(RandomValueStrategyFactory.generateRandomValue(Object.class));
        }
        return list;
    }
}