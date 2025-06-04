package pages;

import dal.admins.AdminDAO;
import java.awt.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    private final AdminDAO adminDao = new AdminDAO();
    private final JTextField usernameField;//variables, lahat ng widgets
    private final JPasswordField passwordField;
    private final JButton signInButton;
    private final JButton signUpButton;
    private final JButton forgotPassButton;


    public LoginPage() { //config/instantiate, position widgets
        setTitle("Admin Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0; //positioning
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        signInButton = new JButton("Log In");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(signInButton, gbc);

        signUpButton = new JButton("Sign Up");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(signUpButton, gbc);

        forgotPassButton = new JButton("Forgot Password");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(forgotPassButton, gbc);

        signInButton.addActionListener(e -> handleLogin());
        signUpButton.addActionListener(e -> handleSignUp());
        forgotPassButton.addActionListener(e -> forgorPass());

        setLocationRelativeTo(null);
        setVisible(true); // if false, naka hide
    }
    //logic
    private void handleLogin() {
        String username = usernameField.getText(); // kuha user innput .get
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        boolean valid = adminDao.checkIfAdminExists(username, password);
        if (valid) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new StudentPage();

            dispose(); // dispose login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void handleSignUp()
    {
        new SignUpPage();
        dispose(); // dispose login
    }

    private void forgorPass()
    {
        new forgorPassPage();
        dispose(); // dispose login
    }
}
