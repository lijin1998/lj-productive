package demo03;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author shkstart
 * @create 2020-05-20 12:48
 * JDBC的工具类测试
 */
public class Demo1 {
    @Test
    /**
     * 查询操作：使用工具类
     */
    public void demo01(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            st = conn.createStatement();

            String sql = "select * from user";

            rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("nickname") + " " + rs.getInt("age"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,st,conn);
        }
    }
}
