package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * Created by sunmood on 2019/3/6.
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("current")
    public String orderForm(Model model){
//        model.addAttribute("order", new Order());
        return "orderForm";
    }

    /**
     * 处理订单
     * @param order
     * @param errors
     * @param sessionStatus
     * @return
     */
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){
        if (errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted:" + order);
        orderRepository.save(order);
        sessionStatus.setComplete();//重置session
        return "redirect:/";
    }
}
