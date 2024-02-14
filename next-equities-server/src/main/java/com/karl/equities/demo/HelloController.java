package com.karl.equities.demo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/sayhello")
@Hidden
public class HelloController {

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello World!");
  }

}
