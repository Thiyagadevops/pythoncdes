 package com.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends Activity
{
	String[] labours = 
	{
			"Nurse","Lab Technician","Doctor","Chief Doctor",
	};
	String[] staffs =
	{
	              "Fresher","Certified","Experienced","Certified & Experienced",
	};
			
	private Button btnregister;
	private Button btnreset;
	private EditText etUsernm;
	private EditText etpwd;
	private EditText etCPwd;
	private Spinner spin;
	private Spinner sp;
	DBAdapter db = new DBAdapter(this);

 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        etUsernm = (EditText) findViewById(R.id.Usernm);
		etpwd = (EditText) findViewById(R.id.pwd);
		etCPwd = (EditText) findViewById(R.id.pwd2);
		btnregister = (Button) findViewById(R.id.register);
		btnreset = (Button) findViewById(R.id.reset);
		spin = (Spinner) findViewById(R.id.spinner1);
		sp =(Spinner) findViewById(R.id.spinner2);
	 // Spinner s = (Spinner) findViewById(R.id.spinner1);
     //  s.setOnItemSelectedListener((OnItemSelectedListener) this);
       // compare();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                           android.R.layout.simple_spinner_item, labours);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
       
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
                                          android.R.layout.simple_spinner_item, staffs);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapt);
   
    spin.setOnItemSelectedListener(new MyOnItemSelectedListener());
    sp.setOnItemSelectedListener(new MyOnItemSelectedListener1());
    btnregister.setOnClickListener(new View.OnClickListener() 
    {
		@Override
		public void onClick(View v) 
		{
			String Username = etUsernm.getText().toString();
			String Password = etCPwd.getText().toString();
			String CPwd = etpwd.getText().toString();
			String Designation = MyOnItemSelectedListener.des;
			String Experience = MyOnItemSelectedListener1.exp;
			// TODO Auto-generated method stub
			
			db.open();
			if((Password!=null && Username!=null) && (Password.equals(CPwd)))
			{
				if((Experience) != null && (Experience=="Fresher"))
				{
	 		            db.insertuser(Username,Password,Designation,Experience,1 );
	 		     if(Designation=="Doctor")
	 		     {
	 		    	 	db.insertdoc(Username, Designation, Experience,1);
	 		     }
	 		     else if(Designation=="Nurse")
	 		     {
		 		    	db.insertnur(Username, Designation, Experience,1);
		 		 }
	 		     else if(Designation=="Lab Technician")
	 		     {
		 		    	db.insertlab(Username, Designation, Experience,1);
		 		 }
	 		     else if(Designation=="Chief Doctor")
	 		     {
		 		    	db.insertcdoc(Username, Designation, Experience,1);
		 		 }
			db.close();	
			Toast.makeText(Registration.this, "Registered successfully",Toast.LENGTH_LONG).show();
			Intent i=new Intent(Registration.this,ProjectActivity.class); 
			startActivity(i);
				}
				
				else if((Experience) != null && (Experience=="Certified"))
				{
	 		db.insertuser(Username,Password,Designation,Experience,2 );
	 		       if(Designation=="Doctor")
	 		       {
	 		    	   db.insertdoc(Username, Designation, Experience,2);
	 		       }
	 		       else if(Designation=="Nurse")
	 		       {
	 		    	   db.insertnur(Username, Designation, Experience,2);
	 		       }
	 		       else if(Designation=="Lab Technician")
	 		       {
	 		    	   db.insertlab(Username, Designation, Experience,2);
	 		       }
	 		       else if(Designation=="Chief Doctor")
	 		       {
	 		    	   db.insertcdoc(Username, Designation, Experience,2);
	 		       }
			db.close();	
			Toast.makeText(Registration.this, "Registered successfully",Toast.LENGTH_LONG).show();
			Intent i=new Intent(Registration.this,ProjectActivity.class); 
			startActivity(i);
				}
				
				else if((Experience) != null && (Experience=="Experienced"))
				{
			db.insertuser(Username,Password,Designation,Experience,3 );
			 	if(Designation=="Doctor")
			 	{
			 		db.insertdoc(Username, Designation, Experience,3);
			 	}
			 	else if(Designation=="Nurse")
			 	{
	 		    	db.insertnur(Username, Designation, Experience,3);
			 	}
			 	else if(Designation=="Lab Technician")
			 	{
	 		    	db.insertlab(Username, Designation, Experience,3);
			 	}
			 	else if(Designation=="Chief Doctor")
			 	{
	 		    	db.insertcdoc(Username, Designation, Experience,3);
			 	}
			db.close();	
			Toast.makeText(Registration.this, "Registered successfully",Toast.LENGTH_LONG).show();
			Intent i=new Intent(Registration.this,ProjectActivity.class); 
			startActivity(i);
				}
				
				else if((Experience) != null && (Experience=="Certified & Experienced"))
				{
			db.insertuser(Username,Password,Designation,Experience,4 );
			 	if(Designation=="Doctor")
			 	{
			 		db.insertdoc(Username, Designation, Experience,4);
			 	}
			 	else if(Designation=="Nurse")
			 	{
	 		    	db.insertnur(Username, Designation, Experience,4);
			 	}
			 	else if(Designation=="Lab Technician")
			 	{
	 		    	db.insertlab(Username, Designation, Experience,4);
			 	}
			 	else if(Designation=="Chief Doctor")
			 	{
	 		    	db.insertcdoc(Username, Designation, Experience,4);
			 	}
			db.close();	
			Toast.makeText(Registration.this, "Registered successfully",Toast.LENGTH_SHORT).show();
			Intent i=new Intent(Registration.this,ProjectActivity.class); 
			startActivity(i);
				}
			}
			else
			Toast.makeText(getBaseContext(), "Please enter Valid User name and password", Toast.LENGTH_SHORT).show();
			}
		}); 
	
		
	btnreset.setOnClickListener(new OnClickListener() {
        public void onClick(View v) 
        {
           etUsernm.setText("");
           etpwd.setText("");
           etCPwd.setText("");
        }
        });

    }
    
    public static class MyOnItemSelectedListener implements OnItemSelectedListener
    {
    	static String des;
    	public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long l){
					
		
				// TODO Auto-generated method stub
			   des = (parent.getItemAtPosition(pos)).toString();
			    //Toast.makeText(getBaseContext(),"your designation is:" +labours[index],Toast.LENGTH_SHORT).show();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				}
	}
   
   public static class MyOnItemSelectedListener1 implements OnItemSelectedListener
   {
   	
    static String exp;
   	public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long l){
					
		
				// TODO Auto-generated method stub
			   exp = (parent.getItemAtPosition(pos)).toString();
			   
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				}
	 }
  
   
}


	           
   


   