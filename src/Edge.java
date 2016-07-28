import java.util.*;

public class Edge {

	private Node node;

	private double weight;
	private HashMap<String, HashMap<String, Double>> prefixes;

	public Edge(Node n, double weight2, HashMap<String, HashMap<String, Double>> prefixes) {
		node = n;
		setWeight(weight2);
		setPrefixes(prefixes);
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String toString() {

		return "( " + node + ", " + weight + " )";
	}

	public HashMap<String, HashMap<String, Double>> getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(HashMap<String, HashMap<String, Double>> prefixes) {
		this.prefixes = prefixes;
	}
}
