package hello.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//stream을 문자로 바꿀땐 항상 어떤 문자로 받을지 지정

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//stream을 문자로 바꿀땐 항상 어떤 문자로 받을지 지정
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /*
    HttpEntity: Http header랑 body 정보조회를 편리하게 해줌
    * 요청 파라미터조회랑은 상관 없음
      - 요청파라미터는 @RequestParam, @ModelAttribute, request.getParameter()로 하는거지

    *지금 여기서는 message body(or header)로 오는거 조회하는 거임!!
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

    //RequestEntity, ResponseEntity도 있음. 둘다 HttpEntity상속받은 거임
    @PostMapping("/request-body-string-v3-1")
    public HttpEntity<String> requestBodyStringV3_1(RequestEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    // 개간단..!
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
