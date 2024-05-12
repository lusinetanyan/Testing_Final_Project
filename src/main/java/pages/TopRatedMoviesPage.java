package pages;

import enums.SortOption;
import locators.TopRatedMoviesLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sections.FilterSection;
import sections.SortSection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TopRatedMoviesPage extends BasePage {
    private static SortSection sortSection;
    private static FilterSection filterSection;

    private final By searchButton = By.xpath(TopRatedMoviesLocators.searchButtonXPath);

    public TopRatedMoviesPage(WebDriver driver) {
        super(driver);
        sortSection = new SortSection(driver);
        filterSection = new FilterSection(driver);
    }

    public void sort(SortOption sortOption) {
        sortSection.applySorting(sortOption);

        WebElement search = driver.findElement(searchButton);
        search.click();
    }

    public void filterReleaseDatesFromCalendar(Calendar from, Calendar to) {
        filterSection.applyReleaseDateFilterFromCalendar(from, to);

        WebElement search = driver.findElement(searchButton);
        search.click();
    }

    public void filterReleaseDatesByTyping(Calendar from, Calendar to) {
        filterSection.applyReleaseDateFilterByTyping(from, to);

        WebElement search = driver.findElement(searchButton);
        search.click();
    }

    public int[] getMovieRatings() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> percents = driver.findElements(By.className("user_score_chart"));
        int[] ratings = new int[percents.size()];
        for (int i = 0; i < percents.size(); i++) {
            String rating = percents.get(i).getAttribute("data-percent");
            ratings[i] = Integer.parseInt(rating);
        }
        return ratings;
    }

    public List<String> getMovieReleaseDates() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> movies = driver.findElements(By.cssSelector(".card.style_1"));
        int size = Math.min(movies.size(), 20);
        movies = movies.subList(0, size);
        List<String> releaseDates = new ArrayList<>(size);
        for (WebElement movie : movies) {
            String releaseDate = movie.findElement(By.tagName("p")).getText();
            releaseDates.add(releaseDate);
        }
        return releaseDates;
    }

}
