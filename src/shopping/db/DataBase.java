package shopping.db;

import shopping.dao.BookDaoImpl;
import shopping.entity.Book;
import shopping.entity.Category;
import shopping.entity.ShoppingCar;
import shopping.entity.User;

import java.util.ArrayList;
import java.util.*;
import java.util.Set;

public class DataBase {
//    private static Book[] books = new Book[100];
    private static Set<User> users = new HashSet<User>();
    private static List<Book> books = new ArrayList<Book>();
    private static List<Category> categories = new ArrayList<>();//拓展部分：用于优化添加书本时的类型选择

    private static int nextCategoryId = 1;//用于生成自增ID

    public static ShoppingCar shoppingCar = new ShoppingCar();

    static {
        addCategory("工具类","软件编程");
        addCategory("小说类","历史");
        Category category1 = categories.get(0);
        Category category2 = categories.get(1);
        books.add(new Book("b001","程序设计","耿详义",45.5f,45,category1));
        books.add(new Book("b002", "大话数据结构", "程杰", 59.8f, 100, category1));
        books.add(new Book("b003", "大话设计模式", "程杰", 49.8f, 80, category1));
        books.add(new Book("b004", "深入理解Java虚拟机", "周志明", 109.0f, 60, category1));
        books.add(new Book("b005", "码农翻身", "刘欣", 69.0f, 90, category1));
        books.add(new Book("b006", "趣学算法", "陈小玉", 79.0f, 70, category1));
        books.add(new Book("b007", "Spring Boot编程思想", "小马哥", 119.0f, 50, category1));books.add(new Book("b008", "万历十五年", "黄仁宇", 39.8f, 120, category2));
        books.add(new Book("b009", "中国历代政治得失", "钱穆", 32.0f, 100, category2));
        books.add(new Book("b010", "明朝那些事儿", "当年明月", 298.0f, 200, category2));
        books.add(new Book("b011", "天朝的崩溃", "茅海建", 78.0f, 85, category2));
        books.add(new Book("b012", "筚路维艰", "萧冬连", 58.0f, 75, category2));
        books.add(new Book("b013", "秦崩", "李开元", 59.0f, 90, category2));
        books.add(new Book("b014", "近代中国社会的新陈代谢", "陈旭麓", 48.0f, 80, category2));

        //初始化购物车 每本书买一个
        for (Book book : books) {
            try {
                shoppingCar.add(book,1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

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

    public static List<Book> getBooks() {
        return books;
    }

    public static Set<User> getUsers() {
        return users;
    }

}
