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

// ��¼����
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
        // ������
        JPanel jPanel1 = new JPanel(new BorderLayout());
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();

        JLabel jLabel1 = new JLabel("��ӭ����ѧ����Ϣ����ϵͳ", JLabel.CENTER);
        jLabel1.setFont(new Font("����", Font.PLAIN, 30));

        JButton b1 = new JButton("��¼");
        JButton b2 = new JButton("ע��");
        b1.setFont(new Font("����", Font.PLAIN, 16));
        b2.setFont(new Font("����", Font.PLAIN, 16));
        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b1.setPreferredSize(new Dimension(80, 40));
        b2.setPreferredSize(new Dimension(80, 40));

        JLabel username = new JLabel("�û���:");
        username.setFont(new Font("����", Font.PLAIN, 20));
        JTextField user = new JTextField();
        JLabel password = new JLabel("�� �� :");
        password.setFont(new Font("����", Font.PLAIN, 20));
        JPasswordField pwd = new JPasswordField();

        user.setPreferredSize(new Dimension(280, 35));
        pwd.setPreferredSize(new Dimension(280, 35));

        // ע�ᰴť���¼�����
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Register();
            }
        });

        // ��¼��ť���¼�����
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> map = new HashMap<String, String>();
                Connection connection = Mysql.BaseConnection();
                boolean bool = false;
                //���������
                Statement statement = null;
                ResultSet rs = null;//��ѯ�����������ȷŵ�rs��
                try {
                    String sql = "select * from user";
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);//ִ�����ݿ��ѯ�ķ������ŵ�rs��
                    while (rs.next()) {//rs�����൱��һ��ָ�룬ָ�����ݿ��һ��������
                        map.put(String.valueOf(rs.getObject("name")), String.valueOf(rs.getObject("password")));
                    }
                    bool = true;
                } catch (Exception a) {
                } finally {//�ص�����������д�������ݿ�ʹ�ú����رգ����û�йر����ݿ�Ľӿ����ޣ��´ξͲ�������
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
                        JLabel label = new JLabel("�˺ţ�����������������룡");
                        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                        JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (!bool) {
                    JLabel label = new JLabel("���ݿ�����ʧ�ܣ���ȷ�Ϻ����ԣ�");
                    label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JLabel label = new JLabel("û������û�����ȷ���û��Ƿ���ڣ�");
                    label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
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
