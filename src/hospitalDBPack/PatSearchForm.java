package hospitalDBPack;

/**
 * The user can search for any patients entry
 * using a portion or the full surname and also
 * can choose to insert a new entry
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
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatSearchForm extends JFrame {
	static String searchEpwnymoVar;
	

	private JPanel contentPane;
	private JTextField frm_SearchEpwnymo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatSearchForm frame = new PatSearchForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatSearchForm() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/Healthcare.png")));
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setTitle("Insert/Search Patients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_surname = new JLabel("Surname");
		lbl_surname.setForeground(new Color(153, 51, 0));
		lbl_surname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_surname.setBounds(170, 20, 85, 29);
		contentPane.add(lbl_surname);
		
		frm_SearchEpwnymo = new JTextField();
		frm_SearchEpwnymo.setBounds(124, 50, 180, 20);
		contentPane.add(frm_SearchEpwnymo);
		frm_SearchEpwnymo.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatSearchForm.searchEpwnymoVar = frm_SearchEpwnymo.getText();
				HospitalApp.patSearchFrame.setEnabled(false);
				HospitalApp.patUpdateFrame.setVisible(true);
			}
		});
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(150, 80, 135, 29);
		contentPane.add(btnSearch);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBounds(59, 11, 322, 125);
		contentPane.add(panel);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.patInsertFrame.setVisible(true);
				HospitalApp.patSearchFrame.setEnabled(false);
			}
		});
		btnInsert.setForeground(new Color(0, 0, 255));
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsert.setBounds(150, 159, 135, 29);
		contentPane.add(btnInsert);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(59, 143, 322, 62);
		contentPane.add(panel_1);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalApp.mainFrame.setEnabled(true);
				HospitalApp.patSearchFrame.setVisible(false);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClose.setBounds(292, 229, 89, 23);
		contentPane.add(btnClose);
	}

}

