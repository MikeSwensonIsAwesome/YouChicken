package bombGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private Clip clip;	
	public static Sound[] gameFx = {new Sound("/bombGame/sounds/idkanymoret.wav"),       //0
									new Sound("/bombGame/sounds/SplashScreenMaybe.wav"), //1
									new Sound("/bombGame/sounds/Wrong Buzzer.wav"),      //2
									new Sound("/bombGame/sounds/beepHighest.wav"),       //3
									new Sound("/bombGame/sounds/BeepHigh.wav"),          //4
									new Sound("/bombGame/sounds/beepLow.wav"),           //5
									new Sound("/bombGame/sounds/beepLowest.wav"),        //6
									new Sound("/bombGame/sounds/blowUp.wav"),    		 //7
									new Sound("/bombGame/sounds/chicken-3.wav"),         //8
									new Sound("/bombGame/sounds/youFailed.wav"),         //9
									new Sound("/bombGame/sounds/VictoryLoop.wav"),       //10
									};
	
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