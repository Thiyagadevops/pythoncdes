package com.hospital;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class patientAdapter extends ArrayAdapter<String> {
	private LayoutInflater mInflater;

	private String[] mPatient = null;
	private String[] mSex = null;
	private String[] mDisease = null;
	

	private int mViewResourceId;

	public patientAdapter(Context ctx, int viewResourceId,ArrayList<String> list, ArrayList<String> list1,ArrayList<String> list2) {
		super(ctx, viewResourceId);

		mInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
			String[] Patientname = list.toArray(new String[list.size()]);
			String[] Sex = list1.toArray(new String[list1.size()]);
			String[] Disease = list2.toArray(new String[list2.size()]);
			
			mPatient = Patientname;
			mSex = Sex;
			mDisease = Disease;
			
		

		mViewResourceId = viewResourceId;
	}

	@Override
	public int getCount() {
		return mPatient.length;
	}

	@Override
	public String getItem(int position) {
		return mPatient[position];
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(mViewResourceId, null);

		TextView patient =(TextView) convertView.findViewById(R.id.phyname);
		TextView sex = (TextView) convertView.findViewById(R.id.physex);
		TextView disease = (TextView) convertView.findViewById(R.id.phydisease);

		long id = getItemId(position);
		patient.setText(mPatient[position]);
		sex.setText(mSex[position]);
		disease.setText(mDisease[position]);
		return convertView;
	}

	
	

}
