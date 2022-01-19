package hospitalDBPack;

/**
 * Implements a Hospital CRUD application which is connected to a database
 * for both the doctors and the patients of the hospital.
 * 
 * To login, userID ="aueb" and password ="1234"
 * 
 * @author I.Karadimas
 * @version 0.1
 */

import java.awt.EventQueue;

public class HospitalApp {
	static MainWindow mainFrame;
	static FrmDoctorsInsert docInsertFrame;
	static FrmDoctorsUpdate docUpdateFrame;
	static DocSearchForm docSearchFrame;
	static Version version;
	static FrmPatientsInsert patInsertFrame;
	static FrmPatientsUpdate patUpdateFrame;
	static PatSearchForm patSearchFrame;
	static LoginWindow loginFrame;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame = new LoginWindow();
					loginFrame.setLocationRelativeTo(null);
					loginFrame.setVisible(true);
					
					mainFrame = new MainWindow();
					mainFrame.setLocationRelativeTo(null);
					mainFrame.setVisible(false);
					
					docInsertFrame = new FrmDoctorsInsert();
					docInsertFrame.setLocationRelativeTo(null);
					docInsertFrame.setVisible(false);
					
					docUpdateFrame = new FrmDoctorsUpdate();
					docUpdateFrame.setLocationRelativeTo(null);
					docUpdateFrame.setVisible(false);
					
					docSearchFrame = new DocSearchForm();
					docSearchFrame.setLocationRelativeTo(null);
					docSearchFrame.setVisible(false);
					
					version = new Version();
					version.setLocationRelativeTo(null);
					version.setVisible(false);
					
					patInsertFrame = new FrmPatientsInsert();
					patInsertFrame.setLocationRelativeTo(null);
					patInsertFrame.setVisible(false);
					
					patUpdateFrame = new FrmPatientsUpdate();
					patUpdateFrame.setLocationRelativeTo(null);
					patUpdateFrame.setVisible(false);
					
					patSearchFrame = new PatSearchForm();
					patSearchFrame.setLocationRelativeTo(null);
					patSearchFrame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}