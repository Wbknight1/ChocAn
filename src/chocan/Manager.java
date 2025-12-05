package chocan;

public class Manager extends Person{
	
	private String managerNumber;

	public Manager(String firstName, String lastName, String phoneNumber, String address, String city, String state,String zipCode, String number) {
		super(firstName, lastName, phoneNumber, address, city, state, zipCode);
		managerNumber = number;
		
	}
	public String getManagerNumber() {
		return managerNumber;
	}
	
	public String returnInfo() {
		return firstName + "_" + lastName + "_" + phoneNumber + "_" + address + "_" + city + "_" + state + "_" + zipCode;
	}
		
	// Written by Wheeler Knight 12/04/2025
	public void RequestServiceReport(ACMEAccountingServices accounting) {
		/*
		This should prompt the manager to enter the week number and year for the report.
		It should then generate and print the report.
		*/
	};

	// Written by Wheeler Knight 12/04/2025
	public void RequestSummaryReport(ACMEAccountingServices accounting) {
		/*
		This should generate and print the summary report.
		*/
	};




}