package backend.page.home;

import org.openqa.selenium.WebDriver;

import backend.page.prototype.TemplatePage;

public class HomePage extends TemplatePage {
    public HomePage(WebDriver driver) {
        super(driver, "Home");
    }
}
