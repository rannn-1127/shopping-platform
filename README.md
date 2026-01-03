# **🛒 电商管理系统（Java Swing 版）**

> **中北大学《Java 程序设计》课程大作业**
> 一个基于 Java Swing 实现的简易电商平台桌面客户端，支持用户登录、注册、角色权限管理、商品浏览与管理等功能。
> ✨ **如果你觉得这个项目对你有帮助，欢迎点个 ⭐ Star 支持一下！也欢迎提出建议或交流学习～**
>==本项目仅供借鉴学习，请勿抄袭==



------

## **一. 项目简介**

本项目是为完成**中北大学计算机相关专业《Java 程序设计》课程大作业**而开发的应用。系统采用经典的 **MVC 架构**，使用 **Java + Swing** 技术栈实现，涵盖面向对象编程、GUI 设计、分层架构、数据封装等核心知识点，适合作为 Java 初学者的综合实践项目。



------

## **二.  功能特性**

| 功能模块     | 描述                                                         |
| :----------- | :----------------------------------------------------------- |
| 用户登录     | 支持管理员与普通用户两种角色                                 |
| 用户注册     | 新用户可注册账号                                             |
| 角色跳转     | 登录后自动进入对应界面： • 管理员 → 后台管理 • 普通用户 → 购物主界面 |
| 商品管理     | 管理员可添加、查询书籍信息                                   |
| 购物车功能   | 用户可将商品加入购物车并查看                                 |
| 用户信息展示 | 查看当前用户详细信息                                         |

> 当前版本使用内存模拟数据库（`db.DataBase`），便于快速运行与调试。



------

## **三. 技术栈**

- **语言**：Java 21
- **GUI 框架**：Swing
- **架构模式**：MVC（Model-View-Controller）
- **包结构**：清晰分层（entity / dao / service / view / db）



------

## **四.项目目录结构**


```txt
shopping/
├── dao/              # 数据访问层接口与实现
│   ├── IBookDao.java
│   ├── IUserDao.java
│   ├── BookDaoImpl.java
│   └── UserDaoImpl.java
│
├── db/               # 数据库模拟类（内存存储）
│   └── DataBase.java
│
├── entity/           # 实体类（POJO）
│   ├── Book.java
│   ├── Category.java
│   ├── ShoppingCart.java
│   └── User.java
│
├── service/          # 业务逻辑层接口与实现
│   ├── IBookService.java
│   ├── IUserService.java
│   ├── BookServiceImpl.java
│   └── UserServiceImpl.java
│
└── view/             # 视图层（GUI 界面）
    ├── AdminFrame.java         # 管理员主界面
    ├── BookAddFrame.java       # 添加书籍界面
    ├── LoginFrame.java         # 登录界面
    ├── QueryAllUserFrame.java  # 查询所有用户
    ├── QueryBookFrame.java     # 查询书籍列表
    ├── RegisterFrame.java      # 注册界面
    ├── ShoppingCarFrame.java   # 购物车界面
    ├── ShoppingFrame.java      # 用户购物主界面
    ├── TestShopping.java       # 测试入口（可选）
    |__ UserInfoFrame.java      # 用户信息展示
    
```



## 五.拓展部分

> 针对学校老师给出的功能，进行了部分优化与拓展

#### 1）Category的id自动增加

在书籍添加页面，设置书籍的类目时，优化了对类目增加的处理

在`DataBase`中添加

```Java
// 核心：根据一级/二级分类名获取或创建 Category
    public static Category getCategory(String firstLevel, String secondLevel) {
        // 先查找是否已存在
        for (Category c : categories) {
            if (firstLevel.equals(c.getFirstLevel()) &&
                    secondLevel.equals(c.getSecondLevel())) {
                return c; // 复用已有
            }
        }
        // 不存在则创建新分类
        Category newCat = new Category(nextCategoryId++, firstLevel, secondLevel);
        categories.add(newCat);
        return newCat;
    }
    // 辅助：初始化时用
    private static void addCategory(String first, String second) {
        categories.add(new Category(nextCategoryId++, first, second));
    }
```

若增加书籍的类目已存在则直接复用，若不存在id自增，创建一个新的类目，加入Category的List中



#### 2）优化一级二级目录的联系

在书籍类目的设置中，实现是 **级联下拉框** —— 即：**第一个下拉框的选择，动态决定第二个下拉框的选项**

根据一级目录匹配下面可选的二级目录

**步骤说明**

1. 全部用 `switch` 写，简单直观
2. **一行监听器**：`addActionListener(e -> { ... })`
3. **自动初始化**：最后一行 `setSelectedIndex(0)` 触发默认加载



```Java
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
```



#### 3）用户购物页面的拓展

##### 3.1）页面的背景美化

1.背景直接让AI生成了一张


```Java
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
```

2.添加用户操作的菜单

```Java
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
```



##### 3.2）功能完善与拓展

在用户购物页面，实现书单查询和操作购物车的功能

- 添加`ShoppingCarFrame`

  ```Java
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
  ```

- 在`DataBase`中添加初识购物车的内容

  ```Java
  public static ShoppingCar shoppingCar = new ShoppingCar();
  
  static{
  		//... ...    
  		//初始化购物车 每本书买一个
          for (Book book : books) {
              try {
                  shoppingCar.add(book,1);
              } catch (Exception e) {
                  throw new RuntimeException(e);
              }
          }
  	}
  ```

- 在`ShoppingCarFrame`中实现购物车的查询、修改，并调用ShoppingCar类的方法实现书籍本书的统计，以及总价的计算，在修改中实时更新

  ```Java
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
  ```

  



**如果你觉得这个项目对你有帮助可以点个 ⭐ Star吗**

**祝大家 Java 学习顺利，期末高分通过！** 😊
