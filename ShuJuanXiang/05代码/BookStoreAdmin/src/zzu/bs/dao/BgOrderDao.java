package zzu.bs.dao;

import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;

import java.util.List;
import java.util.Map;

public interface BgOrderDao {

    public List<Map<String,Object>> getOrders(int start, int end) throws Exception;

    public int queryOrderCount() throws Exception;

    public boolean deleteOrderByOrderid(Integer orderid) throws Exception;

    public List<OrderItem> getOrderItemByOrderSeq(String orderSeq) throws Exception;

    public Order getOrderByOrderSeq(String orderSeq) throws Exception;
}
