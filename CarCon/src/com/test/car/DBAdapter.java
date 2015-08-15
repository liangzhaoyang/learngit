package com.test.car;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_ACCOUNT = "account";	
	public static final String KEY_PSD = "password";
	public static final String KEY_PORTRAIT = "portrait";
	public static final String KEY_NAME = "name";
	public static final String KEY_TEL = "telephone";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_BRAND="brand";
	public static final String KEY_TYPE = "type";
	public static final String KEY_PLATE = "plate";
	
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "UserManger";
	private static final String DATABASE_TABLE = "UserInfo";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE =
	"create table UserInfo (_id integer primary key autoincrement, "
	+ "account text not null, password text not null,portrait text null," +
	"name text null,telephone text not null,address text null," +
	"brand text null,type text null,plate text null);";
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	//
	public DBAdapter(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DATABASE_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
		int newVersion)
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion
			+ " to "
			+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS song");
			onCreate(db);
		}
	}
	//
	//---打开数据库---

	public DBAdapter open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	//---关闭数据库---

	public void close()
	{
		DBHelper.close();
	}
	//---向数据库插入一个标题---

	public long insertUser(String account, String password,String telNumber)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_PSD, password);
		initialValues.put(KEY_ACCOUNT, account);
		initialValues.put(KEY_TEL, telNumber);
		initialValues.put(KEY_PORTRAIT, "portrait.png");
		//initialValues.put(KEY_PUBLISHER, publisher);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	public long updateInfo(long rowId,String account,String telNumber)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, account);
		initialValues.put(KEY_TEL, telNumber);
		//initialValues.put(KEY_PUBLISHER, publisher);
		return db.update(DATABASE_TABLE, initialValues,
				KEY_ROWID + "=" + rowId, null) ;
	}
	//---删除一个指定的标题---

	public boolean deleteUser(long rowId)
	{
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	//---检索所有标题---

	public Cursor getAllUser()
	{
		return db.query(DATABASE_TABLE, new String[] {
		KEY_ROWID,KEY_ACCOUNT,KEY_PSD,KEY_PORTRAIT,KEY_NAME,
		KEY_TEL,KEY_ADDRESS,KEY_BRAND,KEY_TYPE,KEY_PLATE
		},
		null,
		null,
		null,
		null,
		null);
	}
	//---检索一个指定的标题---

	public Cursor getUser(long rowId) throws SQLException
	{
		Cursor mCursor =
		db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID,KEY_ACCOUNT,KEY_PSD,KEY_PORTRAIT,KEY_NAME,
				KEY_TEL,KEY_ADDRESS,KEY_BRAND,KEY_TYPE,KEY_PLATE
		},
		KEY_ROWID + "=" + rowId,
		null,
		null,
		null,
		null,
		null);
		if (mCursor != null) {
		mCursor.moveToFirst();
		}
		return mCursor;
	}
	//---更新一个标题---

	public boolean updateUser(long rowId, String account,
	String password,String portrait,String name,String tel,String address,
	String brand,String type,String plate)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_ACCOUNT, account);
		args.put(KEY_PSD, password);
		args.put(KEY_PORTRAIT, password);
		args.put(KEY_NAME, name);
		args.put(KEY_TEL, tel);
		args.put(KEY_ADDRESS, address);
		args.put(KEY_BRAND, brand);
		args.put(KEY_TYPE, type);
		args.put(KEY_PLATE, plate);

		//args.put(KEY_PUBLISHER, publisher);
		return db.update(DATABASE_TABLE, args,
		KEY_ROWID + "=" + rowId, null) > 0;
	}
}
