package demo02;

import org.junit.Test;

import java.sql.*;

/**
 * @author shkstart
 * @create 2020-05-20 11:45
 */
public class Demo02 {
    @Test
    /**
     * JDBC的入门
     * 插入
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
//            st = conn.createStatement();
//
//            // 3.2编写SQL语句:
//            String sql = "insert into user values (null,'eee','123','阿黄',22)";
//
//            // 3.3执行SQL:
//            int num = st.executeUpdate(sql);
//            if (num>0){
//                System.out.println("保存用户成功！");
//            }

            if (conn.createStatement().executeUpdate("insert into user values (null,'kkk','223','阿吊',22)")>0){
                System.out.println("保存用户成功！");
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4.释放资源
//            statement.close();
//            conn.close();

            if (st!=null){
                try {
                    st.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
                st = null;
            }
        }
    }
}
