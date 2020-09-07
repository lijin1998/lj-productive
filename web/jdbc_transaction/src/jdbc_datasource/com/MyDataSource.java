package jdbc_datasource.com;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author shkstart
 * @create 2020-05-21 12:19
 * 自定义连接池
 */

public class MyDataSource implements DataSource {
    // 将一些连接存入到内存中，可以定义一个集合，用于存储连接对象。
    private List<Connection> connList = new ArrayList<Connection>();

    // 在初始化的时候提供一些连接
    public MyDataSource(){
        // 初始化连接：
        for (int i = 1; i <= 3; i++) {
            // 向集合中存入3个连接:
            connList.add(JDBCUtils.getConnection());
        }
    }

    // 从连接池中获得连接的方法
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = connList.remove(0);
        //增强连接：
        MyConnectionWrapper connectionWrapper = new MyConnectionWrapper(conn,connList);
        return connectionWrapper;
    }

    // 编写一个归还连接的方法:
    /*public void addBack(Connection conn){
        connList.add(conn);
    }*/

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
