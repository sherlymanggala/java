package DM19S1;

public class Donator {
	// instance fields
	private String name;
	private CustomDate birthday;
	private String phone;
	private String postcode;
	private Address address;
	private String recipientStr;
	private String donationStr;
	
	// constructor
	public Donator() {
		name = null;
		birthday = null;
		phone = null;
		postcode = null;
		address = null;
		recipientStr = null;
		donationStr = null;
	}
	
	// constructor
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
	
	public boolean isValidToAdd() {
		//System.out.println("\nvalidate donator to add\nname: " + validName() + "\nbirthday: " + validBirthday() + "\n");
		return validName() && validBirthday();
	}
	
	private boolean validName() {
		if (name != null && name.matches("^[\\p{L}\\s'.-]+$")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validBirthday() {
		//System.out.println(birthday + " " + birthday.isValid());
		if (birthday != null && birthday.isValid()) {
			return true;
		}
		else {
			return false;
		}
	}
	
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
	public String getName() {
		return name;
	}
	
	public CustomDate getBirthday() {
		return birthday;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public String getRecipient() {
		return recipientStr;
	}
	
	public String getDonation() {
		return donationStr;
	}
	
	// setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBirthday(CustomDate birthday) {
		this.birthday = birthday;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public void setRecipient(String recipientStr) {
		this.recipientStr = recipientStr;
	}
	
	public void setDonation(String donationStr) {
		this.donationStr = donationStr;
	}
}


