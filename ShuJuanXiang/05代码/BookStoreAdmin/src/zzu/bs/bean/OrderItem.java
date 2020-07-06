package zzu.bs.bean;

import java.io.Serializable;

public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer itemId;
	private String orderSeq;
	private Integer bid;
	private Integer amount;
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public OrderItem() {
	}

	public OrderItem(Integer itemId, String orderSeq, Integer bid, Integer amount) {
		this.itemId = itemId;
		this.orderSeq = orderSeq;
		this.bid = bid;
		this.amount = amount;
	}

	public OrderItem(String orderSeq, Integer bid, Integer amount) {
		this.orderSeq = orderSeq;
		this.bid = bid;
		this.amount = amount;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderItem{" +
				"itemId=" + itemId +
				", orderSeq='" + orderSeq + '\'' +
				", bid=" + bid +
				", amount=" + amount +
				", book=" + book +
				'}';
	}
}
