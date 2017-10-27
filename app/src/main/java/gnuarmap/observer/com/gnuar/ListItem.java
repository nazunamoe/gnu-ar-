package gnuarmap.observer.com.gnuar;

import android.support.v4.app.FragmentActivity;

import gnuarmap.observer.com.gnuar.Lists.AR_View;
import gnuarmap.observer.com.gnuar.Lists.Filtering;
import gnuarmap.observer.com.gnuar.Lists.GMap;

public class ListItem {
    public final int titleId;
    public final int descriptionId;
    public final Class<? extends FragmentActivity> activity;

    public ListItem(int titleId, int descriptionId, Class<?extends FragmentActivity> activity){
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activity = activity;
    }

    public static final ListItem[] listItems = {
            new ListItem(R.string.map_view_search,
                    R.string.map_view_desc, GMap.class),
            new ListItem(R.string.filtering_view,
                    R.string.filtering_view_desc, Filtering.class),
            new ListItem(R.string.ar_view,
                    R.string.ar_view_desc, AR_View.class)
    };
}
