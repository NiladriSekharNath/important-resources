package com.adidas.lld.splitwise.expense;

import com.adidas.lld.splitwise.BalanceSheetController;
import com.adidas.lld.splitwise.expense.split.ExpenseSplit;
import com.adidas.lld.splitwise.expense.split.Split;
import com.adidas.lld.splitwise.user.User;

import java.util.List;

public class ExpenseController {
  BalanceSheetController balanceSheetController;
  public ExpenseController(){

  }
  public Expense createExpense(String expenseId, String description, double expenseAmount, List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser){
    ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
    expenseSplit.validateSplitRequest(splitDetails, expenseAmount);

    Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);

    balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);

    return expense;

  }
}
