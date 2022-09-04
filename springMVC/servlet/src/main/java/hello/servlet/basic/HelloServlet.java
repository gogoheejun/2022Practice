package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);//request = org.apache.catalina.connector.RequestFacade@68dcead7..와스별로 http를 해석해주는 패키지가 있음
        System.out.println("response = " + response);

        String username = request.getParameter("username");//servlet이 http메시지를 이렇게 읽기 쉽게 해주는거임
        System.out.println("username = "+username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello"+username);//이러면 http메시지 바디에 들어감
    }
}
