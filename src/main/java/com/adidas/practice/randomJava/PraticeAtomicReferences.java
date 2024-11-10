package com.adidas.practice.randomJava;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PraticeAtomicReferences {
  public static void main(String args[]){
    new PraticeAtomicReferences().testAtomicReferenceList();
    CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
    strings.add("asome");
  }

  public void testAtomicReferenceList(){
    AtomicReference<List<Integer>> atomicList = new AtomicReference<>();
  }
}
