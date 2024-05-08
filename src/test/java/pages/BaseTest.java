package pages;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;


public class BaseTest {

    protected static RemoteWebDriver driver;
    protected static HomePage homePage;
    static Capabilities chromeCapabilities = DesiredCapabilities.chrome();


    @BeforeAll
    public static void setUp() throws MalformedURLException {
        System.setProperty("webdriver.chrome.drive", "chromedriver.exe");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);;
        driver.get("https://www.themoviedb.org");

        homePage = new HomePage(driver);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    protected void screenshot(String message) {
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

