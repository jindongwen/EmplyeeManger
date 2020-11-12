package com.ylb.util;

import com.ylb.entity.Employee;

import java.sql.*;

/**
 *  数据库访问工具类
 */
public abstract class DBUtil {
    private DBUtil(){}

    /**
     * 获取数据库的连接
     * @return
     */
    public static Connection getConnection(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "123";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭所有
     * @param result
     * @param pStmt
     * @param conn
     */
    public static void closeAll(ResultSet result, Statement pStmt,Connection conn){
        try {
            if (result != null) {
                result.close();
            }
            if (pStmt != null) {
                pStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 完成 insert update delete
     * @param sql
     * @param params
     * @return
     */
    public static int ExecuteUpdate(String sql,Object[] params) {
        Connection conn = null;
        PreparedStatement pStat = null;
        ResultSet result = null;
        int n = 0;
        try {
            conn = DBUtil.getConnection();
            pStat = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pStat.setObject(i+1,params[i]);
            }
            n = pStat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeAll(result,pStat,conn);
        }
        return n;
    }
}
