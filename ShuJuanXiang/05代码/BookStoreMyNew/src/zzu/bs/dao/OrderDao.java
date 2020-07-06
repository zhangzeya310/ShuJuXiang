package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;

public interface OrderDao {

	//添加订单
	public boolean addOrder(Order order) throws Exception;

	//查询某用户所有订单
	public List<Order> queryOrdersByUid(Integer uid) throws Exception;

	//根据订单号查询该订单所有订单项
	public List<OrderItem> queryOrderItemsByOrderSeq(String orderSeq) throws Exception;

	public boolean updateOrderStatus(String orderSeq,Integer status)throws Exception;

}
