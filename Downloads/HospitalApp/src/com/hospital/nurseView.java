package com.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class nurseView extends Activity {
	private TextView patientname;
	private TextView sexspin;
	private TextView bloodgroup;
	private TextView diseasename;
	private TextView wardno;
	private TextView contact;
	private TextView medicine;
	private TextView chiefdoctor;
	private TextView doctor;
	private TextView nurse;
	private TextView labtech;
	ImageView imgback;
	String bnurse;
	
	
	int user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patientview);
		   
		    imgback=(ImageView)findViewById(R.id.back);
		    patientname = (TextView) findViewById(R.id.etpatient);
		    sexspin = (TextView) findViewById(R.id.etsexspin);
		    bloodgroup = (TextView) findViewById(R.id.etbloodgrp);
		    diseasename = (TextView) findViewById(R.id.etdiseasename);
		    wardno = (TextView) findViewById(R.id.etwardno);
		    contact = (TextView) findViewById(R.id.etphone);
		    medicine = (TextView) findViewById(R.id.etmedicine);
		    chiefdoctor = (TextView) findViewById(R.id.etchiefdoc);
		    doctor = (TextView) findViewById(R.id.etdoc);
		    nurse = (TextView) findViewById(R.id.etnur);
		    labtech = (TextView) findViewById(R.id.etlab);
		    
		Bundle bundle = this.getIntent().getExtras();
		bnurse = bundle.getString("nurse");
		user=bundle.getInt("trust");
		
		
		patientname.setText(bundle.getString("patientname"));
		sexspin.setText(bundle.getString("sex"));
		bloodgroup.setText(bundle.getString("bloodgroup"));
		diseasename.setText(bundle.getString("disease"));
		wardno.setText(bundle.getString("wardno"));
		contact.setText(bundle.getString("contact"));
		medicine.setText(bundle.getString("medicine"));
		chiefdoctor.setText(bundle.getString("chiefdoctor"));
		doctor.setText(bundle.getString("doctor"));
		nurse.setText(bundle.getString("nurse"));
		labtech.setText(bundle.getString("labtech"));
	
     	imgback.setOnClickListener(new View.OnClickListener() {
    	@Override
    	public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    		Intent i = new Intent(nurseView.this, phynursedetail.class);
    		i.putExtra("nurse",bnurse);
    		i.putExtra("trust",user);
    	    startActivity(i);
    	}
    });
}
}
