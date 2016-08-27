import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class GraphTest {

	public static void main(String[] args) {

		JsonFileParser fileParser = new JsonFileParser();
		Graph graph = fileParser.parseFile("JsonFiles/retail_data_model.txt");

		System.out.println(graph.toString());

		System.out.println("Enter query: ");
		Scanner sc = new Scanner(System.in);
		String query = sc.nextLine();
		String[] terms = query.split(" ");
		ArrayList<SearchQuery> resultList = new ArrayList<SearchQuery>();
		for (String term : terms) {
			HashMap<String, Double> similarity = new HashMap<String, Double>();
			HashMap<String, ArrayList<Node>> entityList = graph.getNodeList();
			Iterator it = entityList.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, ArrayList<Node>> entry = (Map.Entry) it.next();
				ArrayList<Node> nodesInEntity = entry.getValue();
				for (int i = 0; i < nodesInEntity.size(); i++) {
					long startTime = System.currentTimeMillis();
					if (nodesInEntity.get(i).getName().equalsIgnoreCase(term)) {
						ArrayList<SearchQuery> queryList = graph.computeRelatedResults(nodesInEntity.get(i));
						for (SearchQuery result : queryList) {
							resultList.add(result);
						}
						long endTime = System.currentTimeMillis();
						System.out.println("Time taken for "+term+":"+(endTime - startTime) + " ms");
					}
				}
			}
		}
		int i = 0;
		while (i<resultList.size() && i < 10) {
			SearchQuery result = resultList.get(i);
			System.out.println(result.getQuery());
			i++;
		}

		// graph.getMaxWeightAdjacentNode(node).getNode().getName());

	}

}
