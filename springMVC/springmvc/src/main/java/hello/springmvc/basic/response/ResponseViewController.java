package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
@ResponseBody가 없으면,'response/hello'로 뷰리졸버가 실행되면서 뷰찾고 렌더링함.
 */
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseView2(Model model){
        model.addAttribute("data", "hello!");
        return "response/hello"; //string만 반환하면 view의 논리적 네임임
    }
}
