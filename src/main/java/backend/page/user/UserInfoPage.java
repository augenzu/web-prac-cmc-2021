package backend.page.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class UserInfoPage extends TemplatePage {
    @FindBy(id = "user-name")
    private WebElement userNameElement;

    @FindBy(id = "user-info")
    private WebElement userInfo;

    @FindBy(id = "edit-user-button")
    private WebElement editUserButton;

    @FindBy(id = "delete-user-button")
    private WebElement deleteUserButton;

    @FindBy(id = "make-order-button")
    private WebElement makeOrderButton;

    @FindBy(css = "tr")
    private List<WebElement> userOrdersList;

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

    public Integer getOrdersListSize() {
        return userOrdersList.size() - 1;  // without table header
    }

    public String getUserName() {
        String userNameElementTxt = userNameElement.getText();  // User <userName>
        String nameLabel = "User ";
        String userName = userNameElementTxt.substring(nameLabel.length());
        return userName;
    }
}
