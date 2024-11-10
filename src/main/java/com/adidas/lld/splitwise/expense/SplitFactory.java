package com.adidas.lld.splitwise.expense;

import com.adidas.lld.splitwise.expense.split.EqualExpenseSplit;
import com.adidas.lld.splitwise.expense.split.ExpenseSplit;
import com.adidas.lld.splitwise.expense.split.PercentageExpenseSplit;
import com.adidas.lld.splitwise.expense.split.UnequalExpenseSplit;

public class SplitFactory {
  public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {

    switch (splitType) {
      case EQUAL:
        return new EqualExpenseSplit();
      case UNEQUAL:
        return new UnequalExpenseSplit();
      case PERCENTAGE:
        return new PercentageExpenseSplit();
      default:
        return null;
    }
  }

}
