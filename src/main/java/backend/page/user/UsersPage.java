package backend.page.user;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("/users")
public class UsersPage extends FluentPage {
    private static final String ADD_USER_BUTTON = "#add-user-button";
    private static final String LINKS_TO_USER_PAGES = "td a";

    @FindBy(css = ADD_USER_BUTTON)
    private FluentWebElement addUserButton;

    @FindBy(css = LINKS_TO_USER_PAGES)
    private FluentList<FluentWebElement> linksToUserPages;

    public UserEditPage goToAddNewUserPage() {
        addUserButton.click();
        return newInstance(UserEditPage.class);
    }

    public UserInfoPage goToFirstUserInListPage() {
        linksToUserPages.first().click();
        return newInstance(UserInfoPage.class);
    }

    public UserInfoPage goToNthUserInListPage(int index) {
        linksToUserPages.get(index).click();
        return newInstance(UserInfoPage.class);
    }
}
