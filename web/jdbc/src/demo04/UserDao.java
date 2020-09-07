package demo04;

import demo03.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author shkstart
 * @create 2020-05-20 13:35
 */
public class UserDao {
    public boolean login(String username, String password){
        //防止SQL注入漏洞
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        // 定义一个变量:
        boolean flag = false;
        try{
            // 获得连接:
            conn = JDBCUtils.getConnection();

            // 完成登录功能:
            // 编写SQL语句:
            String sql = "select * from user where username = ? and password = ?";

            //预编译SQL
            pst = conn.prepareStatement(sql);

            //设置相关的参数
            pst.setString(1,username);
            pst.setString(2,password);
            // 执行SQL:
            rs = pst.executeQuery();
            if(rs.next()){
                // 说明根据用户名和密码可以查询到这条记录
                flag = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtils.release(rs, pst, conn);
        }
        return flag;

        //SQL注入漏洞
        /*Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 定义一个变量:
        boolean flag = false;
        try{
            // 获得连接:
            conn = JDBCUtils.getConnection();
            // 完成登录功能:
            // 创建执行SQL语句的对象：
            stmt = conn.createStatement();
            // 编写SQL语句:
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            // 执行SQL:
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                // 说明根据用户名和密码可以查询到这条记录
                flag = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtils.release(rs, stmt, conn);
        }
        return flag;*/

    }
}
