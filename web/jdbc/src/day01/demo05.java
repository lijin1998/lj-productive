package day01;

import org.junit.Test;

import java.sql.*;

/**
 * @author shkstart
 * @create 2020-05-20 12:31
 */
public class demo05 {
    @Test
    /**
     * JDBC的入门
     * 查询
     */
    public void demo01() {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2.获得连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test3", "root", "root");

            // 3.基本操作：执行SQL
            // 3.1获得执行SQL语句的对象
            statement = conn.createStatement();

            // 3.2编写SQL语句:
            String sql = "select * from user where id = 4";

            // 3.3执行SQL:
            rs = statement.executeQuery(sql);

            // 3.4遍历结果集:
            if (rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("nickname") + " " + rs.getInt("age"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4.释放资源
//            rs.close();
//            statement.close();
//            conn.close();
            if (rs!=null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (statement!=null){
                try {
                    statement.close();
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
}
