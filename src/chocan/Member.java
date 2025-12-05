/*
Member class authored by Lindsey B.
Inherits from Person and stores all information relevant to a "Member"
Not really responsible for much besides being an object with member info
Contains class MemberCard which stores the name and ID of a member
 */

package chocan;

public class Member extends Person {
    private String email;  //
    private MemberCard memberCard; //let all members have object member card

    public Member(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String email, String number)
    {
        super(firstName, lastName, phoneNumber, address, city, state, zipCode);
        this.email = email;
        this.memberCard = new MemberCard(firstName, lastName, number);
    }

    // Getters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public MemberCard getCard() {return memberCard;}
    /*public String getFullName()
    {
        return firstName + " " + lastName;
    }*/
    public String returnInfo() {
    	return firstName + "_" + lastName + "_" + phoneNumber  + "_" + address  + "_" + city + "_" +  state  + "_" + zipCode  + "_" + email + "_" + memberCard.getMemberNumber();
    }

}
