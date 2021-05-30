package backend.page.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import backend.entity.Good;
import backend.page.prototype.TemplatePage;

public class ItemAddPage extends TemplatePage {
    @FindBy(id = "good-id")
    private WebElement itemElement;

    private Select itemSelector = new Select(itemElement);

    @FindBy(id = "item-number")
    private WebElement itemNumberField;

    @FindBy(id = "add-item-button")
    private WebElement addItemButton;

    public ItemAddPage(WebDriver driver) {
        super(driver, "Add Item");
    }

    public ShowBasketPage addItem(Good item, Integer itemNumber) {
        itemNumberField.clear();

        itemNumberField.sendKeys(itemNumber.toString());
        itemSelector.selectByVisibleText(item.getName());

        addItemButton.click();
        return new ShowBasketPage(driver);
    }
}
