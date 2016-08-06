import java.util.ArrayList;
import java.util.HashMap;

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

	public static HashMap<String, ArrayList<Prefix>> getPrefixData(String source, String sink,
			ArrayList<SearchQuery> queryList) {
		HashMap<String, ArrayList<Prefix>> prefixData = new HashMap<String, ArrayList<Prefix>>();

		if (getGlobalPrefixData(queryList).containsKey(source))
			prefixData.put(source, getGlobalPrefixData(queryList).get(source).getSourcePrefixList());
		else
			prefixData.put(source, Prefix.getPrefixSourceList("", queryList));

		if (getGlobalPrefixData(queryList).containsKey(sink))
			prefixData.put(sink, getGlobalPrefixData(queryList).get(sink).getSinkPrefixList());
		else
			prefixData.put(sink, Prefix.getPrefixSinkList("", queryList));

		// om

		return prefixData;
	}

	private static HashMap<String, PrefixRelation> getGlobalPrefixData(ArrayList<SearchQuery> queryList) {
		HashMap<String, PrefixRelation> data = new HashMap<String, PrefixRelation>();

		data.put("country", new PrefixRelation(Prefix.getPrefixSourceList("country", queryList),
				Prefix.getPrefixSinkList("country", queryList)));
		data.put("sales", new PrefixRelation(Prefix.getPrefixSourceList("sales", queryList),
				Prefix.getPrefixSinkList("sales", queryList)));
		data.put("product", new PrefixRelation(Prefix.getPrefixSourceList("product", queryList),
				Prefix.getPrefixSinkList("product", queryList)));

		return data;
	}

	private void removePrefixData() {

	}
}
