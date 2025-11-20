package ui;

import exceptions.BookNotAvailableException;
import models.Book;
import service.LibraryService;
import threads.AutoRefreshTask;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibraryGUI extends JFrame {

    private final LibraryService service = new LibraryService();

    private JTable table;
    private DefaultTableModel model;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField issueField;
    private JTextField returnField;

    public LibraryGUI() {
        setTitle("Library Management System (No DB)");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        // background auto-refresh
        new Thread(new AutoRefreshTask(this)).start();

        setVisible(true);
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new GridLayout(3, 1));

        // ----- Add book row -----
        JPanel addPanel = new JPanel();
        addPanel.add(new JLabel("Title:"));
        titleField = new JTextField(10);
        addPanel.add(titleField);

        addPanel.add(new JLabel("Author:"));
        authorField = new JTextField(10);
        addPanel.add(authorField);

        JButton addBtn = new JButton("Add Book");
        addBtn.addActionListener(e -> onAddBook());
        addPanel.add(addBtn);
        topPanel.add(addPanel);

        // ----- Issue row -----
        JPanel issuePanel = new JPanel();
        issuePanel.add(new JLabel("Issue Book ID:"));
        issueField = new JTextField(5);
        issuePanel.add(issueField);
        JButton issueBtn = new JButton("Issue");
        issueBtn.addActionListener(e -> onIssueBook());
        issuePanel.add(issueBtn);
        topPanel.add(issuePanel);

        // ----- Return row -----
        JPanel returnPanel = new JPanel();
        returnPanel.add(new JLabel("Return Book ID:"));
        returnField = new JTextField(5);
        returnPanel.add(returnField);
        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(e -> onReturnBook());
        returnPanel.add(returnBtn);
        topPanel.add(returnPanel);

        // ----- Table -----
        model = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Available"}, 0);
        table = new JTable(model);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBooksInTable();
    }

    private void onAddBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter title and author");
            return;
        }

        service.addBook(title, author);
        titleField.setText("");
        authorField.setText("");
        loadBooksInTable();
    }

    private void onIssueBook() {
        try {
            int id = Integer.parseInt(issueField.getText().trim());
            service.issueBook(id);
            JOptionPane.showMessageDialog(this, "Book issued");
            loadBooksInTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        } catch (BookNotAvailableException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void onReturnBook() {
        try {
            int id = Integer.parseInt(returnField.getText().trim());
            service.returnBook(id);
            JOptionPane.showMessageDialog(this, "Book returned");
            loadBooksInTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        } catch (BookNotAvailableException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void loadBooksInTable() {
        SwingUtilities.invokeLater(() -> {
            List<Book> books = service.getAllBooks();
            model.setRowCount(0);
            for (Book b : books) {
                model.addRow(new Object[]{
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.isAvailable() ? "Yes" : "No"
                });
            }
        });
    }
}
