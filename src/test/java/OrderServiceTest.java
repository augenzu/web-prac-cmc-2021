import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.Good;
import backend.entity.Order;
import backend.entity.Status;
import backend.entity.User;
import backend.service.OrderService;
import backend.service.StatusService;
import backend.service.UserService;

@SpringBootTest(classes = Appliances.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @Test
    @Transactional
    @Rollback
    public void updateTest() {
        Status oldStatus = statusService.findByName("processing").get();
        Status newStatus = statusService.findByName("complete").get();
        LocalDateTime orderedAt = LocalDateTime.now();
        User user = new User("userName", "userAddress", "userEmail", null);
        User savedUser = userService.save(user);

        Order order = new Order(orderedAt, oldStatus, "someDeliveryAddress", null, savedUser);
        Order savedOrder = orderService.save(order);
        savedOrder.setStatus(newStatus);
        Optional<Order> updatedOrder = orderService.update(savedOrder);
        assertTrue(updatedOrder.isPresent());
        order.setStatus(newStatus);
        assertEquals(order.getStatus(), updatedOrder.get().getStatus());

        orderService.delete(order);
        Optional<Order> notFoundOrder = orderService.update(order);
        assertFalse(notFoundOrder.isPresent());;
    }

    @Test
    public void findByIdTest() {
        Long existingId = 1L;
        Optional<Order> foundOrder = orderService.findById(existingId);
        assertTrue(foundOrder.isPresent());

        Long nonExistentId = -42L;
        Optional<Order> notFoundOrder = orderService.findById(nonExistentId);
        assertFalse(notFoundOrder.isPresent());
    }

    @Test
    @Transactional
    @Rollback
    public void findByOrderedAtBetweenOrderByOrderedAtDescTest() {
        User user = new User("userName", "userAddress", "userEmail", null);
        User savedUser = userService.save(user);
        Status processingStatus = statusService.findByName("processing").get();
        LocalDateTime veryDistantDateTime = LocalDateTime.now().plusYears(1000);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(veryDistantDateTime.plusYears(1),
                processingStatus, "someDeliveryAddress", null, savedUser));
        orders.add(new Order(veryDistantDateTime,
                processingStatus, "someDeliveryAddress", null, savedUser));
        List<Order> savedOrders = orderService.saveAll(orders);

        LocalDateTime beg = veryDistantDateTime.minusYears(10);
        LocalDateTime end = veryDistantDateTime.plusYears(10);
        List<Order> foundOrders = orderService.findByOrderedAtBetweenOrderByOrderedAtDesc(beg, end);

        assertIterableEquals(savedOrders, foundOrders);
    }

    @Test
    public void findAllByOrderByOrderedAtDescTest() {
        LocalDateTime beg = LocalDateTime.now().minusYears(1000);
        LocalDateTime end = LocalDateTime.now().plusYears(1000);
        List<Order> orders = orderService.findByOrderedAtBetweenOrderByOrderedAtDesc(beg, end);

        List<Order> foundOrders = orderService.findAllByOrderByOrderedAtDesc();

        assertIterableEquals(orders, foundOrders);
    }

    @Test
    @Transactional
    public void findOrderGoodsByIdTest() {
        Long idOfOrderContainingGooods = 2L;
        Order orderContainingGooods = orderService.findById(idOfOrderContainingGooods).get();
        List<Good> goods = orderContainingGooods.getGoods();
        List<Good> foundGoods = orderService.findOrderGoodsById(idOfOrderContainingGooods);
        assertIterableEquals(goods, foundGoods);
    }
}
