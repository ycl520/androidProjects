package com.hao123.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class OftenData extends SQLiteOpenHelper {
	private static final String DB_NAME = "OftenDataBase";
	private static final int VERSON = 1;
	public static final String TABLENAME = "often";
	public static final String _ID = "_id";
	public static final String URL = "url";
	public static final String TITLE = "title";
	public static final String COUNT = "count";

	public OftenData(Context context) {
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
	public void insert(OftenBean often) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(URL, often.url);
		values.put(TITLE, often.title);
		values.put(COUNT, often.count);
		db.insert(TABLENAME, null, values);

	}

	// 查询所有的数据
	public Cursor findAll() {
		SQLiteDatabase db = getReadableDatabase();

		
		Cursor cursor=db.query(TABLENAME, null, null,
				null, null, null, COUNT+" desc");
	
		return cursor;
	}

	// 通过url查询count
	public Cursor findByname(String title) {
		SQLiteDatabase db = getReadableDatabase();
		return db.query(TABLENAME, new String[] { _ID, URL, COUNT, TITLE }, TITLE
				+ "=" + "\"" + title + "\"", null, null, null, null);
	}

	// 修改count
	public void updata(OftenBean often) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COUNT, often.count);
		db.update(TABLENAME, values, _ID + "=" + often._id, null);

	}
	public void delteAll(){
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLENAME, null, null);
	}
	

}
