package system.prototype;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}
