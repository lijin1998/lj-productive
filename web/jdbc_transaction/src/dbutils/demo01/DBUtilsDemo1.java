package dbutils.demo01;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author shkstart
 * @create 2020-05-21 18:37
 * DBUtils的增删改的操作：
 */
public class DBUtilsDemo1 {
    @Test
//    添加操作
    public void demo01() throws SQLException {
        // 创建核心类：QueryRunner:
        QueryRunner queryRunner = new QueryRunner(JDBCUtils2.getDataSource());
        queryRunner.update("insert into account values (null,?,?)","eee","1000");

    }

    @Test
//    修改操作
    public void demo02() throws SQLException {
        // 创建核心类：QueryRunner:
        QueryRunner queryRunner = new QueryRunner(JDBCUtils2.getDataSource());
        queryRunner.update("update account set name  = ?,money = ? where id = ?","eee",20000,7);

    }

    @Test
//    删除操作
    public void demo03() throws SQLException {
        // 创建核心类：QueryRunner:
        QueryRunner queryRunner = new QueryRunner(JDBCUtils2.getDataSource());
        queryRunner.update("delete from account where name = ?","eee");

    }
}
