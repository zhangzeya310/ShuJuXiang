package zzu.bs.dao;

import zzu.bs.bean.Book;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

public interface BgBookDao {

    public List<Map<String,Object>> getBooks(int start, int end) throws Exception;

    public int queryBookCount() throws Exception;

    public boolean deleteBookByBookid(Integer orderid) throws Exception;

    public boolean updateBookByID(Integer bid, Book book) throws Exception;

    boolean addBook(String[] book, Part part) throws Exception;

    public Book getBookByOBookId(String orderSeq) throws Exception;
}
