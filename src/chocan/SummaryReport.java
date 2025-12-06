package chocan;
/* Written by Lindsey Bowen 12/04/2025*/

public class SummaryReport {

    public static void printSummaryReport(DataCenter dataCenter) {
        java.util.Map<String, ProviderSummary> providerMap = new java.util.HashMap<>();

        java.util.List<ServiceRecord> serviceRecords = dataCenter.getServiceRecordsForLastWeek();
        for (ServiceRecord sr : serviceRecords) {
            String providerNumber = sr.getProviderNumber();
            double fee = sr.getServiceFee();
            Provider provider = dataCenter.getProviderByNumber(providerNumber);
            if (provider == null) continue; 

            ProviderSummary summary = providerMap.get(providerNumber);
            if (summary == null) {
                summary = new ProviderSummary(provider.getFullName(), providerNumber);
                providerMap.put(providerNumber, summary);
            }
            summary.consultCount += 1;
            summary.feeOwed += fee;
        }

        System.out.println("Summary Report (This Week)");
        System.out.printf("%-20s %-15s %-20s %-15s\n", "Provider Name", "Provider Number", "Number of Consultations", "Fee Owed ($)");
        double totalFeeAll = 0;
        int totalConsults = 0;

        for (ProviderSummary ps : providerMap.values()) {
            System.out.printf("%-20s %-15s %-20d $%-14.2f\n", ps.name, ps.number, ps.consultCount, ps.feeOwed);
            totalFeeAll += ps.feeOwed;
            totalConsults += ps.consultCount;
        }

        System.out.println("\nTotal Providers Who Serviced Members: " + providerMap.size());
        System.out.println("Total Number of Consultations: " + totalConsults);
        System.out.printf("Overall Fee to be distributed to providers: $%.2f\n", totalFeeAll);
    }

    static class ProviderSummary {
        String name;
        String number;
        int consultCount;
        double feeOwed;

        ProviderSummary(String name, String number) {
            this.name = name;
            this.number = number;
            this.consultCount = 0;
            this.feeOwed = 0.0;
        }
    }
}
