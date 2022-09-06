package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
핸들러어댑터: FrontCotrollerServlet과 다양한 핸들러(=컨트롤러) 사이에서 어댑터 역할을 함. 덕분에 다양한 컨트롤러 호출가능
핸들러: 컨트롤러의 이름을 더 넓은 의미로 변경. 이제 어댑터가 있으니까 꼭 컨트롤러 뿐 아니라 어떤것이든 받을 수 있기에 컨트롤러를 포함하는 개념인 핸들러로 통칭.
 */
public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
