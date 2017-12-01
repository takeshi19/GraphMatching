import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * GraphNode is the super type of all vertices in p5.
 *  
 * Implements basic get/set for name field, and provides a list of 
 * graphnodes that is used to keep track of the adjacent vertices of 
 * this node.
 * 
 * There are two abstract method which must be defined by sub-classes of this type.
 * String toString()     - used to display to standard output
 * String toFileString() - provides output string in format to be written to a file
 */
public abstract class GraphNode implements Comparable<GraphNode> {
	
	/** Identifier for this vertex */
	private String name;
	
	/** 
	 * List of all adjacent nodes from this vertex.
	 * Events will have volunteers as adjacent vertices (successors)
	 * Volunteers will have events as adjacent verticies (successors)
	 */
	private List<GraphNode> adjacentNodes;
	
	/**
	 * Construct a new node with the given name.  
	 * @throws IllegalArgument Exception if name is null.
	 * @param name The name or identifier for this node.
	 */
	public GraphNode(String name){
		if(name == null) throw new IllegalArgumentException();
		this.name = name;
		this.adjacentNodes = new ArrayList<GraphNode>();
	}

	/**
	 * Constructor for the GraphNode given name of the node and adjacentNodes list.
	 * Throws IllegalArgumentException if either of the arguments are null. 
	 *
	 * @throws IllegalArgument Exception if name is null.
	 * @param name The name or identifier for this node.
	 */
	protected GraphNode(String name, List<GraphNode> adjacentNodes){
		if(name == null || adjacentNodes == null) throw new IllegalArgumentException();
		this.name = name;
		this.adjacentNodes = adjacentNodes;
	}
	
	/**
	 * 
	 * @return the name of this graph node.
	 */
	public String getName(){
		return this.name;
	}

	/**
 	 * 
	 * @return return the adjacent modes of this node (matches)
	 */
	protected List<GraphNode> getAdjacentNodes(){
		return this.adjacentNodes;
	}
	
	/**
	 * This method is used to add a given node to the adjacentNodes.
	 * After adding, adjacentNodes should be sorted.
	 * @param node 
	 */
	public void addAdjacentNode(GraphNode node){
		adjacentNodes.add(node);
		Collections.sort(adjacentNodes);
	}
	
	/**
	 * This method is used to remove a given node from the adjacentNodes.
	 * @param node
	 * @return 
	 */
	public boolean removeAdjacentNode(GraphNode node){
		return adjacentNodes.remove(node);
	}
	
	/**
	 * This method checks if adjacentNodes contain a node with the 
	 * given name
	 * @param name
	 * @return true if adjacentNodes contain a node with the 
	 * given name, otherwise returns false.
	 */
	public boolean isAdjacentNode(String name){
		for(GraphNode node : adjacentNodes){
			if(node.getName().equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	public abstract String toString();
	public abstract String toFileString();

	/**
	 * compares this node with the given node based on names.
	 */
	@Override
	public int compareTo(GraphNode node){
		return this.getName().compareToIgnoreCase(node.getName());
	}
}
