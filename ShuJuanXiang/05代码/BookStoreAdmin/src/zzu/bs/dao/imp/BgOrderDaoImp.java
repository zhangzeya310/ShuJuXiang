package zzu.bs.dao.imp;

import zzu.bs.bean.Book;
import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;
import zzu.bs.dao.BgOrderDao;
import zzu.bs.utils.JDBCUtils;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/17
 * version v1.0
 **/

public class BgOrderDaoImp implements BgOrderDao {

    @Override
    public List<Map<String, Object>> getOrders(int start, int end) throws Exception {
        String sql = "select * from t_order limit ?,?";
        return JDBCUtils.queryForList(sql, start, end);
    }

    @Override
    public int queryOrderCount() throws Exception {
        String sql = "select count(*) from t_order";
        return JDBCUtils.queryForInt(sql);
    }

    @Override
    public boolean deleteOrderByOrderid(Integer ordierid) throws Exception {
        String sql = "delete from t_order where orderid=?";
        return JDBCUtils.update(sql, ordierid);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderSeq(String orderSeq) throws Exception {
        String sql = "select * from t_order_item where orderseq=?";
        List<OrderItem> orderItems = JDBCUtils.queryForList(sql, OrderItem.class , orderSeq);
        for (OrderItem ot : orderItems){
            String bookSql = "select * from t_book where bid=?";
            Integer bid = ot.getBid();
            Book book = JDBCUtils.queryForObject(bookSql, Book.class, bid);
            ot.setBook(book);
        }
        return orderItems;
    }

    @Override
    public Order getOrderByOrderSeq(String orderSeq) throws Exception {
        String sql = "select * from t_order where orderseq=?";
        return JDBCUtils.queryForObject(sql, Order.class, orderSeq);
    }
}
