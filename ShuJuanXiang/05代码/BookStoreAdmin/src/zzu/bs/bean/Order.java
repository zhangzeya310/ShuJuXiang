package zzu.bs.bean;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private String orderSeq;
	private Integer uid;
	private Integer num;
	private String date;
	private Double total;
	private Integer status;
	private String address;
	private List<Cart> cartList;//为方便在事务中进行数据库操作而设置此项
	private List<OrderItem> orderItemList;//为方便在事务中进行数据库操作而设置此项

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	public Order() {
	}

	public Order(String orderSeq, Integer uid, Integer num, String date, Double total, Integer status, String address) {
		this.orderSeq = orderSeq;
		this.uid = uid;
		this.num = num;
		this.date = date;
		this.total = total;
		this.status = status;
		this.address = address;
	}

	public Order(Integer orderId, String orderSeq, Integer uid, Integer num, String date, Double total, Integer status,
			String address) {
		this.orderId = orderId;
		this.orderSeq = orderSeq;
		this.uid = uid;
		this.num = num;
		this.date = date;
		this.total = total;
		this.status = status;
		this.address = address;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderSeq=" + orderSeq + ", uid=" + uid + ", num=" + num + ", date="
				+ date + ", total=" + total + ", status=" + status + ", address=" + address + "]";
	}

}
