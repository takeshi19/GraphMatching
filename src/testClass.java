import java.io.FileNotFoundException;

public class testClass {
	public static void main (String[] args) {
		EventManager mgmt = new EventManager();
//		VolunteerMatch vmatch = new VolunteerMatch();
		
		//**Testing if the parsing is correct for the files in eventManager.**
		try {
			VolunteerMatch.readFromFile(mgmt, "testFile.txt");
			//**Our file looks like this: 
			// v; Mingi; 1, 2, 3, 4, 23
			//	
			// e; Birth Day; 23; 1; Mingi
			 //-Program should read in Mingi as a volunteer, available on the 23rd (date), and skip the blank line
				//Then, the program should take e as an event, after Mingi added to volunteer list, and and 
					//MATCH Mingi to the Birth Day event on the 23rd.
			
			//FIXME: Problem, when we get to second line of the file, it is empty, and the program skips that line,
				//but doesn't go to the next line in the file, which is an event, and the program ends.
			//FIXME problem is not empty lines, it is the EVM.
			
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
