package io.github.cchenxi.w5.h2;

/**
 * Date: 2020-11-16
 *
 * @author chenxi
 */
public class Book {
    private int id;
    private String name;
    private String author;

    public Book() {

    }

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public static Book createBook() {
        Book book = new Book();
        book.setId(1000);
        book.setName("book-x");
        book.setAuthor("book-author");
        return book;
    }
}
