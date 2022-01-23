package hospitalDBPack;

/**
 * Create a new user account
 * 
 * @author I.Karadimas
 * @version 0.1
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class RegisterAccount extends JFrame {

	private JPanel contentPane;
	private JTextField userIdTextField;
	private JTextField passwordTextField;
	private JPasswordField passConfTextField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAccount frame = new RegisterAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterAccount() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				userIdTextField.setText("");
				passwordTextField.setText("");
				passConfTextField.setText("");
			}
		});
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setResizable(false);
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_create = new JLabel("Create Account");
		lbl_create.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_create.setBounds(180, 23, 125, 27);
		contentPane.add(lbl_create);
		
		JLabel lbl_userId = new JLabel("Username:");
		lbl_userId.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_userId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_userId.setBounds(55, 77, 71, 14);
		contentPane.add(lbl_userId);
		
		JLabel lbl_password = new JLabel("Password:");
		lbl_password.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_password.setBounds(55, 112, 71, 14);
		contentPane.add(lbl_password);
		
		userIdTextField = new JTextField();
		userIdTextField.setBounds(152, 75, 180, 20);
		contentPane.add(userIdTextField);
		userIdTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(152, 109, 180, 20);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		//Creates a new user account
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(220, 220, 220));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
				String userId = userIdTextField.getText().trim();
				String password = passwordTextField.getText().trim();
					
				//Checks if the username is already in use and returns a message if it is
				PreparedStatement p = LoginWindow.conn.prepareStatement("select id from userlist where username = ?");
				p.setString(1, userId);
				ResultSet rs = p.executeQuery();
				
				//Confirms Password
				if (passwordTextField.getText().equals(passConfTextField.getText())) {
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Username already exists!", "Account Creation", JOptionPane.WARNING_MESSAGE);
						//Prevents the user from inserting empty fields
					} else if ((userId.equals("")) || (password.equals(""))) {
						JOptionPane.showMessageDialog(null, "Username and Password fields cannot be empty!", "Registration Failed", JOptionPane.WARNING_MESSAGE);
						//Inserts a new entry into the DB
					} else {
						p = LoginWindow.conn.prepareStatement("INSERT INTO hospital.userlist (username, password) value (?, ?)");
						
						p.setString(1, userId);
						p.setString(2, password);
						
						p.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Account creation was successful!", "Account Creation", JOptionPane.PLAIN_MESSAGE);
						
						HospitalApp.loginFrame.setVisible(true);
						HospitalApp.registerFrame.setVisible(false);	
					}
				} else {
					JOptionPane.showMessageDialog(null, "Confirmation password does not match!", "Registration Failed", JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSubmit.setFocusable(false);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(197, 179, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(220, 220, 220));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.loginFrame.setEnabled(true);
				HospitalApp.registerFrame.setVisible(false);
			}
		});
		btnClose.setBounds(337, 229, 89, 23);
		btnClose.setFocusable(false);
		contentPane.add(btnClose);
		
		JLabel lbl_passConfirm = new JLabel("Confirm Password:");
		lbl_passConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_passConfirm.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_passConfirm.setBounds(10, 146, 116, 14);
		contentPane.add(lbl_passConfirm);
		
		passConfTextField = new JPasswordField();
		passConfTextField.setBounds(152, 143, 180, 20);
		contentPane.add(passConfTextField);
	}
}
