package zzu.bs.service;

import java.util.List;

import zzu.bs.bean.OrderItem;

public interface OrderItemService {

	//根据orderSeq添加订单项
	public boolean addOrderItem(List<OrderItem> orderItemList)throws Exception;
}
