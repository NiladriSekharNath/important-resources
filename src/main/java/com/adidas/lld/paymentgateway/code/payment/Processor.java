package com.adidas.lld.paymentgateway.code.payment;

public class Processor {

  public void processPayment(TransactionDTO transactionDTO) {
    System.out.println(String.format("Amount '%s' has been paid successfully by user '%s', from instrument '%s' to receiver '%s' on instrument '%s' "
        , transactionDTO.getAmount(), transactionDTO.getSenderUserId(), transactionDTO.getDebitInstrumentId(), transactionDTO.getReceiverUserId(), transactionDTO.getCreditInstrumentId()));
  }
}
