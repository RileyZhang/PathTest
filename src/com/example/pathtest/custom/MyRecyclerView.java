package com.example.pathtest.custom;

import com.example.pathtest.R;
import com.example.pathtest.Adapter.RecyclerViewAdapter;
import com.example.pathtest.Adapter.RecyclerViewAdapter.TestRecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class MyRecyclerView extends RecyclerView {

	private int xDown, yDown, xMove, yMove, xUp, yUp;
	private int mTouchSlop;
	private int mMaxLength;
	private int mPos;
	private int mStartX = 0;
	private boolean isFirst = true;
	private Rect mTouchFrame;
	private LinearLayout mLinearLayout;
	private Scroller mScroller;
	private TextView mRlText;
	private ImageView mRlImage;
	private onGetListener mGetListener;
	
	public interface onGetListener {
		void getPosition(int position);
	}

	public void setListener(onGetListener listener) {
	    mGetListener = listener;
	}
	
	public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		Log.i("mtq", "MyRecyclerView()333333333");
	}

	public MyRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mMaxLength = ((int)(180 * context.getResources().getDisplayMetrics().density + 0.5f));
		mScroller = new Scroller(context, new LinearInterpolator(context,null));
		Log.i("mtq", "MyRecyclerView() mMaxLength = " + mMaxLength + " mTouchSlop = " + mTouchSlop);
	}

	public MyRecyclerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.i("mtq", "MyRecyclerView()111111");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDown = x;
			yDown = y;
			int mFirstPosition = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
			Rect frame = mTouchFrame;
			if (frame == null) {
				mTouchFrame = new Rect();
				frame = mTouchFrame;
			}
			
			int count = getChildCount();
			for(int i = 0; i < count; i++) {
				final View child = getChildAt(i);
				if (child.getVisibility() == View.VISIBLE) {
					child.getHitRect(frame);
					if (frame.contains(x,y)) {
						mPos = mFirstPosition + i;
					}
				}
			}
			View view = getChildAt(mPos - mFirstPosition);
			TestRecyclerView viewHolder = (TestRecyclerView)getChildViewHolder(view);
			mLinearLayout = viewHolder.mLLayout;
			mRlText = (TextView) mLinearLayout.findViewById(R.id.recycler_view_item_rl_text);
			mRlImage = (ImageView) mLinearLayout.findViewById(R.id.recycler_view_item_rl_image);
			break;
		case MotionEvent.ACTION_MOVE:
			xMove = x;
			yMove = y;
			int distanceX = xMove - xDown;
			int distanceY = yMove - yDown;
			
			Log.i("mtq", "dY = " + Math.abs(distanceY) + " dX = " + Math.abs(distanceX));
			if (Math.abs(distanceY) < mTouchSlop * 2 && Math.abs(distanceX) > mTouchSlop) {
				int scrollX = mLinearLayout.getScrollX();
				int newScrollX = mStartX - x;
				if (newScrollX < 0 && scrollX <= 0) {
					newScrollX = 0;
				} else if (newScrollX > 0 && scrollX > mMaxLength){
					newScrollX = 0;
				}
				Log.i("mtq", "newScrollX = " + newScrollX + " scrollX = " + scrollX);
				if (scrollX > mMaxLength / 2) {
					mRlText.setVisibility(View.GONE);
					mRlImage.setVisibility(View.VISIBLE);
					
					if (isFirst) {
						ObjectAnimator animatorX = ObjectAnimator.ofFloat(mRlImage, "scrollX", 1f, 1.2f, 1f);
						ObjectAnimator animatorY = ObjectAnimator.ofFloat(mRlImage, "scrollY", 1f, 1.2f, 1f);
						AnimatorSet animatorSet = new AnimatorSet();
						animatorSet.play(animatorX).with(animatorY);
						animatorSet.setDuration(800);
						animatorSet.start();
						isFirst = false;
					}
				} else {
					mRlText.setVisibility(View.VISIBLE);
					mRlImage.setVisibility(View.GONE);
				}
				mLinearLayout.scrollBy(newScrollX, 0);
			}
			break;
		case MotionEvent.ACTION_UP:
			xUp = x;
			yUp = y;
			int dx = xUp - xDown;
			int dy = yUp - yDown;
			Log.i("mtq", "ACTION_UP dx = " + dx + " dy = " + dy);
			if (Math.abs(dx) < mTouchSlop && Math.abs(dy) < mTouchSlop) {
				mGetListener.getPosition(mPos);
			} else {
				int scrollX = mLinearLayout.getScrollX();
				Log.i("mtq", "ACTION_UP scrollX = " + scrollX + " mMaxLength = " + mMaxLength);
				if (scrollX > mMaxLength/2) {
					((RecyclerViewAdapter) getAdapter()).removeRecycle(mPos);
				} else {
					Log.i("mtq", "startScroll###########");
					mScroller.startScroll(scrollX, 0, -scrollX, 0);
					invalidate();
				}
				isFirst = true;
			}
			break;
		default:
			break;
		}
		mStartX = x;
		return super.onTouchEvent(event);
	}
	
	public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
        	mLinearLayout.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
