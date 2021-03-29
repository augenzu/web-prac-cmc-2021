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
    public void saveUserTest() {
        User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);
        User savedUser = userService.save(arthurDent);
        assertEquals(arthurDent, savedUser);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteUserTest() {
        User trillian = new User("Trillian", "Earth", "tricia@mcmillan.com", null);
        User savedUser = userService.save(trillian);
        userService.delete(savedUser);
        assertFalse(userService.existsById(savedUser.getId()));
    }

    @Test
    public void findByIdTest() {
        Long existingId = 1L;
        Optional<User> foundUser = userService.findById(existingId);
        assertTrue(foundUser.isPresent());

        Long nonExistentId = -42L;
        Optional<User> notFoundUser = userService.findById(nonExistentId);
        assertFalse(notFoundUser.isPresent());
    }

    @Test
    @Transactional
    @Rollback
    public void findByNameContainingTest() {
        User zaphodBeeblebrox = new User("Zaphod Beeblebrox", "Betelgeuse V", "zaphod@beeblebrox.com", null);
        userService.save(zaphodBeeblebrox);
        List<User> foundUsers = userService.findByNameContaining("zaphod");
        assertTrue(foundUsers.contains(zaphodBeeblebrox));
    }

    @Test
    @Transactional
    @Rollback
    public void findByEmailTest() {
        User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);
        userService.save(arthurDent);
        List<User> foundUsers = userService.findByEmail(arthurDent.getEmail());
        assertTrue(foundUsers.contains(arthurDent));
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
