package com.hospital;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProjectActivity extends Activity {
	
	
	private Button signedin,signup;
	private EditText etUsername;
	private EditText etPassword;
		
	DBAdapter db = new DBAdapter(this);
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        etUsername = (EditText) findViewById(R.id.etUser);
		etPassword = (EditText) findViewById(R.id.etPwd);
        signedin = (Button) findViewById(R.id.signedin);
		signup = (Button) findViewById(R.id.signup);
				
     signedin.setOnClickListener(new View.OnClickListener() 
        {
        	
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = etUsername.getText().toString();
				String password = etPassword.getText().toString();
				
				// ---get a title---
				
				if(username==null && password==null)
				{
					Toast.makeText(getApplicationContext(), "Please enter valid username and Password..", Toast.LENGTH_SHORT).show();	
				}
				else if(username!=null && password!=null)
				{
				db.open();
				Cursor c= db.getTitle(username);
								
				   if (c.moveToFirst()) 
				     {
					  do {
						int val = c.getInt(c.getColumnIndex("value"));
						
								    Intent  i = new Intent(ProjectActivity.this, EmployeeList.class);
								    i.putExtra("trust",val);
									startActivity(i);
									break;
		            	  } 
					   while (c.moveToNext());
					   }
				  db.close();
				}
				else
				{
				Toast.makeText(getApplicationContext(), "ERROR PAGE..", Toast.LENGTH_SHORT).show();	
				}
			}
		    });
    	
    	signup.setOnClickListener(new OnClickListener() 
    	{
		   
			public void onClick(View v) {
				Intent i=new Intent(ProjectActivity.this,Registration.class); 
				startActivity(i);
				}
		});
			}
        	}
    

