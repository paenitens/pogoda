package com.krzysiek.pogoda;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    private String[] data;
    private Context ctx;

    public Adapter(Context ctx, String[] importeddata) {
        this.data = importeddata;
        this.ctx = ctx;
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txt;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder view_holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_adapter, parent, false);
            view_holder = new ViewHolder();
            view_holder.txt = (TextView) convertView.findViewById(R.id.textView1);

            convertView.setTag(view_holder);
        } else {
            view_holder = (ViewHolder) convertView.getTag();
        }

        view_holder.txt.setText(data[position]);

        return convertView;
    }

}
