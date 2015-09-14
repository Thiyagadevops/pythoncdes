package com.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class EmployeeList extends Activity {
	
	protected String[] employees = {"Nurse","Doctor","Lab Technician","Chief doctor"};
    private ListView employeeList;
    Button signout;
    
    static int trust;
    /** Called when the activity is first created. */
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
       
       signout = (Button) findViewById(R.id.signout);
       employeeList = (ListView) findViewById(R.id.listV);
       employeeList.setAdapter(new listAdapter(getApplicationContext(),
				R.layout.listviw,employees));
       
       Bundle bundle=this.getIntent().getExtras();
       trust= bundle.getInt("trust");                       
       
       employeeList = (ListView) findViewById(R.id.listV);
       employeeList.setAdapter(new listAdapter(getApplicationContext(),
                       				R.layout.listviw,employees));
                            
       employeeList.setOnItemClickListener(new OnItemClickListener() {
    	   @Override
    	   public void onItemClick(AdapterView<?> a, View v, int position, long id) {
    		   Object o=employeeList.getItemAtPosition(position);
    		   if(o=="Doctor")
    		   {
    			   Intent i = new Intent(EmployeeList.this, docdetail.class);
    			   i.putExtra("trust",trust);
   				   startActivity(i);
    		   }
    		   else if(o=="Nurse")
    		   {
    			   Intent i = new Intent(EmployeeList.this, nursedetail.class);
    			   i.putExtra("trust",trust);
   				   startActivity(i);
    		   }
    		   else if(o=="Lab Technician")
    		   {
    			   Intent i = new Intent(EmployeeList.this, labtechnician.class);
    			   i.putExtra("trust",trust);
   				   startActivity(i);
    		   }
    		   else if(o=="Chief doctor")
    		   {
    			   Intent i = new Intent(EmployeeList.this, chiefdetail.class);
    			   i.putExtra("trust",trust);
   				   startActivity(i);
    		   }
    	   }
           });
       signout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(EmployeeList.this, ProjectActivity.class);
 			   
				   startActivity(i);
			}
        });
}
}