package com.data;

public class KeystrokeData {
	int _id;
	String _key;
	String _timestamp;
	public int get_id() {
		return _id;
	}
	public KeystrokeData(int _id, String _key, String _timestamp) {
		this._id = _id;
		this._key = _key;
		this._timestamp = _timestamp;
	}
	public KeystrokeData( String _key, String _timestamp) {
		this._key = _key;
		this._timestamp = _timestamp;
	}
	public KeystrokeData() {
		// TODO Auto-generated constructor stub
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_key() {
		return _key;
	}
	public void set_key(String _key) {
		this._key = _key;
	}
	public String get_timestamp() {
		return _timestamp;
	}
	public void set_timestamp(String _timestamp) {
		this._timestamp = _timestamp;
	}
	
}
