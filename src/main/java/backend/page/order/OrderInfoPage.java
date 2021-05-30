package backend.page.order;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.error.InvalidActionPage;
import backend.page.prototype.TemplatePage;

public class OrderInfoPage extends TemplatePage {
    @FindBy(id = "order-info")
    private WebElement orderInfo;

    @FindBy(css = "tr")
    private List<WebElement> goodItemsList;

    @FindBy(id = "update-order-status-button")
    private WebElement updateOrderStatusButton;

    public OrderInfoPage(WebDriver driver) {
        super(driver, "Order Info");
    }

    public String getDisplayedOrderInfo() {
        return orderInfo.getText();
    }

    public Integer getItemsListSize() {
        return goodItemsList.size() - 1;  // without table header
    }

    public OrderInfoPage updateOrderStatusIfProcessingOrComplete() {
        updateOrderStatusButton.click();
        return new OrderInfoPage(driver);
    }

    public InvalidActionPage tyrToUpdateOrderStatusIfDelivered() {
        updateOrderStatusButton.click();
        return new InvalidActionPage(driver);
    }
}
