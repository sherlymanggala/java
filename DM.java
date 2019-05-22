package DM19S1;

/** COMP9103 - Software Development in Java - DM System
 * Main Class
 * @author Sherly Manggala
 * UniKey sman4740
 * SID 490375332
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
