package com.roboshed.roommateapp.ui;

import java.util.List;

import com.roboshed.roommateapp.R;
import com.roboshed.roommateapp.data.Bill;
import com.roboshed.roommateapp.data.MockUserData;
import com.roboshed.roommateapp.util.DateUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BillActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.bill_list);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		ListView billList = (ListView) findViewById(R.id.billList);
		billList.setAdapter(new BillListAdapter(getApplicationContext(), MockUserData.getInstance().getBillList()));
	}
	
	private class BillListAdapter extends ArrayAdapter<Bill>
	{
		public BillListAdapter(Context context, List<Bill> objects)
		{
			super(context, 0, MockUserData.getInstance().getBillList());			
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.bill_row, null);
			}
			
			Bill bill = getItem(position);
		
			TextView amount = (TextView) v.findViewById(R.id.amount);
			amount.setText(Double.toString(bill.amount));
			
			TextView billType = (TextView) v.findViewById(R.id.billType);
			billType.setText(bill.billType.toString());
			
			TextView date = (TextView) v.findViewById(R.id.date);
			date.setText(DateUtil.getDateFromEpoch(bill.date));
			
			TextView paidByUser = (TextView) v.findViewById(R.id.paidByUser);
			paidByUser.setText(MockUserData.getInstance().getUserNameFromId(bill.userId));
			
			return v;
		}
	}
}
