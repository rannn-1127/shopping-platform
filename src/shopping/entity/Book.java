package shopping.entity;

public class Book {
    private String bid;
    private String name;
    private String author;
    private float price;
    private int number;
    private Category category;

    public Book() {
    }

    public Book(String bid, String name, String author, float price, int number, Category category) {
        this.bid = bid;
        this.name = name;
        this.author = author;
        this.price = price;
        this.number = number;
        this.category = category;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return "商品编号：" + bid +
                "|商品名称：" + name +
                "|作者：" + author +
                "|价格：" + price +
                "|库存数量：" + number +
                "|图书类目：" + category;
    }
}
