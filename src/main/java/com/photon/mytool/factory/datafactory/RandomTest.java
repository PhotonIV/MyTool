package com.photon.mytool.factory.datafactory;

import com.photon.mytool.entity.Book;
import com.photon.mytool.utils.GenerateEntityUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lzx
 * @description 随机数生成测试类
 * @date 2023/03/23/ 9:33
 */
public class RandomTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Book book = GenerateEntityUtil.generateEntity(Book.class);
        System.out.println(book);

    }
}
