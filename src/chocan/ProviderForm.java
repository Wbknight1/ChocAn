package chocan;

public class ProviderForm {
    private byte hours;
    private byte minutes;
    private byte seconds;
    private String number;
    private String name;
    private byte day;
    private byte month;
    private short year;
    private double fee;

    //GetHours() etc
    public String getInfo() {
        return name + "|" + number + "|" + hours + "|" + minutes + "|" + seconds + "|" + day + "|" + month + "|" + year
                + "|" + fee;
    }
    
    // Written by Wheeler Knight 12/04/2025
    public byte getHours() {
        return hours;
    }

    public void setHours(byte hours) {
        this.hours = hours;
    }

    public byte getMinutes() {
        return minutes;
    }

    public void setMinutes(byte minutes) {
        this.minutes = minutes;
    }

    public byte getSeconds() {
        return seconds;
    }

    public void setSeconds(byte seconds) {
        this.seconds = seconds;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    
}
