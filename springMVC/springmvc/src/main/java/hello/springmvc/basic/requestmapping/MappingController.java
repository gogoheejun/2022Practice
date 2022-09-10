package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    //변수명이 같으면 생략가능
    @GetMapping("/mapping/{userId}")
    public String mappingPath2(@PathVariable String userId){
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    //다중 매핑
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath2(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    //특정 헤더로 조건추가
    @GetMapping(value = "/mapping-header", headers="mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }
}
