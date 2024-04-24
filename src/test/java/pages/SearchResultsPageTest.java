package pages;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsPageTest extends BaseTest {

    @Test
    void singleValidSearchResult() {
        String movieTitle = "Pirates of the Caribbean: On Stranger Tides";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        assertEquals(1, results.size());
        assertTrue(results.get(0).equals(movieTitle));
    }

    @Test
    void multipleValidSearchResults() {
        String movieTitle = "forrest gump";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        screenshot("validSearchResults");
        assertEquals(2, results.size());
        results.stream().forEach(result -> assertTrue(result.toLowerCase().contains("forrest gump")));
    }

    @Test
    void emptySearchResults() {
        String movieTitle = "";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        assertEquals(0, results.size());
    }

    @Test
    void noResults() {
        String movieTitle = "dfaereagtr";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        screenshot("noResults");
        assertEquals(0, results.size());
    }
}