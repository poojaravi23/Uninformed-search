/**@author Pooja Ravi
*UTA id: 1001578517
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class find_route {
	public static void main(String[ ] args ) {

		String input_file = args[0];//string declaration for the inputs
		String source= args[1];
		String destination = args[2];
		ArrayList<String> list=new ArrayList<String>();//Creating array list
		try {// to read a input text file

			FileReader fileReader = new FileReader(input_file);
			BufferedReader bufferedReader =new BufferedReader(fileReader);

			String line;

			while((line = bufferedReader.readLine().toString()) .equals("END OF INPUT")==false) {
				list.add(line);

			}


			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '"
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		node_properties parent_node = new node_properties(null,0,0,source);      // object creations for the node- initial value of root, depth value, cost, name of the source
		ArrayList<node_properties> fringe_array=new ArrayList<node_properties>();//Creating array list to enter it to the fringe
		fringe_array.add(parent_node);
		node_properties goal_node=null;
		ArrayList<String> closed_array=new ArrayList<String>();
		while(!(fringe_array.isEmpty())&& goal_node==null) {
			node_properties current_node  = fringe_array.remove(0);             // removing the first element of the fringe and adding simultaneously

			if (current_node.present_state.equals(destination))
			{
				goal_node=current_node;
			}
			else{
				if (closed_array.contains(current_node.present_state)) {}
				else {
					closed_array.add (current_node.present_state);
					for(String temp : list)                                    //string,variable,:,list name
					{
						if(temp.contains(current_node.present_state)) {
							StringTokenizer tokenstring=new StringTokenizer(temp, " "); //split using the stringtokenizer by space
							String cityA=tokenstring.nextToken();
							String cityB=tokenstring.nextToken();
							float cost=(float)Integer.parseInt(tokenstring.nextToken());
							if(current_node.present_state.equals(cityA))
							{
								node_properties city_node = new node_properties(current_node,current_node.depth+1,current_node.path_cost+cost,cityB);
								fringe_array.add(city_node); //add the successor to the fringe
							}
							else {node_properties city_node = new node_properties(current_node,current_node.depth+1,current_node.path_cost+cost,cityA);
							fringe_array.add(city_node);
							}
						}
					}
					Collections.sort(fringe_array); //sorting the fringe to obtain an optimal solution
				}
			}
		}
		if(goal_node==null) {
			System.out.println("distance:infinity \n route\n none"); //if no route found, display this
		}
		else {
			goal_node.child=null;  //retracing the path from the destination to print the output in the same order
			System.out.println("Distance : "+goal_node.path_cost+"\n route : ");
			while(goal_node.parent!=null) {
				goal_node.parent.child=goal_node;
				goal_node=goal_node.parent;
			}
			while(goal_node.child!=null) {
				System.out.println(goal_node.present_state+" to "+goal_node.child.present_state+" "+(goal_node.child.path_cost-goal_node.path_cost));
				goal_node=goal_node.child;
			}
		}
	}
}

