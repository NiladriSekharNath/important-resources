package com.adidas.lld.splitwise.payment;

import com.adidas.lld.splitwise.expense.Expense;
import com.adidas.lld.splitwise.expense.split.Split;
import com.adidas.lld.splitwise.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Payment {
  /**
   *
   * @param expenses
   * @return
   *
   * using the expenses of a particular user in a group we are tracking and performing the calculations
   * and simplifying the payments of the each user
   *
   * inspiration taken from : https://medium.com/@interviewready/low-level-design-of-splitwise-f334c8f6ff77
   *
   * https://chatgpt.com/c/672682ba-9c34-8011-8eb2-6148fe3fdf87
   */
  public List<PaymentNode> settlePaymentAndCreatePaymentGraph(List<Expense> expenses) {
    List<PaymentNode> paymentGraph = new ArrayList<>();
    /**
     * constructing payment graph from expenses;
     */
    for (Expense expense : expenses) {
      User paidBy = expense.getPaidByUser();
      for (Split split : expense.getSplitDetails()) {
        paymentGraph.add(new PaymentNode(paidBy, split.getUser(), split.getAmountOwe()));
      }
    }
    return simplifyPayment(paymentGraph);
  }

  private List<PaymentNode> simplifyPayment(List<PaymentNode> paymentGraph) {
    List<PaymentNode> simplifiedGraph = new ArrayList<>();

    PriorityQueue<User> positivesReceiversMaxHeap = new PriorityQueue<>(
        (user1, user2) -> (int) (Math.abs(user2.getUserExpenseBalanceSheet().getTotalYouOwe()) - Math.abs(user1.getUserExpenseBalanceSheet().getTotalYouOwe())));

    PriorityQueue<User> negativesSendersMaxHeap = new PriorityQueue<>(
        (user1, user2) -> (int) (Math.abs(user2.getUserExpenseBalanceSheet().getTotalYouOwe()) - Math.abs(user1.getUserExpenseBalanceSheet().getTotalYouOwe())));

    for (PaymentNode paymentNode : paymentGraph) {
      User senderNode = paymentNode.getSenderNode();
      User receiverNode = paymentNode.getReceiverNode();

      /***
       * updating sender and receiver nodes with transaction amounts
       */

      senderNode.getUserExpenseBalanceSheet().setTotalYouOwe(
          senderNode.getUserExpenseBalanceSheet().getTotalYouOwe() - paymentNode.getTransactionAmount());

      receiverNode.getUserExpenseBalanceSheet().setTotalYouOwe(
          receiverNode.getUserExpenseBalanceSheet().getTotalYouOwe() + paymentNode.transactionAmount);
    }

    for(PaymentNode paymentNode : paymentGraph){
      if(paymentNode.getReceiverNode().getUserExpenseBalanceSheet().getTotalYouOwe() < 0){
        negativesSendersMaxHeap.add(paymentNode.receiverNode);
      }
      else if(paymentNode.getReceiverNode().getUserExpenseBalanceSheet().getTotalYouOwe() > 0){
        positivesReceiversMaxHeap.add(paymentNode.senderNode);
      }

      if(paymentNode.getSenderNode().getUserExpenseBalanceSheet().getTotalYouOwe() < 0){
        negativesSendersMaxHeap.add(paymentNode.senderNode);
      }
      else{
        positivesReceiversMaxHeap.add(paymentNode.senderNode);
      }
    }

    while(!positivesReceiversMaxHeap.isEmpty()){
      User receiverNode = positivesReceiversMaxHeap.remove();
      User senderNode = negativesSendersMaxHeap.remove();

      double transactionAmount = Math.min(Math.abs(receiverNode.getUserExpenseBalanceSheet().getTotalYouOwe()),
          Math.abs(senderNode.getUserExpenseBalanceSheet().getTotalYouOwe()));

      simplifiedGraph.add(new PaymentNode(senderNode, receiverNode, transactionAmount));

      receiverNode.getUserExpenseBalanceSheet().setTotalYouOwe(Math.abs(receiverNode.getUserExpenseBalanceSheet().getTotalYouOwe()) - transactionAmount);
      senderNode.getUserExpenseBalanceSheet().setTotalYouOwe(Math.abs(senderNode.getUserExpenseBalanceSheet().getTotalYouOwe() - transactionAmount));

      if(receiverNode.getUserExpenseBalanceSheet().getTotalYouOwe() != 0){
        positivesReceiversMaxHeap.add(receiverNode);
      }
      if(senderNode.getUserExpenseBalanceSheet().getTotalYouOwe() != 0){
        negativesSendersMaxHeap.add(senderNode);
      }
    }
    return simplifiedGraph;
  }
}
