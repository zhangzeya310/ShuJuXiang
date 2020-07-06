package zzu.bs.bean;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer bid;
	private String name;
	private String author;
	private Double price;
	private Integer discount;
	private String publishing;
	private String publishTime;
	private Integer edition;
	private Integer pageNum;
	private String isnb;
	private Integer categoryId;
	private Integer secondCategoryId;
	private String imgUrlBig;
	private String imgUrlMid;
	private String imgUrlSmall;
	private String bookDescription;
	private String bookformat;
	private String pack;
	private String editorComment;
	private String authordescription;
	private String floor;
	private String URL;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Book() {
	}

	public String getBookformat() {
		return bookformat;
	}

	public void setBookformat(String bookformat) {
		this.bookformat = bookformat;
	}

	public Book(String name, String author, Double price, Integer discount, String publishing, String publishTime,
			Integer edition, Integer pageNum, String isnb, Integer categoryId, Integer secondCategoryId,
			String imgUrlBig, String imgUrlMid, String imgUrlSmall, String bookDescription, String bookformat,
			String pack, String editorComment, String authordescription, String floor,String URL) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.discount = discount;
		this.publishing = publishing;
		this.publishTime = publishTime;
		this.edition = edition;
		this.pageNum = pageNum;
		this.isnb = isnb;
		this.categoryId = categoryId;
		this.secondCategoryId = secondCategoryId;
		this.imgUrlBig = imgUrlBig;
		this.imgUrlMid = imgUrlMid;
		this.imgUrlSmall = imgUrlSmall;
		this.bookDescription = bookDescription;
		this.bookformat = bookformat;
		this.pack = pack;
		this.editorComment = editorComment;
		this.authordescription = authordescription;
		this.floor = floor;
		this.URL = URL;
	}

	public Book(Integer bid, String name, String author, Double price, Integer discount, String publishing,
			String publishTime, Integer edition, Integer pageNum, String isnb, Integer categoryId,
			Integer secondCategoryId, String imgUrlBig, String imgUrlMid, String imgUrlSmall, String bookDescription,
			String bookformat, String pack, String editorComment, String authordescription, String floor,String URL) {
		super();
		this.bid = bid;
		this.name = name;
		this.author = author;
		this.price = price;
		this.discount = discount;
		this.publishing = publishing;
		this.publishTime = publishTime;
		this.edition = edition;
		this.pageNum = pageNum;
		this.isnb = isnb;
		this.categoryId = categoryId;
		this.secondCategoryId = secondCategoryId;
		this.imgUrlBig = imgUrlBig;
		this.imgUrlMid = imgUrlMid;
		this.imgUrlSmall = imgUrlSmall;
		this.bookDescription = bookDescription;
		this.bookformat = bookformat;
		this.pack = pack;
		this.editorComment = editorComment;
		this.authordescription = authordescription;
		this.floor = floor;
		this.URL = URL;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getIsnb() {
		return isnb;
	}

	public void setIsnb(String isnb) {
		this.isnb = isnb;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public String getImgUrlBig() {
		return imgUrlBig;
	}

	public void setImgUrlBig(String imgUrlBig) {
		this.imgUrlBig = imgUrlBig;
	}

	public String getImgUrlMid() {
		return imgUrlMid;
	}

	public void setImgUrlMid(String imgUrlMid) {
		this.imgUrlMid = imgUrlMid;
	}

	public String getImgUrlSmall() {
		return imgUrlSmall;
	}

	public void setImgUrlSmall(String imgUrlSmall) {
		this.imgUrlSmall = imgUrlSmall;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getEditorComment() {
		return editorComment;
	}

	public void setEditorComment(String editorComment) {
		this.editorComment = editorComment;
	}

	public String getAuthordescription() {
		return authordescription;
	}

	public void setAuthordescription(String authordescription) {
		this.authordescription = authordescription;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
}
