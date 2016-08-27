import java.util.*;

public class Utility {

	public HashMap<String, HashMap<String, Double>> getPrefixes(String source, String sink,
			ArrayList<SearchQuery> queryList) {
		source = source.toLowerCase();
		sink = sink.toLowerCase();
		HashMap<String, HashMap<String, Double>> prefixDataMap = new HashMap<String, HashMap<String, Double>>();

		HashMap<String, ArrayList<Prefix>> prefixData = PrefixRelation.getPrefixData(source, sink, queryList);
		// temporary data structure change. Later update everything to Prefix
		// object.

		HashMap<String, Double> prefixSourceMap = new HashMap<String, Double>();
		for (Prefix prefix : prefixData.get(source)) {
			prefixSourceMap.put(prefix.getPrefix(), prefix.getValue());
		}
		prefixSourceMap = sortByValues(prefixSourceMap);
		prefixDataMap.put(source, prefixSourceMap);

		HashMap<String, Double> prefixSinkMap = new HashMap<String, Double>();
		for (Prefix prefix : prefixData.get(sink)) {
			prefixSinkMap.put(prefix.getPrefix(), prefix.getValue());
		}
		prefixSinkMap = sortByValues(prefixSinkMap);
		prefixDataMap.put(sink, prefixSinkMap);

		return prefixDataMap;

	}

	public ArrayList<String> getBestPrefix(Node source, Node sink, ArrayList<SearchQuery> queryList) {
		HashMap<String, HashMap<String, Double>> prefixData = getPrefixes(source.getHfType(), sink.getHfType(), queryList);
		ArrayList<String> resultArray = new ArrayList<String>();
		if (prefixData.isEmpty())
			return null;
		if (!prefixData.get(source.getHfType()).entrySet().iterator().hasNext())
			return null;
		Map.Entry<String, Double> entry1 = prefixData.get(source.getHfType()).entrySet().iterator().next();
		String prefix1 = entry1.getKey();
		Double value1 = entry1.getValue();
		if (!prefixData.get(sink.getHfType()).entrySet().iterator().hasNext())
			return null;
		entry1 = prefixData.get(sink.getHfType()).entrySet().iterator().next();
		String result1 = prefix1 + " " + source.getName() + " " + entry1.getKey() + " " + sink.getName();
		value1 += entry1.getValue();
		Map.Entry<String, Double> entry2 = prefixData.get(source.getHfType()).entrySet().iterator().next();
		prefixData = getPrefixes(sink.getHfType(), source.getHfType(), queryList);
		entry2 = prefixData.get(sink.getHfType()).entrySet().iterator().next();
		String prefix2 = entry2.getKey();
		Double value2 = Double.MIN_VALUE;
		if(prefixData.get(source.getHfType()).entrySet().iterator().hasNext()) {
		value2 = entry2.getValue();
		entry2 = prefixData.get(source.getHfType()).entrySet().iterator().next();
		String result2 = prefix2 + " " + sink.getName() + " " + entry2.getKey() + " " + source.getName();
		value2 += entry2.getValue();
		}
		if (value1 > value2) {
			resultArray.add(prefix1);
			resultArray.add(source.getName());
			resultArray.add(entry1.getKey());
			resultArray.add(sink.getName());
		} else {
			resultArray.add(prefix2);
			resultArray.add(sink.getName());
			resultArray.add(entry2.getKey());
			resultArray.add(source.getName());
		}

		return resultArray;
		
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
