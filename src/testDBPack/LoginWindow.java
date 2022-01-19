package testDBPack;

/**
 * Implements a login page
 * The userID field is not case sensitive
 * 
 * userID = "aueb"
 * password = "1234"
 * 
 * @author I.Karadimas
 * @version 0.1
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userIDField;
	private JPasswordField userPasswordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginWindow() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setResizable(false);
		setTitle("Hospital Database - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_userID = new JLabel("userID:");
		lbl_userID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_userID.setBounds(50, 100, 75, 25);
		contentPane.add(lbl_userID);
		
		JLabel lbl_password = new JLabel("password:");
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_password.setBounds(50, 160, 75, 25);
		contentPane.add(lbl_password);
		
		userIDField = new JTextField();
		userIDField.setBounds(125, 100, 200, 25);
		contentPane.add(userIDField);
		userIDField.setColumns(10);
		
		userPasswordField = new JPasswordField();
		userPasswordField.setBounds(125, 160, 200, 25);
		contentPane.add(userPasswordField);
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = userIDField.getText();
				String password = String.valueOf(userPasswordField.getPassword());
				
				if (userId.equalsIgnoreCase("aueb") && password.equals("1234")) {
					HospitalApp.mainFrame.setVisible(true);
					HospitalApp.loginFrame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Id or Password", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_login.setBackground(new Color(220, 220, 220));
		btn_login.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_login.setBounds(125, 200, 100, 25);
		btn_login.setFocusable(false);
		contentPane.add(btn_login);
		
		//Clears the userId and password fields
		JButton btn_reset = new JButton("Reset");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userIDField.setText("");
				userPasswordField.setText("");
			}
		});
		btn_reset.setForeground(new Color(0, 0, 0));
		btn_reset.setBackground(new Color(220, 220, 220));
		btn_reset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_reset.setBounds(225, 200, 100, 25);
		btn_reset.setFocusable(false);
		contentPane.add(btn_reset);
		
		//Just a small hint
		JButton btn_hint = new JButton("Hint");
		btn_hint.setBackground(new Color(220, 220, 220));
		btn_hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "userID:aueb\npassword:1234", "Hint", JOptionPane.WARNING_MESSAGE);
			}
		});
		btn_hint.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_hint.setBounds(180, 236, 89, 23);
		btn_hint.setFocusable(false);
		contentPane.add(btn_hint);
	}
}
