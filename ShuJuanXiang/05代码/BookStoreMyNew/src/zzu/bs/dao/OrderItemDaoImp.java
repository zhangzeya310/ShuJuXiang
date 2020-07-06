package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.OrderItem;
import zzu.bs.utils.JDBCUtils;

public class OrderItemDaoImp implements OrderItemDao{

	@Override
	public boolean addOrderItem(List<OrderItem> orderItemList) throws Exception {
		String sql = "insert into t_order_item(orderseq,bid,amount) values(?,?,?)";
		for(int i=0;i<orderItemList.size();i++){
			OrderItem orderItem = orderItemList.get(i);
			boolean update = JDBCUtils.update(sql, orderItem.getOrderSeq(),orderItem.getBid(),orderItem.getAmount());
			if(!update){
				return false;
			}
		}
		return true;
	}

}
