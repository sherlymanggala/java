package DM19S1;

public class Address {
	private String address;
	
	/**
	 * Constructor
	 * @param a is the string of full address
	 */
	public Address(String a) {
		a = a.trim();
		
		if(a.matches(".*[\\s][a-zA-Z]{3}$")) {
			address = a;
		}
		else {
			address = "";
		}
	}
	
	/**
	 * check address validation
	 * @return true if address is valid, and false if address is invalid
	 */
	public boolean isValid() {
		return address != "";
	}
	
	/**
	 * parse address to String type of data
	 * @return address if address is valid, return "address invalid" if address is invalid 
	 */
	public String toString() {
		if(isValid()) {
			return address;
		}
		else {
			return "address invalid";
		}
	}
}
