package zzu.bs.service;

import zzu.bs.bean.Book;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

public interface BgBookService {

    public List<Map<String,Object>> getBooks(int start, int end) throws Exception;

    public int queryBookCount() throws Exception;

    public boolean deleteBookByBookid(Integer bid) throws Exception;

    public boolean updateBookByID(Integer bid, Book book) throws Exception;

    public boolean addBook(String[] book, Part part) throws Exception;

    public Book getBookByOBookId(String bid) throws Exception;
}
