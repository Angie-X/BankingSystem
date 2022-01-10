import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteALine {

	public static void main(String[] args) {

		String str = "This is the final test";
		String fileName = "saving.txt";
		
		try {
			FileWriter filewriter = new FileWriter(fileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

			writer.write(str);
			

			writer.close();

		} catch (IOException ioe) {

		} 
		
		
		
			
		
		
		
	}

}
