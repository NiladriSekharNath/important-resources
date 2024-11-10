package com.adidas.lld.splitwise;

import com.adidas.lld.splitwise.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserExpenseBalanceSheet {
  Map<User, Balance> userVsBalance;
  double totalYourExpense;
  double totalPayment;
  double totalYouOwe;
  double totalYouGetBack;
  public UserExpenseBalanceSheet(){
    userVsBalance = new HashMap<>();
    totalYourExpense = 0;
    totalYouOwe = 0;
    totalYouGetBack = 0;
    totalPayment = 0;
  }

  public Map<User, Balance> getUserVsBalance() {
    return userVsBalance;
  }

  public double getTotalYourExpense() {
    return totalYourExpense;
  }

  public void setTotalYourExpense(double totalYourExpense) {
    this.totalYourExpense = totalYourExpense;
  }

  public double getTotalYouOwe() {
    return totalYouOwe;
  }

  public void setTotalYouOwe(double totalYouOwe) {
    this.totalYouOwe = totalYouOwe;
  }

  public double getTotalYouGetBack() {
    return totalYouGetBack;
  }

  public void setTotalYouGetBack(double totalYouGetBack) {
    this.totalYouGetBack = totalYouGetBack;
  }

  public double getTotalPayment() {
    return totalPayment;
  }

  public void setTotalPayment(double totalPayment) {
    this.totalPayment = totalPayment;
  }


}
