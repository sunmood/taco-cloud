package com.example.demo.repository;

import com.example.demo.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sunmood on 2019/3/7.
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
