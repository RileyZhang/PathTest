package util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{

	private int space;
	public SpacesItemDecoration(int spacingInPixels) {
		// TODO Auto-generated constructor stub
		space = spacingInPixels;
	}
	
	@Override
	public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
		// TODO Auto-generated method stub
		outRect.left = space;
		outRect.right = space;
		outRect.bottom = space;
		outRect.top = space;
	}

}
