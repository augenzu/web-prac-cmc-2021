package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.Order;
import backend.entity.User;
import backend.service.UserService;

@SpringBootTest(classes = Appliances.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @Rollback
    public void updateTest() {
        String oldName = "Trillian";
        String newName = "Tricia Mcillan";

        User trillian = new User(oldName, "Earth", "tricia@mcmillan.com", null);
        User savedUser = userService.save(trillian);
        savedUser.setName(newName);
        Optional<User> updatedUser = userService.update(savedUser);
        assertTrue(updatedUser.isPresent());
        trillian.setName(newName);
        assertEquals(trillian.getName(), updatedUser.get().getName());

        userService.delete(trillian);
        Optional<User> notFoundUser = userService.update(trillian);
        assertFalse(notFoundUser.isPresent());
    }

    @Test
    @Transactional
    public void findUserOrdersByIdTest() {
        Long idOfUserWhoHasOrders = 2L;
        User userWhoHasOrders = userService.findById(idOfUserWhoHasOrders).get();
        List<Order> orders = userWhoHasOrders.getOrders();
        List<Order> foundOrders = userService.findUserOrdersById(idOfUserWhoHasOrders);
        assertIterableEquals(orders, foundOrders);
    }
}
