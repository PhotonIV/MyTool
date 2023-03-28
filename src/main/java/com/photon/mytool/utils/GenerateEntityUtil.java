package com.photon.mytool.utils;

import com.photon.mytool.factory.datafactory.strategy.RandomValueStrategyFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @description
 * @date 2023/03/19/ 21:07
 */
public class GenerateEntityUtil {

    /**
     * 生成批量随机数据方法
     *
     * @param clazz
     * @param num
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> generateMultipleEntity(Class<T> clazz, Integer num) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> dataList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            dataList.add(generateEntity(clazz));
        }
        return dataList;
    }

    /**
     * 生成随机数据方法
     *
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T generateEntity(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取当前类无参构造器
        Constructor<T> constructor = clazz.getConstructor();
        //使用构造器创建对象
        T instance = constructor.newInstance();
        //获取当前类所有方法
        Method[] methods = clazz.getDeclaredMethods();
        //遍历所有方法
        for (Method method : methods) {
            //判断方法名是否是set方法
            int indexOfSet = method.getName().indexOf("set");
            if (indexOfSet == 0) {
                int i = 0;
                //是就获取参数列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                //创建参数数组，用于传入invoke()方法
                Object[] parameterArray = new Object[parameterTypes.length];
                //遍历参数类型列表
                for (Class<?> parameterType : parameterTypes) {
                    //使用随机生成数据工厂生成相应类型的参数，并赋值给参数数组
                    parameterArray[i] = RandomValueStrategyFactory.generateRandomValue(parameterType);
                    i++;

                }
                //执行set方法
                method.invoke(instance, parameterArray);
            }
        }
        //返回对象
        return instance;

    }

}
