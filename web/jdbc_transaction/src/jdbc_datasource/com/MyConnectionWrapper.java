package jdbc_datasource.com;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author shkstart
 * @create 2020-05-21 13:03
 */
public class MyConnectionWrapper extends ConnectionWrapper {

    private List<Connection> connList;
    private Connection conn;

    public MyConnectionWrapper(Connection conn, List<Connection> connList) {
        super(conn);
        this.conn = conn;
        this.connList = connList;
    }

    //增强close()方法
    @Override
    public void close() throws SQLException{
//        super.close();

        //归还连接
        connList.add(conn);
    }
}
