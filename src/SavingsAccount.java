import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This is a method that will read and write to the user's saving account file
 * update their account balance after the user do deposit or withdraw, and save
 * the record of the changes.
 */

public class SavingsAccount {

	double numBalance;
	double numDeposit;
	double numWithdraw;
	Profile theProfile;
	String userName;
	String fileName;
 
	/**
	 * this constructor pass in profile and gets userName and fileName
	 * @param profile
	 *        profile of the customer
	 */
	public SavingsAccount(Profile profile) {
		this.theProfile = profile;
		String str = "saving.txt";
		userName = theProfile.getUserName();
		fileName = userName + str;
	}
	
	/**
	 * Gets file name.
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * This method reads the last line of the file 
	 * @return numBalance 
	 *         number of Balance
	 */
	public double getBalance() {
		String line;
		try {
			File file = new File(fileName);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				String[] theData = line.split("\t");
				String balanceData = theData[4];
				numBalance = Double.parseDouble(balanceData);
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

		return numBalance;
	}

	
	/**
	 * this method get date every time the user make changes
	 * and return theDate as a string
	 * @return theDate
	 *         the date as a string
	 */
	public String getDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String theDate = formatter.format(date);
		return theDate;
	}

	/**
	 * this method update the curBalance after the user deposit
	 * adds up a new line and save it every time the user make changes
	 * @param accountFileName 
	 *        account file name
	 * @param deposit 
	 *        the number of deposit
	 * @param balance
	 *        account balance
	 * @param thedate
	 *        current date
	 * @throws IOException when input or output exception occurred
	 */
	
	public void doDeposit(String accountFileName, double deposit, double balance, String thedate) throws IOException {

		String theBalance = Double.toString(balance);
		String theDeposit = Double.toString(deposit);

		// read all records from a account file.
		List<String[]> recordList = readAccount(accountFileName);

		// create a adding record
		String date;
		date = getDate();

		String[] recordAdd = new String[5];
		recordAdd[0] = date;
		recordAdd[1] = "deposit";
		recordAdd[2] = theDeposit;
		recordAdd[3] = "balance";
		recordAdd[4] = theBalance;

		// add the record to this account
		recordList.add(recordAdd);

		// save records to the account file.
		saveAccount(accountFileName, recordList);
	}

	
	/**
	 * this method update the curBalance after the user withdraw
	 * adds up a new line and save it every time the user make changes
	 * @param accountFileName
	 *        account file name
	 * @param withdraw 
	 *        number of withdraw
	 * @param balance
	 *        number of account balance
	 * @param thedate
	 *        current date
	 * @throws IOException when input or output exception occurred    
	 */
	public void doWithdraw(String accountFileName, double withdraw, double balance, String thedate) throws IOException {

		String theBalance = Double.toString(balance);
		String theWithdraw = Double.toString(withdraw);

		// read all records from a account file.
		List<String[]> recordList = readAccount(accountFileName);

		// create a adding record
		String date;
		date = getDate();

		String[] recordAdd = new String[5];
		recordAdd[0] = date;
		recordAdd[1] = "withdraw";
		recordAdd[2] = theWithdraw;
		recordAdd[3] = "balance";
		recordAdd[4] = theBalance;

		// add the record to this account
		recordList.add(recordAdd);

		// save records to the account file.
		saveAccount(accountFileName, recordList);
	}

	/**
	 * this method read account info from a txt file.
	 * @param accountFileName 
	 *        account file name
	 * @return recordList
	 *         list string of the record
	 * @throws IOException when input or output exception occurred
	 */
	
	private static List<String[]> readAccount(String accountFileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(accountFileName));

		List<String[]> recordList = new ArrayList<String[]>();
		// read a line text
		String line = br.readLine();
		while (line != null) {
			// split the line into pieces by Tab
			String[] record = line.split("\t");
			recordList.add(record);
			line = br.readLine();
		}

		br.close();
		return recordList;
	}
	
	/**
	 * this method save a new line to the txt file
	 * @param accountFileName 
	 *        account file name
	 * @param recordList
	 * @throws IOException
	 */
	private static void saveAccount(String accountFileName, List<String[]> recordList) throws IOException {
		FileWriter fw = new FileWriter(accountFileName);

		for (int i = 0; i < recordList.size(); i++) {
			String[] record = recordList.get(i);
			String line = record[0] + "\t" + record[1] + "\t" + record[2] + "\t" + record[3] + "\t" + record[4];
			fw.write(line);
			fw.write("\r\n");// move to next line.
		}

		fw.close();

	}

}
