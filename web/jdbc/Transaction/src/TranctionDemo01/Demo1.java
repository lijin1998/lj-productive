package TranctionDemo01;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author shkstart
 * @create 2020-05-21 10:40
 * 事务管理的测试类：
 */

public class Demo1 {
    @Test
//    完成转账的案例
    public void demo01(){
        Connection conn = null;
        PreparedStatement pst  = null;
        try {
              /*完成转账代码：
              扣除某个账号的钱
              给另外一个账号加钱*/
            // 获得连接：
            conn = JDBCUtils.getConnection();

            // 编写SQL语句：
            String sql = "update account set money = money + ? where name = ?";

            // 预编译SQL:
            pst = conn.prepareStatement(sql);

            // 设置参数:
            // 用aaa账号给bbb账号转1000元
            pst.setDouble(1,-1000);
            pst.setString(2,"aaa");
            // 执行SQL：扣除aaa账号1000元
            pst.executeUpdate();

            // 给bbb账号加1000
            pst.setDouble(1,1000);
            pst.setString(2,"bbb");
            pst.executeUpdate();

            // 提交事务:
            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pst,conn);
        }
    }
}
