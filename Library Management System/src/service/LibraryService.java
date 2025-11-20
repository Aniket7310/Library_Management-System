package service;

import database.DatabaseConfig;
import exceptions.BookNotAvailableException;
import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    public LibraryService() {}

    // Add book to DB using JDBC
    public void addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author, available) VALUES (?, ?, TRUE)";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.executeUpdate();

            System.out.println("Book added to DB");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Issue book (set available = false)
    public void issueBook(int id) throws BookNotAvailableException {
        String check = "SELECT available FROM books WHERE id=?";
        String update = "UPDATE books SET available=FALSE WHERE id=?";

        try (Connection con = DatabaseConfig.getConnection()) {

            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setInt(1, id);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next())
                throw new BookNotAvailableException("Book not found");

            if (!rs.getBoolean("available"))
                throw new BookNotAvailableException("Book already issued");

            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return book (set available = true)
    public void returnBook(int id) throws BookNotAvailableException {
        String update = "UPDATE books SET available=TRUE WHERE id=?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(update)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load all books (JDBC SELECT)
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try (Connection con = DatabaseConfig.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
