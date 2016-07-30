import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Prefix {
	
	public Prefix (String prefix, Double aqRank){
		this.prefix = prefix;
		this.value = aqRank;
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
	public void setValue(Double aqRank) {
		this.value = aqRank;
	}
	
	public static ArrayList<Prefix> getPrefixSourceList(String nodeName) {
		ArrayList<Prefix> prefixList = new ArrayList<Prefix>();
		switch(nodeName){
		case "Country" :
			prefixList.add(new Prefix("top 10", 4.5));
			prefixList.add(new Prefix("bottom 10", 8.5));
			break;
		case "Sales" :
			prefixList.add(new Prefix("top 5", 2.5));
			prefixList.add(new Prefix("bottom 5", 6.5));
			break;
		default :
			prefixList.add(new Prefix("worst", 3.5));
			prefixList.add(new Prefix("best", 4.5));
			break;
		}
		return prefixList;
	}
	
	public static ArrayList<Prefix> getPrefixSinkList(String nodeName) {
		ArrayList<Prefix> prefixList = new ArrayList<Prefix>();
		switch(nodeName){
		case "Country" :
			prefixList.add(new Prefix("by", 4.5));
			prefixList.add(new Prefix("for all", 8.5));
			break;
		case "Sales" :
			prefixList.add(new Prefix("with highest", 2.5));
			prefixList.add(new Prefix("with lowest", 6.5));
			break;
		default :
			prefixList.add(new Prefix("for", 3.5));
			prefixList.add(new Prefix("not for", 4.5));
			break;
		}
		return prefixList;
	}
}
