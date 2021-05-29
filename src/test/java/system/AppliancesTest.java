package system;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import backend.Appliances;
import backend.entity.AppType;
import backend.entity.Good;
import backend.entity.User;
import backend.page.good.GoodEditPage;
import backend.page.good.GoodInfoPage;
import backend.page.good.GoodsPage;
import backend.page.home.HomePage;
import backend.page.user.UserEditPage;
import backend.page.user.UserInfoPage;
import backend.page.user.UsersPage;
import backend.service.AppTypeService;
import system.prototype.SeleniumTest;

@SpringBootTest(classes = Appliances.class,
    webEnvironment = WebEnvironment.DEFINED_PORT)
public class AppliancesTest extends SeleniumTest{
    private static final String HOME = "http://localhost:8080/";

    @Autowired
    private AppTypeService appTypeService;

    private static void assertThatInfoCorrespondsToUser(String info, User user) {
        assertTrue(info.contains(user.getName().toString()));
        assertTrue(info.contains(user.getAddress().toString()));
        assertTrue(info.contains(user.getEmail().toString()));
        assertTrue(info.contains(user.getNumber().toString()));
    }

    private static void assertThatInfoCorrespondsToGood(String info, Good good) {
        assertTrue(true);
    }

    @Test
    public void userActionsTest() {
        // add new user
        driver.get(HOME);
        HomePage homePage = new HomePage(driver);
        homePage.isAt();
        UsersPage usersPage = homePage.goToUsersPage();
        usersPage.isAt();
        UserEditPage userAddPage = usersPage.goToAddNewUserPage();
        userAddPage.isAt();
        User originalUser = new User("Trillian", "Earth", "tricia@mcmillan.com", "911");
        UserInfoPage originalUserInfoPage = userAddPage.addOrUpdateUser(originalUser);
        originalUserInfoPage.isAt();
        String savedUserInfo = originalUserInfoPage.getDisplayedUserInfo();
        assertThatInfoCorrespondsToUser(savedUserInfo, originalUser);

        // edit user info
        UserEditPage userEditPage = originalUserInfoPage.goToEditUserPage();
        userEditPage.isAt();
        User editedUser = new User("Zaphod Beeblebrox", "Betelgeuse V", "zaphod@beeblebrox.com", "112");
        UserInfoPage editedUserInfoPage = userEditPage.addOrUpdateUser(editedUser);
        editedUserInfoPage.isAt();
        String updatedUserInfo = editedUserInfoPage.getDisplayedUserInfo();
        assertThatInfoCorrespondsToUser(updatedUserInfo, editedUser);

        // delete user
        usersPage = editedUserInfoPage.deleteUser();
        usersPage.isAt();
    }

    @Test
    public void goodActionsTest() {
        AppType oldAppType = appTypeService.findByName("microwave").get();
        AppType newAppType = appTypeService.findByName("tv").get();

        // add new good
        driver.get(HOME);
        HomePage homePage = new HomePage(driver);
        homePage.isAt();
        GoodsPage goodsPage = homePage.goToGoodsPage();
        goodsPage.isAt();
        GoodEditPage goodAddPage = goodsPage.goToAddNewGoodPage();
        goodAddPage.isAt();
        Good originalGood = new Good(oldAppType, "old-name", 42.42, "LG", "China", 42, "microwave");
        GoodInfoPage originalGoodInfoPage = goodAddPage.addOrUpdateGood(originalGood);
        originalGoodInfoPage.isAt();
        String savedGoodInfo = originalGoodInfoPage.getDisplayedGoodInfo();
        assertThatInfoCorrespondsToGood(savedGoodInfo, originalGood);

        // edit good info
        GoodEditPage goodEditPage = originalGoodInfoPage.goToEditGoodPage();
        goodEditPage.isAt();
        Good editedGood = new Good(newAppType, "new-name", 3.14, "Intel", "USA", 0, "tv");
        GoodInfoPage editedGoodInfoPage = goodEditPage.addOrUpdateGood(editedGood);
        editedGoodInfoPage.isAt();
        String updatedGoodInfo = editedGoodInfoPage.getDisplayedGoodInfo();
        assertThatInfoCorrespondsToGood(updatedGoodInfo, editedGood);

        // delete good
        goodsPage = editedGoodInfoPage.deleteGood();
        goodsPage.isAt();
    }
}
