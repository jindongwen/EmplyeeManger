package com.ylb.test;

import com.ylb.dao.EmployeeDao;
import com.ylb.dao.impl.EmplyeeDaoImpl;
import com.ylb.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 前台类
 */
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("*****欢迎进入员工管理系统*****");
            System.out.println("\t1.查询所有员工信息");
            System.out.println("\t2.查询指定编号员工");
            System.out.println("\t3.添加员工信息");
            System.out.println("\t4.修改员工信息");
            System.out.println("\t5.删除员工信息");
            System.out.println("\t6.退出");
            System.out.println("***************************");
            System.out.print("请选择菜单：");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    findAll();
                    break;
                case 2:
                    findByID();
                    break;

                case 3:
                    save();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    System.out.println("谢谢使用");
                    return;
                default:
                    System.out.println("输入错误");
            }
            System.out.println("请输入任意键继续");
            //input.next();
            input.nextLine();
            input.nextLine();
        } while (true);
    }

    public static void delete() {
        // 从键盘输入要添加的员工信息
        Scanner input = new Scanner(System.in);
        System.out.println("输入要删除的员工编号");
        int empno = input.nextInt();

        //调用后台完成添加操作并返回结果
        EmployeeDao employeeDao = new EmplyeeDaoImpl();
        int n = employeeDao.delete(empno);
        if (n > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        //输出结果
    }

    public static void update() {
        // 从键盘输入要添加的员工信息
        Scanner input = new Scanner(System.in);
        System.out.println("输入要修改的员工编号");
        int empno = input.nextInt();
        System.out.println("输入员工岗位");
        String job = input.next();
        System.out.println("输入员工薪水");
        Double sal = input.nextDouble();
        System.out.println("输入员工部门编号");
        int deptno = input.nextInt();
        //调用后台完成添加操作并返回结果
        Employee emp = new Employee(job, sal, empno, deptno);
        EmployeeDao employeeDao = new EmplyeeDaoImpl();
        int n = employeeDao.update(emp);
        if (n > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
        //输出结果
    }

    public static void save() {
        // 从键盘输入要添加的员工信息
        Scanner input = new Scanner(System.in);
        System.out.println("输入员工姓名");
        String ename = input.next();
        System.out.println("输入员工岗位");
        String job = input.next();
        System.out.println("输入上级编号");
        int mgr = input.nextInt();
        System.out.println("输入员工入职时间（yyyy-MM-dd）");
        String sdate = input.next();
        System.out.println("输入员工薪水");
        Double sal = input.nextDouble();
        System.out.println("输入员工津贴");
        Double comm = input.nextDouble();
        System.out.println("输入员工部门编号");
        int deptno = input.nextInt();
        Date hirDate = null;

        DateFormat sdf = new SimpleDateFormat("y-M-d");
        try {
            hirDate = sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //调用后台完成添加操作并返回结果
        Employee emp = new Employee(ename, job, mgr, hirDate, sal, comm, deptno);
        EmployeeDao employeeDao = new EmplyeeDaoImpl();
        int n = employeeDao.save(emp);
        if (n > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
        //输出结果
    }

    public static void findAll() {
        EmployeeDao emplyeeDao = new EmplyeeDaoImpl();
        List<Employee> empList = emplyeeDao.findAll();
        for (Employee employee : empList) {
            System.out.println(employee);
        }
    }

    public static void findByID() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int index = input.nextInt();
        EmployeeDao emplyeeDao = new EmplyeeDaoImpl();
        Employee tmp = emplyeeDao.findByID(index);
        if (tmp == null) {
            System.out.println("查无此人");
            return;
        }
        System.out.println(tmp);
    }
}

class ttt {
    static {
        System.out.println("........................");
    }
}
