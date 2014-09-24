package it.moondroid.slidingtabsmenu.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import it.moondroid.slidingtabsmenu.ChatTabEntry;
import it.moondroid.slidingtabsmenu.TabsFragment;


public class MainActivity extends FragmentActivity implements ChatTabEntry.Listener {

    private TabsFragment tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        this.tabs = (TabsFragment) fm.findFragmentById(R.id.tabs);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void onChatClick(long threadId, boolean showKeyboard) {
        Log.d("MainActivity", "onChatClick "+threadId);
        openChat(threadId, showKeyboard, null);
    }

    @Override
    public void onChatDelete(long threadId) {
        Log.d("MainActivity", "onChatDelete "+threadId);
        //TODO
    }


    private void openChat(long threadId, boolean showKeyboard, String defaultText) {
//        if (threadId == 0) {
//            throw new IllegalArgumentException("no thread id in openChat");
//        }
        tabs.refresh(threadId);
//        this.content.removeAllViews();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content, ChatFragment.newInstance(threadId, showKeyboard & Welcome.isModalDimissed(this), defaultText)).commit();
    }

}
