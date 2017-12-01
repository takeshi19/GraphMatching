/**
 * class Resource
 * 
 * DO NOT EDIT THIS CLASS
 * 
 * This class is for providing string resources 
 * for the VolunteerMatch program.
 *
 * You must use these strings to print out any messages
 * in your program.
 * 
 * See the instruction on each methods and use appropriate strings.
 * 
 * NOTE : THE STRINGS MIGHT CONTAIN A NEW LINE CHARACTER AT THE END OF IT
 *        MIND THIS WHEN YOU USE THEM AND DO COMPARE TO PROVIDED SAMPLE RUNS
 *        FOR ADDITIONAL NEW LINES
 */
public class Resource {
	
	public static final String STR_MENU_MAIN = 
			"=============================\n"
			+ "[ Main Menu ]\n" 
			+ "=============================\n"
			+ "1. Load EVM from a file\n"
			+ "2. Save EVM to a file\n"
			+ "3. Display all events and corresponding matches\n"
			+ "4. Display all volunteers and corresponding matches\n"
			+ "5. Create a match\n"
			+ "6. Remove a match\n"
			+ "7. Add a volunteer\n"
			+ "8. Remove a volunteer\n"
			+ "9. Quit\n"
			+ ">";
	public static final String STR_ERROR_MENU_INPUT = "ERROR: Input should be 1~9\n";
	
	// ----- FILE OPERATION MSG -----
	public static final String STR_INPUT_FILEPATH = "Enter filepath : ";
	
	public static final String STR_ERROR_READ_FILE_PRINT_FORMAT = "ERROR: Cannot read file - %s\n";
	public static final String STR_ERROR_WRITE_FILE_PRINT_FORMAT = "ERROR: Cannot write file - %s\n";

	// ----- OPERATION MSG -----
	public static final String STR_INPUT_MATCH_EVENT_NAME = "Which event : ";
	public static final String STR_INPUT_MATCH_VOLUNTEER_NAME = "Which volunteer : ";

	public static final String STR_ERROR_MATCH_CREATE_FAILED_PRINT_FORMAT = "ERROR: Failed to create the match (Event:%s,Volunteer:%s)\n";
	public static final String STR_ERROR_MATCH_REMOVE_FAILED_PRINT_FORMAT = "ERROR: Failed to remove the match (Event:%s,Volunteer:%s)\n";
	
	public static final String STR_INPUT_VOLUNTEER_NAME = "Volunteer name : ";
	public static final String STR_INPUT_VOLUNTEER_AVAILABLE_DATE = "Volunteer's available date (1~30,1~30,...) : ";

	public static final String STR_ERROR_VOLUNTEER_CREATE_FAILED_PRINT_FORMAT = "ERROR: Failed to create the volunteer (%s)\n";
	public static final String STR_ERROR_VOLUNTEER_REMOVE_FAILED_PRINT_FORMAT = "ERROR: Failed to remove the volunteer (%s)\n";	
	

	// ----- DISPLAY MSG -----
	public static final String STR_DISPLAY_ALL_VOLUNTEERS_PRINT_FORMAT = 
			"Display All Volunteers [%d volunteers]\n"
			+ "------------------------\n";
	public static final String STR_VOLUNTEER_PRINT_FORMAT = 
			"-Name: %s\n"
			+ " Available: %s\n"
			+ " Matched Event(s):\n";
	public static final String STR_VOLUNTEER_EVENT_PRINT_FORMAT = 
			" Event: %s\t Date: %d\n";

	public static final String STR_DISPLAY_ALL_EVENTS_PRINT_FORMAT = 
			"Display All Events [%d events]\n"
			+ "------------------------\n";
	public static final String STR_DISPLAY_EVENT_PRINT_FORMAT = 
			"-Name: %s\n"
			+ " Date: %d\n"
			+ " Maximum number of volunteers: %d\n"
			+ " Matched Volunteer(s):\n";
	public static final String STR_DISPLAY_EVENT_VOLUNTEERS_PRINT_FORMAT = 
			" %d. %s\n";
	
	public static final String STR_DISPLAY_NO_MATCHES = " No match yet.\n";

	public static final String STR_ERROR_DISPLAY_VOLUNTEER_FAILED = "There is no volunteer yet.\n";
	public static final String STR_ERROR_DISPLAY_EVENT_FAILED = "There is no event yet.\n";
}
