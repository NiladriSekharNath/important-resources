package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Approach we suppose k = 17, and we have n = 4 so n! = 24 permutation and in order to find the 17th permutation we understand in the
 *
 * 24 permutations we have 1,2,3,4 numbers now if we consider to store the permutations in a 0-based indexing data structure all our permutations would
 * stored like this
 *
 * 1 -> 0 - 5,     index = 0
 * 2 -> 6 - 11,    index = 1
 * 3 -> 12 - 17,   index = 2
 * 4 -> 18 - 23    index = 3
 *
 * also we define "each" variable defined as the number of permutations in each section, here, each = 6 [ ( n!/n) = (n-1)! = (3!) ]
 *
 * we have above the permutation starting with 1, 6 permutations ( 0 - 5 ) so our kth = 17 th permutation, k = 16 which is again 4th permutation
 * beginning with 3(answer lies in the 3rd block) we can easily find out the index  = k / each = 16 / 6 = 2 so our first number  = 3
 * and new k = k % each; , k = 16 % 6  = 4, then we remove the number 3 from the block
 *
 * which is this way  _ _ _ _ -> 3 _ _ _ which means the first number is placed and
 *
 * our task is find out of 1, 2, 4 block, out of 3 numbers, k = 4th permutation
 *
 * and to bring our final answer 3 4 1 2.
 *
 *
 *
 */

public class KthPermutationSequence {


  public String getPermutation(int n, int k) {

    List<Integer> numbers = new ArrayList<>();

    int fact = 1;

    for(int i = 1 ; i < n ; i++){
      numbers.add(i);
      fact = fact * i ;
    }
    numbers.add(n);
    k -= 1;
    StringBuilder sequence = new StringBuilder();
    while(true){
      sequence.append(numbers.get(k/fact));
      numbers.remove(k/fact);
      k = k % fact;

      if(numbers.size() == 0 )
        break;
      fact = fact / numbers.size();
    }

    return sequence.toString();
  }
}
