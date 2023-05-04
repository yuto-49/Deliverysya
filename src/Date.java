public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        this(1, 1, 2000);
    }

    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public Date(Date date) {
        this(date.day, date.month, date.year);
    }

    // Accessor methods
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

    // Mutator methods
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new InvalidDateException("Day must be between 1 and 31 (inclusive).");
        }
        this.day = day;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new InvalidDateException("Month must be between 1 and 12 (inclusive).");
        }
        this.month = month;
    }

    public void setYear(int year) {
        if (year < 0) {
            throw new InvalidDateException("Year must be non-negative.");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public class InvalidDateException extends RuntimeException {
        public InvalidDateException(String message) {
            super(message);
        }
    }
}

