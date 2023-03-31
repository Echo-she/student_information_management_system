package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

// ע�����
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
            ps= connection.prepareStatement(sql);//��д�õ�sql��䴫�ݵ����ݿ⣬�����ݿ�֪������Ҫ��ʲô
            int a=ps.executeUpdate();//����������ڸı����ݿ����ݣ�a����ı����ݿ������
            if(a>0){
                JLabel label = new JLabel("��ӳɹ���");
                label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(null, label);
            }
        }catch(Exception a){
            JLabel label = new JLabel("���ʧ�ܣ������û����Ƿ��ظ���");
            label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
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
        // 6�����
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel(new BorderLayout());

        JLabel jLabel = new JLabel("ѧ����Ϣ����ϵͳ�û�ע��",JLabel.CENTER);
        JButton jButton1 = new JButton("ע��");
        JButton jButton2 = new JButton("����");
        JTextField jTextField1 = new JTextField();
        JPasswordField jPasswordField1 = new JPasswordField();
        JPasswordField jPasswordField2 = new JPasswordField();

        JLabel jLabel1 = new JLabel("�û�����");
        JLabel jLabel2 = new JLabel("�� �� ��");
        JLabel jLabel3 = new JLabel("���ٴ��������룺");

        jLabel.setFont(new Font("����", Font.PLAIN, 30));
        jLabel1.setFont(new Font("����", Font.PLAIN, 20));
        jLabel2.setFont(new Font("����", Font.PLAIN, 20));
        jLabel3.setFont(new Font("����", Font.PLAIN, 20));
        jTextField1.setPreferredSize(new Dimension(280, 35));
        jPasswordField1.setPreferredSize(new Dimension(280, 35));
        jPasswordField2.setPreferredSize(new Dimension(205, 35));
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));
        jButton1.setFont(new Font("����", Font.PLAIN, 18));
        jButton2.setFont(new Font("����", Font.PLAIN, 18));
        // ע����¼�����
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(jPasswordField1.getPassword()).equals(String.valueOf(jPasswordField2.getPassword()))){
                    String sql="INSERT user VALUES('"+jTextField1.getText()+"','"+String.valueOf(jPasswordField1.getPassword())+"')";
                    insert_user(sql);
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
