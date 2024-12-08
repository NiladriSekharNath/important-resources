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


        /**
         * see the whole idea of the problem is we have transactions and we need to find the minimum count of transactions that
         * are needed to simply the total transaction so we try out all solutions and try to find the minimum out of them.
         *
         * let's say we have this example                     f(0, [70, -200, 300, -70, -40, -100, -30])
         *
         * now the next calls are in this way: f(1, [0, -130, 300, -70, -40, -100, -30] f(1, [0, -200, 300, 0, -40, -100, -30])
         *
         * now the point if we see for currentIndex = 0 but transactionIndex = 3, if we continue after this for the transactionIndex = 4, for -40
         * and again if we did not break the for loop for f(1, [0, -200, 300, -70, 30, -100, -30]) that could cause more time for our program to solve
         * since we found a perfect transaction(or balance) and we don't want partial transactions so we don't need to move ahead for that currentIndex
         *
         *
         */
        if (transactions.get(currentIndex) + currentTransaction == 0) {
          break;
        }
      }
    }

    return minTransactionCount;

  }
}
