import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
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
import backend.repository.UserRepository;

@SpringBootTest(classes = Appliances.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    public void saveUserTest() {
        User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);
        User savedUser = userRepository.save(arthurDent);
        assertEquals(arthurDent, savedUser);
    }

    @Test
    @Transactional
    @Rollback
    public void saveMultipleUsersTest() {
        User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);
        User fordPrefect = new User("Ford Prefect", "Betelgeuse V", "ford@prefect.com", null);
        User zaphodBeeblebrox = new User("Zaphod Beeblebrox", "Betelgeuse V", "zaphod@beeblebrox.com", null);

        List<User> users = new LinkedList<>();
        users.add(arthurDent);
        users.add(fordPrefect);
        users.add(zaphodBeeblebrox);

        List<User> savedUsers = userRepository.saveAll(users);
        assertIterableEquals(users, savedUsers);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteUserTest() {
        User trillian = new User("Trillian", "Earth", "trisia@macmillan.com", null);
        User savedUser = userRepository.save(trillian);
        userRepository.delete(savedUser);
        assertFalse(userRepository.existsById(savedUser.getId()));
    }

    @Test
    public void findByIdTest() {
        Long unexistingId = -42L;
        Optional<User> notFoundUser = userRepository.findById(unexistingId);
        assertFalse(notFoundUser.isPresent());

        Long existingId = 1L;
        Optional<User> foundUser = userRepository.findById(existingId);
        assertTrue(foundUser.isPresent());
    }

    @Test
    @Transactional
    @Rollback
    public void findByNameTest() {
        User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);
        userRepository.save(arthurDent);
        List<User> foundUsers = userRepository.findByName(arthurDent.getName());
        assertTrue(foundUsers.contains(arthurDent));
    }

    @Test
    @Transactional
    @Rollback
    public void findByEmailTest() {
        User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);
        userRepository.save(arthurDent);
        List<User> foundUsers = userRepository.findByEmail(arthurDent.getEmail());
        assertTrue(foundUsers.contains(arthurDent));
    }

    @Test
    @Transactional
    @Rollback
    public void findUserOrdersByIdTest() {
        // User arthurDent = new User("Arthur Dent", "Earth", "arthur@dent.com", null);

        // Order order1 = new Order();
        // Order order2 = new Order();
        // arthurDent.addOrder(order1);
        // arthurDent.addOrder(order2);

        // User savedUser = userRepository.save(arthurDent);

        // List<Order> foundOrders = userRepository.findUserOrdersById(savedUser.getId());
        // assertTrue(foundOrders.contains(order1)
        //         && foundOrders.contains(order2)
        //         && foundOrders.size() == 2);

        List<Order> foundOrders = userRepository.findUserOrdersById(2L);
        assertEquals(foundOrders.size(), 2);
    }
}
