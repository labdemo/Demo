package com.demo.login;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.example.demo.R;

public class LoadingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView view = new ImageView(LoadingActivity.this);
		view.setBackgroundResource(R.drawable.pic_welcome);
		setContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoadingActivity.this,LoginActivity.class));
				finish();
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		}, 1000*2);
	}
}

/*		//判断是否第一次使用
if(){
	startActivity(new Intent(LoadingActivity.this,LoginActivity.class));
}
else if(){
	startActivity(new Intent(LoadingActivity.this,MainActivity.class));
}*/
