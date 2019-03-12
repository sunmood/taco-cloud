package com.example.demo.repository.impl;

import com.example.demo.domain.Ingredient;
import com.example.demo.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sunmood on 2019/3/7.
 */
@Repository//该注解同@Controller和@Component等注解的作用一样
public class JdbcIngredientRepository{
    private JdbcTemplate jdbc;

    /**
     * 创建该bean实例时自动注入JdbcTemplate
     * JdbcTemplate构造注入的实例变量将被用到其他方法中进行查询和插入等数据操作
     * @param jdbc
     */
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }


    public Ingredient findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient WHERE id=?",
                this::mapRowToIngredient, id);
    }


    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id, name, type) VALUES (?, ? , ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );
    }
}
