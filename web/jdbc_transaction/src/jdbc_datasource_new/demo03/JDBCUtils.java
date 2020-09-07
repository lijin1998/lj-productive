package jdbc_datasource_new.demo03;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @autho"root"r shkstart
 * @create 2020-05-20 12:36
 * JDBC的工具类
 */

public class JDBCUtils {
    // 创建一个连接池：但是这个连接池只需要创建一次即可。
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();


    /**
     * 获得连接的方法
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

//    获得连接池:
    public static DataSource getDataSource(){
        return dataSource;
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
