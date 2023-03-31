package Student_information_management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.io.FileWriter;


// ȫ��ѧ����Ϣ���
public class All_Student_Information {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("All Student Information");
        jFrame.setSize(1000, 760);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public All_Student_Information(String name) {
        init();
        // �������
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
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
        Connection connection=Mysql.BaseConnection();
        Vector<Vector<Object>> contextList = new Vector<>();
        //���������
        Statement statement=null;
        ResultSet rs=null;//��ѯ�����������ȷŵ�rs��
        try{
            String sql="SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh";
            statement = connection.createStatement();
            rs=statement.executeQuery(sql);//ִ�����ݿ��ѯ�ķ������ŵ�rs��
            while(rs.next()){//rs�����൱��һ��ָ�룬ָ�����ݿ��һ��������
                Vector<Object> vector1 = new Vector<>();
                vector1.add(rs.getObject("xh"));
                vector1.add(rs.getObject("xm"));
                vector1.add(rs.getObject("xb"));
                vector1.add(rs.getObject("csrq"));
                vector1.add(rs.getObject("zy"));
                vector1.add(rs.getObject("bj"));
                vector1.add(rs.getObject("rxnd"));
                vector1.add(rs.getObject("gkzf"));
                contextList.add(vector1);
            }
        }catch(Exception a){
            JLabel label = new JLabel("���ݿ�����ʧ�ܣ���ȷ�Ϻ����ԣ�");
            label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
        }finally{//�ص�����������д�������ݿ�ʹ�ú����رգ����û�йر����ݿ�Ľӿ����ޣ��´ξͲ�������
            try{
                if(rs!=null){
                    rs.close();
                }if(statement!=null){
                    statement.close();
                }if(connection!=null){
                    connection.close();
                }
            }catch(Exception e2){
            }
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(contextList,titleList){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // ����һ�����ָ����ͷ������������
        JTable table = new JTable(defaultTableModel);
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
        // �� ��� �ŵ� ������� �У���ͷ���Զ���ӵ�������嶥����
        JScrollPane scrollPane = new JScrollPane(table);

        JButton jButton1 = new JButton("����");
        JButton jButton2 = new JButton("����");
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setFont(new Font("����",Font.PLAIN,20));
        jButton2.setFont(new Font("����",Font.PLAIN,20));
        jButton1.setPreferredSize(new Dimension(120,60));
        jButton2.setPreferredSize(new Dimension(120,60));

        // �������¼�����
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("����������·��(�����ļ�������ʽ):");
                label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                String filename=JOptionPane.showInputDialog(null,label,"Input",JOptionPane.PLAIN_MESSAGE);
                if (filename!=null){
                    try {
                        File file = new File(filename);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (int i = 0; i < titleList.size(); i++) {
                            String str=String.valueOf(titleList.get(i)).replace("[","").replace("]","").replace(","," ");
                            bw.write(str+" ");
                        }
                        bw.write("\n");
                        for (int i = 0; i < contextList.size(); i++) {
                            String str=String.valueOf(contextList.get(i)).replace("[","").replace("]","").replace(","," ");
                            bw.write(str+"\n");
                        }
                        bw.close();
                        JLabel label2 = new JLabel("�����ɹ����뵽��ӦĿ¼�鿴��");
                        label2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                        JOptionPane.showMessageDialog(null, label2);
                    } catch (IOException c) {
                        JLabel label1 = new JLabel("����ʧ�ܣ����������ʽ�Ƿ���ȷ��");
                        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                        JOptionPane.showMessageDialog(null, label1, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // ���ص��¼�����
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Administrators(name);
            }
        });

        jPanel1.setLayout(new BorderLayout());
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));

        jPanel1.add(scrollPane);
        jPanel2.add(jButton2);
        jPanel2.add(jButton1);

        jFrame.add(jPanel1);
        jFrame.add(jPanel2,BorderLayout.SOUTH);

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new All_Student_Information("admin");
    }
}
