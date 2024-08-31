package com.adidas.design.patterns.structural.proxy.code;

public interface EmployeeDao {

    public void create(String client) ;

    public void delete(String client, int employeeId);

    public int get(String client, int employeeId);
}
