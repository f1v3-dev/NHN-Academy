public class Date {
    private int year;
    private int month;
    private int day;

    public Date() {
        this(1970, 01, 01);
    }

    public Date (int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    @Override
    public String toString() {
        return this.year + "-" + this.month + "-" + this.day;
    }
}
