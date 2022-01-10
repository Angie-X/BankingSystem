import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileIntInput {

	public static void main(String[] args) throws IOException {
		FileInputStream fileByteStream = null; // file input stream
		Scanner inFS = null; // scanner object
		int fileNum1 = 0; // data value from file
		int fileNum2 = 0;
		int fileNum3 = 0;

		// try to open file
		System.out.println("Opening the file myfile.txt");
		fileByteStream = new FileInputStream("myfile.txt");
		inFS = new Scanner(fileByteStream);

		// File is open and valid if we got this far (otherwise exception thrown)
		// myfile.txt should contain two integers, else problems
		System.out.println("reading three integers.");
		fileNum1 = inFS.nextInt();
		fileNum2 = inFS.nextInt();
		fileNum3 = inFS.nextInt();

		// Output values read from file

		System.out.println("num1: " + fileNum1);
		System.out.println("num2: " + fileNum2);
		System.out.println("num3: " + fileNum3);
		System.out.println("num1+num2+num3: " + (fileNum1 + fileNum2 + fileNum3));

		// Done with file, so try to close it
		System.out.println("Closing file myfile.txt.");
		fileByteStream.close(); // close() may throw IOException if fails

		return;

	}

}
