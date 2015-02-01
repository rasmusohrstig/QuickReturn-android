package com.example.quickreturn;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by rasmus on 2015-02-01.
 */
public class ObservableScrollView extends ScrollView {

    public interface ObservableScrollViewListener {
        void onScroll(int x, int y, int oldX, int oldY);
    }

    private ObservableScrollViewListener listener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListener(ObservableScrollViewListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if(listener != null) {
            listener.onScroll(l, t, oldl, oldt);
        }
    }

}
