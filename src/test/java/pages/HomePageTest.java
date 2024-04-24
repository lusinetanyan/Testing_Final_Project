package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomePageTest extends BaseTest {

    @Test
    void navigateToLoginPage() {
       assertEquals(homePage.navigateToLoginPage().getClass(), LoginPage.class);
    }

    @Test
    void searchMovie() {
        assertEquals(homePage.searchMovie("harry potter").getClass(), SearchResultsPage.class);
    }
}