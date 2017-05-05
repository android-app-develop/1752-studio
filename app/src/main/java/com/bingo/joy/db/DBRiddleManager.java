package com.bingo.joy.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bingo.joy.model.Riddle;
import com.bingo.util.HalfAngleToAngleUtil;

public class DBRiddleManager {
	private DBHelper dbHelper = null;
	private SQLiteDatabase sdDatabase = null;
	public final String TABLE_NAME = "riddle";

	public DBRiddleManager(Context context) {
		dbHelper = new DBHelper(context);
		sdDatabase = dbHelper.getWritableDatabase();
		Log.i("test", "DBRiddleManager");
	}

	// 根据ID查找
	public Riddle findRiddleById(int id) {
		Cursor cursor = sdDatabase.query(TABLE_NAME, null, "riddleId=?",
				new String[] { String.valueOf(id) }, null, null, null);
		Riddle riddle = new Riddle();
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			riddle.setRiddleId(Integer.parseInt(cursor.getString(0)));
			riddle.setRiddleDes(HalfAngleToAngleUtil.ToDBC(cursor.getString(1)));
			riddle.setRiddleKey(cursor.getString(2));
			riddle.setRiddleKind(cursor.getString(3));
			riddle.setRiddleRemark(cursor.getString(4));
			return riddle;
		} else {
			return null;
		}

	}

	// 根据kind查找
	public List<Riddle> findRiddlesByKind(String kind) {
		Cursor cursor;
		List<Riddle> list = new ArrayList<Riddle>();

		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "riddleKind=?",
					new String[] { String.valueOf(kind) }, null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
					null);
		}

		if (cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				Riddle riddle = new Riddle();
				riddle.setRiddleId(Integer.parseInt(cursor.getString(0)));
				riddle.setRiddleDes(cursor.getString(1));
				riddle.setRiddleKey(cursor.getString(2));
				riddle.setRiddleKind(cursor.getString(3));
				riddle.setRiddleRemark(cursor.getString(4));
				list.add(riddle);

				// System.out.println("=====list====="+list.toString());
			}
			return list;
		} else {
			return null;
		}

	}

	// 根据ID查找
	public List<Riddle> findRiddlesById(int id) {
		Cursor cursor;
		List<Riddle> list = new ArrayList<Riddle>();

		System.out.println("=====id====" + id);
		if (!"".equals(id)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "riddleId=?",
					new String[] { String.valueOf(id) }, null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
					null);
		}

		Riddle riddle = new Riddle();
		if (cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				riddle.setRiddleId(Integer.parseInt(cursor.getString(0)));
				riddle.setRiddleDes(cursor.getString(1));
				riddle.setRiddleKey(cursor.getString(2));
				riddle.setRiddleKind(cursor.getString(3));
				riddle.setRiddleRemark(cursor.getString(4));
				list.add(riddle);
			}
			return list;
		} else {
			return null;
		}

	}

	// 根据kind查找
	public Riddle findRiddleByKindAndId(String kind, int id) {
		Cursor cursor;
		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME, null,
					"riddleKind=? and riddleId=?",
					new String[] { String.valueOf(kind), String.valueOf(id) },
					null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, "riddleId=?",
					new String[] { String.valueOf(id) }, null, null, null);
		}

		Riddle riddle = new Riddle();
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			riddle.setRiddleId(Integer.parseInt(cursor.getString(0)));
			riddle.setRiddleDes(cursor.getString(1));
			riddle.setRiddleKey(cursor.getString(2));
			riddle.setRiddleKind(cursor.getString(3));
			riddle.setRiddleRemark(cursor.getString(4));
			System.out.println("=====riddle.setRiddleDes===="
					+ cursor.getString(1));
			System.out.println("=====riddle.setRiddleKey===="
					+ cursor.getString(2));
			return riddle;
		} else {
			return null;
		}

	}

	// 根据kind查找返回灯谜条数
	public int findRiddleCountByKind(String kind) {
		Cursor cursor;
		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "riddleKind=?",
					new String[] { String.valueOf(kind) }, null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
					null);
		}

		if (cursor.getCount() > 0) {
			return cursor.getCount();
		} else {
			return 0;
		}

	}

	// 根据kind查找返回灯谜条数
	public int findRiddleLastIdByKind(String kind) {
		Cursor cursor;
		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME,
					new String[] { String.valueOf("riddleId") },
					"riddleKind=?", new String[] { String.valueOf(kind) },
					null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME,
					new String[] { String.valueOf("riddleId") }, null, null,
					null, null, null);
		}

		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			System.out.println("======riddleId====="
					+ cursor.getColumnIndex("riddleId"));
			return cursor.getCount();
		} else {
			return 0;
		}

	}

	// 根据riddleDes查找RiddleId
	public List<Riddle> findRiddleIdByDes(String riddleDes) {
		Cursor cursor;
		List<Riddle> list = new ArrayList<Riddle>();

		System.out.println("=====riddleDes====" + riddleDes);
		if (!"".equals(riddleDes)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "riddleDes=?",
					new String[] { String.valueOf(riddleDes) }, null, null,
					null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
					null);
		}

		if (cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				Riddle riddle = new Riddle();
				riddle.setRiddleId(Integer.parseInt(cursor.getString(0)));
				riddle.setRiddleDes(cursor.getString(1));
				riddle.setRiddleKey(cursor.getString(2));
				riddle.setRiddleKind(cursor.getString(3));
				riddle.setRiddleRemark(cursor.getString(4));
				list.add(riddle);

				// System.out.println("=====list====="+list.toString());
			}
			return list;
		} else {
			return null;
		}

	}

	// 根据Remark查找
	public List<Riddle> findRiddlesByRemark(String kind) {
		Cursor cursor;
		List<Riddle> list = new ArrayList<Riddle>();

		System.out.println("=====remark====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase
					.query(TABLE_NAME,
							null,
							"riddleRemark=? and riddleKind=?",
							new String[] { String.valueOf("最爱"),
									String.valueOf(kind) }, null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, "riddleRemark=?",
					new String[] { String.valueOf("最爱") }, null, null, null);
		}

		if (cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				Riddle riddle = new Riddle();
				riddle.setRiddleId(Integer.parseInt(cursor.getString(0)));
				riddle.setRiddleDes(cursor.getString(1));
				riddle.setRiddleKey(cursor.getString(2));
				riddle.setRiddleKind(cursor.getString(3));
				riddle.setRiddleRemark(cursor.getString(4));
				list.add(riddle);

				// System.out.println("=====list====="+list.toString());
			}
			return list;
		} else {
			return null;
		}

	}

	// 按种类获取所有明细的列表
	public List<HashMap<String, String>> queryAllByKind(String rKind) {
		Cursor cursor = sdDatabase.query(TABLE_NAME, null, "riddleKind=?",
				new String[] { rKind }, null, null, null);
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		while (cursor.moveToNext()) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("riddleId", cursor.getString(0));
			map.put("riddleDes", cursor.getString(1));
			map.put("riddleKey", cursor.getString(2));
			map.put("riddleKind", cursor.getString(3));
			map.put("riddleRemark", cursor.getString(4));

			resultList.add(map);
		}

		return resultList;
	}

	public void insert(Riddle riddle) {
		ContentValues cValues = new ContentValues();
		// cValues.put("riddleId", riddle.getRiddleId());
		cValues.put("riddleDes", riddle.getRiddleDes());
		cValues.put("riddleKey", riddle.getRiddleKey());
		cValues.put("riddleKind", riddle.getRiddleKind());
		cValues.put("riddleRemark", riddle.getRiddleRemark());
		sdDatabase.insert(TABLE_NAME, null, cValues);
	}

	// 删除
	public boolean delRiddleById(int id) {
		int nt = sdDatabase.delete(TABLE_NAME, "riddleId=?",
				new String[] { String.valueOf(id) });
		return nt > 0 ? true : false;
	}

	// 修改
	public boolean update(Riddle riddle) {
		ContentValues cValues = new ContentValues();

		// cValues.put("riddleId", riddle.getRiddleId());
		// cValues.put("riddleDes", riddle.getRiddleDes());
		// cValues.put("riddleKey", riddle.getRiddleKey());
		// cValues.put("riddleKind", riddle.getRiddleKind());
		cValues.put("riddleRemark", riddle.getRiddleRemark());

		int i = sdDatabase.update(TABLE_NAME, cValues, "riddleId=?",
				new String[] { String.valueOf(riddle.getRiddleId()) });

		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean idValidRiddleKey(String rdes, String rkey) {
		Log.i("text", "ki" + rdes);
		Log.i("text", "ki" + rkey);
		Cursor cuserCursor = sdDatabase.query(TABLE_NAME, null, "riddleDes=?",
				new String[] { rdes }, null, null, null);

		Log.i("text", "cuserCursor" + cuserCursor);
		if (cuserCursor.getCount() > 0) {
			cuserCursor.moveToNext();
			Log.i("text", "ki rkey" + String.valueOf(cuserCursor.getString(2)));
			if (rkey.trim().equals(cuserCursor.getString(2).trim()))
				return true;
			else {
				return false;
			}
		} else {
			return false;
		}
	}
}
