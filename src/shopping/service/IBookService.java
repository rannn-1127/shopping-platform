package shopping.service;

import shopping.entity.Book;

import java.util.List;

public interface IBookService {
    public abstract List<Book> queryAllBooks();
    public abstract List<Book> queryByName(String name);
    public abstract List<Book> queryBookByType(String type);
    public abstract void addBook(Book book);
}
