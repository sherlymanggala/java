package DM19S1;

/** CustomDate Class
 * A class to check donator's birthday date pattern
 * @author sherly
 */

import java.util.*;
import java.text.*;

public class CustomDate {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private String dateStr;
	private Date date;
	
	/**
	 * Constructor
	 * @param d the birthday
	 */
	public CustomDate(String d) {
		dateStr = d;
		String[] temp;
		// check regex \\D means non-digits (it can be - or /), \\d means digits
		if(dateStr.matches("\\d+\\D\\d+\\D\\d+")) {
			temp = dateStr.split("\\D");
			if(temp.length == 3) {
				for(int i = 0; i < 2; ++i) {
					if(temp[i].length() < 2) {
						temp[i] = "0" + temp[i];
					}
				}
				
				dateStr = temp[0] + "-" + temp[1] + "-" + temp[2];
			}
		}
		
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			date = null;
		}
	}
	
	/**
	 * validation of the birthday
	 * @return true if birthday matches the format, false if birthday doesn't match the format
	 */
	public boolean isValid() {
		if(date != null) {
			// validate the date
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * parse the date into string type of data
	 */
	public String toString() {
		return dateFormat.format(date);
	}
	
	/**
	 * birthday or date getter
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
}