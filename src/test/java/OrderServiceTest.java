import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.Good;
import backend.entity.Order;
import backend.service.OrderService;

@SpringBootTest(classes = Appliances.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void findByIdTest() {
        Long existingId = 1L;
        Optional<Order> foundOrder = orderService.findById(existingId);
        assertTrue(foundOrder.isPresent());

        Long nonExistentId = -42L;
        Optional<Order> notFoundOrder = orderService.findById(nonExistentId);
        assertFalse(notFoundOrder.isPresent());
    }

    // @Test
    // public void findAllByOrderByOrderedAtTest() {
    //     orderService.findAllByOrderByOrderedAt().forEach(it -> {
    //         System.out.println();
    //         System.out.println();
    //         System.out.println(it);
    //         System.out.println();
    //         System.out.println();
    //     });
    // }

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
