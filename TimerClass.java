package bombGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class TimerClass extends JPanel implements ActionListener{
	private static final long serialVersionUID = -7249490075272358572L;
	
	//Called by the GUI
	public final Timer timer = new Timer(1000, this);
	private static int i = 180;
	
	//Used to link field i, ActionListener i
	 public void setI(int i) {
		TimerClass.i = i;
	}
	@Override
	 public void actionPerformed(ActionEvent ev){
		final Timer timer = new Timer(1000, new ActionListener() {			
		int i = 20;

		int switchChecker;
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String formatTime = null;
		    	if(!MorseCode.morseCodeWinChecker 
    			&& !SimonSays.simonCodeWinChecker
    			&& !Switches.switchesWinChecker
    			&& !Memory.memoryWinChecker){ //ADD MORE BOOLEANS AS YOU MAKE THEM
		    		switchChecker = 1;
		    	}
	    		switch(switchChecker){
	    			//Case 0 General Game running display time/Blows up
	    			case 0:
			    		formatTime = String.valueOf(String.format("%2d : %02d", i / 60, i % 60));
			    		BombGUI.lblTime.setText(formatTime);
				    	--i;
				    	BombGUI.lblTime.repaint();
				    	setI(i);
				    	
				    	if(i == 9){
				    		Sound.gameFx[7].play();
				    	}
				    	if(i < 0){
				    		((Timer)e.getSource()).stop();
				    		Sound.gameFx[0].stop();
				    		Sound.gameFx[9].loop();
				    		try {
								createEndGameSplash();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
				    	}
				    	break;
				    //Case 1 Wins the game Check youWin() method, stops switch statement
	    			case 1: 
	    				try {
							youWin();
						} catch (InterruptedException e1) {
							Thread.currentThread().interrupt();
						}
	    				switchChecker = -1;
	    				break;	    				
	    			default:
	    				System.out.println("TimerClass Switch is broken");
		    		}
		    }
	    });
		Sound.gameFx[0].play();
		timer.start();
	}
	 //Timer isn't supposed to call itself to wait but this is the only place
	 //I could put it without scope issues so it always throws the exception
	 protected void youWin() throws InterruptedException {
		Sound.gameFx[0].stop();
		Sound.gameFx[7].stop();
		timer.stop();
		Sound.gameFx[10].loop();
		
		//Writes to TimeRemaining in the Format "3:00"\n
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream("/bombGame/ioReadWrite/TimeRemaining.txt", true), "UTF-8"));){
			writer.write(String.format("%d", i));
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			e.getCause();
		}
		createEndGameSplash();
		//End it all
		TimerClass.this.wait();
	}
	private void createEndGameSplash() throws InterruptedException {
		JWindow window = new JWindow();
		window.setBounds(500, 150, 300, 200);
		window.setVisible(true);
		
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(1280,720);
		frame.add(new EndSplash());
		frame.setVisible(true);

		window.dispose();
	}
	 
	public void stopTimer(){
	 	timer.stop();
	}
	 
	 public static int getI() {
		return i;
	}
}