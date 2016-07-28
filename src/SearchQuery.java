
public class SearchQuery implements Comparable<SearchQuery>	{

	private String query;
	private double aqRank;
	
	public SearchQuery(String query, double aqRank) 	{
		this.query = query;
		this.aqRank = aqRank;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public double getAqRank() {
		return aqRank;
	}
	public void setAqRank(double aqRank) {
		this.aqRank = aqRank;
	}
	
	public int compareTo(SearchQuery q)	{
		if(this.aqRank > q.aqRank)	{
			return 1;
		}
		if(this.aqRank< q.aqRank)	{
			return -1;
		}
		return 0;
	}
}
