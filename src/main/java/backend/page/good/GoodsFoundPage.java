package backend.page.good;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class GoodsFoundPage extends TemplatePage {
    @FindBy(css = "tr")
    private List<WebElement> goodsFoundList;

    public GoodsFoundPage(WebDriver driver) {
        super(driver, "Goods Found");
    }

    public Integer getGoodsFoundNumber() {
        return goodsFoundList.size() - 1;  // without table header
    }
}
