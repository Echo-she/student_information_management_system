package Student_information_management;

import java.sql.*;

// ������ݿ�����ӹ���
public class Mysql {
    public static Connection BaseConnection(){//�����������ȡmysql������
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");//����������
            conn=DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/student_information?useUnicode=true&characterEncoding=utf8&useSSL=true","root","");//��url���ݿ��IP��ַ��user���ݿ��û�����password���ݿ����룩
        }catch(Exception e){ }
        return conn;
    }
}
