package pages;

import dal.admins.AdminDAO;
import java.awt.*;
import javax.swing.*;

public class forgorPassPage extends JFrame {
    private final JTextField usernameField;//variables, lahat ng widgets
    private final JPasswordField newPasswordField;
    private final JButton signUpButton;
    AdminDAO admin = new AdminDAO();

    public forgorPassPage()
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

        JLabel passwordLabel = new JLabel("New Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        newPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(newPasswordField, gbc);

        
        signUpButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);

        signUpButton.addActionListener(e -> handleForgorPassword());

        setLocationRelativeTo(null);
        setVisible(true); // if false, naka hide

        
    }

    
    private void handleForgorPassword()
    {
        String username = usernameField.getText(); // kuha user innput .get
        String newPassword = new String(newPasswordField.getPassword());
        boolean approved = admin.checkUsername(username);
        
        if (username.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and new password.");
            return;
        }

        if(approved)
        {
            
            admin.forgorPass(username, newPassword);
            JOptionPane.showMessageDialog(this,"Your password has been updated!");
            new StudentPage();

            dispose(); // dispose forgorPass
        }
        else 
        {
         JOptionPane.showMessageDialog(this, "Invalid username.");   
        }
    }
}


