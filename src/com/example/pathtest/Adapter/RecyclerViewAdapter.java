package com.example.pathtest.Adapter;

import java.util.ArrayList;

import com.example.pathtest.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import util.GlobalConstant;

public class RecyclerViewAdapter extends Adapter<RecyclerView.ViewHolder> {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ArrayList<String> mItemList;

	public RecyclerViewAdapter() {
		// TODO Auto-generated constructor stub
		mContext = GlobalConstant.getApplicationContext();
		mLayoutInflater = LayoutInflater.from(mContext);
	}
	
	public void setRecyleViewList(ArrayList<String> itemList) {
		mItemList = itemList;
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return mItemList.size();
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		TestRecyclerView recyclerView = (TestRecyclerView)viewHolder;
		recyclerView.mItemText.setText(mItemList.get(position));
		recyclerView.mLLayout.scrollTo(0, 0);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		// TODO Auto-generated method stub
		View view = mLayoutInflater.inflate(R.layout.recycler_view_item_layout, viewGroup, false);
		TestRecyclerView viewHolder = new TestRecyclerView(view);
		viewHolder.mItemText = (TextView) view.findViewById(R.id.recycler_view_item_text);
		viewHolder.mRlText = (TextView) view.findViewById(R.id.recycler_view_item_rl_text);
		viewHolder.mRlImage = (ImageView) view.findViewById(R.id.recycler_view_item_rl_image);
		viewHolder.mLLayout = (LinearLayout) view.findViewById(R.id.recycler_view_llayout);
		return viewHolder;
	}

	public void removeRecycle(int position) {
		mItemList.remove(position);
		notifyDataSetChanged();
		if (mItemList.size() == 0) {
			Toast.makeText(mContext, "已经没有数据了", Toast.LENGTH_LONG).show();
		}
	}
	
	public static class TestRecyclerView extends RecyclerView.ViewHolder{

		public TestRecyclerView(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
		}
		
		public LinearLayout mLLayout;
		public TextView mItemText;
		public TextView mRlText;
		public ImageView mRlImage;
	}
}
