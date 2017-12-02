
public class testClass {
	public static void main (String[] args) {
		EventManager mgmt = new EventManager();
		String[] numbers = {"1", "11", "13", "-22"};
		
		//FIXME we have bugs in the EventManager
		//-Solved.
		if (mgmt.addVolunteer("doggod", numbers)) {
			System.out.println("addVolunteer() returned true.");
		}
		else {
			System.out.println("addVolunteer() returned false.");
		}
	}
}
