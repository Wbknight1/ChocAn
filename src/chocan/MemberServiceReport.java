package chocan;

/* Written By Wheeler Knight 12/04/2025*/
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
    private String serviceName;
    private int serviceCode;

    // Constructors and getters

    public MemberServiceReport() {} // default constructor

    public MemberServiceReport(String name, String number, String address, String city, String state, String zipCode, byte day, byte month, short year, String comments, String serviceName, int serviceCode) {
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
        this.serviceName = serviceName;
        this.serviceCode = serviceCode;
    }

    // Getters
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

    public String getServiceName() {
        return serviceName;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Print a member service report for a given member and weekly records.
     * Written by Wheeler Knight 12/04/2025
     * NOTE: AI ASSISTED IN CREATING THE SORTER AND THE REPORT GENERATOR
    */
    public static void printMemberReport(Member member, java.util.List<ServiceRecord> weeklyRecords, DataCenter dataCenter) {
        java.util.List<ServiceRecord> memberRecords = new java.util.ArrayList<>();
        for (ServiceRecord sr : weeklyRecords) {
            if (sr != null && sr.getMemberNumber() != null && sr.getMemberNumber().equals(member.getCard().getMemberNumber())) {
                memberRecords.add(sr);
            }
        }

        java.util.Collections.sort(memberRecords, new java.util.Comparator<ServiceRecord>() {
            @Override
            public int compare(ServiceRecord r1, ServiceRecord r2) {
                java.time.LocalDate d1 = null, d2 = null;
                try {
                    if (r1.getServiceDate() instanceof java.time.LocalDate)
                        d1 = (java.time.LocalDate) r1.getServiceDate();
                    else if (r1.getServiceDate() != null)
                        d1 = java.time.LocalDate.parse(r1.getServiceDate().toString());
                } catch (Exception ignored) {}
                try {
                    if (r2.getServiceDate() instanceof java.time.LocalDate)
                        d2 = (java.time.LocalDate) r2.getServiceDate();
                    else if (r2.getServiceDate() != null)
                        d2 = java.time.LocalDate.parse(r2.getServiceDate().toString());
                } catch (Exception ignored) {}
                if (d1 == null && d2 == null) return 0;
                if (d1 == null) return 1;
                if (d2 == null) return -1;
                return d1.compareTo(d2);
            }
        });

        // Header
        System.out.println("Member Service Report");
        System.out.println("--------------------");
        System.out.println("Name        : " + member.getFirstName() + " " + member.getLastName());
        System.out.println("Number      : " + member.getCard().getMemberNumber());
        System.out.println("Address     : " + member.getAddress());
        System.out.println("City        : " + member.getCity());
        System.out.println("State       : " + member.getState());
        System.out.println("Zip Code    : " + member.getZipCode());
        System.out.println();

        if (memberRecords.isEmpty()) {
            System.out.println("No services rendered for this member during the selected week.");
            return;
        }

        // Table Header
        System.out.printf("%-12s  %-20s  %-20s  %-20s\n", "Service Date", "Provider Name", "Service Name", "Comments");
        System.out.println("-------------------------------------------------------------------------------");

        for (ServiceRecord sr : memberRecords) {
            // Date of service
            String date = "";
            try {
                if (sr.getServiceDate() instanceof java.time.LocalDate)
                    date = ((java.time.LocalDate) sr.getServiceDate()).toString();
                else if (sr.getServiceDate() != null)
                    date = sr.getServiceDate().toString();
            } catch (Exception e) { date = ""; }

            String providerName = "";
            String providerNum = sr.getProviderNumber();
            Provider[] allProviders = dataCenter.getProviders();
            if (providerNum != null && allProviders != null) {
                for (Provider p : allProviders) {
                    if (p != null && providerNum.equals(p.getProviderNumber())) {
                        providerName = p.getFullName();
                        break;
                    }
                }
            }
            String serviceName = "";
            try {
                int code = Integer.parseInt(sr.getServiceCode());
                if (code >= 1 && code <= dataCenter.SERVICE_NAMES.length) {
                    serviceName = dataCenter.SERVICE_NAMES[code - 1];
                }
            } catch (Exception e) {
                serviceName = "";
            }

            String comments = "";

            System.out.printf("%-12s  %-20s  %-20s  %-20s\n", date, providerName, serviceName, comments);
        }

        System.out.println();
    }
}
