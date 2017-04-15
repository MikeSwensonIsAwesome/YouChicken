package bombGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerClass extends JPanel implements ActionListener{
	private static final long serialVersionUID = -7249490075272358572L;
	private  static int i = 180;
	public final Timer timer = new Timer(1000, this);
	 @Override
	 public void actionPerformed(ActionEvent ev){
		final Timer timer = new Timer(1000, new ActionListener() {
		int i = 180;
		int switchChecker;
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String formatTime = null;
		    	if(MorseCode.morseCodeWinChecker 
    			&& SimonSays.simonCodeWinChecker){ //ADD MORE BOOLEANS AS YOU MAKE THEM
		    		switchChecker = 1;
		    	}
	    		switch(switchChecker){
	    			case 0:
				    	 if(i % 60 == 0){
				    		formatTime = String.valueOf(String.format("%2d : %d0", i / 60, i % 60));
				    		BombGUI.lblTime.setText(formatTime);
				    	}else if(i % 60 < 10){
				    		formatTime = String.valueOf(String.format("%2d : 0%d", i / 60, i % 60));
				    		BombGUI.lblTime.setText(formatTime);	
				    	}else{
				    		formatTime = String.valueOf(String.format("%2d : %d", i / 60, i % 60));
				    		BombGUI.lblTime.setText(formatTime);
				    	}
				    	--i;
				    	BombGUI.lblTime.repaint();
				    	if(i < 0){
				    		((Timer)e.getSource()).stop();
				    		Sound.CrescendoBeep.stop();
				    		Sound.Bok.play();
				    	}
				    	break;
				    	
	    			case 1: 
	    				try {
							youWin();
						} catch (InterruptedException e1) {
							Thread.currentThread().interrupt();
						}
	    				switchChecker = 2;
	    				break;	    				
	    			default:
	    				System.out.println("TimerClass Switch is broken");
		    		}
		    }
	    });
		Sound.CrescendoBeep.play();
		timer.start();
	}
	 //Timer isn't supposed to call itself to wait but this is the only place
	 //I could put it without scope issues so it always throws the exception
	 protected void youWin() throws InterruptedException {
		Sound.CrescendoBeep.stop();
		timer.stop();
		Sound.VictoryLoop.loop();
		TimerClass.this.wait();
	}
	 
	public void stopTimer(){
	 	timer.stop();
	}
	 
	 public static int getI() {
		return i;
	}
}