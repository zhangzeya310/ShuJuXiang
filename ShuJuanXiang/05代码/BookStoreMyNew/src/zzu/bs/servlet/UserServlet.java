package zzu.bs.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zzu.bs.bean.User;
import zzu.bs.service.UserService;
import zzu.bs.service.UserServiceImp;
import zzu.bs.utils.BeanFactory;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = BeanFactory.getBean(UserServiceImp.class);
																				
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doing = req.getParameter("doing");
		switch (doing) {
		case "login":
			userLogin(req, resp);
			break;
		case "registe":
			userRegiste(req, resp);
			break;
		case "exitUser":
			exitUser(req,resp);
			break;
		case "reviseUser":
			reviseUser(req,resp);
			break;
		default: break;
		}
	}

	private void reviseUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			User userMy = userService.checkUser(email, password);
			if(userMy!=null) {
			String phone = req.getParameter("phone");
			String nickname = req.getParameter("nickname");
			nickname = new String(nickname.getBytes("ISO-8859-1"), "utf-8");
			String passwordNew = req.getParameter("passwordNew");
			String address = req.getParameter("address");
			address = new String(address.getBytes("ISO-8859-1"), "utf-8");
			if(userService.checkNickname(email)) {
				if(userService.reviseUser(email, nickname, passwordNew, address, phone)) {
					HttpSession session = req.getSession();
					resp.sendRedirect(session.getServletContext().getContextPath() + "/UserServlet?doing=exitUser");
				}else {
					req.setAttribute("user5", "No");
					req.getRequestDispatcher("/person.jsp").forward(req, resp);
				}
			}else {
				req.setAttribute("user3", "No");
				req.getRequestDispatcher("/person.jsp").forward(req, resp);
			}
		}else {
			req.setAttribute("user1", "No");
			req.getRequestDispatcher("/person.jsp").forward(req, resp);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exitUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect(session.getServletContext().getContextPath() + "/BookServlet?doing=queryTwoCategory");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 用户注册
	private void userRegiste(HttpServletRequest req, HttpServletResponse resp) {
		try {
			/*req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");*/
			String email = req.getParameter("email");
			String nickname = req.getParameter("nickname");
			nickname = new String(nickname.getBytes("ISO-8859-1"), "utf-8");
			String password = req.getParameter("password");
			String address = req.getParameter("address");
			System.out.println("address=" + address);
			address = new String(address.getBytes("ISO-8859-1"), "utf-8");
			Date d = new Date();
			String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d);
			if (userService.checkNickname(email)) {
				//用户已存在
				req.setAttribute("user3", "No");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			} else {
				if (userService.registerUser(email, nickname, password, address, date)) {
					//注册成功
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				} else {
					//注册失败
					req.setAttribute("user5", "No");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 用户登录
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			User user = userService.checkUser(email, password);
			if (user == null) {
				//用户不存在
				req.setAttribute("user2", "No");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				//登录成功
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect(session.getServletContext().getContextPath() + "/BookServlet?doing=queryTwoCategory");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
