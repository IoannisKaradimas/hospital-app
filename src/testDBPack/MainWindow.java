package testDBPack;

/**
 * Opens the main windows where the user can choose
 * which table (Doctors or Patients) to CRUD
 * 
 * @author I.Karadimas
 * @version 0.1
 */

import java.awt.BorderLayout;


import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Connection conn;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false&serverTimezone=UTC";
				String username	= "root";
				String password = "121292joh";
				
				try {
					conn = DriverManager.getConnection(url, username, password);
				} catch (SQLException ex) {
					throw new IllegalStateException("Cannot connect the database!", ex);
				}
			}
		});
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setTitle("Hospital Database");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HEALTHCARE QUALITY");
		lblNewLabel.setBounds(0, 11, 659, 35);
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HEALTHCARE QUALITY");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBounds(95, 5, 387, 47);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(128, 128, 128));
		separator.setBounds(10, 50, 649, 2);
		contentPane.add(separator);
		
		JButton btnDoctors = new JButton("Doctors");
		btnDoctors.setForeground(new Color(0, 0, 0));
		btnDoctors.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDoctors.setFocusable(false);
		btnDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.docSearchFrame.setVisible(true);
				HospitalApp.mainFrame.setEnabled(false);
			}
		});
		btnDoctors.setBounds(255, 126, 150, 50);
		contentPane.add(btnDoctors);
		
		JButton btnVersion = new JButton("Version");
		btnVersion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVersion.setFocusable(false);
		btnVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.version.setVisible(true);
				HospitalApp.mainFrame.setEnabled(false);
			}
		});
		btnVersion.setBounds(280, 414, 100, 40);
		contentPane.add(btnVersion);
		
		JButton btnPatients = new JButton("Patients");
		btnPatients.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPatients.setFocusable(false);
		btnPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.patSearchFrame.setVisible(true);
				HospitalApp.mainFrame.setEnabled(false);
			}
		});
		btnPatients.setBounds(255, 202, 150, 50);
		contentPane.add(btnPatients);
	}
}
