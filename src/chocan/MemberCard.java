/*
Member Class
Responsible for being able to return membercard info relevant to a particular member
WRitten by Dylan Stokes 12/05/2025
 */

package chocan;

public class MemberCard {
    private String memberFirstName;
    private String memberLastName;
    private String memberNumber;

    public MemberCard() {} // default constructor

    public MemberCard(String memberFirstName, String memberLastName, String memberNumber)
    {
        this.memberFirstName = memberFirstName;
        this.memberLastName = memberLastName;
        this.memberNumber = memberNumber;

    }

    public String getFirstName() { return memberFirstName;}
    public String getLastName() { return memberLastName;}
    public String getMemberNumber() { return memberNumber;}
}
