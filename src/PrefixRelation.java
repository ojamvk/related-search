import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class PrefixRelation {

	public PrefixRelation(ArrayList<Prefix> sourcePrefixList, ArrayList<Prefix> sinkPrefixList) {
		this.sourcePrefixList = sourcePrefixList;
		this.sinkPrefixList = sinkPrefixList;
	}

	private ArrayList<Prefix> sourcePrefixList;
	private ArrayList<Prefix> sinkPrefixList;

	public ArrayList<Prefix> getSourcePrefixList() {
		return sourcePrefixList;
	}

	public void setSourcePrefixList(ArrayList<Prefix> sourcePrefixList) {
		this.sourcePrefixList = sourcePrefixList;
	}

	public ArrayList<Prefix> getSinkPrefixList() {
		return sinkPrefixList;
	}

	public void setSinkPrefixList(ArrayList<Prefix> sinkPrefixList) {
		this.sinkPrefixList = sinkPrefixList;
	}

	public static HashMap<String, ArrayList<Prefix>> getPrefixData(String sourceHfType, String sinkHfType,
			ArrayList<SearchQuery> queryList) {
		HashMap<String, ArrayList<Prefix>> prefixData = new HashMap<String, ArrayList<Prefix>>();
		HashMap<String, PrefixRelation> prefixMap = JsonFileParser.getPrefixMap("JsonFiles/Prefix.json");
		HashMap<String, PrefixRelation> globalMap = getFilteredPrefixData(queryList,prefixMap);
		if (globalMap.containsKey(sourceHfType))
			prefixData.put(sourceHfType, globalMap.get(sourceHfType).getSourcePrefixList());
		else
			prefixData.put(sourceHfType, Prefix.getPrefixSourceList("", queryList, prefixMap));

		if (globalMap.containsKey(sinkHfType))
			prefixData.put(sinkHfType, globalMap.get(sinkHfType).getSinkPrefixList());
		else
			prefixData.put(sinkHfType, Prefix.getPrefixSinkList("", queryList, prefixMap));

		return prefixData;
	}

	private static HashMap<String, PrefixRelation> getFilteredPrefixData(ArrayList<SearchQuery> queryList, HashMap<String, PrefixRelation> prefixMap) {
		HashMap<String, PrefixRelation> data = new HashMap<String, PrefixRelation>();
		
		Set<String> hfTypes = prefixMap.keySet();
		for(String hfType: hfTypes)	{
			data.put(hfType, new PrefixRelation(Prefix.getPrefixSourceList(hfType, queryList, prefixMap),
					Prefix.getPrefixSinkList(hfType, queryList,prefixMap)));
		}
		
		return data;
	}

	
}
