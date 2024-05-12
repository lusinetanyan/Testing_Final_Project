package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class SignUpPageTest {

    private static SignUpPage signUpPage;
    private static HomePage homePage;
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.drive", "chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @BeforeTest
    public void navigateToHomepage() {
        driver.get("https://www.themoviedb.org");
        signUpPage = homePage.navigateToSignUpPage();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    @Test
    @Parameters({"username", "password", "passwordConfirm", "email"})
    public void test_password(String username, String password, String passwordConfirm, String email) {
        signUpPage.signUp(username, password, passwordConfirm, email);
        List<String> errorMessages = signUpPage.getErrorMessages();
        if (password.isEmpty() && passwordConfirm.isEmpty()) {
            assertEquals(errorMessages.size(), 3);
            assertTrue(errorMessages.contains("Password can't be blank"));
            assertTrue(errorMessages.contains("Password confirm can't be blank"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (password.isEmpty()) {
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Password can't be blank"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (passwordConfirm.isEmpty() && password.length() < 4) {
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Password confirm can't be blank"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (passwordConfirm.isEmpty()) {
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Password confirm can't be blank"));
        } else if (password.equals(passwordConfirm) && password.length() < 4) {
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (!password.equals(passwordConfirm) && password.length() < 4) {
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Password and password confirmation do not match"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (!password.equals(passwordConfirm)) {
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Password and password confirmation do not match"));
        }
    }

    @Test
    @Parameters({"username", "password", "passwordConfirm", "email"})
    public void test_username(String username, String password, String passwordConfirm, String email) {
        signUpPage.signUp(username, password, passwordConfirm, email);
        List<String> errorMessages = signUpPage.getErrorMessages();
        if (username.isEmpty()) {
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Username can't be blank"));
        } else {
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Username has already been taken"));
        }
    }


    @Test
    @Parameters({"username", "password", "passwordConfirm", "email"})
    public void test_email(String username, String password, String passwordConfirm, String email) {
        signUpPage.signUp(username, password, passwordConfirm, email);
        List<String> errorMessages = signUpPage.getErrorMessages();
        if (email.isEmpty()) {
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Email can't be blank"));
            assertTrue(errorMessages.contains("Email does not appear to be valid"));
        } else {
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Email does not appear to be valid"));
        }
    }
}