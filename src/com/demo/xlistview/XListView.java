package com.demo.xlistview;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Scroller;

public class XListView extends ListView implements OnScrollListener {
	
	private HeaderLayout headerLayout;
	
	//header height
	private static final int HEADERLAYOUT_HEIGHT = 100;
	
	//刷新状态
	private boolean isRefereshing = false;
	
	//记录滑动过程中每一次触发ontouch函数的Y坐标
	private int DownY;
		
	//监听滑动
	private Scroller mScroller;
	private OnScrollListener mOnScrollListener;
	
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;

	private IXListViewListener mListViewListener;
	
	public XListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	public XListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	public XListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initViews(context);
	}

	private void initViews(Context context){
		super.setOnScrollListener(this);
		mScroller = new Scroller(context, new DecelerateInterpolator());
		headerLayout = new HeaderLayout(context);
		addHeaderView(headerLayout);
	}
	
	//设置时间
	public void setRefreshTime(String time){
		headerLayout.refreshTimeText.setText(time);
	}
	
	//setSelection
	@Override
	public void setAdapter(ListAdapter adapter) {
		// TODO Auto-generated method stub
		super.setAdapter(adapter);
		setSelection(1);
	}
	
	@Override
	public void setOnScrollListener(OnScrollListener l) {
		// TODO Auto-generated method stub
		mOnScrollListener = l;
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		Log.i("testout", "----->onScrollStateChanged");
		if (mOnScrollListener != null) {
			mOnScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		Log.i("testout", "----->onScroll");
		if (mOnScrollListener != null) {
			mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN:
			//记录初始按下的DownY
			DownY = (int)ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			//滑动过程中，系统会在滑动的过程中上取一些点，用DownY记录
			int diatance = (int)ev.getRawY() - DownY;
			DownY = (int)ev.getRawY();
			if (getFirstVisiblePosition() == 0 && (headerLayout.getHeight() > 0 || diatance > 0)) {
				headerLayout.setHeight(diatance / 2 + headerLayout.getHeight());
				if (!isRefereshing) {
					if (headerLayout.getHeight() >= 100) {
						headerLayout.setState(HeaderLayout.STATE_RELASE_TOREFRESH);
					}
					else {
						headerLayout.setState(HeaderLayout.STATE_NORMAL);
					}
				}
				setSelection(0); // scroll to top each time
				invokeOnScrolling();
			}
			break;
		case MotionEvent.ACTION_UP:
			if(getFirstVisiblePosition() == 0){
				if(headerLayout.getHeight() > HEADERLAYOUT_HEIGHT){
					//进入刷新状态
					headerLayout.setState(HeaderLayout.STATE_ISREFRESHING);
					isRefereshing = true;
					if (mListViewListener != null) {
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}
	

	private void resetHeaderHeight() {
		// TODO Auto-generated method stub
		if(headerLayout.getHeight() == 0){
			return ;
		}
		if(isRefereshing && headerLayout.getHeight() <= HEADERLAYOUT_HEIGHT){
			return ;
		}
		int finalHeight = 0;
		if(isRefereshing && headerLayout.getHeight() > HEADERLAYOUT_HEIGHT){
			finalHeight = HEADERLAYOUT_HEIGHT;
		}
		mScrollBack = SCROLLBACK_HEADER;
		mScroller.startScroll(0, headerLayout.getHeight(), 0, finalHeight - headerLayout.getHeight(), 500);
		invalidate();
	}
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			if (mScrollBack == SCROLLBACK_HEADER) {
				headerLayout.setHeight(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}
	
	public void stopRefresh(){
		if(isRefereshing){
			isRefereshing = false;
		}
		resetHeaderHeight();
		
	}
	
	
	public interface OnXScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}
	private void invokeOnScrolling() {
		if (mOnScrollListener instanceof OnXScrollListener) {
			OnXScrollListener l = (OnXScrollListener) mOnScrollListener;
			l.onXScrolling(this);
		}
	}
	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}
	public interface IXListViewListener {
		public void onRefresh();

	}
}
