package bombGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BombGUI extends JPanel {
	private static final long serialVersionUID = 4094206125518620500L;
	protected static JLabel lblTime;
	private JPanel gameDecorator_1;
	private JPanel decoratorLeft_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BombGUI frame = new BombGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BombGUI() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(MAXIMIZED_BOTH);
		// setUndecorated(true);
		setBounds(100, 100, 800, 800);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.BLACK);
		setBackground(Color.LIGHT_GRAY);

		createTimerAndStart();
		Sound.gameFx[1].stop();
		Sound.gameFx[0].play();

		JPanel timerPanel = new JPanel();
		JLabel lblModelNumber = createLblModelNumber();
		timerPanel.add(lblModelNumber);
		createTimerPanel(timerPanel);
		add(timerPanel, BorderLayout.NORTH);

		JPanel panelIsolateExit = new JPanel();
		createPanelIsolateExit(panelIsolateExit);
		timerPanel.add(panelIsolateExit);

		JButton btnExit = new JButton("EXIT");
		btnExitFormat(panelIsolateExit, btnExit);
		createBtnExit(btnExit);

		// Frame around Game area
		JPanel gameDecorator = createGameDecorator();

		JPanel decoratorLeft = createDecoratorLeft();
		createDecoratorLeft(decoratorLeft);
		gameDecorator.add(decoratorLeft, BorderLayout.WEST);

		JPanel decoratorRight = createDecoratorRight();
		createDifficultyMeter(decoratorRight);
		gameDecorator.add(decoratorRight, BorderLayout.EAST);

		// Location of Games
		JPanel panelGameHolder = panelGameHolder();
		gameDecorator.add(panelGameHolder, BorderLayout.CENTER);

		JPanel panelSouth = createPanelSouth();
		this.add(panelSouth, BorderLayout.SOUTH);
	}

	private void btnExitFormat(JPanel panelIsolateExit, JButton btnExit) {
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.anchor = GridBagConstraints.EAST;
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 0;
		panelIsolateExit.add(btnExit, gbc_btnExit);
	}

	private void createPanelIsolateExit(JPanel panelIsolateExit) {
		panelIsolateExit.setOpaque(false);
		GridBagLayout gbl_panelIsolateExit = new GridBagLayout();
		gbl_panelIsolateExit.columnWidths = new int[] { 97, 69, 0, 0, 0 };
		gbl_panelIsolateExit.rowHeights = new int[] { 25, 0 };
		gbl_panelIsolateExit.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelIsolateExit.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelIsolateExit.setLayout(gbl_panelIsolateExit);
	}

	private JPanel createPanelSouth() {
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(SystemColor.windowText);
		panelSouth.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblSpaceLeft = new JLabel("");
		lblSpaceLeft.setBorder(new EmptyBorder(5, 40, 2, 0));
		lblSpaceLeft.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/waveform1270.gif")));
		lblSpaceLeft.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				System.out.println("South Label Size: " + e.getComponent().getSize());
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});
		panelSouth.add(lblSpaceLeft);
		return panelSouth;
	}

	private JPanel createDecoratorRight() {
		JPanel decoratorRight = new JPanel();
		decoratorRight.setBorder(new EmptyBorder(0, 10, 0, 10));
		decoratorRight.setOpaque(false);
		decoratorRight.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel lblDifficultyMeter = new JLabel("");
		createMeter(lblDifficultyMeter);
		decoratorRight.add(lblDifficultyMeter);

		JPanel panel2Lights1Switch = new JPanel();
		panel2Lights1Switch.setOpaque(false);
		decoratorRight.add(panel2Lights1Switch);
		create2LightsRightSwitch(panel2Lights1Switch);
		panel2Lights1Switch.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblRightSwitch = new JLabel("");
		lblRightSwitch.setVerticalAlignment(SwingConstants.TOP);
		lblRightSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightSwitch.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/SwitchRight.png")));
		panel2Lights1Switch.add(lblRightSwitch);

		JPanel panelOffRed = new JPanel();
		panelOffRed.setOpaque(false);
		decoratorRight.add(panelOffRed);
		panelOffRed.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel lblLightOffbyON = new JLabel("");
		lblLightOffbyON.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightOffbyON.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/OffRedLetters.png")));
		panelOffRed.add(lblLightOffbyON);

		JLabel lblRedOff = new JLabel("");
		lblRedOff.setHorizontalAlignment(SwingConstants.CENTER);
		lblRedOff.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/switchLeft.png")));
		panelOffRed.add(lblRedOff);
		return decoratorRight;
	}

	private void create2LightsRightSwitch(JPanel panel2Lights1Switch) {
		JLabel lblLightOn = new JLabel("");
		lblLightOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightOn.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/LightIndicatorOff.png")));
		panel2Lights1Switch.add(lblLightOn);

		JLabel lblLightOff = new JLabel("");
		lblLightOff.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightOff.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/LightIndicatorOff.png")));
		panel2Lights1Switch.add(lblLightOff);

		JLabel lblBlank = new JLabel("");
		panel2Lights1Switch.add(lblBlank);
	}

	private void createMeter(JLabel lblDifficultyMeter) {
		lblDifficultyMeter.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/MeterSmall.png")));
		lblDifficultyMeter.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficultyMeter.setIconTextGap(0);
	}

	private void createDifficultyMeter(JPanel decoratorRight) {
	}

	private JPanel panelGameHolder() {
		JPanel panelGameHolder = new JPanel();
		panelGameHolder.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelGameHolder.setOpaque(false);
		panelGameHolder.setLayout(new GridLayout(2, 2, 0, 0));
		createGameQuadrants(panelGameHolder);
		return panelGameHolder;
	}

	private void createGameQuadrants(JPanel panelGameHolder) {
		JPanel panelMorseCode = new JPanel();
		panelMorseCode.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(139, 0, 0),
				new Color(128, 0, 0), new Color(255, 0, 0)));
		panelGameHolder.add(panelMorseCode);
		panelMorseCode.setLayout(new BorderLayout(0, 0));
		MorseCode morseCodeGame = new MorseCode();
		panelMorseCode.add(morseCodeGame);

		JPanel panelSimonSays = new JPanel();
		panelSimonSays.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(139, 0, 0),
				new Color(128, 0, 0), new Color(255, 0, 0)));
		panelGameHolder.add(panelSimonSays);
		panelSimonSays.setLayout(new BorderLayout(0, 0));
		SimonSays SimonSaysGame = new SimonSays();
		panelSimonSays.add(SimonSaysGame);

		JPanel panelSwitches = new JPanel();
		panelSwitches.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(139, 0, 0),
				new Color(128, 0, 0), new Color(255, 0, 0)));
		panelGameHolder.add(panelSwitches);
		panelSwitches.setLayout(new BorderLayout(0, 0));
		Switches switchesGame = new Switches();
		panelSwitches.add(switchesGame);

		JPanel panelMemory = new JPanel();
		panelMemory.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(139, 0, 0),
				new Color(128, 0, 0), new Color(255, 0, 0)));
		panelMemory.setLayout(new BorderLayout(0, 0));
		Memory memoryGame = new Memory();
		panelGameHolder.add(memoryGame);
	}

	private void createDecoratorLeft(JPanel decoratorLeft) {

		JLabel lblMotorButtonImg = new JLabel("");
		lblMotorButtonImg.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/Motor.png")));
		decoratorLeft.add(lblMotorButtonImg);

		JLabel lblBloodyAbort = new JLabel("");
		lblBloodyAbort.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/BloodyAbort.png")));
		decoratorLeft_1.add(lblBloodyAbort);

		JLabel lblOffAutoButtonImg = new JLabel("");
		lblOffAutoButtonImg.setIcon(new ImageIcon(BombGUI.class.getResource("/bombGame/images/offauto.png")));
		decoratorLeft.add(lblOffAutoButtonImg);
	}

	private JPanel createDecoratorLeft() {
		decoratorLeft_1 = new JPanel();
		decoratorLeft_1.setBorder(new EmptyBorder(0, 10, 0, 10));
		decoratorLeft_1.setOpaque(false);
		decoratorLeft_1.setLayout(new GridLayout(4, 1, 0, 0));
		return decoratorLeft_1;
	}

	private JPanel createGameDecorator() {
		gameDecorator_1 = new JPanel();
		gameDecorator_1.setBorder(new EmptyBorder(0, 20, 0, 20));
		gameDecorator_1.setLayout(new BorderLayout(0, 0));
		gameDecorator_1.setBackground(Color.DARK_GRAY);
		add(gameDecorator_1, BorderLayout.CENTER);
		return gameDecorator_1;
	}

	private JLabel createLblModelNumber() {
		JLabel lblModelNumber = new JLabel("SN 1E5279");
		lblModelNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModelNumber.setForeground(Color.WHITE);
		lblModelNumber.setBorder(new EmptyBorder(15, 0, 0, 0));
		lblModelNumber.setVerticalAlignment(SwingConstants.TOP);
		lblModelNumber.setHorizontalAlignment(SwingConstants.CENTER);
		return lblModelNumber;
	}

	private void createTimerAndStart() {
		TimerClass tc = new TimerClass();
		Timer timer = new Timer(0, tc);
		timer.setRepeats(false);
		timer.start();
	}

	private void createTimerPanel(JPanel timerPanel) {
		timerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		timerPanel.setBackground(Color.DARK_GRAY);
		timerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

		int i = TimerClass.getI();
		lblTime = new JLabel(String.format("%2d : %d0", i / 60, i % 60));
		createLblTime();
		timerPanel.add(lblTime);
	}

	private void createBtnExit(JButton btnExit) {
		btnExit.setOpaque(false);
		btnExit.setBackground(Color.BLACK);
		btnExit.setContentAreaFilled(false);
		btnExit.setForeground(new Color(220, 20, 60));
		btnExit.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 14));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sound.gameFx[8].play();
				try {
					Thread.currentThread();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Interrupted Exception on Exit");
				}
				System.exit(0);
			}
		});
	}

	private void createLblTime() {
		lblTime.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(192, 192, 192), Color.WHITE,
				new Color(192, 192, 192), null));
		lblTime.setBackground(Color.BLACK);
		lblTime.setOpaque(true);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTime.setForeground(Color.RED);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
