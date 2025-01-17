package com.adidas.dsa.striversde.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class HouseRobberII {

  /**
   * This solution is exactly similar to the HouseRobberI except for the part that the house are present in a circle,
   * which means if we choose the last house we can't choose the first house and vice versa(if we choose the first house,
   * we can't choose the first house)
   *
   * So if we think about we can't choose both the first house and the last house at the same time,
   * so what we should do instead is
   *
   * For single Value case we need to handle the element -> return nums[0]
   *
   * let's say we have [4, 5, 2] in the array
   *
   * firstHouseChosen and lastHouse not chosen -> [4, 5]  -----> denoted by the allElementsExceptLast
   * firstHouseNotChose and lastHouse chosen -> [5, 2]   -----> denoted by the allElementsExceptFirst
   *
   * and our final value is Math.max(function(allElementsExceptLast), function(allElementsExceptFirst))
   *
   *
   *
   *
   */
  public static long houseRobber(int[] valueInHouse) {

    int n = valueInHouse.length;
    if(n == 1) return valueInHouse[0];

    /**
     *
     * Instead of taking a list we could have taken this way but only catch is that allValuesExceptFirst
     *
     * works in the range[start, end) -> start is inclusive but end is exclusive
     *
     * int[] allValuesExceptLast = new int[n - 1], allValuesExceptFirst = new int[n - 1];
     *
     *     allValuesExceptLast = Arrays.copyOfRange(valueInHouse, 0, n - 1);
     *
     *     allValuesExceptFirst = Arrays.copyOfRange(valueInHouse, 1, n);
     *
     */



    List<Integer> allValuesExceptLast = new ArrayList<>(), allValuesExceptFirst = new ArrayList<>();

    for(int i = 0 ; i < n ; i++){
      if(i != 0) allValuesExceptFirst.add(valueInHouse[i]);
      if(i != n - 1) allValuesExceptLast.add(valueInHouse[i]);
    }

    return Math.max(solutionWithSpaceOptimization(allValuesExceptFirst, n - 1),
        solutionWithSpaceOptimization(allValuesExceptLast, n - 1));

  }

  private static long solutionWithSpaceOptimization(List<Integer> nums, int n){

    long secondPrevious = 0 , previous = nums.get(0), current = 0;

    for (int cI = 1; cI < n; cI++) {
      long pickChoice = 0, notPickChoice = 0;


      pickChoice = nums.get(cI) + (cI - 2 >= 0 ? secondPrevious : 0);

      notPickChoice = previous ;

      current = Math.max(pickChoice, notPickChoice);

      secondPrevious = previous;

      previous = current;

    }

    return previous;
  }


  public static void main(String[] args){
    new HouseRobberII().houseRobber(new int[]{1});
    new HouseRobberII().houseRobber(new int[]{2, 3 ,2});
  }
}
