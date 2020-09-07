package jdbc_datasource.com;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author shkstart
 * @create 2020-05-21 12:31
 */
public class DataSourceDemo01 {
    @Test
    public void demo01(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource dataSource = null;
        try {
            // 获得连接:
//            conn = JDBCUtils.getConnection();

            // 从连接池中获得连接：
            dataSource = new MyDataSource();
            conn = dataSource.getConnection();

            // 编写SQL：
            String sql = "select * from account";

            // 预编译SQL：
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
            // 归还连接:
//            dataSource.addBack(conn);
        }
    }
}
