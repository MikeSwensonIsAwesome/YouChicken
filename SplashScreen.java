package bombGame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;


import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = 8360160492152814755L;
	private JPanel contentPane;
	private CardLayout cardLayout = new CardLayout();
	private JPanel pnlManual = new Manual();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(cardLayout);
		setContentPane(contentPane);
		
		Sound.gameFx[1].loop();

		JPanel pnlWelcome = createWelcomePanel();
		contentPane.add(pnlWelcome, "Welcome Panel");
		contentPane.add(pnlManual, "Manual");
	}

	private JPanel createWelcomePanel() {
		JPanel pnlWelcome = new JPanel();
		pnlWelcome.setBackground(Color.BLACK);
		pnlWelcome.setLayout(new BorderLayout(0, 0));

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setIcon(new ImageIcon(SplashScreen.class.getResource("/bombGame/images/youChicken.png")));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		pnlWelcome.add(lblWelcome, BorderLayout.CENTER);

		JPanel pnlSelection = createControlPanel();
		pnlWelcome.add(pnlSelection, BorderLayout.SOUTH);

		return pnlWelcome;
	}

	private JPanel createControlPanel() {
		JPanel pnlControl = new JPanel();
		pnlControl.setBackground(Color.BLACK);
		pnlControl.setBorder(new EmptyBorder(0, 50, 50, 50));

		JButton btnManual = new JButton("");
		btnManual.setBorder(new LineBorder(Color.RED, 5));
		btnManual.setBackground(Color.BLACK);
		btnManual.setIcon(new ImageIcon(SplashScreen.class.getResource("/bombGame/images/manualButton.png")));
		btnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPane, "Manual");
			}
		});
		pnlControl.setLayout(new BorderLayout(0, 0));
		pnlControl.add(btnManual, BorderLayout.EAST);

		JButton btnBomb = new JButton("");
		btnBomb.setBorder(new LineBorder(Color.RED, 5));
		btnBomb.setBackground(Color.BLACK);
		btnBomb.setIcon(new ImageIcon(SplashScreen.class.getResource("/bombGame/images/bombButton.png")));
		btnBomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BombGUI bombGUI = new BombGUI();
				contentPane.add(bombGUI, "Bomb");
				cardLayout.show(contentPane, "Bomb");
			}
		});
		pnlControl.add(btnBomb, BorderLayout.WEST);

		JPanel pnlExit = new JPanel();
		pnlExit.setBorder(new EmptyBorder(20, 0, 0, 0));
		pnlExit.setBackground(Color.BLACK);
		pnlControl.add(pnlExit, BorderLayout.SOUTH);

		JButton btnExit = new JButton("");
		pnlExit.add(btnExit);
		btnExit.setBorder(new LineBorder(Color.RED, 5));
		btnExit.setBackground(Color.BLACK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread.currentThread();
				try {
					Sound.gameFx[8].play();
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println("Interrupted Exception on Splash Exit");
					e1.printStackTrace();
				}
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnExit.setBackground(Color.BLACK);
		btnExit.setIcon(new ImageIcon(SplashScreen.class.getResource("/bombGame/images/exitButton.png")));
		return pnlControl;
	}

}
