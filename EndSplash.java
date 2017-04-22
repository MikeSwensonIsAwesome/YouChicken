package bombGame;

import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.BorderLayout;

public class EndSplash extends JPanel {
	//The only subtle thing about this class is The order in which the components are made has to end with lblSpiralBG
	private static final long serialVersionUID = 7077710723267094653L;
	public EndSplash() {
		setAlignmentY(0.0f);
		setAlignmentX(0.0f);
		setBorder(null);
		setLayout(null);
		setSize(new Dimension(1280, 720));
		
		JButton btnFlashingExit = new JButton("");
		btnFlashingExit.setBounds(775, 297, 300, 141);
		btnFlashingExit.setIcon(new ImageIcon(EndSplash.class.getResource("/bombGame/images/85kFJOOb9ddiE.gif")));
		btnFlashingExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(btnFlashingExit);
		
		JPanel panelHighScore = new JPanel();
		panelHighScore.setBounds(227, 279, 224, 211);
		add(panelHighScore);
		panelHighScore.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtPaneHighScoreHolder = new JTextPane();
		createTextPane(txtPaneHighScoreHolder);
		panelHighScore.add(txtPaneHighScoreHolder);
		
		JLabel lblThanksForPlaying = new JLabel("Thanks For Playing!!!");
		createLblThanks(lblThanksForPlaying);
		add(lblThanksForPlaying);
		
		JLabel lblHighScoreSign = new JLabel("Survivor's Times");
		createLblHighScoreSign(lblHighScoreSign);
		add(lblHighScoreSign);
		
		JLabel lblHighScore = new JLabel("Check The Top For Your Score!");
		createLblHighScore(lblHighScore);
		add(lblHighScore);
				
		JLabel lblSpiralBg = new JLabel("");
		lblSpiralBg.setIcon(new ImageIcon(EndSplash.class.getResource("/bombGame/images/EndGameSpiral.gif")));
		lblSpiralBg.setBounds(0, 0, 1280, 720);
		add(lblSpiralBg);
	}

	private void createTextPane(JTextPane txtPaneHighScoreHolder) {
		txtPaneHighScoreHolder.setSelectionColor(Color.RED);
		txtPaneHighScoreHolder.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPaneHighScoreHolder.setEditable(false);
		String line;
		StringBuilder sb = new StringBuilder(20);
		List<Integer> highScoreInputs = new ArrayList<>();
		List<Integer> reversedHighScore = new ArrayList<>();
		
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/bombGame/ioReadWrite/TimeRemaining.txt"), 
				 StandardCharsets.UTF_8));) {
			while((line = reader.readLine()) != null){
				line = line.trim();
				int i = Integer.parseInt(line);
				highScoreInputs.add(i);
			}
			reversedHighScore = arrayListReverse(highScoreInputs);
			
		for(Integer ele : reversedHighScore){ 
				sb.append(String.format("%2d : %02d", ele / 60, ele % 60)).append(System.getProperty("line.separator"));
				System.out.println(ele);
		}
		
		txtPaneHighScoreHolder.setText(sb.toString());
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createLblHighScoreSign(JLabel lblHighScoreSign) {
		lblHighScoreSign.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHighScoreSign.setOpaque(true);
		lblHighScoreSign.setBackground(Color.BLACK);
		lblHighScoreSign.setForeground(Color.WHITE);
		lblHighScoreSign.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScoreSign.setBounds(227, 252, 224, 28);
	}

	private void createLblHighScore(JLabel lblHighScore) {
		lblHighScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHighScore.setBackground(Color.BLACK);
		lblHighScore.setForeground(Color.WHITE);
		lblHighScore.setOpaque(true);
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setBounds(227, 490, 224, 28);
	}

	private void createLblThanks(JLabel lblThanksForPlaying) {
		lblThanksForPlaying.setBackground(Color.BLACK);
		lblThanksForPlaying.setOpaque(true);
		lblThanksForPlaying.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThanksForPlaying.setForeground(Color.WHITE);
		lblThanksForPlaying.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanksForPlaying.setBounds(775, 252, 302, 45);
	}

	public static <T> List<T> arrayListReverse(List<T> lst) {
		Collections.reverse(lst);
		return lst;
	}
}
