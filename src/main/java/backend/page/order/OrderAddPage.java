package backend.page.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class OrderAddPage extends TemplatePage {
    @FindBy(id = "start-selecting-items-button")
    private WebElement startSelectingItemsButton;

    public OrderAddPage(WebDriver driver) {
        super(driver, "Add New Order");
    }

    public ItemAddPage goToAddItemPage() {
        startSelectingItemsButton.click();
        return new ItemAddPage(driver);
    }
}
