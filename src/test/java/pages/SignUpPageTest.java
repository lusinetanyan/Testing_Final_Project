package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class SignUpPageTest {

    private static SignUpPage signUpPage;
    private static WebDriver driver;

    @BeforeTest
    public void navigateToHomepage() {
        driver = new ChromeDriver();
        driver.get("https://www.themoviedb.org");
        HomePage homePage = new HomePage(driver);
        signUpPage = homePage.navigateToSignUpPage();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    // test for password input
    @Test
    @Parameters({"username", "password", "passwordConfirm", "email"})
    public void test_password(String username, String password, String passwordConfirm, String email) {
        signUpPage.signUp(username, password, passwordConfirm, email);
        List<String> errorMessages = signUpPage.getErrorMessages();
        if (password.isEmpty() && passwordConfirm.isEmpty()) { // password field is empty, password confirm is empty
            assertEquals(errorMessages.size(), 3);
            assertTrue(errorMessages.contains("Password can't be blank"));
            assertTrue(errorMessages.contains("Password confirm can't be blank"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (password.isEmpty()) { // password field is empty, password confirm is not empty
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Password can't be blank"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (passwordConfirm.isEmpty() && password.length() < 4) { // password length is invalid, password confirm is empty
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Password confirm can't be blank"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (passwordConfirm.isEmpty()) { // password length is valid, password confirm is empty
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Password confirm can't be blank"));
        } else if (password.equals(passwordConfirm) && password.length() < 4) { // password length is invalid, password confirm is matching the password
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (!password.equals(passwordConfirm) && password.length() < 4) { // password length is invalid, password confirm is not matching the password
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Password and password confirmation do not match"));
            assertTrue(errorMessages.contains("Password needs to be at least 4 characters long"));
        } else if (!password.equals(passwordConfirm)) { // password length is valid, password confirm is not matching the password
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Password and password confirmation do not match"));
        }
    }


    // test for username input
    @Test
    @Parameters({"username", "password", "passwordConfirm", "email"})
    public void test_username(String username, String password, String passwordConfirm, String email) {
        signUpPage.signUp(username, password, passwordConfirm, email);
        List<String> errorMessages = signUpPage.getErrorMessages();
        if (username.isEmpty()) { // username is empty
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Username can't be blank"));
        } else { // username is taken
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Username has already been taken"));
        }
    }


    // test for email input
    @Test
    @Parameters({"username", "password", "passwordConfirm", "email"})
    public void test_email(String username, String password, String passwordConfirm, String email) {
        signUpPage.signUp(username, password, passwordConfirm, email);
        List<String> errorMessages = signUpPage.getErrorMessages();
        if (email.isEmpty()) { // email is empty
            assertEquals(errorMessages.size(), 2);
            assertTrue(errorMessages.contains("Email can't be blank"));
            assertTrue(errorMessages.contains("Email does not appear to be valid"));
        } else if (!email.contains("@")) { // email is not valid
            assertEquals(errorMessages.size(), 1);
            assertTrue(errorMessages.contains("Email does not appear to be valid"));
        }
    }
}