import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.AppType;
import backend.entity.Good;
import backend.service.AppTypeService;

@SpringBootTest(classes = Appliances.class)
public class AppTypeServiceTest {
    @Autowired
    private AppTypeService appTypeService;

    @Test
    public void findByNameTest() {
        String existingAppTypeName = "microwave";
        Optional<AppType> existingAppType = appTypeService.findByName(existingAppTypeName);
        assertTrue(existingAppType.isPresent());

        String nonExistentAppTypeName = "no_such_type";
        Optional<AppType> nonExistentAppType = appTypeService.findByName(nonExistentAppTypeName);
        assertFalse(nonExistentAppType.isPresent());
    }

    @Test
    public void findAllByOrderByNameTest() {
        List<String> allTheAppTypeNames = new ArrayList<>();
        allTheAppTypeNames.add("coffee_maker");
        allTheAppTypeNames.add("cooktop");
        allTheAppTypeNames.add("fridge");
        allTheAppTypeNames.add("microwave");
        allTheAppTypeNames.add("tv");
        allTheAppTypeNames.add("vacuum_cleaner");
        allTheAppTypeNames.add("washer");

        List<AppType> foundAppTypes = appTypeService.findAllByOrderByName();
        List<String> foundAppTypeNames = new ArrayList<>();
        foundAppTypes.forEach(it -> foundAppTypeNames.add(it.getName()));

        assertIterableEquals(allTheAppTypeNames, foundAppTypeNames);
    }

    @Test
    @Transactional
    public void findGoodsByAppTypeNameTest() {
        String existingAppTypeName = "microwave";
        AppType existingAppType = appTypeService.findByName(existingAppTypeName).get();
        List<Good> goods = existingAppType.getGoods();
        List<Good> foundGoods = appTypeService.findGoodsByAppTypeName(existingAppTypeName);
        assertIterableEquals(goods, foundGoods);

        String nonExistentAppTypeName = "no_such_good";
        List<Good> notFoundGoods = appTypeService.findGoodsByAppTypeName(nonExistentAppTypeName);
        assertTrue(notFoundGoods.isEmpty());
    }
}
