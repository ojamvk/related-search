import java.util.*;

public class Utility {

	public HashMap getPrefixes(String source, String sink) {
		HashMap<String, HashMap<String, Double>> prefixData = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> prefixList1 = new HashMap<String, Double>();
		prefixList1.put("top 5", 0.1);
		prefixList1.put("bottom 5", 0.61);
		prefixList1.put("by", 0.56);
		// Sort by values
		prefixData.put("country", prefixList1);

		HashMap<String, Double> prefixList2 = new HashMap<String, Double>();
		prefixList2.put("with lowest", 0.1);
		prefixList2.put("with highest", 0.61);
		prefixData.put("sales", prefixList2);

		return prefixData;
	}

	public String getBestPrefix(String source, String sink) {
		HashMap<String, HashMap<String, Double>> prefixData = getPrefixes(source, sink);
		Map.Entry<String, Double> entry = prefixData.get(source).entrySet().iterator().next();
		String result1 = entry.getKey();
		Double value1 = entry.getValue();
		entry = prefixData.get(sink).entrySet().iterator().next();
		result1 += " " + source + " "+ entry.getKey() + " "+ sink;
		value1 += entry.getValue();

		prefixData = getPrefixes(sink, source);
		entry = prefixData.get(sink).entrySet().iterator().next();
		String result2 = entry.getKey();
		Double value2 = entry.getValue();
		entry = prefixData.get(source).entrySet().iterator().next();
		result2 += " " + sink + " "+ entry.getKey() + " "+ sink;
		value2 += entry.getValue();
		
		
		return value1>value2 ? result1 : result2;
	}
	
	
}
