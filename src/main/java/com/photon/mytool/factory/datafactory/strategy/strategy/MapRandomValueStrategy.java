package com.photon.mytool.factory.datafactory.strategy.strategy;

import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategy;
import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategyFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapRandomValueStrategy implements RandomValueStrategy<Map<?, ?>> {
    @Override
    public Map<?, ?> generate() {
        // 生成一个随机长度的 Map，并填充随机数据
        int size = new Random().nextInt(10);
        Map<Object, Object> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(RandomValueStrategyFactory.generateRandomValue(Object.class), RandomValueStrategyFactory.generateRandomValue(Object.class));
        }
        return map;
    }
}