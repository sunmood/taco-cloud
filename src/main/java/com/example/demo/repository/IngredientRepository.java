package com.example.demo.repository;

import com.example.demo.domain.Ingredient;

/**
 * Created by sunmood on 2019/3/7.
 */
public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
