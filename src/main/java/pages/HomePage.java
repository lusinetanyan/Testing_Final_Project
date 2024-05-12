package pages;

import locators.HomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    private final By searchField = By.id(HomePageLocators.searchFieldID);
    private final By searchButton = By.xpath(HomePageLocators.searchButtonXPath);
    private final By headerMovies = By.cssSelector(HomePageLocators.moviesLink);
    private final By topRatedMovies = By.cssSelector(HomePageLocators.topRatedLink);
    private final By loginButton = By.xpath(HomePageLocators.loginXPath);


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public TopRatedMoviesPage navigateToTopRatedMoviesPage() {
        WebElement movies = driver.findElement(headerMovies);
        Actions hover = new Actions(driver);
        hover.moveToElement(movies).perform();
        WebElement topRated = driver.findElement(topRatedMovies);
        topRated.click();
        return new TopRatedMoviesPage(driver);
    }

    public SignUpPage navigateToSignUpPage() {
        driver.findElement(loginButton).click();
        driver.findElement(By.cssSelector("a[href='/signup']")).click();

        return new SignUpPage(driver);
    }

    public SearchResultsPage searchMovie(String movieKeyword) {
        driver.findElement(searchField).sendKeys(movieKeyword);
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }
}
