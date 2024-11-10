package com.adidas.lld.paymentgateway.code.instrument;

public class InstrumentFactory {

  public InstrumentService getInstrumentService(InstrumentType instrumentType) {
    if (instrumentType == InstrumentType.BANK)
      return new BankService();
    else if (instrumentType == InstrumentType.CARD)
      return new CardService();
    return null;
  }
}
