import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Graph {

	private HashMap<Node, ArrayList<Edge>> adjacencyList;

	// private Node n;

	private HashMap<String, ArrayList<Node>> entityList;

	public Graph() {
		adjacencyList = new HashMap<Node, ArrayList<Edge>>();
		entityList = new HashMap<String, ArrayList<Node>>();
	}

	public void addNodeToEntityList(Node node) {
		String entityName = node.getEntityName();
		ArrayList attributes;
		if (entityList.containsKey(entityName)) {
			attributes = entityList.get(entityName);
			if (!(attributes.contains(node))) {
				attributes.add(node);
			}
		} else {
			attributes = new ArrayList<Node>();
			attributes.add(node);
		}
		entityList.put(entityName, attributes);

	}

	public void addArcsWithinEntity(Node node1) {
		HashMap<String, ArrayList<Node>> entityList = getNodeList();
		Utility utility = new Utility();
		// prefixes.put("prefix", );
		String entityName = node1.getEntityName();
		if (entityList.containsKey(entityName)) {
			ArrayList<Node> nodes = entityList.get(entityName);
			if (nodes != null) {
				for (Node node2 : nodes) {
					if (node2 != node1) {
						HashMap<String, HashMap<String, Double>> prefixes = utility.getPrefixes(node1.getHfType(),
								node2.getHfType(), new ArrayList<SearchQuery>());
						addArc(node1, node2, (node1.getAqRank() + node2.getAqRank()), prefixes);
						addArc(node2, node1, (node1.getAqRank() + node2.getAqRank()), prefixes);
					}

				}
			}
		}
	}

	public void addToList(Node node, ArrayList<Edge> connectedNodes) {

		adjacencyList.put(node, connectedNodes);
		// String entityName = Node.getEntityName();

		// entityList.put(Node.toString(), new ArrayList<Node>());

		// entityList.add(Node);

		for (Edge nodeConnectedToAddedNode : connectedNodes) {
			ArrayList<Edge> correspondingConnectedList = adjacencyList.get(nodeConnectedToAddedNode.getNode());

			if (correspondingConnectedList == null) {
				adjacencyList.put(nodeConnectedToAddedNode.getNode(), new ArrayList<Edge>());
				// entityList.put(Node.toString(),
				// NodeConnectedToAddedNode.getNode());
				correspondingConnectedList = adjacencyList.get(nodeConnectedToAddedNode.getNode());
			}

		}

	}

	public boolean addArc(Node source, Node sink, double weight, HashMap<String, HashMap<String, Double>> prefixes) {

		if (!adjacencyList.containsKey(source)) {
			ArrayList<Edge> tempList = new ArrayList<Edge>();
			tempList.add(new Edge(sink, weight, prefixes));
			// adjacencyList.put(source, tempList);
			addToList(source, tempList);
			return true;
		}

		/*
		 * if (!adjacencyList.containsKey(sink)) { ArrayList<Edge> tempList =
		 * new ArrayList<Edge>(); //adjacencyList.put(sink, tempList);
		 * //add(sink, tempList); }
		 */
		adjacencyList.get(source).add(new Edge(sink, weight, prefixes));
		return true;
	}

	public ArrayList<String> getAdjacentNodes(Node node) {
		ArrayList<String> returnList = new ArrayList<String>();
		for (Edge edge : adjacencyList.get(node)) {
			returnList.add(edge.getNode().getName());
		}
		return returnList;
	}

	public double getDistanceBetween(Node source, Node sink) {
		for (Edge edge : adjacencyList.get(source)) {
			if (edge.getNode() == sink) {
				return edge.getWeight();
			}
		}
		return Double.POSITIVE_INFINITY;
	}

	public ArrayList<SearchQuery> computeRelatedResults(Node source) {
		Utility utility = new Utility();
		ArrayList<SearchQuery> queryList = new ArrayList<SearchQuery>();

		for (Edge edge : adjacencyList.get(source)) {
			if (!(edge.getNode().getName().equalsIgnoreCase(source.getName()))) {
				ArrayList<String> query = utility.getBestPrefix(source, edge.getNode(), queryList);
				if (query != null)
					queryList.add(new SearchQuery(query, edge.getWeight()));

			}
		}
		Collections.sort((List) queryList);

		return queryList;
	}

	public HashMap<String, ArrayList<Node>> getNodeList() {
		return entityList;
	}

	public Edge getMaxWeightAdjacentNode(Node node) {
		Edge maxWeightNode = adjacencyList.get(node).get(0);
		for (Edge edge : adjacencyList.get(node)) {
			if (maxWeightNode.getWeight() < edge.getWeight()) {
				maxWeightNode = edge;
			}

		}
		return maxWeightNode;
	}

	public String toString() {
		String s = "";
		Iterator it = entityList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ArrayList<Node> nodeList = (ArrayList<Node>) pair.getValue();
			for (Node node : nodeList) {
				s += node.getName();
				s += " : ";
				if (adjacencyList != null && adjacencyList.get(node) != null) {
					for (Edge n : adjacencyList.get(node)) {
						s += n.getNode().getName() + ", ";
					}
				}
				s = s.substring(0, s.length() - 2);
				s += "\n";
			}
		}
		return s;
	}

}
