package com.example.quickreturn;

/**
 * Created by rasmus on 2015-02-01.
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements ObservableScrollView.ObservableScrollViewListener {

    private Toolbar toolbar;
    private ObservableScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        scrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
        scrollView.setListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onScroll(int x, int y, int oldX, int oldY) {
        int dy = y - oldY;
        int toolbarHeight = toolbar.getHeight();
        float toolbarPosition = toolbar.getY();
        // Leave some room for Android's bounce behavior
        if(y > 0.10 * toolbarHeight) {
            if (dy > 0 && toolbarPosition > -toolbarHeight) {
                // We are scrolling down and the toolbar is still showing
                float u = toolbarPosition + toolbarHeight;
                if (dy < u) {
                    toolbar.animate().translationYBy(-dy).setDuration(0).start();
                } else {
                    toolbar.animate().translationYBy(-u).setDuration(0).start();
                }
            } else if (dy < 0 && toolbarPosition < 0) {
                // We are scrolling up. Let the toolbar scroll back into view
                float d = -toolbarPosition;
                if (-dy < d) {
                    toolbar.animate().translationYBy(-dy).setDuration(0).start();
                } else {
                    toolbar.animate().translationYBy(d).setDuration(0).start();
                }
            }
        }
    }
}
