package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

// 修改密码面板
public class Change_Password {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Change Password");
        jFrame.setSize(500, 370);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void update_user(String sql){
        Connection connection=Mysql.BaseConnection();
        PreparedStatement ps=null;
        try{
            ps= connection.prepareStatement(sql);//把写好的sql语句传递到数据库，让数据库知道我们要干什么
            int a=ps.executeUpdate();//这个方法用于改变数据库数据，a代表改变数据库的条数
            if(a>0){
                JLabel label = new JLabel("修改成功！");
                label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(null, label);
            }
        }catch(Exception a){
        }try{
            if(ps!=null){
                ps.close();
            }if(connection!=null){
                connection.close();
            }
        }catch(Exception e2){
        }
    }

    public Change_Password(String name) {
        init();
        // 四个面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();

        JButton jButton1 = new JButton("修改");
        JButton jButton2 = new JButton("返回");
        JPasswordField jPasswordField1 = new JPasswordField();
        JPasswordField jPasswordField2 = new JPasswordField();

        JLabel jLabel2 = new JLabel("密 码 ：");
        JLabel jLabel3 = new JLabel("请再次输入密码：");

        jLabel2.setFont(new Font("宋体", Font.PLAIN, 20));
        jLabel3.setFont(new Font("宋体", Font.PLAIN, 20));
        jPasswordField1.setPreferredSize(new Dimension(280, 35));
        jPasswordField2.setPreferredSize(new Dimension(205, 35));
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));
        jButton1.setFont(new Font("宋体", Font.PLAIN, 18));
        jButton2.setFont(new Font("宋体", Font.PLAIN, 18));

        // 修改的事件监听
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))){
                    String sql="update user set password='"+String.valueOf(jPasswordField1.getPassword())+"' where name='"+name+"'";
                    update_user(sql);
                    jFrame.dispose();
                    new Login();
                }else {
                    JLabel label = new JLabel("两次密码不一样，请确认后重试！");
                    label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 返回的事件监听
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Administrators(name);
            }
        });

        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel1.add(jLabel2);
        jPanel1.add(jPasswordField1);

        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel2.add(jLabel3);
        jPanel2.add(jPasswordField2);

        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER,40,60));
        jPanel3.add(jButton1);
        jPanel3.add(jButton2);

        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER,60,40));
        jPanel4.add(jPanel1);
        jPanel4.add(jPanel2);

        jFrame.setLayout(new GridLayout(2,1));
        jFrame.add(jPanel4);
        jFrame.add(jPanel3);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Change_Password("admin");
    }
}
