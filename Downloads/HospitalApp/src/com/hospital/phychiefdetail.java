package com.hospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class phychiefdetail extends Activity {

	private DBAdapter DBHelper = new DBAdapter(this);
	SQLiteDatabase db;
	ImageButton addPatient;
	ImageView imgback;
	EditText username;
	Button btnnext;
	ListAdapter adapter;
	ListView patientList;
	EditText searchText;
	String chief;
	int user;

	static ArrayList<String> list;
    static ArrayList<String> list1;
    static ArrayList<String> list2;
	
    String PatientName;
	String Sex;
	String Bloodgroup;
	String Disease;
	String Wardno;
	String Contact;
	String Medicine;
	String Chiefdoctor,Doctor,Nurse,Labtechni;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phydetail);
		addPatient = (ImageButton) findViewById(R.id.addPatient);
		imgback=(ImageView)findViewById(R.id.back);
		btnnext = (Button) findViewById(R.id.searchButton);
		searchText = (EditText) findViewById(R.id.searchText);
		patientList = (ListView) findViewById(R.id.listview);
		
		Bundle bundle=this.getIntent().getExtras();
	    chief = bundle.getString("chief");
	    user=bundle.getInt("trust");
		DBHelper.open();
		fetchdata();
		DBHelper.close();
		setButtonClickListener();
		setPatientButtonClickListener();
	}

	private void setPatientButtonClickListener() {
		addPatient.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(phychiefdetail.this, chiefpatientdetails.class);
				i.putExtra("chief",chief);
				i.putExtra("trust",user);
				startActivity(i);

			}
		});
	}

	private void setButtonClickListener() {
		btnnext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				list = new ArrayList<String>();
				list1 = new ArrayList<String>();
				list2 = new ArrayList<String>();
				list.clear();
				list1.clear();
				list2.clear();

				DBHelper.open();

				Cursor c = DBHelper.getPatient(searchText.getText().toString());

				if (c.moveToFirst()) {
					do {

						list.add(c.getString(c.getColumnIndex("patientname")));
						list1.add(c.getString(c.getColumnIndex("sex")));
						list2.add(c.getString(c.getColumnIndex("diseasename")));

					} while (c.moveToNext());

				}
				c.close();

				DBHelper.close();

				patientList.setAdapter(new patientAdapter(
						getApplicationContext(), R.layout.phy_list, list,
						list1, list2));
			}
		});
		imgback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(phychiefdetail.this,EmployeeList.class);
				i.putExtra("trust",user);
				startActivity(i);
			}	
		});

	}

	private void fetchdata() {
        
		list = new ArrayList<String>();
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		list.clear();
		list1.clear();
		list2.clear();
		
        Cursor cursor = DBHelper.getpatientforchief(chief);
		if (cursor.moveToFirst()) {
			do {
				
				list.add(cursor.getString(cursor.getColumnIndex("patientname")));
				list1.add(cursor.getString(cursor.getColumnIndex("sex")));
				list2.add(cursor.getString(cursor.getColumnIndex("diseasename")));

			} while (cursor.moveToNext());

		}
		cursor.close();
		patientList.setAdapter(new patientAdapter(getApplicationContext(),
				R.layout.phy_list, list, list1, list2));
        
		patientList.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id)
			{
			if((user==6)||(user==7)||(user==8))
			{
			Object o = patientList.getItemAtPosition(position);

			int ID = position + 1;
			DBHelper.open();

			Cursor c = DBHelper.getPatient(o.toString());

			if (c.moveToFirst()) {
			do {

			PatientName = c.getString(c.getColumnIndex("patientname"));
			Sex = c.getString(c.getColumnIndex("sex"));
			Disease = c.getString(c.getColumnIndex("diseasename"));
			Wardno=c.getString(c.getColumnIndex("wardno"));
			Contact=c.getString(c.getColumnIndex("contact"));
			Medicine=c.getString(c.getColumnIndex("medicine"));
			Chiefdoctor=c.getString(c.getColumnIndex("chiefdoctor"));
			Doctor=c.getString(c.getColumnIndex("doctor"));
			Nurse=c.getString(c.getColumnIndex("nurse"));
			Labtechni=c.getString(c.getColumnIndex("labtech"));
			Bloodgroup=c.getString(c.getColumnIndex("bloodgroup"));

			} while (c.moveToNext());

			}
			c.close();

			DBHelper.close();
			Intent i = new Intent(phychiefdetail.this, chiefedit.class);
			Bundle bundle = new Bundle();
			bundle.putInt("_id", ID);
			bundle.putString("patientname", PatientName);
			bundle.putString("sex", Sex);
			bundle.putString("disease", Disease);
			bundle.putString("wardno", Wardno);
			bundle.putString("contact", Contact);
			bundle.putString("medicine", Medicine);
			bundle.putString("chiefdoctor",Chiefdoctor);
			bundle.putString("doctor",Doctor);
			bundle.putString("nurse",Nurse);
			bundle.putString("labtech",Labtechni);
			bundle.putString("bloodgroup", Bloodgroup);
			i.putExtras(bundle);
			i.putExtra("chief",chief);
			i.putExtra("trust",user);
			startActivity(i);
			return true;
			}
				
			return false;
			}
			});

        patientList.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) 
			{
				if((user==4)||(user==5)||(user==6)||(user==7)||(user==8))
				{
				Object o = patientList.getItemAtPosition(position);

				DBHelper.open();

				Cursor c = DBHelper.getPatient(o.toString());

				if (c.moveToFirst()) {
					do {

					String	rPatientName = c.getString(c
								.getColumnIndex("patientname"));
					String rSex = c.getString(c.getColumnIndex("sex"));
					String rBloodgroup = c.getString(c.getColumnIndex("bloodgroup"));
					String rDisease = c.getString(c.getColumnIndex("diseasename"));
					String rWardno = c.getString(c.getColumnIndex("wardno"));
					String rContact = c.getString(c.getColumnIndex("contact"));
					String 	rMedicine = c.getString(c.getColumnIndex("medicine"));
					String 	rChiefdoctor = c.getString(c.getColumnIndex("chiefdoctor"));
					String 	rDoctor = c.getString(c.getColumnIndex("doctor"));
					String 	rNurse = c.getString(c.getColumnIndex("nurse"));
					String 	rLabtech = c.getString(c.getColumnIndex("labtech"));
					
					Intent i = new Intent(phychiefdetail.this, chiefView.class);
					Bundle bundle = new Bundle();
					
					bundle.putString("patientname", rPatientName);
					bundle.putString("sex", rSex);
					bundle.putString("bloodgroup", rBloodgroup);
					bundle.putString("disease", rDisease);
					bundle.putString("wardno", rWardno);
					bundle.putString("contact", rContact);
					bundle.putString("medicine", rMedicine);
					bundle.putString("chiefdoctor", rChiefdoctor);
					bundle.putString("doctor", rDoctor);
					bundle.putString("nurse", rNurse);
					bundle.putString("labtech", rLabtech);
					i.putExtras(bundle);
					i.putExtra("chief",chief);
					i.putExtra("trust",user);
				
					startActivity(i);

					} while (c.moveToNext());

				}
				
				c.close();
                DBHelper.close();
			}	
			}
			});
		}
	
	
		}

