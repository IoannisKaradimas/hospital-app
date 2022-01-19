package testDBPack;

/**
 * Inserts a new entry in the patients table
 * 
 * @author I.Karadimas
 * @version 0.1
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.BevelBorder;

public class FrmPatientsInsert extends JFrame {

	private JPanel contentPane;
	private JTextField frm_ID;
	private JTextField frm_sname;
	private JTextField frm_fname;
	private JTextField frm_DOC_ID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPatientsInsert frame = new FrmPatientsInsert();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmPatientsInsert() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				frm_ID.setText("");
				frm_sname.setText("");
				frm_fname.setText("");
				frm_DOC_ID.setText("");
			}
		});
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setResizable(false);
		setTitle("New Patient Entry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setForeground(new Color(178, 34, 34));
		lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ID.setBounds(29, 51, 49, 14);
		contentPane.add(lbl_ID);
		
		JLabel lbl_sname = new JLabel("Surname");
		lbl_sname.setForeground(new Color(178, 34, 34));
		lbl_sname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_sname.setBounds(29, 89, 70, 14);
		contentPane.add(lbl_sname);
		
		JLabel lbl_fname = new JLabel("First Name");
		lbl_fname.setForeground(new Color(178, 34, 34));
		lbl_fname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_fname.setBounds(29, 129, 80, 14);
		contentPane.add(lbl_fname);
		
		frm_ID = new JTextField();
		frm_ID.setBounds(196, 50, 130, 20);
		contentPane.add(frm_ID);
		frm_ID.setColumns(10);
		
		frm_sname = new JTextField();
		frm_sname.setBounds(196, 88, 180, 20);
		contentPane.add(frm_sname);
		frm_sname.setColumns(10);
		
		frm_fname = new JTextField();
		frm_fname.setBounds(196, 128, 180, 20);
		contentPane.add(frm_fname);
		frm_fname.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(196, 216, 276, 1);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(10, 216, 199, 1);
		contentPane.add(separator_1);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int id = Integer.parseInt(frm_ID.getText());
					String sname = frm_sname.getText();
					String fname = frm_fname.getText();
					int iddoc = Integer.parseInt(frm_DOC_ID.getText());
					
					PreparedStatement p = MainWindow.conn.prepareStatement("INSERT INTO hospital.patients VALUE (?, ?, ?, ?)");
					
					p.setInt(1, id);
					p.setString(2, sname);
					p.setString(3, fname);
					p.setInt(4, iddoc);
					
					p.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record inserted!", "INSERT", JOptionPane.PLAIN_MESSAGE);
					
					p.close();
					
				// Gives a warning error if the given Doctor's ID does not exist in Doctor's database 	
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Doctor's ID does not exist!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnInsert.setBounds(284, 228, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.patSearchFrame.setEnabled(true);
				HospitalApp.patInsertFrame.setVisible(false);
			}
		});
		btnClose.setBounds(383, 228, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lbl_docsid = new JLabel("Patient's Doctor");
		lbl_docsid.setForeground(new Color(178, 34, 34));
		lbl_docsid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_docsid.setBounds(29, 171, 130, 14);
		contentPane.add(lbl_docsid);
		
		frm_DOC_ID = new JTextField();
		frm_DOC_ID.setBounds(196, 170, 180, 20);
		contentPane.add(frm_DOC_ID);
		frm_DOC_ID.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 21, 462, 184);
		contentPane.add(panel);
	}
}
