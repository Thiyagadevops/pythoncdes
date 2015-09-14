package com.hospital;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class doctorAdapter extends ArrayAdapter<String> {
	private LayoutInflater mInflater;

	private String[] mdoc = null;
	private String[] mdesig = null;
	

	private int mViewResourceId;

	public doctorAdapter(Context ctx, int viewResourceId,ArrayList<String> list, ArrayList<String> list1) {
		super(ctx, viewResourceId);

		mInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
			String[] Doctorname = list.toArray(new String[list.size()]);
			String[] Designation = list1.toArray(new String[list1.size()]);
	
			
			mdoc = Doctorname;
			mdesig = Designation;
         
			mViewResourceId = viewResourceId;
	}

	@Override
	public int getCount() {
		return mdoc.length;
	}

	@Override
	public String getItem(int position) {
		return mdoc[position];
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(mViewResourceId, null);

		TextView doctor =(TextView) convertView.findViewById(R.id.docname);
		TextView design = (TextView) convertView.findViewById(R.id.docdesg);
		

		long id = getItemId(position);
		doctor.setText(mdoc[position]);
		design.setText(mdesig[position]);
		return convertView;
	}

	
	

}











