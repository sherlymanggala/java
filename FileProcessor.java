package DM19S1;

import java.util.*;
import java.io.*;

public class FileProcessor {
	private File recordFile;
	private File instructionFile;
	private File resultFile;
	private File reportFile;
	private Record record;
	
	/**
	 * FileProcessor constructor
	 * @param s the command/argument line from cmd
	 */
	public FileProcessor(String[] s) {
		recordFile = new File(s[0]);
		instructionFile = new File(s[1]);
		resultFile = new File(s[2]);
		reportFile = new File(s[3]);
		record = new Record();
	}
	
	/**
	 * read the record/member file method
	 */
	public void readRecord() {
		try {
			Scanner scan = new Scanner(recordFile);
			String temp = "";
			boolean foundAddress = false;
			while(scan.hasNextLine()) {
				String member = scan.nextLine();
				Scanner sc = new Scanner(member);
				String keyword, param;
				if(sc.hasNext()) {
					keyword = sc.next();
					
					if(keyword.equalsIgnoreCase("address")) {
						foundAddress = true;
						if(sc.hasNextLine()) {
							param = sc.nextLine();
							temp = temp + "; " + keyword.toLowerCase() + " " + param;
						}
					}
					
					else if(keyword.equalsIgnoreCase("name")
							|| keyword.equalsIgnoreCase("birthday")
							|| keyword.equalsIgnoreCase("postcode")
							|| keyword.equalsIgnoreCase("phone")
							|| keyword.equalsIgnoreCase("recipient")
							|| keyword.equalsIgnoreCase("donation")) {
						foundAddress = false;
						if(sc.hasNextLine()) {
							param = sc.nextLine();						
							
							temp = temp + "; " + keyword.toLowerCase() + " " + param;
						}
					}
					
					// if found extended address in a new line
					if(!keyword.equalsIgnoreCase("address") 
							&& !keyword.equalsIgnoreCase("name")
							&& !keyword.equalsIgnoreCase("birthday")
							&& !keyword.equalsIgnoreCase("postcode")
							&& !keyword.equalsIgnoreCase("phone")
							&& !keyword.equalsIgnoreCase("recipient")
							&& !keyword.equalsIgnoreCase("donation")
							&& foundAddress) {
						temp = temp + " " + keyword;
					}
				}
				
				if(member.equals("") || !scan.hasNextLine()) {
					record.addDonator(temp);
					temp = "";
				}
				sc.close();
			} 
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read the instruction file
	 */
	public void readInstruction() {
		try {
			Scanner scan = new Scanner(instructionFile);
			while(scan.hasNextLine()) {
				String instruction = scan.nextLine();
				Scanner sc = new Scanner(instruction);
				String keyword, param;
				if(sc.hasNext()) {
					keyword = sc.next();
					if(sc.hasNextLine()) {
						param = sc.nextLine();
						if(keyword.equalsIgnoreCase("update")) {
							record.updateDonator(param);
						}
						else if (keyword.equalsIgnoreCase("delete")) {
							record.deleteDonator(param);
						}
						else if (keyword.equalsIgnoreCase("donate")) {
							record.donateDonator(param);
						}
						else if (keyword.equalsIgnoreCase("query")) {
							record.queryDonator(param);
						}
					}
				}
				sc.close();
			}
			scan.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write / save the record list to result file
	 */
	public void saveResult() {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(resultFile));
			out.println(record.resultToString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write / save the query and report to report file
	 */
	public void saveReport() {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(reportFile));
			out.println(record.reportToString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
