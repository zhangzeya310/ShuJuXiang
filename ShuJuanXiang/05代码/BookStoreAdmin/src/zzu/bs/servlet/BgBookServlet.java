package zzu.bs.servlet;

import zzu.bs.bean.Category;
import zzu.bs.bean.SecondCategory;
import zzu.bs.service.BgBookService;
import zzu.bs.service.BgCategoryService;
import zzu.bs.service.imp.BgBookServiceImp;
import zzu.bs.service.imp.BgCategoryServiceImp;
import zzu.bs.utils.BeanFactory;
import zzu.bs.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BgBookServlet" ,urlPatterns = {"/admin/book.do"})
@MultipartConfig

public class BgBookServlet extends HttpServlet {
    private BgBookService bgBookService = BeanFactory.getBean(BgBookServiceImp.class);
    private BgCategoryService categoryService = BeanFactory.getBean(BgCategoryServiceImp.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doWhat = request.getParameter("doWhat");
        switch (doWhat){
            case "addBook":
                addBook(request, response);
                break;
            case "listBooks":
                listBooks(request,response);
                break;
            case "deleteBook":
                deleteBookBybid(request, response);
                break;
            case "create":
                createBook(request,response);
                break;
            case "editBook" :
                editBookByBid(request, response);
                break;
            case "updateBook":
                updateBookBybid(request, response);
                break;
            default:
                break;
        }

    }

    private void updateBookBybid(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");

        try {
            List<Category> categories = categoryService.listAllCategory();
            List<SecondCategory> secondCategories = categoryService.listAllSecondCategoris();
            request.setAttribute("categories",categories);
            request.setAttribute("secondCategories",secondCategories);
            createBook(request,response);
            bgBookService.deleteBookByBookid(Integer.valueOf(bid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editBookByBid(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");
        try {
//            Book book = bgBookService.getBookByOBookId(bid);
//            request.setAttribute("book",book);
//            List<Category> category = categoryService.listAllCategory();
//            List<SecondCategory> secondCategories = categoryService.listAllSecondCategoris();
//            request.setAttribute("categories",category);category
            request.setAttribute("bid",bid);
            System.out.println(bid);
            request.getRequestDispatcher("/admin/book/add.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBook(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String publishing = request.getParameter("publishing");
        String poublishtime = request.getParameter("poublishtime");
        String edition = request.getParameter("edition");
        String pagenum = request.getParameter("pagenum");
        String isnb = request.getParameter("isnb");
        String bookdescription = request.getParameter("bookdescription");
        String editorcomment = request.getParameter("editorcomment");
        String authordescription = request.getParameter("authordescription");
        String category = request.getParameter("category");
        String secondCategory = request.getParameter("secondCategory");
//        Book book = new Book();
//        book.setName(name);book.setAuthor(author);book.setPrice(Double.valueOf(Integer.valueOf(price)));
//        book.setDiscount(Integer.valueOf(discount));book.setPublishing(publishing);
//        book.setPublishTime(poublishtime);book.setEdition(Integer.valueOf(edition));
//        book.setPageNum(Integer.valueOf(pagenum));book.setIsnb(isnb);
//        book.setBookDescription(bookdescription);book.setEditorComment(editorcomment);book.setAuthordescription(authordescription);
//        book.setCategoryId(Integer.valueOf(category));book.setSecondCategoryId(Integer.valueOf(secondCategory));

        Part part = null;
        try {
            part = request.getPart("imgUrlMid");
            String header = part.getHeader("Content-Disposition");
            String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
            String savePath = "D:/PIC";
            File file = new File(savePath);
            //判断上传文件的保存目录是否存在
            if (!file.exists() && !file.isDirectory()) {
//                System.out.println(savePath + "目录不存在，需要创建");
                //创建目录
                file.mkdir();
            }

            InputStream in = part.getInputStream();
            OutputStream out = new FileOutputStream(savePath + "/" + fileName);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            String s = "insert into t_book(name,author,price,discount,publishing,publishtime,edition,pagenum,isnb,categoryid,secondcategoryid,bookdescription," +
                    "editorcomment,authordescription,imgurlmid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            JDBCUtils.update(s,name,author,price,discount,poublishtime,poublishtime,edition,pagenum,isnb,category,secondCategory,bookdescription,editorcomment,authordescription,
                    savePath+"/"+fileName);

            request.getRequestDispatcher("/admin/book.do?doWhat=listBooks").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void deleteBookBybid(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        try {
            bgBookService.deleteBookByBookid(Integer.valueOf(bid));
            request.getRequestDispatcher("/admin/book.do?doWhat=listBooks").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) {
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
            int count = bgBookService.queryBookCount();
//            System.out.println("count: " + count);
            //总页码
            int totalPage =count%pageSize==0? count/pageSize:count/pageSize+1;
            //开始位置

            int start = (currentPage-1)*pageSize;
//            System.out.println("start: " + start);
            //结束位置
//            int end =currentPage*pageSize;

            List<Map<String, Object>> list = bgBookService.getBooks(start,pageSize);
            request.setAttribute("books", list);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("/admin/book/booklist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Category> categories = categoryService.listAllCategory();
            List<SecondCategory> secondCategories = categoryService.listAllSecondCategoris();
            request.setAttribute("categories",categories);
            request.setAttribute("secondCategories",secondCategories);
            request.getRequestDispatcher("/admin/book/add.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
