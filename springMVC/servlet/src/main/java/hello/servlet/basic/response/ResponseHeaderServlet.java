package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //상태코드
        response.setStatus(HttpServletResponse.SC_OK);
        //그외 다른 헤더들
        response.setHeader("Content-Type", "text/plain");
        response.setHeader("my-header", "hahahaha");

        response.setContentType("text/plain");//이렇게도 가

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);//600초
        response.addCookie(cookie);

        //message body
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
}
