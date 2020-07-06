package zzu.bs.service;

import java.util.List;

import zzu.bs.bean.OrderItem;
import zzu.bs.dao.OrderItemDao;
import zzu.bs.dao.OrderItemDaoImp;
import zzu.bs.utils.BeanFactory;

public class OrderItemServiceImp implements OrderItemService{

	private OrderItemDao orderItemDao = BeanFactory.getBean(OrderItemDaoImp.class);
	
	@Override
	public boolean addOrderItem(List<OrderItem> orderItemList) throws Exception {
		return orderItemDao.addOrderItem(orderItemList);
	}

	
}
