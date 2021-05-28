package backend.page.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class UserInfoPage extends TemplatePage {
    @FindBy(id = "user-info")
    private WebElement userInfo;

    @FindBy(id = "edit-user-button")
    private WebElement editUserButton;

    @FindBy(id = "delete-user-button")
    private WebElement deleteUserButton;

    @FindBy(id = "make-order-button")
    private WebElement makeOrderButton;

    public UserInfoPage(WebDriver driver) {
        super(driver, "User Info");
    }

    public String getDisplayedUserInfo() {
        return userInfo.getText();
    }

    public UserEditPage goToEditUserPage() {
        editUserButton.click();
        return new UserEditPage(driver);
    }

    public UsersPage deleteUser() {
        deleteUserButton.click();
        return new UsersPage(driver);
    }
}
