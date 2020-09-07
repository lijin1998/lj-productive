package jdbc_datasource_new.demo2;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdbc_datasource_new.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author shkstart
 * @create 2020-05-21 17:58
 * C3P0连接池的测试
 */

public class C3P0Demo1 {
    /**
     * 采用配置文件的方式：
     */
    @Test
    public void demo02(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // 获得连接：从连接池中获取：
            // 创建连接池：//创建连接池默认去类路径下查找c3p0-config.xml
            ComboPooledDataSource datasource = new ComboPooledDataSource();

            // 从连接池中获得连接:
            conn = datasource.getConnection();

            String sql = "select * from account";

            pst = conn.prepareStatement(sql);

            // 设置参数:
            // 执行SQL:
            rs = pst.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getDouble("money"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pst,conn);
        }
    }

    @Test
    /**
     * 手动设置参数的方式:
     */
    public void demo01(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // 获得连接：从连接池中获取：
            // 创建连接池：
            ComboPooledDataSource datasource = new ComboPooledDataSource();
            // 设置连接参数:
            datasource.setDriverClass("com.mysql.jdbc.Driver");
            datasource.setJdbcUrl("jdbc:mysql:///web_test4");
            datasource.setUser("root");
            datasource.setPassword("root");
            // 从连接池中获得连接:
            conn = datasource.getConnection();

            String sql = "select * from account";

            pst = conn.prepareStatement(sql);

            // 设置参数:
            // 执行SQL:
            rs = pst.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getDouble("money"));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pst,conn);
        }
    }
}
