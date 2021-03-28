import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.AppType;
import backend.entity.Good;
import backend.repository.AppTypeRepository;

@SpringBootTest(classes = Appliances.class)
public class AppTypeRepositoryTest {
    @Autowired
    private AppTypeRepository appTypeRepository;

    @Test
    public void findAllByOrderByNameTest() {
        List<String> allTheAppTypeNames = new LinkedList<>();
        allTheAppTypeNames.add("coffee_maker");
        allTheAppTypeNames.add("cooktop");
        allTheAppTypeNames.add("fridge");
        allTheAppTypeNames.add("microwave");
        allTheAppTypeNames.add("tv");
        allTheAppTypeNames.add("vacuum_cleaner");
        allTheAppTypeNames.add("washer");

        List<AppType> foundAppTypes = appTypeRepository.findAllByOrderByName();
        List<String> foundAppTypeNames = new LinkedList<>();
        foundAppTypes.forEach(it -> foundAppTypeNames.add(it.getName()));

        assertIterableEquals(allTheAppTypeNames, foundAppTypeNames);
    }

    @Test
    @Transactional
    @Rollback
    public void findGoodsByAppTypeNameTest() {
        String existingAppTypeName = "microwave";
        List<Good> foundGoods = appTypeRepository.findGoodsByAppTypeName(existingAppTypeName);
        assertEquals(foundGoods.size(), 1);
        assertEquals(foundGoods.iterator().next().getId(), 4L);

        String unexistingAppTypeName = "no_such_good";
        List<Good> notFoundGoods = appTypeRepository.findGoodsByAppTypeName(unexistingAppTypeName);
        assertTrue(notFoundGoods.isEmpty());
    }
}
