/**@author Pooja Ravi
*UTA id: 1001578517
*/

public class node_properties implements Comparable<node_properties> {
	 // declaring the properties of the nodes in a separate class
	node_properties parent;
	int depth;
	float path_cost;
	String present_state;
	node_properties child;
	public node_properties(node_properties parent, int depth, float path_cost, String present_state)// constructor to initialize the nodes
	{
		this.parent = parent;
		this.depth = depth;
		this.path_cost = path_cost;
		this.present_state = present_state;
	}
	@Override
	public int compareTo(node_properties arg0) {
		return Float.compare(this.path_cost, arg0.path_cost);// sorting the array in the fringe
	}
	// TODO Auto-generated method stub
}
