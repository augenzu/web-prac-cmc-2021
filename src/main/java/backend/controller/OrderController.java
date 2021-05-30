package backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.entity.Good;
import backend.entity.Order;
import backend.entity.OrderGood;
import backend.entity.User;
import backend.service.GoodService;
import backend.service.OrderService;
import backend.service.StatusService;
import backend.service.UserService;

@Controller
public class OrderController {
    @Autowired
    private GoodService goodService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    private HashMap<Good, Integer> basket = new HashMap<>();

    private LocalDateTime trimToMinutes(LocalDateTime value) {
        LocalDateTime trimmed = LocalDateTime.of(
                value.getYear(), 
                value.getMonth(),
                value.getDayOfMonth(),
                value.getHour(),
                value.getMinute());
        return trimmed;
    }
    
    @GetMapping("/orders")
    public String getOrders(Model model) {
        List<Order> orders = orderService.findAllByOrderByOrderedAtDesc();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/order-add")
    public String addNewOrder(Model model) {
        basket.clear();
        return "order/order-add";
    }

    @GetMapping("/order-show-basket")
    public String showBasket(@RequestParam(name = "good-id") Long goodId,
            @RequestParam(name = "item-number") Integer itemNumber,
            Model model) {
        Optional<Good> foundGood = goodService.findById(goodId);
        if (!foundGood.isPresent()) {
            model.addAttribute("error", new ErrorMsg("There is no good with id=" + goodId));
            return "error/invalid-action";
        }
        Good good = foundGood.get();

        if (basket.containsKey(good)) {
            basket.put(good, basket.get(good) + itemNumber);
        } else {
            basket.put(good, itemNumber);
        }
        
        model.addAttribute("items", basket.keySet());
        model.addAttribute("basket", basket);
        return "order/order-show-basket";
    }

    @GetMapping("/item-add")
    public String addNewItemToBasket(Model model) {
        List<Good> goods = goodService.findAll();
        model.addAttribute("goods", goods);
        return "order/item-add";
    }

    @GetMapping("/order-checkout")
    public String selectUserAndDeliveryTerms(Model model) {
        if (basket.isEmpty()) {
            model.addAttribute("error", new ErrorMsg("You should select at least one item"));
            return "error/invalid-action";
        }
        model.addAttribute("basket", basket);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "order/order-checkout";
    }

    @PostMapping("/order-save")
    public String orderSave(@RequestParam(name = "order-user-id") Long userId,
            @RequestParam(name = "order-delivery-address") String deliveryAddress,
            @RequestParam(name = "order-deliver-on", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
            LocalDate deliverOn,
            Model model) {
        LocalDateTime now = trimToMinutes(LocalDateTime.now());

        Optional<User> foundUser = userService.findById(userId);
        if (!foundUser.isPresent()) {
            model.addAttribute("error", new ErrorMsg("There is no user with id="
                    + userId)
                    + ". Therefore, cannot add an order for her/him");
            return "error/invalid-action";
        }
        User user = foundUser.get();

        Order order = new Order(now, statusService.processing(),
                deliveryAddress, deliverOn, user);

        Iterator<Entry<Good, Integer>> it = basket.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Good, Integer> pair = it.next();
            Good good = (Good) pair.getKey();
            Integer number = (Integer) pair.getValue();
            OrderGood item = new OrderGood(order, good, number);
            order.getGoodItems().add(item);
        }
                
        Order newOrder = orderService.save(order);
        if (newOrder.getId() != null) {
            String redirect = "redirect:/order-info?order-id=" + newOrder.getId();
            return redirect;
        } else {
            model.addAttribute("error", new ErrorMsg("Cannot save order info"));
            return "error/invalid-action";
        }
    }

    @PostMapping("/order-update-status")
    public String orderUpdateStatus(@RequestParam(name = "order-id") Long id,
            Model model) {
        Optional<Order> foundOrder = orderService.findById(id);
        if (!foundOrder.isPresent()) {
            model.addAttribute("error", new ErrorMsg("There is no order with id=" + id));
            return "error/invalid-action";
        }
        else {
            Order order = foundOrder.get();
            if (order.getStatus().equals(statusService.processing())) {
                order.setStatus(statusService.complete());
            } else if (order.getStatus().equals(statusService.complete())) {
                order.setStatus(statusService.delivered());
            } else {
                model.addAttribute("error", new ErrorMsg("Order is already delivered"));
                return "error/invalid-action";
            }
            Optional<Order> updatedOrder = orderService.update(order);
            if (updatedOrder.isPresent()) {
                String redirect = "redirect:/order-info?order-id=" + updatedOrder.get().getId();
                return redirect;
            } else {
                model.addAttribute("error", new ErrorMsg("Cannot update order status"));
                return "error/invalid-action";
            }
        }
    }

    @GetMapping("/order-info")
    public String getOrderInfo(@RequestParam(name = "order-id") Long id,
            Model model) {
        Optional<Order> foundOrder= orderService.findById(id);
        if (foundOrder.isPresent()) {
            Order order = foundOrder.get();
            model.addAttribute("order", order);
            model.addAttribute("items", order.getGoodItems());
            return "order/order-info";
        } else {
            model.addAttribute("error", new ErrorMsg("There is no order with id=" + id));
            return "error/invalid-action";
        }
    }
}
