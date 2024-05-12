package pages;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


public class BaseTest {

    protected static WebDriver driver;
    protected static HomePage homePage;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.drive", "chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @BeforeEach
    public void navigateToHomepage() {
        driver.get("https://www.themoviedb.org");
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    protected static void screenshot(String message) {
        try {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            String projectDirectory = System.getProperty("user.dir");
            String relativePath = "/screenshots/" + message + "_" + LocalDateTime.now() + ".png";
            String filePath = projectDirectory + relativePath;
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

