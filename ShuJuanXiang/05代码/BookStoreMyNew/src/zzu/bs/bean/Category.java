package zzu.bs.bean;

import java.util.List;

public class Category {

	private Integer categoryId;
	private String categoryName;
	private String categoryDesc;
	private List<SecondCategory> secondCategory;

	public Category() {
	}

	//无id，集合
	public Category(String categoryName, String categoryDesc) {
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}

	//无集合
	public Category(Integer categoryId, String categoryName, String categoryDesc) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public List<SecondCategory> getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(List<SecondCategory> secondCategory) {
		this.secondCategory = secondCategory;
	}

	
}
