package hello.servlet.web.frontcontroller.v4.controller;


import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    //더이상 ModelView로 반환하지도 않음. 개발자가 더 편해짐
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
