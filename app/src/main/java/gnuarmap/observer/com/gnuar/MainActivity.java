package gnuarmap.observer.com.gnuar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter adapter = new LIstAdapter(this, ListItem.listItems);
        setListAdapter(adapter);
}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ListItem list = (ListItem)getListAdapter().getItem(position);
        startActivity(new Intent(this,list.activity));
    }
}
