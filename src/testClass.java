import java.io.FileNotFoundException;
import java.util.ArrayList;

public class testClass {
	public static void main (String[] args) {
		EventManager mgmt = new EventManager();
		ArrayList<Integer> dates = new ArrayList<Integer>();
		dates.add(19);
		
		//**Testing if the parsing is correct for the files in eventManager.**
		try {
			VolunteerMatch.readFromFile(mgmt, "testFile.txt");			
		} catch (FileNotFoundException e) {
			System.out.println("File not found from given file path");
		}
//		String[] numbers = {"1", "11", "13", "-22"};
//		
//		//FIXME we have bugs in the EventManager
//		//-Solved.
//		if (mgmt.addVolunteer("doggod", numbers)) {
//			System.out.println("addVolunteer() returned true.");
//		}
//		else {
//			System.out.println("addVolunteer() returned false.");
//		}
	}
}
