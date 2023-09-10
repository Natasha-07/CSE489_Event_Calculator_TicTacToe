package edu.ewubd.cse489_2019_1_60_202;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private final Context context;
    private final ArrayList<Contact> values;

    public ContactAdapter(@NonNull Context context, @NonNull ArrayList<Contact> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.contact_row, parent, false);

        TextView Name = rowView.findViewById(R.id.tvtName);
        TextView Email = rowView.findViewById(R.id.tvEmail);
        ImageView photo = rowView.findViewById(R.id.photo);

        Name.setText(values.get(position).name);
        Email.setText(values.get(position).email);
        photo.setImageBitmap(values.get(position).photo);

        return rowView;
    }
}
