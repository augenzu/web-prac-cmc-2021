package backend.page.good;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class GoodsPage extends TemplatePage {
    @FindBy(id = "good-query")
    private WebElement goodSearchBox;

    @FindBy(css = "td a")
    private List<WebElement> linksToGoodPages;

    @FindBy(id = "add-good-button")
    private WebElement addGoodButton;

    public GoodsPage(WebDriver driver) {
        super(driver, "Goods");
    }

    public GoodsFoundPage searchForGoodsByQuery(String query) {
        goodSearchBox.clear();
        goodSearchBox.sendKeys(query);
        goodSearchBox.sendKeys(Keys.ENTER);
        return new GoodsFoundPage(driver);
    }

    public GoodEditPage goToAddNewGoodPage() {
        addGoodButton.click();
        return new GoodEditPage(driver);
    }

    public GoodInfoPage goToFirstGoodInListPage() {
        linksToGoodPages.get(0).click();
        return new GoodInfoPage(driver);
    }
}
