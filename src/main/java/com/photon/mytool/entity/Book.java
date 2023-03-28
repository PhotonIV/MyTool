package com.photon.mytool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lzx
 * @description 书实体
 * @date 2023/03/19/ 21:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;

    private Integer price;

    private Integer num;
}
