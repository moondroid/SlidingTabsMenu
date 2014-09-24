package it.moondroid.slidingtabsmenu;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by marco.granatiero on 24/09/2014.
 */
public class ThemeUtils {

    public static int getColor(Context context, int attrId) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

    public static int getResourceId(Context context, int attrId) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.resourceId;
    }

}
