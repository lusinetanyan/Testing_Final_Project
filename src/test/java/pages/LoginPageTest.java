package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest extends BaseTest{

    @Test
    public void validLogin() {
        LoginPage loginPage = homePage.navigateToLoginPage();
        loginPage.setUsername("lusine_tanyan");
        loginPage.setPassword("pomhomework");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        screenshot("ValidLogin");
        assertEquals("lusine_tanyan", secureAreaPage.getUsername());
    }

    @Test
    public void invalidLogin() {
        LoginPage loginPage = homePage.navigateToLoginPage();
        loginPage.setUsername("erfedrf");
        loginPage.setPassword("refrtre");
        InvalidParameterException exception = assertThrows(InvalidParameterException.class, loginPage::clickLoginButton);
        screenshot("InvalidLogin");
        assertEquals("Invalid login!", exception.getMessage());
    }
}