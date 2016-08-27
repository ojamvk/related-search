import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Prefix {

	public Prefix(String prefix, Double value) {
		this.prefix = prefix;
		this.value = value;
	}

	private String prefix;
	private Double value;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public static ArrayList<Prefix> getCleanPrefixList(ArrayList<Prefix> prefixList, ArrayList<SearchQuery> queryList,
			Boolean isSource) {
		ArrayList<Prefix> cleanPrefixList = new ArrayList<Prefix>();

		for (Prefix prefix : prefixList) {
			Boolean found = false;
			for (SearchQuery query : queryList) {
				if (prefix.getPrefix().equalsIgnoreCase(isSource ? query.getSourcePrefix() : query.getSinkPrefix())) {
					found = true;
					break;
				}

			}
			if (!found)
				cleanPrefixList.add(prefix);
		}
		return cleanPrefixList;
	}
	
	public static ArrayList<Prefix> getPrefixSourceList(String hfType, ArrayList<SearchQuery> queryList, HashMap<String, PrefixRelation> prefixMap) {
		ArrayList<Prefix> prefixList = new ArrayList<Prefix>();
		//hfType = hfType.toLowerCase();
		prefixList = prefixMap.get(hfType).getSourcePrefixList();
		prefixList = getCleanPrefixList(prefixList, queryList, true);
		return prefixList;
	}
	public static ArrayList<Prefix> getPrefixSinkList(String hfType, ArrayList<SearchQuery> queryList, HashMap<String, PrefixRelation> prefixMap) {
		ArrayList<Prefix> prefixList = new ArrayList<Prefix>();
		hfType = hfType.toLowerCase();
		prefixList = prefixMap.get(hfType).getSinkPrefixList();
		prefixList = getCleanPrefixList(prefixList, queryList, false);
		return prefixList;
	}

}
