package bombGame;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class SimonSays extends JPanel {
	private static final long serialVersionUID = 9222211199639083764L;
	private StringBuilder patternChecker = new StringBuilder(8);
	private String stage1 = "RBGY";
	private String stage2 = "RRGG";
	private String stage3 = "BGRY";
	private JPanel soundSamplePanel;
	private JLabel winDisplay;
	protected static volatile Boolean simonCodeWinChecker = false;
	private JLabel lblCurrentSb;


	public SimonSays() {
		setSize(new Dimension(475, 296));
		setLayout(new GridLayout(0, 3, 0, 0));
		setBounds(0, 0, 475, 296);
		
		//First Panel
		JPanel btnPanel = new JPanel();
		createBtnSquares(btnPanel);
		btnPanel.setLayout(new GridLayout(4, 0, 0, 0));
		add(btnPanel);		
		
		//Middle Panel
		soundSamplePanel = new JPanel();
		soundSamplePanel.setLayout(new GridLayout(12, 0, 0, 0));
		createGridSoundButtons(soundSamplePanel);
		add(soundSamplePanel);
		
		JLabel lblPatternLabel = new JLabel("Pattern:");
		lblPatternLabel.setHorizontalAlignment(SwingConstants.CENTER);
		soundSamplePanel.add(lblPatternLabel);
		lblCurrentSb = new JLabel("");
		createLblSbDisplay();
		soundSamplePanel.add(lblCurrentSb);
			
		//Far Right Panel
		JPanel stagesPanel = new JPanel();
		createChkBoxesAndSubmit(stagesPanel);
		stagesPanel.setLayout(new GridLayout(2, 0, 0, 0));
		add(stagesPanel);		
	}

	private void createLblSbDisplay() {
		lblCurrentSb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentSb.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSb.setForeground(Color.RED);
	}

	private void createBtnSquares(JPanel btnPanel) {
		JButton btnGreen = new JButton("");
		btnGreen.setContentAreaFilled(false);
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					patternChecker.append("G");
					lblCurrentSb.setText(patternChecker.toString());
			}
		});
		btnGreen.setIcon(new ImageIcon(SimonSays.class.getResource("/bombGame/images/GreenSquareSimonSays66.png")));
		btnPanel.add(btnGreen);
		
		JButton btnYellow = new JButton("");
		btnYellow.setContentAreaFilled(false);
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patternChecker.append("Y");
				lblCurrentSb.setText(patternChecker.toString());
			}
		});
		btnYellow.setIcon(new ImageIcon(SimonSays.class.getResource("/bombGame/images/YellowSquareSimonSays66.png")));
		btnPanel.add(btnYellow);
		
		JButton btnBlue = new JButton("");
		btnBlue.setContentAreaFilled(false);
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patternChecker.append("B");
				lblCurrentSb.setText(patternChecker.toString());
			}
		});
		btnBlue.setIcon(new ImageIcon(SimonSays.class.getResource("/bombGame/images/BlueSquareSimonSays66.png")));
		btnPanel.add(btnBlue);
		
		JButton btnRed = new JButton("");
		btnRed.setContentAreaFilled(false);
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patternChecker.append("R");
				lblCurrentSb.setText(patternChecker.toString());
			}
		});
		btnRed.setIcon(new ImageIcon(SimonSays.class.getResource("/bombGame/images/RedSquareSimonSays66.png")));
		btnPanel.add(btnRed);
	}

	private void createChkBoxesAndSubmit(JPanel stagesPanel) {
		JPanel panelChkBoxes = new JPanel();
		stagesPanel.add(panelChkBoxes);
		panelChkBoxes.setLayout(new GridLayout(3, 0, 0, 0));
		
		JCheckBox chkBoxStage1 = new JCheckBox("Stage 1");
		panelChkBoxes.add(chkBoxStage1);
		createChkBoxStage1(chkBoxStage1);
		
		JCheckBox chkBoxStage2 = new JCheckBox("Stage 2");
		panelChkBoxes.add(chkBoxStage2);
		crateChkBoxStage2Format(chkBoxStage2);
		
		JCheckBox chkBoxStage3 = new JCheckBox("Stage 3");
		createChkBox3Format(chkBoxStage3);
		panelChkBoxes.add(chkBoxStage3);
		
		JButton buttonSubmitStage = new JButton("");
		buttonSubmitStage.setContentAreaFilled(false);
		buttonSubmitStage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check start state
				if(    !chkBoxStage1.isSelected()
					&& !chkBoxStage2.isSelected()
					&& !chkBoxStage3.isSelected()){
					//check Stage1 Guess
					if(stage1.equalsIgnoreCase(patternChecker.toString())){
						chkBoxStage1.setSelected(true);
					}else{
						Sound.Buzzer.play();
					}
				}
				//Check if Stage 1 is done
				else if(    chkBoxStage1.isSelected()
						&& !chkBoxStage2.isSelected()
						&& !chkBoxStage3.isSelected()){
					//Check Stage2 Guess
					if(stage2.equalsIgnoreCase(patternChecker.toString())){
						chkBoxStage2.setSelected(true);
					}else{
						Sound.Buzzer.play();
						System.out.println(patternChecker.toString() + "||" + stage2);
					}
				}
				else{
					if(stage3.equalsIgnoreCase(patternChecker.toString())){
						chkBoxStage3.setSelected(true);
						soundSamplePanel.setBackground(Color.GREEN);
						winDisplay.setText("You Got it!");
						simonCodeWinChecker = true;
						buttonSubmitStage.setEnabled(false);
					}else{
						Sound.Buzzer.play();
					}
				}
				patternChecker.setLength(0);
				lblCurrentSb.setText("");
			}
		});
		buttonSubmitStage.setIcon(new ImageIcon(SimonSays.class.getResource("/bombGame/images/BtnSubmit66.png")));
		stagesPanel.add(buttonSubmitStage);
	}

	private void createChkBox3Format(JCheckBox chkBoxStage3) {
		chkBoxStage3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chkBoxStage3.setHorizontalAlignment(SwingConstants.CENTER);
		chkBoxStage3.setEnabled(false);
	}

	private void crateChkBoxStage2Format(JCheckBox chkBoxStage2) {
		createChkBox3Format(chkBoxStage2);
	}

	private void createChkBoxStage1(JCheckBox chkBoxStage1) {
		createChkBox3Format(chkBoxStage1);
	}

	private void createGridSoundButtons(JPanel soundSamplePanel) {
		JButton btnGreenBeep = new JButton("Green Beep");
		btnGreenBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.HighestBeep.play();
			}
		});
		soundSamplePanel.add(btnGreenBeep);	
		createBufferSpaceTopMiddle(soundSamplePanel);
		
		JButton btnYellowBeep = new JButton("Yellow Beep");
		btnYellowBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.HighBeep.play();
			}
		});
		soundSamplePanel.add(btnYellowBeep);
		createBufferSpaceMiddleBot(soundSamplePanel);
		
		JButton btnBlueBeep = new JButton("Blue Beep");
		btnBlueBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.LowBeep.play();
			}
		});
		soundSamplePanel.add(btnBlueBeep);
		createBufferSpaceAndWinDisplay(soundSamplePanel);
		
		JButton btnRedBeep = new JButton("Red Beep");
		btnRedBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.LowestBeep.play();
			}
		});
		soundSamplePanel.add(btnRedBeep);
	}

	private void createBufferSpaceAndWinDisplay(JPanel soundSamplePanel) {
		winDisplay = new JLabel("");
		winDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		soundSamplePanel.add(winDisplay);	
		JLabel label_5 = new JLabel("");
		soundSamplePanel.add(label_5);
	}
	
	private void createBufferSpaceMiddleBot(JPanel soundSamplePanel) {
		createBufferSpaceAndWinDisplay(soundSamplePanel);
	}

	private void createBufferSpaceTopMiddle(JPanel soundSamplePanel) {
		createBufferSpaceAndWinDisplay(soundSamplePanel);
	}
}
