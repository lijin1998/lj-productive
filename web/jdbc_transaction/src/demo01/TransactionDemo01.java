package demo01;

/**
 * @author shkstart
 * @create 2020-05-21 10:26
 */

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务管理的测试类：
 * @author jt
 */
public class TransactionDemo01 {

    /**
     * 完成转账的案例
     */
    @Test
    public void demo01(){
        Connection conn = null;
        PreparedStatement pst  = null;
        try {
              /*完成转账代码：
              扣除某个账号的钱
              给另外一个账号加钱*/
            // 获得连接：
            conn = JDBCUtils.getConnection();

            // 开启事务
            conn.setAutoCommit(false);

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

            int i = 1 / 0;

            // 给bbb账号加1000
            pst.setDouble(1,1000);
            pst.setString(2,"bbb");
            pst.executeUpdate();

            // 提交事务:
            conn.commit();

        }catch (Exception e){
            // 回滚事务:
            try {
                conn.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pst,conn);
        }
    }
}
