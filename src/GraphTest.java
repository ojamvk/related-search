import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class GraphTest {

	public static void main(String[] args) {

		JsonFileParser fileParser = new JsonFileParser();
		Graph graph = fileParser.parseFile("C:\\Users\\Reshma\\Downloads\\retail_data_model.txt");
		
		System.out.println(graph.toString());
		HashMap<String, Double> prefixesWord1 = new HashMap<String, Double>();
		prefixesWord1.put("total", 0.9);
		prefixesWord1.put("top", 0.3);

		HashMap<String, Double> prefixesWord2 = new HashMap<String, Double>();
		prefixesWord2.put("in", 0.9);
		prefixesWord2.put("for", 0.3);
		
		
		
		System.out.println("Enter query: ");
		Scanner sc = new Scanner(System.in);
		String query = sc.nextLine();
		String[] terms = query.split(" ");
		for(String term: terms)	{
			HashMap<String, Double> similarity = new HashMap<String, Double>();
			String result = "";
			HashMap<String, ArrayList<Node>> entityList = graph.getNodeList();
			Iterator it = entityList.entrySet().iterator();
			while(it.hasNext())	{
				Map.Entry<String, ArrayList<Node>> entry = (Map.Entry)it.next();
				ArrayList<Node> nodesInEntity = entry.getValue();
				for(int i=0; i<nodesInEntity.size(); i++)	{
					if(nodesInEntity.get(i).getName().equalsIgnoreCase(term))	{
						System.out.println("Found node: "+ term);
						graph.computeRelatedResults(nodesInEntity.get(i));
						//Found the node in the graph. Compute similarity based on prefix first word or prefix second word.
					}
				}
			}
		}

		// graph.getMaxWeightAdjacentNode(node).getNode().getName());

	}

	

}
