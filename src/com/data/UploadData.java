package com.data;

import java.net.UnknownHostException;
import java.util.List;

import android.util.Log;

import com.mongodb.*;


public class UploadData {

	List<WordData> words;

	
	public UploadData(List<WordData> words) {
		
		
		super();
		this.words = words;
	
		
	}
	
	public void uploadDataToMongo() throws UnknownHostException, Exception{
		MongoClientURI uri = new MongoClientURI("mongodb://qs:qslab@ds033018.mongolab.com:33018/qslab");

		MongoClient mongo = new MongoClient(uri);
		DB dbm = mongo.getDB("qslab");
		boolean auth = dbm.authenticate("qs", "qslab".toCharArray());
		DBCollection table = dbm.getCollection("words");
		Log.d("soft_keyboard","accessed mongo DB");
		for(int i=0;i<this.words.size();i++){
			WordData word = this.words.get(i);
			BasicDBObject doc = new BasicDBObject();
			doc.put("timestamp",word.get_timestamp());
			doc.put("time_taken", word.get_time_taken());
			doc.put("total_keystrokes", word.get_total_keystrokes());
			doc.put("alphabetical_characters", word.get_alphabetical_characters());
			doc.put("numeric_characters", word.get_numeric_characters());
			doc.put("backspaces", word.get_backspaces());
			doc.put("word_separators", word.get_word_separators());
			doc.put("special_characters", word.get_special_characters());
			doc.put("uppercase", word.get_capslock_characters());
			doc.put("a_characters", word.get_a_characters() );
			doc.put("e_characters", word.get_e_characters() );
			doc.put("i_characters", word.get_i_characters() );
			doc.put("o_characters", word.get_o_characters() );
			doc.put("u_characters", word.get_u_characters() );
			doc.put("f_words", word.get_f_words());
			table.insert(doc);
			Log.d("soft_keyboard","Uploaded to mongo DB");
		}

		
	}
}
