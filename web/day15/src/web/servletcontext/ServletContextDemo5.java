package web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author shkstart
 * @create 2020-05-24 11:46
 */
@WebServlet("/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*ServletContext功能：
        1. 获取MIME类型：
        2. 域对象：共享数据
        3. 获取文件的真实(服务器)路径*/
        //2. 通过HttpServlet获取
//        ServletContext context = this.getServletContext();
//
//        // 获取文件的服务器路径
//        String b = context.getRealPath("/b.txt");
//        System.out.println(b);
////        File file = new File(b);
//
//        String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问
//        System.out.println(c);
//
//        String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问
//        System.out.println(a);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
