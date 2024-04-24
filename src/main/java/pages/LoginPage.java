package pages;

import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.security.InvalidParameterException;

public class LoginPage extends BasePage {
    private final By usernameField = By.id(LoginPageLocators.usernameFieldID);
    private final By passwordField = By.id(LoginPageLocators.passwordFieldID);
    private final By loginButton = By.id(LoginPageLocators.loginButtonID);
    private final By error = By.className(LoginPageLocators.errorClass);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public SecureAreaPage clickLoginButton() {
        driver.findElement(loginButton).click();
        try {
            driver.findElement(error);
            throw new InvalidParameterException("Invalid login!");
        } catch (NoSuchElementException e) {
            return new SecureAreaPage(driver);
        }
    }
}
