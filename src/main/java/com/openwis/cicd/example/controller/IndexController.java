package com.openwis.cicd.example.controller;

import com.openwis.cicd.example.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

  IndexService helloWorldService;

  @GetMapping("/hello")
  public String sayHello() {
    return "hello";
  }

  @GetMapping(path = "/sum/{num1}/{num2}")
  @ResponseBody
  public String getSum(@PathVariable int num1, @PathVariable int num2) {
    helloWorldService = new IndexService();
    int theSum = helloWorldService.sumNumbers(num1, num2);
    return "The sum of the two numbers is: " + theSum;
  }
}
