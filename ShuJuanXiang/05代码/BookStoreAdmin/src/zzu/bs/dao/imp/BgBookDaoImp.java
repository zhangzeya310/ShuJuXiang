package zzu.bs.dao.imp;

import zzu.bs.bean.Book;
import zzu.bs.dao.BgBookDao;
import zzu.bs.utils.JDBCUtils;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/20
 * version v1.0
 **/

public class BgBookDaoImp implements BgBookDao {

    @Override
    public List<Map<String, Object>> getBooks(int start, int end) throws Exception {
        String sql = "select * from t_book limit ?,?";
        return JDBCUtils.queryForList(sql, start, end);
    }

    @Override
    public int queryBookCount() throws Exception {
        String sql = "select count(*) from t_book";
        return JDBCUtils.queryForInt(sql);
    }

    @Override
    public boolean deleteBookByBookid(Integer orderid) throws Exception {

        String sql = "delete from t_book where bid=?";
        return JDBCUtils.update(sql,orderid);
    }

    @Override
    public boolean updateBookByID(Integer bid, Book book) throws Exception {
        //        update s_emp set salary=:salary,last_name=:name where id=:id
//        private Integer bid;
//        private String name;
//        private String author;
//        private Double price;
//        private Integer discount;
//        private String publishing;
//        private String publishTime;
//        private Integer edition;
//        private Integer pageNum;
//        private String isnb;
//        private Integer categoryId;
//        private Integer secondCategoryId;
//        private String imgUrlBig;
//        private String imgUrlMid;
//        private String imgUrlSmall;bookDescription;bookformat;pack;editorComment;authordescription;floor;
        String sql = "update t_book set name=:name,author=:author,price=:price,discount=:discount,publishing=:publishing,publishtime=:publishTime,edition=:edition," +
                "pagenum=:pageNum,isnb=:isnb,categoryid=:categoryId,secondCategoryid=:secondCategoryId,imgUrlbig=:imgUrlBig,imgUrlmid=:imgUrlMid," +
                "imgUrlsmall=:imgUrlSmall,bookdescription=:bookDescription,bookformat=:bookformat,pack=:pack,editorcomment=:editorComment,authordescription=:authordescription," +
                "floor=:floor where bid=:bid";
        return JDBCUtils.update(book, sql);
    }

    @Override
    public Book getBookByOBookId(String bid) throws Exception {
        String sql = "select * from t_book where bid=?";
        return JDBCUtils.queryForObject(sql, Book.class,bid);
    }

    @Override
    public boolean addBook(String[] book, Part part) throws Exception {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));

        InputStream in = part.getInputStream();
        OutputStream out = new FileOutputStream("/upload/" + fileName);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
         String s = "insert into t_book(name,author,price,discount,publishing,publishtime,edition,pagenum,isnb,categoryid,secondcategoryid,bookdescription," +
                "editorcomment,authordescription,imgurlmid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return JDBCUtils.update(s,book);
    }
}
