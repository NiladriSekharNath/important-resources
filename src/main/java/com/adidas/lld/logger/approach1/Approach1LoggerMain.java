package com.adidas.lld.logger.approach1;

public class Approach1LoggerMain {
  public static void main(String args[]){
    Logger logger = new InfoLevelLogger(new DebugLevelLogger(null));
  }
}
