package com.ylb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 连接池
 */
public class ConnectionPool {
    /**
     * 存放多个数据库的连接
     */
    private static LinkedList<Connection> list = new LinkedList<>();

    /**
     * 第一次加载类时，执行
     */
    static {
            for (int i = 0; i < 1; i++) {
                list.add(newConnection());
            }
    }

    public static Connection getConnection() {
        if(list.size() > 0){
            return list.removeFirst();
        }else{
            return newConnection();
        }
    }
    //driver=com.mysql.cj.jdbc.Drive
    //        url=jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    //user=root
    //        pwd=123

    private static Connection newConnection(){
        String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "123";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static  void returnConnectionj(Connection conn){
        if(list.size() < 10) {
            list.addLast(conn);
        }else{
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
