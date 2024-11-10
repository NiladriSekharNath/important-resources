package com.adidas.lld.logger.approach2;

public class DebugLevelLogger extends LoggerProcessor {
  private Logger nextLoggerInChain;
  public DebugLevelLogger(Logger nextLoggerInChain) {
    super(nextLoggerInChain);
    this.nextLoggerInChain = nextLoggerInChain;
  }

  @Override
  public void log(String logLevel, String message) {
    if(logLevel.equalsIgnoreCase("debug")){
      System.out.println(String.format("Current log level: '%s', message: '%s'", logLevel, message));

    }
    else
      super.log(logLevel, message);
  }

}
