package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.Status;
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
}
