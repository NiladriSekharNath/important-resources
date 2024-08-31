package com.adidas.design.patterns.structural.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeDaoProxy implements EmployeeDao{

    private EmployeeDao employeeDao ;
    public EmployeeDaoProxy(){
        this.employeeDao = new EmployeeDaoImpl();
    }
    @Override
    public void create(String client) {
        if(client.equalsIgnoreCase("ADMIN")) {
            this.employeeDao.create(client);
            return ;
        }

        log.error("Access Denied");

    }

    @Override
    public void delete(String client, int employeeId) {
        if(client.equalsIgnoreCase("ADMIN")) {
            this.employeeDao.delete(client, employeeId);
        }
        log.error("Access denied");
    }

    @Override
    public int get(String client, int employeeId) {
        if(client.equalsIgnoreCase("ADMIN") || client.equalsIgnoreCase("USER"))
            return this.employeeDao.get(client, employeeId);
        return 0;
    }
}
