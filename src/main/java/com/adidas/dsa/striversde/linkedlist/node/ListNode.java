package com.adidas.dsa.striversde.linkedlist.node;

public class ListNode {
  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public ListNode(int val){
    this.val = val;
    this.next = null;
  }
}