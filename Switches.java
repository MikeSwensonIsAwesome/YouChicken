package bombGame;

import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Switches extends JPanel {

	private static final long serialVersionUID = -6147859146160850260L;
	protected static volatile Boolean switchesWinChecker = false;

	private ImageIcon[] switchImages = { (new ImageIcon(Switches.class.getResource("/bombGame/images/up.png"))),
			(new ImageIcon(Switches.class.getResource("/bombGame/images/down.png"))),
			(new ImageIcon(Switches.class.getResource("/bombGame/images/right.png"))),
			(new ImageIcon(Switches.class.getResource("/bombGame/images/left.png"))) };

	private Random rand = new Random();

	private Boolean isCorrectOne = false;
	private Boolean isCorrectTwo = false;
	private Boolean isCorrectThree = false;
	private Boolean isCorrectFour = false;

	private JLabel lbl1 = new JLabel("1");
	private JButton btn1 = new JButton("");
	private JLabel lbl2 = new JLabel("2");
	private JButton btn2 = new JButton("");
	private JLabel lbl3 = new JLabel("3");
	private JButton btn3 = new JButton("");
	private JLabel lbl4 = new JLabel("4");
	private JButton btn4 = new JButton("");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Switches frame = new Switches();
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
	public Switches() {
		setSize(new Dimension(475, 296));
		setLayout(new GridLayout(0, 2, 0, 0));
		setBounds(0, 0, 475, 296);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		createButtonLabel1(panel_1);
		createButtonLabel3(panel_1);

		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		createButtonLabel2(panel_2);
		createButtonLabel4(panel_2);

	}

	private void createButtonLabel1(JPanel panel_1) {
		panel_1.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1.setIcon(switchImages[rand.nextInt(switchImages.length)]);
				// if switched to the right
				if (btn1.getIcon().equals(switchImages[2])) {
					isCorrectOne = true;
					checkWin();
				} else {
					isCorrectOne = false;
				}
			}
		});
		btn1.setBackground(Color.BLACK);
		btn1.setIcon(new ImageIcon(Switches.class.getResource("/bombGame/images/up.png")));

		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Courier New", Font.BOLD, 44));
		panel_1.add(lbl1);
	}

	private void createButtonLabel2(JPanel panel_2) {
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setFont(new Font("Courier New", Font.BOLD, 44));
		panel_2.add(lbl2);

		panel_2.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn2.setIcon(switchImages[rand.nextInt(switchImages.length)]);
				// if switched down
				if (btn2.getIcon().equals(switchImages[1])) {
					isCorrectTwo = true;
					checkWin();
				} else {
					isCorrectTwo = false;
				}
			}
		});
		btn2.setBackground(Color.BLACK);
		btn2.setIcon(new ImageIcon(Switches.class.getResource("/bombGame/images/up.png")));
	}

	private void createButtonLabel3(JPanel panel_1) {
		panel_1.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn3.setIcon(switchImages[rand.nextInt(switchImages.length)]);
				// if switched to the right
				if (btn3.getIcon().equals(switchImages[2])) {
					isCorrectThree = true;
					checkWin();
				} else {
					isCorrectThree = false;
				}
			}
		});
		btn3.setBackground(Color.BLACK);
		btn3.setIcon(new ImageIcon(Switches.class.getResource("/bombGame/images/left.png")));

		panel_1.add(lbl3);
		lbl3.setFont(new Font("Courier New", Font.BOLD, 44));
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lbl3);
	}

	private void createButtonLabel4(JPanel panel_2) {
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setFont(new Font("Courier New", Font.BOLD, 44));
		panel_2.add(lbl4);

		panel_2.add(btn4);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn4.setIcon(switchImages[rand.nextInt(switchImages.length)]);
				// if switched down
				if (btn4.getIcon().equals(switchImages[1])) {
					isCorrectFour = true;
					checkWin();
				} else {
					isCorrectFour = false;
				}
			}
		});
		btn4.setBackground(Color.BLACK);
		btn4.setIcon(new ImageIcon(Switches.class.getResource("/bombGame/images/right.png")));
	}

	private void checkWin() {
		if (isCorrectOne == true && isCorrectTwo == true && isCorrectThree == true && isCorrectFour == true) {
			btn1.setBackground(Color.GREEN);
			btn3.setBackground(Color.GREEN);
			btn2.setBackground(Color.GREEN);
			btn4.setBackground(Color.GREEN);
			switchesWinChecker = true;
			
		}
	}
}
