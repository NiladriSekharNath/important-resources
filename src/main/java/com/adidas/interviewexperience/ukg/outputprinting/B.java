package com.adidas.interviewexperience.ukg.outputprinting;

class A {

  public void display() {
    System.out.println("Parent display");
  }
  public void show() {
    System.out.println("Parent show");
  }
}

public class B extends A {
  public void display() {
    System.out.println("Child display");
  }
  public void print() {
    System.out.println("Child Print");
  }
  public static void main(String[] args) {
    A a = new B();

    a.display(); // Child display
    //a.print(); // Child Print
    a.show(); //Parent show
    B b = new B();
    b.display(); // Child display
    b.print(); // Child Print
    b.show(); // Parent show

    B c = new B() {
      public void print() {
        System.out.println("Inside Print");
      }
      public void show() {
        System.out.println("Inside show");
      }
      public void dis() {
        System.out.println("Inside dis");
      }
    };
    c.display(); // Child display
    c.print(); // Inside Print
    c.show(); // Inside show
    //c.dis(); // Inside dis
  }
}