package jdbc_datasource.com;

import java.sql.*;

/**
 * @autho"root"r shkstart
 * @create 2020-05-20 12:36
 * JDBC的工具类
 */

public class JDBCUtils {

    private static final String diverClassName;
    private static final String url;
    private static final String username;
    private static final String password;
    static {

        diverClassName = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql:///web_test4";
        username = "root";
        password = "root";
    }
    /**
     * 注册驱动的方法
     */
    public static void loadDriver(){
        try {
            Class.forName(diverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接的方法
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            // 将驱动一并注册:
            loadDriver();
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源的方法
     */
    public static void release(Statement pst, Connection conn){
        if (pst != null){
            try {
                pst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void release(ResultSet rs, Statement st, Connection conn){
        if (rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (st != null){
            try {
                st.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
