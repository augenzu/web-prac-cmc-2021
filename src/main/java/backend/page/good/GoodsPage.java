package backend.page.good;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class GoodsPage extends TemplatePage {
    @FindBy(id = "add-good-button")
    private WebElement addGoodButton;

    @FindBy(css = "td a")
    private List<WebElement> linksToGoodPages;

    public GoodsPage(WebDriver driver) {
        super(driver, "Goods");
    }

    public GoodEditPage goToAddNewGoodPage() {
        addGoodButton.click();
        return new GoodEditPage(driver);
    }

    public GoodInfoPage goToFirstGoodInListPage() {
        linksToGoodPages.get(0).click();
        return new GoodInfoPage(driver);
    }

    public GoodInfoPage goToNthGoodInListPage(int index) {
        linksToGoodPages.get(index).click();
        return new GoodInfoPage(driver);
    }
}
