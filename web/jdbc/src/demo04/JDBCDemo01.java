package demo04;

import org.junit.Test;

/**
 * @author shkstart
 * @create 2020-05-20 13:52
 *
 *SQL注入漏洞
 */
public class JDBCDemo01 {

    @Test
    public void demo01(){
        UserDao userDao = new UserDao();
        boolean flag = userDao.login("aaa","123");
//        boolean flag = userDao.login("aaa' or 'das", "adasd");
//        boolean flag = userDao.login("aaa' -- ", "adasd");
        if (flag){
            System.out.println("登陆成功!");
        }else {
            System.out.println("登陆失败!");
        }
    }
}
