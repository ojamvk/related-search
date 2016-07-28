import java.util.HashMap;

public class Node {

	private String name;
	private String hfType;
	private String hfFormat;
	private String dataType;
	private String dataVariety;
	private double aqRank;
	private String entityName;

	public String getHfType() {
		return hfType;
	}

	public void setHfType(String hfType) {
		this.hfType = hfType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataVariety() {
		return dataVariety;
	}

	public void setDataVariety(String dataVariety) {
		this.dataVariety = dataVariety;
	}

	public String getHfFormat() {
		return hfFormat;
	}

	public void setHfFormat(String hfFormat) {
		this.hfFormat = hfFormat;
	}

	public double getAqRank() {
		return aqRank;
	}

	public void setAqRank(double aqRank) {
		this.aqRank = aqRank;
	}

	public Node(String name, String hfType, String hfFormat, String dataType, String dataVariety, double aqRank,
			String entityName) {
		setName(name);
		setHfType(hfType);
		setHfFormat(hfFormat);
		setDataType(dataType);
		setDataVariety(dataVariety);
		setAqRank(aqRank);
		setEntityName(entityName);

	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
