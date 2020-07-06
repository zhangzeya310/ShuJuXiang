package zzu.bs.servlet;

import zzu.bs.service.BgCategoryService;
import zzu.bs.service.imp.BgCategoryServiceImp;
import zzu.bs.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BgCategoryServlet", urlPatterns = {"/admin/category.do"})
public class BgCategoryServlet extends HttpServlet {

    private BgCategoryService service = BeanFactory.getBean(BgCategoryServiceImp.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat = request.getParameter("doWhat");
        switch (doWhat){
            case "list":
                listAllCategory(request, response);
                break;
        }

    }

    private void listAllCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/admin/book/listcategory.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
