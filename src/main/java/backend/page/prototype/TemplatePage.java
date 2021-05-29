package backend.page.prototype;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import backend.page.good.GoodsPage;
import backend.page.order.OrdersPage;
import backend.page.user.UsersPage;

public class TemplatePage extends PageObject {
    private WebDriverWait wait = new WebDriverWait(driver, 10);

    protected String title = "";

    @FindBy(className = "sidebar")
    private WebElement sidebar;

    @FindBy(className = "page-content")
    private WebElement pageContent;

    @FindBy(id = "users-link")
    private WebElement usersPageLink;

    @FindBy(id = "goods-link")
    private WebElement goodsPageLink;

    @FindBy(id = "orders-link")
    private WebElement ordersPageLink;

    public TemplatePage(WebDriver driver, String title) {
        super(driver);
        this.title = title;
    }

    public void isAt() {
        wait.until(ExpectedConditions.titleIs(title));
        assertTrue(sidebar.isDisplayed());
        assertTrue(pageContent.isDisplayed());
    }

    public UsersPage goToUsersPage() {
        usersPageLink.click();
        return new UsersPage(driver);
    }

    public GoodsPage goToGoodsPage() {
        goodsPageLink.click();
        return new GoodsPage(driver);
    }

    public OrdersPage goToOrdersPage() {
        ordersPageLink.click();
        return new OrdersPage(driver);
    }
}
