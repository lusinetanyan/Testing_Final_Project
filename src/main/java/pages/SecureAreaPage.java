package pages;

import locators.SecureAreaPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SecureAreaPage extends BasePage {

    private final By username = By.xpath(SecureAreaPageLocators.usernameXPath);
    private final By watchlist = By.xpath(SecureAreaPageLocators.watchlistDropdownListXPath);
    private final By watchlistMovies = By.xpath(SecureAreaPageLocators.watchlistMoviesXPath);

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    //*[@id=\"new_shortcut_bar_mn_active\"]
    //*[@id="new_shortcut_bar_mn_active"]
    public String getUsername() {
        return driver.findElement(username).getText();
    }

    public void goToWatchlist() {
        WebElement watchlistElement = driver.findElement(watchlist);
        Actions hover = new Actions(driver);
        hover.moveToElement(watchlistElement).perform();
        WebElement movies = driver.findElement(watchlistMovies);
        movies.click();
    }
}
