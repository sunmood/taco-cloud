package com.example.demo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by sunmood on 2019/3/6.
 * 原料类
 * 使用lombok自动生成get、set方法和构造器方法，
 * 使用注解@RequiredArgsConstructor，构造器方法会把final属性作为构造方法的参数注入
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
