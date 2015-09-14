package com.hospital;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class chiefdetail extends Activity {

	private DBAdapter DBHelper = new DBAdapter(this);
	SQLiteDatabase db;
	
	EditText username;
	Button btnsearch;
	ListAdapter adapter;
	ListView List;
	ImageView image;
	EditText searchText;

	
	
	static ArrayList<String> list;
    static ArrayList<String> list1;
    static ArrayList<String> list2;
    int user;
    
    String PatientName;
	String Sex;
	String Bloodgroup;
	String Disease;
	String Wardno;
	String Contact;
	String Medicine;
	String Physician;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doc);
		image = (ImageView) findViewById(R.id.imagebutton);
		btnsearch = (Button) findViewById(R.id.searchButton);
		searchText = (EditText) findViewById(R.id.searchText);
		List = (ListView) findViewById(R.id.listview);
		DBHelper.open();
		fetchdata();
		DBHelper.close();
		setButtonClickListener();
		
		
	}

	private void setButtonClickListener() {
		btnsearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				list = new ArrayList<String>();
				list1 = new ArrayList<String>();
				
				list.clear();
				list1.clear();
				
				DBHelper.open();

				Cursor c = DBHelper.getnurse(searchText.getText().toString());

				if (c.moveToFirst()) {
					do {

						list.add(c.getString(c.getColumnIndex("nursename")));
						list1.add(c.getString(c.getColumnIndex("designation")));

					} while (c.moveToNext());

				}
				c.close();

				DBHelper.close();
				List.setAdapter(new doctorAdapter(getApplicationContext(),
						R.layout.doclist, list, list1));

				}
		});
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(chiefdetail.this,EmployeeList.class);
				i.putExtra("user", user);
				startActivity(i);

			}
		});


	}

	private void fetchdata() {

		list = new ArrayList<String>();
		list1 = new ArrayList<String>();
		
		list.clear();
		list1.clear();

		Cursor c = DBHelper.getAllchief();
		if (c.moveToFirst()) 
		{
			do {

				list.add(c.getString(c.getColumnIndex("chiefdocname")));
				list1.add(c.getString(c.getColumnIndex("designation")));
				
			} while (c.moveToNext());

		}
		c.close();
		List.setAdapter(new doctorAdapter(getApplicationContext(),
				R.layout.doclist, list, list1));
        
		List.setOnItemClickListener(new OnItemClickListener() 
		{
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int pos, long arg3) {
							// TODO Auto-generated method stub
							DBHelper.open();
							Object o = List.getItemAtPosition(pos);
							Cursor c = DBHelper.getTitle(o.toString());
							if (c.moveToFirst()) 
						     {
								do {
								      int doctorValue=c.getInt(c.getColumnIndex("value"));;
								      int userValue = EmployeeList.trust;
								      int trust = doctorValue + userValue;
								      if(trust>=3)
								      {
								          Intent i = new Intent(chiefdetail.this, phychiefdetail.class);
										  i.putExtra("chief",o.toString());
										  i.putExtra("trust", trust);
										  startActivity(i);  
									     }
								         else
								         {
								    	  Toast.makeText(getApplicationContext(), "you are not authorised to access", Toast.LENGTH_SHORT).show();	
								         }
								  }while (c.moveToNext());
							 }
							DBHelper.close();
							
						}
					});

          }	
	}
