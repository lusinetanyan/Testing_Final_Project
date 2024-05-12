package sections;

import enums.SortOption;
import locators.SortSectionLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SortSection {

    private final WebDriver driver;

    private final By sort = By.xpath(SortSectionLocators.sortXPath);
    private final By sortResultsBy = By.xpath(SortSectionLocators.sortResultsByXPath);
    private final By listbox = By.cssSelector(SortSectionLocators.listboxCssSelector);

    public SortSection(WebDriver driver) {
        this.driver = driver;
    }

    public void applySorting(SortOption sortOption) {
        WebElement sortElement = driver.findElement(sort);
        sortElement.click();

        WebElement selectElement = driver.findElement(sortResultsBy);
        selectElement.click();

        List<WebElement> listOfOptions = driver.findElements(listbox);

        WebElement option = listOfOptions.get(sortOption.getValue());
        option.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
