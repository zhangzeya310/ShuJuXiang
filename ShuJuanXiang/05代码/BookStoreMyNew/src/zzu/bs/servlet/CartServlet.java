package zzu.bs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zzu.bs.bean.Book;
import zzu.bs.bean.Cart;
import zzu.bs.service.BookService;
import zzu.bs.service.BookServiceImp;
import zzu.bs.utils.BeanFactory;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private BookService bookService = BeanFactory.getBean(BookServiceImp.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doing = req.getParameter("doing");System.out.println("doing="+doing);
		switch(doing){
		case "addCart":addCart(req,resp);break;
		case "deleteCart":deleteCart(req,resp);break;
		case "subOne":subOne(req,resp);break;
		case "addOne":addOne(req,resp);break;
		default:break;
		}
	}

	//将对应图书的数量加一。ajax
	private void addOne(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bookId = req.getParameter("bookId");
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
			int bookNum = -1;
			for(int i=0;i<cartList.size();i++){
				Cart tempCart = cartList.get(i);
				if(Integer.parseInt(bookId)==tempCart.getBook().getBid()){
					bookNum = tempCart.getNum()+1;
					if(bookNum<=0){
						resp.getWriter().write("-1");
						break;
					}else{
						tempCart.setNum(bookNum);
						Book book = tempCart.getBook();
						//得到页面上的小计
						double sum = book.getPrice()*book.getDiscount()*tempCart.getNum()*0.1;
						//得到总价
						double cartSumPrice = getCartSumPrice(cartList);
						resp.getWriter().write(bookNum+"-"+sum+"-"+cartSumPrice);
						break;						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//将对应图书的数量减一。ajax
	private void subOne(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bookId = req.getParameter("bookId");
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
			int bookNum = -1;
			for(int i=0;i<cartList.size();i++){
				Cart tempCart = cartList.get(i);
				if(Integer.parseInt(bookId)==tempCart.getBook().getBid()){
					bookNum = tempCart.getNum()-1;
					if(bookNum<=0){
						resp.getWriter().write("-1");
						break;
					}else{
						tempCart.setNum(bookNum);
						Book book = tempCart.getBook();
						//得到页面上的小计
						double sum = book.getPrice()*book.getDiscount()*tempCart.getNum()*0.1;
						//得到总价
						double cartSumPrice = getCartSumPrice(cartList);
						resp.getWriter().write(bookNum+"-"+sum+"-"+cartSumPrice);//bookNum+""。必须加""空串，不然传不过去。？？？
						break;						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//添加图书到购物车中
	private void addCart(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bookId = req.getParameter("bookId");System.out.println("bookId="+bookId);
			String bookNum = req.getParameter("bookNum");System.out.println("bookNum="+bookNum);
			Book book = bookService.queryBookById(Integer.parseInt(bookId));
			String picture = book.getImgUrlMid();
			book.setURL(bookService.getPictureUrl(picture));
			HttpSession session = req.getSession();
			Cart cart = new Cart(book,Integer.parseInt(bookNum));
			@SuppressWarnings("unchecked")
			List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
			if(cartList==null){
				cartList = new ArrayList<Cart>();
			}
			boolean isHaveThis = false;//是否cartList中有当前得到的图书。true：是
			for(int i=0;i<cartList.size();i++){
				Cart tempCart = cartList.get(i);
				if(Integer.parseInt(bookId)==tempCart.getBook().getBid()){
					int tempNum = tempCart.getNum()+Integer.parseInt(bookNum);
					tempCart.setNum(tempNum);
					isHaveThis = true;//cartList中有当前图书
					break;
				}
			}
			if(!isHaveThis){
				cartList.add(cart);				
			}
			session.setAttribute("cartList", cartList);
			double cartSumPrice = getCartSumPrice(cartList);
			session.setAttribute("cartSumPrice", cartSumPrice);
			
			@SuppressWarnings("unchecked")
			List<Cart> list = (List<Cart>) session.getAttribute("cartList");
			for(int i=0;i<list.size();i++){
				Cart c2 = list.get(i);
				System.out.println(c2.getNum()+"==="+c2.getBook());
			}
			
			resp.sendRedirect(session.getServletContext().getContextPath()+"/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//从购物车中删除图书
	private void deleteCart(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bookId = req.getParameter("bookId");
			System.out.println(bookId);
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
			//要删除的cart的下标
			int deleteIndex=-1;
			for(int i=0;i<cartList.size();i++){
				Cart tempCart = cartList.get(i);
				if(Integer.parseInt(bookId)==tempCart.getBook().getBid()){
					deleteIndex = i;
					break;
				}
			}
			cartList.remove(deleteIndex);
			double cartSumPrice = getCartSumPrice(cartList);
			session.setAttribute("cartSumPrice", cartSumPrice);
			resp.sendRedirect(session.getServletContext().getContextPath()+"/cart.jsp");
		} catch (Exception e) {
			try {
				resp.sendRedirect(req.getSession().getServletContext().getContextPath()+"/index.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	//得到购物车中图书的总价
	private double getCartSumPrice(List<Cart> cartList){
		try {
			double sumPrice=0.0;
			for(int i=0;i<cartList.size();i++){
				Cart cart = cartList.get(i);
				Book b = cart.getBook();
				sumPrice += b.getPrice()*b.getDiscount()*cart.getNum()*0.1;
			}
			return sumPrice;
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

}
