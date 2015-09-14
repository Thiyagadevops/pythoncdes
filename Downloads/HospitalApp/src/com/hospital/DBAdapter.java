package com.hospital;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends Activity {
	
		public static final String KEY_ROWID = "_id";
		public static final String KEY_USERNAME = "username";
		public static final String KEY_PASSWORD= "password";
		public static final String KEY_DESIGNATION= "designation";
		public static final String KEY_EXPERIENCE= "experience";
		public static final String KEY_VALUE="value";
		
		public static final String KEY_PATIENTNAME="patientname";
		public static final String SEX="sex";
		public static final String KEY_DISEASENAME="diseasename";
		public static final String KEY_MEDICINE="medicine";
    	public static final String KEY_CONTACT = "contact";
    	public static final String KEY_WARDNO = "wardno";
    	public static final String KEY_BLOODGROUP = "bloodgroup";
    	public static final String KEY_CHIEF = "chiefdoctor";
        public static final String KEY_DOC = "doctor";
    	public static final String KEY_NUR = "nurse";
    	public static final String KEY_LABTECH = "labtech";
    	
    	public static final String KEY_DOCNAME = "doctorname";
    	
    	public static final String KEY_NURNAME = "nursename";
    	
    	public static final String KEY_LABTECHNAME = "labtechname";
    	
    	public static final String KEY_CHIEFNAME = "chiefdocname";
    	
    	public static final String KEY_ENTITY1 = "entity1";
    	public static final String KEY_ENTITYVALUE1 = "entity1val";
    	public static final String KEY_ENTITY2 = "entity2";
    	public static final String KEY_ENTITYVALUE2 = "entity2val";
    	public static final String KEY_TRUST = "trustvalue";
    	
    	
    	public static final String DATABASE_NAME = "Patients.db";
    	public static final int DATABASE_VERSION =1;
    	public static final String DATABASE_TABLE1 ="PatientDetails";
    	public static final String DATABASE_TABLE2 ="physicianlogin";
    	public static final String DATABASE_TABLE3 ="Diseases";
    	public static final String DATABASE_TABLE4="Doctors";
    	public static final String DATABASE_TABLE5="Nurse";
    	public static final String DATABASE_TABLE6="Labtech";
    	public static final String DATABASE_TABLE7="Chiefdoc";
    	
    	
    	private static final String DATABASE_CREATE =
    	        "create table physicianlogin (_id integer primary key autoincrement, "
    	        + "username text not null, password text not null,designation text not null," +
    	        		"experience text not null,value integer ); ";
    	 
    	 private static final String DATABASE_CREATE1 =
 	        "create table PatientDetails (_id integer primary key autoincrement, "
 	        + "patientname text not null, sex text not null, bloodgroup text not null, " +
 	        		"diseasename text not null, wardno integer, contact integer, " +
 	        		"medicine text not null, chiefdoctor text not null,doctor text not null," +
 	        		"nurse text not null,labtech text not null); ";
    	 
    	 private static final String DATABASE_CREATE2 =
 	        "create table Diseases (_id integer primary key autoincrement, "
 	        + "Diseasename text not null, medicine text not null ); ";
		

    	 private static final String DATABASE_CREATE3 =
 	        "create table Doctors (_id integer primary key autoincrement, "
 	        + "doctorname text not null, designation text not null,experience text not null,value integer ); ";
    	 
    	 private static final String DATABASE_CREATE4 =
  	        "create table Nurse (_id integer primary key autoincrement, "
  	        + "nursename text not null, designation text not null,experience text not null,value integer ); ";
    	 
    	 private static final String DATABASE_CREATE5 =
  	        "create table Labtech (_id integer primary key autoincrement, "
  	        + "labtechname text not null, designation text not null,experience text not null,value integer ); ";
    	 
    	 private static final String DATABASE_CREATE6 =
  	        "create table Chiefdoc (_id integer primary key autoincrement, "
  	        + "chiefdocname text not null, designation text not null,experience text not null,value integer ); ";
    	 
        private SQLiteDatabase db;
        private DatabaseHelper DBHelper;
        private final Context context;
        
        public DBAdapter(Context ctx) 
        {
            this.context = ctx;
           
        }
     
        private static class DatabaseHelper extends SQLiteOpenHelper 
        {
            	DatabaseHelper(Context context) 
            {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
        

            	@Override
            	public void onCreate(SQLiteDatabase db) {
             
                db.execSQL(DATABASE_CREATE);
                db.execSQL(DATABASE_CREATE1);
                db.execSQL(DATABASE_CREATE2);
                db.execSQL(DATABASE_CREATE3);
                db.execSQL(DATABASE_CREATE4);
                db.execSQL(DATABASE_CREATE5);
                db.execSQL(DATABASE_CREATE6);
                
         }
       
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               db.execSQL("DROP TABLE IF EXISTS patientdetails");
                db.execSQL("DROP TABLE IF EXISTS physicianlogin");
                onCreate(db);
        }
}
       public DBAdapter open() throws SQLException 
		{
			DBHelper = new DatabaseHelper(context);
			db = DBHelper.getWritableDatabase();
			return this;
		}

		//---closes the database---    
		public void close() 
		{
			DBHelper.close();
		}
		
		Cursor SelectedData() 
	        {
			  Cursor c = db.rawQuery("SELECT experience FROM " +
			  		"physicianlogin" ,null);
			  return c;
	        }
		 public long insertuser( String user, String pass, String design, String experience,int value) 
        {
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_USERNAME, user);
            initialValues.put(KEY_PASSWORD, pass);
            initialValues.put(KEY_DESIGNATION, design);
            initialValues.put(KEY_EXPERIENCE, experience);
            initialValues.put(KEY_VALUE, value);
            return db.insert(DATABASE_TABLE2, null, initialValues);
            
        }
        
        public long insertdoc(String name,String desig,String experience,int value)
        {
        	 ContentValues initialValues = new ContentValues();
             initialValues.put(KEY_DOCNAME, name);
             initialValues.put(KEY_DESIGNATION, desig);
             initialValues.put(KEY_EXPERIENCE, experience);
             initialValues.put(KEY_VALUE, value);
             return db.insert(DATABASE_TABLE4, null, initialValues);
        }
        public long insertnur(String name,String desig,String experience,int value)
        {
        	 ContentValues initialValues = new ContentValues();
             initialValues.put(KEY_NURNAME, name);
             initialValues.put(KEY_DESIGNATION, desig);
             initialValues.put(KEY_EXPERIENCE, experience);
             initialValues.put(KEY_VALUE, value);
             return db.insert(DATABASE_TABLE5, null, initialValues);
        }
        public long insertlab(String name,String desig,String experience,int value)
        {
        	 ContentValues initialValues = new ContentValues();
             initialValues.put(KEY_LABTECHNAME, name);
             initialValues.put(KEY_DESIGNATION, desig);
             initialValues.put(KEY_EXPERIENCE, experience);
             initialValues.put(KEY_VALUE, value);
             return db.insert(DATABASE_TABLE6, null, initialValues);
        }
        public long insertcdoc(String name,String desig,String experience,int value)
        {
        	 ContentValues initialValues = new ContentValues();
             initialValues.put(KEY_CHIEFNAME, name);
             initialValues.put(KEY_DESIGNATION, desig);
             initialValues.put(KEY_EXPERIENCE, experience);
             initialValues.put(KEY_VALUE, value);
             return db.insert(DATABASE_TABLE7, null, initialValues);
        }
        
        
       public long insertpatientdetails( String patientname, String sex, String bloodgroup,String disname,String wno,
    		   String phone, String med,String chief,String phy,String nur,String lab ) 
        {
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_PATIENTNAME, patientname);
            initialValues.put(SEX, sex);
            initialValues.put(KEY_BLOODGROUP, bloodgroup);
            initialValues.put(KEY_DISEASENAME, disname);
            initialValues.put(KEY_WARDNO, wno);
            initialValues.put(KEY_CONTACT, phone);
            initialValues.put(KEY_MEDICINE, med);
            initialValues.put(KEY_CHIEF, chief);
            initialValues.put(KEY_DOC, phy);
            initialValues.put(KEY_NUR, nur);
            initialValues.put(KEY_LABTECH, lab);
       
           return db.insert(DATABASE_TABLE1, null, initialValues);
            
        }
       
        public boolean updateTitle(int id,String mPatientname, String mGender,
        		String mBloodgroup, String mDiseasename, String mWardno,String mContact,
        		String mMedicine, String mChief,String mDoctor,String mNurse ,String mLabtech ) {
					        	
        	ContentValues initialValues = new ContentValues();
    		initialValues.put(KEY_PATIENTNAME, mPatientname);
            initialValues.put(SEX, mGender);
            initialValues.put(KEY_BLOODGROUP, mBloodgroup);
            initialValues.put(KEY_DISEASENAME, mDiseasename);
            initialValues.put(KEY_WARDNO, mWardno);
            initialValues.put(KEY_CONTACT, mContact);
            initialValues.put(KEY_MEDICINE, mMedicine);
            initialValues.put(KEY_CHIEF, mChief);
            initialValues.put(KEY_DOC, mDoctor);
            initialValues.put(KEY_NUR, mNurse);
            initialValues.put(KEY_LABTECH, mLabtech);
    	
    		return db.update(DATABASE_TABLE1, initialValues,
    				KEY_ROWID + "=" + id, null) > 0;
        	
        }
        
        public Cursor getTitle(String userMatch){
    		Cursor cursor = db.rawQuery("SELECT _id, username, password, designation,experience,value FROM physicianlogin WHERE username LIKE ?",
    				new String[] { "%"
    						+ userMatch + "%" });
    		return cursor;
    		
        }
        
        public Cursor getpatientfordoctor(String searchP){
   		Cursor cursor = db.rawQuery("SELECT _id,patientname,sex,diseasename,chiefdoctor,doctor,nurse,labtech FROM PatientDetails WHERE doctor LIKE ?",
   				new String[] { "%"
   						+ searchP + "%" });
   		return cursor;
   		
       }
        public Cursor getpatientfornurse(String searchN){
       		Cursor cursor = db.rawQuery("SELECT _id,patientname,sex,diseasename,chiefdoctor,doctor,nurse,labtech FROM PatientDetails WHERE nurse LIKE ?",
       				new String[] { "%"
       						+ searchN + "%" });
       		return cursor;
       		
           }
        public Cursor getpatientforlab(String searchL){
       		Cursor cursor = db.rawQuery("SELECT _id,patientname,sex,diseasename,chiefdoctor,doctor,nurse,labtech FROM PatientDetails WHERE labtech LIKE ?",
       				new String[] { "%"
       						+ searchL + "%" });
       		return cursor;
       		
           }
        public Cursor getpatientforchief(String searchC){
       		Cursor cursor = db.rawQuery("SELECT _id,patientname,sex,diseasename,chiefdoctor,doctor,nurse,labtech FROM PatientDetails WHERE chiefdoctor LIKE ?",
       				new String[] { "%"
       						+ searchC + "%" });
       		return cursor;
       		
           }
       
       public Cursor getPatient(String searchpatient){
   		Cursor cursor = db.rawQuery("SELECT _id,patientname,sex,bloodgroup,diseasename,wardno,contact,medicine,chiefdoctor,doctor,nurse,labtech FROM PatientDetails WHERE patientname LIKE ?",
   				new String[] { "%"
   						+ searchpatient + "%" });
   		return cursor;
   		
       }
        public Cursor getDoctor(String searchdoc)
        {
        	Cursor cursor = db.rawQuery("SELECT _id,doctorname,designation FROM Doctors WHERE doctorname LIKE ?",
    				new String[] { "%"
    						+ searchdoc + "%" });
    		return cursor;
    		
        }
        public Cursor getnurse(String searchnur)
        {
        	Cursor cursor = db.rawQuery("SELECT _id,nursename,designation FROM Nurse WHERE nursename LIKE ?",
    				new String[] { "%"
    						+ searchnur+ "%" });
    		return cursor;
    		
        }
        public Cursor getlabtech(String searchlt)
        {
        	Cursor cursor = db.rawQuery("SELECT _id,labtechname,designation FROM Labtech WHERE labtechname LIKE ?",
    				new String[] { "%"
    						+ searchlt+ "%" });
    		return cursor;
    		
        }
        public Cursor getchief(String searchchief)
        {
        	Cursor cursor = db.rawQuery("SELECT _id,chiefdocname,designation FROM Chiefdoc WHERE chiefdocname LIKE ?",
    				new String[] { "%"
    						+ searchchief+ "%" });
    		return cursor;
    		
        }
       
          public boolean deleteTitle(long rowId) 
        {
            return db.delete(DATABASE_TABLE2, KEY_ROWID + 
            		"=" + rowId, null) > 0;
        }
        public Cursor getAlldetails() 
        {
        	return db.query(DATABASE_TABLE2, new String[] {KEY_ROWID, KEY_USERNAME,KEY_PASSWORD,KEY_DESIGNATION,
                    KEY_EXPERIENCE,
            		KEY_VALUE},
                    null, 
                    null, 
                    null, 
                    null, 
                    null);
        }
       public Cursor getAlldetails1() 
       {
            return db.query(DATABASE_TABLE1, new String[] {KEY_ROWID,KEY_PATIENTNAME, SEX,KEY_BLOODGROUP,
            KEY_DISEASENAME,
            KEY_WARDNO,
            KEY_CONTACT,
            KEY_MEDICINE,
            KEY_CHIEF,
            KEY_DOC,
            KEY_NUR,
            KEY_LABTECH},          
            null, 
            null, 
            null, 
            null,
            null);
}

public Cursor getAlldoc() 
{
	  return db.query(DATABASE_TABLE4, new String[] {
    		KEY_ROWID, 
    		KEY_DOCNAME,
            KEY_DESIGNATION},
            null, 
            null, 
            null, 
            null, 
            null);
}
public Cursor getAllnurse() 
{
	return db.query(DATABASE_TABLE5, new String[] {
    		KEY_ROWID, 
    		KEY_NURNAME,
            KEY_DESIGNATION},
            null, 
            null, 
            null, 
            null, 
            null);
}
public Cursor getAlllabtech() 
{
	

	  return db.query(DATABASE_TABLE6, new String[] {
	    		KEY_ROWID, 
	    		KEY_LABTECHNAME,
	            KEY_DESIGNATION},
	            null, 
	            null, 
	            null, 
	            null, 
	            null);
	}
public Cursor getAllchief() 
	{
		return db.query(DATABASE_TABLE7, new String[] {
		    		KEY_ROWID, 
		    		KEY_CHIEFNAME,
		            KEY_DESIGNATION},
		            null, 
		            null, 
		            null, 
		            null, 
		            null);
		}
public Cursor getdocpatient(String d)
{
	Cursor cursor = db.rawQuery("SELECT _id,patientname,sex,diseasename,physician FROM PatientDetails WHERE physician LIKE ?",
			new String[] { "%"
					+ d + "%" });
	return cursor;
}
}

	


