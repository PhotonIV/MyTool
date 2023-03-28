package com.photon.mytool.factory.datafactory.strategy;

import com.photon.mytool.factory.datafactory.strategy.strategy.IntegerRandomValueStrategy;
import com.photon.mytool.factory.datafactory.strategy.strategy.ListRandomValueStrategy;
import com.photon.mytool.factory.datafactory.strategy.strategy.MapRandomValueStrategy;
import com.photon.mytool.factory.datafactory.strategy.strategy.StringRandomValueStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @description 生成随机数工厂
 * @date 2023/03/19/ 22:07
 */
public class RandomValueStrategyFactory {
    private static final Map<Class<?>, RandomValueStrategy<?>> strategyMap = new HashMap<>();

    static {
        strategyMap.put(Integer.class, new IntegerRandomValueStrategy());
        strategyMap.put(int.class, new IntegerRandomValueStrategy());
        strategyMap.put(String.class, new StringRandomValueStrategy());
        strategyMap.put(List.class, new ListRandomValueStrategy());
        strategyMap.put(Map.class, new MapRandomValueStrategy());
        // 添加其他类型的策略
    }

    public static <T> T generateRandomValue(Class<T> clazz) {
        RandomValueStrategy<T> strategy = getStrategy(clazz);
        return strategy.generate();
    }

    private static <T> RandomValueStrategy<T> getStrategy(Class<T> clazz) {
        RandomValueStrategy<T> strategy = (RandomValueStrategy<T>) strategyMap.get(clazz);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for " + clazz);
        }
        return strategy;
    }


}
