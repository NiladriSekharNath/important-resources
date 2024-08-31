package com.adidas.design.patterns.structural.proxy.code;

public class TestEmployeeProxy {
    public static void main(String args[]){
        /**
         * so here we are creating a different type of proxy object which would internally call the concrete class method for other verifications or uses
         *
         */
        EmployeeDao employeeDaoProxy = new EmployeeDaoProxy();
        employeeDaoProxy.create("user");
        employeeDaoProxy.create("ADMIN");
    }
}
