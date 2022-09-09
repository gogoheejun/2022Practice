package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
@Controller의 역할은 크게 두가지임.
1. @Component가 내부에 있기에, 스프링 빈에 자동등록하도록 함
2. 스프링MVC가 얘를 어노테이션 기반 컨트롤러로 인식하도록 해줌.

즉, @Component때버리고 @Component랑 @RequestMapping을 클래스 레벨에 붙여도 동일하게 작동함
 */
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
