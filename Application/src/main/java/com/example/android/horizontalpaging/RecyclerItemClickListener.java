package com.example.android.horizontalpaging;

/**
 * Created by jayy998 on 23.4.2015.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener cListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    GestureDetector gDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        cListener = listener;
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && cListener != null && gDetector.onTouchEvent(e)) {
            cListener.onItemClick(childView, view.getChildPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }
}