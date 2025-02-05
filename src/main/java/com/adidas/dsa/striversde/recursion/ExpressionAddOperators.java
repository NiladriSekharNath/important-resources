package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {


  public List<String> addOperators(String num, int target) {
    List<String> resultList = new ArrayList<>();
    helper(0, 0, 0, num, target, new StringBuilder(), resultList);
    return resultList;
  }

  private void helper(int currentIndex, long prev, long currentSum, String num, int target, StringBuilder currentExpression, List<String> resultList) {
    if (currentIndex >= num.length()) {
      if (currentSum == target) {
        resultList.add(new String(currentExpression.toString()));
        return;
      }
    }

    /**
     * here the explanation for calculating the length is simple we take the currentLength since we are working with stringBuilder instead of
     * removing from the end during backtracking we use the "setLength(length)" function to set the length calculated here
     */


    int length = currentExpression.length();

    for (int start = currentIndex; start < num.length(); start++) {

      /**
       * this portion we are writing because imagine a number "105" and we want target = 5, if currentIndex = 1, and start = 1 when substring is 0
       * then it's okay we get combination 1 * 0 + 5, but if currentIndex = 1 and start = 2 then we get the substring = 05 which is incorrect and we
       * don't want that
       */

      if (start > currentIndex && num.charAt(currentIndex) == '0') break;

      Long currentValue = Long.parseLong(num.substring(currentIndex, start + 1));

      /**
       * here we did the check on currentIndex = 0 because we want let's say any number 105 we want the expression to be appended as
       *
       * 1 when currentIndex = 0 but other places we want the existing expression to append "+ current digit"
       *
       * same goes for 10 + 5, where currentIndex = 0 and start = 1 so we get substring(0, 2) = 10
       */

      if (currentIndex == 0) {
        currentExpression.append(currentValue);
        helper(start + 1, currentValue, currentSum + currentValue, num, target, currentExpression, resultList);
        currentExpression.setLength(length);
      } else {

        // for addition

        currentExpression.append("+").append(currentValue);
        helper(start + 1, currentValue, currentSum + currentValue, num, target, currentExpression, resultList);
        currentExpression.setLength(length);

        // for subtraction

        currentExpression.append("-").append(currentValue);
        helper(start + 1, -currentValue, currentSum - currentValue, num, target, currentExpression, resultList);
        currentExpression.setLength(length);

        // for multiplication

        currentExpression.append("*").append(currentValue);
        /**
         * for understanding the currentSum we are doing this extra step
         *    prev
         *     |
         *    \/
         * 1 + 2 * 3
         *
         * Current Sum from the previous function = 3
         *
         * previous = 2
         *
         * currSum - prev + (prev * currentValue)
         *
         * 3 - 2 + (2 * 3)
         * 1 + (2 * 3)
         *
         * so the previous for the next function call would be (2 * 3)
         *
         * as we previously we go (1 + 2) * 3  -> 3 * 3 (not correct)
         *
         * ideally in BODMAS we should be getting like : 1 + (2 * 3)
         */
        helper(start + 1, (prev * currentValue), (currentSum - prev) + (prev * currentValue), num, target, currentExpression, resultList);
        currentExpression.setLength(length);


      }
    }


  }


}
