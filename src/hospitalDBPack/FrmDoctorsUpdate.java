package hospitalDBPack;

/**
 * The user can navigate through the Doctors' database
 * database and update or delete any inserts 
 * 
 * @author I.Karadimas
 * @version 0.1
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.exceptions.RSAException;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmDoctorsUpdate extends JFrame {
	private static final long serialVersionUID = 1L;
	
	PreparedStatement pst;
	ResultSet rs;
	

	private JPanel contentPane;
	private JTextField udfrm_ID;
	private JTextField udfrm_sname;
	private JTextField udfrm_fname;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDoctorsUpdate frame = new FrmDoctorsUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmDoctorsUpdate() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					String sql = "SELECT DOC_ID, DOC_SNAME, DOC_FNAME FROM hospital.doctors WHERE DOC_SNAME LIKE ?";
					pst = MainWindow.conn.prepareStatement(sql);
					pst.setString(1, DocSearchForm.searchEpwnymoVar + '%');
					rs = pst.executeQuery(); 
						if (rs.next()) {
							udfrm_ID.setText(Integer.toString(rs.getInt("DOC_ID")));
							udfrm_sname.setText(rs.getString("DOC_SNAME"));
							udfrm_fname.setText(rs.getString("DOC_FNAME"));
						} else {
							JOptionPane.showMessageDialog(null, "Doctor not found!", "Search Result", JOptionPane.PLAIN_MESSAGE);
							HospitalApp.docSearchFrame.setEnabled(true);
							HospitalApp.docUpdateFrame.setVisible(false);
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				
				udfrm_ID.setText("");
				udfrm_sname.setText("");
				udfrm_fname.setText("");
			}
		});
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Doctors");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setForeground(new Color(178, 34, 34));
		lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ID.setBounds(10, 39, 49, 14);
		contentPane.add(lbl_ID);
		
		JLabel lbl_sname = new JLabel("Surname");
		lbl_sname.setForeground(new Color(178, 34, 34));
		lbl_sname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_sname.setBounds(10, 74, 70, 14);
		contentPane.add(lbl_sname);
		
		JLabel lbl_fname = new JLabel("First Name");
		lbl_fname.setForeground(new Color(178, 34, 34));
		lbl_fname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_fname.setBounds(10, 110, 80, 14);
		contentPane.add(lbl_fname);
		
		udfrm_ID = new JTextField();
		udfrm_ID.setEditable(false);
		udfrm_ID.setBounds(124, 38, 130, 20);
		contentPane.add(udfrm_ID);
		udfrm_ID.setColumns(10);
		
		udfrm_sname = new JTextField();
		udfrm_sname.setBounds(124, 73, 180, 20);
		contentPane.add(udfrm_sname);
		udfrm_sname.setColumns(10);
		
		udfrm_fname = new JTextField();
		udfrm_fname.setBounds(124, 109, 180, 20);
		contentPane.add(udfrm_fname);
		udfrm_fname.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 160, 416, 1);
		contentPane.add(separator);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						udfrm_ID.setText(Integer.toString(rs.getInt("DOC_ID")));
						udfrm_sname.setText(rs.getString("DOC_SNAME"));
						udfrm_fname.setText(rs.getString("DOC_FNAME"));
					} else {
						JOptionPane.showMessageDialog(null, "No More Entries Found", "Empty Result", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnFirst.setIcon(new ImageIcon(this.getClass().getResource("/resources/FirstRecord.png")));
		btnFirst.setBounds(309, 172, 30, 23);
		contentPane.add(btnFirst);
		
		JButton btnPrev = new JButton("");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						udfrm_ID.setText(Integer.toString(rs.getInt("DOC_ID")));
						udfrm_sname.setText(rs.getString("DOC_SNAME"));
						udfrm_fname.setText(rs.getString("DOC_FNAME"));
					} else {
						rs.first();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		btnPrev.setIcon(new ImageIcon(this.getClass().getResource("/resources/PreviousRecord.png")));
		btnPrev.setBounds(338, 172, 30, 23);
		contentPane.add(btnPrev);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						udfrm_ID.setText(Integer.toString(rs.getInt("DOC_ID")));
						udfrm_sname.setText(rs.getString("DOC_SNAME"));
						udfrm_fname.setText(rs.getString("DOC_FNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e4) {
					e4.printStackTrace();
				}
			}
		});
		btnNext.setIcon(new ImageIcon(this.getClass().getResource("/resources/NextRecord.png")));
		btnNext.setBounds(367, 172, 30, 23);
		contentPane.add(btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						udfrm_ID.setText(Integer.toString(rs.getInt("DOC_ID")));
						udfrm_sname.setText(rs.getString("DOC_SNAME"));
						udfrm_fname.setText(rs.getString("DOC_FNAME"));
					} else {
						JOptionPane.showMessageDialog(null, "No More Entries Found", "Empty Result", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e5) {
					e5.printStackTrace();
				}
			}
		});
		btnLast.setIcon(new ImageIcon(this.getClass().getResource("/resources/LastRecord.png")));
		btnLast.setBounds(396, 172, 30, 23);
		contentPane.add(btnLast);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query ="DELETE FROM hospital.doctors WHERE DOC_ID = ?";
					PreparedStatement preparedStmt = MainWindow.conn.prepareStatement(query);
					preparedStmt.setInt(1, Integer.parseInt(udfrm_ID.getText()));
					
					int dialogButton;
					dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
					
					if (dialogButton == JOptionPane.YES_OPTION) preparedStmt.execute();
					HospitalApp.docSearchFrame.setEnabled(true);
					HospitalApp.docUpdateFrame.setVisible(false);
					} catch (SQLException e6) {
						e6.printStackTrace();
					}
			} 
		});
		btnDelete.setBounds(137, 229, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE hospital.doctors set DOC_SNAME = ?, DOC_FNAME = ? WHERE DOC_ID = ?";
					PreparedStatement preparedStmt = MainWindow.conn.prepareStatement(query);
					preparedStmt.setString(1, udfrm_sname.getText());
					preparedStmt.setString(2, udfrm_fname.getText());
					preparedStmt.setInt(3, Integer.parseInt(udfrm_ID.getText()));
					
					preparedStmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Update Done", "UPDATE", JOptionPane.PLAIN_MESSAGE);
					preparedStmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(237, 229, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.docSearchFrame.setEnabled(true);
				HospitalApp.docUpdateFrame.setVisible(false);
			}
		});
		btnClose.setBounds(337, 229, 89, 23);
		contentPane.add(btnClose);
	}

}