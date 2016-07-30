import java.util.ArrayList;
import java.util.HashMap;

public class PrefixRelation {
	
	public PrefixRelation(ArrayList<Prefix> sourcePrefixList, ArrayList<Prefix> sinkPrefixList){
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
	
	public static HashMap<String, ArrayList<Prefix>> getPrefixData(String source, String sink) {
		HashMap<String, ArrayList<Prefix>> prefixData =  new HashMap<String, ArrayList<Prefix>>();
		
		if(getGlobalPrefixData().containsKey(source))
			prefixData.put(source, getGlobalPrefixData().get(source).getSourcePrefixList());
		else
			prefixData.put(source, Prefix.getPrefixSourceList(""));
		
		if(getGlobalPrefixData().containsKey(sink))
			prefixData.put(sink, getGlobalPrefixData().get(sink).getSinkPrefixList());
		else
			prefixData.put(sink, Prefix.getPrefixSinkList(""));
		
		return prefixData;
	}
	
	private static HashMap<String, PrefixRelation> getGlobalPrefixData(){
		HashMap<String, PrefixRelation> data = new HashMap<String, PrefixRelation>();
		
		data.put("country", new PrefixRelation(Prefix.getPrefixSourceList("country"), Prefix.getPrefixSinkList("country")));
		data.put("sales", new PrefixRelation(Prefix.getPrefixSourceList("sales"), Prefix.getPrefixSinkList("sales")));
		data.put("product", new PrefixRelation(Prefix.getPrefixSourceList("product"), Prefix.getPrefixSinkList("product")));
		
		return data;
	}
}
