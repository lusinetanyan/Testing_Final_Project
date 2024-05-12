package sections;

import locators.FilterSectionLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FilterSection {

    private final WebDriver driver;

    private static final By dateFromCalendar = By.xpath(FilterSectionLocators.dateFromCalendarXPath);
    private static final By dateFromInput = By.id(FilterSectionLocators.dateFromInputID);
    private static final By dateToInput = By.id(FilterSectionLocators.dateToInputID);
    private static final By dateToCalendar = By.xpath(FilterSectionLocators.dateToCalendarXPath);
    private static final By calendarFrom = By.id(FilterSectionLocators.calendarFromID);
    private static final By calendarTo = By.id(FilterSectionLocators.calendarToID);

    public FilterSection(WebDriver driver) {
        this.driver = driver;
    }


    public void typeFilterReleaseDateFrom(Date dateFrom) {
        driver.findElement(dateFromInput).sendKeys(dateFrom.toString());
    }

    public void typeFilterReleaseDateTo(Date dateTo) {
        driver.findElement(dateToInput).sendKeys(dateTo.toString());
    }

    public void applyReleaseDateFilterFromCalendar(Calendar from, Calendar to) {
        // as the testing specification is for the case when the user chooses the date from the calendar
        // and does not type it specifically, the logic is implemented only for the May when choosing the date from
        // and for the November when choosing the date to as they are the initial months when opening,
        // in order not to dive in the date picking according to other month or year

        // pick date from
        driver.findElement(dateFromCalendar).click();

        WebElement calendarFromElement = driver.findElement(calendarFrom);

        String dateFrom = String.format("%04d/%01d/%01d", from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH));

        String dateFromXPathExpression = "//td//a[@data-value='" + dateFrom + "']";

        WebElement dateFromCell = calendarFromElement.findElement(By.xpath(dateFromXPathExpression));
        dateFromCell.click();

        // pick date to
        driver.findElement(dateToCalendar).click();

        WebElement calendarToElement = driver.findElement(calendarTo);
        String dateTo = String.format("%04d/%01d/%01d", to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH));

        String dateToXPathExpression = "//td//a[@data-value='" + dateTo + "']";

        WebElement dateToCell = calendarToElement.findElement(By.xpath(dateToXPathExpression));
        dateToCell.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void applyReleaseDateFilterByTyping(Calendar from, Calendar to) {
        WebElement dateFromField = driver.findElement(dateFromInput);
        WebElement dateToField = driver.findElement(dateToInput);

        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");

        String dateFrom = format.format(from.getTime());
        String dateTo = format.format(to.getTime());

        dateFromField.sendKeys(dateFrom);
        dateToField.sendKeys(dateTo);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
