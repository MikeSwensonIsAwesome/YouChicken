package bombGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private Clip clip;
	
	public static Sound DrumLoop = new Sound("/bombGame/sounds/DrumBeatMinus19DBLoop.wav");
	public static Sound CrescendoBeep = new Sound("/bombGame/sounds/idkanymoret.wav");
	public static Sound Buzzer = new Sound("/bombGame/sounds/Wrong Buzzer.wav");
	public static Sound HighestBeep = new Sound("/bombGame/sounds/beepHighest.wav");
	public static Sound HighBeep = new Sound("/bombGame/sounds/BeepHigh.wav");
	public static Sound LowBeep = new Sound("/bombGame/sounds/beepLow.wav");
	public static Sound LowestBeep = new Sound("/bombGame/sounds/beepLowest.wav");	
	public static Sound VictoryLoop = new Sound("/bombGame/sounds/VictoryLoop.wav");
	//For the lulz
	public static Sound Bok = new Sound("/bombGame/sounds/chicken-3.wav");

	
	public Sound (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isActive(){
		return clip.isActive();
	}
}
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
//
//public class Utils{
//	private Player player;
//	
//	public void playSong(){
//	    String path = BombGUI.class.getResource("/guiPractice/0SmokeOnTheWaterSample.mp3").getFile();
//	    File file = new File(path);
//		try(FileInputStream fis = new FileInputStream(file)){
//		    BufferedInputStream bis = new BufferedInputStream(fis);    
//		    player = new Player(bis);
//		    player.play();
//		 }catch(IOException | JavaLayerException | NullPointerException e){
//			 e.printStackTrace();
//		 }
//	}
//}
////import java.io.FileInputStream;
////import java.io.IOException;
////
////import javazoom.jl.decoder.JavaLayerException;
////import javazoom.jl.player.Player;
////
////public class Utils implements Runnable {
////	public static volatile boolean flag = true;
////	private Player player;
////	
////	Thread audioPlayer = new Thread(this);
////
////	public void playSong() throws JavaLayerException{
////	    String path = BombGUI.class.getResource("/guiPractice/0SmokeOnTheWaterSample.mp3").getFile();
////	    File file = new File(path);
////		try(FileInputStream fis = new FileInputStream(file)){
////		    BufferedInputStream bis = new BufferedInputStream(fis);    
////		    player = new Player(bis);
////		    player.play();
////		 }catch(IOException | JavaLayerException | NullPointerException e){
////			 e.printStackTrace();
////		 }
////	       
////
////	}
////	public void run(){
////		try {
////			this.finalize();
////		} catch (Throwable e) {
////			e.printStackTrace();
////		}
////	}
////}
