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


// 全部学生信息面板
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
        // 两个面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
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
        Connection connection=Mysql.BaseConnection();
        Vector<Vector<Object>> contextList = new Vector<>();
        //结果集对象
        Statement statement=null;
        ResultSet rs=null;//查询出来的数据先放到rs中
        try{
            String sql="SELECT student.xh,student.xm,student.xb,major.major_name AS zy,class.class_name AS bj,student.csrq,student.rxnd,student.gkzf FROM student INNER JOIN class ON class.id = student.bj INNER JOIN major ON major.bh = class.bh AND student.zy = major.bh";
            statement = connection.createStatement();
            rs=statement.executeQuery(sql);//执行数据库查询的方法，放到rs中
            while(rs.next()){//rs对象相当于一个指针，指向数据库的一横行数据
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
            JLabel label = new JLabel("数据库连接失败，请确认后重试！");
            label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            JOptionPane.showMessageDialog(null, label, "Error!", JOptionPane.ERROR_MESSAGE);
        }finally{//重点下面代码必须写，当数据库使用后必须关闭，如果没有关闭数据库的接口有限，下次就不能连接
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
        // 创建一个表格，指定表头和所有行数据
        JTable table = new JTable(defaultTableModel);
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
        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);

        JButton jButton1 = new JButton("返回");
        JButton jButton2 = new JButton("导出");
        jButton1.setContentAreaFilled(false);
        jButton2.setContentAreaFilled(false);
        jButton1.setFont(new Font("宋体",Font.PLAIN,20));
        jButton2.setFont(new Font("宋体",Font.PLAIN,20));
        jButton1.setPreferredSize(new Dimension(120,60));
        jButton2.setPreferredSize(new Dimension(120,60));

        // 导出的事件监听
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("请输入完整路径(包括文件名及格式):");
                label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
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
                        JLabel label2 = new JLabel("导出成功，请到对应目录查看！");
                        label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                        JOptionPane.showMessageDialog(null, label2);
                    } catch (IOException c) {
                        JLabel label1 = new JLabel("导出失败，请检查输入格式是否正确！");
                        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                        JOptionPane.showMessageDialog(null, label1, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // 返回的事件监听
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
