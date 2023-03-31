package Student_information_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ѧ����Ϣ���ӻ����
public class Student_Plot {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Statistics");
        jFrame.setSize(1000, 800);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public Student_Plot(String name) {
        init();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JRadioButton jRadioButton1 = new JRadioButton("����ͼ");
        JRadioButton jRadioButton2 = new JRadioButton("��״ͼ");
        jRadioButton1.setFont(new Font("΢���ź�",Font.PLAIN,16));
        jRadioButton2.setFont(new Font("΢���ź�",Font.PLAIN,16));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        JScrollPane jScrollPane = new JScrollPane();

        // ����ͼ��ť���¼�����
        jRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel jLabel1 = new JLabel("",JLabel.CENTER);
                JLabel jLabel2 = new JLabel("",JLabel.CENTER);
                JLabel jLabel3 = new JLabel("",JLabel.CENTER);
                JLabel jLabel4 = new JLabel("",JLabel.CENTER);
                JLabel jLabel5 = new JLabel("",JLabel.CENTER);
                ImageIcon img1= new ImageIcon("src\\Student_information_management\\plot\\pie_bj.png");//����ͼƬ����
                ImageIcon img2 = new ImageIcon("src\\Student_information_management\\plot\\pie_xb.png");//����ͼƬ����
                ImageIcon img3 = new ImageIcon("src\\Student_information_management\\plot\\pie_zy.png");//����ͼƬ����
                ImageIcon img4 = new ImageIcon("src\\Student_information_management\\plot\\pie_rxnd.png");//����ͼƬ����
                ImageIcon img5 = new ImageIcon("src\\Student_information_management\\plot\\pie_csrq.png");//����ͼƬ����
                img1.setImage(img1.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img2.setImage(img2.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img3.setImage(img3.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img4.setImage(img4.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img5.setImage(img5.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                jLabel1.setIcon(img1);
                jLabel2.setIcon(img2);
                jLabel3.setIcon(img3);
                jLabel4.setIcon(img4);
                jLabel5.setIcon(img5);

                jPanel2.removeAll();
                jPanel2.add(jLabel1);
                jPanel2.add(jLabel2);
                jPanel2.add(jLabel3);
                jPanel2.add(jLabel4);
                jPanel2.add(jLabel5);

                jScrollPane.add(jPanel2);
                jScrollPane.setViewportView(jPanel2);
                jScrollPane.repaint();
            }
        });
        // ��״ͼ��ť���¼�����
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel jLabel1 = new JLabel("",JLabel.CENTER);
                JLabel jLabel2 = new JLabel("",JLabel.CENTER);
                JLabel jLabel3 = new JLabel("",JLabel.CENTER);
                JLabel jLabel4 = new JLabel("",JLabel.CENTER);
                JLabel jLabel5 = new JLabel("",JLabel.CENTER);
                ImageIcon img1= new ImageIcon("src\\Student_information_management\\plot\\bar_bj.png");//����ͼƬ����
                ImageIcon img2 = new ImageIcon("src\\Student_information_management\\plot\\bar_xb.png");//����ͼƬ����
                ImageIcon img3 = new ImageIcon("src\\Student_information_management\\plot\\bar_zy.png");//����ͼƬ����
                ImageIcon img4 = new ImageIcon("src\\Student_information_management\\plot\\bar_csrq.png");//����ͼƬ����
                ImageIcon img5 = new ImageIcon("src\\Student_information_management\\plot\\bar_rxnd.png");//����ͼƬ����
                img1.setImage(img1.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img2.setImage(img2.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img3.setImage(img3.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img4.setImage(img4.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                img5.setImage(img5.getImage().getScaledInstance(800,640,Image.SCALE_DEFAULT));
                jLabel1.setIcon(img1);
                jLabel2.setIcon(img2);
                jLabel3.setIcon(img3);
                jLabel4.setIcon(img4);
                jLabel5.setIcon(img5);

                jPanel2.removeAll();
                jPanel2.add(jLabel1);
                jPanel2.add(jLabel2);
                jPanel2.add(jLabel3);
                jPanel2.add(jLabel4);
                jPanel2.add(jLabel5);

                jScrollPane.add(jPanel2);
                jScrollPane.setViewportView(jPanel2);
                jScrollPane.repaint();
            }
        });
        // ���ô��ڹرպ���¼�����
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Administrators(name);
            }
        });

        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
        jPanel2.setLayout(new GridLayout(5,1,0,40));
        jPanel1.add(jRadioButton1);
        jPanel1.add(jRadioButton2);
        jPanel1.add(new JLabel("������ͳ��Ŀǰ���ڿ����У�"));

        jFrame.add(jPanel1, BorderLayout.NORTH);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Student_Plot("admin");
    }
}
