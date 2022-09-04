package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //요청이 GET URL쿼리 파라미터로 오든, POST HTML Form으로 오든 형식이 xx=aaa&yy=bbb 형식이기에
        // request.getParameter()로 똑같이 하면됨
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> System.out.println(paramName+"= "+request.getParameter(paramName)));
    }
}
