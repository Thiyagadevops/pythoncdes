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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class docdetail extends Activity {

	private DBAdapter DBHelper = new DBAdapter(this);
	SQLiteDatabase db;
	
	EditText username;
	Button btnsearch;
	ImageView img;
	ListAdapter adapter;
	ListView List;
	EditText searchText;
     
    int trust;
	
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
	String Physician;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doc);
		btnsearch = (Button) findViewById(R.id.searchButton);
		img = (ImageView) findViewById(R.id.imagebutton);
		searchText = (EditText) findViewById(R.id.searchText);
		List = (ListView) findViewById(R.id.listview);
		DBHelper.open();
		fetchdata();
		DBHelper.close();
		setButtonClickListener();
		
		Bundle bundle=this.getIntent().getExtras();
	    trust= bundle.getInt("trust"); 
		
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

				Cursor c = DBHelper.getDoctor(searchText.getText().toString());

				if (c.moveToFirst()) {
					do {

						list.add(c.getString(c.getColumnIndex("doctorname")));
						list1.add(c.getString(c.getColumnIndex("designation")));

					} while (c.moveToNext());

				}
				c.close();

				DBHelper.close();
				List.setAdapter(new doctorAdapter(getApplicationContext(),
						R.layout.doclist, list, list1));

				}
		});
		
		img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(docdetail.this,EmployeeList.class);
				i.putExtra("trust",trust);
				startActivity(i);
			}	
		});
	}

	private void fetchdata() {

		list = new ArrayList<String>();
		list1 = new ArrayList<String>();
		
		list.clear();
		list1.clear();

		Cursor c = DBHelper.getAlldoc();
		if (c.moveToFirst()) 
		{
			do {

				list.add(c.getString(c.getColumnIndex("doctorname")));
				list1.add(c.getString(c.getColumnIndex("designation")));
				
			} while (c.moveToNext());

		}
		c.close();
		List.setAdapter(new doctorAdapter(getApplicationContext(),
				R.layout.doclist, list, list1));
        
		List.setOnItemClickListener(new OnItemClickListener() 
		{
						@Override
						public void onItemClick(AdapterView<?> a, View v,
								int position, long l) {
							// TODO Auto-generated method stub
							DBHelper.open();
							Object o = List.getItemAtPosition(position);
							Cursor c = DBHelper.getTitle(o.toString());
							if (c.moveToFirst()) 
						     {
							  do {
							      int doctorValue=c.getInt(c.getColumnIndex("value"));;
							      int userValue = EmployeeList.trust;
							      int trust = doctorValue + userValue;
							      if(trust>=3)
							      {
							          Intent i = new Intent(docdetail.this, phydetail.class);
									  i.putExtra("phy",o.toString());
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
