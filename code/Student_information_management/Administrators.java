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

// 管理面板
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

        JLabel jLabel1 = new JLabel("当前时间为:");
        jLabel2 = new JLabel();
        JLabel jLabel = new JLabel("欢迎" + name, JLabel.CENTER);
        jLabel.setFont(new Font("宋体", Font.BOLD, 56));
        jLabel1.setFont(new Font("宋体", Font.PLAIN, 22));
        jLabel2.setFont(new Font("宋体", Font.PLAIN, 22));
        java.util.Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(),new Date(), 1000);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu("菜单");
        JMenu jMenu2 = new JMenu("关于");
        JMenu jMenu3 = new JMenu("测试");
        JMenu jMenu4 = new JMenu("编辑");
        JMenu jMenu5 = new JMenu("统计");

        JMenuItem jMenuItem1 = new JMenuItem("添加学生信息");
        JMenuItem jMenuItem2 = new JMenuItem("查询学生信息");
        JMenuItem jMenuItem3 = new JMenuItem("退出");
        JMenuItem jMenuItem4 = new JMenuItem("关于我们");
        JMenuItem jMenuItem5 = new JMenuItem("测试数据库连接");
        JMenuItem jMenuItem6 = new JMenuItem("更改学生信息");
        JMenuItem jMenuItem7 = new JMenuItem("删除学生信息");
        JMenuItem jMenuItem8 = new JMenuItem("修改密码");
        JMenuItem jMenuItem9 = new JMenuItem("全部学生信息");
        JMenuItem jMenuItem10 = new JMenuItem("数据可视化");
        // 修改密码的事件监听
        jMenuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Change_Password(name);
            }
        });
        // 添加学生信息的事件监听
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Add_Student_Information(name);
            }
        });
        // 查询学生信息的事件监听
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Look_Student_Information(name);
            }
        });
        // 更改学生信息的事件监听
        jMenuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Update_Student_Information(name);
            }
        });
        // 删除学生信息的事件监听
        jMenuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Delete_Student_Information(name);
            }
        });
        // 测试数据库连接的事件监听
        jMenuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection=Mysql.BaseConnection();
                if (connection==null){
                    JLabel label = new JLabel("数据库连接失败，请检查后重试！");
                    label.setFont(new Font("微软雅黑",Font.PLAIN,16));
                    JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
                }else {
                    JLabel label = new JLabel("数据库连接成功！");
                    label.setFont(new Font("微软雅黑",Font.PLAIN,16));
                    JOptionPane.showMessageDialog(null, label, "Nice!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        // 关于我们的事件监听
        jMenuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("这是xxx创作的程序，如果你喜欢请支持我们，我们将带来更多精彩作品！");
                label.setFont(new Font("微软雅黑",Font.PLAIN,16));
                JOptionPane.showMessageDialog(null, label, "关于我们", JOptionPane.PLAIN_MESSAGE);
            }
        });
        // 退出管理界面的事件监听
        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Login();
            }
        });
        // 全部学生信息的事件监听
        jMenuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new All_Student_Information(name);
            }
        });
        // 数据可视化的事件监听
        jMenuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (start_py()){
                        jFrame.dispose();
                        new Student_Plot(name);
                    }else {
                        JLabel label = new JLabel("统计失败，请检查相关配置后重试！");
                        label.setFont(new Font("微软雅黑",Font.PLAIN,16));
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
