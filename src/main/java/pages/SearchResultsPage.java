package pages;

import locators.SearchResultsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsPage extends BasePage {
    private final By searchResults = By.xpath(SearchResultsPageLocators.searchResultsXPath);
    private final By title = By.className(SearchResultsPageLocators.titlesClass);
    private final By movies = By.className(SearchResultsPageLocators.moviesClass);

    public SearchResultsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public List<String> getSearchResults() {
        WebElement bulkResult = driver.findElement(searchResults);
        if(bulkResult.getText().equals("There are no movies that matched your query."))
            return Collections.emptyList();

        List<WebElement> results = getAllElements(movies, bulkResult);
        List<String> titles = new ArrayList<>(results.size());

        for (WebElement movie: results) {
            titles.add(movie.findElement(title).getText());
        }
        return titles;
    }
}
