package mansonheart.com.realmrxnotifications.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import mansonheart.com.realmrxnotifications.R;
import mansonheart.com.realmrxnotifications.data.Event;
import rx.functions.Action1;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public final class EventsAdapter extends BaseAdapter {
    private final LayoutInflater inflater;

    private List<Event> items = Collections.emptyList();

    public EventsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setItems(List<Event> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Event getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getEventId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.category = (TextView) convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        Event item = getItem(position);
        CharSequence title = item.getTitle();
        CharSequence category = item.getCategory() != null
                ? item.getCategory().getName() : "";
        holder.title.setText(title);
        holder.category.setText(category);
        return convertView;
    }

    public static class ViewHolder {
        TextView title;
        TextView category;
    }
}
