package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

// �������
public class Administrators {
    final JFrame jFrame = new JFrame();
    final JLabel jLabel2;

    public void init() {
        jFrame.setTitle("Home");
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    protected class JLabelTimerTask extends TimerTask{
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        @Override
        public void run() {
            String time = dateFormatter.format(Calendar.getInstance().getTime());
            jLabel2.setText(time);
        }
    }

    public boolean start_py() throws IOException, InterruptedException {
        boolean bool;
        Runtime rn = Runtime.getRuntime();
        try {
            rn.exec("src\\Student_information_management\\py\\StudentInformation_plot.exe");
            bool=true;
        } catch (Exception e) {
            bool=false;
        }
        return bool;
    }

    public Administrators(String name) {
        init();
        JPanel jPanel = new JPanel();

        JLabel jLabel1 = new JLabel("��ǰʱ��Ϊ:");
        jLabel2 = new JLabel();
        JLabel jLabel = new JLabel("��ӭ" + name, JLabel.CENTER);
        jLabel.setFont(new Font("����", Font.BOLD, 56));
        jLabel1.setFont(new Font("����", Font.PLAIN, 22));
        jLabel2.setFont(new Font("����", Font.PLAIN, 22));
        java.util.Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(),new Date(), 1000);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu("�˵�");
        JMenu jMenu2 = new JMenu("����");
        JMenu jMenu3 = new JMenu("����");
        JMenu jMenu4 = new JMenu("�༭");
        JMenu jMenu5 = new JMenu("ͳ��");

        JMenuItem jMenuItem1 = new JMenuItem("���ѧ����Ϣ");
        JMenuItem jMenuItem2 = new JMenuItem("��ѯѧ����Ϣ");
        JMenuItem jMenuItem3 = new JMenuItem("�˳�");
        JMenuItem jMenuItem4 = new JMenuItem("��������");
        JMenuItem jMenuItem5 = new JMenuItem("�������ݿ�����");
        JMenuItem jMenuItem6 = new JMenuItem("����ѧ����Ϣ");
        JMenuItem jMenuItem7 = new JMenuItem("ɾ��ѧ����Ϣ");
        JMenuItem jMenuItem8 = new JMenuItem("�޸�����");
        JMenuItem jMenuItem9 = new JMenuItem("ȫ��ѧ����Ϣ");
        JMenuItem jMenuItem10 = new JMenuItem("���ݿ��ӻ�");
        // �޸�������¼�����
        jMenuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Change_Password(name);
            }
        });
        // ���ѧ����Ϣ���¼�����
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Add_Student_Information(name);
            }
        });
        // ��ѯѧ����Ϣ���¼�����
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Look_Student_Information(name);
            }
        });
        // ����ѧ����Ϣ���¼�����
        jMenuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Update_Student_Information(name);
            }
        });
        // ɾ��ѧ����Ϣ���¼�����
        jMenuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Delete_Student_Information(name);
            }
        });
        // �������ݿ����ӵ��¼�����
        jMenuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection=Mysql.BaseConnection();
                if (connection==null){
                    JLabel label = new JLabel("���ݿ�����ʧ�ܣ���������ԣ�");
                    label.setFont(new Font("΢���ź�",Font.PLAIN,16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                }else {
                    JLabel label = new JLabel("���ݿ����ӳɹ���");
                    label.setFont(new Font("΢���ź�",Font.PLAIN,16));
                    JOptionPane.showMessageDialog(null, label, "Nice!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        // �������ǵ��¼�����
        jMenuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("����xxx�����ĳ��������ϲ����֧�����ǣ����ǽ��������ྫ����Ʒ��");
                label.setFont(new Font("΢���ź�",Font.PLAIN,16));
                JOptionPane.showMessageDialog(null, label, "��������", JOptionPane.PLAIN_MESSAGE);
            }
        });
        // �˳����������¼�����
        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Login();
            }
        });
        // ȫ��ѧ����Ϣ���¼�����
        jMenuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new All_Student_Information(name);
            }
        });
        // ���ݿ��ӻ����¼�����
        jMenuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (start_py()){
                        jFrame.dispose();
                        new Student_Plot(name);
                    }else {
                        JLabel label = new JLabel("ͳ��ʧ�ܣ�����������ú����ԣ�");
                        label.setFont(new Font("΢���ź�",Font.PLAIN,16));
                        JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                }
            }
        });

        jMenu4.add(jMenuItem1);
        jMenu4.add(jMenuItem7);
        jMenu4.add(jMenuItem6);
        jMenu4.add(jMenuItem2);
        jMenu4.addSeparator();
        jMenu4.add(jMenuItem9);

        jMenu1.add(jMenuItem8);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem3);

        jMenu2.add(jMenuItem4);
        jMenu3.add(jMenuItem5);

        jMenu5.add(jMenuItem10);

        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu4);
        jMenuBar.add(jMenu5);
        jMenuBar.add(jMenu3);
        jMenuBar.add(jMenu2);

        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(jLabel);
        jFrame.add(jPanel,BorderLayout.SOUTH);

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Administrators("admin");
    }
}
