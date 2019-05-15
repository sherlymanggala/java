package DM19S1;

public class Address {
	private String address;
	
	/**
	 * Constructor
	 * @param a is the string of full address
	 */
	public Address(String a) {
		a = a.trim();
		String[] temp = a.trim().split(",");
		if(temp.length == 3) {
			// check regex if the last address that stored in temp[2] is a state
			if(temp[2].trim().matches("[a-zA-Z]{3}$")) {
				address = a;
			}
			else {
				address = "";
			}
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
