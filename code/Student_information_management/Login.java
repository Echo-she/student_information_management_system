package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

// 登录界面
public class Login extends JFrame {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Login");
        jFrame.setSize(600, 400);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Login() {
        init();
        // 五个面板
        JPanel jPanel1 = new JPanel(new BorderLayout());
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();

        JLabel jLabel1 = new JLabel("欢迎来到学生信息管理系统", JLabel.CENTER);
        jLabel1.setFont(new Font("宋体", Font.PLAIN, 30));

        JButton b1 = new JButton("登录");
        JButton b2 = new JButton("注册");
        b1.setFont(new Font("宋体", Font.PLAIN, 16));
        b2.setFont(new Font("宋体", Font.PLAIN, 16));
        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b1.setPreferredSize(new Dimension(80, 40));
        b2.setPreferredSize(new Dimension(80, 40));

        JLabel username = new JLabel("用户名:");
        username.setFont(new Font("宋体", Font.PLAIN, 20));
        JTextField user = new JTextField();
        JLabel password = new JLabel("密 码 :");
        password.setFont(new Font("宋体", Font.PLAIN, 20));
        JPasswordField pwd = new JPasswordField();

        user.setPreferredSize(new Dimension(280, 35));
        pwd.setPreferredSize(new Dimension(280, 35));

        // 注册按钮的事件监听
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Register();
            }
        });

        // 登录按钮的事件监听
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> map = new HashMap<String, String>();
                Connection connection = Mysql.BaseConnection();
                boolean bool = false;
                //结果集对象
                Statement statement = null;
                ResultSet rs = null;//查询出来的数据先放到rs中
                try {
                    String sql = "select * from user";
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);//执行数据库查询的方法，放到rs中
                    while (rs.next()) {//rs对象相当于一个指针，指向数据库的一横行数据
                        map.put(String.valueOf(rs.getObject("name")), String.valueOf(rs.getObject("password")));
                    }
                    bool = true;
                } catch (Exception a) {
                } finally {//重点下面代码必须写，当数据库使用后必须关闭，如果没有关闭数据库的接口有限，下次就不能连接
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (map.get(user.getText()) != null) {
                    if (map.get(user.getText()).equals(String.valueOf(pwd.getPassword()))) {
                        jFrame.dispose();
                        new Administrators(user.getText());
                    } else {
                        JLabel label = new JLabel("账号，密码错误，请重新输入！");
                        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                        JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (!bool) {
                    JLabel label = new JLabel("数据库连接失败，请确认后重试！");
                    label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JLabel label = new JLabel("没有这个用户，请确认用户是否存在！");
                    label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jFrame.setLayout(new GridLayout(3, 1, 0, 50));
        jPanel2.setLayout(new GridLayout(2, 1, 0, 20));
        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        jPanel1.add(jLabel1);

        jPanel4.add(username);
        jPanel4.add(user);

        jPanel5.add(password);
        jPanel5.add(pwd);

        jPanel2.add(jPanel4);
        jPanel2.add(jPanel5);

        jPanel3.add(b1);
        jPanel3.add(b2);

        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
