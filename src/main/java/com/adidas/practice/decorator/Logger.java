package com.adidas.practice.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {
  public Operation log(Operation operation){
    /*return new Operation() {
      @Override
      public int execute(int a, int b) {
        log.info("logging operation");
        return operation.execute(a, b);

      }
    };*/

    return (a, b) -> {
      log.info("logging operation");
      return operation.execute(a, b);
    };
  }
}
