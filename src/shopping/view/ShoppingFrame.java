package shopping.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingFrame extends JFrame {
    JMenu user_Menu;
    JMenuItem book_Menu,shopping_Car;
    JMenuBar menuBar;
    public ShoppingFrame() {
        setTitle("电商购物平台");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(true);
        // 创建一个带有背景图的面板
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 加载并绘制背景图
                ImageIcon imgIcon = new ImageIcon("src/image/shoppingFrame.jpg");
                Image img = imgIcon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // 设置透明度，使得背景可见
        backgroundPanel.setOpaque(false);

        setContentPane(backgroundPanel);
        init();
        setVisible(true);
    }
    public void init(){
        user_Menu = new JMenu("用户操作");
        book_Menu = new JMenuItem("书单");
        shopping_Car = new JMenuItem("购物车");

        user_Menu.add(book_Menu);
        user_Menu.addSeparator();
        user_Menu.add(shopping_Car);

        menuBar = new JMenuBar();
        menuBar.add(user_Menu);
        setJMenuBar(menuBar);//使其成为该窗口的菜单栏

        book_Menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryBookFrame();
            }
        });
        shopping_Car.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCarFrame();
            }
        });
    }
}
