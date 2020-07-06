package zzu.bs.servlet;

import zzu.bs.bean.Order;
import zzu.bs.bean.OrderItem;
import zzu.bs.service.BgOrderService;
import zzu.bs.service.imp.BgOrderServiceImp;
import zzu.bs.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BgOrderServlet", urlPatterns = {"/admin/order.do"})
public class BgOrderServlet extends HttpServlet {
    private BgOrderService orderService = BeanFactory.getBean(BgOrderServiceImp.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat = request.getParameter("doWhat");
        switch (doWhat){
            case "listOrders":
                listOrders(request, response);
                break;
                case "deleteOrder":
                    deleteOrderByid(request, response);
                    break;
            case "editOrder":
                editOrderBySeq(request, response);
                break;

        }
    }

    private void editOrderBySeq(HttpServletRequest request, HttpServletResponse response) {
        String seq = request.getParameter("orderSeq");
        try {
            List<OrderItem> item = orderService.getOrderItemByOrderSeq(seq);
            request.setAttribute("orders",item);
            Order order = orderService.getOrderByOrderSeq(seq);
            Integer status = order.getStatus();
            request.setAttribute("status",status);
            request.getRequestDispatcher("/admin/order/detail.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteOrderByid(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("orderId");
        try {
            System.out.println("int deleter");
            orderService.deleteOrderByOrderid(Integer.parseInt(id));
            request.getRequestDispatcher("/admin/order.do?doWhat=listOrders").forward(request, response);
        } catch (Exception e) {
            try {
                request.getRequestDispatcher("/admin/order.do?doWhat=listOrders").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) {
        try {
            String current = request.getParameter("currentPage");
            if(current==null){
                current="1";
            }
            //当前页
            int currentPage = Integer.parseInt(current);
            //每一页显示数据的条数
            int pageSize = 15;
            //资源信息总记录数
            int count = orderService.queryOrderCount();
//            System.out.println("count: " + count);
            //总页码
            int totalPage =count%pageSize==0? count/pageSize:count/pageSize+1;
            //开始位置

            int start = (currentPage-1)*pageSize;
//            System.out.println("start: " + start);
            //结束位置
//            int end =currentPage*pageSize;

            List<Map<String, Object>> list = orderService.getOrders(start,pageSize);
            request.setAttribute("orders", list);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);

            request.getRequestDispatcher("/admin/order/orderlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
