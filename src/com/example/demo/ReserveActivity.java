package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

public class ReserveActivity extends Activity {
	private TextView reversrTitleText;
	private TextView deviceNumber,deviceClassify,deviceInfoText;
	private RadioButton tmoMorning,tmoAfternoon,tmoEvening,afterTmoMorning,afterTmoAfternoon,afterTmoEvening;
	private ReserveTimeRadioButton radioButtonListener;
	private RadioButton[] timeGroupButton;
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
    	
    	tmoMorning = (RadioButton)findViewById(R.id.tmoMorning);
    	tmoAfternoon = (RadioButton)findViewById(R.id.tmoAfternoon);
    	tmoEvening = (RadioButton)findViewById(R.id.tmoEvening);
    	afterTmoMorning = (RadioButton)findViewById(R.id.afterTmoMorning);
    	afterTmoAfternoon = (RadioButton)findViewById(R.id.afterTmoAfternoon);
    	afterTmoEvening = (RadioButton)findViewById(R.id.afterTmoEvening);
	}
	
	private void initEvents(){
		timeGroupButton = new RadioButton[] {tmoMorning,tmoAfternoon,tmoEvening,afterTmoMorning,afterTmoAfternoon,afterTmoEvening};
    	radioButtonListener = new ReserveTimeRadioButton();
    	tmoMorning.setOnClickListener(radioButtonListener);
    	tmoAfternoon.setOnClickListener(radioButtonListener);
    	tmoEvening.setOnClickListener(radioButtonListener);
    	afterTmoMorning.setOnClickListener(radioButtonListener);
    	afterTmoAfternoon.setOnClickListener(radioButtonListener);
    	afterTmoEvening.setOnClickListener(radioButtonListener);
    	
    	setRadioButtonChecked(timeGroupButton[1]);
    }
	
	class ReserveTimeRadioButton implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.tmoMorning:
				setCurrentSelectedRadioButton(0);
				break;
			case R.id.tmoAfternoon:
				setCurrentSelectedRadioButton(1);
				break;
			case R.id.tmoEvening:
				setCurrentSelectedRadioButton(2);
				break;
			case R.id.afterTmoMorning:
				setCurrentSelectedRadioButton(3);
				break;
			case R.id.afterTmoAfternoon:
				setCurrentSelectedRadioButton(4);
				break;
			case R.id.afterTmoEvening:
				setCurrentSelectedRadioButton(5);
				break;

			}
		}
	}
	
	//设置RadioButton为不可选中
	private void setRadioButtonChecked(RadioButton radioButton){
		radioButton.setTextColor(getResources().getColor(R.color.radiobuttonselected));
		radioButton.setClickable(false);
	}
	//设定RadioButton为可选中
	private void setRadioButtonUncheced(RadioButton radioButton){
		radioButton.setTextColor(getResources().getColor(R.color.blackcolor));
		radioButton.setClickable(true);
	}
	
	//实现RadioGroup的效果
	private void setCurrentSelectedRadioButton(int position){
		int i = 0;
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
