package bombGame;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class MorseCode extends JPanel {
	private static final long serialVersionUID = -1276379210635329571L;
	
	//Might need to be synchronized not volatile IDK
	protected static volatile Boolean morseCodeWinChecker = false;
	private StringBuilder userInput = new StringBuilder(20);
	
	//Months of Year JFMAMJJA
	String morseCodeQuestion = ".--- ..-. -- .- -- .--- .--- .-";
	//SON 
	String morseCodeAnswer = "... --- -.";
	public MorseCode() {
		setSize(new Dimension(475, 296));
		setLayout(new GridLayout(3, 0, 0, 0));
		setBounds(0, 0, 475, 296);
		
		//Top Grid
		JLabel lblAnswer = new JLabel();
		createLblAnswer(lblAnswer);
		add(lblAnswer);
		
		//Middle Grid
		JPanel panelDotDashSubmit = new JPanel();
		panelDotDashSubmit.setBackground(Color.DARK_GRAY);
		createPanelDotDashSubmit(panelDotDashSubmit);
		createButtons(lblAnswer, panelDotDashSubmit);
		add(panelDotDashSubmit);
				
		//Bottom Grid
		JTextArea lblCodeQuestion = new JTextArea("");
		lblCodeQuestion.setForeground(new Color(128, 0, 0));
		lblCodeQuestion.setWrapStyleWord(true);
		createLblCodeQuestion(lblCodeQuestion);
		add(lblCodeQuestion);
	}

	private void createButtons(JLabel lblAnswer, JPanel panelDotDashSubmit) {
		//Middle Panel Visual Stuff Check btn***Function for what it does
		JButton btnDot = new JButton("");
		btnDot.setIcon(new ImageIcon(MorseCode.class.getResource("/bombGame/images/BtnDot66.png")));
		btnDot.setContentAreaFilled(false);
		btnDotFunction(lblAnswer, btnDot);
		panelDotDashSubmit.add(btnDot);
		
		JButton btnDash = new JButton("");
		btnDash.setIcon(new ImageIcon(MorseCode.class.getResource("/bombGame/images/BtnDash66.png")));
		btnDash.setContentAreaFilled(false);
		btnDashFunction(lblAnswer, btnDash);
		panelDotDashSubmit.add(btnDash);
		
		JButton btnSpace = new JButton("");
		btnSpace.setIcon(new ImageIcon(MorseCode.class.getResource("/bombGame/images/BtnSpace66.png")));
		btnSpace.setContentAreaFilled(false);
		bntDeleteFunction(lblAnswer, btnSpace);
		panelDotDashSubmit.add(btnSpace);
		
		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(MorseCode.class.getResource("/bombGame/images/BtnDelete66.png")));
		btnDelete.setContentAreaFilled(false);
		btnDeleteFunction(lblAnswer, btnDelete);
		panelDotDashSubmit.add(btnDelete);
		
		JButton btnSubmit = new JButton("");
		btnSubmit.setIcon(new ImageIcon(MorseCode.class.getResource("/bombGame/images/BtnSubmit66.png")));
		btnSubmit.setContentAreaFilled(false);
		btnSubmitFunction(lblAnswer, btnSubmit);
		panelDotDashSubmit.add(btnSubmit);
	}

	private void createLblCodeQuestion(JTextArea lblCodeQuestion) {
		lblCodeQuestion.setBorder(new EmptyBorder(40, 0, 0, 0));
		lblCodeQuestion.setLineWrap(true);
		lblCodeQuestion.setFont(new Font("Monospaced", Font.BOLD, 24));
		lblCodeQuestion.setOpaque(true);
		lblCodeQuestion.setBackground(new Color(91, 92, 96));
		lblCodeQuestion.setText(morseCodeQuestion);
		lblCodeQuestion.setEditable(false);
	}
	
	//Adds Dot and displays
	private void btnDotFunction(JLabel lblAnswer, JButton btnDot) {
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userInput.append(".");
				lblAnswer.setText(userInput.toString());
			}
		});
	}
	
	//Adds Dash and displays
	private void btnDashFunction(JLabel lblAnswer, JButton btnDash) {
		btnDash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ascii 196 not supported
				userInput.append("-");
				lblAnswer.setText(userInput.toString());
			}
		});
	}

	private void bntDeleteFunction(JLabel lblAnswer, JButton btnSpace) {
		btnSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInput.append(" ");
				lblAnswer.setText(userInput.toString());
			}
		});
	}

	private void btnDeleteFunction(JLabel lblAnswer, JButton btnDelete) {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userInput.length() > 0){
					userInput.deleteCharAt(userInput.length() - 1);
					lblAnswer.setText(userInput.toString());
				}
			}
		});
	}
	
	//Checks StringBuilder vs Answer String, On Wrong resets StringBuilder
	private void btnSubmitFunction(JLabel lblAnswer, JButton btnSubmit) {
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userInput.toString().equalsIgnoreCase(morseCodeAnswer)){
					lblAnswer.setText("You got it");
					lblAnswer.setBackground(Color.GREEN);
					morseCodeWinChecker = true;
					btnSubmit.setEnabled(false);
				}
				else{
					lblAnswer.setText("");
					userInput.setLength(0);
					Sound.gameFx[2].play();
				}
			}
		});
	}

	private void createPanelDotDashSubmit(JPanel panelDotDashSubmit) {
		panelDotDashSubmit.setBorder(new MatteBorder(7, 7, 7, 7, (Color) new Color(0, 0, 0)));
		panelDotDashSubmit.setSize(new Dimension(641, 453));
		panelDotDashSubmit.setLayout(new GridLayout(0, 5, 0, 0));
	}

	private void createLblAnswer(JLabel lblAnswer) {
		lblAnswer.setOpaque(true);
		lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAnswer.setBackground(new Color(91, 92, 96));
	}
	
	public Boolean getMorseCodeWinChecker() {
		return morseCodeWinChecker;
	}
}
