package zzu.bs.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;
import zzu.bs.bean.Book;
import zzu.bs.utils.JDBCUtils;
import zzu.bs.utils.SplitPage;

public class BookDaoImp implements BookDao{
	
	@Override
	public List<Book> queryBooksByFloor(String floor) throws Exception{
		String sql = "select * from t_book where floor=? order by bid desc limit 0,8";
		return JDBCUtils.queryForList(sql, Book.class, floor);
	}

	@Override
	public Map<String,Object> queryBooksByCidAndSid(Integer cid, Integer sid,Integer currPage,Integer pageSize) throws Exception {
		String bookListSql = "SELECT * from t_book where categoryid=? and secondcategoryid=? order by bid desc limit ?,?";
		String countSql = "SELECT count(*) from t_book where categoryid=? and secondcategoryid=?";
		return SplitPage.split(bookListSql,countSql,currPage,pageSize,Book.class,cid,sid);
				//JDBCUtils.queryForList(sql, Book.class, cid,sid);
	}

	@Override
	public Book queryBookById(Integer bookId) throws Exception {
		String sql = "SELECT * from t_book where bid=?";
		return JDBCUtils.queryForObject(sql, Book.class, bookId);
	}
	
	@Override
	public Map<String,Object> queryByPublish(String publish,Integer currPage,Integer pageSize) throws Exception {
		String bookListSql = "SELECT * from t_book where publishing=? order by bid desc limit ?,?";
		String countSql = "SELECT count(*) from t_book where publishing=?";
		return SplitPage.split(bookListSql,countSql,currPage,pageSize,Book.class,publish);
				//JDBCUtils.queryForList(sql, Book.class, publish);
	}

	@Override
	public Map<String,Object> queryByPrice(String price,Integer currPage,Integer pageSize) throws Exception {
		String [] bookPrice = price.split("-");
		int  bookPriceInt[] = new int[2];
		for(int i=0;i<bookPrice.length;i++)
		{
			bookPriceInt[i] = Integer.parseInt(bookPrice[i]);
		}
		String bookListSql = "SELECT * from t_book where price*discount*0.1>=? AND price*discount*0.1<=? order by bid desc limit ?,?";
		String countSql = "SELECT count(*) from t_book where price*discount*0.1>? AND price*discount*0.1<=?";
		return SplitPage.split(bookListSql,countSql,currPage,pageSize,Book.class,bookPriceInt[0],bookPriceInt[1]);
				//JDBCUtils.queryForList(sql, Book.class,bookPriceInt[0],bookPriceInt[1]);
	}

	@Override
	public Map<String,Object> queryByDiscount(String discount,Integer currPage,Integer pageSize) throws Exception {
		String [] bookDiscount = discount.split("-");
		int  bookDiscountInt[] = new int[2];
		for(int i=0;i<bookDiscount.length;i++)
		{
			bookDiscountInt[i] = Integer.parseInt(bookDiscount[i]);
		}
		String bookListSql = "SELECT * from t_book where discount>=? AND discount<=? order by bid desc limit ?,?";
		String countSql = "select count(*) from t_book where discount>=? AND discount<=?";
		return SplitPage.split(bookListSql,countSql,currPage,pageSize,Book.class,bookDiscountInt[0],bookDiscountInt[1]);//JDBCUtils.queryForList(sql, Book.class,bookDiscountInt[0],bookDiscountInt[1]);
	}

	@Override
	public Map<String, Object> queryAll(Integer currPage,Integer pageSize) throws Exception {
		String bookListSql = "SELECT * from t_book order by bid desc limit ?,?";
		String countSql = "select count(*) from t_book";
		return SplitPage.split(bookListSql,countSql,currPage,pageSize,Book.class);
	}

	@Override
	public Map<String, Object> bookSearch(String search, Integer currPage,Integer pageSize) throws Exception {
		String bookListSql = "select * from t_book where (name like concat('%',?,'%')) or (author like concat('%',?,'%')) or (publishing like concat('%',?,'%')) order by bid desc limit ?,?";
		String countSql = "select count(*) from t_book where (name like concat('%',?,'%')) or (author like concat('%',?,'%')) or (publishing like concat('%',?,'%'))";
		return SplitPage.split(bookListSql,countSql,currPage,pageSize,Book.class,search,search,search);
	}

	@Override
	public String getPictureUrl(String picture) throws Exception {
		int i;
		FileInputStream fis = new FileInputStream(picture);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		byte [] buf = new byte[1024];
		while((i=fis.read(buf))!=-1) {
			b.write(buf);
		}
		byte b2[] = b.toByteArray();
		BASE64Encoder encoder = new BASE64Encoder();
		String url = "data:image/jpeg;base64,"+encoder.encode(b2);
		return url;
	}
}

//		public  Map<String, Object> bookSearch(String search,Integer currPage,Integer pageSize) throws Exception {
//			String bookListSql = "select * from t_book where (name like concat('%',?,'%')) or (author like concat('%',?,'%')) or (publishing like concat('%',?,'%')) order by bid desc";
//			return JDBCUtils.queryForList(bookListSql,Book.class,search,search,search);
//	}
//}
