package com.ylb.dao.impl;

import com.ylb.dao.EmployeeDao;
import com.ylb.entity.Employee;

import java.util.List;

/**
 * 实现接口
 */
public class EmplyeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findByID(int empno) {
        return null;
    }

    @Override
    public void save(Employee emp) {

    }

    @Override
    public int update(Employee emp) {
        return 0;
    }

    @Override
    public int delete(int empno) {
        return 0;
    }
}
