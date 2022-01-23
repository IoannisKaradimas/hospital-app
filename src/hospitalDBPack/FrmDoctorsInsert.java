package hospitalDBPack;

/**
 * Inserts an new entry in the doctor table
 * Doctor's ID is generated automatically
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

public class FrmDoctorsInsert extends JFrame {

	private JPanel contentPane;
	private JTextField frm_sname;
	private JTextField frm_fname;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDoctorsInsert frame = new FrmDoctorsInsert();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmDoctorsInsert() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				frm_sname.setText("");
				frm_fname.setText("");
			}
		});
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setResizable(false);
		setTitle("New Doctor Entry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(196, 174, 276, 1);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(10, 174, 199, 1);
		contentPane.add(separator_1);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sname = frm_sname.getText();
					String fname = frm_fname.getText();
					
					PreparedStatement p = MainWindow.conn.prepareStatement("INSERT INTO hospital.doctors (DOC_SNAME, DOC_FNAME) VALUE (?, ?)");
					
					p.setString(1, sname);
					p.setString(2, fname);
					
					p.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
					
					p.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(284, 228, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.docSearchFrame.setEnabled(true);
				HospitalApp.docInsertFrame.setVisible(false);
			}
		});
		btnClose.setBounds(383, 228, 89, 23);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 30, 462, 135);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_sname = new JLabel("Surname");
		lbl_sname.setBounds(10, 29, 70, 14);
		panel.add(lbl_sname);
		lbl_sname.setForeground(new Color(178, 34, 34));
		lbl_sname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		frm_sname = new JTextField();
		frm_sname.setBounds(177, 28, 180, 20);
		panel.add(frm_sname);
		frm_sname.setColumns(10);
		
		JLabel lbl_fname = new JLabel("First Name");
		lbl_fname.setBounds(10, 91, 80, 14);
		panel.add(lbl_fname);
		lbl_fname.setForeground(new Color(178, 34, 34));
		lbl_fname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		frm_fname = new JTextField();
		frm_fname.setBounds(177, 90, 180, 20);
		panel.add(frm_fname);
		frm_fname.setColumns(10);
	}
}
