package chocan;

/* Written By Rossy Hollinger 12/05/2025 */

import java.util.List;

public class Provider extends Person {
	
	private String providerNumber;
    private String password;

    public Provider() {
        super();
    }
    
        // Written by Wheeler Knight 12/04/2025
        public String getProviderNumber()
        {
            return providerNumber;
        }
        
        public String getPassword() {
            return password;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }

	
    public Provider(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String number){
        super(firstName, lastName, phoneNumber, address, city, state, zipCode);
        providerNumber = number;
    }

    // Written by Wheeler Knight 12/04/2025
    public boolean checkCard(MemberCard card) {

        DataCenter dataCenter = new DataCenter();
        for (Member member : dataCenter.getMembers()) {
            MemberCard mcard = member.getCard();
            if (mcard.getMemberNumber().equals(card.getMemberNumber()) &&
                mcard.getFirstName().equalsIgnoreCase(card.getFirstName()) &&
                mcard.getLastName().equalsIgnoreCase(card.getLastName())) {
                boolean isSuspended = false;
                for (Member sm : dataCenter.getSuspendedMembers()) {
                    if (sm.getCard().getMemberNumber().equals(card.getMemberNumber())) {
                        isSuspended = true;
                        break;
                    }
                }
                return !isSuspended;
            }
        }
        return false;
    };
    
    // Written by Wheeler Knight 12/04/2025
    /**
     */
    public ProviderForm fillForm(ACMEAccountingServices accounting) 
    {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            ProviderForm form = new ProviderForm();

            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            form.setHours((byte) now.getHour());
            form.setMinutes((byte) now.getMinute());
            form.setSeconds((byte) now.getSecond());

            System.out.println("=== Provider Form ===");
            System.out.println("Enter the date of service:");

            System.out.print("  Day (1-31): ");
            byte day = (byte) Integer.parseInt(scanner.nextLine().trim());
            form.setDay(day);

            System.out.print("  Month (1-12): ");
            byte month = (byte) Integer.parseInt(scanner.nextLine().trim());
            form.setMonth(month);

            System.out.print("  Year (e.g. 2025): ");
            short year = (short) Integer.parseInt(scanner.nextLine().trim());
            form.setYear(year);

            System.out.print("Enter the member number: ");
            String memberNumber = scanner.nextLine().trim();
            form.setNumber(memberNumber);

            String memberName = "";
            for (Member m : accounting.getMembers()) {
                if (m.getCard().getMemberNumber().equals(memberNumber)) {
                    memberName = m.getFirstName() + " " + m.getLastName();
                    break;
                }
            }
            if (memberName.isEmpty())
                System.out.println("WARNING: Member not found in system!");

            form.setName(memberName.isEmpty() ? "Unknown Member" : memberName);

            System.out.print("Enter the 6-digit service code: ");
            String codeInput = scanner.nextLine().trim();
            int serviceCode;
            try {
                serviceCode = Integer.parseInt(codeInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid code, defaulting to 1.");
                serviceCode = 1;
            }

            double fee = 0.0;
            DataCenter dc = new DataCenter(); 
            if (serviceCode >= 1 && serviceCode <= dc.SERVICE_FEE_RATES.length) {
                fee = dc.SERVICE_FEE_RATES[serviceCode - 1];
                System.out.printf("Service fee for code %06d is $%.2f.\n", serviceCode, fee);
            } else {
                System.out.print("Unknown code. Enter the fee manually: ");
                try {
                    fee = Double.parseDouble(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    fee = 0.0;
                }
            }

            form.setFee(fee);

            System.out.println("Provider form filled successfully.");

            return form;
        }
    }

    // Written by Wheeler Knight 12/04/2025
    public boolean EnterPassword(Person person) {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            System.out.print("Enter your password: ");
            String inputPassword = scanner.nextLine();

    
            if (this.getPassword() != null && this.getPassword().equals(inputPassword)) {
                System.out.println("Password correct. Access granted.");
                return true;
            } else {
                System.out.println("Incorrect password. Access denied.");
                return false;
            }
        }
    }

    // Written by Wheeler Knight 12/04/2025
    public List<Provider> RequestProviderDirectory() {
        DataCenter dataCenter = new DataCenter();
        ProviderDirectory directory = new ProviderDirectory(dataCenter);

        directory.displayProviderDirectory();

        String filePath = "provider_directory.txt";
        directory.saveProviderDirectoryToFile(filePath);
        System.out.println("Provider directory has been saved to: " + filePath);


        return java.util.Arrays.asList(dataCenter.getProviders());

    
    };
    

    

    public String returnInfo() {
    	return firstName + "_" + lastName + "_" + phoneNumber  + "_" + address  + "_" + city + "_" +  state  + "_" + zipCode + "_" + providerNumber;
    }

     // pull getSuspendedMembers from ACMEAccountingServices

    public void checkSuspended(ACMEAccountingServices acmeAccounting) {
    	Member[] suspendedMembers = acmeAccounting.getSuspendedMembers();
    	for(int i = 0; i < suspendedMembers.length; i++) {
    		if(suspendedMembers[i].getCard().getMemberNumber() == this.getProviderNumber()) {
    			//provider is suspended
    		}
    	}
    }


    public ServiceRecord requestServiceRecord(String providerNumber, String startDate, String endDate) {
        DataCenter dataCenter = new DataCenter();

        java.util.List<ServiceRecord> lastWeekRecords = dataCenter.getServiceRecordsForLastWeek();

        if (lastWeekRecords == null || lastWeekRecords.isEmpty()) {
            System.out.println("No service records found for the last week.");
            return null;
        }

        System.out.println("Member Service Record(s) for last week:");
        for (ServiceRecord record : lastWeekRecords) {
            if (providerNumber == null || providerNumber.isEmpty() || providerNumber.equals(record.getProviderNumber())) {
                System.out.println("Provider Number: " + record.getProviderNumber()
                    + ", Member Number: " + record.getMemberNumber()
                    + ", Service Code: " + record.getServiceCode()
                    + ", Service Fee: $" + record.getServiceFee()
                    + ", Service Date: " + record.getServiceDate());
            }
        }

        for (ServiceRecord record : lastWeekRecords) {
            if (providerNumber == null || providerNumber.isEmpty() || providerNumber.equals(record.getProviderNumber())) {
                return record;
            }
        }
        return null;
    
    }

    public SummaryReport requestSummaryReport(String startDate, String endDate) {
        DataCenter dataCenter = new DataCenter();
        SummaryReport.printSummaryReport(dataCenter);
        return null;
    }

}
