package com.adidas.lld.splitwise.group;

import com.adidas.lld.splitwise.expense.Expense;
import com.adidas.lld.splitwise.expense.ExpenseController;
import com.adidas.lld.splitwise.expense.ExpenseSplitType;
import com.adidas.lld.splitwise.expense.split.Split;
import com.adidas.lld.splitwise.user.User;

import java.util.ArrayList;
import java.util.List;

public class Group {
  String groupId;
  String groupName;
  List<User> groupMembers;
  List<Expense> expenseList;
  ExpenseController expenseController;

  /**
   * add a payment object here payment object will have a method to settle payment and show the minimum transactions to settle
   *
   */


  Group(){
    groupMembers = new ArrayList<>();
    expenseList = new ArrayList<>();
    expenseController = new ExpenseController();
  }

  //add member to group
  public void addMember(User member){
    groupMembers.add(member);
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Expense createExpense(String expenseId, String description, double expenseAmount,
                               List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser) {

    Expense expense = expenseController.createExpense(expenseId, description, expenseAmount, splitDetails, splitType, paidByUser);
    expenseList.add(expense);
    return expense;
  }

}
