package com.demo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.demo.R;

public class ReserveFragment extends Fragment {
	private Spinner equipmentGroup,currentStateGroup,timeGroup;
	private ArrayAdapter<CharSequence> equipmentGroupAdapter,currentStateAdapter,timeGroupAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.reserve_fragment, container, false);
		equipmentGroup = (Spinner)v.findViewById(R.id.equipmentGroup);
		currentStateGroup = (Spinner)v.findViewById(R.id.currentStateGroup);
		timeGroup = (Spinner)v.findViewById(R.id.timeGroup);
		
		equipmentGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.equipmentgroup, R.layout.spinner_text);
		equipmentGroup.setAdapter(equipmentGroupAdapter);
		currentStateAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.stategroup, R.layout.spinner_text);
		currentStateGroup.setAdapter(currentStateAdapter);
		timeGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.timegroup, R.layout.spinner_text);
		timeGroup.setAdapter(timeGroupAdapter);
		equipmentGroup.setOnItemSelectedListener(new equipmentGroupListener());
		currentStateGroup.setOnItemSelectedListener(new currentStateGroupListener());
		timeGroup.setOnItemSelectedListener(new timeGroupListener());
		return v;
	}
	

	
	private class equipmentGroupListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Log.i("testout", "equipmentAdapter----------->"+equipmentGroupAdapter.getItem(position));
			Toast.makeText(getActivity(), "您选择了：" + equipmentGroupAdapter.getItem(position), Toast.LENGTH_SHORT).show();		
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}
	private class currentStateGroupListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Log.i("testout", "currentstateAdapter----------->"+currentStateAdapter.getItem(position));

		}
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class timeGroupListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/*	class SpinnerGroupListener implements OnItemSelectedListener{
	@Override
	public void onItemSelected(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.equipmentGroup:
			Log.i("testout", "equipmentAdapter----------->"+equipmentGroupAdapter.getItem(position));
			Toast.makeText(getActivity(), "您选择了：" + equipmentGroupAdapter.getItem(position), Toast.LENGTH_SHORT).show();
			break;
		case R.id.currentStateGroup:
			
			break;
		case R.id.timeGroup:
			
			break;
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}*/

}
