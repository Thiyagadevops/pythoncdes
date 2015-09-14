package com.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class nursepatientdetails extends Activity {
	String[] sex =
	{
		"Male","Female",
	};
	
	private EditText patientname;
	private Spinner sexspin;
	private EditText bloodgroup;
	private EditText diseasename;
	private EditText wardno;
	private EditText contact;
	private EditText medicine;
	private EditText chiefdoc;
	private EditText doc;
	private EditText nurse;
	private EditText labtech;
	private Button btok;
	private Button btcancel;
	DBAdapter db = new DBAdapter(this);
	String bnurse;
	int user;
	
    /** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);
        
        btok = (Button) findViewById(R.id.ok);
        btcancel = (Button) findViewById(R.id.cancel);
	    patientname = (EditText) findViewById(R.id.etpatient);
	    bloodgroup = (EditText) findViewById(R.id.etbloodgrp);
	    diseasename = (EditText) findViewById(R.id.etdiseasename);
	    wardno = (EditText) findViewById(R.id.etwardno);
	    contact = (EditText) findViewById(R.id.etphone);
	    medicine = (EditText) findViewById(R.id.etmedicine);
	    chiefdoc = (EditText) findViewById(R.id.etchief);	    
	    doc = (EditText) findViewById(R.id.etdoc);
	    nurse = (EditText) findViewById(R.id.etnurse);
	    labtech = (EditText) findViewById(R.id.etlabtech);
        sexspin = (Spinner) findViewById(R.id.etsexspin);

        Bundle bundle=this.getIntent().getExtras();
        bnurse = bundle.getString("nurse");
        user= bundle.getInt("trust");
        
	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
												android.R.layout.simple_spinner_item, sex);
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     sexspin.setAdapter(adapter);
     sexspin.setOnItemSelectedListener(new nOnItemSelectedListener());
     btcancel.setOnClickListener(new View.OnClickListener() 
     {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(nursepatientdetails.this,phynursedetail.class);
			i.putExtra("nurse",bnurse);
			i.putExtra("trust",user);
			startActivity(i);
			
		}
	});
	btok.setOnClickListener(new View.OnClickListener() 
{
	@Override
	public void onClick(View v) 
	{
		String pname = patientname.getText().toString();
		String bldgrp = bloodgroup.getText().toString();
		String disname = diseasename.getText().toString();
		String wno = wardno.getText().toString();
		String phone = contact.getText().toString();
		String med = medicine.getText().toString();
		String sspin = nOnItemSelectedListener.s;
		String cdoc = chiefdoc.getText().toString();
		String doctor = doc.getText().toString();
		String nur = nurse.getText().toString();
		String labt = labtech.getText().toString();

		if((pname) != null && (sspin)!= null && (wno)!= null && (bldgrp)!= null && (cdoc)!= null &&  (med)!= null
				&&  (doctor)!= null &&  (nur)!= null &&  (labt)!= null &&  (disname)!= null &&  (phone)!= null)
		{
	db.open();
	db.insertpatientdetails(pname,sspin,bldgrp,disname,wno,phone,med,cdoc,doctor,nur,labt);
	db.close();
	Toast.makeText(nursepatientdetails.this, "Data Entered successfully",Toast.LENGTH_LONG).show();
	Intent i=new Intent(nursepatientdetails.this,phynursedetail.class);
	i.putExtra("nurse",bnurse);
	i.putExtra("trust",user);
	startActivity(i);
		}
		else
		{	
			Toast.makeText(nursepatientdetails.this, "Fill all the details",Toast.LENGTH_LONG).show();
		}	
	}
});
    }
	
	public static class nOnItemSelectedListener implements OnItemSelectedListener
		    {
			static String s;
		    	public void onItemSelected(AdapterView<?> parent, View v,
							int pos, long l){
							
				
						// TODO Auto-generated method stub
					   s = (parent.getItemAtPosition(pos)).toString();
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						}
			}
}	
