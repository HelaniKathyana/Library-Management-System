package util;

public class DateTime {
    private int day;
    private int month;
    private int year;
    private int hr;
    private int mint;
    private int dateTimes []= new int[5];

    public DateTime(int day, int month, int year, int hr, int mint) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hr = hr;
        this.mint = mint;
    }
    public DateTime(){

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getMint() {
        return mint;
    }

    public void setMint(int mint) {
        this.mint = mint;
    }

    public void setdateTime(int day, int month, int year, int hr, int mint){
        dateTimes[0] = day;
        dateTimes[1] = month;
        dateTimes[2] = year;
        dateTimes[3]= hr;
        dateTimes[4]= mint;
    }

    private int[] getDateTimes(){
        return dateTimes;
    }
}
