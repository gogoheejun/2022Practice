package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
컨트롤러가 호출되려면 HandlerMapping과 HandlerAdapter가 필요함.
- 핸들러매핑에서 핸들러(컨트롤러)를 찾을 수 있어야 하고, 여기서 찾은 핸들러를 실행할 수 있는 핸들러 어댑터가 필요한 것임.
스프링은 자동으로 핸들러매핑과 핸들러어댑터를 거의다 구현해두었음.
그중 우선순위가 높은것:
* HandlerMapping
0순위: RequestMappingHandlerMapping: 어노테이션기반의 컨트롤러인 @ReqeustMapping에서 사용
1순위: BeanNameUrlHandlerMapping: 스프링 빈의 이름으로 핸들러 찾음-> 현재에제임.localhost:8080/springmvc/old-controller 검색하면 url주소이름으로 똑같은애 찾음

* HandlerAdapter
0순위: RequestMappingHandlerAdapter: 어노테이션 기반의 컨트롤러인 @ReqeustMapping에서 사용
1순위: HttpRequestHandlerAdapter: HttpRequestHandler처리
2순위: SimpleControllerHandlerAdapter: Controller 인터페이스 처리(옛날방식임)..-> 현재 예제
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
