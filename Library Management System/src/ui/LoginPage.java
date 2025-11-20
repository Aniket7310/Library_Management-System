package ui;

import javax.swing.*;
import java.awt.*;
import service.UserService;

public class LoginPage extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    private final UserService userService = new UserService();

    public LoginPage() {
        setTitle("Library Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        init();
        setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> doLogin());
        panel.add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> openRegister());
        panel.add(registerBtn);

        add(panel);
    }

    private void doLogin() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();

        if (userService.login(user, pass)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose();
            new LibraryGUI();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    private void openRegister() {
        new RegisterPage();
    }
}
