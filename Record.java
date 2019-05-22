package DM19S1;

/** Record Class
 * A class that maintains donator's record
 * @author sherly
 */

import java.util.*;

public class Record {
	private ArrayList<Donator> donator;
	private StringBuilder reportSb;
	
	/**
	 * Constructor
	 */
	public Record() {
		donator = new ArrayList<Donator>();
		reportSb = new StringBuilder();
	}
	
	/**
	 * add donator method
	 * @param s the 1 line string contains donator's information to be added
	 */
	public void addDonator(String s) {
		Donator d = new Donator(s);
		if(d.isValidToAdd()) {
			donator.add(d);
		}
	}
	
	/**
	 * update donator method
	 * @param s the 1 line string contains donator's new information to be updated
	 */
	public void updateDonator(String s) {
		String[] temp = s.split(";");
		for(int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
		}
		
		reportSb.append("----update " + temp[0] + "----");
		reportSb.append(System.getProperty("line.separator"));
		
		Donator newDonator = new Donator(s);
		boolean matchDonator = false;
		if(newDonator.isValidToAdd()) {
			String tempName = newDonator.getName();
			CustomDate tempBirthday = newDonator.getBirthday();
			for (int i = 0; i < donator.size(); i++) {
				if(donator.get(i).getName().equalsIgnoreCase(tempName) && donator.get(i).getBirthday().toString().equals(tempBirthday.toString())) {
					if(newDonator.getPhone() != null) {
						donator.get(i).setPhone(newDonator.getPhone());
					}
					if(newDonator.getPostcode() != null) {
						donator.get(i).setPostcode(newDonator.getPostcode());
					}
					if(newDonator.getAddress() != null) {
						donator.get(i).setAddress(newDonator.getAddress());
					}
					matchDonator = true;
					
					reportSb.append("Record updated!");
					reportSb.append(System.getProperty("line.separator"));
					reportSb.append("-------------------------");
					reportSb.append(System.getProperty("line.separator"));
					reportSb.append(System.getProperty("line.separator"));
					
					break;
				}
				else {
					matchDonator = false;
				}
			}
			if(!matchDonator && newDonator.isValidToAdd()) {
				reportSb.append("Record added!");
				reportSb.append(System.getProperty("line.separator"));
				reportSb.append("-------------------------");
				reportSb.append(System.getProperty("line.separator"));
				reportSb.append(System.getProperty("line.separator"));
				donator.add(newDonator);
			}
		}
		
		else {
			reportSb.append("Invalid instruction (not supported field)!");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append("-------------------------");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append(System.getProperty("line.separator"));
		}
	}
	
	/**
	 * delete donator method
	 * @param s the 1 line string to be check
	 * search for donator's name and birthday to be removed
	 */
	public void deleteDonator(String s) {
		boolean donatorDeleted = false;
		String[] temp = s.split(";");
		for(int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
		}
		
		reportSb.append("----delete " + temp[0] + "----");
		reportSb.append(System.getProperty("line.separator"));
		
		String tempName = temp[0];
		CustomDate tempBirthday = new CustomDate(temp[1]);
		
		for(int n = 0; n < donator.size(); n++) {
			if(tempBirthday.isValid()) {
				if(donator.get(n).getBirthday().toString().equals(tempBirthday.toString()) 
						&& donator.get(n).getName().equalsIgnoreCase(tempName)) {
					donator.remove(n);
					donatorDeleted = true;
					break;
				}
				else {
					donatorDeleted = false;
				}
			}
		}
		
		if(donatorDeleted) {
			reportSb.append("Record deleted!");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append("-------------------------");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append(System.getProperty("line.separator"));
		}
		else {
			reportSb.append("Record not found!");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append("-------------------------");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append(System.getProperty("line.separator"));
		}
	}
	
	/**
	 * donate method for donator to do donation, checks the donator's name and birthday first
	 * then update / add recipient and donation
	 * @param s the string line from file
	 */
	public void donateDonator(String s) {
		String[] temp = s.split(";");
		int count = 0;
		
		// temp[0] = name
		// temp[1] = birthday
		// temp[2]~ = recipient, donation
		for(int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
		}
		
		if(temp.length > 2) {
			boolean donatorFound = false;
			String tempName = temp[0];
			CustomDate tempBirthday = new CustomDate(temp[1]);
			
			for(int i = 2; i < temp.length; i++) {
				if(!temp[i].equals("")) {
					String[] temp2 = temp[i].split(",");
					String tempRecipient = temp2[0].trim();
					int tempDonation = Integer.parseInt(temp2[1].trim());
					
					// check every donator
					for(int j = 0; j < donator.size(); j++) {
						
						// check if there's record with the given name and birthday
						if(donator.get(j).getName().equalsIgnoreCase(tempName)
								&& donator.get(j).getBirthday().toString().equals(tempBirthday.toString())) {
							
							String recipientStr = "";
							String donationStr = "";
							
							if(donator.get(j).getRecipient() != null) {
								String[] recipientList = donator.get(j).getRecipient().trim().split(",");
								String[] donationList = donator.get(j).getDonation().trim().split(",");
								boolean foundRecipient = false;
								
								// check for the recipient list
								for(int k = 0; k < recipientList.length; k++) {
									if (recipientList[k].trim().equalsIgnoreCase(tempRecipient)) {
										int donation = Integer.parseInt(donationList[k].trim());
										donation += tempDonation;
										donationList[k] = Integer.toString(donation);
										foundRecipient = true;
									}
								}
								
								recipientStr = recipientStr + recipientList[0];
								donationStr = donationStr + donationList[0];
								for(int k = 1; k < recipientList.length; k++) {
									recipientStr = recipientStr + ", " + recipientList[k].trim();
									donationStr = donationStr + ", " + donationList[k].trim();
								}
								
								// if there's no same recipient as given, add new recipient into the list
								if(!foundRecipient) {
									recipientStr = recipientStr + ", " + tempRecipient;
									donationStr = donationStr + ", " + Integer.toString(tempDonation);
								}
								
								count++;
								donator.get(j).setRecipient(recipientStr);
								donator.get(j).setDonation(donationStr);
							}
							else {
								count++;
								recipientStr = tempRecipient;
								donationStr = Integer.toString(tempDonation);
								donator.get(j).setRecipient(recipientStr);
								donator.get(j).setDonation(donationStr);
							}
							
							donatorFound = true;
						}
					}
				}
			}
			
			if(donatorFound) {
				reportSb.append("----donate " + temp[0] + "----");
				reportSb.append(System.getProperty("line.separator"));
				reportSb.append(count + " new donation record(s) for " + tempName + " birthday " + tempBirthday);
				reportSb.append(System.getProperty("line.separator"));
				reportSb.append("-------------------------");
				reportSb.append(System.getProperty("line.separator"));
				reportSb.append(System.getProperty("line.separator"));
			}
		}
	}
	
	/**
	 * Query the record method, the method consists 3 main keywords: "top", "name", and "recipients"
	 * @param s the string that contains keyword and param
	 */
	public void queryDonator(String s) {
		String[] temp = s.split("\\s");
		for(int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
		}
		
		if(temp[1].equalsIgnoreCase("name")) { // I sincerely don't know why temp[0] is blank space
			int count = 0;
			String tempName = "";
			tempName = temp[2];
			if(temp.length > 3) {
				for(int i = 3; i < temp.length; i++) {
					tempName = tempName + " " + temp[i];
				}
			}
			
			reportSb.append("----query name " + tempName + "----");
			reportSb.append(System.getProperty("line.separator"));
			
			for(int i = 0; i < donator.size(); i++) {
				if(donator.get(i).getName().equalsIgnoreCase(tempName)) {
					count++;
				}
			}
			
			if(count > 0) {
				reportSb.append(count + " record(s) found:");
				reportSb.append(System.getProperty("line.separator"));
				
				if (count == 1) {
					for(int i = 0; i < donator.size(); i++) {
						if(donator.get(i).getName().equalsIgnoreCase(tempName)) {
							reportSb.append(donator.get(i).toString());
							reportSb.append(System.getProperty("line.separator"));
							reportSb.append("-------------------------");
							reportSb.append(System.getProperty("line.separator"));
							reportSb.append(System.getProperty("line.separator"));
						}
					}
				}
				else {
					// compare birthday
					for(int i = 0; i < donator.size(); i++) {
						for(int j = i+1; j < donator.size(); j++) {
							if(donator.get(i).getName().equalsIgnoreCase(tempName)) {
								if(donator.get(j).getName().equalsIgnoreCase(tempName)) {
									String[] tempBoD1 = donator.get(i).getBirthday().toString().split("-");
									String[] tempBoD2 = donator.get(j).getBirthday().toString().split("-");
									
									if(tempBoD1.length == 3 && tempBoD2.length == 3) {
										
										// compare birth year
										if(Integer.parseInt(tempBoD1[2]) < Integer.parseInt(tempBoD2[2])) {
											donator.add(i, donator.get(j));
											donator.remove(j+1);
										}
										else if (Integer.parseInt(tempBoD1[2]) == Integer.parseInt(tempBoD2[2])) {
											if(Integer.parseInt(tempBoD1[1]) < Integer.parseInt(tempBoD2[1])) {
												donator.add(i, donator.get(j));
												donator.remove(j+1);
											}
											else if (Integer.parseInt(tempBoD1[1]) == Integer.parseInt(tempBoD2[1])) {
												if(Integer.parseInt(tempBoD1[0]) < Integer.parseInt(tempBoD2[0])) {
													donator.add(i, donator.get(j));
													donator.remove(j+1);
												}
											}
										}
									}
								}
							}
						}
					}
					
					for(int i = 0; i < donator.size(); i++) {
						if(donator.get(i).getName().equalsIgnoreCase(tempName)) {
							if(count > 1) {
								reportSb.append(donator.get(i).toString());
								reportSb.append(System.getProperty("line.separator"));
								reportSb.append(System.getProperty("line.separator"));
								count--;
							}
							else if (count == 1) {
								reportSb.append(donator.get(i).toString());
								reportSb.append(System.getProperty("line.separator"));
							}
						}
					}
					
					reportSb.append("-------------------------");
					reportSb.append(System.getProperty("line.separator"));
					reportSb.append(System.getProperty("line.separator"));
				}
			}
		}
		
		else if (temp[1].equalsIgnoreCase("top")) {
			int n = Integer.parseInt(temp[2]);
			String[][] queryTop = new String[donator.size()][2];
			int[] queryTopDonation = new int[donator.size()];
			
			for(int i = 0; i < donator.size(); i++) {
				queryTop[i][0] = donator.get(i).getName();
				queryTop[i][1] = donator.get(i).getBirthday().toString();
				
				if(donator.get(i).getDonation() != null) {
					String[] tempDonation = donator.get(i).getDonation().split(",");
					
					if(tempDonation.length == 1) {
						queryTopDonation[i] = Integer.parseInt(tempDonation[0].trim());
					}
					
					else {
						for(int j = 0; j < tempDonation.length; j++) {
							queryTopDonation[i] += Integer.parseInt(tempDonation[j].trim());
						}
					}
				}
			}
			
			// Do Bubble Sort (Descending order)
			for(int i = 0; i < donator.size() - 1; i++) {
				//System.out.println(queryTop[i][0] + "; " + queryTop[i][1] + "; " + queryTopDonation[i]);
				for(int j = 0; j < donator.size() - i - 1; j++) {
					if(queryTopDonation[j] < queryTopDonation[j+1]) {
						int tempSwapDonation = queryTopDonation[j];
						queryTopDonation[j] = queryTopDonation[j+1];
						queryTopDonation[j+1] = tempSwapDonation;
						
						String tempSwapName = queryTop[j][0];
						queryTop[j][0] = queryTop[j+1][0];
						queryTop[j+1][0] = tempSwapName;
						
						String tempSwapBirthday = queryTop[j][1];
						queryTop[j][1] = queryTop[j+1][1];
						queryTop[j+1][1] = tempSwapBirthday;
					}
					
					// compare donator's name if there are multiple donators have the same amount of donations
					else if (queryTopDonation[j] == queryTopDonation[j+1]) {
						if(queryTop[j][0].compareToIgnoreCase(queryTop[j+1][0]) > 0) {
							int tempSwapDonation = queryTopDonation[j];
							queryTopDonation[j] = queryTopDonation[j+1];
							queryTopDonation[j+1] = tempSwapDonation;
							
							String tempSwapName = queryTop[j][0];
							queryTop[j][0] = queryTop[j+1][0];
							queryTop[j+1][0] = tempSwapName;
							
							String tempSwapBirthday = queryTop[j][1];
							queryTop[j][1] = queryTop[j+1][1];
							queryTop[j+1][1] = tempSwapBirthday;
						}
					}
				}
			}
			reportSb.append("----query top " + n + "----");
			reportSb.append(System.getProperty("line.separator"));
			
			if(n == 1) {
				reportSb.append(queryTop[0][0] + "; " + queryTop[0][1] + "; " + queryTopDonation[0]);
			}
			
			else if (n > 1) {
				reportSb.append(queryTop[0][0] + "; " + queryTop[0][1] + "; " + queryTopDonation[0]);
				for(int i = 1; i < n; i++) {
					reportSb.append(System.getProperty("line.separator"));
					reportSb.append(queryTop[i][0] + "; " + queryTop[i][1] + "; " + queryTopDonation[i]);
				}
			}
			
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append("-------------------------");
			reportSb.append(System.getProperty("line.separator"));
			reportSb.append(System.getProperty("line.separator"));
		}
		
		else if (temp[1].equalsIgnoreCase("recipients")) {
			ArrayList<String> recipientList = new ArrayList<String>();
			ArrayList<Integer> donationList = new ArrayList<Integer>();
			ArrayList<String> postcodeList = new ArrayList<String>();
			for(int i = 0; i < donator.size(); i++) {
				if(donator.get(i).getRecipient() != null) {
					String[] tempRecipient = donator.get(i).getRecipient().split(",");
					String[] tempD = donator.get(i).getDonation().split(",");
					int[] tempDonation = new int[tempD.length];
					for(int j = 0; j < tempRecipient.length; j++) {
						tempRecipient[j] = tempRecipient[j].trim();
						tempDonation[j] = Integer.parseInt(tempD[j].trim());
					}
					
					if(recipientList.size() > 0) {
						for(int j = 0; j < tempRecipient.length; j++) {
							boolean recipientFound = false;
							for(int k = 0; k < recipientList.size(); k++) {
								if(tempRecipient[j].equalsIgnoreCase(recipientList.get(k))) {
									if(tempDonation[j] > donationList.get(k)) {
										donationList.set(k, tempDonation[j]);
										postcodeList.set(k, donator.get(i).getPostcode());
									}
									
									else if (tempDonation[j] == donationList.get(k)) {
										postcodeList.set(k, postcodeList.get(k) + ", " + donator.get(i).getPostcode());
									}
									
									recipientFound = true;
								}
							}
							
							if(!recipientFound) {
								recipientList.add(tempRecipient[j]);
								donationList.add(tempDonation[j]);
								postcodeList.add(donator.get(i).getPostcode());
							}
						}
					}
					else {
						for(int j = 0; j < tempRecipient.length; j++) {
							recipientList.add(tempRecipient[j]);
							donationList.add(tempDonation[j]);
							postcodeList.add(donator.get(i).getPostcode());
						}
					}
				}
			}
			
			reportSb.append("----query recipients----");
			reportSb.append(System.getProperty("line.separator"));
			for(int i = 0; i < recipientList.size(); i++) {
				//System.out.println(recipientList.get(i) + ": " + donationList.get(i) + "; postcode " + postcodeList.get(i));
				reportSb.append(recipientList.get(i) + ": " + donationList.get(i) + "; postcode " + postcodeList.get(i));
				reportSb.append(System.getProperty("line.separator"));
			}
			reportSb.append("-------------------------");
		}
	}
	
	/**
	 * get record list
	 * @return donator the record of donators
	 */
	public ArrayList<Donator> getRecord() {
		return donator;
	}
	
	/**
	 * set record list method
	 * @param donator the new record of donators
	 */
	public void setRecord(ArrayList<Donator> donator) {
		this.donator = donator;
	}
	
	/**
	 * stringify result method
	 * @return string of donator's list to result
	 */
	public String resultToString() {
		StringBuilder sb = new StringBuilder();
		for(Donator d : donator) {
			sb.append(d.toString());
			sb.append(System.getProperty("line.separator"));
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	
	/**
	 * stringify report method
	 * @return string of reports
	 */
	public String reportToString() {
		return reportSb.toString();
	}
}