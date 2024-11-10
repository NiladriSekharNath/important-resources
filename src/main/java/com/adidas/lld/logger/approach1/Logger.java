package com.adidas.lld.logger.approach1;

public abstract class Logger {
  private Logger nextLoggerChain;

  public Logger(Logger nextLoggerChain) {
    this.nextLoggerChain = nextLoggerChain;
  }

  public void log(String logLevel, String message) {
    if (nextLoggerChain != null)
      this.nextLoggerChain.log(logLevel, message);
  }
}
