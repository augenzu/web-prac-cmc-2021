package backend.page.user;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.assertj.FluentLeniumAssertions.*;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class UserInfoPage extends FluentPage {
    private static final String PAGE_CONTENT = ".page-content";

    @FindBy(css = PAGE_CONTENT)
    private FluentWebElement pageContent;

    public void checkPageContentPresence() {
        assertThat(pageContent).isDisplayed();
    }
}
