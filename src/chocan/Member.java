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

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public MemberCard getCard() {return memberCard;}
    
    public String returnInfo() {
        return firstName + "_" + lastName + "_" + phoneNumber + "_" + address + "_" + city + "_" + state + "_" + zipCode
                + "_" + email + "_" + memberCard.getMemberNumber();
    }
    
    public MemberCard GetCard() {
        return memberCard;
    }

    public void RequestHealthService() {
        /*
        The member requests a health service from a provider and service of their choosing */

    }

}
