import java.io.BufferedReader;
import java.io.File; // java.io.file*
import java.io.FileReader;
import java.io.IOException;

public class ReadALine {
	static boolean debug = true;

	public static void main(String[] args) {
		try {
			File file = new File("customerInfor1.txt"); // create an file object for File class(system predefined class)
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			Profile profileArray[] = new Profile[5];// Profile [] profileArray = new Profile[5]
			while ((line = bufferedReader.readLine()) != null) {
				Profile newProfile = new Profile();
				String[] theData = line.split(",");
				String firstName = theData[2];
				String lastName = theData[3];
				String password = theData[1];
				String address = theData[4];
				String userName = theData[0];

				if (debug) {
					System.out.println("firstName is " + firstName);
					System.out.println("lastName is " + lastName);
				}
				newProfile.setUserName(userName);
				newProfile.setPassword(password);
				newProfile.setFirstName(firstName);
				newProfile.setLastName(lastName);
				newProfile.setAddress(address);

				stringBuffer.append(line);
				stringBuffer.append("\n");

				if (debug) {
					System.out.println("Contents of file:");
					System.out.println(stringBuffer.toString());
				}

			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
