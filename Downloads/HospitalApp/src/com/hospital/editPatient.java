package com.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class editPatient extends Activity {
	private EditText patientname;
	private EditText bloodgroup;
	private EditText diseasename;
	private EditText wardno;
	private EditText contact;
	private EditText medicine;
	private EditText chiefdoc;
	private EditText doc;
	private EditText nurse;
	private EditText labtech;
	private EditText gender;
	private Button btok;
	private Button btcancel;
	private DBAdapter DBHelper = new DBAdapter(this);
	public String mPatientname, mBloodgroup, mDiseasename, mWardno, mContact,
			mMedicine, mChief,mDoctor,mNurse,mLabtech, mGender;
	String phy;
	int Id,user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editpatient);
		btok = (Button) findViewById(R.id.ok);
		btcancel = (Button) findViewById(R.id.cancel);
		patientname = (EditText) findViewById(R.id.etpatient);
		bloodgroup = (EditText) findViewById(R.id.etbloodgrp);
		diseasename = (EditText) findViewById(R.id.etdiseasename);
		wardno = (EditText) findViewById(R.id.etwardno);
		contact = (EditText) findViewById(R.id.etphone);
		medicine = (EditText) findViewById(R.id.etmedicine);
		chiefdoc = (EditText) findViewById(R.id.etchiefdoc);	    
	    doc = (EditText) findViewById(R.id.etdoc);
	    nurse = (EditText) findViewById(R.id.etnurse);
	    labtech = (EditText) findViewById(R.id.etlab);
		gender = (EditText) findViewById(R.id.gender);
		Bundle bundle=this.getIntent().getExtras();
	    phy = bundle.getString("phy");
	    user= bundle.getInt("trust");
		bindData();
		setButtonClickListener();
	}
	private void setButtonClickListener() {
		btcancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(editPatient.this, phydetail.class);
				i.putExtra("phy",phy);
				i.putExtra("trust", user);
				startActivity(i);

			 }
		});
		
		btok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				getData();

				Intent i = new Intent(editPatient.this, phydetail.class);
				i.putExtra("phy",phy);
				i.putExtra("trust", user);
				startActivity(i);

			 }
         
			private void getData() {
			
				String mPatientname = patientname.getText().toString();
				String mBloodgroup = bloodgroup.getText().toString();
				String mDiseasename = diseasename.getText().toString();
				String mWardno = wardno.getText().toString();
				String mContact = contact.getText().toString();
				String mMedicine = medicine.getText().toString();
				String mChief = chiefdoc.getText().toString();
				String mDoctor = doc.getText().toString();
				String mNurse = nurse.getText().toString();
				String mLabtech = labtech.getText().toString();
				String mGender = gender.getText().toString();
				DBHelper.open();
				DBHelper.updateTitle(Id, mPatientname, mGender, mBloodgroup,
						mDiseasename, mWardno, mContact, mMedicine,mChief, mDoctor,
						mNurse,mLabtech);
				DBHelper.close();

			}
		});
		
	}

	private void bindData() {
		Bundle bundle = this.getIntent().getExtras();
		Id =  bundle.getInt("_id");
		patientname.setText(bundle.getString("patientname"));
		gender.setText(bundle.getString("sex"));
		diseasename.setText(bundle.getString("disease"));
		wardno.setText(bundle.getString("wardno"));
		medicine.setText(bundle.getString("medicine"));
		contact.setText(bundle.getString("contact"));
		chiefdoc.setText(bundle.getString("chiefdoctor"));
		doc.setText(bundle.getString("doctor"));
		nurse.setText(bundle.getString("nurse"));
		labtech.setText(bundle.getString("labtech"));
		bloodgroup.setText(bundle.getString("bloodgroup"));

	}

}
