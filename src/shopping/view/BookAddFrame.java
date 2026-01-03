package shopping.view;

import shopping.db.DataBase;
import shopping.entity.Book;
import shopping.entity.Category;
import shopping.service.BookServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookAddFrame extends JFrame {
    private JLabel lbl_id,lbl_name,lbl_author,lbl_price,lbl_number,lbl_firstLevel,lbl_secondLevel;
    private JTextField txt_id,txt_name,txt_author,txt_price,txt_number;
    private JComboBox<String> combo_firstLevel,combo_secondLevel;
    private JButton btn_reset,btn_add;
    public BookAddFrame() {
        setTitle("电商购物平台——添加书籍");
        setSize(400,500);
        setLocationRelativeTo(null);//窗体在屏幕中间显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点击X能退出
        init();
        setResizable(true);
        setVisible(true);
    }
    public void init(){
        setLayout(new GridLayout(8,2,5,5));
        //文本框
        lbl_id = new JLabel("编号",JLabel.CENTER);
        lbl_name = new JLabel("书名",JLabel.CENTER);
        lbl_author = new JLabel("作者",JLabel.CENTER);
        lbl_price = new JLabel("价格",JLabel.CENTER);
        lbl_number = new JLabel("库存",JLabel.CENTER);
        lbl_firstLevel = new JLabel("一级目录",JLabel.CENTER);
        lbl_secondLevel = new JLabel("二级目录",JLabel.CENTER);

        //输入框
        txt_id = new JTextField();
        txt_name = new JTextField();
        txt_author = new JTextField();
        txt_price = new JTextField();
        txt_number = new JTextField();

//        combo_firstLevel = new JComboBox<String>();
//        combo_firstLevel.addItem("工具类");
//        combo_firstLevel.addItem("小说类");
//        combo_firstLevel.addItem("英语类");
//        combo_firstLevel.addItem("科普类");
//        combo_secondLevel = new JComboBox<String>();
//        combo_secondLevel.addItem("软件编程");
//        combo_secondLevel.addItem("计算机原理类");
//        combo_secondLevel.addItem("历史类");
//        combo_secondLevel.addItem("科幻类");
//        combo_secondLevel.addItem("语法类");
//        combo_secondLevel.addItem("听力类");
//        combo_secondLevel.addItem("写作类");
//        combo_secondLevel.addItem("物理类");
//        combo_secondLevel.addItem("化学类");
//        combo_secondLevel.addItem("生物类");
        //下拉框
        combo_firstLevel = new JComboBox<>(new String[]{"工具类", "小说类", "英语类", "科普类"});
        combo_secondLevel = new JComboBox<>();

       //监听第一个下拉框变化
        combo_firstLevel.addActionListener(e -> {
            combo_secondLevel.removeAllItems(); // 清空第二个框
            // 根据选择动态添加选项
            switch ((String) combo_firstLevel.getSelectedItem()) {
                case "工具类":
                    combo_secondLevel.addItem("软件编程");
                    combo_secondLevel.addItem("计算机原理类");
                    break;
                case "小说类":
                    combo_secondLevel.addItem("历史类");
                    combo_secondLevel.addItem("科幻类");
                    break;
                case "英语类":
                    combo_secondLevel.addItem("语法类");
                    combo_secondLevel.addItem("听力类");
                    combo_secondLevel.addItem("写作类");
                    break;
                case "科普类":
                    combo_secondLevel.addItem("物理类");
                    combo_secondLevel.addItem("化学类");
                    combo_secondLevel.addItem("生物类");
                    break;
            }
        });
        //默认触发一次（初始化二级选项）
        combo_firstLevel.setSelectedIndex(0);

        //按钮
        btn_add = new JButton("添加");
        btn_reset = new JButton("重置");

        //把组件添加到页面内
        add(lbl_id);
        add(txt_id);
        add(lbl_name);
        add(txt_name);
        add(lbl_author);
        add(txt_author);
        add(lbl_price);
        add(txt_price);
        add(lbl_number);
        add(txt_number);
        add(lbl_firstLevel);
        add(combo_firstLevel);
        add(lbl_secondLevel);
        add(combo_secondLevel);
        add(btn_reset);
        add(btn_add);

        btn_add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText().trim();
                String name = txt_name.getText().trim();
                String author = txt_author.getText().trim();
                float price = Float.parseFloat(txt_price.getText().trim());
                Integer number = Integer.valueOf(txt_number.getText().trim());
                String firstLevel = combo_firstLevel.getSelectedItem().toString();
                String secondLevel = combo_secondLevel.getSelectedItem().toString();
                Category category = DataBase.getCategory(firstLevel,secondLevel);
                Book book = new Book(id, name, author, price, number, category);
                BookServiceImpl bookService = new BookServiceImpl();
                bookService.addBook(book);
                JOptionPane.showMessageDialog(null,"书籍添加成功");
                dispose();//关闭窗口
            }
        });
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_id.setText("");
                txt_name.setText("");
                txt_author.setText("");
                txt_price.setText("");
                txt_number.setText("");
                combo_firstLevel.setSelectedIndex(0);
                combo_secondLevel.setSelectedIndex(0);

            }
        });

    }
}
