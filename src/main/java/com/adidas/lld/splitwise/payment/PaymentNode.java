package com.adidas.lld.splitwise.payment;

import com.adidas.lld.splitwise.user.User;

public class PaymentNode {
  User receiverNode;
  User senderNode;
  double transactionAmount;

  public PaymentNode(User senderNode, User receiverNode, double transactionAmount){
    this.receiverNode = receiverNode;
    this.senderNode = senderNode;
    this.transactionAmount = transactionAmount;

  }

  public User getReceiverNode() {
    return receiverNode;
  }

  public User getSenderNode() {
    return senderNode;
  }

  public double getTransactionAmount() {
    return transactionAmount;
  }
}
