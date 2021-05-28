package backend.page.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class UsersPage extends TemplatePage {
    @FindBy(id = "add-user-button")
    private WebElement addUserButton;

    @FindBy(css = "td a")
    private List<WebElement> linksToUserPages;

    public UsersPage(WebDriver driver) {
        super(driver, "Users");
    }

    public UserEditPage goToAddNewUserPage() {
        addUserButton.click();
        return new UserEditPage(driver);
    }

    public UserInfoPage goToFirstUserInListPage() {
        linksToUserPages.get(0).click();
        return new UserInfoPage(driver);
    }

    public UserInfoPage goToNthUserInListPage(int index) {
        linksToUserPages.get(index).click();
        return new UserInfoPage(driver);
    }
}
