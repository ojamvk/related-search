import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileParser {

	public Graph parseFile(String path) {
		Graph graph = new Graph();
		GraphTest graphTest = new GraphTest();
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) object;
			String author = (String) jsonObject.get("Author");
			JSONArray entityList = (JSONArray) jsonObject.get("entities");
			System.out.println("\nEntity List:");
			Iterator<JSONObject> iterator = entityList.iterator();
			while (iterator.hasNext()) {
				JSONObject entity = iterator.next();
				JSONArray attributes = (JSONArray) entity.get("attributes");
				Iterator<JSONObject> i = attributes.iterator();
				while (i.hasNext()) {
					JSONObject attribute = i.next();
					String attributeName = (String) attribute.get("name");
					String hfType = (String) attribute.get("hfType");
					String hfFormat = (String) attribute.get("hfFormat");
					String dataType = (String) attribute.get("dataType");
					String dataVariety = (String) attribute.get("variety");
					String entityName = (String) attribute.get("entityName");
					double aqRank = Double.parseDouble(attribute.get("aqRank").toString());
					Node node = new Node(attributeName, hfType, hfFormat, dataType, dataVariety, aqRank, entityName);
					graph.addNodeToEntityList(node);
					graph.addArcsWithinEntity(node);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return graph;
	}
}
