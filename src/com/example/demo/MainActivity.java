package com.example.demo;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.fragment.MyInfoFragment;
import com.demo.fragment.RecordFragment;
import com.demo.fragment.ReserveFragment;

public class MainActivity extends FragmentActivity {
	private TextView reserveText, recordText, myInfoText, mainTitleText;
	private RelativeLayout reserveMenuLayout, recordMenuLayout, myInfoMenuLayout;
	private LinearLayout netWarningLayout;
	private ImageView reserveImage, recordImage, myInfoImage;
	private ViewPager viewPager;
	private TextView[] bottomTextList;
	private ImageView[] BottomImagelist;
	private Drawable[] menuPicNormalList;
	private Drawable[] menuPicBlueList;
	private int nowSelectedPager = 0;
	private int nextSelectedPager;
	private long exitTimeCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initViews();
		initEvents();
		IntentFilter netStateFilter = new IntentFilter();
		netStateFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		MainActivity.this.registerReceiver(netStateReceiver, netStateFilter);
	}

	// 获取控件
	private void initViews() {
		mainTitleText = (TextView) findViewById(R.id.mainTitleText);
		reserveText = (TextView) findViewById(R.id.reserveText);
		recordText = (TextView) findViewById(R.id.recordText);
		myInfoText = (TextView) findViewById(R.id.myInfoText);
		reserveImage = (ImageView) findViewById(R.id.reserveImage);
		recordImage = (ImageView) findViewById(R.id.recordImage);
		myInfoImage = (ImageView) findViewById(R.id.myInfoImage);
		netWarningLayout = (LinearLayout)findViewById(R.id.netWarningLayout);
		reserveMenuLayout = (RelativeLayout) findViewById(R.id.reserveMenuLayout);
		recordMenuLayout = (RelativeLayout) findViewById(R.id.recordMenuLayout);
		myInfoMenuLayout = (RelativeLayout) findViewById(R.id.myInfoMenuLayout);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
	}

	// 控件绑定监听器等操作
	private void initEvents() {
		bottomTextList = new TextView[] { reserveText, recordText, myInfoText };
		BottomImagelist = new ImageView[] { reserveImage, recordImage, myInfoImage };
		menuPicNormalList = new Drawable[] {
				getResources().getDrawable(R.drawable.pic_bespeak_normal),
				getResources().getDrawable(R.drawable.pic_record_normal),
				getResources().getDrawable(R.drawable.pic_myinfo_normal) };
		menuPicBlueList = new Drawable[] {
				getResources().getDrawable(R.drawable.pic_bespeak_blue),
				getResources().getDrawable(R.drawable.pic_record_blue),
				getResources().getDrawable(R.drawable.pic_myinfo_blue) };
		reserveMenuLayout.setOnClickListener(bottomMenuLayoutListener);
		recordMenuLayout.setOnClickListener(bottomMenuLayoutListener);
		myInfoMenuLayout.setOnClickListener(bottomMenuLayoutListener);
		viewPager.setAdapter(fpaAdapter);
		viewPager.setOnPageChangeListener(sopclistener);
	}

	private OnClickListener bottomMenuLayoutListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.reserveMenuLayout:
				nextSelectedPager = 0;
				if (nowSelectedPager == 0) {
					return;
				} else {
					setcurrenttitle(nowSelectedPager, nextSelectedPager);
					viewPager.setCurrentItem(0);
				}
				break;
			case R.id.recordMenuLayout:
				nextSelectedPager = 1;
				if (nowSelectedPager == 1) {
					return;
				} else {
					setcurrenttitle(nowSelectedPager, nextSelectedPager);
					viewPager.setCurrentItem(1);
				}
				break;
			case R.id.myInfoMenuLayout:
				nextSelectedPager = 2;
				if (nowSelectedPager == 2) {
					return;
				} else {
					setcurrenttitle(nowSelectedPager, nextSelectedPager);
					viewPager.setCurrentItem(2);
				}
				break;
			}
		}
	};

	private void setcurrenttitle(int localNowSelectedPager, int localNextselectedPager) {

		BottomImagelist[localNowSelectedPager]
				.setImageDrawable(menuPicNormalList[localNowSelectedPager]);
		BottomImagelist[localNextselectedPager]
				.setImageDrawable(menuPicBlueList[localNextselectedPager]);
		bottomTextList[localNowSelectedPager].setTextColor(getResources()
				.getColor(R.color.textnomalcolor));
		bottomTextList[localNextselectedPager].setTextColor(getResources()
				.getColor(R.color.textselected));
		switch (localNextselectedPager) {
		case 0:
			mainTitleText.setText(R.string.reserve);
			break;
		case 1:
			mainTitleText.setText(R.string.record);
			break;
		case 2:
			mainTitleText.setText(R.string.myinfo);
			break;
		}
		nowSelectedPager = localNextselectedPager;
	}

	// ViewPager 页面改变的监听器
	private SimpleOnPageChangeListener sopclistener = new SimpleOnPageChangeListener() {
		public void onPageSelected(int position) {
			// 设置当前的页面缓存的左右的页面的数量（分别为2）
			viewPager.setOffscreenPageLimit(2);
			setcurrenttitle(nowSelectedPager, position);
		};
	};
	private FragmentPagerAdapter fpaAdapter = new FragmentPagerAdapter(
			getSupportFragmentManager()) {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// 设置预加载页面的个数
			return 3;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new ReserveFragment();
				break;
			case 1:
				fragment = new RecordFragment();
				break;
			case 2:
				fragment = new MyInfoFragment();
				break;
			}
			return fragment;
		}
	};
	// 接收网络状态Broadcast
	private BroadcastReceiver netStateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				ConnectivityManager netConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = netConnectivityManager.getActiveNetworkInfo();
				if (info != null) {
					netWarningLayout.setVisibility(View.GONE);
					// Toast.makeText(MainActivity.this, "当前网络类型:" + info.getTypeName(),Toast.LENGTH_SHORT).show();
				} else {
					netWarningLayout.setVisibility(View.VISIBLE);
					Toast.makeText(MainActivity.this, "当前网络已断开", Toast.LENGTH_SHORT).show();

				}
			}
		}
	};

	// 复写返回键实现按两次返回键退出程序
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - exitTimeCount >= 2000) {
				Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exitTimeCount = System.currentTimeMillis();
				return true;
			} else {
				MainActivity.this.finish();
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
			10);

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		for (MyOnTouchListener listener : onTouchListeners) {
			listener.onTouch(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
		onTouchListeners.add(myOnTouchListener);
	}

	public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
		onTouchListeners.remove(myOnTouchListener);
	}

	public interface MyOnTouchListener {
		public boolean onTouch(MotionEvent ev);
	}

}