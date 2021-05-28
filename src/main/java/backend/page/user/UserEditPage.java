package backend.page.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.entity.User;
import backend.page.prototype.TemplatePage;

public class UserEditPage extends TemplatePage {
    @FindBy(id = "user-name")
    private WebElement userNameField;

    @FindBy(id = "user-address")
    private WebElement userAddressField;

    @FindBy(id = "user-email")
    private WebElement userEmailField;

    @FindBy(id = "user-number")
    private WebElement userNumberField;

    @FindBy(id = "save-user-button")
    private WebElement saveUserButton;

    public UserEditPage(WebDriver driver) {
        super(driver, "Edit User Info");
    }

    public UserInfoPage addOrUpdateUser(User user) {
        userNameField.clear();
        userAddressField.clear();
        userEmailField.clear();
        userNumberField.clear();
        userNameField.sendKeys(user.getName());
        userAddressField.sendKeys(user.getAddress());
        userEmailField.sendKeys(user.getEmail());
        userNameField.sendKeys(user.getNumber());
        saveUserButton.click();
        return new UserInfoPage(driver);
    }
}
