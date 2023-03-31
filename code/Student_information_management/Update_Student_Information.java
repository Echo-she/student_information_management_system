package Student_information_management;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
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

// �޸�ѧ����Ϣ���
public class Update_Student_Information {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Update Student Information");
        jFrame.setSize(900, 690);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Vector<Vector<Object>> look_student_information(String sql) {
        Vector<Vector<Object>> content = new Vector<>();
        Connection connection = Mysql.BaseConnection();
        Statement statement = null;
        ResultSet rs = null;//��ѯ�����������ȷŵ�rs��
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);//ִ�����ݿ��ѯ�ķ������ŵ�rs��
            while (rs.next()) {//rs�����൱��һ��ָ�룬ָ�����ݿ��һ��������
                Vector<Object> vector1 = new Vector<>();
                vector1.add(rs.getObject("xh"));
                vector1.add(rs.getObject("xm"));
                vector1.add(rs.getObject("xb"));
                vector1.add(rs.getObject("csrq"));
                vector1.add(rs.getObject("zy"));
                vector1.add(rs.getObject("bj"));
                vector1.add(rs.getObject("rxnd"));
                vector1.add(rs.getObject("gkzf"));
                content.add(vector1);
            }
        } catch (Exception a) {
            JLabel label = new JLabel("��ѯ��Ϣʧ�ܣ���ȷ�Ϻ����ԣ�");
            label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
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
        return content;
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

    public void update_student(String sql) {
        Connection connection = Mysql.BaseConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);//��д�õ�sql��䴫�ݵ����ݿ⣬�����ݿ�֪������Ҫ��ʲô
            int a = ps.executeUpdate();//����������ڸı����ݿ����ݣ�a����ı����ݿ������
            if (a > 0) {
                JLabel label = new JLabel("���ĳɹ���");
                label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(null, label);
            }
        } catch (Exception a) {
            JLabel label = new JLabel("����ʧ�ܣ����������ʽ�Ƿ���ȷ����ѧ���ظ���");
            label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e2) {
        }
    }

    public Update_Student_Information(String name) {
        init();
        // �ĸ����
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel(new BorderLayout());
        JPanel jPanel4 = new JPanel(new BorderLayout());
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel();
        JPanel jPanel7 = new JPanel();
        JPanel jPanel8 = new JPanel();
        // ��ͷ
        Vector<Object> titleList = new Vector<>();
        titleList.add("ѧ��");
        titleList.add("����");
        titleList.add("�Ա�");
        titleList.add("��������");
        titleList.add("רҵ");
        titleList.add("�༶");
        titleList.add("��ѧ���");
        titleList.add("�߿��ܷ�");
        // �������
        Vector<Vector<Object>> contextList = new Vector<>();
        Vector<Object> vector1 = new Vector<>();
        contextList.add(vector1);
        DefaultTableModel defaultTableModel = new DefaultTableModel(contextList, titleList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // ����һ�����ָ�� ��ͷ �� ����������
        JTable table = new JTable(defaultTableModel);
        // ����ֻ��ѡ����
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ���ñ��������ɫ
        table.setForeground(Color.BLACK);                   // ������ɫ
        table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
        table.setGridColor(Color.GRAY);                     // ������ɫ
        // ���ñ�ͷ
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������
        // �����и�
        table.setRowHeight(30);
        // ���ù�������ӿڴ�С�������ô�С�������ݣ���Ҫ�϶����������ܿ�����
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        // �� ��� �ŵ� ������� �У���ͷ���Զ���ӵ�������嶥����
        JScrollPane scrollPane = new JScrollPane(table);

        JButton jButton1 = new JButton("����");
        JButton jButton2 = new JButton("��ѯ");
        JButton jButton3 = new JButton("��ղ�ѯ");
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton3.setContentAreaFilled(false);
        jButton1.setFont(new Font("����", Font.PLAIN, 20));
        jButton2.setFont(new Font("����", Font.PLAIN, 20));
        jButton3.setFont(new Font("����", Font.PLAIN, 20));
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));
        jButton3.setPreferredSize(new Dimension(140, 40));

        JLabel jLabel1 = new JLabel("������");
        JLabel jLabel2 = new JLabel("רҵ��");
        JLabel jLabel3 = new JLabel("�༶��");
        JLabel jLabel4 = new JLabel("(�س�ȷ���޸�)", JLabel.CENTER);
        jLabel1.setFont(new Font("����", Font.PLAIN, 22));
        jLabel2.setFont(new Font("����", Font.PLAIN, 22));
        jLabel3.setFont(new Font("����", Font.PLAIN, 22));
        jLabel4.setFont(new Font("����", Font.PLAIN, 18));
        JTextField jTextField1 = new JTextField();
        JTextField jTextField3 = new JTextField();
        jTextField1.setPreferredSize(new Dimension(100, 35));
        jTextField3.setPreferredSize(new Dimension(100, 35));

        String[] major = {"", "�����רҵ", "�������", "���ѧ", "����ѧ", "����ѧ", "�������"};
        JComboBox<Object> jComboBox = new JComboBox<>(major);
        jComboBox.setPreferredSize(new Dimension(100, 35));
        jComboBox.setFont(new Font("����", Font.PLAIN, 15));

        // ���ص��¼�����
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Administrators(name);
            }
        });
        // ��ѯ���¼�����
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> content;
                if (jTextField1.getText().equals("") && jTextField3.getText().equals("") && jComboBox.getSelectedItem() == ""){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh");
                }else if (jComboBox.getSelectedItem()=="" && jTextField1.getText().equals("")){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.class_name = '" + jTextField3.getText() + "' INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh");
                }else if(jComboBox.getSelectedItem()=="" && jTextField3.getText().equals("")){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh AND student.xm='"+ jTextField1.getText() +"'");
                }else if(jTextField1.getText().equals("") && jTextField3.getText().equals("")){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh AND major_name='"+jComboBox.getSelectedItem()+"'");
                }else if (jTextField1.getText().equals("")){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh AND major_name='"+jComboBox.getSelectedItem()+"' AND class.class_name='"+jTextField3.getText()+"'");
                }else if (jTextField3.getText().equals("")){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh AND major_name='"+jComboBox.getSelectedItem()+"' AND student.xm='"+jTextField1.getText()+"'");
                }else if (jComboBox.getSelectedItem()==""){
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh AND class_name='"+jTextField3.getText()+"' AND student.xm='"+jTextField1.getText()+"'");
                }else {
                    content = look_student_information("SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh AND class_name='"+jTextField3.getText()+"' AND student.xm='"+jTextField1.getText()+"' AND major_name='"+jComboBox.getSelectedItem()+"'");
                }
                DefaultTableModel defaultTableModel = new DefaultTableModel(content, titleList){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if(column==0){
                            return false;
                        }else {
                            return true;
                        }
                    }
                };
                // ���ñ����¼�����
                defaultTableModel.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        Map<String, Integer> map = new HashMap<String, Integer>();
                        map.put("�����רҵ",1001);
                        map.put("�������",1002);
                        map.put("���ѧ",1003);
                        map.put("����ѧ",1004);
                        map.put("����ѧ",1005);
                        map.put("�������",1006);
                        System.out.println(table.getValueAt(e.getFirstRow(),e.getColumn()));
                        switch (e.getColumn()) {
                            case 1:
                                update_student("update student set xm='"+table.getValueAt(e.getFirstRow(),e.getColumn())+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                            case 2:
                                update_student("update student set xb='"+table.getValueAt(e.getFirstRow(),e.getColumn())+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                            case 3:
                                update_student("update student set csrq='"+table.getValueAt(e.getFirstRow(),e.getColumn())+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                            case 4:
                                String zy=table.getValueAt(e.getFirstRow(),e.getColumn()).toString();
                                update_student("update student set zy='"+map.get(zy)+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                            case 5:
                                String zy_1=table.getValueAt(e.getFirstRow(),4).toString();
                                int bj=find_class("SELECT class.id FROM class INNER JOIN major ON class.bh = major.bh AND major.bh="+map.get(zy_1)+" AND class.class_name='"+table.getValueAt(e.getFirstRow(),e.getColumn())+"'");
                                update_student("update student set bj='"+bj+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                            case 6:
                                update_student("update student set rxnd='"+table.getValueAt(e.getFirstRow(),e.getColumn())+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                            case 7:
                                update_student("update student set gkzf='"+table.getValueAt(e.getFirstRow(),e.getColumn())+"' where xh='"+table.getValueAt(e.getFirstRow(),0)+"'");
                                break;
                        }
                    }
                });
                table.setModel(defaultTableModel);
            }
        });
        // ��ղ�ѯ��ʱ�����
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(defaultTableModel);
            }
        });

        jFrame.setLayout(new GridLayout(2, 1));
        jPanel1.setLayout(new BorderLayout());
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
        jPanel8.setLayout(new GridLayout(3, 1, 0, 0));

        jPanel1.add(scrollPane);
        jPanel2.add(jButton2);
        jPanel2.add(jButton3);
        jPanel2.add(jButton1);

        jPanel5.add(jLabel1);
        jPanel5.add(jTextField1);
        jPanel6.add(jLabel2);
        jPanel6.add(jComboBox);
        jPanel7.add(jLabel3);
        jPanel7.add(jTextField3);

        jPanel4.add(jPanel5);
        jPanel4.add(jPanel6);
        jPanel4.add(jPanel7);

        jPanel8.add(jPanel4);
        jPanel8.add(jPanel2);
        jPanel8.add(jLabel4);

        jFrame.add(jPanel1);
        jFrame.add(jPanel8);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Update_Student_Information("admin");
    }
}
