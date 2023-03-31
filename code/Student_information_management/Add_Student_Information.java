package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

// ���ѧ����Ϣ���
public class Add_Student_Information {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Add Student Information");
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void insert_student(String sql){
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
            JLabel label = new JLabel("���ʧ�ܣ����������ʽ�Ƿ���ȷ����ѧ���ظ���");
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

    public int find_class(String sql){
        Connection connection = Mysql.BaseConnection();
        Statement statement = null;
        ResultSet rs = null;//��ѯ�����������ȷŵ�rs��
        int id=0;
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);//ִ�����ݿ��ѯ�ķ������ŵ�rs��
            while (rs.next()) {//rs�����൱��һ��ָ�룬ָ�����ݿ��һ��������
                id=rs.getInt("id");
            }
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
            }
        }
        return id;
    }

    public Add_Student_Information(String name) {
        init();
        // �Ÿ����
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel();
        JPanel jPanel7 = new JPanel();
        JPanel jPanel8 = new JPanel();
        JPanel jPanel9 = new JPanel();

        JButton jButton1 = new JButton("����");
        JButton jButton2 = new JButton("���");
        jButton1.setFont(new Font("����", Font.PLAIN, 16));
        jButton2.setFont(new Font("����", Font.PLAIN, 16));
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));

        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel1 = new JLabel("ѧ�ţ�");
        jLabel1.setFont(new Font("����",Font.PLAIN,20));
        JTextField jTextField1 = new JTextField();
        jTextField1.setPreferredSize(new Dimension(280,40));
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);

        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel2 = new JLabel("�Ա�");
        jLabel2.setFont(new Font("����",Font.PLAIN,20));
        JRadioButton jRadioButton1 = new JRadioButton("��");
        JRadioButton jRadioButton2 = new JRadioButton("Ů");
        jRadioButton1.setFont(new Font("����", Font.PLAIN, 20));
        jRadioButton2.setFont(new Font("����", Font.PLAIN, 20));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        jPanel2.add(jLabel2);
        jPanel2.add(jRadioButton1);
        jPanel2.add(jRadioButton2);

        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel3 = new JLabel("�������ڣ�");
        jLabel3.setFont(new Font("����",Font.PLAIN,20));
        JTextField jTextField3 = new JTextField();
        jTextField3.setPreferredSize(new Dimension(240,40));
        jTextField3.setToolTipText("��ʽΪ��1999-12-31");
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        jPanel3.add(jLabel3);
        jPanel3.add(jTextField3);

        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel4 = new JLabel("רҵ��");
        jLabel4.setFont(new Font("����",Font.PLAIN,20));
        String[] major = {"�����רҵ", "�������", "���ѧ", "����ѧ", "����ѧ", "�������"};
        JComboBox<Object> jComboBox = new JComboBox<>(major);
        jComboBox.setPreferredSize(new Dimension(200,40));
        jComboBox.setFont(new Font("����",Font.PLAIN,20));
        jPanel4.add(jLabel4);
        jPanel4.add(jComboBox);

        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel5 = new JLabel("�༶��");
        jLabel5.setFont(new Font("����",Font.PLAIN,20));
        JTextField jTextField5 = new JTextField();
        jTextField5.setPreferredSize(new Dimension(280,40));
        jPanel5.add(jLabel5);
        jPanel5.add(jTextField5);

        jPanel6.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel6 = new JLabel("��ѧ��ȣ�");
        jLabel6.setFont(new Font("����",Font.PLAIN,20));
        JTextField jTextField6 = new JTextField();
        jTextField6.setPreferredSize(new Dimension(240,40));
        jTextField6.setToolTipText("��ʽΪ��1999-12-31");
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        jPanel6.add(jLabel6);
        jPanel6.add(jTextField6);

        jPanel7.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel7 = new JLabel("�߿��ܷ֣�");
        jLabel7.setFont(new Font("����",Font.PLAIN,20));
        JTextField jTextField7 = new JTextField();
        jTextField7.setPreferredSize(new Dimension(240,40));
        jPanel7.add(jLabel7);
        jPanel7.add(jTextField7);

        jPanel8.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel8 = new JLabel("������");
        jLabel8.setFont(new Font("����",Font.PLAIN,20));
        JTextField jTextField8 = new JTextField();
        jTextField8.setPreferredSize(new Dimension(280,40));
        jPanel8.add(jLabel8);
        jPanel8.add(jTextField8);

        // ���ص��¼�����
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Administrators(name);
            }
        });
        // ��ӵ��¼�����
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int xh=Integer.parseInt(jTextField1.getText());
                String xm=String.valueOf(jTextField8.getText());
                String xb;
                if (jRadioButton1.isSelected()){
                    xb=String.valueOf(jRadioButton1.getText());
                }else {
                    xb=String.valueOf(jRadioButton2.getText());
                }
                String csrq=String.valueOf(jTextField3.getText());
                String zy=String.valueOf(jComboBox.getSelectedItem());
                String bj=String.valueOf(jTextField5.getText());
                String rxnd=String.valueOf(jTextField6.getText());
                double gkzf=Double.parseDouble(jTextField7.getText());
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("�����רҵ",1001);
                map.put("�������",1002);
                map.put("���ѧ",1003);
                map.put("����ѧ",1004);
                map.put("����ѧ",1005);
                map.put("�������",1006);
                String sql="insert into student values ("+xh+",'"+xm+"'"+",'"+xb+"'"+",'"+csrq+"'"+",'"+map.get(zy)+"'"+",'"+find_class("SELECT class.id FROM class INNER JOIN major ON class.bh = major.bh AND major.bh="+map.get(zy)+" AND class.class_name='"+bj+"'")+"'"+",'"+rxnd+"',"+gkzf+")";
                insert_student(sql);
            }
        });

        jFrame.setLayout(new GridLayout(9,1));
        jPanel9.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        jPanel9.add(jButton2);
        jPanel9.add(jButton1);

        jFrame.add(jPanel1);
        jFrame.add(jPanel8);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);
        jFrame.add(jPanel7);
        jFrame.add(jPanel9);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Add_Student_Information("admin");
    }
}
