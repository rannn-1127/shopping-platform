package shopping.view;

import shopping.entity.User;
import shopping.service.IUserService;
import shopping.service.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    JTextField txt_id,txt_name;
    JPasswordField p_psw,p_repsw;
    JComboBox<String> cb_role,cb_city;
    JRadioButton rb_male,rb_female;
    JButton btn_register,btn_reset;
    JPanel panel;
    public RegisterFrame() {
        setTitle("电商购物平台——注册页面");
        setSize(400,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }
    public void init() {
        setLayout(new GridLayout(8,2,5,5));
        JLabel lbl_id = new JLabel("账号：",JLabel.RIGHT);
        JLabel lbl_name = new JLabel("姓名",JLabel.RIGHT);
        JLabel lbl_psw = new JLabel("密码：",JLabel.RIGHT);
        JLabel lbl_repsw = new JLabel("确认密码：",JLabel.RIGHT);
        JLabel lbl_role = new JLabel("用户类型：",JLabel.RIGHT);
        JLabel lbl_sex = new JLabel("性别：",JLabel.RIGHT);
        JLabel lbl_city = new JLabel("城市：",JLabel.RIGHT);

        txt_id = new JTextField(30);
        txt_name = new JTextField(30);
        p_psw = new JPasswordField(30);
        p_repsw = new JPasswordField(30);
        cb_role = new JComboBox<String>();
        cb_role.addItem("普通用户");
        cb_role.addItem("管理员");
        cb_city = new JComboBox<String>();
        cb_city.addItem("北京");
        cb_city.addItem("上海");
        cb_city.addItem("广州");
        cb_city.addItem("深圳");
        cb_city.addItem("杭州");
        cb_city.addItem("南京");
        cb_city.addItem("天津");
        cb_city.addItem("太原");

        rb_male = new JRadioButton("男");
        rb_female = new JRadioButton("女");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb_male);
        bg.add(rb_female);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(rb_female);
        panel.add(rb_male);

        btn_register = new JButton("注册");
        btn_reset = new JButton("重置");

        add(lbl_id);
        add(txt_id);
        add(lbl_name);
        add(txt_name);
        add(lbl_psw);
        add(p_psw);
        add(lbl_repsw);
        add(p_repsw);
        add(lbl_role);
        add(cb_role);
        add(lbl_sex);
        add(panel);
        add(lbl_city);
        add(cb_city);
        add(btn_reset);
        add(btn_register);

        btn_reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txt_id.setText("");
                txt_name.setText("");
                p_psw.setText("");
                p_repsw.setText("");
                cb_role.setSelectedIndex(0);
                cb_city.setSelectedIndex(0);
            }
        });
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText().trim();
                String name = txt_name.getText().trim();
                String psw = new String(p_psw.getPassword());
                String repsw = new String(p_repsw.getPassword());
                String role = cb_role.getSelectedItem().toString();
                String city = cb_city.getSelectedItem().toString();
                char sex;
                if(rb_female.isSelected()){
                    sex = '女';
                }else {
                    sex = '男';
                }
                if(psw.equals(repsw)){
                    User u = new User(id,name,psw,role,sex,city);
                    IUserService userService = new UserServiceImpl();
                    userService.addUser(u);//存数据
                    JOptionPane.showMessageDialog(null,"注册成功");
                    dispose();//关闭当前页面
                }else {
                    JOptionPane.showMessageDialog(null,"两次输入的密码不一致");
                }
            }
        });

    }


}
