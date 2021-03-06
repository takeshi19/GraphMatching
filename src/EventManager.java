/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          p5
// FILE:             EventManager.java
//
// TEAM:    N/A
// Authors: Manuel T. Gomez
// Author1: Manuel T. Gomez, gomez22@wisc.edu, gomez22, 005
// Author2: N/A
//
// ---------------- OTHER ASSISTANCE CREDITS 
// https://stackoverflow.com/questions/1532461/stringbuilder-vs-string
//-concatenation-in-tostring-in-java 
// --> This link helped me build the large strings in the toStringAllEvents()
//		method and the toStringAllVolunteers() method.
//  
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * EventManager manages a list of events and a separate list of volunteers.
 * 
 * IMPLEMENT THE METHODS OF THIS CLASS
 * 
 * It also provides methods for adding matches between events and volunteers
 * and for displaying the events and volunteers that exist.
 * 
 * An EventManager instance manages the list of events and volunteers.
 * 
 * <p> Bugs: N/A
 * @author Manuel T. Gomez
 */
public class EventManager {
	
	/** the list of events */
	private List<Event> eventList;

	/** the list of volunteers */
	private List<Volunteer> volunteerList;

	/**
	 * Constructor for an EventManager instance
	 */
	public EventManager(){
		this.eventList = new ArrayList<Event>();
		this.volunteerList = new ArrayList<Volunteer>();
	}
	
	/**
	 * Adds an event to the list of events or returns false if the details for the event are not valid.
	 * This maintains the event list in sorted order (sort is ascending by name only).
	 * 
	 * Tip: Collections.sort can be used after a new event is added.
	 * 
	 * The following conditions result in no event being added and false being returned
	 * <ul>
	 * <li>name is null or an empty string "".</li>
	 * <li>date is not an integer in range 1 to 30, inclusive.</li>
	 * <li>the event name already exists (duplicate event names are not allowed)</li>
	 * <li>the volunteer limit is less than one</li>
	 * </ul>
	 * 
	 * @param name the name of a new event
	 * @param dateStr the string for the date of this event
	 * @param limitStr the string for the volunteers limit in this event
	 * @return true if arguments have valid format and added event successfully, otherwise false
	 */
	public boolean addEvent(String name, String dateStr, String limitStr){
		int eventDate = Integer.parseInt(dateStr);		//Integer value of the date of the event.
		int maxVolunteers = Integer.parseInt(limitStr); //Integer value of the max number of volunteers for this event.
				
		if (!(findEvent(name) == null) || name == null || name.isEmpty()) {
			return false;	//If duplicate name, empty name, or null name, then return false.
		}
		if (!(1 <= eventDate) || !(eventDate <= 30)) {
			return false;	//If the event date is out of valid range, then return false.
		}
		if (maxVolunteers < 1) {
			return false; 	//Invalid amount of maximum volunteers (has to be at least one).
		}
		
		//**If valid event, then we add it to sorted list.**
		Event newEvent = new Event(name, eventDate, maxVolunteers);
		eventList.add(newEvent); 
		Collections.sort(eventList);

		return true;	//Return true after adding in the event list.
	}
		
	/**
	 * Adds a new volunteer to the list of volunteers or returns false.
	 * Maintains the volunteer list in sorted order.  
	 * 
	 * Tip: Collections.sort can be used after a new volunteer is added.
	 * 
	 * The following conditions result in no volunteer being added and false being returned.
	 * <ul>
	 * <li>Name must not be null or empty string</li>
	 * <li>Volunteer name must not be a duplicate.</li>
	 * </ul>
	 *
	 * @param name the name of a new volunteer
	 * @param availableDatesStrAry a String array that has date strings
	 * @return true if volunteer was successfully added to volunteer list. Else, return false.
	 */
	public boolean addVolunteer(String name, String[] availableDatesStrAry){		
		int date = 0;		//The separate integer values we got from file, put into availableDatesStrAry.
		ArrayList<Integer> intDates = new ArrayList<Integer>(0);  //The list of integers dates derived from String array.
	
		if (!(findVolunteer(name) == null) || name == null || name.isEmpty()) {
			return false;	//If duplicate name, empty name, or null name, then return false.
		}
		if (availableDatesStrAry.length != 0) { 	   //Helps for when dates are empty in file lines of volunteer.
			if (!availableDatesStrAry[0].equals("")) { //If array of dates is available for volunteer.
				/*
				 * Getting volunteer dates and putting them into an arrayList.
				 * For each string number, make into integer, add as date to intDates list.
				 */
				for (String str : availableDatesStrAry) { 
					try {
						date = Integer.parseInt(str.trim()); //Removing trailing/leading whitespace to avoid NumFormatExeception
					} catch (NumberFormatException e) {
						return false;      //If user tries entering in anything other than integers for dates of volunteer.
					}
					intDates.add(date); 		  
				}		
				//**Return false if there are any invalid date values (not in 1 -> 30 range), or duplicate dates.**
				for (int i = 0; i < intDates.size(); i++) {
					if (!(1 <= intDates.get(i)) || !(intDates.get(i) <= 30)) {
						return false;		//Return false for invalid dates.
					}
					for (int j = i + 1; j < intDates.size(); j++) { 
						if (intDates.get(i) == intDates.get(j)) {
							return false;	//Return false for duplicate dates.
						}
					}
				}
			}
		}
		//**If valid volunteer (even if no available dates), then we add it to sorted list.**
		Volunteer newVolunteer = new Volunteer(name, intDates);
		volunteerList.add(newVolunteer); 
		Collections.sort(volunteerList);
		
		return true;	//Return true after adding in the volunteer.
	}
	
	/** 
	 * USED ONLY IF AN EVENT NEEDS TO BE REMOVED WHILE READ FROM FILE
	 * 
	 * Iterates through the event list and removes the event if event exists. 
	 * This method must also remove all the event-volunteer matches corresponding to this event.
	 * 
	 * @param name the name of the event to be removed
	 * @return true if the event existed and removed successfully, otherwise false
	 */
	public boolean removeEvent(String name) {
		for (Event certainEvent : eventList) {
			if (certainEvent.getName().equalsIgnoreCase(name)) {
				eventList.remove(certainEvent);	//Removing a selected event from eventList if it exists.
				/*
				 * Removing all event-volunteer matches corresponding to removed event.
				 * Do not want volunteers to still be matched to a non-existent event.
				 */
				for (GraphNode volunteer : certainEvent.getAdjacentNodes()) {
					String volunteerName = volunteer.getName(); 		//Name of a matched volunteer to certainEvent.
					removeMatch(certainEvent.getName(), volunteerName); //Removing the relationship.
				}
				return true;		//Return true after a certain event was successfully removed from the list. 
			}
		}
		return false;				//If an event was never found in eventList, then return false.
	}
	
	/**
	 * Iterates through the volunteer list and removes the volunteer if volunteer exists. 
	 * Also removes all the event-volunteer matches corresponding to this volunteer
	 * 
	 * @param name the name of the volunteer to be removed
	 * @return true if volunteer existed and removed successfully, otherwise false
	 */
	public boolean removeVolunteer(String name){
		for (Volunteer certainVolunteer : volunteerList) {
			if (certainVolunteer.getName().equalsIgnoreCase(name)) {
				volunteerList.remove(certainVolunteer);	//Removing a selected volunteer from volunteerList if it exists.
				/*
				 * Removing all event-volunteer matches corresponding to removed volunteer.
				 * Do not want events to still be matched to a non-existent volunteer.
				 */
				for (GraphNode event : certainVolunteer.getAdjacentNodes()) {
					String eventName = event.getName(); 			    //Name of a matched event to certainVolunteer.
					removeMatch(eventName, certainVolunteer.getName()); //Removing the relationship.
				}
				return true;		//Return true after a certain volunteer was successfully removed from the list. 
			}
		}
		return false;				//If a volunteer was never found in volunteerList, then return false.
	}
	
	/**
	 * Given the event name, check if the event exists in the event list. 
	 * 
	 * @param name the name of the event to be found
	 * @return event if the event exists, otherwise null.
	 */
	public Event findEvent(String name){
		for (Event anEvent : eventList) {
			if (anEvent.getName().equalsIgnoreCase(name)) {
				return anEvent;
			}
		}
		return null;
	}
	
	/**
	 * Return the volunteer with the given name.
	 * 
	 * @param name the name of the volunteer
	 * @return volunteer if the volunteer exists, otherwise null.
	 */
	public Volunteer findVolunteer(String name){
		for (Volunteer aVolunteer: volunteerList) {
			if (aVolunteer.getName().equalsIgnoreCase(name)) {
				return aVolunteer;
			}
		}
		return null;
	}
	
	/**
	 * This method is used to create a match between an event and a volunteer.
	 * 
	 * <ol>
	 * <li>Find the event and the volunteer from their names.</li>
	 * <li>If either is null, return false.</li>
	 * <li>If event has not reached volunteer limit and volunteer has the event's date in its availability list, then
	 *     <ol><li>add the volunteer node to the event's adjacency list</li>
	 *     <li>add the event to the volunteer's list</li>
	 *     <li>set the availability date for the volunteer to false</li>
	 *     </ol>
	 * </li>
	 * <li>return true if all is well</li>
	 * </ol> 
	 * 
	 * @param eventName the name of an event to be matched to a volunteer
	 * @param volunteerName the name of a volunteer to be matched to a event
	 * @return true if the match is created, otherwise false.
	 */
	public boolean createMatch(String eventName, String volunteerName){
		Volunteer potentialVol; //The potential volunteer, created from volunteerName, for certain event.
		Event matchedEvent;	    //The event that this potential volunteer can be matched to.
		/*
		 * If eventName found in list of events, make a GraphNode from it to match it to volunteer,
		 * else return false.  
		 */
		matchedEvent = findEvent(eventName); 
		if (matchedEvent == null) {
			return false;	
		}
		
		/*
		 * If volunteerName found in list of volunteers, make a GraphNode from it to match it to volunteer,
		 * else return false.  
		 */
		potentialVol = findVolunteer(volunteerName);
		if (potentialVol == null) {
			return false;
		}
		
		//**If Volunteer isn't available on the date of the event, no match made.**
		if (!potentialVol.isAvailable(matchedEvent.getDate())) {
			return false;
		}
		
		//**If volunteer has been matched to any other event on the same date, no match made.**
		for (Event otherEvent : eventList) {
			if (!otherEvent.getName().equalsIgnoreCase(eventName)) {  
				if (matchedEvent.getDate() == otherEvent.getDate()) {
					if (potentialVol.hasEvent(otherEvent.getName())) {
						return false;
					}
				}		
			}
		}
		
		//**If all test conditions passed, then create a match if volunteer is below limit.**
		if (matchedEvent.isBelowLimit()) { 
			matchedEvent.addAdjacentNode(potentialVol);
			potentialVol.addAdjacentNode(matchedEvent); 
			return true; //Return true after match has been created.
		}
		
		return false; 	 //If event is over limit of volunteers, return false.
	}
	
	/**
	 * Given the event and volunteer, remove the match between them if it exists.
	 * Return true if the match is found and removed.
	 * 
	 * If a match is found:
	 * 
	 * <ul>
	 * <li>remove the volunteer from the event's volunteer list</li>
	 * <li>remove the event from volunteer's event list</li>
	 * <li>set the event's date to available for the volunteer</li>
	 * <li>return true if all is well</li>
	 * </ul>
	 * 
	 * @param eventName the name of an event to be removed from match
	 * @param volunteer the name of a volunteer to be removed from match
	 * @return true if the match existed and removed successfully, otherwise false.
	 */
	public boolean removeMatch(String eventName, String volunteerName){
		Event event = findEvent(eventName);					//The event GraphNode returned from eventName.
		Volunteer volunteer = findVolunteer(volunteerName);	//The volunteer GraphNode returned from volunteerName.
		
		//**Because our graph is undirected, we have to removed both corresponding matches between volunteer & event.**
		if (event == null || volunteer == null) {
			return false; //If either the event or the volunteer do not exist, return false.
		}
		//**Checking for adjacency between the 2 nodes before removal.**
		if (event.isAdjacentNode(volunteerName)) {
			event.removeAdjacentNode(volunteer);
			volunteer.removeAdjacentNode(event);
			volunteer.isAvailable(event.getDate()); //Volunteer is now free on that day that it used to have the event.
			System.out.println("Removed match yayyy-in EVM()");
			return true;
		}	
		else {
			System.out.println("Couldn't remove match- in EVM()");
			return false;	//Return false if not adjacent to each other.
		}
	}
	
	/**
	 * This method is used to display all the events along 
	 * with corresponding matches with the volunteers.
	 * Check sample files for exact format of the display.
	 * 
	 * Utilize formats defined in the Resource class
	 * to display in correct format.
	 * 
	 * Resource.STR_ERROR_DISPLAY_EVENT_FAILED
	 * Resource.STR_DISPLAY_ALL_EVENTS_PRINT_FORMAT
	 */
	public void displayAllEvents(){
		if (eventList.isEmpty()) {	//If the eventList empty, then there are no events to display- notify this to user. 
			System.out.println(Resource.STR_ERROR_DISPLAY_EVENT_FAILED);
			System.out.printf(Resource.STR_DISPLAY_ALL_EVENTS_PRINT_FORMAT, eventList.size());
		}
		else {						//Else, there are some events to display, and they are printed accordingly.
			System.out.printf(Resource.STR_DISPLAY_ALL_EVENTS_PRINT_FORMAT, eventList.size());
			
			for (Event eve : eventList) {
				System.out.print(eve.toString());
			}
		}
	}
	
	/**	 
	 * This method is used to display all the volunteers along 
	 * with corresponding matches with the events.
	 * Check sample files for exact format of the display.
	 * 
	 * Utilize formats defined in the resource file for 
	 * display in the correct format.
	 * 
	 * Resource.STR_ERROR_DISPLAY_VOLUNTEER_FAILED
	 * Resource.STR_DISPLAY_ALL_VOLUNTEERS_PRINT_FORMAT
	 */
	public void displayAllVolunteers(){
		if (volunteerList.isEmpty())  {//If volunteerList empty, then there are no vols to display- notify this to user.
			System.out.println(Resource.STR_ERROR_DISPLAY_VOLUNTEER_FAILED);
			System.out.printf(Resource.STR_DISPLAY_ALL_VOLUNTEERS_PRINT_FORMAT, volunteerList.size());
		}
		else {				          //Else, there are volunteers to display, and print them accordingly.
			System.out.printf(Resource.STR_DISPLAY_ALL_VOLUNTEERS_PRINT_FORMAT, volunteerList.size());

			for (Volunteer vol : volunteerList) {
				System.out.print(vol.toString());		
			}
		}
	}
	
	/**
	 * This is helper method to create a string for
	 * writing all the volunteers in a file.
	 * 
	 * (Example)
	 * <pre>
	 * v;Mingi;5,23,30
	 * v;Sonu;1,2,3,4,5
	 * </pre>
	 * 
	 * @return a single string object containing all the volunteers 
	 * in the format needed to be printed in the file.
	 */
	public String toStringAllVolunteers(){
		StringBuilder fileStringOfVols = new StringBuilder();	//Concatenating strings together into 1 large string.
		 
		for (Volunteer vol : volunteerList) {
			fileStringOfVols.append(vol.toFileString()).append("\n");
		}
		return fileStringOfVols.toString();
	}
	
	/**
	 * This is helper method to create a string for
	 * writing all the events in a file.
	 * 
	 * (Example)
	 * e;Field trip;7;10;
	 * e;Birthday;23;12;Mingi,Sonu
	 * 
	 * @return string containing all the events in the format
	 * needed to be printed in the file.
	 */
	public String toStringAllEvents(){
		StringBuilder fileStringOfEvents = new StringBuilder();	//Concatenating strings together into 1 large string.
		 
		for (Event eve : eventList) {
			fileStringOfEvents.append(eve.toFileString()).append("\n");
		}
		return fileStringOfEvents.toString();
	}
}
