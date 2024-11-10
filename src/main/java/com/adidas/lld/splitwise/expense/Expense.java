package com.adidas.lld.splitwise.expense;

import com.adidas.lld.splitwise.expense.split.Split;
import com.adidas.lld.splitwise.user.User;

import java.util.ArrayList;
import java.util.List;

public class Expense {
  String expenseId;
  String description;
  double expenseAmount;
  User paidByUser;
  ExpenseSplitType splitType ;
  List<Split> splitDetails = new ArrayList<>();

  public Expense(String expenseId, double expenseAmount, String description, User paidByUser, ExpenseSplitType splitType, List<Split> splitDetails) {
    this.expenseId = expenseId;
    this.description = description;
    this.expenseAmount = expenseAmount;
    this.paidByUser = paidByUser;
    this.splitType = splitType;
    this.splitDetails = splitDetails;
  }

  public String getExpenseId() {
    return expenseId;
  }

  public void setExpenseId(String expenseId) {
    this.expenseId = expenseId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getExpenseAmount() {
    return expenseAmount;
  }

  public void setExpenseAmount(double expenseAmount) {
    this.expenseAmount = expenseAmount;
  }

  public User getPaidByUser() {
    return paidByUser;
  }

  public void setPaidByUser(User paidByUser) {
    this.paidByUser = paidByUser;
  }

  public ExpenseSplitType getSplitType() {
    return splitType;
  }

  public void setSplitType(ExpenseSplitType splitType) {
    this.splitType = splitType;
  }

  public List<Split> getSplitDetails() {
    return splitDetails;
  }

  public void setSplitDetails(List<Split> splitDetails) {
    this.splitDetails = splitDetails;
  }


}
