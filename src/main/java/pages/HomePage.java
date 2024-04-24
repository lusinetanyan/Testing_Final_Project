package pages;

import locators.HomePageLocators;
import locators.SearchResultsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By searchField = By.id(HomePageLocators.searchFieldID);
    private final By loginButton = By.xpath(HomePageLocators.loginXPath);
    private final By searchButton = By.xpath(HomePageLocators.searchButtonXPath);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateToLoginPage() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public SearchResultsPage searchMovie(String movieKeyword) {
        driver.findElement(searchField).sendKeys(movieKeyword);
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }
}
