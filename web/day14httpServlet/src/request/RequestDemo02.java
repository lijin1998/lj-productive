package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author shkstart
 * @create 2020-05-23 12:47
 */
@WebServlet("/requestDemo02")
public class RequestDemo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //演示获取请求头数据
        //1.获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //2.遍历
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();

            //根据名称获取请求头的值
            String value = request.getHeader(s);
            System.out.println(s + "-------" + value);
        }
//        host-------localhost:8080
//        connection-------keep-alive
//        upgrade-insecure-requests-------1
//        user-agent-------Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36
//        accept-------text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//sec-fetch-site-------none
//sec-fetch-mode-------navigate
//sec-fetch-user-------?1
//sec-fetch-dest-------document
//accept-encoding-------gzip, deflate, br
//accept-language-------zh-CN,zh;q=0.9,de;q=0.8
//cookie-------Webstorm-1713651e=f09d7fe4-d8f2-4c04-a45c-b8dfef89ff46; JSESSIONID=D0A0C79C8FAD2B29BA92609B51BB0B61


    }
}
