import java.util.*;

public class Utility {

	public HashMap<String, HashMap<String, Double>> getPrefixes(String source, String sink)	{
		HashMap<String, HashMap<String, Double>> prefixDataMap = new HashMap<String, HashMap<String, Double>>();
		
		HashMap<String, ArrayList<Prefix>> prefixData = PrefixRelation.getPrefixData(source, sink);
		//temporary data structure change. Later update everything to Prefix object.
		
		HashMap<String, Double> prefixSourceMap = new HashMap<String, Double>();
		for(Prefix prefix : prefixData.get(source)){
			prefixSourceMap.put(prefix.getPrefix(), prefix.getValue());
		}
		prefixSourceMap = sortByValues(prefixSourceMap);
		prefixDataMap.put(source, prefixSourceMap);
		
		HashMap<String, Double> prefixSinkMap = new HashMap<String, Double>();
		for(Prefix prefix : prefixData.get(sink)){
			prefixSinkMap.put(prefix.getPrefix(), prefix.getValue());
		}
		prefixSinkMap = sortByValues(prefixSinkMap);
		prefixDataMap.put(sink, prefixSinkMap);
		
		return prefixDataMap;
		
	}
	/*public HashMap getPrefixes(String source, String sink) {
		HashMap<String, HashMap<String, Double>> prefixData = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> prefixList1 = new HashMap<String, Double>();
		prefixList1.put("top 5", 0.1);
		prefixList1.put("bottom 5", 0.61);
		prefixList1.put("by", 0.56);
		prefixList1 = sortByValues(prefixList1);
		prefixData.put("country", prefixList1);

		HashMap<String, Double> prefixList2 = new HashMap<String, Double>();
		prefixList2.put("with lowest", 0.1);
		prefixList2.put("with highest", 0.61);
		prefixList2 = sortByValues(prefixList2);
		prefixData.put("sales", prefixList2);

		return prefixData;
	}*/

	public String getBestPrefix(String source, String sink) {
		HashMap<String, HashMap<String, Double>> prefixData = getPrefixes(source, sink);
		Map.Entry<String, Double> entry = prefixData.get(source).entrySet().iterator().next();
		String result1 = entry.getKey();
		Double value1 = entry.getValue();
		entry = prefixData.get(sink).entrySet().iterator().next();
		result1 += " " + source + " " + entry.getKey() + " " + sink;
		value1 += entry.getValue();

		prefixData = getPrefixes(sink, source);
		entry = prefixData.get(sink).entrySet().iterator().next();
		String result2 = entry.getKey();
		Double value2 = entry.getValue();
		entry = prefixData.get(source).entrySet().iterator().next();
		result2 += " " + sink + " " + entry.getKey() + " " + source;
		value2 += entry.getValue();

		return value1 > value2 ? result1 : result2;
		
	}

	public static HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

}
