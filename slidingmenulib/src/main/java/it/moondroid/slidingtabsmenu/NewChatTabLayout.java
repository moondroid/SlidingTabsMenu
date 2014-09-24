package it.moondroid.slidingtabsmenu;

/**
 * Created by marco.granatiero on 24/09/2014.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import it.moondroid.slidingmenulib.R;


public class NewChatTabLayout extends RelativeLayout implements TabsFragment.SelectableTab {

    private Listener listener = new Listener() { //empty listener
        @Override
        public void onNewChatClick() {

        }
    };

    public static interface Listener {
        void onNewChatClick();
    }

    public NewChatTabLayout(Context context) {
        super(context);
        setup();
    }

    public NewChatTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public NewChatTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        inflate(getContext(), R.layout.tab_new_chat, this);
        setBackgroundResource(ThemeUtils.getResourceId(getContext(), R.attr.drawable_bg_tab_solid_top));
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                NewChatTabLayout.this.selectTab();
            }
        });
    }

    public void refresh() {
        View activeBackground = findViewById(R.id.active_background);
        View imageView = findViewById(R.id.imageView);
//        if (-1 == MainActivity.getActiveThreadId()) {
//            activeBackground.setVisibility(VISIBLE);
//            imageView.setBackgroundColor(0);
//            return;
//        }
        activeBackground.setVisibility(INVISIBLE);
        imageView.setBackgroundColor(ThemeUtils.getColor(getContext(), R.attr.color_tab_background));
    }

    public void selectTab() {
            this.listener.onNewChatClick();
    }

    protected void setListener(Listener listener) {
        this.listener = listener;
    }
}
