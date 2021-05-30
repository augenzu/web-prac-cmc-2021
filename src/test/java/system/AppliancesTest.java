package system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import backend.Appliances;
import backend.entity.AppType;
import backend.entity.Good;
import backend.entity.User;
import backend.page.error.InvalidActionPage;
import backend.page.good.GoodEditPage;
import backend.page.good.GoodInfoPage;
import backend.page.good.GoodsFoundPage;
import backend.page.good.GoodsPage;
import backend.page.home.HomePage;
import backend.page.order.CheckoutPage;
import backend.page.order.ItemAddPage;
import backend.page.order.OrderAddPage;
import backend.page.order.OrderInfoPage;
import backend.page.order.OrdersPage;
import backend.page.order.ShowBasketPage;
import backend.page.user.UserEditPage;
import backend.page.user.UserInfoPage;
import backend.page.user.UsersPage;
import backend.service.AppTypeService;
import backend.service.GoodService;
import system.prototype.SeleniumTest;

@SpringBootTest(classes = Appliances.class,
    webEnvironment = WebEnvironment.DEFINED_PORT)
public class AppliancesTest extends SeleniumTest{
    private static final String HOME = "http://localhost:8080/";

    @Autowired
    private AppTypeService appTypeService;

    @Autowired
    private GoodService goodService;

    private static void assertThatInfoCorrespondsToUser(String info, User user) {
        assertTrue(info.contains(user.getName().toString()));
        assertTrue(info.contains(user.getAddress().toString()));
        assertTrue(info.contains(user.getEmail().toString()));
        assertTrue(info.contains(user.getNumber().toString()));
    }

    private static void assertThatInfoCorrespondsToGood(String info, Good good) {
        assertTrue(info.contains(good.getName()));
        assertTrue(info.contains(good.getPrice().toString()));
        assertTrue(info.contains(good.getCompany()));
        assertTrue(info.contains(good.getAssemblyPlace()));
        assertTrue(info.contains(good.getQuantity().toString()));
        assertTrue(info.contains(good.getDescription()));
        assertTrue(info.contains(good.getAppType().getName()));
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

    @Test
    public void searchGoodTest() {
        driver.get(HOME);
        HomePage homePage = new HomePage(driver);
        homePage.isAt();
        GoodsPage goodsPage = homePage.goToGoodsPage();
        goodsPage.isAt();
        GoodsFoundPage goodsFoundPage = goodsPage.searchForGoodsByQuery("coffeemaker");
        goodsFoundPage.isAt();
        assertEquals(1, goodsFoundPage.getGoodsFoundNumber());

        goodsPage = goodsFoundPage.goToGoodsPage();
        goodsPage.isAt();
        goodsFoundPage = goodsPage.searchForGoodsByQuery("steel");
        goodsFoundPage.isAt();
        assertEquals(4, goodsFoundPage.getGoodsFoundNumber());

        goodsPage = goodsFoundPage.goToGoodsPage();
        goodsPage.isAt();
        goodsFoundPage = goodsPage.searchForGoodsByQuery("no-such-good");
        goodsFoundPage.isAt();
        assertEquals(0, goodsFoundPage.getGoodsFoundNumber());

        goodsPage = goodsFoundPage.goToGoodsPage();
        goodsPage.isAt();
        goodsFoundPage = goodsPage.searchForGoodsByQuery("germany");
        goodsFoundPage.isAt();
        assertEquals(2, goodsFoundPage.getGoodsFoundNumber());

        goodsPage = goodsFoundPage.goToGoodsPage();
        goodsPage.isAt();
        goodsFoundPage = goodsPage.searchForGoodsByQuery("huawei");
        goodsFoundPage.isAt();
        assertEquals(0, goodsFoundPage.getGoodsFoundNumber());
    }

    @Test
    public void orderActionsTest() {
        // go to user's page and get the number of his/her orders
        // and keep his/her name
        driver.get(HOME);
        HomePage homePage = new HomePage(driver);
        homePage.isAt();
        UsersPage usersPage = homePage.goToUsersPage();
        usersPage.isAt();
        UserInfoPage userInfoPage = usersPage.goToFirstUserInListPage();
        userInfoPage.isAt();
        Integer oldNumberOfOrders = userInfoPage.getOrdersListSize();
        String userName = userInfoPage.getUserName();

        // add new order
        // add one item twice (in the amounts 1 and 2)
        // and check that order contains only one item (not two)
        // and it's amount is amount1 + amount2
        driver.get(HOME);
        homePage = new HomePage(driver);
        homePage.isAt();
        OrdersPage ordersPage = homePage.goToOrdersPage();
        ordersPage.isAt();
        OrderAddPage orderAddPage = ordersPage.goToAddNewOrderPage();
        orderAddPage.isAt();
        ItemAddPage itemAddPage = orderAddPage.goToAddItemPage();
        itemAddPage.isAt();
        Good coffemakerItem = goodService.findByDescriptionContaining("coffeemaker").get(0);
        Good tvItem = goodService.findByDescriptionContaining("tv").get(0);
        ShowBasketPage showBasketPage = itemAddPage.addItem(coffemakerItem, 3);
        showBasketPage.isAt();
        itemAddPage = showBasketPage.goToAddItemPage();
        itemAddPage.isAt();
        showBasketPage = itemAddPage.addItem(tvItem, 1);
        showBasketPage.isAt();
        itemAddPage = showBasketPage.goToAddItemPage();
        itemAddPage.isAt();
        showBasketPage = itemAddPage.addItem(coffemakerItem, 7);
        showBasketPage.isAt();
        assertEquals(2, showBasketPage.getItemsListSize());
        CheckoutPage checkoutPage = showBasketPage.goToCheckoutPage();
        checkoutPage.isAt();
        String deliveryAddress = "CMC MSU";
        LocalDate deliverOn = LocalDate.now();
        OrderInfoPage orderInfoPage = checkoutPage.addOrder(deliveryAddress, deliverOn, userName);
        orderInfoPage.isAt();
        assertEquals(2, orderInfoPage.getItemsListSize());

        // change order status until it is "delivered"
        // and check that after it status is unchangeable
        // all the new orders are "processing"
        orderInfoPage = orderInfoPage.updateOrderStatusIfProcessingOrComplete();
        orderInfoPage.isAt();  // now status is "complete"
        orderInfoPage = orderInfoPage.updateOrderStatusIfProcessingOrComplete();
        orderInfoPage.isAt();  // now status is "delivered"
        InvalidActionPage invalidActionPage = orderInfoPage.tyrToUpdateOrderStatusIfDelivered();
        invalidActionPage.isAt();
        assertEquals("Order is already delivered", invalidActionPage.getDisplayedErrorMessage());

        // go to user's page and check
        // that number of his/her orders += 1
        driver.get(HOME);
        homePage = new HomePage(driver);
        homePage.isAt();
        usersPage = homePage.goToUsersPage();
        usersPage.isAt();
        userInfoPage = usersPage.goToFirstUserInListPage();
        userInfoPage.isAt();
        Integer newNumberOfOrders = userInfoPage.getOrdersListSize();
        assertEquals(oldNumberOfOrders + 1, newNumberOfOrders);
    }
}
