package com.demo.myinfo.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo.R;

public class BackSuggestionActivity extends Activity {
	private EditText backEditText;
	private Button backCommitButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_backsuggestion);
		
		backEditText = (EditText)findViewById(R.id.backEditText);
		backCommitButton = (Button)findViewById(R.id.backCommitButton);
		
		backCommitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder builder = new AlertDialog.Builder(BackSuggestionActivity.this)
				.setTitle(R.string.backsuggestion)
				.setMessage(R.string.backcommitsuccess)
				.setPositiveButton("确定", null);
				
				builder.show();
			}
		});
	}
}
