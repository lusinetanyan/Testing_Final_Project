package enums;

public enum SortOption {
    POPULARITY_DESC(0),
    POPULARITY_ASC(1),
    RATING_DESC(2),
    RATING_ASC(3),
    RELEASE_DATE_DESC(4),
    RELEASE_DATE_ASC(5),
    TITLE_ASC(6),
    TITLE_DESC(7);

    private final int value;

    SortOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
