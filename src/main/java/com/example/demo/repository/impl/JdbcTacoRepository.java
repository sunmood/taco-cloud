package com.example.demo.repository.impl;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Taco;
import com.example.demo.repository.TacoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by sunmood on 2019/3/7.
 */
@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (String ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private void saveIngredientToTaco(String ingredient, long tacoId) {
        jdbc.update("INSERT into Taco_Ingredients (taco, ingredient) VALUES (?, ?)",
                tacoId, ingredient
        );
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());

        String sql = "INSERT INTO Taco (NAME , createdAt) VALUES (?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1,taco.getName());
                psst.setTimestamp(2,  new Timestamp(taco.getCreatedAt().getTime()));
                return psst;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
