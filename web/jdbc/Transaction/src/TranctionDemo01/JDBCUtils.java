package TranctionDemo01;

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
        //获取属性文件中的内容
/*        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\workspace_idea\\jdbc\\src\\demo03\\db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        diverClassName = properties.getProperty("diverClassName");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");*/

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
