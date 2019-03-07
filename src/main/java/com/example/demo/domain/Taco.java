package com.example.demo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by sunmood on 2019/3/6.
 */
@Data
public class Taco {
    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<String> ingredients;
}
