package models;

import exceptions.BookNotAvailableException;
import interfaces.Borrowable;

public class Book implements Borrowable {

    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() throws BookNotAvailableException {
        if (!available) {
            throw new BookNotAvailableException("Book already issued: " + title);
        }
        available = false;
    }

    @Override
    public void giveBack() {
        available = true;
    }

    @Override
    public String toString() {
        return id + " - " + title + " (" + author + ")";
    }
}
