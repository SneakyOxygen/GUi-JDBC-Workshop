package pages;

import dal.admins.AdminDAO;
import java.awt.*;
import javax.swing.*;

public class SignUpPage extends JFrame { 
    private final JTextField usernameField;//variables, lahat ng widgets
    private final JPasswordField passwordField;
    private final JButton signUpButton;

    public SignUpPage()
    {
        setTitle("New User Sign Up");
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

        
        signUpButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        signUpButton.addActionListener(e -> handleSignUp());

        setLocationRelativeTo(null);
        setVisible(true); // if false, naka hide
    }
    
    private void handleSignUp()
    {   
        String username = usernameField.getText(); // kuha user innput .get
        String password = new String(passwordField.getPassword());
        AdminDAO admin = new AdminDAO();
        
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        else 
        {
            admin.newUser(username, password);
            JOptionPane.showMessageDialog(this,"New User logged in!");
            new StudentPage();

            dispose(); // dispose sign up
        }
    }
        
    
}
