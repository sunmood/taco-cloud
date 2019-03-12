package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sunmood on 2019/3/6.
 * 原料类
 * JPA需要无参构造器
 * 使用lombok自动生成get、set方法和构造器方法，
 * 使用注解@RequiredArgsConstructor， 会生成一个包含常量和标识了NotNull的变量的构造方法。生成的构造方法是私有的private。
 * 使用注解@NoArgsConstructor，生成一个无参数的构造方法，(force = true)，
 * 然后就会为没有初始化的final字段设置默认值 0 / false / null, 这样编译器就不会报错。
 * 对于具有约束的字段（例如@NotNull字段），不会生成检查或分配，因此请注意，正确初始化这些字段之前，这些约束无效
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity//声明该类为jpa实体类
public class Ingredient {
    @Id//声明为id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
