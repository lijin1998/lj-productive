package demo05;

/**
 * @author shkstart
 * @create 2020-05-20 14:32
 */

import demo03.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC的CRUD的操作：PreparedStatement对象
 * @author jt
 *
 */
public class JDBCDemo05 {
    @Test
    /**
     * 保存操作
     */
    public void demo1(){
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            // 获得连接:
            conn = JDBCUtils.getConnection();
            // 编写SQL语句:
            String sql = "insert into user values (null,?,?,?,?)";
            // 预编译SQL:
            pst = conn.prepareStatement(sql);
            // 设置参数:
            pst.setString(1, "eee");
            pst.setString(2, "abc");
            pst.setString(3, "旺财");
            pst.setInt(4, 32);
            // 执行SQL
            int num = pst.executeUpdate();
            if(num > 0){
                System.out.println("保存成功！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtils.release(pst, conn);
        }
    }

    @Test
    /**
     * 修改操作
     */
    public void demo2(){
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "update user set username = ?,password = ?,nickname = ?,age = ? where id = ?";

            pst = conn.prepareStatement(sql);

            pst.setString(1,"abc");
            pst.setString(2,"124");
            pst.setString(3,"汪汪");
            pst.setString(4,"56");
            pst.setInt(5,5);

            int num = pst.executeUpdate();
            if (num > 0){
                System.out.println("修改成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pst,conn);
        }
    }

    @Test
    /**
     * 删除操作
     */
    public void demo3(){
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from user where id = ?";

            pst = conn.prepareStatement(sql);

            pst.setInt(1,6);

            int num = pst.executeUpdate();
            if (num > 0){
                System.out.println("删除成功！");
            }else {
                System.out.println("没用该数据");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pst,conn);
        }
    }

    @Test
    /**
     * 查询操作
     */
    public void demo4(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();

            String sql = "select * from user ";

            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getString("nickname") + " " + rs.getInt("age"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pst,conn);
        }
    }
}

