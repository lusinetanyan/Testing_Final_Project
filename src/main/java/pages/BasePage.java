package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected final RemoteWebDriver driver;
    private WebDriverWait wait;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    protected List<WebElement> getAllElements(By locator, WebElement parentElement){
        return wait.until(driver -> parentElement.findElements(locator));
    }
}
