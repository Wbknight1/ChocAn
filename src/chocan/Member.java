/*
Member class authored by Lindsey Bowen 12/05/2025
Inherits from Person and stores all information relevant to a "Member"
Not really responsible for much besides being an object with member info
Contains class MemberCard which stores the name and ID of a member
 */

package chocan;

public class Member extends Person {
    private String email;  //
    private String pin; // PIN for login validation
    private MemberCard memberCard; //let all members have object member card

    public Member() {
        super(); // inherits from Person
    }

    public Member(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String email, String number)
    {
        super(firstName, lastName, phoneNumber, address, city, state, zipCode);
        this.email = email;
        this.memberCard = new MemberCard(firstName, lastName, number);
    }

    // Getters and Setters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPin() {return pin;}
    public void setPin(String pin) {this.pin = pin;}
    public MemberCard getCard() {return memberCard;}
    /*public String getFullName()
    {
        return firstName + " " + lastName;
    }*/
    public String returnInfo() {
        return firstName + "_" + lastName + "_" + phoneNumber + "_" + address + "_" + city + "_" + state + "_" + zipCode
                + "_" + email + "_" + memberCard.getMemberNumber();
    }

    /**
     * Written by Wheeler Knight 12/04/2025
     * Scanner try/catch wrappen written with the help of AI Agent
     */
    public void RequestHealthService() {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            System.out.println("=== Request Health Service ===");
            System.out.print("Enter the provider's name: ");
            String providerName = scanner.nextLine().trim();

            System.out.print("Enter the type of service: ");
            String serviceType = scanner.nextLine().trim();

            ServiceRequest request = new ServiceRequest(this, providerName, serviceType);

            System.out.println("Service request created:");
            System.out.println("  Member Number: " + request.member.getCard().getMemberNumber());
            System.out.println("  Provider: " + request.providerName);
            System.out.println("  Service Type: " + request.serviceType);
        }
    }
}
