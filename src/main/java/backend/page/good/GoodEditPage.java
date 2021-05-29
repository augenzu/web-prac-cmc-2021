package backend.page.good;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import backend.entity.Good;
import backend.page.prototype.TemplatePage;

public class GoodEditPage extends TemplatePage {
    @FindBy(id = "good-name")
    private WebElement goodNameField;

    @FindBy(id = "good-price")
    private WebElement goodPriceField;

    @FindBy(id = "good-company")
    private WebElement goodCompanyField;

    @FindBy(id = "good-assembly-place")
    private WebElement goodAssemblyPlaceField;

    @FindBy(id = "good-quantity")
    private WebElement goodQuantityField;

    @FindBy(id = "good-description")
    private WebElement goodDescriptionField;

    @FindBy(id = "good-type")
    private WebElement goodTypeElement;

    private Select goodTypeSelect = new Select(goodTypeElement);

    @FindBy(id = "save-good-button")
    private WebElement saveGoodButton;

    public GoodEditPage(WebDriver driver) {
        super(driver, "Edit Good Info");
    }

    public GoodInfoPage addOrUpdateGood(Good good) {
        goodNameField.clear();
        goodPriceField.clear();
        goodCompanyField.clear();
        goodAssemblyPlaceField.clear();
        goodQuantityField.clear();
        goodDescriptionField.clear();
        
        goodNameField.sendKeys(good.getName());
        goodPriceField.sendKeys(good.getPrice().toString());
        goodCompanyField.sendKeys(good.getCompany());
        goodAssemblyPlaceField.sendKeys(good.getAssemblyPlace());
        goodQuantityField.sendKeys(good.getQuantity().toString());
        goodDescriptionField.sendKeys(good.getDescription());
        goodTypeSelect.selectByVisibleText(good.getAppType().getName());;

        saveGoodButton.click();
        return new GoodInfoPage(driver);
    }
}
