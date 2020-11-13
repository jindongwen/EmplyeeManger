package com.ylb.util;

import com.ylb.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库访问工具类
 */
public abstract class DBUtil {
    private DBUtil() {
    }
   static String driver = null;
   static String url = null;
   static String user = null;
   static String password = null;
   private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);


    //静态代码块 只会执行一次
    static {
        Properties prop = new Properties();
        InputStream is = DBUtil.class.getResourceAsStream("/jdbc.properties");
        try {
            prop.load(is);
            logger.trace("打开成功");
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("打开失败"+ e.toString());
        }
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("pwd");
    }

    //定义一个日志记录器
    //在合适的位置使用并记录

    /**
     * 获取数据库的连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            logger.trace("获取数据库连接成功");
        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error("获取数据库连接失败" + throwables.toString());
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭所有
     *
     * @param result
     * @param pStmt
     * @param conn
     */
    public static void closeAll(ResultSet result, Statement pStmt, Connection conn) {
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
     *
     * @param sql
     * @param params
     * @return
     */
    public static int ExecuteUpdate(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement pStat = null;
        ResultSet result = null;
        int n = 0;
        try {
            conn = DBUtil.getConnection();
            pStat = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pStat.setObject(i + 1, params[i]);
            }
            n = pStat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeAll(result, pStat, conn);
        }
        return n;
    }
}
