package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Approach we try for each substrings length 1, then length2 and check if it's palindromic if it's palindromic we would then try with the next part of the string
 *
 * Time Complexity is O( N * 2^N) where N to store the path in ans  and 2^N for generating subsequence
 * And the Space Complexity is O( N * N )  , where N to store subsequence in path vector and N for recursion tree
 */
public class PalindromicPartioning {

  public List<List<String>> partition(String s) {
    return helper(s, 0, new ArrayList<>());
  }

  private List<List<String>> helper(String s, int currentIndex, List<String> currentList) {
    List<List<String>> resultList = new ArrayList<>();

    if (!currentList.isEmpty()) {
      if (currentIndex == s.length())
        resultList.add(new ArrayList<>(currentList));
    }

    for (int start = currentIndex; start < s.length(); start++) {
      String subString = s.substring(currentIndex, start + 1);
      if (isPalindrome(subString)) {
        currentList.add(subString);
        resultList.addAll(helper(s, start + 1, currentList));
        currentList.remove(currentList.size() - 1);
      }
    }

    return resultList;

  }

  private boolean isPalindrome(String s){
    if(s.length() == 0) return true;
    int left = 0, right = s.length() - 1;
    while(left <= right){
      if(s.charAt(left) != s.charAt(right))
        return false;
      left++;
      right--;
    }

    return true;
  }
}
