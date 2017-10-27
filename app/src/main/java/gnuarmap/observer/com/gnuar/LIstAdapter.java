package gnuarmap.observer.com.gnuar;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import static gnuarmap.observer.com.gnuar.R.layout.activity_list_item;


public class LIstAdapter extends ArrayAdapter<ListItem> {

    public LIstAdapter(Context context, ListItem[] Lists){
        super(context, R.layout.activity_list_item, Lists);
    }

@   Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListItem list = getItem(position);

        if(convertView == null){
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(activity_list_item,null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(list.titleId);

        TextView description=(TextView) convertView.findViewById(R.id.description);
        description.setText(list.descriptionId);

        return convertView;
    }
}

