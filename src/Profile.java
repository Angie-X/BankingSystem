import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * read the user name and password if the username and password match then allow
 * the user login the system and gets the firstName, lastName, userName and
 * password information.
 */

public class Profile {

	private String firstName; // stores user's firstName as a string
	private String lastName;
	private String address;
	private String userName;
	private String password;

	private boolean debug = false;

	/**
	 * get user's first name
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set user's first name with the string parameter
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get user's last name
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set user's last name with the string parameter
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * get user's address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set user's address with the string parameter
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * get user name
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * this method pass in three parameters, read customerInfor1 and get userName
	 * and password, then match the userName and password user typed in. If both
	 * match then login successfully, then get the user's userName, return boolean
	 * success.
	 * 
	 * @param fileName
	 *            file name
	 * @param userName
	 *            user name
	 * @param password
	 * @return success boolean that shows login successfully
	 */
	public boolean readProfileData(String fileName, String userName, String password) {
		boolean success = false;
		try {
			File file = new File("customerInfor1.txt"); // ?
			FileReader fileReader = new FileReader(file); // ?
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer(); // ?
			String line;

			while ((line = bufferedReader.readLine()) != null) { // Does this just check one line or check all the
																	// lines?
				String[] theData = line.split(",");
				String userNameData = theData[0];
				String passwordData = theData[1];
				if (userName.equals(userNameData) && password.equals(passwordData)) {
					success = true;
					String firstName = theData[2];
					String lastName = theData[3];
					String address = theData[4];
					if (debug) {
						System.out.println("firstName is " + firstName);
						System.out.println("lastName is " + lastName);
					}

					this.setUserName(theData[0]);
					this.setPassword("passwordData");
					this.setFirstName("firstName");
					this.setLastName("lastName");
					this.setAddress("address");
					break;
				}
			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return success;

	}

}
