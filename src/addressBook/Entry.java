package addressBook;

import java.util.Objects;

public class Entry {
	private String firstName; //initializing the firstName variable for each Entry object
	private String lastName; //initializing the lastName variable for each Entry object
	private String phoneNum; //initializing the phoneNum variable for each Entry object
	private String emailAd; //initializing the emailAd variable for each Entry object

	public Entry(String firstName, String lastName, String phoneNum, String emailAd) { //constructor for each new entry
		this.firstName = firstName; //setting the entry firstName
		this.lastName = lastName; //setting the entry lastName
		this.phoneNum = phoneNum; //setting the entry phoneNum
		this.emailAd = emailAd; //setting the entry emailAd 
	}
//Just basic getters/setters to pass information around the program
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return emailAd;
	}

	public void setEmailAd(String emailAd) {
		this.emailAd = emailAd;
	}
// end of the getters and setters
	
	@Override //overriding the equals method and passing in myObj
	public boolean equals(Object myObj) {
		if (this == myObj) {  //Checking to see if 'THIS' is equal to myObj
			return true; //If it is equal return true
		}
		if (myObj == null || getClass() != myObj.getClass()) { //if myObj is null OR class does not equal myObj Class
			return false; //returns false because this object is either null or does not equal myObj class
		}
		Entry entryObj = (Entry) myObj; //assigns the value of the ENTRY object 'myObj' to entryObj
		return Objects.equals(getEmail(), entryObj.getEmail()); // returns a method callback to the .equals method, 
																//getEmail method on both the OBJECTS class and the EntryObj class
	}

	@Override
	public int hashCode() { //using hash code to correctly locate the emailAd within my list of Objects (entries)
		return Objects.hash(getEmail());
	}

	@Override
	public String toString() { //formatting the output method of an entry when searched
		return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phoneNum='" + phoneNum
				+ '\'' + ", emailAd='" + emailAd + '\'' + '}';
	}
}
