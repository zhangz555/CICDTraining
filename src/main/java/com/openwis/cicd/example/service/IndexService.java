package com.openwis.cicd.example.service;

import org.springframework.stereotype.Service;

@Service
public class IndexService {

  public int sumNumbers(int number1, int number2) {
    return number1 + number2;
  }
}
