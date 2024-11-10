package com.adidas.lld.paymentgateway.code.payment;

public class Transaction {
  private int amount;
  private String transactionId;
  private String senderId;
  private String receiverId;
  private String debitInstrumentId;
  private String creditInstrumentId;

  private TransactionStatus transactionStatus;

  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(String receiverId) {
    this.receiverId = receiverId;
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
}
