package com.adidas.lld.logger.approach1;

public class DebugLevelLogger extends Logger {
  public DebugLevelLogger(Logger nextLoggerChain) {
    super(nextLoggerChain);
  }

  @Override
  public void log(String logLevel, String message) {
    if (logLevel.equalsIgnoreCase("DEBUG")) {
      System.out.println(String.format("Current log level: '%s', message: '%s'", logLevel, message));
    } else
      super.log(logLevel, message);
  }
}
