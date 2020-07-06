package zzu.bs.service;

import java.util.List;

import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;
import zzu.bs.dao.OrderDao;
import zzu.bs.dao.OrderDaoImp;
import zzu.bs.utils.BeanFactory;

public class OrderServiceImp implements OrderService{

	private OrderDao orderDao = BeanFactory.getBean(OrderDaoImp.class);
	
	@Override
	public boolean addOrder(Order order) throws Exception {
		return orderDao.addOrder(order);
	}

	@Override
	public List<Order> queryOrdersByUid(Integer uid) throws Exception {
		return orderDao.queryOrdersByUid(uid);
	}

	@Override
	public List<OrderItem> queryOrderItemsByOrderSeq(String orderSeq) throws Exception {
		return orderDao.queryOrderItemsByOrderSeq(orderSeq);
	}

	@Override
	public boolean updateOrderStatus(String orderSeq,Integer status) throws Exception {
		return orderDao.updateOrderStatus(orderSeq,status);
	}

}
