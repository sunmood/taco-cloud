package com.example.demo.controller;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Order;
import com.example.demo.domain.Taco;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
@SessionAttributes("order")//将order对象放入session中，多个跨页面请求都可以访问该对象
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    /**
     * 构造注入bean
     * @param ingredientRepository
     * @param tacoRepository
     */
    @Autowired//优先使用byType匹配，而后是byName
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    /**
     *
     * @param design
     * @param errors
     * @param order  @ModelAttribute告诉spring mvc该参数放进model，不对请求参数进行绑定
     * @return
     */
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if (errors.hasErrors()){
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        log.info("Processing design:" + design);
        order.addDesign(saved);

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
