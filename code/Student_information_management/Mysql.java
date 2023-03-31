package Student_information_management;

import java.sql.*;

// 完成数据库的连接工作
public class Mysql {
    public static Connection BaseConnection(){//用这个方法获取mysql的连接
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");//加载驱动类
            conn=DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/student_information?useUnicode=true&characterEncoding=utf8&useSSL=true","root","");//（url数据库的IP地址，user数据库用户名，password数据库密码）
        }catch(Exception e){ }
        return conn;
    }
}
