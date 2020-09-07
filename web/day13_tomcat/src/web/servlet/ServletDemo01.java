package web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-05-22 14:52
 */
public class ServletDemo01 implements Servlet {
    //提供服务的方法

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Hello!servlet");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
