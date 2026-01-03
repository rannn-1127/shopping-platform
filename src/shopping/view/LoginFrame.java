package shopping.view;

import shopping.entity.User;
import shopping.service.IUserService;
import shopping.service.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JLabel lbl_name,lbl_password,lbl_type;
    private JTextField txt_name;
    private JPasswordField p_password;
    private JComboBox<String> combo_type;
    private JButton btn_login,btn_register,btn_reset;
    public LoginFrame() {
        setTitle("电商平台——登录页面");
        setSize(400,280);
        setLocationRelativeTo(null);//窗体在屏幕中间显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点击X能退出
        init();
        setResizable(true);
        setVisible(true);
    }
    public void init(){
        setLayout(null);//自定义布局
        lbl_name = new JLabel("用户名",new ImageIcon("src\\image\\user.png"),JLabel.CENTER);
        lbl_type = new JLabel("用户类别",JLabel.CENTER);
        lbl_password = new JLabel("密码",JLabel.CENTER);

        txt_name = new JTextField();
        combo_type = new JComboBox<String>();
        combo_type.addItem("--请选择--");
        combo_type.addItem("管理员");
        combo_type.addItem("普通用户");
        p_password = new JPasswordField();
        btn_login = new JButton("登录");
        btn_register = new JButton("注册");
        btn_reset = new JButton("重置");

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3,2,5,5));
        p.add(lbl_name);
        p.add(txt_name);
        p.add(lbl_type);
        p.add(combo_type);
        p.add(lbl_password);
        p.add(p_password);
        p.setBounds(5,5,375,180);
        this.add(p);
        p = new JPanel();
        p.setLayout(new GridLayout(1,3,5,5));
        p.add(btn_register);
        p.add(btn_login);
        p.add(btn_reset);
        p.setBounds(5,190,375,45);
        this.add(p);

        btn_reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txt_name.setText("");
                combo_type.setSelectedIndex(0);
                p_password.setText("");
            }
        });
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(txt_name.getText().trim(),
                        new String(p_password.getPassword()),//getPassword() 返回 char[]（安全设计），需转为 String
                        combo_type.getSelectedItem().toString());//getSelectedItem() 返回 Object，需转为 String

                IUserService  userService = new UserServiceImpl();
                if(userService.isVAlidUser(user)){
                    if(user.getRole().equals("管理员")){
                        new AdminFrame();
                    }else {
                        new ShoppingFrame();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"用户名或者密码错误");//null表示弹窗出现在整个屏幕正中间
                }

            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame();
//        new RegisterFrame();
//        new UserInfoFrame(new User("u001","zs","123","管理员",'女',"上海"));
//        new AdminFrame();
//        new BookAddFrame();

    }
}
