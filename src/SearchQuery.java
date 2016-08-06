import java.util.ArrayList;

public class SearchQuery implements Comparable<SearchQuery> {

	private String query;
	private double aqRank;
	private String sourcePrefix;
	private String sinkPrefix;
	private String sourceName;
	private String sinkName;

	public SearchQuery(ArrayList<String> query, double aqRank) {
		this.sourcePrefix = query.get(0);
		this.sourceName = query.get(1);
		this.sinkPrefix = query.get(2);
		this.sinkName = query.get(3);
		this.aqRank = aqRank;
	}

	public String getQuery() {
		return sourcePrefix + " " + sourceName + " " + sinkPrefix + " " + sinkName;
	}

	public String getSourcePrefix() {
		return sourcePrefix;
	}

	public String getSinkPrefix() {
		return sinkPrefix;
	}

	public String getSourceName() {
		return sourceName;
	}

	public String getSinkName() {
		return sinkName;
	}

	
	public double getAqRank() {
		return aqRank;
	}

	public void setAqRank(double aqRank) {
		this.aqRank = aqRank;
	}

	public int compareTo(SearchQuery q) {
		if (this.aqRank > q.aqRank) {
			return 1;
		}
		if (this.aqRank < q.aqRank) {
			return -1;
		}
		return 0;
	}
}
