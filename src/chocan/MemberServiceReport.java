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

    //construcotrs and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Utility for generating weekly service reports for members and providers.
     * Follows ChocAn requirements for report content and formatting.
     */
    public static class ReportGenerator {
    /**
     * Print a member's service report for the week.
     * The report includes: name, number, address, city, state, zip code,
     * and, for each service received that week: date of service, provider name, service name.
     */
    public static void printMemberReport(Member member, java.util.List<ServiceRecord> weeklyRecords, DataCenter dataCenter) {
        System.out.println("Member Service Report");
        System.out.println("----------------------");
        System.out.println("Name: " + member.getFirstName() + " " + member.getLastName());
        System.out.println("Member Number: " + member.getCard().getMemberNumber());
        System.out.println("Address: " + member.getAddress());
        System.out.println("City: " + member.getCity());
        System.out.println("State: " + member.getState());
        System.out.println("ZIP Code: " + member.getZipCode());
        System.out.println();

        // Filter records belonging to this member, order by service date
        java.util.List<ServiceRecord> memberRecords = new java.util.ArrayList<>();
        for (ServiceRecord sr : weeklyRecords) {
            if (sr.getMemberNumber().equals(member.getCard().getMemberNumber())) {
                memberRecords.add(sr);
            }
        }
        memberRecords.sort((a, b) -> {
            if (a.getServiceDate() == null && b.getServiceDate() == null) return 0;
            if (a.getServiceDate() == null) return 1;
            if (b.getServiceDate() == null) return -1;
            return a.getServiceDate().compareTo(b.getServiceDate());
        });

        if (memberRecords.isEmpty()) {
            System.out.println("No services received this week.");
        } else {
            System.out.printf("%-15s %-20s %-20s\n", "Date of Service", "Provider Name", "Service Name");
            for (ServiceRecord sr : memberRecords) {
                Provider provider = dataCenter.getProviderByNumber(sr.getProviderNumber());
                String providerName = (provider != null) ? provider.getName() : "Unknown";
                String serviceName = "Unknown";
                try {
                    int scIdx = Integer.parseInt(sr.getServiceCode()) - 1;
                    if (scIdx >= 0 && scIdx < dataCenter.SERVICE_NAMES.length) {
                        serviceName = dataCenter.SERVICE_NAMES[scIdx];
                    }
                } catch (Exception e) {
                    // ignore, leave as "Unknown"
                }
                String serviceDate = (sr.getServiceDate() != null) ? sr.getServiceDate().toString() : "";
                System.out.printf("%-15s %-20s %-20s\n", serviceDate, providerName, serviceName);
            }
        }
        System.out.println();
    }

    /**
     * Print a provider's report for the week.
     * Lists all services provided this week (order entered), 
     * and then a summary of number of consultations and fees.
     */
    public static void printProviderReport(Provider provider, java.util.List<ServiceRecord> weeklyRecords, DataCenter dataCenter) {
        System.out.println("Provider Weekly Report");
        System.out.println("----------------------");
        System.out.println("Provider Name: " + provider.getName());
        System.out.println("Provider Number: " + provider.getProviderNumber());
        System.out.println();

        // Filter records for this provider, order by appearance (entry order)
        java.util.List<ServiceRecord> providerRecords = new java.util.ArrayList<>();
        for (ServiceRecord sr : weeklyRecords) {
            if (sr.getProviderNumber().equals(provider.getProviderNumber())) {
                providerRecords.add(sr);
            }
        }

        int consultCount = 0;
        double totalFees = 0.0;

        if (providerRecords.isEmpty()) {
            System.out.println("No services provided this week.");
        } else {
            System.out.printf("%-15s %-15s %-20s %-12s\n", "Date of Service", "Member Number", "Service Name", "Service Fee");
            for (ServiceRecord sr : providerRecords) {
                consultCount++;
                totalFees += sr.getServiceFee();
                String memberNum = sr.getMemberNumber();
                String serviceName = "Unknown";
                try {
                    int scIdx = Integer.parseInt(sr.getServiceCode()) - 1;
                    if (scIdx >= 0 && scIdx < dataCenter.SERVICE_NAMES.length) {
                        serviceName = dataCenter.SERVICE_NAMES[scIdx];
                    }
                } catch (Exception e) {
                    // ignore, leave as "Unknown"
                }
                String serviceDate = (sr.getServiceDate() != null) ? sr.getServiceDate().toString() : "";
                System.out.printf("%-15s %-15s %-20s $%-11.2f\n", serviceDate, memberNum, serviceName, sr.getServiceFee());
            }
        }

        // Summary
        System.out.println();
        System.out.println("Number of Consultations: " + consultCount);
        System.out.printf("Total Fees: $%.2f\n", totalFees);
        System.out.println();
    }
  }
}
