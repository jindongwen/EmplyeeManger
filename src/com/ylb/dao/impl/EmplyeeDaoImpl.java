package com.ylb.dao.impl;

import com.ylb.dao.EmployeeDao;
import com.ylb.entity.Employee;
import com.ylb.util.DBUtil;

import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;


/**
 * 实现接口
 */
public class EmplyeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        PreparedStatement pStmt = null;
        ResultSet result = null;
        String sql = null;
        List<Employee> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            sql = "select * from emp";
            pStmt = conn.prepareStatement(sql);
            result = pStmt.executeQuery();
            //public Employee(int empno, String ename, String job, int mgr, Date hireDate, double sal, double comm, int deptno) {
            while (result.next()) {
                int empno = result.getInt("empno");
                String ename = result.getString("ename");
                String job = result.getString("job");
                int mgr = result.getInt("mgr");
                Date hireDate = result.getDate("hireDate");
                double sal = result.getDouble("sal");
                double comm = result.getDouble("comm");
                int deptno = result.getInt("deptno");
                list.add(new Employee(empno, ename, job, mgr, hireDate, sal, comm, deptno));
            }
            for (Employee employee : list) {
                System.out.println(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeAll(result,pStmt,conn);
        }
        return list;
    }

    @Override
    public Employee findByID(int empno) {
        Connection conn = null;
        PreparedStatement pStat = null;
        ResultSet result = null;
        String sql = "select * from emp where empno=?";
        try {
            conn = DBUtil.getConnection();
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, empno);
            result = pStat.executeQuery();
            while (result.next()) {
                int empno1 = result.getInt("empno");
                String ename = result.getString("ename");
                String job = result.getString("job");
                int mgr = result.getInt("mgr");
                Date hireDate = result.getDate("hireDate");
                double sal = result.getDouble("sal");
                double comm = result.getDouble("comm");
                int deptno = result.getInt("deptno");
                return  new Employee(empno1,ename,job,mgr,hireDate,sal,comm,deptno);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeAll(result,pStat,conn);
        }
        return null;
    }

    @Override
    public int save(Employee emp) {
        String sql = "insert into emp values(default,?,?,?,?,?,?,?)";
        Object[] params = {emp.getEname(),emp.getJob(),emp.getMgr(),new java.sql.Date(emp.getHireDate().getTime()),emp.getSal(),emp.getComm(),emp.getDeptno()};
        return DBUtil.ExecuteUpdate(sql,params);

    }

    @Override
    public int update(Employee emp) {
        String sql = "update emp set job = ?,sal = ?, deptno= ? where empno = ?";
        Object[] params = {emp.getJob(),emp.getSal(),emp.getDeptno(),emp.getEmpno()};
        return DBUtil.ExecuteUpdate(sql,params);
    }

    @Override
    public int delete(int empno) {
        String sql = "delete  from emp  where empno = ?";
        return DBUtil.ExecuteUpdate(sql,new Object[]{empno});
    }
}
