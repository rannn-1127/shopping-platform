package shopping.service;

import shopping.dao.BookDaoImpl;
import shopping.dao.IBookDao;
import shopping.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements IBookService {
    private IBookDao bookDao = new BookDaoImpl();
    private List<Book> books;

    @Override
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBooks();
    }

    @Override
    public List<Book> queryByName(String name) {
        return bookDao.queryByName(name);
    }

    @Override
    public List<Book> queryBookByType(String type) {
        return bookDao.queryByType(type);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
}
