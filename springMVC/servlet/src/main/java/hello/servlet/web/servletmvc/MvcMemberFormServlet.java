package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        참고로 WEB-INF경로 아래에 있는 자원들은 클라이언트가 직접 호출 못함. 서버 내부에서만 호출되는 애들임
        localhost:8080/WEB-INF/view/...이런식으로 검색안됨.
         */
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); //서버 내부에서 호출 != 리다이렉트(ㄹㅇ클라이언트까 또 요청하느거)

    }
}
