package backend.page.home;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import backend.page.good.GoodsPage;
import backend.page.order.OrdersPage;
import backend.page.user.UsersPage;

@PageUrl("/")
public class HomePage extends FluentPage {
    private static final String USERS_LINK = "#users-link";
    private static final String GOODS_LINK = "#goods-link";
    private static final String ORDERS_LINK = "#orders-link";

    @FindBy(css = USERS_LINK)
    private FluentWebElement usersPageLink;

    @FindBy(css = GOODS_LINK)
    private FluentWebElement goodsPageLink;

    @FindBy(css = ORDERS_LINK)
    private FluentWebElement ordersPageLink;

    public UsersPage goToUsersPage() {
        usersPageLink.click();
        return newInstance(UsersPage.class);
    }

    public GoodsPage goToGoodsPage() {
        goodsPageLink.click();
        return newInstance(GoodsPage.class);
    }

    public OrdersPage goToOrdersPage() {
        ordersPageLink.click();
        return newInstance(OrdersPage.class);
    }
}
