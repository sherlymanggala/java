package DM19S1;

/** Donator Management System (DMS)
 * COMP9103 - Software Development in Java
 * @author Sherly Manggala - sman4740
 * 2019
 */

public class DM {
	
	/**
	 * main class, read files, parse instruction, save result and report to files
	 * @param args read the command line
	 */
	public static void main(String[] args) {
		FileProcessor fp = new FileProcessor(args);
		fp.readRecord();
		fp.readInstruction();
		fp.saveResult();
		fp.saveReport();
	}
}
