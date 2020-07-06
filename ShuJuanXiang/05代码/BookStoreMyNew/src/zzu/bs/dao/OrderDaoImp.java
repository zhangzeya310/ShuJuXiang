package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;
import zzu.bs.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public boolean addOrder(Order order) throws Exception {
		String sql = "insert into t_order(orderseq,uid,num,date,total,status,address) VALUES(?,?,?,?,?,?,?)";
		return JDBCUtils.update(sql, order.getOrderSeq(),order.getUid(),order.getNum(),order.getDate(),order.getTotal(),order.getStatus(),order.getAddress());
	}

	@Override
	public List<Order> queryOrdersByUid(Integer uid) throws Exception {
		String sql = "select * from t_order where uid=?";
		return JDBCUtils.queryForList(sql, Order.class, uid);
	}

	@Override
	public List<OrderItem> queryOrderItemsByOrderSeq(String orderSeq) throws Exception {
		String sql = "select * from t_order_item where orderseq=?";
		return JDBCUtils.queryForList(sql, OrderItem.class, orderSeq);
	}

	@Override
	public boolean updateOrderStatus(String orderSeq,Integer status) throws Exception {
		String sql = "update t_order set status=? where orderseq=?";
		return JDBCUtils.update(sql, status,orderSeq);
	}

}
