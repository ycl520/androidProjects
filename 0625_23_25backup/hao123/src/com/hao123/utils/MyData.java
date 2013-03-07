package com.hao123.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class MyData extends SQLiteOpenHelper {
	private static final String DB_NAME = "HisDataBase";
	private static final int VERSON = 1;
	public static final String TABLENAME = "history";
	public static final String _ID = "_id";
	public static final String URL = "url";
	public static final String TITLE = "title";
	public static final String COUNT = "count";

	public MyData(Context context) {
		super(context, DB_NAME, null, VERSON);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table " + TABLENAME + "(" + _ID
				+ " integer primary key autoincrement," + URL
				+ " text not null unique," 
				+ TITLE + " text not null," + COUNT
				+ " integer not null" + ")";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "DROP TABLE IF EXISTS " + TABLENAME;
		
		    db.execSQL(sql);
		
		    onCreate(db);

	}

	// 插入新的数据
	public void insert(HistoryBean his) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(URL, his.url);
		values.put(TITLE, his.title);
		values.put(COUNT, his.count);
		db.insert(TABLENAME, null, values);

	}

	// 查询所有的数据
	public Cursor findAll() {
		SQLiteDatabase db = getReadableDatabase();

		return db.query(TABLENAME, null, null,
				null, null, null, COUNT+" desc");
	}

	// 通过url查询count
	public Cursor findByname(String title) {
		SQLiteDatabase db = getReadableDatabase();
		return db.query(TABLENAME, new String[] { _ID, URL, COUNT, TITLE }, TITLE
				+ "=" + "\"" + title + "\"", null, null, null, null);
	}

	// 修改count
	public void updata(HistoryBean his) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COUNT, his.count);
		db.update(TABLENAME, values, _ID + "=" + his._id, null);

	}
	public void delteAll(){
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLENAME, null, null);
	}
	public void deleteByid(String title){
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLENAME, TITLE+"="+"\"" + title + "\"", null);
	}
	

}
