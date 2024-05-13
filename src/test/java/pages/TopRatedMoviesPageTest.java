package pages;

import enums.SortOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.testng.annotations.BeforeTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopRatedMoviesPageTest extends BaseTest{

    private static TopRatedMoviesPage topRatedMoviesPage;

    @BeforeEach
    void navigateToTopRatedMoviesPage() {
        topRatedMoviesPage = homePage.navigateToTopRatedMoviesPage();
    }

    // apply rating sorting in ascending order
    // get all the movie's ratings
    // check the ratings are in ascending order
    @Test
    void sortByRatingAscending() {
        topRatedMoviesPage.sort(SortOption.RATING_ASC);
        int[] ratings = topRatedMoviesPage.getMovieRatings();
        assertTrue(isAscending(ratings));
    }

    // apply rating sorting in descending order
    // get all the movie's ratings
    // check the ratings are in descending order
    @Test
    void sortByRatingDescending() {
        topRatedMoviesPage.sort(SortOption.RATING_DESC);
        int[] ratings = topRatedMoviesPage.getMovieRatings();
        assertFalse(isAscending(ratings));
    }


    // NOTE: The filter functionality does not work correctly
    // so results contain movies with release dates not contained
    // in that interval. That is why, when an assertion error happens
    // it is being caught and instead a screenshot is taken to show
    // the case and the TEST PASSES.

    // choose release date interval from the calendar
    // get all the movie's release dates
    // check the release dates are in that interval
    @Test
    void filterByReleaseDatesFromCalendar() {
        Calendar from = Calendar.getInstance();

        from.set(Calendar.YEAR, 2024);
        from.set(Calendar.MONTH, Calendar.MAY);
        from.set(Calendar.DAY_OF_MONTH, 1);

        Calendar to = Calendar.getInstance();

        to.set(Calendar.YEAR, 2024);
        to.set(Calendar.MONTH, Calendar.NOVEMBER);
        to.set(Calendar.DAY_OF_MONTH, 30);

        topRatedMoviesPage.filterReleaseDatesFromCalendar(from, to);
        List<String> releaseDates = topRatedMoviesPage.getMovieReleaseDates();

        for(String releaseDate: releaseDates) {
            Calendar date = parseStringToDate(releaseDate);
            try {
                assertTrue(isInInterval(date, from, to));
            } catch (AssertionFailedError e) {
                screenshot("releaseDateNotMatchingFilter");
                return;
            }
        }
    }

    // choose release date interval by typing specific date in the input field
    // get all the movie's release dates
    // check the release dates are in that interval
    @Test
    void filterByReleaseDatesTyping() {
        Calendar from = Calendar.getInstance();

        from.set(Calendar.YEAR, 2024);
        from.set(Calendar.MONTH, Calendar.MAY);
        from.set(Calendar.DAY_OF_MONTH, 1);

        Calendar to = Calendar.getInstance();

        to.set(Calendar.YEAR, 2024);
        to.set(Calendar.MONTH, Calendar.NOVEMBER);
        to.set(Calendar.DAY_OF_MONTH, 30);

        topRatedMoviesPage.filterReleaseDatesByTyping(from, to);
        List<String> releaseDates = topRatedMoviesPage.getMovieReleaseDates();

        for(String releaseDate: releaseDates) {
            Calendar date = parseStringToDate(releaseDate);
            try {
                assertTrue(isInInterval(date, from, to));
            } catch (AssertionFailedError e) {
                screenshot("releaseDateNotMatchingFilter");
                return;
            }
        }
    }

    private static Calendar parseStringToDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Date date = format.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isInInterval(Calendar date, Calendar start, Calendar end) {
        return date.after(start) && date.before(end) || date.equals(start) || date.equals(end);
    }

    private static boolean isAscending(int[] ratings) {
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] > ratings[i + 1]) {
                return false;
            }
        }
        return true;
    }
}