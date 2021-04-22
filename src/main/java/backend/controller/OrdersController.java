package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backend.entity.Order;
import backend.service.OrderService;

@Controller
public class OrdersController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping("orders")
    public String getOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }
}
