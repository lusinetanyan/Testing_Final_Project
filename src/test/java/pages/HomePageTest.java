package pages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomePageTest extends BaseTest {

    @Test
    void navigateToTopRatedMoviesPage() {
        assertEquals(homePage.navigateToTopRatedMoviesPage().getClass(), TopRatedMoviesPage.class);
    }

    @Test
    void navigateToSignUpPage() {
        assertEquals(homePage.navigateToSignUpPage().getClass(), SignUpPage.class);
    }

    @Test
    void searchMovie() {
        assertEquals(homePage.searchMovie("harry potter").getClass(), SearchResultsPage.class);
    }
}