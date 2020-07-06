package zzu.bs.service;

import java.util.List;
import java.util.Map;

import zzu.bs.bean.Book;

public interface BookService {

	//根据楼层查Book
	public List<Book> queryFloorBooks(String floor)throws Exception;
	
	//根据一级目录id和二级目录id查图书
	public Map<String,Object> queryBookByCidAndSid(Integer cid,Integer sid,Integer currPage,Integer pageSize)throws Exception;
	
	//根据图书id查图书
	public Book queryBookById(Integer bookId)throws Exception;
	
	//根据出版社查图书
	public Map<String,Object> queryByPublish(String publish,Integer currPage,Integer pageSize)throws Exception;
		 
	//根据价格查图书
	public Map<String,Object> queryByPrice(String price,Integer currPage,Integer pageSize)throws Exception;
		
	//分页。根据折扣查图书Integer totalPage = map.get("totalPage");List<Book> list = map.get("bookList");
	public Map<String,Object> queryByDiscount(String discount,Integer currPage,Integer pageSize)throws Exception;
	
	//查询全部图书信息
	public Map<String,Object> queryAll(Integer currPage,Integer pageSize)throws Exception;
	
	//图书模糊查询
	public Map<String, Object> bookSearch(String search,Integer currPage,Integer pageSize)throws Exception;
	
	//get图书URL
	public String getPictureUrl(String picture) throws Exception;
}
