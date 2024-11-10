package com.adidas.lld.paymentgateway.code.payment;

import java.util.List;

public class TransactionController {
  private TransactionService transactionService;

  public TransactionController(){
    this.transactionService = new TransactionService();
  }

  public TransactionDTO makePayment(TransactionDTO transactionDTO){
    return transactionService.makePayment(transactionDTO);
  }
  public List<Transaction> getTransactionHistory(String userId){
    return transactionService.getTransactionHistory(userId);
  }
}
