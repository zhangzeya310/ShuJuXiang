package zzu.bs.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zzu.bs.bean.Book;
import zzu.bs.bean.Cart;
import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;
import zzu.bs.bean.User;
import zzu.bs.service.BookService;
import zzu.bs.service.BookServiceImp;
import zzu.bs.service.OrderItemService;
import zzu.bs.service.OrderItemServiceImp;
import zzu.bs.service.OrderService;
import zzu.bs.service.OrderServiceImp;
import zzu.bs.utils.BeanFactory;

@WebServlet("/OrderServlet") 
public class OrderServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private OrderService orderService = BeanFactory.getBean(OrderServiceImp.class);
	private OrderItemService orderItemService = BeanFactory.getBean(OrderItemServiceImp.class);
	private BookService bookService = BeanFactory.getBean(BookServiceImp.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doing = req.getParameter("doing");
		switch (doing) {
		case "addOrder":
			addOrder(req, resp);
			break;
		case "queryAllOrder":
			queryAllOrder(req, resp);
			break;
		case "sure":
			sureOrder(req, resp);
			break;
		default:
			break;
		}
	}

	//修改订单状态为已完成
	private void sureOrder(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String orderSeq = req.getParameter("orderSeq");
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Order> orderList = (List<Order>) session.getAttribute("orders");
			boolean b=false;
			for(Order o:orderList){
				String orderSeq2 = o.getOrderSeq();
				if(orderSeq.equals(orderSeq2)){
					o.setStatus(2);
					System.out.println("orderSeq="+orderSeq);
					b = orderService.updateOrderStatus(orderSeq,2);
					break;
				}
			}
			if(b) {
				resp.sendRedirect(session.getServletContext().getContextPath()+"/OrderServlet?doing=queryAllOrder");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//查询当前用户所有订单
	private void queryAllOrder(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			Integer uid = user.getUid();
			List<Order> orderList = orderService.queryOrdersByUid(uid);
			for(Order o:orderList){
				List<OrderItem> orderItems = orderService.queryOrderItemsByOrderSeq(o.getOrderSeq());
				for(OrderItem ot:orderItems){
					Book book = bookService.queryBookById(ot.getBid());
					ot.setBook(book);
				}			
				o.setOrderItemList(orderItems);
			}
			session.setAttribute("orders", orderList);
			resp.sendRedirect(session.getServletContext().getContextPath()+"/order.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addOrder(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Date date = new Date();
			long time = date.getTime();
			String orderSeq = String.valueOf(time);//订单号
			User user = (User) req.getSession().getAttribute("user");
			if(user==null) {
				resp.sendRedirect(req.getSession().getServletContext().getContextPath()+"/login.jsp");
				return;
			}
			Integer uid = user.getUid();//用户id
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
			int num = cartList.size();//图书种类数
			String date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);//订单日期
			double sumPrice=0.0;//总价
			for(int i=0;i<cartList.size();i++){
				Cart cart = cartList.get(i);
				Book b = cart.getBook();
				sumPrice += b.getPrice()*b.getDiscount()*cart.getNum()*0.1;
			}
			int status = 0;//订单状态。0：订单已下，1：已发货（交易完成）
			String address = new String(user.getAddress().getBytes("ISO-8859-1"), "utf-8");
			Order order = new Order(orderSeq,uid,num,date2,sumPrice,status,address);
			//order.setCartList(cartList);
			System.out.println(order);
			//添加订单
			boolean addOrder = orderService.addOrder(order);
			System.out.println("addOrder="+addOrder);
			
			//添加订单项
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			for(int i=0;i<cartList.size();i++){
				Cart cart = cartList.get(i);
				Integer bid = cart.getBook().getBid();
				int num2 = cart.getNum();
				OrderItem orderItem = new OrderItem(orderSeq,bid,num2);
				orderItemList.add(orderItem);
			}
			boolean addOrderItem = orderItemService.addOrderItem(orderItemList);
			System.out.println("addOrderItem="+addOrderItem);
			if(addOrder&&addOrderItem){
				//设置当前订单。移除购物车。并跳转
				List<Cart> orderNow = cartList;
				session.setAttribute("orderNow", orderNow);
				double orderSumPrice=0.0;
				for(int i=0;i<orderNow.size();i++){
					Cart cart = orderNow.get(i);
					Book b = cart.getBook();
					orderSumPrice += b.getPrice()*b.getDiscount()*cart.getNum()*0.1;
				}
				session.setAttribute("orderSumPrice", orderSumPrice);
				session.setAttribute("cartList", null);
				resp.sendRedirect(session.getServletContext().getContextPath()+"/orderItem.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
