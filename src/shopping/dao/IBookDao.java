package shopping.dao;

import shopping.entity.Book;

import java.util.List;

public interface IBookDao {
    public abstract List<Book> queryAllBooks();
    public abstract List<Book> queryByName(String name);
    public abstract List<Book> queryByType(String type);
    public abstract void addBook(Book book);
}
