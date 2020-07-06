package zzu.bs.bean;

/*
 * 临时对象，并不放在数据库中
 * 存放购物车中的一条图书项
 * */
public class Cart {

	private Book book;
	private int num;

	public Cart() {
	}

	public Cart(Book book, int num) {
		super();
		this.book = book;
		this.num = num;
	}

	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
