package com.bingo.joy.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bingo.joy.model.Saying;
import com.bingo.util.HalfAngleToAngleUtil;

public class DBSayingManager {
	private DBHelper dbHelper = null;
	private SQLiteDatabase sdDatabase = null;
	public final String TABLE_NAME = "saying";

	public DBSayingManager(Context context) {
		dbHelper = new DBHelper(context);
		sdDatabase = dbHelper.getWritableDatabase();
		Log.i("test", "DBSayingManager");
	}
	
	// 根据sayingDes查找SayingId
		public List<Saying> findSayingIdByDes(String sayingDes) {
			Cursor cursor;
			List<Saying> list = new ArrayList<Saying>();

			System.out.println("=====sayingDes====" + sayingDes);
			if (!"".equals(sayingDes)) {
				cursor = sdDatabase.query(TABLE_NAME, null, "sayingDes=?",
						new String[] { String.valueOf(sayingDes) }, null, null,
						null);
			} else {
				cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
						null);
			}

			if (cursor.getCount() > 0) {
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToNext();
					Saying saying = new Saying();
					saying.setSayingId(Integer.parseInt(cursor.getString(0)));
					saying.setSayingDes(cursor.getString(1));
					saying.setSayingKey(cursor.getString(2));
					saying.setSayingKind(cursor.getString(3));
					saying.setSayingRemark(cursor.getString(4));
					list.add(saying);

					// System.out.println("=====list====="+list.toString());
				}
				return list;
			} else {
				return null;
			}

		}
	
	// 根据Remark查找
		public List<Saying> findSayingsByRemark(String kind) {
			Cursor cursor;
			List<Saying> list = new ArrayList<Saying>();

			System.out.println("=====remark====" + kind);
			if (!"".equals(kind)) {
				cursor = sdDatabase
						.query(TABLE_NAME,
								null,
								"sayingRemark=? and sayingKind=?",
								new String[] { String.valueOf("最爱"),
										String.valueOf(kind) }, null, null, null);
			} else {
				cursor = sdDatabase.query(TABLE_NAME, null, "sayingRemark=?",
						new String[] { String.valueOf("最爱") }, null, null, null);
			}

			if (cursor.getCount() > 0) {
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToNext();
					Saying saying = new Saying();
					saying.setSayingId(Integer.parseInt(cursor.getString(0)));
					saying.setSayingDes(cursor.getString(1));
					saying.setSayingKey(cursor.getString(2));
					saying.setSayingKind(cursor.getString(3));
					saying.setSayingRemark(cursor.getString(4));
					list.add(saying);

					// System.out.println("=====list====="+list.toString());
				}
				return list;
			} else {
				return null;
			}

		}
		
		// 修改
		public boolean update(Saying saying) {
			ContentValues cValues = new ContentValues();

			// cValues.put("sayingId", saying.getSayingId());
			// cValues.put("sayingDes", saying.getSayingDes());
			// cValues.put("sayingKey", saying.getSayingKey());
			// cValues.put("sayingKind", saying.getSayingKind());
			cValues.put("sayingRemark", saying.getSayingRemark());

			int i = sdDatabase.update(TABLE_NAME, cValues, "sayingId=?",
					new String[] { String.valueOf(saying.getSayingId()) });

			if (i > 0) {
				return true;
			} else {
				return false;
			}
		}

	// 根据ID查找
	public Saying findSayingById(int id) {
		Cursor cursor = sdDatabase.query(TABLE_NAME, null, "sayingId=?",
				new String[] { String.valueOf(id) }, null, null, null);
		Saying saying = new Saying();
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			saying.setSayingId(Integer.parseInt(cursor.getString(0)));
			saying.setSayingDes(HalfAngleToAngleUtil.ToDBC(cursor.getString(1)));
			saying.setSayingKey(cursor.getString(2));
			saying.setSayingKind(cursor.getString(3));
			saying.setSayingRemark(cursor.getString(4));
			return saying;
		} else {
			return null;
		}

	}

	// 根据kind查找
	public List<Saying> findSayingsByKind(String kind) {
		Cursor cursor;
		List<Saying> list = new ArrayList<Saying>();

		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "sayingKind=?",
					new String[] { String.valueOf(kind) }, null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
					null);
		}

		if (cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				Saying saying = new Saying();
				saying.setSayingId(Integer.parseInt(cursor.getString(0)));
				saying.setSayingDes(cursor.getString(1));
				saying.setSayingKey(cursor.getString(2));
				saying.setSayingKind(cursor.getString(3));
				saying.setSayingRemark(cursor.getString(4));
				list.add(saying);
				
				//System.out.println("=====list====="+list.toString());
			}
			return list;
		} else {
			return null;
		}

	}

	// 根据ID查找
	public List<Saying> findSayingsById(int id) {
		Cursor cursor;
		List<Saying> list = new ArrayList<Saying>();

		System.out.println("=====id====" + id);
		if (!"".equals(id)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "sayingId=?",
					new String[] { String.valueOf(id) }, null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, null, null, null, null,
					null);
		}

		Saying saying = new Saying();
		if (cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				saying.setSayingId(Integer.parseInt(cursor.getString(0)));
				saying.setSayingDes(cursor.getString(1));
				saying.setSayingKey(cursor.getString(2));
				saying.setSayingKind(cursor.getString(3));
				saying.setSayingRemark(cursor.getString(4));
				list.add(saying);
			}
			return list;
		} else {
			return null;
		}

	}

	// 根据kind查找
	public Saying findSayingByKindAndId(String kind, int id) {
		Cursor cursor;
		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME, null,
					"sayingKind=? and sayingId=?",
					new String[] { String.valueOf(kind), String.valueOf(id) },
					null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME, null, "sayingId=?",
					new String[] { String.valueOf(id) }, null, null, null);
		}

		Saying saying = new Saying();
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			saying.setSayingId(Integer.parseInt(cursor.getString(0)));
			saying.setSayingDes(cursor.getString(1));
			saying.setSayingKey(cursor.getString(2));
			saying.setSayingKind(cursor.getString(3));
			saying.setSayingRemark(cursor.getString(4));
			System.out.println("=====saying.setSayingDes===="
					+ cursor.getString(1));
			System.out.println("=====saying.setSayingKey===="
					+ cursor.getString(2));
			return saying;
		} else {
			return null;
		}

	}

	// 根据kind查找返回灯谜条数
	public int findSayingCountByKind(String kind) {
		Cursor cursor;
		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME, null, "sayingKind=?",
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
	public int findSayingLastIdByKind(String kind) {
		Cursor cursor;
		System.out.println("=====kind====" + kind);
		if (!"".equals(kind)) {
			cursor = sdDatabase.query(TABLE_NAME,
					new String[] { String.valueOf("sayingId") },
					"sayingKind=?", new String[] { String.valueOf(kind) },
					null, null, null);
		} else {
			cursor = sdDatabase.query(TABLE_NAME,
					new String[] { String.valueOf("sayingId") }, null, null,
					null, null, null);
		}

		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			System.out.println("======sayingId====="
					+ cursor.getColumnIndex("sayingId"));
			return cursor.getCount();
		} else {
			return 0;
		}

	}

	// 按种类获取所有明细的列表
	public List<HashMap<String, String>> queryAllByKind(String rKind) {
		Cursor cursor = sdDatabase.query(TABLE_NAME, null, "sayingKind=?",
				new String[] { rKind }, null, null, null);
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		while (cursor.moveToNext()) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("sayingId", cursor.getString(0));
			map.put("sayingDes", cursor.getString(1));
			map.put("sayingKey", cursor.getString(2));
			map.put("sayingKind", cursor.getString(3));
			map.put("sayingRemark", cursor.getString(4));

			resultList.add(map);
		}

		return resultList;
	}

	public void insert(Saying saying) {
		ContentValues cValues = new ContentValues();
		// cValues.put("sayingId", saying.getSayingId());
		cValues.put("sayingDes", saying.getSayingDes());
		cValues.put("sayingKey", saying.getSayingKey());
		cValues.put("sayingKind", saying.getSayingKind());
		cValues.put("sayingRemark", saying.getSayingRemark());
		sdDatabase.insert(TABLE_NAME, null, cValues);
	}

	// 删除
	public boolean delSayingById(int id) {
		int nt = sdDatabase.delete(TABLE_NAME, "sayingId=?",
				new String[] { String.valueOf(id) });
		return nt > 0 ? true : false;
	}

	public boolean idValidSayingKey(String rdes, String rkey) {
		Log.i("text", "ki" + rdes);
		Log.i("text", "ki" + rkey);
		Cursor cuserCursor = sdDatabase.query(TABLE_NAME, null, "sayingDes=?",
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
