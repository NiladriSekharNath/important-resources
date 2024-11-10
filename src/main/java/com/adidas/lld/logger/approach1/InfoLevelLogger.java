package com.adidas.lld.logger.approach1;

public class InfoLevelLogger extends Logger {
  public InfoLevelLogger(Logger nextLoggerChain) {
    super(nextLoggerChain);
  }

  @Override
  public void log(String logLevel, String message) {
    if (logLevel.equalsIgnoreCase("INFO")) {
      System.out.println(String.format("Current log level: '%s', message: '%s'", logLevel, message));
    } else
      super.log(logLevel, message);
  }
}
