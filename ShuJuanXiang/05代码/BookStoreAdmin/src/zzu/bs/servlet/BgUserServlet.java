package zzu.bs.servlet;

import zzu.bs.bean.User;
import zzu.bs.service.BgUserService;
import zzu.bs.service.imp.BgUserServiceImp;
import zzu.bs.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BgUserServlet" , urlPatterns = {"/admin/user.do"})
public class BgUserServlet extends HttpServlet {
    private BgUserService userService = BeanFactory.getBean(BgUserServiceImp.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWaht = request.getParameter("doWhat");
        switch (doWaht){
            case "listUser":
                listUsers(request, response);
                break;
            case "deleteUser":
                deleteUserByUid(request, response);
                break;
            case "editUser":
                editUserByUid(request, response);
                break;
            case  "updateUser":
                updateUserByUid(request, response);
                break;
        }

    }

    private void updateUserByUid(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String passwd = request.getParameter("password");
        String status = request.getParameter("status");
        String address = request.getParameter("address");
        String uid = request.getParameter("uid");
        try {

            userService.editUserByUid(Integer.parseInt(uid),email, nickname, passwd, status, address);
            request.getRequestDispatcher("/admin/user.do?doWhat=listUser").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    UID
//    用户邮箱
//    昵称
//    用户密码
//    状态
//    用户地址

    private void editUserByUid(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getParameter("userUid");
        request.setAttribute("uid",uid);
        try {
            User user = userService.getUserByUid(Integer.valueOf(uid));
            request.setAttribute("user",user);
            request.getRequestDispatcher("/admin/user/detail.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUserByUid(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getParameter("uid");
        try {
            userService.deleteUserByUid(Integer.parseInt(uid));
            request.getRequestDispatcher("/admin/user.do?doWhat=listUser").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("/admin/user.do?doWhat=listUser");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            String current = request.getParameter("currentPage");
            if(current==null){
                current="1";
            }
            //当前页
            int currentPage = Integer.parseInt(current);
            //每一页显示数据的条数
            int pageSize = 10;
            //资源信息总记录数
            int count = userService.queryUserCount();
//            System.out.println("count: " + count);
            //总页码
            int totalPage =count%pageSize==0? count/pageSize:count/pageSize+1;
            //开始位置

            int start = (currentPage-1)*pageSize;
//            System.out.println("start: " + start);
            //结束位置
//            int end =currentPage*pageSize;

            List<Map<String, Object>> list = userService.getUsers(start,pageSize);
            request.setAttribute("users", list);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("/admin/user/userlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
