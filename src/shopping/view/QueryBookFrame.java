package shopping.view;

import shopping.entity.Book;
import shopping.service.BookServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QueryBookFrame extends JFrame {
    BookServiceImpl bookService = new BookServiceImpl();
    List<Book> books = bookService.queryAllBooks();
    JLabel lbl_name,lbl_type;
    JTextField txt_name;
    JButton btn_query;
    JTable table;
    JComboBox<String> combo_type;
    DefaultTableModel model;//表示需要动态操作表格

    public QueryBookFrame() {
        setTitle("电商购物平台——书籍查询");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(Color.WHITE);
        init();
        setVisible(true);
    }

    private void init() {
        setLayout(new BorderLayout());
        lbl_name = new JLabel("书名：",JLabel.RIGHT);
        lbl_type = new JLabel("分类：",JLabel.RIGHT);
        txt_name = new JTextField(20);
        combo_type = new JComboBox<String>();
        combo_type.addItem("工具类");
        combo_type.addItem("小说类");
        combo_type.addItem("英语类");
        combo_type.addItem("科普类");

        btn_query = new JButton("查询");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(lbl_name);
        panel.add(txt_name);
        panel.add(btn_query);
        panel.add(lbl_type);
        panel.add(combo_type);
        add(panel,BorderLayout.NORTH);

        String[] columnNames = {"编号","书名","作者","定价","库存","分类"};
        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        resetTableDAta(books);//查询全部书籍
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txt_name.getText().trim();
                if(name.equals("")){
                    resetTableDAta(books);//文本框为空查询全部
                }else {
                    resetTableDAta(bookService.queryByName(name));//按照书名查询
                }
            }
        });

        combo_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = combo_type.getSelectedItem().toString();
                resetTableDAta(bookService.queryBookByType(type));//按照类型查询
            }
        });

    }
    public void resetTableDAta(List<Book> books) {
        model.setRowCount(0);
        for (Book book : books) {
            Object[] row = {
                    book.getBid(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getNumber(),
                    book.getCategory()
            };
            model.addRow(row);
        }
    }
}
