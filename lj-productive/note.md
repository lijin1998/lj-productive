# JDBC
## 什么是JDBC
java数据库连接（Java Database Connectivity）

## 有什么用
利用 Java 代码， 可以操作数据库。

## 怎么用
1. 注册驱动
2. 获取数据库连接
3. 创建 Statement / PreparedStatement / 对象
4. 执行SQL
5. 处理结果
6. 释放资源

## 示例
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author lujiapeng
 * @className JDBC01
 * @description JDBC的第一个例子
 **/
public class JDBC01 {
    public static void main( String[] args ) throws  Exception {
        // 01 : 注册驱动 ( 引入对应的 jar 包 )
        Class.forName("com.mysql.cj.jdbc.Driver") ;
        System.out.println("注册驱动成功");
        // 02 : 获取数据库连接
        // 在Java 中， 使用 java.sql.Connection 接口表示 一个 连接
        Connection connection ;
        String url = "jdbc:mysql://localhost:3306/ecut?serverTimezone=Asia/Shanghai" ;
        String username = "root" ;
        String password = "root" ;
        connection = DriverManager.getConnection(url , username , password) ;
        System.out.println( connection );
        System.out.println( "获取连接成功" );
        // 03 ： 创建 Statement 对象
        Statement statement = connection.createStatement() ;
        // 04 ： 执行SQL
        String SQL = "select * from country" ;
        boolean result = statement.execute( SQL ) ;
        System.out.println( result );

        // 05 ： 处理结果
        ResultSet resultSet = statement.getResultSet();
        while( resultSet.next() ){
            int id = resultSet.getInt("id");
            String countryname = resultSet.getString("countryname");
            String countrycode = resultSet.getString("countrycode");
            System.out.println(id + " " + countryname + " " + countrycode );
        }
        // 最后一步，释放资源 : 先开的 后关闭
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

## 使用 JDBC 完成数据库的 增加、删除、修改、查询 
- 增加
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author lujiapeng
 * @className JDBC02
 * @description 实现增加数据
 * @date 2020/9/7 14:43
 **/
public class JDBC02 {
    public static void main(String[] args) throws Exception{
        // 加载驱动
        // 用到的原理是 ： 反射
        Class.forName("com.mysql.cj.jdbc.Driver") ;
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/ecut?serverTimezone=Asia/Shanghai" ;
        String username = "root" ;
        String password = "root" ;
        Connection connection = DriverManager.getConnection(url , username , password ) ;
        // 获取 Statement / PreparedStatement / CallableStatement 对象
        // PreparedStatement 与 CallableStatement 都是 Statement 的 子接口

        Statement statement = connection.createStatement() ;

        // 执行SQL( 插入语句 )
        String SQL = "insert into country( countryname, countrycode) values ('南非共和国','NF') , ('埃及','AJ')" ;
        // 想要执行 insert、update、delete 语句，那么就需要 使用 executeUpdate 方法
        // executeUpdate 方法一般用于执行 DDL
        // 该方法返回  受SQL 影响的记录条数
        int executeUpdate = statement.executeUpdate(SQL);
        // 获取结果
        System.out.println( executeUpdate );

        // 关闭资源
        statement.close();
        connection.close();
    }
}

``` 

- 练习 ： 自己实现 删除、修改数据 。
- 练习2： 自己实现 查询数据 ，并将结果输出。

### 常用对象

- Connection ： 表示一个连接
-  Statement  ： 用于执行一条SQL语句

    Statement执行过程
        1. 将 SQL 发给数据库
        2. SQL 会编译
        3. 执行SQL
        4. 再获取结果。
        
   - 常用方法：
    ```text
       execute( [String SQL] ) : 一般用于执行 任意SQL，返回boolean 类型
       executeUpdate( String SQL ) : 一般用于执行 DML(insert 、update、delete) 和 DDL( create table 、drop table等)
       executeQuery( String SQL ) : 一般用于 静态 查询 SQL，例如： select * from country where id = 1; 
    ```
- ResultSet : 结果集，返回一个具体的结果 
    主要方法：
    - getXXX( int columnIndex ) : 根据指定的列数，获取结果
    - getXXX( String columnLabel) : 根据指定的别名，获取结果

- PreparedStatement : 表示预编译SQL语句的对象。也就是将 SQL 语句，再执行之前，就发给数据库了，然后，每次都是替换 指定的参数即可。

    - 练习 ： 使用 PreparedStatement 进行 增加、删除、修改、查询
    
- CallableStatement : 用于 执行 存储过程。

## 事务 
事务封装在 java.sql.Connection 中 。
- commit() : 提交事务
- rollback() : 回滚事务


## 封装工具类
目的 ： 为了 程序的简单性 ， 便于我们使用！可能不是那么复杂，功能性考虑的不是很好，只是提供一个思路。
```java
package cn.ecut;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author lujiapeng
 * @className JDBCHelper
 * @description JDBC工具类，可以叫做 JDBCHelper 或 JDBCUtil
 **/
public class JDBCHelper {
    private static String driverName ;
    private static String url ;
    private static String username ;
    private static String password ;
    private static boolean autoCommit ;
    private static int isolation ;

    private static Connection connection ;
    /**
     * 0、加载配置文件 ：当类被加载之后，就直接 加载 配置文件
     */
    static{
        // 类代码块 ： 当类加载的时候，就会被执行，而且比 构造方法先执行
        config();
    }
    private static void config(){
        // 创建一个对象，获取到资源文件
        Properties properties = new Properties() ;

        // 通过 Class 中的方法，可以获取到 指定的资源文件
        InputStream resourceAsStream = JDBCHelper.class.getResourceAsStream("/jdbc.properties");
        // 将流中的中的内容，加载到 properties 对象中
        try {
            properties.load( resourceAsStream );
            // 根据指定的属性名，获取 相应的内容（属性值）
            String connect = properties.getProperty("connect");
            String autocommit = properties.getProperty("jdbc.connection.transaction.autocommit");
            autoCommit = Boolean.valueOf( autocommit ) ;
            String i = properties.getProperty("jdbc.connection.transaction.isolation");
            isolation = Integer.valueOf( i ) ;
            driverName = properties.getProperty("jdbc.connection.mysql.driver");
             url = properties.getProperty("jdbc.connection.mysql.url") ;
             username = properties.getProperty("jdbc.connection.mysql.username") ;
             password = properties.getProperty("jdbc.connection.mysql.password") ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1、加载驱动
     */
    private static boolean load(){
        try {
            Class.forName( driverName ) ;
            return true ;
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动异常：" + e.getMessage());
        }
        return false ;
    }
    /**
     * 2、建立连接
     */
    public static Connection connect(){
        if( load() ){
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("建立连接失败" + e.getMessage() );
            }
        }
        return connection ;
    }
    /**
     * 3、设置事务是否是 自动提交的， 并且 还要设置 事务的隔离级别
     */
    public static void transaction(){
        if(Objects.isNull( connection)){
            connection = connect() ;
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.setTransactionIsolation(isolation);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 4、创建 Statement 对象 或 PreparedStatement 对象
     */
    public static Statement statement(){
        Statement st = null ;
        if( Objects.isNull(connection )){
            connection = connect() ;
        }
        try {
            st = connection.createStatement() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st ;
    }


    /**
     *
     * @param SQL
     * @param autoGeneratedKeys 是否需要自动生成主键
     * @return
     */
    public static PreparedStatement prepare( String SQL , boolean autoGeneratedKeys ){
        PreparedStatement ps = null ;
        if( Objects.isNull(connection )){
            connection = connect() ;
        }
        try {
            if( autoGeneratedKeys ) {
                ps = connection.prepareStatement(SQL , Statement.RETURN_GENERATED_KEYS);
            }else{
                ps = connection.prepareStatement( SQL ) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps ;
    }

    /**
     * 5、执行 SQL ： 查询、insert、delete、update
     */
    /**
     * 仅仅处理查询语句
     * @param SQL
     * @param params 可变长参数，在方法中当作数组来处理即可。
     * @return
     */
    public static ResultSet query( String SQL , Object... params ){
        if( SQL == null || SQL.trim().isEmpty() || !SQL.trim().toLowerCase().startsWith("select")){
            throw new RuntimeException("你的SQL语句为空，或不是查询语句") ;
        }
        ResultSet rs = null ;
        if(params.length > 0 ){
            PreparedStatement ps = prepare( SQL , false ) ;
            try {
                for( int  i = 0 ; i < params.length ; i ++ ){
                    ps.setObject( i+1 , params[i]);
                }
                rs = ps.executeQuery() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Statement st = statement() ;
            try {
                rs = st.executeQuery( SQL ) ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rs ;
    }


    /**
     * 执行 Insert、update、delete等SQL
     * @param SQL
     * @param params
     * @return
     */
    public static boolean  execute( String SQL , Object... params ){
        if( SQL == null || SQL.trim().isEmpty() || SQL.trim().toLowerCase().startsWith("select")){
            throw new RuntimeException("你的SQL语句为空，或是 查询语句") ;
        }
        boolean r = false ;
        if(params.length > 0 ){
            PreparedStatement ps = prepare( SQL , false ) ;
            Connection c = null ;
            try {
                c = ps.getConnection() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                for( int  i = 0 ; i < params.length ; i ++ ){
                    ps.setObject( i+1 , params[i]);
                }
                 ps.executeUpdate() ;
                r = true ;
                // 提交事务
                commit( c );
            } catch (SQLException e) {
                e.printStackTrace();
                rollback( c );
            }
        }else{
            Statement st = statement() ;
            Connection c = null ;
            try {
                c = st.getConnection() ;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                st.executeUpdate( SQL ) ;
                r = true ;
                commit( c );
            } catch (SQLException e) {
                e.printStackTrace();
                rollback( c );
            }
        }
        return r ;
    }


    /**
     * 6、处理事务： 提交或 回滚事务
     */
    private static void commit( Connection c ){
        if( c != null && autoCommit ){
            try {
                c.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void rollback( Connection c ){
        if( c != null && autoCommit ){
            try {
                c.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 最后一步：释放资源
     */
    public static void release( Object o ){
        if(Objects.nonNull( o )){
            if( o instanceof ResultSet){
                ResultSet resultSet = ( ResultSet) o ;
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( o instanceof Statement){
                Statement st = ( Statement) o ;
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( o instanceof Connection){
                Connection c = ( Connection) o ;
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```

要求 ： 自己能写出来！

# 前端
三大核心：HTML + CSS + JavaScript

备注：仅仅是应用

参考网站：w3school , 主要是知道上哪里去寻找参考文档（API）

## 方法与变量的定义
参看 javascript01.html

## BOM
BOM : Browser Object Model 浏览器对象模型

### window 
- setTimeout( 表达式或函数 , 毫秒值) ： 表示 多少毫秒值之后执行 前面的表达式或函数 
- clearTimeout() : 清除 ， 具体用法参看 javascript01.html 

练习 ： 熟练掌握这两个方法。

# 额外补充
## 获取 Class 类型的几种方式
1. 通过 指定的类.class 的方式获取 ，例如： java.lang.String.class 。 
2. 如果是基本数据类型或数组，也可以使用 .class 的方式获取 。例如：int.class ; int[].class ;
3. 如果是引用类型，可以通过 运行时 实例 的 getClass方法，进行获取。例如：String s = "123" ; s.getClass() ; 
4. 通过 Class 类型的 静态方法，可以获取到对应的class 实例 

