package backend.page.error;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class InvalidActionPage extends TemplatePage {
    @FindBy(id = "error-message")
    private WebElement errorMessage;

    public InvalidActionPage(WebDriver driver) {
        super(driver, "Invalid Action");
    }

    public String getDisplayedErrorMessage() {
        return errorMessage.getText();
    }
}
