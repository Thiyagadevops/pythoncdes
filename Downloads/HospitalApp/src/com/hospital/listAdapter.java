package com.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class listAdapter extends ArrayAdapter<String> {
	
	Context context; 
    int layoutResourceId;
	String emp[] =null;

	public listAdapter(Context context, int layoutResourceId,String[] emp) {
		super(context, layoutResourceId,emp);
		// TODO Auto-generated constructor stub
		this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.emp = emp;
	}
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
	    
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(layoutResourceId, null);
      
		TextView name =(TextView) convertView.findViewById(R.id.name);
	      
		   String e = emp[position];
	       name.setText(e);
	       return convertView;
	}
	
}
