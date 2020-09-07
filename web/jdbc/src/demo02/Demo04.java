package demo02;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author shkstart
 * @create 2020-05-20 12:26
 */
public class Demo04 {
    @Test
    /**
     * JDBC的入门
     * 删除
     */
    public void demo01() throws Exception {
        Connection conn = null;
        Statement st = null;

        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 2.获得连接
            conn = DriverManager.getConnection("jdbc:mysql:///web_test3", "root", "root");

            // 3.基本操作：执行SQL
            // 3.1获得执行SQL语句的对象
            /*st = conn.createStatement();

            // 3.2编写SQL语句:
            String sql = "delete from user where id = 8";

            // 3.3执行SQL:
            int num = st.executeUpdate(sql);
            if (num > 0){
                System.out.println("删除成功！");
            }*/

            if (conn.createStatement().executeUpdate("delete from user where id = 8")>0){
                System.out.println("删除成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (st!=null){
                try {
                    st.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
                st = null;
            }
            if (conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
                conn = null;
            }
        }
    }
}
