package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.OrderItem;

public interface OrderItemDao {

	//添加指定订单号的订单项
	public boolean addOrderItem(List<OrderItem> orderItemList)throws Exception;
}
