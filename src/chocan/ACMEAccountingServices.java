package chocan;

/* Written by Logan Hernandez 12/05/2025 */

public class ACMEAccountingServices {
    private Member[] members;
    private Member[] suspendedMembers;
    private boolean memberValidationEnabled;

    public ACMEAccountingServices(Member[] members, Member[] suspendedMembers) {
        this.members = members;
    };

    public Member[] getMembers() {
    	return members;
    }
    
    public Member[] getSuspendedMembers() {
    	return suspendedMembers;
    }
    
    // Edited by Wheeler Knight on 12/4/2025 - Fixed bug: was setting this.members instead of this.suspendedMembers
    public void setSuspendedMembers(Member[] suspendedMembers) {
    	this.suspendedMembers = suspendedMembers;
    }
    

    // Edited by Wheeler Knight on 12/4/2025 - Implemented suspendMember functionality
    public void suspendMember(String number) {
    	for(int i = 0; i < members.length; i++) {
    		if(members[i].getCard().getMemberNumber().equals(number)) {
    			Member memberToSuspend = members[i];
    			Member[] newSuspended = new Member[suspendedMembers.length + 1];
    			System.arraycopy(suspendedMembers, 0, newSuspended, 0, suspendedMembers.length);
    			newSuspended[suspendedMembers.length] = memberToSuspend;
    			this.suspendedMembers = newSuspended;
    			Member[] newMembers = new Member[members.length - 1];
    			int idx = 0;
    			for(int j = 0; j < members.length; j++) {
    				if(j != i) {
    					newMembers[idx++] = members[j];
    				}
    			}
    			this.members = newMembers;
    			
    			System.out.println("Member suspended: " + memberToSuspend.getFirstName() + " " + memberToSuspend.getLastName());
    			return;
    		}
    	}
    	System.out.println("Member not found with number: " + number);
    }
    

    // Written by Wheeler Knight on 12/4/2025 - Implemented unsuspendMember functionality
    public void unsuspendMember(String number) {
    	for(int i = 0; i < suspendedMembers.length; i++) {
    		if(suspendedMembers[i].getCard().getMemberNumber().equals(number)) {
    			Member memberToUnsuspend = suspendedMembers[i];
    			Member[] newMembers = new Member[members.length + 1];
    			System.arraycopy(members, 0, newMembers, 0, members.length);
    			newMembers[members.length] = memberToUnsuspend;
    			this.members = newMembers;
    			Member[] newSuspended = new Member[suspendedMembers.length - 1];
    			int idx = 0;
    			for(int j = 0; j < suspendedMembers.length; j++) {
    				if(j != i) {
    					newSuspended[idx++] = suspendedMembers[j];
    				}
    			}
    			this.suspendedMembers = newSuspended;
    			System.out.println("Member unsuspended: " + memberToUnsuspend.getFirstName() + " " + memberToUnsuspend.getLastName());
    			return;
    		}
    	}
    	System.out.println("Suspended member not found with number: " + number);
    }
    
    // Written by Wheeler Knight 12/04/2025
    public void AddMembers(Member member) {
    if (member == null) return;
    // Check if member already exists (by member number)
    for (Member m : members) {
        if (m != null && m.getCard().getMemberNumber().equals(member.getCard().getMemberNumber())) {
            System.out.println("Member already exists with number: " + member.getCard().getMemberNumber());
            return;
        }
    }

    // Add to members array
    Member[] newMembers = new Member[members.length + 1];
    System.arraycopy(members, 0, newMembers, 0, members.length);
    newMembers[members.length] = member;
    this.members = newMembers;
    System.out.println("Member added: " + member.getFirstName() + " " + member.getLastName());

    };

    // Written by Wheeler Knight 12/04/2025
    public void ToggleValidation() {
    this.memberValidationEnabled = !this.memberValidationEnabled;
    System.out.println("Member card validation is now " + (this.memberValidationEnabled ? "ENABLED" : "DISABLED") + ".");
    };

    // Written by Wheeler Knight 12/04/2025
    // NOTE: THIS METHOD IS PARTIALLY GENERATED/EDITED BY AI (Curosr IDE)
    public void BillMembers() {
        System.out.println("=== Billing Members ===");
        DataCenter dataCenter = new DataCenter();
        java.util.List<ServiceRecord> records = dataCenter.getServiceRecords();
        java.util.Map<String, Double> memberTotalFees = new java.util.HashMap<>();
        java.util.Map<String, java.util.List<ServiceRecord>> memberServices = new java.util.HashMap<>();

        // Aggregate service fees for each member
        for (ServiceRecord sr : records) {
            String memberNumber = sr.getMemberNumber();
            double fee = 0.0;
            try {
                fee = sr.getServiceFee();
            } catch (Exception e) {
                try {
                    int code = Integer.parseInt(sr.getServiceCode());
                    if (code >= 1 && code <= dataCenter.SERVICE_FEE_RATES.length) {
                        fee = dataCenter.SERVICE_FEE_RATES[code - 1];
                    }
                } catch (Exception ignore) { }
            }
            memberTotalFees.put(memberNumber, memberTotalFees.getOrDefault(memberNumber, 0.0) + fee);

            if (!memberServices.containsKey(memberNumber)) {
                memberServices.put(memberNumber, new java.util.ArrayList<>());
            }
            memberServices.get(memberNumber).add(sr);
        }

        // Bill each member who has services
        for (Member member : members) {
            String memberNum = member.getCard().getMemberNumber();
            if (!memberTotalFees.containsKey(memberNum)) {
                System.out.println("No services to bill for member: " + member.getFirstName() + " " + member.getLastName());
                continue;
            }
            double totalFee = memberTotalFees.get(memberNum);
            System.out.println("\n--- Bill for Member ---");
            System.out.println("Name: " + member.getFirstName() + " " + member.getLastName());
            System.out.println("Member Number: " + memberNum);
            System.out.println("Total Due: $" + String.format("%.2f", totalFee));
            System.out.println("Services Received:");
            for (ServiceRecord sr : memberServices.get(memberNum)) {
                String dateStr = sr.getServiceDate() != null ? sr.getServiceDate().toString() : "";
                String providerName = "Unknown";
                Provider prov = dataCenter.getProviderByNumber(sr.getProviderNumber());
                if (prov != null) providerName = prov.getFullName();
                String serviceName = "Unknown";
                try {
                    int code = Integer.parseInt(sr.getServiceCode());
                    if (code >= 1 && code <= dataCenter.SERVICE_NAMES.length)
                        serviceName = dataCenter.SERVICE_NAMES[code - 1];
                } catch (Exception ignore) { }
                double fee = 0.0;
                try {
                    fee = sr.getServiceFee();
                } catch (Exception e) {
                    try {
                        int code = Integer.parseInt(sr.getServiceCode());
                        if (code >= 1 && code <= dataCenter.SERVICE_FEE_RATES.length)
                            fee = dataCenter.SERVICE_FEE_RATES[code - 1];
                    } catch (Exception ignore) { }
                }
                System.out.printf("  %s | Provider: %s | Service: %s | Fee: $%.2f\n", dateStr, providerName, serviceName, fee);
            }
            System.out.println("-----------------------\n");
        }
    };
 
}
