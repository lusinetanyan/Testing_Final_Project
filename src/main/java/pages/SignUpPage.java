package pages;

import locators.SignUpPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sections.ErrorSection;

import java.util.List;

public class SignUpPage extends BasePage {

    private static final By usernameField = By.id(SignUpPageLocators.usernameID);
    private static final By passwordField = By.id(SignUpPageLocators.passwordID);
    private static final By passwordConfirmField = By.id(SignUpPageLocators.passwordConfirmID);
    private static final By emailField = By.id(SignUpPageLocators.emailID);
    private static final By signUpButton = By.id(SignUpPageLocators.signUpButtonID);
    private static final By error = By.cssSelector(SignUpPageLocators.errorClass);

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void typeUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void typePasswordConfirm(String passwordConfirm) {
        driver.findElement(passwordConfirmField).sendKeys(passwordConfirm);
    }

    public void typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void signUp(String username, String password, String passwordConfirm, String email) {
        this.typeUsername(username);
        this.typePassword(password);
        this.typePasswordConfirm(passwordConfirm);
        this.typeEmail(email);
        driver.findElement(signUpButton).click();
    }

    public List<String> getErrorMessages() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement errorElement = driver.findElement(error);
        ErrorSection errorSection = new ErrorSection(errorElement);
        return errorSection.getErrorMessages();
    }
}
