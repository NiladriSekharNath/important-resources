package com.adidas.design.patterns.structural.proxy.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class EmployeeDaoImpl implements EmployeeDao{

    Random random = new Random();
    @Override
    public void create(String client) {
        log.info("Created new row in the Employee table: {}", random.nextInt(0, 450000));
    }

    @Override
    public void delete(String client, int employeeId) {
        log.info("Deleted row with EmployeeId: {}", employeeId);
    }

    @Override
    public int get(String client, int employeeId) {
        log.info("Fetched data from DB with employeeId : {}", employeeId);
        return employeeId;
    }
}
