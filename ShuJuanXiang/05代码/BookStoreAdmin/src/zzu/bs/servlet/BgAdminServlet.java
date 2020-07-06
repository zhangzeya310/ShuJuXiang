package zzu.bs.servlet;

import zzu.bs.bean.Admin;
import zzu.bs.service.AdminService;
import zzu.bs.service.imp.AdminServiceImp;
import zzu.bs.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BgAdminServlet" ,urlPatterns = {"/admin/login.do"})
public class BgAdminServlet extends HttpServlet {

    private AdminService adminService = BeanFactory.getBean(AdminServiceImp.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat = request.getParameter("doWhat");
        System.out.println("doWhat: " + doWhat);
        switch (doWhat){
            case "login":
                loginAdmin(request, response);
                break;
            case "register":
                registerAdmin(request, response);
                break;
            case "listAdmin":
                listAdmin(request, response);
                break;
            case "deleteAdmin":
                deleteAdminByUid(request, response);
                break;
        }

    }

    /**
     *根据管理员的id删除
     * @param request
     * @param response
     */
    private void deleteAdminByUid(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getParameter("uid");
        try {
            adminService.deleteAdminByUid(Integer.parseInt(uid));
            request.getRequestDispatcher("/admin/login.do?doWhat=listAdmin").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String current = request.getParameter("currentPage");
            if(current==null){
                current="1";
            }
            //当前页
            int currentPage = Integer.parseInt(current);
            //每一页显示数据的条数
            int pageSize = 6;
            //资源信息总记录数
            int count = adminService.queryAdminCount();
//            System.out.println("count: " + count);
            //总页码
            int totalPage =count%pageSize==0? count/pageSize:count/pageSize+1;
            //开始位置
            int start = (currentPage-1)*pageSize+1;
//            System.out.println("start: " + start);
            //结束位置
//            int end =currentPage*pageSize;
            List<Map<String, Object>> list = adminService.getAdmins(start,pageSize);
            request.setAttribute("users", list);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("/admin/user/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("username");
        String passwd = request.getParameter("password");
        Admin user = null;
        try {
            user = adminService.getAdmin(email, passwd);
            if(user == null){
                System.out.println("is null");
                request.getRequestDispatcher(request.getContextPath() + "/admin/user/login.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                request.getRequestDispatcher(request.getContextPath() + "/admin/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerAdmin(HttpServletRequest request, HttpServletResponse response) {
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(nickname + " " + email+ " "+password);
        try {
            adminService.register(nickname, email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
