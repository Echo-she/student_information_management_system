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

// 添加学生信息面板
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
            ps= connection.prepareStatement(sql);//把写好的sql语句传递到数据库，让数据库知道我们要干什么
            int a=ps.executeUpdate();//这个方法用于改变数据库数据，a代表改变数据库的条数
            if(a>0){
                JLabel label = new JLabel("添加成功！");
                label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(null, label);
            }
        }catch(Exception a){
            JLabel label = new JLabel("添加失败，请检查输入格式是否正确或者学号重复！");
            label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
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
        ResultSet rs = null;//查询出来的数据先放到rs中
        int id=0;
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);//执行数据库查询的方法，放到rs中
            while (rs.next()) {//rs对象相当于一个指针，指向数据库的一横行数据
                id=rs.getInt("id");
            }
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
            }
        }
        return id;
    }

    public Add_Student_Information(String name) {
        init();
        // 九个面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel();
        JPanel jPanel7 = new JPanel();
        JPanel jPanel8 = new JPanel();
        JPanel jPanel9 = new JPanel();

        JButton jButton1 = new JButton("返回");
        JButton jButton2 = new JButton("添加");
        jButton1.setFont(new Font("宋体", Font.PLAIN, 16));
        jButton2.setFont(new Font("宋体", Font.PLAIN, 16));
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));

        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel1 = new JLabel("学号：");
        jLabel1.setFont(new Font("宋体",Font.PLAIN,20));
        JTextField jTextField1 = new JTextField();
        jTextField1.setPreferredSize(new Dimension(280,40));
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);

        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel2 = new JLabel("性别：");
        jLabel2.setFont(new Font("宋体",Font.PLAIN,20));
        JRadioButton jRadioButton1 = new JRadioButton("男");
        JRadioButton jRadioButton2 = new JRadioButton("女");
        jRadioButton1.setFont(new Font("宋体", Font.PLAIN, 20));
        jRadioButton2.setFont(new Font("宋体", Font.PLAIN, 20));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        jPanel2.add(jLabel2);
        jPanel2.add(jRadioButton1);
        jPanel2.add(jRadioButton2);

        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel3 = new JLabel("出生日期：");
        jLabel3.setFont(new Font("宋体",Font.PLAIN,20));
        JTextField jTextField3 = new JTextField();
        jTextField3.setPreferredSize(new Dimension(240,40));
        jTextField3.setToolTipText("格式为：1999-12-31");
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        jPanel3.add(jLabel3);
        jPanel3.add(jTextField3);

        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel4 = new JLabel("专业：");
        jLabel4.setFont(new Font("宋体",Font.PLAIN,20));
        String[] major = {"计算机专业", "软件工程", "会计学", "金融学", "财政学", "工程造价"};
        JComboBox<Object> jComboBox = new JComboBox<>(major);
        jComboBox.setPreferredSize(new Dimension(200,40));
        jComboBox.setFont(new Font("宋体",Font.PLAIN,20));
        jPanel4.add(jLabel4);
        jPanel4.add(jComboBox);

        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel5 = new JLabel("班级：");
        jLabel5.setFont(new Font("宋体",Font.PLAIN,20));
        JTextField jTextField5 = new JTextField();
        jTextField5.setPreferredSize(new Dimension(280,40));
        jPanel5.add(jLabel5);
        jPanel5.add(jTextField5);

        jPanel6.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel6 = new JLabel("入学年度：");
        jLabel6.setFont(new Font("宋体",Font.PLAIN,20));
        JTextField jTextField6 = new JTextField();
        jTextField6.setPreferredSize(new Dimension(240,40));
        jTextField6.setToolTipText("格式为：1999-12-31");
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        jPanel6.add(jLabel6);
        jPanel6.add(jTextField6);

        jPanel7.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel7 = new JLabel("高考总分：");
        jLabel7.setFont(new Font("宋体",Font.PLAIN,20));
        JTextField jTextField7 = new JTextField();
        jTextField7.setPreferredSize(new Dimension(240,40));
        jPanel7.add(jLabel7);
        jPanel7.add(jTextField7);

        jPanel8.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel8 = new JLabel("姓名：");
        jLabel8.setFont(new Font("宋体",Font.PLAIN,20));
        JTextField jTextField8 = new JTextField();
        jTextField8.setPreferredSize(new Dimension(280,40));
        jPanel8.add(jLabel8);
        jPanel8.add(jTextField8);

        // 返回的事件监听
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Administrators(name);
            }
        });
        // 添加的事件监听
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
                map.put("计算机专业",1001);
                map.put("软件工程",1002);
                map.put("会计学",1003);
                map.put("金融学",1004);
                map.put("财政学",1005);
                map.put("工程造价",1006);
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
