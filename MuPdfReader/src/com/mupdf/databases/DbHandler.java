package com.mupdf.databases;

import java.util.ArrayList;
import java.util.List;







import com.mupdf.entity.BookMark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "BookMark.db";
	public static final String TABLE_BOOK_MARK = "Book_Mark";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PAGE_ID = "Page_Count";


	private SQLiteDatabase database;
	private Context context;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_BOOK_MARK + "(" + COLUMN_ID
			+ " integer primary key autoincrement, "  + COLUMN_PAGE_ID + " TEXT unique);";

	public DbHandler(Context context) {
		super(context, DATABASE_NAME, null, 1);
		this.context = context;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		// initializing the values
	

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_MARK);
		onCreate(db);

	}

	private void openConnection() {
		database = this.getWritableDatabase();
	}

	private void closeConnection() {
		database.close();
	}
	public long addBookMark(BookMark b)
	{
		openConnection();
		ContentValues cv=new ContentValues();
		cv.put(COLUMN_PAGE_ID, b.getPageId());
		
		long result = database.insert(TABLE_BOOK_MARK,null,cv);
		closeConnection();
		return  result;
	}

	public List<BookMark> getPages()
	{
		openConnection();
		List <BookMark> bookmarks = new ArrayList<BookMark>();
		
		BookMark bMark;
		
		//String col[] = new String[]{colId,colfirstName,collastName,colEmail,colDOB,colAddress};
		Cursor c = database.query(TABLE_BOOK_MARK, null, null, null, null, null, null);
		int colid = c.getColumnIndex(COLUMN_ID);
		int mCOLUMN_PAGE_ID=c.getColumnIndex(COLUMN_PAGE_ID);
	

	
		while(c.moveToNext())
		{
			bMark = new  BookMark();//(c.getInt(colid),c.getString(mCOLUMN_COUNTRY_NAME),c.getString(mCOLUMN_CURRENCY_VALUE),context );
			bMark.setId(c.getInt(colid));
			bMark.setPageId(c.getString(mCOLUMN_PAGE_ID));
			bookmarks.add(bMark);	
		}
		closeConnection();
		return bookmarks;
		
	}
	
	public void deleteBookMark(int id) {
		// TODO Auto-generated method stub
		openConnection();
		database.delete(TABLE_BOOK_MARK, COLUMN_PAGE_ID + "=" + id, null);
		closeConnection();
		
	}

}

