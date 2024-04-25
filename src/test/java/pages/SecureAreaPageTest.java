package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

class SecureAreaPageTest extends BaseTest {

    @Test
    public void testGoToWatchlist() {
        LoginPage loginPage = homePage.navigateToLoginPage();
        loginPage.setUsername("lusine_tanyan");
        loginPage.setPassword("pomhomework");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertDoesNotThrow(secureAreaPage::goToWatchlist);
    }


}