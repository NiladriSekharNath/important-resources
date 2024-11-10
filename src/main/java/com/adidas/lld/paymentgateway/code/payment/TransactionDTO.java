package com.adidas.lld.paymentgateway.code.payment;


public class TransactionDTO {
  private String transactionId;
  private int amount;
  private String senderUserId;
  private String receiverUserId;

  private String debitInstrumentId ;

  private String creditInstrumentId;

  private TransactionStatus status;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getSenderUserId() {
    return senderUserId;
  }

  public void setSenderUserId(String senderUserId) {
    this.senderUserId = senderUserId;
  }

  public String getReceiverUserId() {
    return receiverUserId;
  }

  public void setReceiverUserId(String receiverUserId) {
    this.receiverUserId = receiverUserId;
  }

  public String getDebitInstrumentId() {
    return debitInstrumentId;
  }

  public void setDebitInstrumentId(String debitInstrumentId) {
    this.debitInstrumentId = debitInstrumentId;
  }

  public String getCreditInstrumentId() {
    return creditInstrumentId;
  }

  public void setCreditInstrumentId(String creditInstrumentId) {
    this.creditInstrumentId = creditInstrumentId;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public void setStatus(TransactionStatus status) {
    this.status = status;
  }
}
