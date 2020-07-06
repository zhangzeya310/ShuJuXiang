package zzu.bs.service.imp;

import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;
import zzu.bs.dao.BgOrderDao;
import zzu.bs.dao.imp.BgOrderDaoImp;
import zzu.bs.service.BgOrderService;
import zzu.bs.utils.BeanFactory;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/17
 * version v1.0
 **/

public class BgOrderServiceImp implements BgOrderService {
    private BgOrderDao orderDao = BeanFactory.getBean(BgOrderDaoImp.class);

    @Override
    public List<Map<String, Object>> getOrders(int start, int end) throws Exception {
        return orderDao.getOrders(start, end);
    }

    @Override
    public int queryOrderCount() throws Exception {
        return orderDao.queryOrderCount();
    }

    @Override
    public boolean deleteOrderByOrderid(Integer orderid) throws Exception {
        return orderDao.deleteOrderByOrderid(orderid);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderSeq(String orderSeq) throws Exception {
        return orderDao.getOrderItemByOrderSeq(orderSeq);
    }

    @Override
    public Order getOrderByOrderSeq(String orderSeq) throws Exception {
        return orderDao.getOrderByOrderSeq(orderSeq);
    }
}
