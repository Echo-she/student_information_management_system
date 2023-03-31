package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

// 注册面板
public class Register {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Register");
        jFrame.setSize(610, 510);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void insert_user(String sql){
        Connection connection=Mysql.BaseConnection();
        PreparedStatement ps=null;
        try{
            ps= connection.prepareStatement(sql);//把写好的sql语句传递到数据库，让数据库知道我们要干什么
            int a=ps.executeUpdate();//这个方法用于改变数据库数据，a代表改变数据库的条数
            if(a>0){
                JLabel label = new JLabel("添加成功！");
                label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(null, label);
            }
        }catch(Exception a){
            JLabel label = new JLabel("添加失败，请检查用户名是否重复！");
            label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
        }try{
            if(ps!=null){
                ps.close();
            }if(connection!=null){
                connection.close();
            }
        }catch(Exception e2){
        }
    }

    public Register() {
        init();
        // 6个面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel(new BorderLayout());

        JLabel jLabel = new JLabel("学生信息管理系统用户注册",JLabel.CENTER);
        JButton jButton1 = new JButton("注册");
        JButton jButton2 = new JButton("返回");
        JTextField jTextField1 = new JTextField();
        JPasswordField jPasswordField1 = new JPasswordField();
        JPasswordField jPasswordField2 = new JPasswordField();

        JLabel jLabel1 = new JLabel("用户名：");
        JLabel jLabel2 = new JLabel("密 码 ：");
        JLabel jLabel3 = new JLabel("请再次输入密码：");

        jLabel.setFont(new Font("宋体", Font.PLAIN, 30));
        jLabel1.setFont(new Font("宋体", Font.PLAIN, 20));
        jLabel2.setFont(new Font("宋体", Font.PLAIN, 20));
        jLabel3.setFont(new Font("宋体", Font.PLAIN, 20));
        jTextField1.setPreferredSize(new Dimension(280, 35));
        jPasswordField1.setPreferredSize(new Dimension(280, 35));
        jPasswordField2.setPreferredSize(new Dimension(205, 35));
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));
        jButton1.setFont(new Font("宋体", Font.PLAIN, 18));
        jButton2.setFont(new Font("宋体", Font.PLAIN, 18));
        // 注册的事件监听
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))){
                    String sql="INSERT user VALUES('"+jTextField1.getText()+"','"+String.valueOf(jPasswordField1.getPassword())+"')";
                    insert_user(sql);
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
                new Login();
            }
        });
        jFrame.setLayout(new GridLayout(3,1,0,0));
        jPanel1.setLayout(new GridLayout(3,1,0,20));
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,40,50));

        jPanel3.add(jLabel1);
        jPanel3.add(jTextField1);
        jPanel4.add(jLabel2);
        jPanel4.add(jPasswordField1);
        jPanel5.add(jLabel3);
        jPanel5.add(jPasswordField2);

        jPanel1.add(jPanel3);
        jPanel1.add(jPanel4);
        jPanel1.add(jPanel5);

        jPanel2.add(jButton1);
        jPanel2.add(jButton2);

        jPanel6.add(jLabel);
        jFrame.add(jPanel6);
        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Register();
    }
}
