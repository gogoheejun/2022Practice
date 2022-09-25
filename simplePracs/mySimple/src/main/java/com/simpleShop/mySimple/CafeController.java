package com.simpleShop.mySimple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CafeController {

    @GetMapping("/cafe/hihi")
    public ResponseEntity<?> hihi(){
        return new ResponseEntity<>("this is body", HttpStatus.OK);
    }
}
