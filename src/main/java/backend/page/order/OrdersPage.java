package backend.page.order;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.prototype.TemplatePage;

public class OrdersPage extends TemplatePage {
    @FindBy(id = "add-order-button")
    private WebElement addOrderButton;

    @FindBy(css = "td a")
    private List<WebElement> linksToOrderPages;

    public OrdersPage(WebDriver driver) {
        super(driver, "Orders");
    }

    public OrderAddPage goToAddNewOrderPage() {
        addOrderButton.click();
        return new OrderAddPage(driver);
    }

    public OrderInfoPage goToFirstOrderInListPage() {
        linksToOrderPages.get(0).click();
        return new OrderInfoPage(driver);
    }

    public OrderInfoPage goToNthOrderInListPage(int index) {
        linksToOrderPages.get(index).click();
        return new OrderInfoPage(driver);
    }
}
