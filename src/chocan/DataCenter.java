package chocan;

import java.util.*;

public class DataCenter extends readAndWritable{
	public final String[] SERVICE_NAMES = {"CONSULTATION", "CONVERSATION", "EMERGENCY"};
	public final Double[] SERVICE_FEE_RATES = {12.99, 8.99, 29.99};

    private Vector<Member> members = new Vector<>();
    private Vector<Member> suspendedMembers = new Vector<>();
    private Vector<Provider> providers = new Vector<>();
    private Vector<Manager> managers = new Vector<>();

    // Edited by Wheeler Knight on 12/4/2025 - Initialized Vector fields to prevent NullPointerException
    private Vector<ProviderForm> weeklyProviderForms = new Vector<>();
    private Vector<MemberServiceReport> allMemberServiceReports = new Vector<>();
    private Vector<ServiceRequest> pendingServiceRequest = new Vector<>();
    private Vector<ServiceRecord> serviceRecords = new Vector<>();
    
    public DataCenter() {
    	Vector<Member> m = readMembers("members.txt");
    	if (m != null) members = m;
    	
    	Vector<Member> sm = readMembers("suspendedmembers.txt");
    	if (sm != null) suspendedMembers = sm;
    	
    	Vector<Provider> p = readProviders("provider.txt");
    	if (p != null) providers = p;
    	
    	Vector<Manager> mg = readManagers("manager.txt");
    	if (mg != null) managers = mg;    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed file path to match read operations (provider.txt not providers.txt)
    public void saveInfo() {
    	
    	 writeInfo("members.txt");
    	 writeInfo("suspendedmembers.txt");
    	 writeInfo("provider.txt");
    	 writeInfo("manager.txt");
    	 
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed string comparisons to use .equals() instead of ==
    public void writeInfo(String fileName) {
    	
    	if(fileName.equals("members.txt")) {
    		writeMember("src/chocan/" + fileName, members);
    	}
    	
    	if(fileName.equals("provider.txt")) {
    		writeProvider("src/chocan/" + fileName, providers);	
    	}
    	
    	if(fileName.equals("manager.txt")) {
    		writeManager("src/chocan/" + fileName, managers);	
    	}
    	if(fileName.equals("suspendedmembers.txt")) {
    		writeMember("src/chocan/" + fileName, suspendedMembers);
    	}
    	
    }
    
    public Member[] getMembers() {
    	
    	return members.toArray(new Member[members.size()]);
    	
    }
    
    public Member[] getSuspendedMembers() {
    	
    	return suspendedMembers.toArray(new Member[suspendedMembers.size()]);
    	
    }
    
    public Provider[] getProviders() {
    	
    	return providers.toArray(new Provider[providers.size()]);
    	
    }
    
    public Manager[] getManagers() {
    	
    	return managers.toArray(new Manager[managers.size()]);
    	
    }
    
    public ProviderForm[] getWeeklyProviderForm() {
    	
    	return weeklyProviderForms.toArray(new ProviderForm[weeklyProviderForms.size()]);
    	
    }
    
    public MemberServiceReport[] getAllMemberServiceReport() {
    	
    	return allMemberServiceReports.toArray(new MemberServiceReport[allMemberServiceReports.size()]);
    	
    }
    
    public ServiceRequest[] getPendingServiceRequest() {
    	
    	return pendingServiceRequest.toArray(new ServiceRequest[pendingServiceRequest.size()]);
    	
    }
    
    public void addMember(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String email, String number) {
    	members.add(new Member(firstName, lastName, phoneNumber, address, city, state, zipCode, email, number));
    }
    
    public void addPendingServiceRequest(Member member, String providerName, String serviceType) {
    	pendingServiceRequest.add(new ServiceRequest(member, providerName, serviceType));
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed string comparisons to use .equals() instead of ==
    public boolean validMember(String name, String number) {
    	for(int i = 0;  i < members.size(); i++) {
    		if((members.get(i).getFirstName() + " " + members.get(i).getLastName()).equals(name) && members.get(i).getCard().getMemberNumber().equals(number)) return true;
    	}
		return false;
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed string comparisons to use .equals() instead of ==
    public boolean validProvider(String name, String number) {
    	for(int i = 0;  i < providers.size(); i++) {
    		if((providers.get(i).getFirstName() + " " + providers.get(i).getLastName()).equals(name) && providers.get(i).getProviderNumber().equals(number)) return true;
    	}
		return false;
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed string comparisons to use .equals() instead of ==
    public boolean validManager(String name, String number) {
    	for(int i = 0;  i < managers.size(); i++) {
    		if((managers.get(i).getFirstName() + " " + managers.get(i).getLastName()).equals(name) && managers.get(i).getManagerNumber().equals(number)) return true;
    	}
		return false;
    }
    
    public java.util.List<ServiceRecord> getServiceRecordsForLastWeek() {
        java.time.LocalDate oneWeekAgo = java.time.LocalDate.now().minusWeeks(1);
        java.util.List<ServiceRecord> result = new java.util.ArrayList<>();
        for (ServiceRecord sr : serviceRecords) {
            if (sr.getServiceDate() != null && !sr.getServiceDate().isBefore(oneWeekAgo)) {
                result.add(sr);
            }
        }
        return result;
    }
    
    public Provider getProviderByNumber(String providerNumber) {
        for (Provider p : providers) {
            if (p.getProviderNumber().equals(providerNumber)) {
                return p;
            }
        }
        return null;
    }
    
    // Written by Wheeler Knight on 12/4/2025 - Added getMemberByNumber for consistency with getProviderByNumber
    public Member getMemberByNumber(String memberNumber) {
        for (Member m : members) {
            if (m.getCard().getMemberNumber().equals(memberNumber)) {
                return m;
            }
        }
        return null;
    }
    
    // Written by Wheeler Knight on 12/4/2025 - Added utility method to centralize service fee lookup
    public double getServiceFeeByCode(int code) {
        if (code >= 1 && code <= SERVICE_FEE_RATES.length) {
            return SERVICE_FEE_RATES[code - 1];
        }
        return 0.0;
    }
    
    public void addServiceRecord(ServiceRecord record) {
        serviceRecords.add(record);
    }
    
    public java.util.List<ServiceRecord> getServiceRecords() {
        return new java.util.ArrayList<>(serviceRecords);
    }
    
    public void addProvider(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String number) {
        providers.add(new Provider(firstName, lastName, phoneNumber, address, city, state, zipCode, number));
    }
}
