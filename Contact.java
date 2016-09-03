
public class Contact {
	
	private final String firstName, lastName, number;

	public Contact(String firstName, String lastName, String number) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getNumber(){
		return number;
	}

}
