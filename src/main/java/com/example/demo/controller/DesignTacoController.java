package com.example.demo.controller;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sunmood on 2019/3/6.
 */
@Slf4j//注解由lombok提供，会自动生成slf4j的日志
@Controller//标记为需要扫描的组件，spring会自动把该组件作为一个bean添加到Spring application context中
@RequestMapping("/design")//该类处理的请求都是以“/design”开头
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
          new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
          new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
          new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
          new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
          new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
          new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
          new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
          new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
          new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
          new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){
        if (errors.hasErrors()){
            return "design";
        }
        log.info("Processing design:" + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> list, Ingredient.Type type){
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : list){
            if (ingredient.getType() == type){
                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }
}
