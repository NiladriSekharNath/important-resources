package com.adidas.lld.logger.approach2;

public class Approach2LoggerMain {
  public static void main(String args []){
    Logger logger = new InfoLevelLogger(new DebugLevelLogger(null));
    /*logger.log("debug", "debugMessage");
    logger.log("info", "infoMessage");*/
    logger.log("error", "infoMessage");

  }
}
