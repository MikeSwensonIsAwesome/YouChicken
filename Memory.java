package bombGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Memory extends JPanel {

	private static final long serialVersionUID = 2540622182205980303L;
	private int count = 1;
	private JLabel lblProgress = new JLabel("             ");
	private JLabel lblStage = new JLabel("Stage " + count);
	private JLabel lblDisplay = new JLabel("1");
	private boolean stageOne = true;
	private boolean stageTwo = false;
	private boolean stageThree = false;
	protected static volatile Boolean memoryWinChecker = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Memory frame = new Memory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Memory() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(475, 296));
		setLayout(new GridLayout(0, 2, 0, 0));
		setBounds(0, 0, 475, 296);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlDisplay = createDisplayPanel();
		add(pnlDisplay);

		JPanel pnlControl = createControlPanel();
		add(pnlControl, BorderLayout.SOUTH);

		JLabel lblProgress = createProgressLabel();
		add(lblProgress, BorderLayout.EAST);
	}

	private JPanel createDisplayPanel() {
		JPanel pnlDisplay = new JPanel();
		pnlDisplay.setLayout(new BorderLayout(0, 0));

		lblStage.setOpaque(true);
		lblStage.setHorizontalAlignment(SwingConstants.CENTER);
		lblStage.setForeground(Color.WHITE);
		lblStage.setFont(new Font("Courier New", Font.BOLD, 30));
		lblStage.setBackground(Color.BLACK);
		pnlDisplay.add(lblStage, BorderLayout.NORTH);

		pnlDisplay.add(lblDisplay, BorderLayout.CENTER);
		lblDisplay.setBorder(new EmptyBorder(3, 0, 3, 3));
		lblDisplay.setOpaque(true);
		lblDisplay.setBackground(Color.BLACK);
		lblDisplay.setForeground(Color.WHITE);
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplay.setFont(new Font("Courier New", Font.BOLD, 70));
		return pnlDisplay;
	}

	private JLabel createProgressLabel() {
		lblProgress.setBorder(new LineBorder(Color.WHITE, 3));
		lblProgress.setBackground(Color.GREEN);
		lblProgress.setOpaque(true);
		return lblProgress;
	}

	private JPanel createControlPanel() {
		JPanel pnlControl = new JPanel();
		pnlControl.setBorder(new EmptyBorder(5, 0, 0, 0));
		pnlControl.setLayout(new GridLayout(1, 0, 20, 0));

		JButton btnFirst = new JButton("1");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// stage 1
				if (stageOne == true) {
					wrongMove(true);
				}

				// stage 2
				if (stageTwo == true) {
					if (lblDisplay.getText().equals("3")) {
						lblDisplay.setText("1");
					} else {
						wrongMove(true);
					}
				}

				// stage 3
				if (stageThree == true) {
					if (lblDisplay.getText().equals("2")) {
						lblDisplay.setText("1");
					} else {
						wrongMove(true);
					}
				}

			}
		});
		btnFirst.setBorder(new EmptyBorder(5, 0, 0, 0));
		btnFirst.setFont(new Font("Courier New", Font.PLAIN, 30));
		pnlControl.add(btnFirst);

		JButton btnSecond = new JButton("3");
		btnSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// stage 1
				if (stageOne == true) {
					if (lblDisplay.getText().equals("2")) {
						lblDisplay.setText("3");
						nextStage();
						return;
					} else if (lblDisplay.getText().equals("1")) {
						lblDisplay.setText("3");
					} else {
						wrongMove(true);
					}
				}

				// stage 2
				if (stageTwo == true) {
					if (lblDisplay.getText().equals("2")) {
						lblDisplay.setText("3");
						nextStage();
						return;
					} else {
						wrongMove(true);
					}
				}

				// stage 3
				if (stageThree == true) {
					if (lblDisplay.getText().equals("1")) {
						lblDisplay.setText("3");
						win();
					} else {
						wrongMove(true);
					}
				}
			}
		});
		btnSecond.setBorder(new EmptyBorder(5, 0, 0, 0));
		btnSecond.setFont(new Font("Courier New", Font.PLAIN, 30));
		pnlControl.add(btnSecond);

		JButton btnThird = new JButton("4");
		btnThird.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// stage 1
				if (stageOne == true) {
					if (lblDisplay.getText().equals("3")) {
						lblDisplay.setText("4");
					} else {
						wrongMove(true);
					}
				}

				// stage 2
				if (stageTwo == true) {
					if (lblDisplay.getText().equals("1")) {
						lblDisplay.setText("4");
					} else {
						wrongMove(true);
					}
				}

				// stage 3
				if (stageThree == true) {
					if (lblDisplay.getText().equals("3")) {
						lblDisplay.setText("4");
					} else {
						wrongMove(true);
					}
				}
			}
		});
		btnThird.setBorder(new EmptyBorder(5, 0, 0, 0));
		btnThird.setFont(new Font("Courier New", Font.PLAIN, 30));
		pnlControl.add(btnThird);

		JButton btnFourth = new JButton("2");
		btnFourth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// stage 1
				if (stageOne == true) {
					if (lblDisplay.getText().equals("4")) {
						lblDisplay.setText("2");
					} else {
						wrongMove(true);
					}
				}

				// stage 2
				if (stageTwo == true) {
					if (lblDisplay.getText().equals("4")) {
						lblDisplay.setText("2");
					} else {
						wrongMove(true);
					}
				}

				// stage 3
				if (stageThree == true) {
					if (lblDisplay.getText().equals("4")) {
						lblDisplay.setText("2");
					} else {
						wrongMove(true);
					}
				}

			}
		});
		btnFourth.setBorder(new EmptyBorder(5, 0, 0, 0));
		btnFourth.setFont(new Font("Courier New", Font.PLAIN, 30));
		pnlControl.add(btnFourth);
		return pnlControl;
	}

	private void wrongMove(boolean wrongMove) {
		if (wrongMove == true) {
			lblProgress.setBackground(Color.RED);
			lblStage.setText("Stage 1");
			lblDisplay.setText("1");
			count = 1;
		}
	}

	private void nextStage() {
		++count;
		lblStage.setText("Stage " + count);
		lblProgress.setBackground(Color.GREEN);
		if (count == 1) {
			stageOne = true;
			stageTwo = false;
			stageThree = false;
		} else if (count == 2) {
			stageOne = false;
			stageTwo = true;
			stageThree = false;
		} else if (count == 3) {
			stageOne = false;
			stageTwo = false;
			stageThree = true;
		}

	}

	private void win() {
		lblStage.setText("Complete");
		lblDisplay.setText("   ");
		memoryWinChecker = true;

	}

}
