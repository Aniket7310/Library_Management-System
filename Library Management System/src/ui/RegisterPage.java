package ui;

import service.UserService;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;

    private final UserService userService = new UserService();

    public RegisterPage() {
        setTitle("Register");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        panel.add(new JLabel("Confirm Password:"));
        confirmPassField = new JPasswordField();
        panel.add(confirmPassField);

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> doRegister());
        panel.add(registerBtn);

        add(panel);
    }

    private void doRegister() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();
        String confirm = new String(confirmPassField.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        if (userService.register(user, pass)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            dispose(); // close register window
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        }
    }
}
