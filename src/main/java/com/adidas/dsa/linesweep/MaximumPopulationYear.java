package com.adidas.dsa.linesweep;

import java.util.Map;
import java.util.TreeMap;

/**
 *

 1854. Maximum Population Year

 You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

 The population of some year x is the number of people alive during that year.
 The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1].
 Note that the person is not counted in the year that they die.

 Return the earliest year with the maximum population

 see if we think about it this way to find the maximum population year

 Approach

 Let's understand with an example
 Suppose we are given the log [1950, 1955]
 This means birth year is 1950 and death year is 1955
 Therefore, years from 1950 to 1955 will be incremented by 1.

 We can do it by iterating from 1950 to 1955, but that will increase time complexity if we have to do it for every query in logs array.

 To do this in O(1), we increment year[1950] by 1 and decrement year[1955] by 1.
 We can repeat this for every query in logs array.

 What will this do

 For the case [1950, 1955], and also let's say we would also have a range,[1952 - 1954] let's look at how the array will look like

 for [1950, 1955]

  1950, 1951, 1952, 1953, 1954, 1955
  [1,     0,   0,    0,    0,    -1]


 okay so if we think about it initially the value or the period here should look like this assuming this is not an array

 we are simply observing now

 1950, 1951, 1952, 1953, 1954, 1955
 [1,     1,   1,    1,    1,    1]


 after introduction of another series [1952 - 1954]
               +1   +1     +1
 1950, 1951, 1952, 1953, 1954, 1955
 [1,     1,   1,    1,    1,    1]

 should look like this:


  1950, 1951, 1952, 1953, 1954, 1955
 [1,     1,   +2,    +2,    +2,    1]


 ideally our answer should store the value at 1952 which has the highest value

 now introduction of another range at [1953 - 1954]

                     +1   +1
 1950, 1951, 1952, 1953, 1954, 1955
 [1,     1,   +2,    +2,    +2,    1]


 should look like this :


 1950, 1951, 1952, 1953, 1954, 1955
 [1,     1,   +2,    +3,    +3,    1] answer = 1953(first number with highest value till date)

 now if we see, if we take all ranges here and keep adding the value this should look like a solution having O(n2) kind of solution

 which is the only problem to this approach is that this approach will take a lot of time

 however if we see what we do here is we are keeping a running sum and adding for the given ranges

 so what we did was keep adding the values +1 for the start, and -1 for the end

 for the ranges [[1950 - 1955], [1952 - 1954], [1953 - 1954]]

 if we include the end range then we would have to mark the [endValue + 1]-- (as simply stated in the question the person's deathYear(endDate)
 is not considered for our answer, however we are writing for the above explanation

 1950, 1951, 1952, 1953, 1954, 1955, 1956
 [1,     0,   1,   1,    0,    -2     -1]

 if we see here when we don't include the endValue we have the value

 [1      1     2    3      3     1      0]




 we see here something that the prefix logic here works because when we keep adding the values to get the running sum
 we get what we need that is the running value
 Sure! Let’s simplify the **prefix sum** explanation with a basic example.

 ---

 ### Example: Range Addition
 You have an array of size 5:  
 [ text{Array: } [0, 0, 0, 0, 0] ]

 You need to perform two range increment operations:
 1. Add +3 to the range [1, 3].
 2. Add +2 to the range [2, 4].

 ---

 ### Step-by-Step Explanation
 #### 1. Mark Changes at Boundaries
 Instead of directly updating every element in the range, we mark changes only at the start and stop points:
 - For the range [1, 3]:
 - At 1, we add +3: [0, +3, 0, 0, 0].
 - At 4 3+1, we add -3: [0, +3, 0, 0, -3].

 - For the range [2, 4]:
 - At 2, we add +2: [0, +3, +2, 0, -3].
 - At 5 4+1, we add -2: [0, +3, +2, 0, -3, -2].

 After marking changes, the auxiliary array looks like this:  
 [ [0, +3, +2, 0, -3, -2] ]

 ---

 #### 2. Compute the Prefix Sum
 Now we compute the **prefix sum** over this array to propagate the changes:

 - Start with 0: Running sum = 0.  
 - Add +3: Running sum = 3.  
 - Add +2: Running sum = 5.  
 - Add 0: Running sum = 5.  
 - Add -3: Running sum = 2.

 The resulting array after applying the prefix sum ignoring the extra boundary beyond size 5 is:  
 [ [3, 5, 5, 2, 0] ]

 ---

 ### Final Result
 The final array after both operations is:  
 [ [3, 5, 5, 2, 0] ]

 ---

 ### Why It Works
 1. **Marking Boundaries**:
 - The +3 at index 1 starts adding 3 from index 1 onward.
 - The -3 at index 4 stops adding 3 after index 3.
 - Similarly, +2 at index 2 starts adding 2, and -2 at index 5 stops it after index 4.

 2. **Prefix Sum Propagation**:
 - The prefix sum ensures that the addition propagates through the array, applying the increments automatically to all positions within the range, without explicitly looping through every index.

 By combining boundary marking with prefix sum, you efficiently update the array with multiple range operations in On + q time.


 now the point is for any given range imagine as adding +1 for the range and adjusting the 1 with -1 after the range

 */
public class MaximumPopulationYear {
  public int maximumPopulation(int[][] logs) {
    Map<Integer, Integer> populationIndex = new TreeMap<>();
    for (int[] log : logs) {
      populationIndex.put(log[0], populationIndex.getOrDefault(log[0], +1));
      populationIndex.put(log[1], populationIndex.getOrDefault(log[0], -1));
    }

    int maxValue = Integer.MIN_VALUE, sum = 0, year = 0;
    for (Map.Entry<Integer, Integer> entry : populationIndex.entrySet()) {
      sum += entry.getValue();

      if (sum > maxValue) {
        year = entry.getKey();
        maxValue = sum;
      }


    }

    return year;
  }

  public int maximumPopulationYear(int[][] logs) {



    int[] treeMap = new int[101];

    for(int[] log : logs){
      treeMap[log[0] - 1950]++;
      treeMap[log[1] - 1950]--;
    }

    int year  = 1950, maxValue = treeMap[0];

    for(int i = 1 ; i < treeMap.length;i++){
      treeMap[i] = treeMap[i] + treeMap[i-1];
      if(treeMap[i] > maxValue){
        maxValue = treeMap[i];
        year = 1950 + i;
      }
    }

    return year;
  }

  public static void main(String args[]){
   new MaximumPopulationYear().maximumPopulation(new int[][]{{2025,2041}, {1988,2007}, {2003,2046}, {2045,2049}, {2025,2027}, {2014,2040}, {2014,2027}, {2011,2027}, {1972,2019}});
  }
}
