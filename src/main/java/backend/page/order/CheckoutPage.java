package backend.page.order;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import backend.page.prototype.TemplatePage;

public class CheckoutPage extends TemplatePage {
    @FindBy(id = "order-user-id")
    private WebElement userElement;

    private Select userSelector = new Select(userElement);

    @FindBy(id = "order-delivery-address")
    private WebElement orderDeliveryAddressField;

    @FindBy(id = "order-deliver-on")
    private WebElement orderDeliverOnField;

    @FindBy(id = "save-order-button")
    private WebElement saveOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver, "Checkout");
    }

    public OrderInfoPage addOrder(String deliveryAddress, LocalDate deliverOn, String userName) {
        orderDeliveryAddressField.clear();
        orderDeliverOnField.clear();

        orderDeliveryAddressField.sendKeys(deliveryAddress);
        orderDeliverOnField.sendKeys(deliverOn.toString());
        userSelector.selectByVisibleText(userName);;

        saveOrderButton.click();
        return new OrderInfoPage(driver);
    }
}
