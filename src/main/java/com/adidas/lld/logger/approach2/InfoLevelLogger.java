package com.adidas.lld.logger.approach2;

public class InfoLevelLogger extends LoggerProcessor {
  private Logger nextLoggerInChain;
  public InfoLevelLogger(Logger nextLoggerInChain) {
    super(nextLoggerInChain);
    this.nextLoggerInChain = nextLoggerInChain;
  }

  @Override
  public void log(String logLevel, String message) {
    if(logLevel.equalsIgnoreCase("INFO")){
      System.out.println(String.format("Current log level: '%s', message: '%s'", logLevel, message));

    }
    else
      nextLoggerInChain.log(logLevel, message);
  }
}
