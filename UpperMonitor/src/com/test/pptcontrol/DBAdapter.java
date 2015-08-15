package com.test.pptcontrol;
/**
 * 定义一个数据库类，用于数据的增、删、改、查
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_singer = "singer";
	public static final String KEY_name = "name";
	
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "music";
	private static final String DATABASE_TABLE = "song";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE =
	"create table song (_id integer primary key autoincrement, "
	+ "singer text not null, name text not null);";
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

	public long insertSong(String singer, String name)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_singer, singer);
		initialValues.put(KEY_name, name);
		//initialValues.put(KEY_PUBLISHER, publisher);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	//---删除一个指定的标题---

	public boolean deleteSong(long rowId)
	{
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	//---检索所有标题---

	public Cursor getAllSong()
	{
		return db.query(DATABASE_TABLE, new String[] {
		KEY_ROWID,
		KEY_singer,
		KEY_name},
		null,
		null,
		null,
		null,
		null);
	}
	//---检索一个指定的标题---

	public Cursor getSong(long rowId) throws SQLException
	{
		Cursor mCursor =
		db.query(true, DATABASE_TABLE, new String[] {
		KEY_ROWID,
		KEY_singer,
		KEY_name
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

	public boolean updateSong(long rowId, String singer,
	String name)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_singer, singer);
		args.put(KEY_name, name);
		//args.put(KEY_PUBLISHER, publisher);
		return db.update(DATABASE_TABLE, args,
		KEY_ROWID + "=" + rowId, null) > 0;
	}
}
