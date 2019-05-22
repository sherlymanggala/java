package DM19S1;

/** Donator Class
 * An object class of a donator
 * @author sherly
 */

public class Donator {
	// instance fields
	private String name;
	private CustomDate birthday;
	private String phone;
	private String postcode;
	private Address address;
	private String recipientStr;
	private String donationStr;
	
	/**
	 * constructor
	 * includes donator's name, birthday, phone, postcode, address,
	 * string for recipient, and string for donation
	 */
	public Donator() {
		name = null;
		birthday = null;
		phone = null;
		postcode = null;
		address = null;
		recipientStr = null;
		donationStr = null;
	}
	
	/**
	 * Overloading constructor, splitting the param string by each keyword
	 * @param s the string that contains input donator's information
	 */
	public Donator(String s) {
		String[] temp = s.trim().split(";");
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].toLowerCase().contains("name")) {
				String[] temp2 = temp[i].split("name ");
				if(temp2.length == 2) {
					name = temp2[1].trim();
				}
				//System.out.println(name);
			}
			if(temp[i].toLowerCase().contains("birthday")) {
				String[] temp2 = temp[i].trim().split("birthday ");
				if(temp2.length == 2) {
					birthday = new CustomDate(temp2[1]);
				}
				//System.out.println(birthday);
			}
			if(temp[i].toLowerCase().contains("phone")) {
				String[] temp2 = temp[i].trim().split("phone ");
				if(temp2.length == 2) {
					phone = temp2[1].trim();
				}
			}
			if(temp[i].toLowerCase().contains("postcode")) {
				String[] temp2 = temp[i].trim().split("postcode ");
				if(temp2.length == 2) {
					postcode = temp2[1].trim();
				}
			}
			if(temp[i].toLowerCase().contains("address")) {
				String[] temp2 = temp[i].trim().split("address ");
				if(temp2.length == 2) {
					address = new Address(temp2[1]);
				}
			}
			if(temp[i].toLowerCase().contains("recipient")) {
				String[] temp2 = temp[i].split("recipient ");
				if(temp2.length == 2) {
					recipientStr = "";
					if(temp2[1].contains(",")) {
						String[] temp3 = temp2[1].split(",");
						if(temp3.length == 1) {
							recipientStr = temp3[0].trim();
						}
						if(temp3.length > 1) {
							recipientStr = temp3[0].trim();
							for(int j = 1; j < temp3.length; j++) {
								recipientStr = recipientStr + ", " + temp3[j].trim();
							}
						}
					}
					else {
						recipientStr = temp2[1].trim();
					}
				}
			}
			if(temp[i].toLowerCase().contains("donation")) {
				String[] temp2 = temp[i].split("donation ");
				if(temp2.length == 2) {
					donationStr = "";
					if(temp2[1].contains(",")) {
						String[] temp3 = temp2[1].split(",");
						if(temp3.length == 1) {
							donationStr = temp3[0].trim();
						}
						if(temp3.length > 1) {
							donationStr = temp3[0].trim();
							for(int j = 1; j < temp3.length; j++) {
								donationStr = donationStr + ", " + temp3[j].trim();
							}
						}
					}
					else {
						donationStr = temp2[1].trim();
					}
				}
			}
		}
	}
	
	/**
	 * check if donator is valid to be added to the record list
	 * @return true if the name and birthday is valid, false if both is invalid
	 */
	public boolean isValidToAdd() {
		//System.out.println("\nvalidate donator to add\nname: " + validName() + "\nbirthday: " + validBirthday() + "\n");
		return validName() && validBirthday();
	}
	
	/**
	 * check if donator's name is valid
	 * @return true if the name is valid, false if the name is invalid or null
	 */
	private boolean validName() {
		if (name != null && name.matches("^[\\p{L}\\s'.-]+$")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * check if donator's birthday is valid
	 * @return true if the birthday is valid, false if the birthday is invalid or null
	 */
	private boolean validBirthday() {
		//System.out.println(birthday + " " + birthday.isValid());
		if (birthday != null && birthday.isValid()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * check if donator's phone is 8 digits and all numeric
	 * @return true if the phone number is valid, false if the phone number is invalid or null
	 */
	private boolean validPhone() {
		if (phone != null) {
			if(phone.matches("[0-9]{8}")) {
				return true;
			}
			else {
				phone = null;
				return false;
			}
		}
		else {
			phone = null;
			return false;
		}
	}
	
	/**
	 * check if donator's address is valid (contains state)
	 * @return true if the address is valid, false if the address is invalid or null
	 */
	private boolean validAddress() {
		if(address != null) {
			if(address.isValid()) {
				return true;
			}
			else {
				address = null;
				return false;
			}
		}
		else {
			address = null;
			return false;
		}
	}
	
	/**
	 * check if donator's postcode is valid (4 digits number)
	 * @return true if the postcode is valid, false if the postcode is invalid or null
	 */
	private boolean validPostcode() {
		if(postcode != null) {
			if(postcode.matches("[0-9]{4}+")) {
				return true;
			}
			else {
				postcode = null;
				return false;
			}
		}
		else {
			postcode = null;
			return false;
		}
	}

	/**
	 * Stringify donator's information
	 * @return string of donator's information
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name " + name + "\n");
		sb.append(System.getProperty("line.separator"));
		sb.append("birthday " + birthday);
		if(phone != null && validPhone()) {
			sb.append(System.getProperty("line.separator"));
			sb.append("phone " + phone);
		}
		if(address != null && validAddress()) {
			sb.append(System.getProperty("line.separator"));
			sb.append("address " + address);
		}
		if(postcode != null && validPostcode()) {
			sb.append(System.getProperty("line.separator"));
			sb.append("postcode " + postcode);
		}
		if(recipientStr != null) {
			sb.append(System.getProperty("line.separator"));
			sb.append("recipient " + recipientStr);
		}
		if(donationStr != null) {
			sb.append(System.getProperty("line.separator"));
			sb.append("donation " + donationStr);
		}
		return sb.toString();
		//return String.format("name %s%nbirthday %s%nphone %s%naddress %s%npostcode %s%nrecipient %s%ndonation %s", name, birthday, phone, address, postcode, recipientStr, donationStr);
		//return String.format("name %s%nbirthday %s%nphone %s%npostcode %s", name, birthday, phone, postcode);
	}
	
	// getters
	
	/**
	 * get donator's name
	 * @return name is donator's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get donator's birthday
	 * @return birthday is donator's birthday
	 */
	public CustomDate getBirthday() {
		return birthday;
	}
	
	/**
	 * get donator's phone number
	 * @return phone is donator's phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * get donator's address
	 * @return address is donator's address;
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * get donator's postcode
	 * @return postcode is donator's postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	
	/**
	 * get donator's recipient list
	 * @return recipient list string
	 */
	public String getRecipient() {
		return recipientStr;
	}
	
	/**
	 * get donator's donation list
	 * @return donation list string
	 */
	public String getDonation() {
		return donationStr;
	}
	
	// setters
	
	/**
	 * set donator's new name
	 * @param name the new donator's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * set donator's new birthday
	 * @param birthday the new donator's birthday
	 */
	public void setBirthday(CustomDate birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * set donator's new phone number
	 * @param phone the new donator's phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * set donator's new address
	 * @param address the new donator's address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * set donator's new postcode
	 * @param postcode the new donator's postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	/**
	 * set donator's new recipient list
	 * @param recipientStr the new string of donator's recipient list
	 */
	public void setRecipient(String recipientStr) {
		this.recipientStr = recipientStr;
	}
	
	/**
	 * set donator's new donation list
	 * @param donationStr the new string of donator's donation list
	 */
	public void setDonation(String donationStr) {
		this.donationStr = donationStr;
	}
}
