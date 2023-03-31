package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

// �޸��������
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
            ps= connection.prepareStatement(sql);//��д�õ�sql��䴫�ݵ����ݿ⣬�����ݿ�֪������Ҫ��ʲô
            int a=ps.executeUpdate();//����������ڸı����ݿ����ݣ�a����ı����ݿ������
            if(a>0){
                JLabel label = new JLabel("�޸ĳɹ���");
                label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
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
        // �ĸ����
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();

        JButton jButton1 = new JButton("�޸�");
        JButton jButton2 = new JButton("����");
        JPasswordField jPasswordField1 = new JPasswordField();
        JPasswordField jPasswordField2 = new JPasswordField();

        JLabel jLabel2 = new JLabel("�� �� ��");
        JLabel jLabel3 = new JLabel("���ٴ��������룺");

        jLabel2.setFont(new Font("����", Font.PLAIN, 20));
        jLabel3.setFont(new Font("����", Font.PLAIN, 20));
        jPasswordField1.setPreferredSize(new Dimension(280, 35));
        jPasswordField2.setPreferredSize(new Dimension(205, 35));
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));
        jButton1.setFont(new Font("����", Font.PLAIN, 18));
        jButton2.setFont(new Font("����", Font.PLAIN, 18));

        // �޸ĵ��¼�����
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))){
                    String sql="update user set password='"+String.valueOf(jPasswordField1.getPassword())+"' where name='"+name+"'";
                    update_user(sql);
                    jFrame.dispose();
                    new Login();
                }else {
                    JLabel label = new JLabel("�������벻һ������ȷ�Ϻ����ԣ�");
                    label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ���ص��¼�����
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
