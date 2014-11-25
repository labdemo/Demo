package com.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.demo.myinfo.activity.AboutActivity;
import com.demo.myinfo.activity.BackSuggestionActivity;
import com.demo.myinfo.activity.CheckForNewActivity;
import com.demo.myinfo.activity.LabMsgCenterActivity;
import com.demo.myinfo.activity.LabNotificationActivity;
import com.demo.myinfo.activity.PersonInfoActivity;
import com.demo.myinfo.activity.TestResourceActivity;
import com.example.demo.R;

public class MyInfoFragment extends Fragment {
	private View v;
	private RelativeLayout myInfoLayout, LabNotificationLayout, LabMsgCenterLayout,
			testResourceLayout, backSuggestionLayout, checkForNewLayout, aboutLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.myinfo_fragment, container, false);
		initViews();
		initEvents();
		return v;
	}
	
	private void initViews(){
		myInfoLayout = (RelativeLayout)v.findViewById(R.id.myInfoLayout);
		LabNotificationLayout = (RelativeLayout)v.findViewById(R.id.LabNotificationLayout);
		LabMsgCenterLayout = (RelativeLayout)v.findViewById(R.id.LabMsgCenterLayout);
		testResourceLayout = (RelativeLayout)v.findViewById(R.id.testResourceLayout);
		backSuggestionLayout = (RelativeLayout)v.findViewById(R.id.backSuggestionLayout);
		checkForNewLayout = (RelativeLayout)v.findViewById(R.id.checkForNewLayout);
		aboutLayout = (RelativeLayout)v.findViewById(R.id.aboutLayout);
	}
	private void initEvents(){
		RelativelayoutListener layoutlistener = new RelativelayoutListener();
		myInfoLayout.setOnClickListener(layoutlistener);
		LabNotificationLayout.setOnClickListener(layoutlistener);
		LabMsgCenterLayout.setOnClickListener(layoutlistener);
		testResourceLayout.setOnClickListener(layoutlistener);
		backSuggestionLayout.setOnClickListener(layoutlistener);
		checkForNewLayout.setOnClickListener(layoutlistener);
		aboutLayout.setOnClickListener(layoutlistener);
	}
	
	public class RelativelayoutListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			switch(v.getId()){
			case R.id.myInfoLayout:
				intent.setClass(getActivity(), PersonInfoActivity.class);
				startActivity(intent);
				break;
			case R.id.LabNotificationLayout:
				intent.setClass(getActivity(), LabNotificationActivity.class);
				startActivity(intent);
				break;
			case R.id.LabMsgCenterLayout:
				intent.setClass(getActivity(), LabMsgCenterActivity.class);
				startActivity(intent);
				break;
			case R.id.testResourceLayout:
				intent.setClass(getActivity(), TestResourceActivity.class);
				startActivity(intent);
				break;
			case R.id.backSuggestionLayout:
				intent.setClass(getActivity(), BackSuggestionActivity.class);
				startActivity(intent);
				break;
			case R.id.checkForNewLayout:
				intent.setClass(getActivity(), CheckForNewActivity.class);
				startActivity(intent);
				break;
			case R.id.aboutLayout:
				intent.setClass(getActivity(), AboutActivity.class);
				startActivity(intent);
				break;
			}
		}
	}
}
