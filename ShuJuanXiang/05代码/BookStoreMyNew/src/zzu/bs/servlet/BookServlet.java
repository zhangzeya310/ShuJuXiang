package zzu.bs.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zzu.bs.bean.Book;
import zzu.bs.bean.Category;
import zzu.bs.service.BookService;
import zzu.bs.service.BookServiceImp;
import zzu.bs.service.TCategoryService;
import zzu.bs.service.TCategoryServiceImp;
import zzu.bs.utils.BeanFactory;
import zzu.bs.utils.InitialValueUtils;

@WebServlet("/BookServlet") // BookServlet?doing=queryTwoCategory
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookService bookService = BeanFactory.getBean(BookServiceImp.class);
	private TCategoryService tCategoryService = BeanFactory.getBean(TCategoryServiceImp.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doing = req.getParameter("doing");
		switch (doing) {
		case "queryAll":
			queryAll(req, resp) ;
			break;
		case "queryTwoCategory":
			queryTwoCategory(req, resp);
			break;
		case "queryBooksByCidAndSid":
			queryBooksByCidAndSid(req, resp);
			break;
		case "queryByPublish":
			queryByPublish(req, resp);
			break;
		case "queryByPrice":
			queryByPrice(req, resp);
			break;
		case "queryByDiscount":
			queryByDiscount(req, resp);
			break;
		case "showItem":
			showItem(req, resp);
			break;
		case "bookSearch":
			bookSearch(req,resp);
			break;
		default:
			break;
		}
	}
	
	public boolean SetupUrl(List<Book> list) {
		try {
			for(Book b:list) {
				String picture = b.getImgUrlMid();
				b.setURL(bookService.getPictureUrl(picture));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//搜索图书信息
	@SuppressWarnings("unchecked")
	private void bookSearch(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String search2 = req.getParameter("search");
			String search = new String(search2.getBytes("ISO-8859-1"), "utf-8");
			Integer currPage = getCurrPage(req);
			Integer pageSize = 5;
			Map<String, Object> bookSearchList = bookService.bookSearch(search, currPage, pageSize);
			HttpSession session = req.getSession();
			SetupUrl((List<Book>) (bookSearchList.get("list")));
			session.setAttribute("booksCidSidList", (List<Book>) (bookSearchList.get("list")));
			Integer totalPage = (Integer) bookSearchList.get("totalPage");
			setPage(session, currPage, totalPage);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currPage", currPage);
			session.setAttribute("splitFormAction", "BookServlet?doing=bookSearch&search=" + search2);
			resp.sendRedirect(session.getServletContext().getContextPath() + "/search.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//显示全部书籍信息
	@SuppressWarnings("unchecked")
	private void queryAll(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer currPage = getCurrPage(req);
			Integer pageSize = 5;
			Map<String, Object> bookListSearch = bookService.queryAll(currPage,pageSize);
			HttpSession session = req.getSession();
			SetupUrl((List<Book>) (bookListSearch.get("list")));
			session.setAttribute("booksCidSidList", (List<Book>) (bookListSearch.get("list")));
			Integer totalPage = (Integer) bookListSearch.get("totalPage");
			setPage(session, currPage, totalPage);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currPage", currPage);
			session.setAttribute("splitFormAction", "BookServlet?doing=queryAll");
			resp.sendRedirect(session.getServletContext().getContextPath() + "/search.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 点击书名或图片链接，显示相关书籍信息
	@SuppressWarnings("unchecked")
	private void showItem(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bidItem = req.getParameter("bid");
			int bid = Integer.parseInt(bidItem);
			Book bookById = bookService.queryBookById(bid);
			String picture = bookById.getImgUrlMid();
			HttpSession session = req.getSession();
			bookById.setURL(bookService.getPictureUrl(picture));
			session.setAttribute("book", bookById);
			resp.sendRedirect(session.getServletContext().getContextPath() + "/item.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据折扣查图书
	@SuppressWarnings("unchecked")
	private void queryByDiscount(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String discount = req.getParameter("discount");
			Integer currPage = getCurrPage(req);
			Integer pageSize = 5;
			Map<String, Object> bookListByDiscount = bookService.queryByDiscount(discount, currPage, pageSize);
			HttpSession session = req.getSession();
			SetupUrl((List<Book>) (bookListByDiscount.get("list")));
			session.setAttribute("booksCidSidList", (List<Book>) (bookListByDiscount.get("list")));
			Integer totalPage = (Integer) bookListByDiscount.get("totalPage");
			System.out.println("totalPage=" + totalPage);
			setPage(session, currPage, totalPage);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currPage", currPage);
			session.setAttribute("splitFormAction", "BookServlet?doing=queryByDiscount&discount=" + discount);
			resp.sendRedirect(session.getServletContext().getContextPath() + "/search.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从req中得到当前页
	private Integer getCurrPage(HttpServletRequest req) {
		String currPageStr = req.getParameter("currPage");
		Integer currPage = null;
		if (currPageStr == null || currPageStr == "") {
			currPage = 1;
		} else {
			currPage = Integer.parseInt(currPageStr);
		}
		return currPage;
	}

	// 设置页面上的5个分页链接
	private void setPage(HttpSession session, Integer currPage, Integer totalPage) {
		Integer[] linksValue = new Integer[5];
		if (totalPage <= 5) {
			for (int i = 1; i <= totalPage; i++) {
				linksValue[i - 1] = i;
			}
			for (int i = totalPage + 1; i <= 5; i++) {
				linksValue[i - 1] = null;
			}
			session.setAttribute("linksValue", linksValue);
			session.setAttribute("blueIndex", currPage);
		} else if (currPage == 1 || currPage == 2 || currPage == 3) {
			for (int i = 1; i <= 5; i++) {
				linksValue[i - 1] = i;
			}
			session.setAttribute("linksValue", linksValue);
			session.setAttribute("blueIndex", currPage);
		} else if (currPage == totalPage - 2 || currPage == totalPage - 1 || currPage == totalPage) {
			for (int i = 1; i <= 5; i++) {
				linksValue[i - 1] = totalPage - 5 + i;
			}
			session.setAttribute("linksValue", linksValue);
			if (currPage == totalPage) {
				session.setAttribute("blueIndex", 5);
			} else if (currPage == totalPage - 1) {
				session.setAttribute("blueIndex", 4);
			} else {
				session.setAttribute("blueIndex", 3);
			}
		} else {
			int index = -2;
			for (int i = 1; i <= 5; i++) {
				linksValue[i - 1] = currPage + index;
				index++;
			}
			session.setAttribute("linksValue", linksValue);
			session.setAttribute("blueIndex", 3);
		}
		System.out.println("setPage:" + Arrays.toString(linksValue));
	}

	// 根据图书价格查询书籍信息，并显示结果
	@SuppressWarnings("unchecked")
	private void queryByPrice(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String price = req.getParameter("price");
			Integer currPage = getCurrPage(req);
			Integer pageSize = 5;
			Map<String, Object> bookListByPrice = bookService.queryByPrice(price, currPage, pageSize);
			HttpSession session = req.getSession();
			SetupUrl((List<Book>) (bookListByPrice.get("list")));
			session.setAttribute("booksCidSidList", (List<Book>) (bookListByPrice.get("list")));
			Integer totalPage = (Integer) bookListByPrice.get("totalPage");
			System.out.println("totalPage=" + totalPage);
			setPage(session, currPage, totalPage);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currPage", currPage);
			session.setAttribute("splitFormAction", "BookServlet?doing=queryByPrice&price=" + price);
			resp.sendRedirect(session.getServletContext().getContextPath() + "/search.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据图书出版社查询书籍信息，并显示结果
	@SuppressWarnings("unchecked")
	private void queryByPublish(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String publishing = req.getParameter("publish");
			Integer currPage = getCurrPage(req);
			Integer pageSize = 5;
			Map<String, Object> bookListByPublish = bookService.queryByPublish(publishing, currPage, pageSize);
			HttpSession session = req.getSession();
			SetupUrl((List<Book>) (bookListByPublish.get("list")));
			session.setAttribute("booksCidSidList", (List<Book>) (bookListByPublish.get("list")));
			Integer totalPage = (Integer) bookListByPublish.get("totalPage");
			System.out.println("totalPage=" + totalPage);
			setPage(session, currPage, totalPage);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currPage", currPage);
			resp.sendRedirect(session.getServletContext().getContextPath() + "/search.jsp");
			session.setAttribute("splitFormAction", "BookServlet?doing=queryByPublish&publish=" + publishing);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据一级目录id和二级目录ID查出对应图书
	@SuppressWarnings("unchecked")
	private void queryBooksByCidAndSid(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			// 得到cid和sid，并查数据库得到对应图书
			String categoryId = req.getParameter("cid");
			String secondCategoryId = req.getParameter("sid");
			System.out.println(categoryId + "=" + secondCategoryId);
			Integer currPage = getCurrPage(req);
			System.out.println("currPage=" + currPage);
			Integer pageSize = 5;
			Map<String, Object> booksCidSidList = bookService.queryBookByCidAndSid(Integer.parseInt(categoryId),
					Integer.parseInt(secondCategoryId), currPage, pageSize);
			SetupUrl((List<Book>) (booksCidSidList.get("list")));
			session.setAttribute("booksCidSidList", (List<Book>) (booksCidSidList.get("list")));
			Integer totalPage = (Integer) booksCidSidList.get("totalPage");
			setPage(session, currPage, totalPage);
			session.setAttribute("totalPage", totalPage);
			session.setAttribute("currPage", currPage);

			session.setAttribute("splitFormAction",
					"BookServlet?doing=queryBooksByCidAndSid&cid=" + categoryId + "&sid=" + secondCategoryId);
			resp.sendRedirect(session.getServletContext().getContextPath() + "/search.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查出两级目录
	private void queryTwoCategory(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 查出三层图书
			List<Book> floorBooksList1 = bookService.queryFloorBooks("1");
			List<Book> floorBooksList2 = bookService.queryFloorBooks("2");
			List<Book> floorBooksList3 = bookService.queryFloorBooks("3");
			SetupUrl((List<Book>) floorBooksList1);
			SetupUrl((List<Book>) floorBooksList2);
			SetupUrl((List<Book>) floorBooksList3);
			HttpSession session = req.getSession();
			session.setAttribute("floorBooksList1", floorBooksList1);
			session.setAttribute("floorBooksList2", floorBooksList2);
			session.setAttribute("floorBooksList3", floorBooksList3);
			// 查出目录结构
			List<Category> category = tCategoryService.queryAllTCategory();
			session.setAttribute("categorys", category);
			// 得到初始设置的出版社
			List<String> publishs = InitialValueUtils.getInitialPublishs();
			// 得到初始设置的价格
			List<String> prices = InitialValueUtils.getInitialPrices();
			// 得到初始设置的折扣
			List<String> discounts = InitialValueUtils.getInitialDiscounts();
			session.setAttribute("publishs", publishs);
			session.setAttribute("prices", prices);
			session.setAttribute("discounts", discounts);
			// 重定向到首页
			resp.sendRedirect(session.getServletContext().getContextPath() + "/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
