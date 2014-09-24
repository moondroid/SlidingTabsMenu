package it.moondroid.slidingtabsmenu;

/**
 * Created by marco.granatiero on 24/09/2014.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import it.moondroid.slidingmenulib.R;


public class FooterTabLayout extends RelativeLayout {
    Listener listener = new Listener() { //empty listener
        @Override
        public void onHelpClick() {

        }

        @Override
        public void onOpenClick() {

        }

        @Override
        public void onSettingsClick() {

        }
    };

    public static interface Listener {
        void onHelpClick();
        void onOpenClick();
        void onSettingsClick();
    }

    public FooterTabLayout(Context context) {
        super(context);
        setup();
    }

    public FooterTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public FooterTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        inflate(getContext(), R.layout.tab_footer, this);
        setBackgroundColor(ThemeUtils.getColor(getContext(), R.attr.color_tab_background));
        findViewById(R.id.settingsIcon).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FooterTabLayout.this.listener.onSettingsClick();
            }
        });
        findViewById(R.id.openIcon).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FooterTabLayout.this.listener.onOpenClick();
            }
        });
        findViewById(R.id.feedback_icon).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FooterTabLayout.this.listener.onHelpClick();
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
