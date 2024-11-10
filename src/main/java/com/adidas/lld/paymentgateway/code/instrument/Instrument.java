package com.adidas.lld.paymentgateway.code.instrument;

/**
 * instrument is type of card credit card, debit card or other types like Banking detailsa
 */
public abstract class Instrument {
  protected String instrumentId;
  protected String userId;
  protected InstrumentType instrumentType ;

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public InstrumentType getInstrumentType() {
    return instrumentType;
  }

  public void setInstrumentType(InstrumentType instrumentType) {
    this.instrumentType = instrumentType;
  }
}
