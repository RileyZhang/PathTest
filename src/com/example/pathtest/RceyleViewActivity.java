package com.example.pathtest;

import java.util.ArrayList;

import com.example.pathtest.Adapter.RecyclerViewAdapter;
import com.example.pathtest.custom.MyRecyclerView;
import com.example.pathtest.custom.MyRecyclerView.onGetListener;
import com.example.pathtest.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import util.SpacesItemDecoration;

public class RceyleViewActivity extends Activity {

	private MyRecyclerView mRecyclerView;
	private RecyclerViewAdapter mRecylerViewAdapter;
	private ArrayList<String> mArrayList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recyle_view_layout);
		mRecyclerView = (MyRecyclerView) findViewById(R.id.recyle_view);
		mRecylerViewAdapter = new RecyclerViewAdapter();
		for (int i = 0 ; i < 15; i++) {
			String testString = "第" + i + "项";
			mArrayList.add(testString);
		}
		mRecylerViewAdapter.setRecyleViewList(mArrayList);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);
		int spacingInPixels = 8;
		mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
		mRecyclerView.setAdapter(mRecylerViewAdapter);
		mRecyclerView.setListener(new onGetListener() {
			
			@Override
			public void getPosition(int position) {
				// TODO Auto-generated method stub
				Toast.makeText(RceyleViewActivity.this, "这是第" + position + "项", Toast.LENGTH_LONG).show();
			}
		});
	}
}
