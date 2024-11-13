package com.adidas.dsa.difficultquestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=6UeDb7ORVPI this the video we are following
 */
public class OptimalAccountBalancing {
  public int minTransactions(int[][] transactions) {
    /**
     * we are given transactions like [0,1,10] -> [from, to, transaction]
     *
     * 1. calculate for each entry the value of (incoming - outgoing)
     *
     */

    Map<Integer, Integer> userVsTransactionsMap = new HashMap<>();
    for (int[] transaction : transactions) {
      int from = transaction[0], to = transaction[1], amount = transaction[2];
      userVsTransactionsMap.put(from, userVsTransactionsMap.getOrDefault(from, 0) - amount);
      userVsTransactionsMap.put(to, userVsTransactionsMap.getOrDefault(to, 0) + amount);
    }
    List<Integer> nonZeroTransactions = new ArrayList<>();
    for (Integer eachValue : userVsTransactionsMap.values()) {
      if (eachValue != 0)
        nonZeroTransactions.add(eachValue);
    }


    return minTransactionsHelper(nonZeroTransactions, 0);
  }

  private int minTransactionsHelper(List<Integer> transactions, int currentIndex) {
    if (transactions.size() == 0 || currentIndex >= transactions.size()) {
      return 0;
    }

    if (transactions.get(currentIndex) == 0) return minTransactionsHelper(transactions, currentIndex + 1);

    int minTransactionCount = Integer.MAX_VALUE;

    for (int transactionIndex = currentIndex + 1; transactionIndex < transactions.size(); transactionIndex++) {
      int currentTransaction = transactions.get(transactionIndex);
      if (transactions.get(currentIndex) * currentTransaction < -1) {
        transactions.set(currentTransaction, transactions.get(currentIndex) + currentTransaction);
        minTransactionCount = Math.min(minTransactionCount, 1 + minTransactionsHelper(transactions, currentIndex + 1));
        transactions.set(currentTransaction, currentTransaction);


        if (transactions.get(currentIndex) + currentTransaction == 0) {
          break;
        }
      }
    }

    return minTransactionCount;

  }
}
