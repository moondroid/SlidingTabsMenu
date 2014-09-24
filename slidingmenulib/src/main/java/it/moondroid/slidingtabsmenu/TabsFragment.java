package it.moondroid.slidingtabsmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.slidingmenulib.R;

/**
 * Created by marco.granatiero on 24/09/2014.
 */
public class TabsFragment extends Fragment {

    private List<ChatTabEntry> tabs;
    private BaseAdapter adapter;
    private ListView listView;

    private boolean tabsAreScrolling;

    protected static interface SelectableTab {
        void selectTab();
    }


    public TabsFragment() {
        this.tabs = new ArrayList<ChatTabEntry>();
        this.tabsAreScrolling = false;
//        this.mostRecentMessageTime = 0;
//        this.buildTabsTask = null;
//        this.conversationsObserver = new AnonymousClass_1(new Handler(Looper.getMainLooper()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        this.adapter = new BaseAdapter() {
            public int getCount() {
                return TabsFragment.this.tabs.size();
            }

            public Object getItem(int position) {
                return TabsFragment.this.tabs.get(position);
            }

            public long getItemId(int position) {
                return 0;
            }

            public View getView(int position, View view, ViewGroup parent) {
                return (TabsFragment.this.tabs.get(position)).getView(view);
            }
        };

        buildTabs();
        //
        // this.listener = (Listener) getActivity();
        this.listView = (ListView) view.findViewById(R.id.listView);
        this.listView.setAdapter(this.adapter);


        return view;
    }


    private void buildTabs() {
        for(int i=0; i<10; i++){
            Message message = new Message(i, "Message "+i, false);
            tabs.add(new ChatTabEntry(TabsFragment.this.getActivity(), message));
        }
    }

    public void refresh(long activeThreadId) {
//        this.stickyChatTabLayout.refresh();
//        this.newChatTabLayout.refresh();
        this.adapter.notifyDataSetChanged();
    }


}
