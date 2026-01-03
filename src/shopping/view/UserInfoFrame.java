package shopping.view;

import shopping.entity.User;

import javax.swing.*;
import java.awt.*;

public class UserInfoFrame extends JFrame {
    private User user;
    JTable table;//表格
    public UserInfoFrame(User user) {
        this.user = user;
        setTitle("电商购物平台——用户信息页面");
        setSize(600,400);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
    }
    private void init() {
        setLayout(new BorderLayout());
        Object[] userTitle = {"用户编号","姓名","密码","角色","性别","城市"};
        Object[][] userInfo = {{user.getId(),user.getName(),user.getPassword(),user.getRole(),user.getSex(),user.getCity()}};
        table = new JTable(userInfo,userTitle);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);
    }
}
