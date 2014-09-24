package it.moondroid.slidingtabsmenu;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import it.moondroid.slidingmenulib.R;

/**
 * Created by marco.granatiero on 23/09/2014.
 */
public class SlidingTabsPanel extends SlidingPaneLayout implements SlidingPaneLayout.PanelSlideListener
{
    private Integer menuWidthClosed;
    private Integer menuWidthExpand;

    private Boolean isOpened = false;
    private float actiondown = 0;

    public SlidingTabsPanel(Context context)
    {
        super(context);
        init();
    }

    public SlidingTabsPanel(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SlidingTabsPanel(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        menuWidthClosed = getResources().getDimensionPixelOffset(R.dimen.menu_width_closed);
        menuWidthExpand = getResources().getDimensionPixelOffset(R.dimen.menu_width_expand);

        setPanelSlideListener(this);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            actiondown = ev.getX();
            return super.onInterceptTouchEvent(ev);
        }

        return (ev.getAction() == MotionEvent.ACTION_MOVE &&
                ((isOpened && actiondown > ev.getX() && actiondown > menuWidthExpand) ||
                        (!isOpened && actiondown < ev.getX() && actiondown < menuWidthClosed)));
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset)
    {
        Log.e("Menu offset", "" + slideOffset);
    }

    @Override
    public void onPanelOpened(View panel)
    {
        Log.e("Is menu opened ?", "Yeah !");
    }

    @Override
    public void onPanelClosed(View panel)
    {
        Log.e("Is menu opened ?", "Nooo !");
    }
}
