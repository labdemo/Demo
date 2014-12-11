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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.demo.xlistview.XListView;
import com.demo.xlistview.XListView.IXListViewListener;
import com.example.demo.MainActivity;
import com.example.demo.MainActivity.MyOnTouchListener;
import com.example.demo.R;
import com.example.demo.ReserveActivity;

public class ReserveFragment extends Fragment implements IXListViewListener {
	private View v;
	//ImageView and Animation
	private ImageView equipmentGroupImage, currentStateGroupImage, timeGroupImage;
	private Animation spinnerImageUp,spinnerImageDown;
	//日期格式
	private SimpleDateFormat dateFormatter;
	//Spinner
	private boolean isSpinnerExpand = false;
	private SpinnerOnTouchListener spinnerOnTouchListener;
	private Spinner equipmentGroup;
	private Spinner  currentStateGroup, timeGroup;
	private ArrayAdapter<CharSequence> equipmentGroupAdapter, currentStateAdapter, timeGroupAdapter;
	//ListView
	private SimpleAdapter listAdapter;
	private ArrayList<HashMap<String, Object>> itemArrayList;
	private XListView reserveList;
	//handler
	private Handler handler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.reserve_fragment, container, false);
		initViews();
		initEvents();
		setAnimations();
		loadData();
		((MainActivity) getActivity()).registerMyOnTouchListener(myOnTouchListener); 

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 1) {
					//重新加载下拉刷新的数据
					loadData();
					setTime();
					listAdapter.notifyDataSetChanged();

					reserveList.stopRefresh();
				}
				if(msg.what == 2){
					//上拉加载更多数据
					loadData();
					listAdapter.notifyDataSetChanged();
					reserveList.stopLoadMore();
				}
			}
		};

		return v;
	}

	private void initViews() {
		reserveList = (XListView) v.findViewById(R.id.reserveList);
		equipmentGroup = (Spinner) v.findViewById(R.id.equipmentGroup);
		currentStateGroup = (Spinner) v.findViewById(R.id.currentStateGroup);
		timeGroup = (Spinner) v.findViewById(R.id.timeGroup);
		equipmentGroupImage = (ImageView)v.findViewById(R.id.equipmentGroupImage);
		currentStateGroupImage = (ImageView)v.findViewById(R.id.currentStateGroupImage);
		timeGroupImage = (ImageView)v.findViewById(R.id.timeGroupImage);
	}

	private void initEvents() {
		// 加载Spinner布局以及设置Adapter和监听器
		equipmentGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.equipmentgroup, R.layout.spinner_text);
		equipmentGroup.setAdapter(equipmentGroupAdapter);
		
		currentStateAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.stategroup, R.layout.spinner_text);
		currentStateGroup.setAdapter(currentStateAdapter);
		timeGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.timegroup, R.layout.spinner_text);
		timeGroup.setAdapter(timeGroupAdapter);
		// Spinner监听器
		equipmentGroup.setOnItemSelectedListener(new equipmentGroupListener());
		currentStateGroup.setOnItemSelectedListener(new currentStateGroupListener());
		timeGroup.setOnItemSelectedListener(new timeGroupListener());
		spinnerOnTouchListener = new SpinnerOnTouchListener();
		equipmentGroup.setOnTouchListener(spinnerOnTouchListener);
		currentStateGroup.setOnTouchListener(spinnerOnTouchListener);
		timeGroup.setOnTouchListener(spinnerOnTouchListener);

		//ListView
		itemArrayList = new ArrayList<HashMap<String, Object>>();
		listAdapter = new SimpleAdapter(getActivity(), itemArrayList,
				R.layout.listview_reserve, new String[] { "itemStateText" },
				new int[] { R.id.itemStateText });
		reserveList.setXListViewListener(this);
		reserveList.setAdapter(listAdapter);
		reserveList.setOnItemClickListener(new itemClickListener());

		//日期格式
		dateFormatter = new SimpleDateFormat("MM月dd日 HH:mm:ss");
		
	}
	
	//Spinner ImageView旋转动画
	private void setAnimations(){
		spinnerImageUp = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		spinnerImageUp.setDuration(500);
		spinnerImageUp.setFillAfter(true);
		
		spinnerImageDown = new RotateAnimation(180.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		spinnerImageDown.setDuration(500);
		spinnerImageDown.setFillAfter(true);
	}

	// 加载ListView数据
	private void loadData() {
		Log.i("testout", "----->loadingData");
		for (int i = 0; i < 15; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemStateText", "可预约");
			itemArrayList.add(map);
		}

	}

	// LisiView ItemCilck Listener
	class itemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(getActivity(), ReserveActivity.class);

			startActivity(intent);
		}
	}

	//下拉刷新的接口
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
	
	//上拉加载的接口
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Log.i("testout", "------------>onRefresh");
					Thread.sleep(1000 * 2);
					Message msg = new Message();
					msg.what = 2;
					handler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	//设置下拉刷新时间
	private void setTime() {
		Date date = new Date(System.currentTimeMillis());
		String time = "上次更新：" + dateFormatter.format(date);
		reserveList.setRefreshTime(time);
	}
	
	//第一组Spinner Item监听器
	private class equipmentGroupListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
//			if(isSpinnerExpand){
//				equipmentGroupImage.clearAnimation();
//				equipmentGroupImage.startAnimation(spinnerImageDown);
//				isSpinnerExpand = false;
//			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
	//第二组Spinner Item监听器
	private class currentStateGroupListener implements OnItemSelectedListener {
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
	//第三组Spinner Item监听器
	private class timeGroupListener implements OnItemSelectedListener {
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
	
	class SpinnerOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.equipmentGroup:
//				if(event.getAction() == MotionEvent.ACTION_DOWN && !isSpinnerExpand){
//					Log.i("testout", "-------------->click equipmentGroup  ACTION_DOWN");
//					isSpinnerExpand = true;
//					equipmentGroupImage.startAnimation(spinnerImageUp);
//				}
				break;
			case R.id.currentStateGroup:
				
				break;
			case R.id.timeGroup:
				
				break;
			}
			return false;
		}
		
	}
	
	//
	private MyOnTouchListener myOnTouchListener = new MainActivity.MyOnTouchListener() {

		@Override
		public boolean onTouch(MotionEvent ev) {
			// TODO Auto-generated method stub
			/*if(ev.getAction() == MotionEvent.ACTION_UP){
				Log.i("testout", "-------------->click fragmentACTION_UP");
			}*/
			return false;
		} 
	};

}
