import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.Status;
import backend.entity.Order;
import backend.service.StatusService;

@SpringBootTest(classes = Appliances.class)
public class StatusServiceTest {
    @Autowired
    private StatusService statusService;

    @Test
    @Transactional
    @Rollback
    public void updateTest() {
        String oldName = "Lost";
        String newName = "off-track";

        Status lost = new Status(oldName);
        Status savedStatus = statusService.save(lost);
        savedStatus.setName(newName);
        Optional<Status> updatedStatus = statusService.update(savedStatus);
        assertTrue(updatedStatus.isPresent());
        lost.setName(newName);
        assertEquals(lost.getName(), updatedStatus.get().getName());

        statusService.delete(lost);
        Optional<Status> notFoundStatus = statusService.update(lost);
        assertFalse(notFoundStatus.isPresent());;
    }

    @Test
    public void findByNameTest() {
        String existingStatusName = "complete";
        Optional<Status> existingStatus = statusService.findByName(existingStatusName);
        assertTrue(existingStatus.isPresent());

        String nonExistentStatusName = "no_such_status";
        Optional<Status> nonExistentStatus = statusService.findByName(nonExistentStatusName);
        assertFalse(nonExistentStatus.isPresent());
    }

    @Test
    public void findAllByOrderByNameTest() {
        List<String> allTheStatusNames = new ArrayList<>();
        allTheStatusNames.add("complete");
        allTheStatusNames.add("delivered");
        allTheStatusNames.add("processing");

        List<Status> foundStatuss = statusService.findAllByOrderByName();
        List<String> foundStatusNames = new ArrayList<>();
        foundStatuss.forEach(it -> foundStatusNames.add(it.getName()));

        assertIterableEquals(allTheStatusNames, foundStatusNames);
    }

    @Test
    @Transactional
    public void findOrdersByStatusNameTest() {
        String existingStatusName = "complete";
        Status existingStatus = statusService.findByName(existingStatusName).get();
        List<Order> orders = existingStatus.getOrders();
        List<Order> foundOrders = statusService.findOrdersByStatusName(existingStatusName);
        assertIterableEquals(orders, foundOrders);

        String nonExistentStatusName = "no_such_order";
        List<Order> notFoundOrders = statusService.findOrdersByStatusName(nonExistentStatusName);
        assertTrue(notFoundOrders.isEmpty());
    }
}
