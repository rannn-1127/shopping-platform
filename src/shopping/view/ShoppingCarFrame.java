package shopping.view;

import shopping.db.DataBase;
import shopping.entity.Book;
import shopping.entity.ShoppingCar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ShoppingCarFrame extends JFrame {
    private ShoppingCar shoppingCar = DataBase.shoppingCar; // 全局购物车

    private JLabel lbl_totalNum, lbl_totalPrice;
    private JButton btn_delete;
    private JTable table;
    private DefaultTableModel model;

    public ShoppingCarFrame() {
        setTitle("电商购物平台——用户购物车");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 不要 EXIT_ON_CLOSE，避免关闭整个程序
        getContentPane().setBackground(Color.WHITE);
        init();
        setVisible(true);
    }

    public void init() {
        setLayout(new BorderLayout());

        //顶部操作区
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        btn_delete = new JButton("删除选中商品");
        lbl_totalNum = new JLabel("总数量：0 本");
        lbl_totalPrice = new JLabel("总价：¥0.00");

        topPanel.add(btn_delete);
        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(lbl_totalNum);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(lbl_totalPrice);

        add(topPanel, BorderLayout.NORTH);

        // 购物车表格
        String[] columnNames = {"编号", "书名", "数量", "单价", "小计"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 初始加载数据
        refreshDisplay();

        // 删除按钮事件
        btn_delete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                        ShoppingCarFrame.this,
                        "请先选择要删除的商品！",
                        "提示",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String bid = (String) model.getValueAt(selectedRow, 0);
            Book bookToDelete = null;
            for (Book book : shoppingCar.keySet()) {
                if (book.getBid().equals(bid)) {
                    bookToDelete = book;
                    break;
                }
            }

            if (bookToDelete == null) {
                JOptionPane.showMessageDialog(
                        ShoppingCarFrame.this,
                        "未在购物车中找到该商品！",
                        "错误",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            //删除
            try {
                shoppingCar.delete(bookToDelete);
                JOptionPane.showMessageDialog(
                        ShoppingCarFrame.this,
                        "删除成功！",
                        "成功",
                        JOptionPane.INFORMATION_MESSAGE
                );
                refreshDisplay(); // 刷新界面
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        ShoppingCarFrame.this,
                        ex.getMessage(),
                        "操作失败",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private void refreshDisplay() {
        model.setRowCount(0); // 清空表格

        // 填充当前购物车内容
        for (Map.Entry<Book, Integer> entry : shoppingCar.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            float subtotal = quantity * book.getPrice();

            Object[] row = {
                    book.getBid(),       // String
                    book.getName(),      // String
                    quantity,            // Integer
                    book.getPrice(),     // Float
                    subtotal             // Float
            };
            model.addRow(row);
        }

        // 更新统计信息
        int totalNum = shoppingCar.countBooksNum();
        float totalPrice = shoppingCar.countBooksPrice();

        lbl_totalNum.setText("总数量：" + totalNum + " 本");
        lbl_totalPrice.setText(String.format("总价：¥%.2f", totalPrice));
    }
}