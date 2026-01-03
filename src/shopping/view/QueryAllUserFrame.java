package shopping.view;

import shopping.entity.User;
import shopping.service.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class QueryAllUserFrame extends JFrame {
    private UserServiceImpl userService;
    private JTable table;
    private DefaultTableModel model;
    public QueryAllUserFrame() {
        setTitle("电商购物平台——查看所有用户");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(true);
        init();
        setVisible(true);
    }
    public void init(){
        userService = new UserServiceImpl();
        String[] columnNames = {"用户编号","姓名","密码","角色","性别","城市"};
        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        resetTableData(userService.queryAllUser());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

    }
    public void resetTableData(Set<User> users){
        model.setRowCount(0);
        for(User user:users){
            Object[] row = {
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    user.getRole(),
                    user.getSex(),
                    user.getCity()
            };
            model.addRow(row);
        }
    }
}
