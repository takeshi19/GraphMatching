import java.util.List;

/**
 * Stores information about an event that requires volunteers.
 * Each event has a case-insensitive name, a date (represented as an integer,
 * and the  maximum number of volunteers that are needed for this event.
 * 
 * DO NOT EDIT THIS CLASS
 * 
 * This type inherits from GraphNode and its adjacent nodes will be GraphNodes for Volunteers.
 */
public class Event extends GraphNode {
	
	/** The date that this event occurs */
	private int date;
	
	/** The maximum number of volunteers for this event */
	private int limit;

	/**
	 * Constructor for an Event instance
	 * 
	 * @param name a case-insensitive event name
	 * @param date an integer value between 1 and 30 for when this event occurs
	 * @param limit the maximum number of volunteers for this event
	 */
	public Event(String name, int date, int limit) {
		super(name);
		if(date < 1 || date > 30 || limit < 0) throw new IllegalArgumentException();
		this.date = date;
		this.limit = limit;
	}

	/** 
	 * Returns the date (1-30) that this event occurs 
	 * 
	 * @return the date this event occurs
	 */
	public int getDate(){
		return this.date;
	}

	/**
	 * The maximum number of volunteers that can be assigned to this event.
	 * @return the maximum number of volunteers allowed for this event.
	 */
	public int getLimit(){
		return this.limit;
	}

	/**
	 * Returns true iff if the current number of volunteers is less than
	 * the volunteer limit for this event.
	 * @return true if there is room for another volunteer
	 */
	public boolean isBelowLimit(){
		return this.limit > getAdjacentNodes().size();
	}

	/**
	 * Return true iff if the specified volunteer is already a volunteer
	 * for this event.
	 * @param name the volunteer's name
	 * @return true if there is a volunteer for this event with this name
	 */
	public boolean hasVolunteer(String name){
		return isAdjacentNode(name);
	}

	/**
	 * Returns a string that provides the details for this event in the
	 * format required for displaying to the standard output (screen).
	 * 
	 * Resource.STR_DISPLAY_EVENT_PRINT_FORMAT
	 * Resource.STR_DISPLAY_EVENT_VOLUNTEERS_PRINT_FORMAT
	 * Resource.STR_DISPLAY_NO_MATCHES
	 * 
	 * @see P5 description
	 * 
	 * @return A formatted string with event details and volunteer list for displaying
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(String.format(Resource.STR_DISPLAY_EVENT_PRINT_FORMAT, getName(), getDate(), getLimit()));
		// matched volunteers
		List<GraphNode> adjList = getAdjacentNodes();
		if(adjList.isEmpty()){
			strBuilder.append(Resource.STR_DISPLAY_NO_MATCHES);
		}
		else {
			for(int i=0;i<adjList.size();i++){
				Volunteer v = (Volunteer) adjList.get(i);
				strBuilder.append(String.format(Resource.STR_DISPLAY_EVENT_VOLUNTEERS_PRINT_FORMAT, i+1, v.getName()));
			}
		}
		return strBuilder.toString();
	}
	
	/**
	 * Returns a string for writing file output.
	 * 
	 * <pre>e;Field Trip;5;2;Mingi,Sonu
	 * e;Birthday;10;5;Mingi,Deb</pre>
	 * 
	 * DO NOT EDIT THIS METHOD
	 * 
	 * @return A formatted string with event details and volunteer list for writing file
	 */
	@Override
	public String toFileString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("e"); // type
		strBuilder.append(";");
		strBuilder.append(getName()); // name
		strBuilder.append(";");
		strBuilder.append(getDate()); // date
		strBuilder.append(";");
		strBuilder.append(getLimit()); // limit
		strBuilder.append(";");
		// matched volunteers
		List<GraphNode> adjList = getAdjacentNodes();
		for(int i=0;i<adjList.size();i++){
			Volunteer v = (Volunteer) adjList.get(i);
			strBuilder.append(v.getName());
			if(i != adjList.size()-1) strBuilder.append(",");
		}
		
		return strBuilder.toString();
	}
}
