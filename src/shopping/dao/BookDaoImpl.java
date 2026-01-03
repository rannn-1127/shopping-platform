package shopping.dao;

import shopping.db.DataBase;
import shopping.entity.Book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {
    List<Book> books = DataBase.getBooks();
    private List<Book> qbooksbyName;
    private List<Book> qbooksbyType;
    //查询所有书籍
    @Override
    public List<Book> queryAllBooks() {
        return books;
    }
    //按照名字查询
    @Override
    public List<Book> queryByName(String name) {
        books = queryAllBooks();
        qbooksbyName = new ArrayList<Book>();
        for (Book book : books) {
            if(book != null && book.getName().equals(name)){
                qbooksbyName.add(book);
            }
        }
        return qbooksbyName;
    }
    //按照类型查询
    @Override
    public List<Book> queryByType(String type) {
        qbooksbyType = new ArrayList<Book>();
        for(Book book : books){
            if(book != null && book.getCategory().getFirstLevel().equals(type)){
                qbooksbyType.add(book);
            }
        }
        return qbooksbyType;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }
}
