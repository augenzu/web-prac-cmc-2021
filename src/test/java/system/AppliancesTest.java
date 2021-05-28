package system;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import backend.Appliances;
import backend.entity.User;
import backend.page.home.HomePage;
import backend.page.user.UserEditPage;
import backend.page.user.UserInfoPage;
import backend.page.user.UsersPage;
import system.prototype.SeleniumTest;

@SpringBootTest(classes = Appliances.class,
    webEnvironment = WebEnvironment.DEFINED_PORT)
public class AppliancesTest extends SeleniumTest{
    private static final String HOME = "http://localhost:8080/";

    private static void assertThatInfoCorrespondsToUser(String info, User user) {
        assertTrue(info.contains(user.getName().toString()));
        assertTrue(info.contains(user.getAddress().toString()));
        assertTrue(info.contains(user.getEmail().toString()));
        assertTrue(info.contains(user.getNumber().toString()));
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
}
