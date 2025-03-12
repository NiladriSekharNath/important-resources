package com.adidas.interviewexperience.ukg.outputprinting;
public class Main {
  String name;
  String address;
  String city;


  public static void main (String[] args) {
    Main emp = new Main();
    emp.name = "ABC";
    emp.address = "West Bangal";
    emp.change(emp);
    System.out.println(emp.name); //
  }
  public void change(Main emp) {
    emp.name = "DEF"; //
    emp = new Main();
    /**
     * call to this here changes value in the calling object (emp.name "ABC")
     */
    Main emp1 = modify(this);

    System.out.println(emp1);
  }
  public Main modify(Main emp) {
    emp.name = "XYZ";

    emp = new Main();
    emp.name = "QA";
    emp = null;
    return emp;
  }
}