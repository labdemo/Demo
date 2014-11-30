package com.demo.fragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.demo.xlistview.XListView;
import com.demo.xlistview.XListView.IXListViewListener;
import com.example.demo.R;
import com.example.demo.ReserveActivity;

public class ReserveFragment extends Fragment implements IXListViewListener {
	private SimpleDateFormat dateFormatter;
	private Spinner equipmentGroup,currentStateGroup,timeGroup;
	private View v;
	private ArrayAdapter<CharSequence> equipmentGroupAdapter,currentStateAdapter,timeGroupAdapter;
	private SimpleAdapter listAdapter;
	private ArrayList<HashMap<String, Object>> itemArrayList;
	private XListView reserveList;
	private Handler handler;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.reserve_fragment, container, false);
		initViews();
		initEvents();
		loadData();
		
		handler  = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what==1){
					loadData();
					setTime();
					listAdapter.notifyDataSetChanged();
					
					reserveList.stopRefresh();
				}
			}
		};
		
		
		return v;
	}
	
	
	
	//获取数据
	private void loadData(){
		Log.i("testout", "----->loadingData");
		for(int i=0;i<15;i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemStateText","可预约");
			itemArrayList.add(map);
		}
		
	}
	
	private void initViews(){
		reserveList = (XListView)v.findViewById(R.id.reserveList);
		equipmentGroup = (Spinner)v.findViewById(R.id.equipmentGroup);
		currentStateGroup = (Spinner)v.findViewById(R.id.currentStateGroup);
		timeGroup = (Spinner)v.findViewById(R.id.timeGroup);
	}
	private void initEvents(){
		//加载Spinner布局以及设置Adapter
		equipmentGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.equipmentgroup, R.layout.spinner_text);
		equipmentGroup.setAdapter(equipmentGroupAdapter);
		currentStateAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.stategroup, R.layout.spinner_text);
		currentStateGroup.setAdapter(currentStateAdapter);
		timeGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.timegroup, R.layout.spinner_text);
		timeGroup.setAdapter(timeGroupAdapter);

		//监听器
		equipmentGroup.setOnItemSelectedListener(new equipmentGroupListener());
		currentStateGroup.setOnItemSelectedListener(new currentStateGroupListener());
		timeGroup.setOnItemSelectedListener(new timeGroupListener());

		equipmentGroup.setOnItemSelectedListener(new equipmentGroupListener());
		currentStateGroup.setOnItemSelectedListener(new currentStateGroupListener());
		timeGroup.setOnItemSelectedListener(new timeGroupListener());
		
		itemArrayList = new ArrayList<HashMap<String,Object>>();
		listAdapter = new SimpleAdapter(getActivity(), itemArrayList , R.layout.listview_reserve,
				new String []{"itemStateText"},
        		new int[]{R.id.itemStateText});
		reserveList.setXListViewListener(this);
		reserveList.setAdapter(listAdapter);
		reserveList.setOnItemClickListener(new itemClickListener());
		
		dateFormatter = new SimpleDateFormat("MM月dd日   HH:mm:ss     ");
	}
	
	//LisiView Item Listener
	class itemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(getActivity(), ReserveActivity.class);
			
			startActivity(intent);
		}
	}
	
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Log.i("testout", "------------>onRefresh");
					Thread.sleep(1000 * 2);
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	private void setTime(){
		Date date = new Date(System.currentTimeMillis());
		String time = "上次刷新时间：" + dateFormatter.format(date);
		reserveList.setRefreshTime(time);
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

}
