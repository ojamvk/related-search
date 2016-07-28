import java.util.*;
public class Utility {

	public HashMap getPrefixes(String source, String sink)	{
		HashMap<String, HashMap<String, Double>> prefixData = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> prefixList1 = new HashMap<String, Double>();
		prefixList1.put("top 5", 0.1);
		prefixList1.put("bottom 5", 0.61);
		prefixList1.put("by", 0.56);
		prefixData.put("country", prefixList1);
		
		HashMap<String, Double> prefixList2 = new HashMap<String, Double>();
		prefixList2.put("with lowest", 0.1);
		prefixList2.put("with highest", 0.61);
		prefixData.put("gross sales", prefixList2);
		
		return prefixData;
	}
}
