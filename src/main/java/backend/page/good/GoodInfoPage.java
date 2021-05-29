package backend.page.good;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class GoodInfoPage extends TemplatePage {
    @FindBy(id = "good-info")
    private WebElement goodInfo;

    @FindBy(id = "edit-good-button")
    private WebElement editGoodButton;

    @FindBy(id = "delete-good-button")
    private WebElement deleteGoodButton;

    public GoodInfoPage(WebDriver driver) {
        super(driver, "Good Info");
    }

    public String getDisplayedGoodInfo() {
        return goodInfo.getText();
    }

    public GoodEditPage goToEditGoodPage() {
        editGoodButton.click();
        return new GoodEditPage(driver);
    }

    public GoodsPage deleteGood() {
        deleteGoodButton.click();
        return new GoodsPage(driver);
    }
}
