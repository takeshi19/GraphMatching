import java.util.Arrays;
import java.util.List;

/**
 * This is the Volunteer class. It extends GraphNode class. 
 * This class is used to store information about a volunteer and 
 * events that this volunteer has been matched (adjacent nodes).
 * 
 * DO NOT EDIT THIS CLASS
 */
public class Volunteer extends GraphNode {
	
	/** 
	 * A boolean array of size 30 corresponding to each date
	 *  from 1-30 (Note that date i corresponds to i-1 index).
	 *  Maintain this array to keep track of whether this volunteer is 
	 *  available on  given date or not
	 *  
	 *  */
	private boolean availableDatesAry[] = new boolean[30]; //initialized as false
	
	/**
	 * Constructor for the Volunteer.
	 * Create an instance of super class with name.
	 * Set availableDatesAry to true for availableDates.
	 * Throw IllegalArgumentException if duplicate dates are there in the list
	 * 
	 * @param name
	 * @param availableDates
	 */
	public Volunteer(String name, List<Integer> availableDates){
		super(name);
		for(int date : availableDates){
			if(!setAvailable(date)) {
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**
	 * Method to check whether volunteer is available on the given date.
	 * Throw IllegalArgumentException if dates are not in valid range.
	 * 
	 * @param date
	 * @return true is volunteer is available on this date, otherwise false
	 */
	public boolean isAvailable(int date){
		if(date<1 || date>30) throw new IllegalArgumentException();
		return availableDatesAry[date-1];
	}
	
	/**
	 * Method to set availableDatesAry to true for the given date
	 * 
	 * @param date
	 * @return true is operation is successful. Return false if availableDatesAry
	 * is already true for the given date.
	 */
	public boolean setAvailable(int date){
		if(!isAvailable(date)) {
			availableDatesAry[date-1] = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Method to set availableDatesAry to false for the given date
	 * 
	 * @return true is operation is successful. Return false if availableDatesAry
	 * is already false for the given date.
	 */
	public boolean setUnavailable(int date){
		if(isAvailable(date)) {
			availableDatesAry[date-1] = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Method to check if volunteer has the event node with the 
	 * given name as its adjacent node. 
	 * 
	 * @param name
	 * @return true if volunteer has the event node with the 
	 * given name as its adjacent node, false otherwise.
	 */
	public boolean hasEvent(String name){
		return isAdjacentNode(name);
	}

	/**
	 * Method to create string format for displaying volunteer 
	 * along with its matches to the events (adjacent nodes).
	 * 
	 * Note that while printing available dates for the volunteer, 
	 * current available dates are used, using information from 
	 * availableDatesAry.
	 * 
	 * Resource.STR_VOLUNTEER_PRINT_FORMAT
	 * Resource.STR_VOLUNTEER_EVENT_PRINT_FORMAT
	 * Resource.STR_DISPLAY_NO_MATCHES
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		
		StringBuilder availableDatesStrBuilder = new StringBuilder();
		boolean isFirst = true;
		for(int i=0;i<availableDatesAry.length;i++){
			if(availableDatesAry[i]){
				if(isFirst) isFirst = false;
				else availableDatesStrBuilder.append(",");
				
				availableDatesStrBuilder.append(i+1);
			}
		}
		strBuilder.append(String.format(Resource.STR_VOLUNTEER_PRINT_FORMAT, getName(),availableDatesStrBuilder.toString()));

		List<GraphNode> adjList = getAdjacentNodes();
		if(adjList.isEmpty()){
			strBuilder.append(Resource.STR_DISPLAY_NO_MATCHES);
		}
		else {
			for(int i=0;i<adjList.size();i++){
				Event e = (Event) adjList.get(i);
				strBuilder.append(String.format(Resource.STR_VOLUNTEER_EVENT_PRINT_FORMAT, e.getName(), e.getDate()));
			}
		}
		
		return strBuilder.toString();
	}

	/**
	 * Method to create string format to write volunteer information
	 * to the file. 
	 * Note that while printing available dates for the volunteer, 
	 * current available dates are used, using information from 
	 * availableDatesAry.
	 * 
	 * v;{name};{date},{date}...
	 */
	@Override
	public String toFileString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("v"); // type
		strBuilder.append(";");
		strBuilder.append(getName()); // name
		strBuilder.append(";");
		// availableDates
		boolean[] availableDatesTmp = Arrays.copyOf(this.availableDatesAry, this.availableDatesAry.length);
		for(GraphNode n : getAdjacentNodes()){
			Event e = (Event) n;
			availableDatesTmp[e.getDate()-1] = true;
		}
		boolean isFirst = true;
		for(int i=0;i<availableDatesTmp.length;i++){
			if(availableDatesTmp[i]){
				if(isFirst) isFirst = false;
				else strBuilder.append(",");
				
				strBuilder.append(i+1);
			}
		}
		
		return strBuilder.toString();
	}
}
