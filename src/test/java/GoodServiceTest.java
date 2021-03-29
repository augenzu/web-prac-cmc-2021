import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import backend.Appliances;
import backend.entity.AppType;
import backend.entity.Good;
import backend.service.AppTypeService;
import backend.service.GoodService;

@SpringBootTest(classes = Appliances.class)
public class GoodServiceTest {
    @Autowired
    private GoodService goodService;

    @Autowired
    private AppTypeService appTypeService;

    @Test
    @Transactional
    @Rollback
    public void findByNameContainingTest() {
        AppType microwaveAppType = appTypeService.findByName("microwave").get();
        Good good = new Good(microwaveAppType, "No sUCh NAme, just mEAnIngleSs TEXT",
                42.42, null, null, 42, null);
        Good savedGood = goodService.save(good);

        List<Good> foundGoods = goodService.findByNameContaining("Just mean");
        assertEquals(1, foundGoods.size());
        assertEquals(savedGood, foundGoods.toArray()[0]);

        List<Good> notFoundGoods = goodService.findByNameContaining("no_goods_found");
        assertTrue(notFoundGoods.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void findByCompanyContainingTest() {
        AppType microwaveAppType = appTypeService.findByName("microwave").get();
        Good good = new Good(microwaveAppType, "some name",
                42.42, "Dream Team Company", null, 42, null);
        Good savedGood = goodService.save(good);

        List<Good> foundGoods = goodService.findByCompanyContaining("Dream team");
        assertEquals(1, foundGoods.size());
        assertEquals(savedGood, foundGoods.toArray()[0]);

        List<Good> notFoundGoods = goodService.findByCompanyContaining("no_goods_found");
        assertTrue(notFoundGoods.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void findByAssemblyPlaceContainingTest() {
        AppType microwaveAppType = appTypeService.findByName("microwave").get();
        Good good = new Good(microwaveAppType, "some name",
                42.42, null, "Hotel California", 42, null);
        Good savedGood = goodService.save(good);

        List<Good> foundGoods = goodService.findByAssemblyPlaceContaining("california");
        assertEquals(1, foundGoods.size());
        assertEquals(savedGood, foundGoods.toArray()[0]);

        List<Good> notFoundGoods = goodService.findByAssemblyPlaceContaining("no_goods_found");
        assertTrue(notFoundGoods.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void findByDescriptionContainingTest() {
        AppType microwaveAppType = appTypeService.findByName("microwave").get();
        Good good = new Good(microwaveAppType, "some name",
                42.42, null, null, 42, "Best description ever");
        Good savedGood = goodService.save(good);

        List<Good> foundGoods = goodService.findByDescriptionContaining("best");
        assertEquals(1, foundGoods.size());
        assertEquals(savedGood, foundGoods.toArray()[0]);

        List<Good> notFoundGoods = goodService.findByDescriptionContaining("no_goods_found");
        assertTrue(notFoundGoods.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void findByPriceBetweenTest() {
        AppType microwaveAppType = appTypeService.findByName("microwave").get();
        Good good = new Good(microwaveAppType, "some name",
                42.42, null, null, 42, null);
        Good savedGood = goodService.save(good);

        List<Good> foundGoods = goodService.findByPriceBetween(42.0, 42.42);
        assertEquals(1, foundGoods.size());
        assertEquals(savedGood, foundGoods.toArray()[0]);

        List<Good> notFoundGoods = goodService.findByPriceBetween(0.0, 0.0);
        assertTrue(notFoundGoods.isEmpty());
    }
}
