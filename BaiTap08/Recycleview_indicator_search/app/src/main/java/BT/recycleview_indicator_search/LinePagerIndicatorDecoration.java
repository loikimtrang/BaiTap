package BT.recycleview_indicator_search;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    public LinePagerIndicatorDecoration() {
        paint = new Paint();
        paint.setColor(0xFFFF0000);
        paint.setStrokeWidth(8);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int height = parent.getHeight();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float left = child.getLeft();
            float right = child.getRight();

            c.drawLine(left, height - 10, right, height - 10, paint);
        }
    }
}
