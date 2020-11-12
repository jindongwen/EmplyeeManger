package com.ylb.dao;

import com.ylb.entity.Employee;

import java.util.List;

/**
 * Employee 是员工对象，代表表格的每一行
 */

public interface EmployeeDao {
    /**
     * 列出所有员工
     * @return
     */
    public List<Employee> findAll();

    /**
     * 查询指定编号员工
     * @param empno
     * @return
     */
    public Employee findByID(int empno);

    /**
     * 添加员工
     * @param emp
     */
    public int save(Employee emp);

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    public int update(Employee emp);

    /**
     * 删除指定编号员工
     * @param empno
     * @return
     */
    public int delete(int empno);
}
