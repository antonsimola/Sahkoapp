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

    // Listener
    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    // GestureDetector for MotionEvents
    GestureDetector gDetector;

    // Constructor
    public RecyclerItemClickListener(Context context, OnItemClickListener listener)
    {
        cListener = listener;
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    // Intercept all touch screen motion events
    // http://developer.android.com/reference/android/view/ViewGroup.html
    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e)
    {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && cListener != null && gDetector.onTouchEvent(e))
        {
            // Invoked when item has been clicked in the CustomAdapter
            cListener.onItemClick(childView, view.getChildPosition(childView));
            return true;
        }
        return false;
    }

    // Handle touch screen motion events
    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }
}