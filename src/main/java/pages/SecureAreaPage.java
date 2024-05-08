package pages;

import locators.SecureAreaPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public class SecureAreaPage extends BasePage {

    private final By username = By.xpath(SecureAreaPageLocators.usernameXPath);
    private final By watchlist = By.xpath(SecureAreaPageLocators.watchlistDropdownListXPath);
    private final By watchlistMovies = By.xpath(SecureAreaPageLocators.watchlistMoviesXPath);

    public SecureAreaPage(RemoteWebDriver driver) {
        super(driver);
    }

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

    public List<String> getWatchlistMovies() {
        List<WebElement> titles = driver.findElements(By.className("title"));
        List<String> movies = new ArrayList<>();
        titles.forEach(title -> movies.add(title.findElement(By.tagName("a")).getText()));
        return movies;
    }
}
