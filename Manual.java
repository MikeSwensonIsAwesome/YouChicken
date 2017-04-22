package bombGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;

public class Manual extends JPanel {

	private static final long serialVersionUID = -5535083336845294175L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manual frame = new Manual();
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
	public Manual() {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JScrollPane scroll = createDisplayPanel();
		add(scroll, BorderLayout.CENTER);

		JPanel pnlExit = createExitPanel();
		add(pnlExit, BorderLayout.SOUTH);

	}

	private JPanel createExitPanel() {
		JPanel pnlExit = new JPanel();
		pnlExit.setBackground(Color.BLACK);

		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBorder(new LineBorder(Color.RED, 5));
		btnExit.setBackground(Color.BLACK);
		btnExit.setIcon(new ImageIcon(Manual.class.getResource("/bombGame/images/exitButtontTypeWriterFont.png")));
		pnlExit.add(btnExit);
		return pnlExit;
	}

	private JScrollPane createDisplayPanel() {
		JPanel pnlDisplay = new JPanel();
		pnlDisplay.setBorder(null);
		pnlDisplay.setBackground(Color.BLACK);
		JScrollPane scroll = new JScrollPane(pnlDisplay);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlDisplay.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = createTitlePanel();
		pnlDisplay.add(pnlTitle, BorderLayout.NORTH);

		JPanel pnlInstructions = new JPanel();
		createInstructionPanel(pnlInstructions);

		pnlDisplay.add(pnlInstructions);

		return scroll;

	}

	private void createInstructionPanel(JPanel pnlInstructions) {
		pnlInstructions.setBorder(new EmptyBorder(10, 25, 10, 25));
		pnlInstructions.setLayout(new GridLayout(2, 2, 25, 25));
		pnlInstructions.setBackground(Color.BLACK);

		JTextPane txtMemory = new JTextPane();
		pnlInstructions.add(txtMemory);
		txtMemory.setBorder(new LineBorder(Color.RED, 3));
		txtMemory.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtMemory.setForeground(Color.WHITE);
		txtMemory.setEditable(false);
		txtMemory.setBackground(Color.BLACK);
		try (Reader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/bombGame/text/MemoryInstructions.txt"), 
						 StandardCharsets.UTF_8));) {
			txtMemory.read(reader, txtMemory);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JTextPane txtSimonSays = new JTextPane();
		pnlInstructions.add(txtSimonSays);
		txtSimonSays.setBorder(new LineBorder(new Color(255, 0, 0), 3));
		txtSimonSays.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtSimonSays.setEditable(false);
		txtSimonSays.setForeground(Color.WHITE);
		txtSimonSays.setBackground(Color.BLACK);
		try (Reader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/bombGame/text/SimonSaysInstructions.txt"), 
						 StandardCharsets.UTF_8));) {
			txtSimonSays.read(reader, txtSimonSays);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(txtSimonSays.getText());
		JTextPane txtSwitches = new JTextPane();
		pnlInstructions.add(txtSwitches);
		txtSwitches.setBorder(new LineBorder(new Color(255, 0, 0), 3));
		txtSwitches.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtSwitches.setEditable(false);
		txtSwitches.setForeground(Color.WHITE);
		txtSwitches.setBackground(Color.BLACK);
		try (Reader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/bombGame/text/SwitchesInstructions.txt"), 
						 StandardCharsets.UTF_8));) {
			txtSwitches.read(reader, txtSwitches);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JTextPane txtMorse = new JTextPane();
		pnlInstructions.add(txtMorse);
		txtMorse.setBorder(new LineBorder(Color.RED, 3));
		txtMorse.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtMorse.setEditable(false);
		txtMorse.setForeground(Color.WHITE);
		txtMorse.setBackground(Color.BLACK);
		try (Reader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/bombGame/text/MorseCodeInstructions.txt"), 
						 StandardCharsets.UTF_8));) {
			txtMorse.read(reader, txtMorse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JPanel createTitlePanel() {
		JPanel pnlTitle = new JPanel();
		pnlTitle.setLayout(new BorderLayout(0, 0));
		pnlTitle.setBackground(Color.BLACK);

		JLabel lblTitle = new JLabel("BOMB DEFUSAL MANUAL");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Courier New", Font.BOLD, 84));
		pnlTitle.add(lblTitle, BorderLayout.NORTH);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setIcon(null);

		JLabel lblSubheading = new JLabel(
				"All the instructions you need are in this manual. \r\nRead quickly, your life depends on it.");
		lblSubheading.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubheading.setForeground(Color.WHITE);
		lblSubheading.setFont(new Font("Courier New", Font.PLAIN, 23));
		pnlTitle.add(lblSubheading, BorderLayout.CENTER);
		return pnlTitle;
	}
}
