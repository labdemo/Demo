package com.demo.myinfo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.changeinfo.ChangeInFoActivity;
import com.demo.login.LoginActivity;
import com.example.demo.R;

public class PersonInfoActivity extends Activity {
	public static final  String ACTION_MYACTION = "myaction";
	public static final int REQUESTCODE = 1;
	private TextView personName,personDetialText;
	private ImageView personHeadPhoto;
	private Button changePersonInfoBtn,logoutButton;
	private ViewsOnclickListener viewsOnclickListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personinfo);
		initViews();
		initEvents();
	}
	
	private void initViews(){
		personName = (TextView)findViewById(R.id.personName);
		personDetialText = (TextView)findViewById(R.id.personDetialText);
		personHeadPhoto = (ImageView)findViewById(R.id.personHeadPhoto);
		changePersonInfoBtn = (Button)findViewById(R.id.changePersonInfoBtn);
		logoutButton = (Button)findViewById(R.id.logoutButton);
	}
	private void initEvents(){
		viewsOnclickListener = new ViewsOnclickListener();
		personHeadPhoto.setOnClickListener(viewsOnclickListener);
		changePersonInfoBtn.setOnClickListener(viewsOnclickListener);
		logoutButton.setOnClickListener(viewsOnclickListener);
	}
	
	class ViewsOnclickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.personHeadPhoto:
				//从图库或者照相机选择图片上传头像
				break;
			case R.id.changePersonInfoBtn:
				//改变个人信息的activity:
				Intent intent = new Intent();
				intent.setClass(PersonInfoActivity.this, ChangeInFoActivity.class);
				startActivityForResult(intent, REQUESTCODE);
				break;
			case R.id.logoutButton:
				//登出，跳转至login界面zd
				startActivity(new Intent(PersonInfoActivity.this,LoginActivity.class));
				
				Intent intentout = new Intent();
				intentout.setAction(ACTION_MYACTION);
				PersonInfoActivity.this.sendBroadcast(intentout);
				PersonInfoActivity.this.finish();
				break;
			}
		}
	}
}
