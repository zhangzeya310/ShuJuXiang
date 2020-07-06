package zzu.bs.bean;

import java.io.Serializable;

public class SecondCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer secondCategoryId;
	private String secondCategoryName;
	private Integer categoryId;

	//无id
	public SecondCategory(String secondCategoryName, Integer categoryId) {
		this.secondCategoryName = secondCategoryName;
		this.categoryId = categoryId;
	}

	public SecondCategory() {
		super();
	}

	//所有字段
	public SecondCategory(Integer secondCategoryId, String secondCategoryName, Integer categoryId) {
		this.secondCategoryId = secondCategoryId;
		this.secondCategoryName = secondCategoryName;
		this.categoryId = categoryId;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
