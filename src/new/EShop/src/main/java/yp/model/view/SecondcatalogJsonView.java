package yp.model.view;

/**
 * 用于展示二级目录id和name的jsonView
 * @author YP
 *
 */
public class SecondcatalogJsonView {
	private Integer secondcatalogId;
	private String secondcatalogName;
	public Integer getSecondcatalogId() {
		return secondcatalogId;
	}
	public void setSecondcatalogId(Integer secondcatalogId) {
		this.secondcatalogId = secondcatalogId;
	}
	public String getSecondcatalogName() {
		return secondcatalogName;
	}
	public void setSecondcatalogName(String secondcatalogName) {
		this.secondcatalogName = secondcatalogName;
	}
}
