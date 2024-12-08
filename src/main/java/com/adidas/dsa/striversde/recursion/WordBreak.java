package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
  public static ArrayList<String> wordBreak(String s, ArrayList<String> dictionary) {
    return helper(0, s, new HashSet<>(dictionary), new StringBuilder());

  }

  private static ArrayList<String> helper(int currentIndex, String s, Set<String> set, StringBuilder sb){
    ArrayList<String> resultSet = new ArrayList<>();
    if(currentIndex >= s.length()){
      resultSet.add(new String(sb.toString().trim()));
    }

    int length = sb.length();
    for(int start = currentIndex; start < s.length() ; start++){
      String currS = s.substring(currentIndex, start + 1);
      if(set.contains(currS)){
        sb.append(currS).append(" ");
        resultSet.addAll(helper(start + 1, s, set, sb));
        sb.setLength(length);

      }
    }

    return resultSet;
  }
}
