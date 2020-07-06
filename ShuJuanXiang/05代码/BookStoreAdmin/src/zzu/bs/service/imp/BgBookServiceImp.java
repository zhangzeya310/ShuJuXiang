package zzu.bs.service.imp;

import zzu.bs.bean.Book;
import zzu.bs.dao.BgBookDao;
import zzu.bs.dao.imp.BgBookDaoImp;
import zzu.bs.service.BgBookService;
import zzu.bs.utils.BeanFactory;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/20
 * version v1.0
 **/

public class BgBookServiceImp implements BgBookService {
    private BgBookDao bgBookDao = BeanFactory.getBean(BgBookDaoImp.class);

    @Override
    public List<Map<String, Object>> getBooks(int start, int end) throws Exception {
        return bgBookDao.getBooks(start, end);
    }

    @Override
    public int queryBookCount() throws Exception {
        return bgBookDao.queryBookCount();
    }

    @Override
    public boolean deleteBookByBookid(Integer bid) throws Exception {
        return bgBookDao.deleteBookByBookid(bid);
    }

    @Override
    public boolean updateBookByID(Integer bid, Book book) throws Exception {
        return bgBookDao.updateBookByID(bid, book);
    }

    @Override
    public boolean addBook(String[] book, Part part) throws Exception {
        return bgBookDao.addBook(book, part);
    }

    @Override
    public Book getBookByOBookId(String bid) throws Exception {
        return bgBookDao.getBookByOBookId(bid);
    }
}
