package chocan;

public class ACMEAccountingServices{
	
	private Member[] members;
	private Member[] suspendedMembers; //ACME's list of suspended members, should talk to VerifyMember
    
    public ACMEAccountingServices(Member[] members, Member[] suspendedMembers) {
    	
    	this.members = members;
    	this.suspendedMembers = suspendedMembers;
    	
    }
    
    public void setMembers(Member[] members) {
    	this.members = members;
    }
    
    
    public Member[] getMembers() {
    	return members;
    }
    
    public Member[] getSuspendedMembers() {
    	return suspendedMembers;
    }
    
    public void setSuspendedMembers(Member[] members) {
    	this.members = members;
    }
    
    public void suspendMember(String number) {
    	for(int i = 0; i < members.length; i++) {
    		if(members[i].getCard().getMemberNumber() == number) ;
    	}
    }
    
    public void unsuspendMember(String number) {

    }
    
    // Written by Wheeler Knight 12/04/2025
    public void AddMembers(Member member) {

    };

    // Written by Wheeler Knight 12/04/2025
    public void ToggleValidation() {
        
    };

    // Written by Wheeler Knight 12/04/2025
    public void BillMembers() {
        /*
        This should bill the members for their services.
        */
    };
 
}
