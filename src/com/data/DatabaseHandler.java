package com.data;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	
	// Database Name
	private static final String DATABASE_NAME = "keystrokesDB";
	
	// Keystrokes table name
	private static final String TABLE_KEYSTROKES = "keystrokes";
	//analyzed words table
	private static final String TABLE_WORDS = "words";
	// Keystrokes Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_KEY = "key";
	private static final String KEY_TIMESTAMP = "timestamp";
	//analyzed words table columns
	private static final String WORD_ID = "id";
	private static final String WORD_ALPHABETICAL_CHARACTERS = "alphabetical_characters";
	private static final String WORD_NUMERIC_CHARACTERS = "numeric_characters" ;
	private static final String WORD_WORD_SEPARATORS = "word_separators";
	private static final String WORD_BACKSPACES = "backspaces";
	private static final String WORD_CAPSLOCK_CHARACTERS = "capslock_characters";
	private static final String WORD_SPECIAL_CHARACTERS = "special_characters";
	private static final String WORD_TOTAL_KEYSTROKES = "total_keystrokes";
	private static final String WORD_TIMESTAMP = "timestamp";
	private static final String WORD_TIME_TAKEN = "time_taken";
	private static final String WORD_A_CHARACTERS = "a_characters";
	private static final String WORD_E_CHARACTERS = "e_characters";
	private static final String WORD_I_CHARACTERS = "i_characters";
	private static final String WORD_O_CHARACTERS = "o_characters";
	private static final String WORD_U_CHARACTERS = "u_characters";
	private static final String WORD_F_WORDS = "f_words";

	
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createKeystrokesTable(db);
		createWordsTable(db);
		
		
	}
	public void createKeystrokesTable(SQLiteDatabase db){
		String CREATE_KEYSTROKES_TABLE = "CREATE TABLE " + TABLE_KEYSTROKES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_KEY + " TEXT,"
				+ KEY_TIMESTAMP + " TEXT" + ")";
		db.execSQL(CREATE_KEYSTROKES_TABLE);
		Log.d("soft_keyboard", "Create table "+TABLE_KEYSTROKES);
	}
	public void createWordsTable(SQLiteDatabase db){
		String CREATE_WORDS_TABLE = "CREATE TABLE "+TABLE_WORDS+"("
				+ WORD_ID + " INTEGER PRIMARY KEY, "
				+ WORD_ALPHABETICAL_CHARACTERS + " INTEGER, "
				+ WORD_NUMERIC_CHARACTERS + " INTEGER, "
				+ WORD_WORD_SEPARATORS + " INTEGER, "
				+ WORD_BACKSPACES + " INTEGER, "
				+ WORD_CAPSLOCK_CHARACTERS + " INTEGER, "
				+ WORD_SPECIAL_CHARACTERS + " INTEGER, "
				+ WORD_TOTAL_KEYSTROKES + " INTEGER, "
				+ WORD_TIMESTAMP + " TEXT, "
				+ WORD_TIME_TAKEN + " TEXT, "
				+ WORD_A_CHARACTERS + " INTEGER, "
				+ WORD_E_CHARACTERS + " INTEGER, "
				+ WORD_I_CHARACTERS + " INTEGER, "
				+ WORD_O_CHARACTERS + " INTEGER, "
				+ WORD_U_CHARACTERS + " INTEGER, "
				+ WORD_F_WORDS + " INTEGER "
				+")";
		db.execSQL(CREATE_WORDS_TABLE);
		Log.d("soft_keyboard","Create table "+TABLE_WORDS);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		dropKeytrokesTable(db);
		dropWordsTable(db);
		// Create tables again
		onCreate(db);
		
	}
	public void dropKeytrokesTable(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEYSTROKES);
		Log.d("soft_keyboard", "Delete table "+TABLE_KEYSTROKES);
	}
	
	public void dropWordsTable(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
		Log.d("soft_keyboard", "Delete table "+TABLE_WORDS);
	}
	/****************
	 * All CRUD(Create, Read, Update, Delete) Operations
	 ****************/
	
	public //Adding a KeystrokeData
	
	void addKeystrokeData(KeystrokeData keystrokeData) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_KEY, keystrokeData.get_key()); // Keystroke key
		values.put(KEY_TIMESTAMP, keystrokeData.get_timestamp()); // Keystroke timestamp

		// Inserting Row
		db.insert(TABLE_KEYSTROKES, null, values);
		
		//db.close(); // Closing database connection
		Log.d("soft_keyboard", "Store keystroke "+keystrokeData.get_key()+" at timestamp "+keystrokeData.get_timestamp());
	}
	
	void addWordData(WordData wordData){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(WORD_ALPHABETICAL_CHARACTERS, wordData.get_alphabetical_characters());
		values.put(WORD_NUMERIC_CHARACTERS, wordData.get_numeric_characters());
		values.put(WORD_WORD_SEPARATORS, wordData.get_word_separators());
		values.put(WORD_BACKSPACES, wordData.get_backspaces());
		values.put(WORD_CAPSLOCK_CHARACTERS, wordData.get_capslock_characters());
		values.put(WORD_SPECIAL_CHARACTERS, wordData.get_special_characters());
		values.put(WORD_TOTAL_KEYSTROKES, wordData.get_total_keystrokes());
		values.put(WORD_TIMESTAMP, wordData.get_timestamp().toString());
		values.put(WORD_TIME_TAKEN, wordData.get_time_taken());
		values.put(WORD_A_CHARACTERS, wordData.get_a_characters());
		values.put(WORD_E_CHARACTERS, wordData.get_e_characters());
		values.put(WORD_I_CHARACTERS, wordData.get_i_characters());
		values.put(WORD_O_CHARACTERS, wordData.get_o_characters());
		values.put(WORD_U_CHARACTERS, wordData.get_u_characters());
		values.put(WORD_F_WORDS, wordData.get_f_words());

		// Inserting Row
		db.insert(TABLE_WORDS, null, values);
		
		//db.close();// Closing database connection
		Log.d("soft_keyboard", "Stored word analysis : "+wordData.toString());
		
	}
	
	// Getting single Keystroke
	KeystrokeData getKeystrokeData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_KEYSTROKES, new String[] { KEY_ID,
				KEY_KEY, KEY_TIMESTAMP }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		KeystrokeData keystrokeData = new KeystrokeData(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return contact
		return keystrokeData;
	}
	
	public void analyzeKeystrokes() throws Exception{
		List<KeystrokeData> keystrokes = getAllKeystrokeData();
		if(keystrokes.size() > 0){
		WordData wordData = new WordData();
		Boolean is_f = false;
		Boolean is_u = false;
		Boolean is_c = false;
		for(int i=0;i<keystrokes.size();i++){
				KeystrokeData curr = keystrokes.get(i);
				wordData.add_total_keystroke();
				if(i==0){
					wordData.set_timestamp(curr.get_timestamp());
				}
				else if(i==keystrokes.size()-1){
					wordData.generate_time_taken(curr.get_timestamp());
				}

					int curr_key = Integer.parseInt(curr.get_key());
					if(curr.get_key().equals("-5") ){
						wordData.add_backspace();
					}
					else if(curr_key == 32 || curr_key == 10){
						wordData.add_word_separator();
					}
					else if(curr.get_key().equals("-1")){
						wordData.add_capslock_character();
					}
					else if(curr_key >= 97 && curr_key <=122){
						wordData.add_alphabetical_character();
						if(curr_key == 97)
						{
							wordData.add_a_character();
						}
						else if(curr_key == 101){
							wordData.add_e_character();
						}
						else if(curr_key == 105){
							wordData.add_i_character();
						}
						else if(curr_key == 111){
							wordData.add_o_character();
						}
						else if(curr_key == 117){
							wordData.add_u_character();
						}
						
						if(!is_f && curr_key == 102){
							is_f = true ;
						}
						else if(!is_u && is_f && curr_key == 117){
							is_u = true;
						}
						else if(!is_c && is_u && curr_key == 99){
							is_c = true;
						}
						else if(!is_u && is_f && curr_key == 99){
							is_u = true;
							is_c = true;
						}
						else if(is_c && curr_key==107){
							wordData.add_f_word();
							is_f = false;
							is_u = false;
							is_c = false;
						}
						else{
							is_f = false;
							is_u = false;
							is_c = false;
						}
						if(curr_key == 102){
							is_f = true;
						}
					}
					else if(curr_key >= 48 && curr_key <=57){
						wordData.add_numeric_character();
					}
					else if((curr_key >=33 && curr_key <= 47) || (curr_key >=58 && curr_key <=64)){
						wordData.add_special_character();
					}
				
			}
			addWordData(wordData);
			SQLiteDatabase db = this.getReadableDatabase();
			dropKeytrokesTable(db);
			createKeystrokesTable(db);
			if(getWordDataCount(db)>10){
				UploadData ud = new UploadData(getAllWordData());
				ud.uploadDataToMongo();
				dropWordsTable(db);
				createWordsTable(db);
			}

		}
	}
	
	// Getting All Keystrokes
	public List<KeystrokeData> getAllKeystrokeData() {
		List<KeystrokeData> keystrokeDataList = new ArrayList<KeystrokeData>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_KEYSTROKES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				KeystrokeData keystrokeData = new KeystrokeData();
				keystrokeData.set_id(Integer.parseInt(cursor.getString(0)));
				keystrokeData.set_key(cursor.getString(1));
				keystrokeData.set_timestamp(cursor.getString(2));
				
				keystrokeDataList.add(keystrokeData);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		Log.d("soft_keyboard","All keystrokes retrieved");
		// return contact list
		return keystrokeDataList;
		
	}
	
	//Getting all words
	public List<WordData> getAllWordData() throws ParseException {
		List<WordData> wordDataList = new ArrayList<WordData>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_WORDS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				WordData wordData = new WordData();
				wordData.set_id(Integer.parseInt(cursor.getString(0)));
				wordData.set_alphabetical_characters(Integer.parseInt(cursor.getString(1)));
				wordData.set_numeric_characters(Integer.parseInt(cursor.getString(2)));
				wordData.set_word_separators(Integer.parseInt(cursor.getString(3)));
				wordData.set_backspaces(Integer.parseInt(cursor.getString(4)));
				wordData.set_capslock_characters(Integer.parseInt(cursor.getString(5)));
				wordData.set_special_characters(Integer.parseInt(cursor.getString(6)));
				wordData.set_total_keystrokes(Integer.parseInt(cursor.getString(7)));
				wordData.set_timestamp(cursor.getString(8));
				wordData.set_time_taken(Integer.parseInt(cursor.getString(9)));
				wordData.set_a_characters(Integer.parseInt(cursor.getString(10)));
				wordData.set_e_characters(Integer.parseInt(cursor.getString(11)));
				wordData.set_i_characters(Integer.parseInt(cursor.getString(12)));
				wordData.set_o_characters(Integer.parseInt(cursor.getString(13)));
				wordData.set_u_characters(Integer.parseInt(cursor.getString(14)));
				wordData.set_f_words(Integer.parseInt(cursor.getString(15)));
				wordDataList.add(wordData);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		Log.d("soft_keyboard","All words retrieved");
		// return contact list
		return wordDataList;
		
	}
	// Updating single KeystrokeData
	public int updateContact(KeystrokeData keystroke) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_KEY, keystroke.get_key());
		values.put(KEY_TIMESTAMP, keystroke.get_timestamp());

		// updating row
		return db.update(TABLE_KEYSTROKES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(keystroke.get_id()) });
	}
	
	// Deleting single keystrokeData
	public void deleteContact(KeystrokeData keystroke) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_KEYSTROKES, KEY_ID + " = ?",
				new String[] { String.valueOf(keystroke.get_id()) });

	}


	// Getting KeystrokeData Count
	public int getKeystrokeDataCount(SQLiteDatabase db) {
		String countQuery = "SELECT  * FROM " + TABLE_KEYSTROKES;

		Cursor cursor = db.rawQuery(countQuery, null);


		// return count
		Log.d("soft_keyboard","Count of Keystrokes : "+cursor.getCount());
		cursor.close();
		return cursor.getCount();
		
	}
	//getting words count
	public int getWordDataCount(SQLiteDatabase db) {
		String countQuery = "SELECT  * FROM " + TABLE_WORDS;
		Cursor cursor = db.rawQuery(countQuery, null);


		// return count
		Log.d("soft_keyboard","Count of Words : "+cursor.getCount());
		cursor.close();
		return cursor.getCount();
		
	}
}
