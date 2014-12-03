package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

public class ReserveActivity extends Activity {
	private TextView reversrTitleText;
	private TextView deviceNumber,deviceClassify,deviceInfoText;
	private RadioButton todayMorning,todayAfternoon,todayEvening,tmoMorning,tmoAfternoon,tmoEvening;
	private ReserveTimeRadioButton radioButtonListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reserve);
		initViews();
        initEvents();
	}
	private void initViews(){
		reversrTitleText = (TextView)findViewById(R.id.reversrTitleText);
		deviceNumber = (TextView)findViewById(R.id.deviceNumber);
    	deviceClassify = (TextView)findViewById(R.id.deviceClassify);
    	deviceInfoText = (TextView)findViewById(R.id.deviceInfoText);
    	
    	todayMorning = (RadioButton)findViewById(R.id.todayMorning);
    	todayAfternoon = (RadioButton)findViewById(R.id.todayAfternoon);
    	todayEvening = (RadioButton)findViewById(R.id.todayEvening);
    	tmoMorning = (RadioButton)findViewById(R.id.tmoMorning);
    	tmoAfternoon = (RadioButton)findViewById(R.id.tmoAfternoon);
    	tmoEvening = (RadioButton)findViewById(R.id.tmoEvening);
	}
	
	private void initEvents(){
    	radioButtonListener = new ReserveTimeRadioButton();
    	todayMorning.setOnClickListener(radioButtonListener);
    	todayAfternoon.setOnClickListener(radioButtonListener);
    	todayEvening.setOnClickListener(radioButtonListener);
    	tmoMorning.setOnClickListener(radioButtonListener);
    	tmoAfternoon.setOnClickListener(radioButtonListener);
    	tmoEvening.setOnClickListener(radioButtonListener);
    }
	
	class ReserveTimeRadioButton implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.todayMorning:
				setCurrentSelectedRadioButton(0);
				break;
			case R.id.todayAfternoon:
				setCurrentSelectedRadioButton(1);
				break;
			case R.id.todayEvening:
				setCurrentSelectedRadioButton(2);
				break;
			case R.id.tmoMorning:
				setCurrentSelectedRadioButton(3);
				break;
			case R.id.tmoAfternoon:
				setCurrentSelectedRadioButton(4);
				break;
			case R.id.tmoEvening:
				setCurrentSelectedRadioButton(5);
				break;

			}
		}
	}
	
	private void setCurrentSelectedRadioButton(int position){
		int i = 0;
		RadioButton timeGroupButton[] = {todayMorning,todayAfternoon,todayEvening,tmoMorning,tmoAfternoon,tmoEvening};
		while(i<6){
			if(i==position){
				timeGroupButton[i].setChecked(true);
			}
			else{
				timeGroupButton[i].setChecked(false);
			}
			i++;
		}
	}

}
