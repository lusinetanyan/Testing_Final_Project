package pages;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultsPageTest extends BaseTest {

    // the search result contains only one movie
    // checks the title to be equal to the one provided in search
    @Test
    void singleValidSearchResult() {
        String movieTitle = "Pirates of the Caribbean: On Stranger Tides";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        assertEquals(1, results.size());
        assertTrue(results.get(0).equals(movieTitle));
    }

    // the search result contains multiple movies
    // checks the titles contain the search key
    @Test
    void multipleValidSearchResults() {
        String movieTitle = "forrest gump";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        screenshot("validSearchResults");
        assertEquals(2, results.size());
        results.stream().forEach(result -> assertTrue(result.toLowerCase().contains("forrest gump")));
    }

    // the search result is empty given empty key
    @Test
    void emptySearchResults() {
        String movieTitle = "";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        assertEquals(0, results.size());
    }

    // the search result is empty given key not matching to any movie
    @Test
    void noResults() {
        String movieTitle = "dfaereagtr";
        SearchResultsPage resultsPage = homePage.searchMovie(movieTitle);
        List<String> results = resultsPage.getSearchResults();
        screenshot("noResults");
        assertEquals(0, results.size());
    }
}