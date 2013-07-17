package com.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WordData {



	int _id;
	int _alphabetical_characters;
	int _numeric_characters;
	int _word_separators;
	int _backspaces;
	int _capslock_characters;
	int _special_characters;
	int _a_characters;
	int _e_characters;
	int _i_characters;
	int _o_characters;
	int _u_characters;
	int _f_words;
	int _total_keystrokes;
	String _timestamp;
	int _time_taken;
	
	public WordData(){
		this._alphabetical_characters = 0;
		this._numeric_characters = 0;
		this._word_separators = 0;
		this._backspaces = 0;
		this._capslock_characters = 0;
		this._special_characters = 0;
		this._total_keystrokes = 0;
		this._timestamp = "";
		this._time_taken = 0;
		this._a_characters = 0;
		this._e_characters = 0;
		this._i_characters = 0;
		this._o_characters = 0;
		this._u_characters = 0;
		this._f_words = 0;
	}
	
	public WordData(int _alphabetical_characters, int _numeric_characters,
			int _word_separators, int _backspaces, int _capslock_characters,
			int _special_characters, String _timestamp, int _time_taken) {
		super();
		this._alphabetical_characters = _alphabetical_characters;
		this._numeric_characters = _numeric_characters;
		this._word_separators = _word_separators;
		this._backspaces = _backspaces;
		this._capslock_characters = _capslock_characters;
		this._special_characters = _special_characters;
		this._timestamp = _timestamp;
		this._time_taken = _time_taken;
	}

	public WordData(int _id, int _alphabetical_characters,
			int _numeric_characters, int _word_separators, int _backspaces,
			int _capslock_characters, int _special_characters,
			int _total_keystrokes, String _timestamp, int _time_taken) {
		super();
		this._id = _id;
		this._alphabetical_characters = _alphabetical_characters;
		this._numeric_characters = _numeric_characters;
		this._word_separators = _word_separators;
		this._backspaces = _backspaces;
		this._capslock_characters = _capslock_characters;
		this._special_characters = _special_characters;
		this._total_keystrokes = _total_keystrokes;
		this._timestamp = _timestamp;
		this._time_taken = _time_taken;
	}
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_alphabetical_characters() {
		return _alphabetical_characters;
	}

	public void set_alphabetical_characters(int _alphabetical_characters) {
		this._alphabetical_characters = _alphabetical_characters;
	}

	public int get_numeric_characters() {
		return _numeric_characters;
	}

	public void set_numeric_characters(int _numeric_characters) {
		this._numeric_characters = _numeric_characters;
	}

	public int get_word_separators() {
		return _word_separators;
	}

	public void set_word_separators(int _word_separators) {
		this._word_separators = _word_separators;
	}

	public int get_backspaces() {
		return _backspaces;
	}

	public void set_backspaces(int _backspaces) {
		this._backspaces = _backspaces;
	}

	public int get_capslock_characters() {
		return _capslock_characters;
	}

	public void set_capslock_characters(int _capslock_characters) {
		this._capslock_characters = _capslock_characters;
	}

	public int get_special_characters() {
		return _special_characters;
	}

	public void set_special_characters(int _special_characters) {
		this._special_characters = _special_characters;
	}

	public String get_timestamp() {
		return _timestamp;
	}


	public void set_timestamp(String _timestamp) throws ParseException{

		this._timestamp = _timestamp;
	}
	public int get_time_taken() {
		return _time_taken;
	}

	public void set_time_taken(int _time_taken){
		this._time_taken = _time_taken;
	}
	

	public void generate_time_taken(String final_timestamp) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = dateFormat.parse(final_timestamp);
		Date timestamp = dateFormat.parse(this._timestamp);
		
		long diffInMillies = date.getTime() - timestamp.getTime();
		diffInMillies = diffInMillies/1000;
		this._time_taken = (int) diffInMillies;
	}
	public int get_total_keystrokes() {
		return _total_keystrokes;
	}

	public void set_total_keystrokes(int _total_keystrokes) {
		this._total_keystrokes = _total_keystrokes;
	}
	public void add_alphabetical_character(){
		this._alphabetical_characters += 1;
	}
	
	public void add_numeric_character(){
		this._numeric_characters += 1;
	}
	
	public void add_word_separator(){
		this._word_separators += 1;
	}
	
	public void add_backspace(){
		this._backspaces += 1;
	}
	
	public void add_capslock_character(){
		this._capslock_characters += 1;
	}

	public void add_special_character(){
		this._special_characters += 1;
	}
	public void add_total_keystroke(){
		this._total_keystrokes += 1;
	}
	
	public String toString(){
		return "Alphabetical characters:"+this._alphabetical_characters+", Numeric characters:"+this._numeric_characters+", Word separators:"+this._word_separators+", Backspaces:"+this._backspaces+", Caps Characters:"+this._capslock_characters+", Special Characters:"+this._special_characters+", a:"+this._a_characters+", e:"+this._e_characters+", i:"+this._i_characters+", o:"+this._o_characters+", u:"+this._u_characters+", f-words:"+this._f_words+", Total keystrokes:"+this._total_keystrokes+", Time taken:"+this._time_taken+", Timestamp:"+this._timestamp.toString();
	}

	public int get_a_characters() {
		return _a_characters;
	}

	public void set_a_characters(int _a_characters) {
		this._a_characters = _a_characters;
	}

	public int get_e_characters() {
		return _e_characters;
	}

	public void set_e_characters(int _e_characters) {
		this._e_characters = _e_characters;
	}

	public int get_i_characters() {
		return _i_characters;
	}

	public void set_i_characters(int _i_characters) {
		this._i_characters = _i_characters;
	}

	public int get_o_characters() {
		return _o_characters;
	}

	public void set_o_characters(int _o_characters) {
		this._o_characters = _o_characters;
	}

	public int get_u_characters() {
		return _u_characters;
	}

	public void set_u_characters(int _u_characters) {
		this._u_characters = _u_characters;
	}

	public int get_f_words() {
		return _f_words;
	}

	public void set_f_words(int _f_words) {
		this._f_words = _f_words;
	}
	
	public void add_a_character(){
		this._a_characters += 1;
	}
	public void add_e_character(){
		this._e_characters += 1;
	}
	public void add_i_character(){
		this._i_characters += 1;
	}
	public void add_o_character(){
		this._o_characters += 1;
	}
	public void add_u_character(){
		this._u_characters += 1;
	}
	
	public void add_f_word(){
		this._f_words += 1;
	}
}
