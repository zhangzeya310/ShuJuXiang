package zzu.bs.service;

import java.util.List;
import java.util.Map;

import zzu.bs.bean.Book;
import zzu.bs.dao.BookDao;
import zzu.bs.dao.BookDaoImp;
import zzu.bs.utils.BeanFactory;

public class BookServiceImp implements BookService{

	private BookDao bookDao = BeanFactory.getBean(BookDaoImp.class);
	
	@Override
	public List<Book> queryFloorBooks(String floor) throws Exception {
		return bookDao.queryBooksByFloor(floor);
	}

	@Override
	public Map<String,Object> queryBookByCidAndSid(Integer cid, Integer sid,Integer currPage,Integer pageSize) throws Exception {
		return bookDao.queryBooksByCidAndSid(cid,sid,currPage,pageSize);
	}

	@Override
	public Book queryBookById(Integer bookId) throws Exception {
		return bookDao.queryBookById(bookId);
	}
	
	@Override
	public Map<String,Object> queryByPublish(String publish,Integer currPage,Integer pageSize) throws Exception {
		return bookDao.queryByPublish(publish,currPage,pageSize);
	}

	@Override
	public Map<String,Object> queryByPrice(String price,Integer currPage,Integer pageSize) throws Exception {
		return bookDao.queryByPrice(price,currPage,pageSize);
	}

	@Override
	public Map<String,Object> queryByDiscount(String discount,Integer currPage,Integer pageSize) throws Exception {
		return bookDao.queryByDiscount(discount,currPage,pageSize);
	}

	@Override
	public Map<String, Object> queryAll(Integer currPage, Integer pageSize) throws Exception {
		return bookDao.queryAll(currPage,pageSize);
	}

	@Override
	public Map<String, Object> bookSearch(String search,Integer currPage,Integer pageSize) throws Exception {
		return bookDao.bookSearch(search,currPage,pageSize);
	}

	@Override
	public String getPictureUrl(String picture) throws Exception {
		return bookDao.getPictureUrl(picture);
	}
}
