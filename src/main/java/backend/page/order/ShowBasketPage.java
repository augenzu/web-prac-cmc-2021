package backend.page.order;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class ShowBasketPage extends TemplatePage{
    @FindBy(css = "tr")
    private List<WebElement> goodItemsList;

    @FindBy(id = "add-item-button")
    private WebElement addItemButton;

    @FindBy(id = "save-order-button")
    private WebElement saveOrderButton;

    public ShowBasketPage(WebDriver driver) {
        super(driver, "Show Basket");
    }

    public Integer getItemsListSize() {
        return goodItemsList.size() - 1;  // without table header
    }

    public ItemAddPage goToAddItemPage() {
        addItemButton.click();
        return new ItemAddPage(driver);
    }

    public CheckoutPage goToCheckoutPage() {
        saveOrderButton.click();
        return new CheckoutPage(driver);
    }
}
