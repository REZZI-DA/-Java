package view.menue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AlienDefenceController;
import controller.GameController;
import model.Level;
import model.persistenceDB.PersistenceDB;
import toDo.User;
import view.game.GameGUI;

public class MainMenue extends JFrame {

	private static final long serialVersionUID = 1L;
	private AlienDefenceController alienDefenceController;
	private JPanel contentPane;
	private JTextField tfdLogin;
	private JPasswordField pfdPassword;
	@SuppressWarnings("rawtypes")
	private JComboBox cboLevelChooser;
	private List<Level> arrLevel;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainMenue(AlienDefenceController alienDefenceController) {

		this.alienDefenceController = alienDefenceController;

		// Allgemeine JFrame-Einstellungen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 195, 518);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// Überschrift
		JLabel lblHeadline = new JLabel("ALIEN DEFENCE");
		lblHeadline.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadline.setForeground(new Color(124, 252, 0));
		lblHeadline.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		contentPane.add(lblHeadline, BorderLayout.NORTH);

		// Alles unter dem Bild, Tabellenlayout mit einer Spalte
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBackground(Color.BLACK);
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new GridLayout(0, 1, 0, 0));

		// Nutzername
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setForeground(Color.orange);
		pnlButtons.add(lblLogin);

		tfdLogin = new JTextField();
		pnlButtons.add(tfdLogin);
		tfdLogin.setColumns(10);

		// Passwort
		JLabel lblPassword = new JLabel("Passwort:");
		lblPassword.setForeground(Color.orange);
		pnlButtons.add(lblPassword);

		pfdPassword = new JPasswordField();
		pnlButtons.add(pfdPassword);

		// Levelauswahl
		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setForeground(Color.orange);
		pnlButtons.add(lblLevel);

		//Levelnamen auslesen
		arrLevel = this.alienDefenceController.getLevelController().readAllLevels();
		String[] arrLevelNames = getLevelNames(arrLevel);
		
		//Combobox erstellen
		cboLevelChooser = new JComboBox(arrLevelNames);
		pnlButtons.add(cboLevelChooser);
		
		//Spiel starten
		//Die Textfelder werden ausgewertet und das Passwort validiert, dann wird das Spiel gestartet
		JButton btnSpielen = new JButton("Spielen");
		btnSpielen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSpielen_Clicked(alienDefenceController, arrLevel);
			}
		});
		pnlButtons.add(btnSpielen);
		
		JButton btnTesten = new JButton("Testen");
		btnTesten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Erstellt Modell von aktuellen Nutzer
				User user = new User(1, "test", "pass");

				Thread t = new Thread("GameThread") {

					@Override
					public void run() {

						GameController gameController = alienDefenceController.startGame(arrLevel.get(0), user);
						new GameGUI(gameController).start();
					}
				};
				t.start();
			}
		});
		pnlButtons.add(btnTesten);
		
		JButton btnHighscore = new JButton("Highscore");
		btnHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Highscore(alienDefenceController.getAttemptController(), arrLevel.get(cboLevelChooser.getSelectedIndex()));
			}
		});
		pnlButtons.add(btnHighscore);
		
		JButton btnLeveleditor = new JButton("Leveleditor");
		btnLeveleditor.setBackground(Color.ORANGE);
		btnLeveleditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LeveldesignWindow(alienDefenceController);
			}
		});
		pnlButtons.add(btnLeveleditor);
		
		JButton btnBeenden = new JButton("Beenden");
		btnBeenden.setBackground(Color.GRAY);
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pnlButtons.add(btnBeenden);

		//Logo		
		JPanel pnlLogo = new JPanel();
		pnlLogo.setBackground(Color.BLACK);
		contentPane.add(pnlLogo, BorderLayout.CENTER);

		JLabel lblLogo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("./pictures/logo.png").getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT));
		lblLogo.setIcon(imageIcon);
		pnlLogo.add(lblLogo);
	}

	private String[] getLevelNames(List<Level> arrLevel) {
		String[] arrLevelNames = new String[arrLevel.size()];

		for (int i = 0; i < arrLevel.size(); i++) {
			arrLevelNames[i] = arrLevel.get(i).getName(); // Array aus Arraylist erstellt
		}

		return arrLevelNames;
	}
	
	public void btnSpielen_Clicked(AlienDefenceController alienDefenceController, List<Level> arrLevel) {
		// User aus Datenbank holen
		//TODO Böser Verstoß gegen MVC - hier muss später nochmal nachgebessert werden
		User user = new PersistenceDB().getUserPersistance().readUser(tfdLogin.getText());

		// Spielstarten, wenn Nutzer existiert und Passwort übereinstimmt
		if (user != null && user.getPassword().equals(new String(pfdPassword.getPassword()))) {

			Thread t = new Thread("GameThread") {
				@Override
				public void run() {

					GameController gameController = alienDefenceController.startGame(arrLevel.get(cboLevelChooser.getSelectedIndex()), user);
					new GameGUI(gameController).start();
				}
			};
			t.start();
		} else {
			// Fehlermeldung - Zugangsdaten fehlerhaft
			JOptionPane.showMessageDialog(null, "Zugangsdaten nicht korrekt", "Fehler",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
