package Student_information_management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

// 删除学生信息面板
public class Delete_Student_Information {
    final JFrame jFrame = new JFrame();

    public void init() {
        jFrame.setTitle("Delete Student Information");
        jFrame.setSize(700, 630);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Vector<Vector<Object>> look_student_information(String sql) {
        Vector<Vector<Object>> content = new Vector<>();
        Connection connection = Mysql.BaseConnection();
        Statement statement = null;
        ResultSet rs = null;//查询出来的数据先放到rs中
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);//执行数据库查询的方法，放到rs中
            while (rs.next()) {//rs对象相当于一个指针，指向数据库的一横行数据
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
            JLabel label = new JLabel("查询信息失败，请确认后重试！");
            label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
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
        return content;
    }

    public void delete_student(String sql) {
        Connection connection = Mysql.BaseConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);//把写好的sql语句传递到数据库，让数据库知道我们要干什么
            int a = ps.executeUpdate();//这个方法用于改变数据库数据，a代表改变数据库的条数
            if (a > 0) {
                JLabel label = new JLabel("删除成功！");
                label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                JOptionPane.showMessageDialog(null, label);
            }
        } catch (Exception a) {
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

    public Delete_Student_Information(String name) {
        init();
        // 四个面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel(new BorderLayout());
        JPanel jPanel4 = new JPanel(new BorderLayout());
        JPanel jPanel5 = new JPanel();
        JPanel jPanel6 = new JPanel();
        JPanel jPanel7 = new JPanel();
        JPanel jPanel8 = new JPanel();
        // 表头
        Vector<Object> titleList = new Vector<>();
        titleList.add("学号");
        titleList.add("姓名");
        titleList.add("性别");
        titleList.add("出生日期");
        titleList.add("专业");
        titleList.add("班级");
        titleList.add("入学年度");
        titleList.add("高考总分");
        // 表格内容
        Vector<Vector<Object>> contextList = new Vector<>();
        Vector<Object> vector1 = new Vector<>();
        contextList.add(vector1);
        DefaultTableModel defaultTableModel = new DefaultTableModel(contextList, titleList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(defaultTableModel);
        // 设置只能选择单行
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色
        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        // 设置行高
        table.setRowHeight(30);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);

        JButton jButton1 = new JButton("返回");
        JButton jButton2 = new JButton("查询");
        JButton jButton3 = new JButton("清空查询");
        JButton jButton4 = new JButton("删除");
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton3.setContentAreaFilled(false);
        jButton4.setContentAreaFilled(false);
        jButton1.setFont(new Font("宋体", Font.PLAIN, 20));
        jButton2.setFont(new Font("宋体", Font.PLAIN, 20));
        jButton3.setFont(new Font("宋体", Font.PLAIN, 20));
        jButton4.setFont(new Font("宋体", Font.PLAIN, 20));
        jButton1.setPreferredSize(new Dimension(80, 40));
        jButton2.setPreferredSize(new Dimension(80, 40));
        jButton3.setPreferredSize(new Dimension(140, 40));
        jButton4.setPreferredSize(new Dimension(80, 40));

        JLabel jLabel1 = new JLabel("姓名：");
        JLabel jLabel2 = new JLabel("专业：");
        JLabel jLabel3 = new JLabel("班级：");
        jLabel1.setFont(new Font("宋体", Font.PLAIN, 22));
        jLabel2.setFont(new Font("宋体", Font.PLAIN, 22));
        jLabel3.setFont(new Font("宋体", Font.PLAIN, 22));
        JTextField jTextField1 = new JTextField();
        JTextField jTextField3 = new JTextField();
        jTextField1.setPreferredSize(new Dimension(100, 35));
        jTextField3.setPreferredSize(new Dimension(100, 35));

        String[] major = {"", "计算机专业", "软件工程", "会计学", "金融学", "财政学", "工程造价"};
        JComboBox<Object> jComboBox = new JComboBox<>(major);
        jComboBox.setPreferredSize(new Dimension(100, 35));
        jComboBox.setFont(new Font("宋体", Font.PLAIN, 15));
        // 返回的事件监听
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Administrators(name);
            }
        });
        // 查询的事件监听
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
                DefaultTableModel defaultTableModel = new DefaultTableModel(content, titleList) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setModel(defaultTableModel);
            }
        });
        // 清空查询的事件监听
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel defaultTableModel = new DefaultTableModel(contextList, titleList) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setModel(defaultTableModel);
            }
        });
        // 删除的事件监听
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete_student("delete from student where xh="+table.getValueAt(table.getSelectedRow(),0));
            }
        });

        jFrame.setLayout(new GridLayout(2, 1));
        jPanel1.setLayout(new BorderLayout());
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 80));
        jPanel8.setLayout(new GridLayout(2, 1, 0, 40));

        jPanel1.add(scrollPane);
        jPanel2.add(jButton2);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
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

        jFrame.add(jPanel1);
        jFrame.add(jPanel8);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Delete_Student_Information("admin");
    }
}
