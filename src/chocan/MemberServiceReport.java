package chocan;

public class MemberServiceReport {
    private String name;
    private String number;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private byte day;
    private byte month;
    private short year;
    private String comments;
    private int serviceCode;

    //construcotrs and getters
    public MemberServiceReport(String name, String number, String address, String city, String state,
                               String zipCode, byte day, byte month, short year, String comments, int serviceCode) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.day = day;
        this.month = month;
        this.year = year;
        this.comments = comments;
        this.serviceCode = serviceCode;
    }

    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public byte getDay() {
        return day;
    }
    public byte getMonth() {
        return month;
    }
    public short getYear() {
        return year;
    }
    public String getComments() {
        return comments;
    }
    public int getServiceCode() {
        return serviceCode;
    }

}
