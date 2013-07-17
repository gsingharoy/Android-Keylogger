package com.data;

import java.util.List;

public class KeystrokeList {


	List<KeystrokeData> keystrokes; 
	
	public KeystrokeList(List<KeystrokeData> keystrokes) {
		super();
		this.keystrokes = keystrokes;
	}

	public void addKeystroke(KeystrokeData keystroke){
		this.keystrokes.add(keystroke);
	}
	
	public void saveKeystrokes(DatabaseHandler db){
		for(int i=0; i < this.keystrokes.size();i++ ){
			db.addKeystrokeData(this.keystrokes.get(i));
		}
	}
}
