package chocan;

public class ServiceRequest {
    Member member;
    String providerName;
    String serviceType;

    public ServiceRequest() {}
    
    public ServiceRequest(Member member, String providerName, String serviceType) {
    	this.member = member;
    	this.providerName = providerName;
    	this.serviceType = serviceType;
    }
    
    public String getInfo() {
    	return member.getCard().getMemberNumber() + "_" + providerName + "_" + serviceType;
    }
    
}
