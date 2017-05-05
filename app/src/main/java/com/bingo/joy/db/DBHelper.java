package com.bingo.joy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public final static String DB_NAME = "joyMIS.db";
	private final static int DB_VERSION = 3;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// String sql
		// ="create  table If Not Exists DB_User (uid integer primary key autoincrement,uname varchar(30),upwd varchar(30))";
		String sql1 = "create table riddle(riddleId integer primary key autoincrement,"
				+ "riddleDes varchar(255),riddleKey varchar(50),riddleKind varchar(20),riddleRemark varchar(20))";
		String sql2 = "create table saying(sayingId integer primary key autoincrement,"
				+ "sayingDes varchar(255),sayingKey varchar(50),sayingKind varchar(20),sayingRemark varchar(20))";
		String sql3 = "create table twister(twisterId integer primary key autoincrement,"
				+ "twisterDes varchar(255),twisterKey varchar(50),twisterKind varchar(20),twisterRemark varchar(20))";

//		String sql11="delete from riddle";  //清空数据
//		String sql12="update sqlite_sequence SET seq = 0 where name ='riddle'";//自增长ID为0

//		db.execSQL(sql11);
//		db.execSQL(sql12);
		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);
		Log.i("test", "DBUSERONcreatre");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
