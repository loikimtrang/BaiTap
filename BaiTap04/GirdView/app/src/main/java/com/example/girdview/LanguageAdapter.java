package com.example.girdview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LanguageAdapter extends BaseAdapter {

    private Context context;
    private String[] names;
    private int[] images;

    public LanguageAdapter(Context context, String[] names, int[] images) {
        this.context = context;
        this.names = names;
        this.images = images;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gird_item, parent, false);
        }

        ImageView imgLogo = convertView.findViewById(R.id.imgLogo);
        TextView txtName = convertView.findViewById(R.id.txtName);

        imgLogo.setImageResource(images[position]);
        txtName.setText(names[position]);

        convertView.setOnClickListener(v -> Toast.makeText(context, "Bạn đã chọn: " + names[position], Toast.LENGTH_SHORT).show());
        return convertView;
    }
}
