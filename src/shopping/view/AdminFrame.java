package shopping.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    JMenu user_Manager,book_Manager;
    JMenuItem user_Add,book_Add,user_SearchAll,book_SearchAll;
    JMenuBar menuBar;
    public AdminFrame() {
        setTitle("电商购物平台——管理员");
        setSize(500,500);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
    }
    public void init(){
        user_Manager = new JMenu("用户管理");
        user_Add = new JMenuItem("添加用户");
        user_SearchAll = new JMenuItem("查看用户");
        user_Manager.add(user_Add);
        user_Manager.addSeparator();
        user_Manager.add(user_SearchAll);

        book_Manager = new JMenu("书籍管理");
        book_Add = new JMenuItem("添加书籍");
        book_SearchAll = new JMenuItem("查询书籍");
        book_Manager.add(book_Add);
        user_Manager.addSeparator();
        book_Manager.add(book_SearchAll);

        menuBar = new JMenuBar();
        menuBar.add(user_Manager);
        menuBar.add(book_Manager);
        setJMenuBar(menuBar);
        user_Add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });
        user_SearchAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryAllUserFrame();
            }
        });
        book_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookAddFrame();
            }
        });
        book_SearchAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryBookFrame();
            }
        });
    }
}
