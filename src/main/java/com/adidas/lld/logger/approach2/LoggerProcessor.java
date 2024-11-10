package com.adidas.lld.logger.approach2;

public abstract class LoggerProcessor implements Logger {
  private Logger nextLoggerInChain;

  public LoggerProcessor(Logger nextLoggerInChain){
    this.nextLoggerInChain = nextLoggerInChain;
  }

  @Override
  public void log(String logLevel, String message){
    if(nextLoggerInChain != null)
      nextLoggerInChain.log(logLevel, message);
    else
      System.out.println(String.format("log level not supported: '%s' not supported ", logLevel));
  }
}
