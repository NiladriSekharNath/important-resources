package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

public class FindIntersectionPointOfTwoLinkedList {

  /**
   * Solution 1 :
   *
   * Approach 1 for both lists headA, headB we are doing this we are calculating the length of the list from headA,
   * and length of the list from headB when we find out we calculate the difference, for the list with the longer length
   * we move a pointer in the longer list till our difference amount with the shorter list is 0, once done we move both pointers
   * till we meet at a point that point is the intersection point.
   *
   *
   * TC : O(2max(length of list1,length of list2))+O(abs(length of list1-length of list2))+O(min(length of list1,length of list2))
   *
   * Reason: Finding the length of both lists takes max(length of list1, length of list2) because it is found simultaneously for both of them.
   * Moving the head pointer ahead by a difference of them. The next one is for searching.
   *
   * Space Complexity: O(1)
   *
   * Reason: No extra space is used.
   * Solution 2:
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int headALen, headBLen;
    headALen = len(headA);
    headBLen = len(headB);
    int lengthDifference = Math.abs(headALen-headBLen);
    if(headALen <= headBLen){
      return passThroughFasterLengthThenAtSameTime(headA, headB, lengthDifference);
    }
    else
      return passThroughFasterLengthThenAtSameTime(headB, headA, lengthDifference);

  }
  private int len(ListNode headA){
    ListNode tempPointer = headA;
    int count = 0;
    while(tempPointer != null){
      tempPointer = tempPointer.next;
      count++;
    }
    return count;
  }

  private ListNode passThroughFasterLengthThenAtSameTime(ListNode shorterListHead, ListNode longerListHead, int difference){
    ListNode slowerListPointer = shorterListHead, longerListPointer = longerListHead;
    for(int i = 0 ; i < difference ; i++){
      longerListPointer = longerListPointer.next;
    }
    while(slowerListPointer != null && longerListPointer != null){
      if(slowerListPointer == longerListPointer) return slowerListPointer;
      slowerListPointer = slowerListPointer.next ;
      longerListPointer = longerListPointer.next;
    }
    return null;
  }

  /**
   * approach 2 we will see and use this here
   */

}
