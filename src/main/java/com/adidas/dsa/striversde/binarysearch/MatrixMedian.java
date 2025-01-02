package com.adidas.dsa.striversde.binarysearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatrixMedian {
  /**
   * Given to us a row Sorted matrix[n x m] we are tasked to find the median in the matrix in logarithmic time our matrix has odd rows and columns
   *
   * Approach: We are tasked to find the median in the matrix we understand this value is in the range in the lowest value in the matrix to
   * the highest value in matrix to find the middle most value which means since it is odd, the median will have the equal number of elements
   * to the left and the right
   *
   * What is the search space where we will apply binary search?
   *
   * If we carefully observe, our answer lies between the smallest and the largest number in the given matrix. So, the search space will be [min(matrix), max(matrix)].
   * While applying binary search how to check if an element ‘x’ is a possible median?
   *
   * If ‘x’ is the median, the number of elements smaller or equal to ‘x’ will be surely greater than (MXN) // 2 (integer division).
   *
   * so the matrix has [n x m] the median would be in the index [n x m]/2 given if we take all the elements put them in a datastructure and
   * sort the elements
   *
   *
   *
   * The main solution is find the smallest element which has a count of elements > the "requiredApproxMedian = [n x m]/2"
   * if the total elements are in the matrix are 15 with numbers belonging in the range[1, 16] (can be duplicate and not all numbers present in the range),
   * so our requiredApproxMedian = 7, so we need the find smallest number x, that has [count of number of elements <= x], greater than 7
   *
   * the <= part is key as
   * let's say we have this matrix:
   * [ 1, 3, 6]
   * [ 2, 6, 9]
   * [ 3, 6, 9]
   *
   * if we put them in a sorted 1D array : -> 1, 2, 3, 3, 6, 6, 6, 9 ,9
   *
   * and we are searching between 1 - 9 range,  till 5 not in the matrix, we get 4 numbers [<= 5], now
   * if we had used '<' just for this example understanding, < 6 we would have got 4 numbers
   *
   * [1, 2, 3, 3 ] less than 6;
   *
   * but if we consider <= 6, [1,2,3,3,6,6,6 ] we have 7 numbers greater than 4 since there could be duplicates here in the matrix,
   * and we find the lowest number which has count greater than 4, which is 6
   *
   * so we take <=
   */
  public int matrixMedian(int[][] arr){
    int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE, ans = 0, rowSize = arr.length, columnSize = arr[rowSize - 1].length;
    for(int row = 0 ; row < rowSize; row++){
      low = Math.min(arr[row][0], low);
      high = Math.max(arr[row][columnSize - 1], high);

    }

    int requiredApproxMedian = (rowSize * columnSize) / 2;
    while(low <= high){
      int mid = low + (high - low)/2;
      /**
       * this is simply written in reverse
       * we were searching <= which is highest which in the reverse way we are finding the lowest value which has greater '>'
       */
      if(numberElementsGreater(arr, mid) > requiredApproxMedian){
        ans = mid;
        high = mid - 1;
      }
      else
        low = mid + 1;
    }

    return ans;
  }

  private int numberElementsGreater(int[][] arr, int x) {
    int coluntNumberOfElementsGreaterThanX = 0;
    for(int[] eachRow : arr){
      coluntNumberOfElementsGreaterThanX += (upperBound(eachRow, x) - 0);

    }
    return coluntNumberOfElementsGreaterThanX;
  }

  private int upperBound(int[] arr, int x) {
    int low = 0, ans = arr.length, high = ans - 1;
    while(low <= high){
      // maybe an answer
      int mid = low + (high - low)/2 ;
      if(arr[mid] > x){
        ans = mid;
        // look for a smaller index on the left
        high = mid - 1;
      }
      else
        low = mid + 1;  // look on the right
    }
    return ans;
  }

  public static void main(String[] args){
    //log.info("Matrix Median : {}", new MatrixMedian().matrixMedian(new int[][]{{1, 5, 7, 9, 11}, {2, 3, 4, 5, 10}, {8, 10, 12, 14, 16}}));
    log.info("Matrix Median : {}", new MatrixMedian().matrixMedian(new int[][]{{1, 3, 4}, {2, 4, 9}, {3, 6, 9}}));
  }
}
