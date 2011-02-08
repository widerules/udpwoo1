package npu.dce.woo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBAdapter {

	 public static final String KEY_OWNER = "owner";
	 public static final String KEY_TYPES = "types";
	 public static final String KEY_GIVENNAME = "givenname";
	 public static final String KEY_MIDDLENAME = "middlename";
	 public static final String KEY_FAMILYNAME = "familyname";
	 
	 public static final String KEY_GENDER = "gender";  
	 
	 public static final String KEY_SPINPHONE = "spinphone";
	 public static final String KEY_PHONE = "phone";
	 
	 public static final String KEY_SPINEMAIL = "spinemail";
	 public static final String KEY_EMAIL = "email";
	 
	 public static final String KEY_SPINIM = "spinim";
	 public static final String KEY_IM = "im";
	 
	 public static final String KEY_SPINADDR = "spinaddr";
	 public static final String KEY_STREET = "street";
	 public static final String KEY_POBOX = "pobox";
	 public static final String KEY_CITY = "city";
	 public static final String KEY_STATE = "state";
	 public static final String KEY_ZIPCODE = "zipcode";
	 public static final String KEY_COUNTRY = "country";
	 
	 public static final String KEY_SPINSNS = "spinsns";
	 public static final String KEY_SNS = "sns";
	 
	 public static final String KEY_SPINORG = "spinorg";
	 public static final String KEY_ORG = "org";
	 
	 public static final String KEY_NOTES = "notes";
	 
	 public static final String KEY_DATE = "date";
	 public static final String KEY_HOUR = "hour";
	 public static final String KEY_MIN = "min";
	 public static final String KEY_SEC = "sec";
	 
	 public static final String KEY_ROWID = "_id";    
	 
	 private static final String TAG = "ContactDbAdapter";
	 private DatabaseHelper mDbHelper;    
	 private SQLiteDatabase mDb;
	
	 /* Database creation sql statement */    
	 private static final String DATABASE_CREATE = "create table contacts (_id integer primary key autoincrement, "                    
		 + "owner text not null, types text not null, givenname text not null, middlename text not null, familyname text not null, " 
		 + "gender text not null, spinphone text, phone text not null,"
		 + "spinemail text, email text not null, spinim text, im text not null,"
		 + "spinaddr text, street text not null, pobox text not null, city text not null, state text not null, zipcode text not null,country text not null,"
		 + "spinsns text, sns text not null, spinorg text, org text not null, notes text not null,"
		 + "date text not null, hour text not null, min text not null, sec text not null);";   
	
	 private static final String DATABASE_NAME = "updatecontact1.db";    
	 private static final String DATABASE_TABLE = "contacts";    
	 private static final int DATABASE_VERSION = 3;    
	 private final Context mCtx;
	 
	 private static class DatabaseHelper extends SQLiteOpenHelper {        
		
		public DatabaseHelper(Context context) {            
			 super(context, DATABASE_NAME, null, DATABASE_VERSION);        
		}        

		@Override        
		public void onCreate(SQLiteDatabase db) {            
			db.execSQL(DATABASE_CREATE);        
		}        
		
		@Override        
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {            
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");           
			db.execSQL("DROP TABLE IF EXISTS contacts");            
			onCreate(db);        
		}    
	} //DatabaseHelper
	
	// Constructor
	public ContactDBAdapter(Context ctx) {        
		 this.mCtx = ctx;   
		 mDbHelper = new DatabaseHelper(ctx);
	}
	 
	public void open() throws SQLException {        
		try {
			mDb = mDbHelper.getWritableDatabase();
		} catch (SQLException  ex) {
			mDb = mDbHelper.getReadableDatabase();
		}		
	}
	
	public void close() {        
		mDbHelper.close();    
	}
	
	public long createContact(String owner, String types, String givenname, String middlename, String familyname, String gender, String spinphone, String phone,
			String spinemail, String email, String spinim, String im, String spinaddr, String street, String pobox, 
			String city, String state, String zipcode, String country, String spinsns, String sns, String spinorg, 
			String org, String notes, String date, String hour, String min, String sec) {        
		 
		 ContentValues initialValues = new ContentValues(); 
		 initialValues.put(KEY_OWNER, owner);
		 initialValues.put(KEY_TYPES, types);
		 initialValues.put(KEY_GIVENNAME, givenname);
		 initialValues.put(KEY_MIDDLENAME, middlename);
		 initialValues.put(KEY_FAMILYNAME, familyname);
		 initialValues.put(KEY_GENDER,gender);  
		 initialValues.put(KEY_SPINPHONE, spinphone);
		 initialValues.put(KEY_PHONE, phone);
		 initialValues.put(KEY_SPINEMAIL, spinemail);
		 initialValues.put(KEY_EMAIL, email);
		 initialValues.put(KEY_SPINIM, spinim);
		 initialValues.put(KEY_IM, im);
		 initialValues.put(KEY_SPINADDR, spinaddr);
		 initialValues.put(KEY_STREET, street);
		 initialValues.put(KEY_POBOX, pobox);
		 initialValues.put(KEY_CITY, city);
		 initialValues.put(KEY_STATE, state);
		 initialValues.put(KEY_ZIPCODE, zipcode);
		 initialValues.put(KEY_COUNTRY, country);
		 initialValues.put(KEY_SPINSNS, spinsns);
		 initialValues.put(KEY_SNS, sns);
		 initialValues.put(KEY_SPINORG, spinorg);
		 initialValues.put(KEY_ORG, org);
		 initialValues.put(KEY_NOTES, notes); 
		 initialValues.put(KEY_DATE, date); 
		 initialValues.put(KEY_HOUR, hour); 
		 initialValues.put(KEY_MIN, min); 
		 initialValues.put(KEY_SEC, sec); 
		 
		 return mDb.insert(DATABASE_TABLE, null, initialValues);    
	} //createContact
	
	public void deleteAllContacts() {        
		 mDb.delete(DATABASE_TABLE, null, null);    
	} 

	public boolean deleteContact(String contactID) {    
		//public boolean deleteContact(long rowId) {       
		// return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;        
		return mDb.delete(DATABASE_TABLE, KEY_FAMILYNAME + "=" + contactID, null) > 0;    
	}
	 
	public Cursor fetchAllContacts() {        
		return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_OWNER, KEY_TYPES,
				KEY_GIVENNAME, KEY_MIDDLENAME, KEY_FAMILYNAME, KEY_GENDER, KEY_SPINPHONE, KEY_PHONE,
				KEY_SPINEMAIL, KEY_EMAIL, KEY_SPINIM, KEY_IM, KEY_SPINADDR, KEY_STREET, KEY_POBOX,
				KEY_CITY, KEY_STATE, KEY_ZIPCODE, KEY_COUNTRY, KEY_SPINSNS, KEY_SNS, KEY_SPINORG,
				KEY_ORG, KEY_NOTES, KEY_DATE, KEY_HOUR, KEY_MIN, KEY_SEC}, null, null, null, null, null);    
	}
	
	public Cursor fetchContact(long rowId) throws SQLException {        
		
		Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_OWNER, KEY_TYPES,                  
				KEY_GIVENNAME, KEY_MIDDLENAME, KEY_FAMILYNAME, KEY_GENDER, KEY_SPINPHONE, KEY_PHONE,
				KEY_SPINEMAIL, KEY_EMAIL, KEY_SPINIM, KEY_IM, KEY_SPINADDR, KEY_STREET, KEY_POBOX,
				KEY_CITY, KEY_STATE, KEY_ZIPCODE, KEY_COUNTRY, KEY_SPINSNS, KEY_SNS, KEY_SPINORG,
				KEY_ORG, KEY_NOTES, KEY_DATE, KEY_HOUR, KEY_MIN, KEY_SEC}, KEY_ROWID + "=" + rowId, null, null, null, null, null);        
		
		if (mCursor != null)
			mCursor.moveToFirst();                
		return mCursor;    
	} //fetchContact
	
	
	public boolean updateContact(long rowId, String owner, String types, String givenname, String middlename, String familyname, String gender, String spinphone, String phone,
			String spinemail, String email, String spinim, String im, String spinaddr, String street, String pobox, 
			String city, String state, String zipcode, String country, String spinsns, String sns, String spinorg, 
			String org, String notes, String date, String hour, String min, String sec) { 
		
		ContentValues args = new ContentValues();        
		args.put(KEY_OWNER, owner);
		args.put(KEY_TYPES, types);
		args.put(KEY_GIVENNAME, givenname);
		args.put(KEY_MIDDLENAME, middlename);
		args.put(KEY_FAMILYNAME, familyname);
		args.put(KEY_GENDER,gender);  
		args.put(KEY_SPINPHONE, spinphone);
		args.put(KEY_PHONE, phone);
		args.put(KEY_SPINEMAIL, spinemail);
		args.put(KEY_EMAIL, email);
		args.put(KEY_SPINIM, spinim);
		args.put(KEY_IM, im);
		args.put(KEY_SPINADDR, spinaddr);
		args.put(KEY_STREET, street);
		args.put(KEY_POBOX, pobox);
		args.put(KEY_CITY, city);
		args.put(KEY_STATE, state);
		args.put(KEY_ZIPCODE, zipcode);
		args.put(KEY_COUNTRY, country);
		args.put(KEY_SPINSNS, spinsns);
		args.put(KEY_SNS, sns);
		args.put(KEY_SPINORG, spinorg);
		args.put(KEY_ORG, org);
		args.put(KEY_NOTES, notes); 
		args.put(KEY_DATE, date); 
		args.put(KEY_HOUR, hour); 
		args.put(KEY_MIN, min); 
		args.put(KEY_SEC, sec); 
	
		return mDb.update(DATABASE_TABLE, args, KEY_ROWID  + "=" + rowId, null) > 0;    
	
	} //updateContact
	
	public boolean updateContactTime(long rowId, String date, String hour, String min, String sec) { 
		
		ContentValues args = new ContentValues();        
//		args.put(KEY_OWNER, owner);
//		args.put(KEY_TYPES, types);
//		args.put(KEY_GIVENNAME, givenname);
//		args.put(KEY_MIDDLENAME, middlename);
//		args.put(KEY_FAMILYNAME, familyname);
		args.put(KEY_DATE, date); 
		args.put(KEY_HOUR, hour); 
		args.put(KEY_MIN, min); 
		args.put(KEY_SEC, sec); 
	
		return mDb.update(DATABASE_TABLE, args, KEY_ROWID  + "=" + rowId, null) > 0;    
	
	} //updateContactTime
	
}
